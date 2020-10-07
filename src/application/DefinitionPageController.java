package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;

public class DefinitionPageController implements Initializable {
	@FXML
	private DuckHomeController mother;
	@FXML
	public Label word; // word1
	@FXML
	public Hyperlink pos1; // partsOfSpeech1
	@FXML
	public Hyperlink pos2; // partsOfSpeech1
	@FXML
	public Label definition1; // definition1
	@FXML
	public Label origin1; // origin1
	@FXML
	public Label definition2;
	@FXML
	public Label defnum2;
	@FXML
	public Label origin2;
	@FXML
	public Label defnum3;
	@FXML
	public Label definition3;
	@FXML
	public Label origin3;
	@FXML
	private JFXButton defRefreshButton; // refresh button
	@FXML
	private JFXButton defFavoriteButton; // refresh button

	@FXML
	void defFavoriteButtonGo(ActionEvent event) {
		if (mother.isUserLoggedIn == true) {
			mother.database.getLoggedInAs().getFavorite().add(word.getText().toLowerCase());
			mother.DashboardPageController.favoriteList.setItems(mother.database.getLoggedInAs().getFavorite());
			System.out.println("Favorite word successful");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Successfully added favorite word");
			alert.setHeaderText(null);
			alert.setContentText("Favorite word added successfuly!");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("You forgot to log in");
			alert.setHeaderText(null);
			alert.setContentText("You have to log-in before you use this feature");

			alert.showAndWait();
		}

	}

	@FXML
	void defRefreshButtonGo(ActionEvent event) {
		System.out.println("refresh button pressed");
	}

	@FXML
	void onPos1Clicked(ActionEvent event) {
		mother.setDefinitions(pos1.getText());
		pos1.setTextFill(Paint.valueOf("#59BD55"));
		pos2.setTextFill(Paint.valueOf("#ff9600"));
	}

	@FXML
	void onPos2Clicked(ActionEvent event) {
		mother.setDefinitions(pos2.getText());
		pos1.setTextFill(Paint.valueOf("#ff9600"));
		pos2.setTextFill(Paint.valueOf("#59BD55"));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// James

	}

	public void resetColor() {
		pos1.setTextFill(Paint.valueOf("#59BD55"));
		pos2.setTextFill(Paint.valueOf("#ff9600"));

	}

	public void init(DuckHomeController duckHomeController) {
		// Set the Mother Controller
		mother = duckHomeController;
	}
}
