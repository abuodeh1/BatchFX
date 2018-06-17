package application;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TreeViewSample extends Application {

	private final Node rootIcon = new ImageView(
			new Image(getClass().getResourceAsStream("department.png"))
	);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tree View Sample");        

		TreeItem<String> rootItem = new TreeItem<String> ("Inbox", rootIcon);
		rootItem.setExpanded(true);
		for (int i = 1; i < 16; i++) {
			TreeItem<String> item = new TreeItem<String> ("Message" + i);            
			rootItem.getChildren().add(item);
		}        

		TreeView<String> tree = new TreeView<String> (rootItem);        

		StackPane root = new StackPane();
		root.getChildren().add(tree);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}