package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class HelpPageController implements Initializable {
	@FXML
	private DuckHomeController mother;
	@FXML
	public Label labelBig;
	@FXML
	private JFXButton help1Button;
	@FXML
	private JFXButton help2Button;
	@FXML
	private JFXButton help3Button;
	@FXML
	private JFXButton help4Button;
	@FXML
	private JFXButton help5Button;
	@FXML
	private JFXButton help6Button;
	@FXML
	private JFXButton help7Button;
	@FXML
	private JFXButton help8Button;
	@FXML
	private JFXButton help9Button;
	@FXML
	private JFXButton help10Button;
	@FXML
	private JFXButton help11Button;
	@FXML
	private JFXButton help12Button;

	void launchHelpWindow(String helpFXMLFile) {// basically this launches a new
												// window that shows a specific
												// help window
		// This function launches the help window that receives a specific fxml
		// file for the help window
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/helpmenu/" + helpFXMLFile)); // main
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle("Help");
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();

			stage.setOnCloseRequest((WindowEvent event1) -> {
				stage.close();
		        System.gc();
		    });
		} catch (IOException e1) {
			System.out.println("failed to launch" + helpFXMLFile + "or file not found");
			e1.printStackTrace();

		}
	}

	@FXML
	void helpDucktionaryTour(ActionEvent event) {
		System.out.println("help1 pressed");
		launchHelpWindow("HelpDucktionaryTour.fxml");
	}

	@FXML
	void helpHowToSearch(ActionEvent event) {
		System.out.println("help2 pressed");
		launchHelpWindow("HelpSearch.fxml");
	}

	@FXML
	void helpAddWord(ActionEvent event) {
		System.out.println("help3 pressed");
		launchHelpWindow("HelpAddWord.fxml");
	}

	@FXML
	void helpRemoveWord(ActionEvent event) {
		System.out.println("help4 pressed");
		launchHelpWindow("HelpRemoveWord.fxml");

	}

	@FXML
	void helpTipsAndTricks(ActionEvent event) {
		System.out.println("help5 pressed");
		launchHelpWindow("HelpTipsAndTricks.fxml");
	}

	@FXML
	void helpKeyboardShortcut(ActionEvent event) {
		System.out.println("help6 pressed");
		launchHelpWindow("HelpKeyboardShortcut.fxml");
	}

	@FXML
	void helpFAQ(ActionEvent event) {
		System.out.println("help7 pressed");
		launchHelpWindow("HelpFAQ.fxml");
	}

	@FXML
	void helpSignIn(ActionEvent event) {
		System.out.println("help8 pressed");
		launchHelpWindow("HelpSignIn.fxml");
	}

	@FXML
	void helpUserManual(ActionEvent event) {
		System.out.println("help9 pressed woyyy");
	     //This code opens the documentation folder
        openDocumentationFolderInExplorer("User Manual");
	}

	@FXML
	void helpProgramManual(ActionEvent event) {
		System.out.println("help10 pressed");
		//This code opens the documentation folder
        openDocumentationFolderInExplorer("Program Manual");
	}

	private void openDocumentationFolderInExplorer(String folderName) {
		try {
			Desktop.getDesktop().open(new File(System.getProperty("user.dir")+"/"+folderName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Folder not found/not accessible");
		}
	}

	@FXML
	void helpManageSettings(ActionEvent event) {
		System.out.println("help11 pressed");

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Reset Ducktionary to Default Database");
		alert.setHeaderText("Hey, since were still in development all we have here in our settings page is to reset the ducktionary to the default database");
		alert.setContentText("So do you want to reset Ducktionary to the default database?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		   System.out.println("Reset Database"); //TODO: reset database functionality

		} else {
		    System.out.println("Canceled Reset Database");
		}
	}

	@FXML
	void helpAbout(ActionEvent event) {
		System.out.println("help12 pressed");
		launchHelpWindow("HelpAbout.fxml");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// James


	}

	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;

	}

}
