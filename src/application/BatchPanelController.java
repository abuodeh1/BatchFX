package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

public class BatchPanelController implements Initializable {
	@FXML
	private TreeTableColumn<RowWrapper, String> transactionClm;
	@FXML
	private TreeTableColumn<RowWrapper, String> groupClm;
	@FXML
	private TreeTableColumn<RowWrapper, Number> pagesClm;
	@FXML
	private TreeTableColumn<RowWrapper, Number> imagesClm;
	
	List<TreeItem<RowWrapper>> treeItems = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	class RowWrapper{
		
		SimpleStringProperty transactionID;
		
		SimpleStringProperty groupID;
		
		SimpleIntegerProperty numberOfPages;
		
		SimpleIntegerProperty numberOfImages;

		public SimpleStringProperty getTransactionID() {
			return transactionID;
		}

		public void setTransactionID(SimpleStringProperty transactionID) {
			this.transactionID = transactionID;
		}

		public SimpleStringProperty getGroupID() {
			return groupID;
		}

		public void setGroupID(SimpleStringProperty groupID) {
			this.groupID = groupID;
		}

		public SimpleIntegerProperty getNumberOfPages() {
			return numberOfPages;
		}

		public void setNumberOfPages(SimpleIntegerProperty numberOfPages) {
			this.numberOfPages = numberOfPages;
		}

		public SimpleIntegerProperty getNumberOfImages() {
			return numberOfImages;
		}

		public void setNumberOfImages(SimpleIntegerProperty numberOfImages) {
			this.numberOfImages = numberOfImages;
		}
	
	}


}
