package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewSearchedWordsPageController implements Initializable {
	@FXML
	private DuckHomeController mother;
	@FXML
	public AnchorPane viewSearchedWordsPane;
	@FXML
	public TableView<String> table;
	@FXML
	public TableColumn<String, String> columnWord;
	@FXML
	private Label labelTitle; //sementara private, change it to public if u want
	@FXML
	public Label labelSelectedAlphabets;
	@FXML
	public Label labelDescription;

	@FXML
	void filterButtonGo(ActionEvent event) {

	}

	@FXML
	void inputSearchGo(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void updateList(String range)
	{
		ArrayList<String> displayedWords = new ArrayList<String>();
		int startRange = (int) range.charAt(0);
		int endRange = (int) range.charAt(range.length()-1);
		System.out.println(startRange + " " + endRange);
		//boolean inRange = false;
		for (int i =0; i < mother.getWordList().size(); ++i)
		{
			if (mother.getWordList().get(i).length() > 0)
			{
				//System.out.println(mother.getWordList().get(i));
				//System.out.println((int)mother.getWordList().get(i).charAt(0));
				//System.out.println((int)'A');
				if (((int)  Character.toUpperCase(mother.getWordList().get(i).charAt(0))) > endRange)
				{
					i = mother.getWordList().size();
				}
				else if (((int)  Character.toUpperCase(mother.getWordList().get(i).charAt(0))) >= startRange)
				{
					//inRange = true;
					displayedWords.add(mother.getWordList().get(i));
				}

			}

		}
		System.out.println(range);

		ObservableList<String> tableWords = FXCollections.observableArrayList(displayedWords);
		table.setItems(tableWords);
		if (tableWords.size() > 0)
		{
			table.scrollTo(tableWords.get(0));
		}
	}



	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;
		//columnWord = new TableColumn<String, String>();
		//table.getColumns().add(columnWord);
		//https://stackoverflow.com/questions/34443678/javafx-how-to-show-string-in-tableview
		columnWord.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));

		//https://stackoverflow.com/questions/26563390/detect-doubleclick-on-row-of-tableview-javafx
		table.setRowFactory(tv -> {
			TableRow<String> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            String rowData = row.getItem();
		            //System.out.println(rowData);
		            mother.goToDefinitionPage(rowData);

		        }
			});
			return row;
		});

		//table.setItems(mother.dictionary.returnWordList());


	}

}
