package paint;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.FileChooser;

public class PaintPane extends BorderPane {
	private final static int WIDTH = 800;
	private final static int HEIGHT = 600;
	private final static String[] NAME = { "rau.jpg", "holz.jpg", "struktur.jpg" };

	private Pane back = new Pane();
	private Canvas canvas = new Canvas(WIDTH, HEIGHT);
	private Point2D oldPoint;
	private ColorPicker picker = new ColorPicker(Color.BLACK);
	private Slider thick = new Slider(0, 100, 15);
	private ComboBox<String> background = new ComboBox<>();
	private ColorPicker backPicker = new ColorPicker(Color.WHITE);
	private StackPane stack;
	private File f;

	public PaintPane() {
		VBox box = new VBox(buildMenu(), buildToolBar());
		setTop(box);
		buildPane();
	}

	private MenuBar buildMenu() {
		Menu file = new Menu("Datei");
		MenuItem newItem = new MenuItem("Neu");
		newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
		newItem.setOnAction(e -> doNew(e));
		MenuItem open = new MenuItem("Öffnen");
		open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
		open.setOnAction(e -> doOpen(e));
		MenuItem save = new MenuItem("Sichern");
		save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
		save.setOnAction(e -> doSave(e));
		MenuItem saveAs = new MenuItem("Sichern unter...");
		saveAs.setOnAction(e -> doSaveAs(e));
		MenuItem print = new MenuItem("Drucken");
		print.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.SHORTCUT_DOWN));
		print.setOnAction(e -> doPrint(e));
		MenuItem quit = new MenuItem("Beenden");
		quit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));
		quit.setOnAction(e -> System.exit(0));
		file.getItems().addAll(newItem, open, save, saveAs, new SeparatorMenuItem(), print, new SeparatorMenuItem(),
				quit);

		return new MenuBar(file);
	}

	private ToolBar buildToolBar() {
		picker.setTooltip(new Tooltip("Vordergrundfarbe"));
		thick.setTooltip(new Tooltip("Strichstärke"));
		thick.setShowTickMarks(true);
		thick.setShowTickLabels(true);

		backPicker.setTooltip(new Tooltip("Hintergrundfarbe"));
		backPicker.setOnAction(e -> doBackPick(e));
		backPicker.setDisable(true);

		background.getItems().addAll("Einfarbig", "Transparent", "Regenbogen", "Tapete", "Holz", "Struktur");
		background.getSelectionModel().select(1);
		background.setOnAction(e -> doBackgroundStyle(e));
		return new ToolBar(picker, thick, new Separator(), background, backPicker);
	}

	private void buildPane() {
		stack = new StackPane(back, canvas);
		AnchorPane anchor = new AnchorPane(stack);
		AnchorPane.setLeftAnchor(stack, 3d);
		AnchorPane.setRightAnchor(stack, 3d);
		AnchorPane.setTopAnchor(stack, 3d);
		AnchorPane.setBottomAnchor(stack, 3d);
		Pane pane = new Pane(anchor);
		setCenter(pane);

		canvas.setOnMouseDragged(e -> drag(e));
		canvas.setOnMousePressed(e -> click(e));
	}

	private void doBackPick(ActionEvent e) {
		setBackColor();
	}

	private void setBackColor() {
		back.setStyle("-fx-background-color: " + toRGBCode(backPicker.getValue()) + ";");
	}

	private void doBackgroundStyle(ActionEvent e) {
		int sel = background.getSelectionModel().getSelectedIndex();
		backPicker.setDisable(true);
		switch (sel) {
		case 0:
			backPicker.setDisable(false);
			setBackColor();
			break;
		case 1:
			back.setStyle("-fx-background-color: transparent;");
			break;
		case 2:
			back.setStyle("-fx-background-color: linear-gradient(to top left, red, yellow, green, blue, magenta);");
			break;
		case 3:
		case 4:
		case 5:
			String img = getClass().getResource(NAME[sel - 3]).toExternalForm();
			back.setStyle("-fx-background-image: url('" + img + "');");
			break;
		default:
			break;
		}
	}

	private static String toRGBCode(Color c) {
		return String.format("#%02X%02X%02X%02X", (int) (c.getRed() * 255), (int) (c.getGreen() * 255),
				(int) (c.getBlue() * 255), (int) (c.getOpacity() * 255));
	}

	private void doNew(ActionEvent e) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	private void doOpen(ActionEvent ev) {
		try {
			FileChooser chooser = new FileChooser();
			chooser.setInitialDirectory(new File("."));
			File f = chooser.showOpenDialog(getScene().getWindow());
			if (f != null) {
				BufferedImage bimg = ImageIO.read(f);
				WritableImage wimg = SwingFXUtils.toFXImage(bimg, null);
				stack.getChildren().remove(1);
				canvas = new Canvas(wimg.getWidth(), wimg.getHeight());
				stack.getChildren().add(canvas);
				GraphicsContext gc = canvas.getGraphicsContext2D();
				gc.drawImage(wimg, 0, 0);
				canvas.setOnMouseDragged(e -> drag(e));
				canvas.setOnMousePressed(e -> click(e));
				this.f = f;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void doSave(ActionEvent e) {
		try {
			if (f != null) {
				SnapshotParameters param = new SnapshotParameters();
				param.setFill(Color.TRANSPARENT);
				WritableImage wimg = stack.snapshot(param, null);
				BufferedImage bimg = SwingFXUtils.fromFXImage(wimg, null);
				ImageIO.write(bimg, "png", f);
			} else {
				doSaveAs(null);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void doSaveAs(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File("."));
		File f = chooser.showSaveDialog(getScene().getWindow());
		if (f != null) {
			this.f = f;
			doSave(null);
		}
	}

	private void doPrint(ActionEvent e) {
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job != null) {
			if (job.showPageSetupDialog(null)) {
				if (job.showPrintDialog(null)) {
					JobSettings js = job.getJobSettings();
					PageLayout layout = js.getPageLayout();

					double height = layout.getPrintableHeight();
					double width = layout.getPrintableWidth();

					double imgW = stack.getWidth();
					double imgH = stack.getHeight();
					double scale = Math.min(height / imgH, width / imgW);

					SnapshotParameters param = new SnapshotParameters();
					param.setFill(Color.TRANSPARENT);
					WritableImage wimg = stack.snapshot(param, null);
					ImageView iv = new ImageView(wimg);

					iv.setScaleX(scale);
					iv.setScaleY(scale);
					iv.setTranslateX((width - imgW) / 2);
					iv.setTranslateY((height - imgH) / 2);
					boolean success = job.printPage(iv);
					if (success) {
						job.endJob();
					}
				}
			}
		}
	}

	private void click(MouseEvent e) {
		oldPoint = null;
	}

	private void drag(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		
		if (oldPoint != null) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setStroke(picker.getValue());
			gc.setLineCap(StrokeLineCap.ROUND);
			gc.setLineWidth(thick.getValue());
			gc.strokeLine(oldPoint.getX(), oldPoint.getY(), x, y);
		}
		oldPoint = new Point2D(x, y);
	}
}
