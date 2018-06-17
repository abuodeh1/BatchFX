package application;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class BatchPanelController implements Initializable {
	@FXML
	private TreeTableColumn<RowWrapper, Number> transactionClm;
	@FXML
	private TreeTableColumn<RowWrapper, Number> groupClm;
	@FXML
	private TreeTableColumn<RowWrapper, Number> pagesClm;
	@FXML
	private TreeTableColumn<RowWrapper, Number> imagesClm;
	
	TreeItem<RowWrapper> root = new TreeItem<>(new RowWrapper(0, 0, 0, 0));
	
	@FXML	
	TreeTableView<RowWrapper> treeTableView; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Batch batch = getResponseAsPOJO(Batch.class, new String(Files.readAllBytes(
					new File("E:\\mywork\\BatchFX\\src\\application\\00020150618 141054 101.xml").toPath())));

			batch.getTransaction().stream().forEach(transaction -> {
				int pages = 0;
				int images = 0;
				int groups = 0;
				TreeItem<RowWrapper> transactiontTreeItem = new TreeItem<>(new RowWrapper(transaction.transactionID, groups, pages, images));
				transaction.getGroup().forEach(group -> {
					
					TreeItem<RowWrapper> groupTreeItem = new TreeItem<>(new RowWrapper(transaction.transactionID, group.groupID, pages, images));
					group.getPage().forEach(page -> {
						groupTreeItem.getChildren().add(new TreeItem<>(new RowWrapper(transaction.transactionID, group.groupID, group.getPage().size(), page.getImage().size())));
					});	
					transactiontTreeItem.getChildren().add(groupTreeItem);
				});			
				root.getChildren().add(transactiontTreeItem);
			});
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		transactionClm.setCellValueFactory((TreeTableColumn.CellDataFeatures<RowWrapper, Number> param) -> param.getValue().getValue().getTransactionID() );
		groupClm.setCellValueFactory((TreeTableColumn.CellDataFeatures<RowWrapper, Number> param) -> param.getValue().getValue().getGroupID() );
		pagesClm.setCellValueFactory((TreeTableColumn.CellDataFeatures<RowWrapper, Number> param) -> param.getValue().getValue().getNumberOfPages() );
		imagesClm.setCellValueFactory((TreeTableColumn.CellDataFeatures<RowWrapper, Number> param) -> param.getValue().getValue().getNumberOfImages() );
		
		treeTableView.getColumns().setAll(transactionClm, groupClm, pagesClm, imagesClm);
		treeTableView.setRoot(root);
		treeTableView.setShowRoot(false);
	
	}
	


	public <T> T getResponseAsPOJO(Class<T> classType, String xmlResponse) {

		T ngoResponse = null;

		try {
			
			StringReader stringReader = new StringReader(xmlResponse);

			JAXBContext jaxbContext = JAXBContext.newInstance(classType);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			ngoResponse = (T) jaxbUnmarshaller.unmarshal(stringReader);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return ngoResponse;
	}

	class RowWrapper{
		
		SimpleLongProperty transactionID;
		
		SimpleLongProperty groupID;
		
		SimpleIntegerProperty numberOfPages;
		
		SimpleIntegerProperty numberOfImages;

		public RowWrapper(long transactionID, long groupID, int numberOfPages, int numberOfImages) {
			this.transactionID = new SimpleLongProperty(transactionID);
			this.groupID = new SimpleLongProperty(groupID);
			this.numberOfPages = new SimpleIntegerProperty(numberOfPages);
			this.numberOfImages = new SimpleIntegerProperty(numberOfImages);
		}
		
		public SimpleLongProperty getTransactionID() {
			return transactionID;
		}

		public void setTransactionID(SimpleLongProperty transactionID) {
			this.transactionID = transactionID;
		}

		public SimpleLongProperty getGroupID() {
			return groupID;
		}

		public void setGroupID(SimpleLongProperty groupID) {
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


