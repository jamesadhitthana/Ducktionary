package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;

public class CustomizePageController implements Initializable {
	@FXML
	private DuckHomeController mother;
	@FXML
	public Label labelBig;
	@FXML
	public JFXButton add;

	@FXML
	void searchWord(ActionEvent event)
	{
		setCustomizePageList();
	}

	@FXML
	private AnchorPane pane;

	@FXML
	public TableView<ArrayList<String>> table;
	@FXML
	public TableColumn<ArrayList<String>, String> wordCol;
	@FXML
	public TableColumn<ArrayList<String>, String> posCol;
	@FXML
	public TableColumn<ArrayList<String>, String> originCol;
	@FXML
	public TableColumn<ArrayList<String>, String> defCol;

	@FXML
	private JFXTextField inputWord;

	@FXML
	private JFXButton remove;

	@FXML
	void addClick(ActionEvent event) {
		System.out.println("addClick");
		mother.tabPaneMain.getSelectionModel().select(8);
	}


	@FXML
	void inputWordGo(ActionEvent event)
	{
		System.out.println("inputWordGo");
		setCustomizePageList();
	}

	@FXML
	void removeClick(ActionEvent event) {
		System.out.println("removeClick");
        mother.tabPaneMain.getSelectionModel().select(11);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// James

	}



	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;

		ObservableList<ArrayList<String>> displayWords = FXCollections.observableArrayList(mother.returnDictionary().returnDisplayDictionary());

		//String wordLine = mother.dictionary.returnWordLine(wordKey);
		//ArrayList<ArrayList<String>> wordContent = dictionary.splitLine(wordLine);
		//definitionContent.removeAll(definitionContent);
		//definitionContent.add(dictionary.splitDefinition(wordContent.get(1).get(0)));

		wordCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
		posCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
		originCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
		defCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));
		table.setItems(displayWords);

		//https://stackoverflow.com/questions/26563390/detect-doubleclick-on-row-of-tableview-javafx
				table.setRowFactory(tv -> {
					TableRow<ArrayList<String>> row = new TableRow<>();
					row.setOnMouseClicked(event -> {
				        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
				            String rowData = row.getItem().get(0);
				            //System.out.println(rowData);
				            mother.goToDefinitionPage(rowData);

				        }

					});
					return row;
				});
	}

	//Customize page stuff
	public void setCustomizePageList()
	{
		ObservableList<ArrayList<String>> displayWordsNew = FXCollections.observableArrayList(mother.returnDictionary().returnDisplayDictionary(inputWord.getText()));

		//System.out.println(displayWordsNew);
		table.setItems(displayWordsNew);
		//mother.CustomizePageController.table.setItems(displayWordsNew);
	}
	//Customize page stuff

}
