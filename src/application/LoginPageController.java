package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
	@FXML
	private DuckHomeController mother;
	@FXML
	public JFXTextField user;
	@FXML
	public JFXPasswordField password;
	@FXML
    JFXButton signup;
	@FXML
    JFXButton login;
	@FXML
	ImageView imageBeard;
	@FXML
	AnchorPane loginPane;

	@FXML
	void makeLogin(ActionEvent event) {
		String username = user.getText();
		String pass = password.getText();

		boolean status = mother.database.Login(username, pass);

		if(status)
		{
			mother.DashboardPageController.getUsername().setText("Logged in as " + mother.database.getLoggedInAs().getUsername());

			ObservableList<String> atAGlance = FXCollections.observableArrayList();
			for (String s : mother.database.getLoggedInAs().getStats())
			{
				String splitResult[] = s.split(":");
				atAGlance.add(splitResult[0] + ":" + splitResult[1]);
			}

			mother.DashboardPageController.atAGlanceList.setItems(atAGlance);
			mother.DashboardPageController.historyList.setItems(mother.database.getLoggedInAs().getHistory());
			mother.DashboardPageController.favoriteList.setItems(mother.database.getLoggedInAs().getFavorite());

			mother.tabPaneMain.getSelectionModel().select(mother.getTabDash());
			mother.setLogoutButtonClickable();
		}
		else
			{
				System.out.println("Invalid credentials");
			}

		System.out.println("---Login Button Clicked\n" + "Username: " + username + "\nPassword: " + pass);
	}

	@FXML
	void makeSignup(ActionEvent event) {
		String username = user.getText();
		System.out.println("--CLICKED Create Account Button");

		mother.tabPaneMain.getSelectionModel().select(7);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//James

	}

	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;

	}

}
