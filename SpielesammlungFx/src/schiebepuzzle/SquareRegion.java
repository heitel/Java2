package schiebepuzzle;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class SquareRegion extends Region {

	public SquareRegion() {
		super();
	}

	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		
		double width = getWidth();
		double height = getHeight();
		double min = Math.min(width, height)-16;
		double x = (width-min)/2;
		double y = (height-min)/2;
		
		for (Node node : getChildren()) {
			node.resizeRelocate(x, y, min, min);
			ObservableList<Node> list = (ObservableList<Node>)((GridPane)node).getChildren();
			for (int i=0; i<list.size();i++) {
				PuzzlePart part = (PuzzlePart)list.get(i);
				part.resize(min/4, min/4);
			}
		}
	}

	public void add(GridPane grid) {
		getChildren().add(grid);
	}
}
