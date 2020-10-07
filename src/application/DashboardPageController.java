package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class DashboardPageController implements Initializable {
    @FXML
    private Label username;
	@FXML
	private DuckHomeController mother;
	@FXML
	public ListView historyList;
	@FXML
	public ListView atAGlanceList;
	@FXML
	public ListView favoriteList;
	@FXML
	private JFXButton dashLoginButton;
	@FXML
	public Label titleLabel;
	@FXML
	public JFXButton dashLogoutButton;
void setLogoutButtonEnabled(Boolean bool) {
	if(bool==true) {
		dashLogoutButton.setDisable(false);
		dashLoginButton.setDisable(true);
	}else {
		dashLogoutButton.setDisable(true);
		dashLoginButton.setDisable(false);
	}

}
	@FXML
	private JFXButton dashGetStartedButton;

	@FXML
	private AnchorPane searchHistoryPane;

	@FXML
	private AnchorPane atGlancePane;

	@FXML
	private AnchorPane favWordPane;

	@FXML
	private JFXButton addWordsShortcut;

	@FXML
	private JFXButton removeWordsShortcut;

	@FXML
	private JFXButton openProfileShortcut;

	@FXML
	private JFXButton viewHelpShortcut;

	@FXML
	private JFXButton wordOfTheDayShortcut;

	// --Word of the day--//
	@FXML
	void wordOfTheDayGo(ActionEvent event) {
		System.out.println("wofday!!");
		wordOfTheDay();
	}
	// Show word of the day//

	public void wordOfTheDay() {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/application/WordOfTheDayPage.fxml")); // main

			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle("Word of The Day");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
			stage.setOnCloseRequest((WindowEvent event1) -> {
				stage.close(); //closes stage
		        System.gc(); //frees memory
		    });
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// END OF: Word of the day--//
	@FXML
	void addWordsGo(ActionEvent event) {
		System.out.println("addWordsGo");
		mother.triggerAddWordButton();
	}

	@FXML
	void getStarted(ActionEvent event) {
		System.out.println("getStarted");
		mother.putCursorOnSearchBar();
	}

    @FXML
    void login(ActionEvent event) {
        System.out.println("login");
        mother.tabPaneMain.getSelectionModel().select(6);
    }

    @FXML
    void logout(ActionEvent event)
    {
        mother.database.UpdateProfileTextFile();
        mother.database.Logout();
        mother.DashboardPageController.getUsername().setText("");
        setLogoutButtonEnabled(false);


        System.out.println("logout");
    }

	@FXML
	void openProfileGo(ActionEvent event) { //ganti jadi view quick tutorial (tapi function masih nama sama)
		System.out.println("View the quick tutorial");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/helpmenu/HelpDucktionaryTour.fxml")); // main
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
			System.out.println("failed to launch HelpDucktionaryTour.fxml or file not found");
			e1.printStackTrace();

		}
	}

	@FXML
	void removeWordsGo(ActionEvent event) {
		System.out.println("removeWordsGo");
		mother.tabPaneMain.getSelectionModel().select(3);
	}

	@FXML
	void viewHelpGo(ActionEvent event) {
		System.out.println("viewHelpGo");
		mother.tabPaneMain.getSelectionModel().select(2);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// James
		setLogoutButtonEnabled(false);

	}

	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;

	}
    //Getters

    public Label getUsername() {
        return username;
    }

}
