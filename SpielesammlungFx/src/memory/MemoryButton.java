package memory;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MemoryButton extends Button {
	private ImageView iv;
	private String text;

	public MemoryButton(String text) {
		this.text = text;
		iv = new ImageView(getClass().getResource(text).toExternalForm()); 
		setPrefWidth(50);
		setPrefHeight(50);
	}

	public String getImgText() {
		return text;
	}

	public boolean equals(MemoryButton button) {
		return text.equals(button.getImgText());
	}

	public void showImage() {
		setGraphic(iv);
	}

	public void hideImage() {
		setGraphic(null);
	}
}
