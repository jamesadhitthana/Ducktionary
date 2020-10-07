package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class DebuggingPageController implements Initializable {
	@FXML
	private DuckHomeController mother;
	@FXML
	public Label labelReplaceMe;
	@FXML
	private JFXTextField debuggingTextField;
	@FXML
	private JFXButton debuggingSendButton;
	@FXML
	private JFXButton debuggingOpenDefButton;
	@FXML
	private JFXButton modDashButton;
	@FXML
	private JFXButton modSearchButton;
	@FXML
	private JFXButton modCustomizeButton;
	@FXML
	private JFXButton modHelpButton;
	@FXML
    private JFXButton modOpenDefButton;

    @FXML
    void modOpenDefButtonGo(ActionEvent event) {
    	mother.tabPaneMain.getSelectionModel().select(5);
    }

	@FXML // replace the top label with the textfield
	void debuggingSendButtonGo(ActionEvent event) {
		System.out.println("Debug button pressed");
		labelReplaceMe.setText(debuggingTextField.getText());
	}

	@FXML // send the input text to mother setDefinitionWord() function.
	void debuggingOpenDefButtonGo(ActionEvent event) {
		System.out.println("click the duck above");
		mother.setDefinitionPageWord(labelReplaceMe.getText());
	}

	@FXML
	void modDashButtonGo(ActionEvent event) {
		System.out.println("modDashButtonGo");
		mother.setDashboardPageTitle(labelReplaceMe.getText());
	}

	@FXML
	void modCustomizeButtonGo(ActionEvent event) {
		System.out.println("modCustomizeButtonGo");
		mother.setCustomizePageTitle(labelReplaceMe.getText());
	}

	@FXML
	void modHelpButtonGo(ActionEvent event) {
		System.out.println("modHelpButtonGo");
		mother.setHelpPageTitle(labelReplaceMe.getText());
	}

	@FXML
	void modSearchButtonGo(ActionEvent event) {
		System.out.println("modSearchButtonGo");
		mother.setSearchPageTitle(labelReplaceMe.getText());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// James

	}

	public void init(DuckHomeController duckHomeController) {
		// Set the Mother Controller
		mother = duckHomeController;

	}
}