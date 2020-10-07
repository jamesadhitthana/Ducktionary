package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SearchPageController implements Initializable {
	@FXML
	private DuckHomeController mother;

	@FXML
	public Label labelBig;
	@FXML
	private JFXButton abcButton;
	@FXML
	private JFXButton defButton;
	@FXML
	private JFXButton ghiButton;
	@FXML
	private JFXButton jklButton;
	@FXML
	private JFXButton mnoButton;
	@FXML
	private JFXButton pqrButton;
	@FXML
	private JFXButton stuButton;
	@FXML
	private JFXButton vwxyzButton;

	@FXML
	void abcClicked(ActionEvent event) {
		System.out.println("abcClicked clicked");
		mother.setViewSearchedWordsPageSelectedAlphabets("ABC");
		mother.DefinitionPageController.resetColor();
		mother.tabPaneMain.getSelectionModel().select(9);
		//load the arraylist for the tableview here
	}

	@FXML
	void defClicked(ActionEvent event) {
		System.out.println("defClicked");
		mother.setViewSearchedWordsPageSelectedAlphabets("DEF");
		mother.DefinitionPageController.resetColor();
		mother.tabPaneMain.getSelectionModel().select(9);
		//load the arraylist for the tableview here
	}

	@FXML
	void ghiClicked(ActionEvent event) {
		System.out.println("ghiClicked");
		mother.setViewSearchedWordsPageSelectedAlphabets("GHI");
		mother.DefinitionPageController.resetColor();
		mother.tabPaneMain.getSelectionModel().select(9);
		//load the arraylist for the tableview here
	}

	@FXML
	void jklClicked(ActionEvent event) {
		System.out.println("jklClicked");
		mother.setViewSearchedWordsPageSelectedAlphabets("JKL");
		mother.DefinitionPageController.resetColor();
		mother.tabPaneMain.getSelectionModel().select(9);
		//load the arraylist for the tableview here
	}

	@FXML
	void mnoClicked(ActionEvent event) {
		System.out.println("mnoClicked");
		mother.setViewSearchedWordsPageSelectedAlphabets("MNO");
		mother.DefinitionPageController.resetColor();
		mother.tabPaneMain.getSelectionModel().select(9);
		//load the arraylist for the tableview here
	}

	@FXML
	void pqrClicked(ActionEvent event) {
		System.out.println("pqrClicked");
		mother.setViewSearchedWordsPageSelectedAlphabets("PQR");
		mother.DefinitionPageController.resetColor();
		mother.tabPaneMain.getSelectionModel().select(9);
		//load the arraylist for the tableview here
	}

	@FXML
	void stuClicked(ActionEvent event) {
		System.out.println("stuClicked");
		mother.setViewSearchedWordsPageSelectedAlphabets("STU");
		mother.DefinitionPageController.resetColor();
		mother.tabPaneMain.getSelectionModel().select(9);
		//load the arraylist for the tableview here
	}

	@FXML
	void vwxyzClicked(ActionEvent event) {
		System.out.println("vwxyzClicked");
		mother.setViewSearchedWordsPageSelectedAlphabets("VWXYZ");
		mother.DefinitionPageController.resetColor();
		mother.tabPaneMain.getSelectionModel().select(9);
		//load the arraylist for the tableview here
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// James

	}

	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;

	}

}
