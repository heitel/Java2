package digitaleBildverarbeitung;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BildverarbeitungsPane extends BorderPane {
	private static final String TITLE = "Digitale Bildverarbeitung - ";

	private static final String[][] TXT = { { "Rot", "RedFilter" }, { "Grün", "GreenFilter" }, { "Blau", "BlueFilter" },
			{ "-", "-" }, { "Negativ", "NegativFilter" }, { "-", "-" }, { "Graustufen", "BWFilter" },
			{ "Sepia", "SepiaFilter" }, { "-", "-" }, { "Horizontal spiegeln", "HorizontalFlipFilter" },
			{ "Vertical spiegeln", "VerticalFlipFilter" }, { "90° im Uhrzeigersinn drehen", "RotateCWFilter" },
			{ "-", "-" }, { "Weichzeichner", "BlurFilter" }, { "Scharfzeichner", "SharpenFilter" }, { "-", "-" },
			{ "SobelX", "SobelXFilter" }, { "SobelY", "SobelYFilter" }, { "Kantendetektor", "SobelFilter" } };

	private ImageCanvas canvas = new ImageCanvas();
	private File f;
	
	public BildverarbeitungsPane() {
		MenuBar bar = buildMenu();
		bar.getStyleClass().add("menuBar");
		setTop(bar);
		setCenter(canvas);
		readFile(new File("ziege.JPG"));
	}

	private MenuBar buildMenu() {
		Menu file = new Menu("Datei");
		MenuItem newItem = new MenuItem("Neu");
		newItem.setDisable(true);
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

		Menu edit = new Menu("Bearbeiten");
		MenuItem backItem = new MenuItem("Zurück");
		backItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
		backItem.setOnAction(e -> canvas.doBack(e));
		edit.getItems().add(backItem);

		Menu filter = new Menu("Filter");
		for (int i = 0; i < TXT.length; i++) {
			if (TXT[i][0].equals("-")) {
				filter.getItems().add(new SeparatorMenuItem());
			} else {
				MenuItem item = new MenuItem(TXT[i][0]);
				item.setUserData(TXT[i][1]);
				item.setOnAction(e -> canvas.doFilter(e));
				filter.getItems().add(item);
			}
		}

		return new MenuBar(file, edit, filter);
	}

	private void doNew(ActionEvent e) {
	}

	private void doOpen(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File("."));
		f = chooser.showOpenDialog(getScene().getWindow());
		if (f != null) {
			readFile(f);
			((Stage)getScene().getWindow()).setTitle(TITLE + f.getName());
		}
	}

	private void readFile(File f) {
		try {
			BufferedImage bimg = ImageIO.read(f);
			WritableImage wimg = SwingFXUtils.toFXImage(bimg, null);
			canvas.setImg(wimg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doSave(ActionEvent e) {
		if (f != null) {
			writeFile();
			((Stage)getScene().getWindow()).setTitle(TITLE + f.getName());
		}
	}

	private void writeFile() {
		try {
			WritableImage wimg = canvas.getImg();
			BufferedImage bimg = SwingFXUtils.fromFXImage(wimg, null);
			ImageIO.write(bimg, "png", f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doSaveAs(ActionEvent e) {
			FileChooser chooser = new FileChooser();
			chooser.setInitialDirectory(new File("."));
			f = chooser.showSaveDialog(null);
			doSave(null);
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
					WritableImage img = canvas.getImg();
					
					double imgW = img.getWidth();
					double imgH = img.getHeight();
					double scale = Math.min(height / imgH, width / imgW);
					ImageView iv = new ImageView(img);
					
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
}
