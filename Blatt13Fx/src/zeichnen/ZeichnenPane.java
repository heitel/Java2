package zeichnen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ZeichnenPane extends BorderPane {
	public enum Tool {
		ARROW, SQUARE, CIRCLE, POLY
	};

	private File f;
	private Stage primaryStage;
	private static final String TITLE = "Vektorgrafik";
	private ZeichnenCanvas canvas = new ZeichnenCanvas();

	public ZeichnenPane(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle(TITLE);

		MenuBar bar = buildMenu();
		ToolBar tb = buildToolBar();
		VBox top = new VBox(bar, tb);
		setTop(top);
		setCenter(canvas);
	}

	private ToolBar buildToolBar() {
		ToggleButton arrowButton = new ToggleButton("",
				new ImageView(getClass().getResource("arrow.gif").toExternalForm()));
		arrowButton.setOnAction(e -> canvas.setTool(Tool.ARROW));
		ToggleButton squareButton = new ToggleButton("",
				new ImageView(getClass().getResource("square.gif").toExternalForm()));
		squareButton.setOnAction(e -> canvas.setTool(Tool.SQUARE));
		ToggleButton circleButton = new ToggleButton("",
				new ImageView(getClass().getResource("circle.gif").toExternalForm()));
		circleButton.setOnAction(e -> canvas.setTool(Tool.CIRCLE));
		ToggleButton polyButton = new ToggleButton("",
				new ImageView(getClass().getResource("poly.gif").toExternalForm()));
		polyButton.setOnAction(e -> canvas.setTool(Tool.POLY));
		ToggleGroup group = new ToggleGroup();
		group.getToggles().addAll(arrowButton, squareButton, circleButton, polyButton);

		return new ToolBar(arrowButton, squareButton, circleButton, polyButton);
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

	private void doNew(ActionEvent e) {
		primaryStage.setTitle(TITLE);
		canvas.clearData();
	}

	private void doOpen(ActionEvent e) {
		try {
			FileChooser chooser = new FileChooser();
			chooser.setInitialDirectory(new File(System.getProperty("user.home")));
			File f = chooser.showOpenDialog(primaryStage);
			if (f != null) {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<Shape> data = (ArrayList<Shape>) ois.readObject();
				canvas.setData(data);
				ois.close();
				primaryStage.setTitle(TITLE + " - " + f.getName());
				this.f = f;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private void doSave(ActionEvent e) {
		try {
			if (f != null) {
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(canvas.getData());
				oos.close();
			} else {
				doSaveAs(e);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void doSaveAs(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File f = chooser.showSaveDialog(primaryStage);
		if (f != null) {
			this.f = f;
			doSave(e);
			primaryStage.setTitle(TITLE + " - " + f.getName());
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
					System.out.println("width = " + width + " height = " + height);

					StackPane stack = new StackPane();
					ArrayList<Shape> data = canvas.getData();
					for (int i = 0; i < data.size(); i++) {
						Shape s = data.get(i);
						javafx.scene.shape.Shape fxS = s.convertToFx();
						fxS.setManaged(false);
						stack.getChildren().add(fxS);
					}
					Bounds b = stack.getBoundsInLocal();
					System.out.println(b);
					double scaleX = width / b.getWidth();
					double scaleY = height / b.getHeight();
					double scale = Math.min(scaleX, scaleY);
					System.out.println("scale = " + scale);
					if (scaleX < scaleY) {
						double ty = (height - b.getHeight() * scale) / 2;
						System.out.println("ty = " + ty);
						stack.setTranslateY(ty);
					} else {
						double tx = (width - b.getWidth() * scale) / 2;
						System.out.println("tx = " + tx);
						stack.setTranslateX(tx);
					}

					stack.setScaleX(scale);
					stack.setScaleY(scale);
					boolean success = job.printPage(stack);
					if (success) {
						job.endJob();
					}
				}
			}
		}
	}
}
