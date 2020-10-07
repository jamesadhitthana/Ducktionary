package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class WordNotFoundPageController implements Initializable {
	@FXML
	private DuckHomeController mother;
	@FXML
	private JFXButton buttonAddWord;

	@FXML
	void addWordGo(ActionEvent event) {

		mother.tabPaneMain.getSelectionModel().select(8);
		System.out.println("add word button pressed");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;

	}
}
