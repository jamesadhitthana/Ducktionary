package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPageController implements Initializable {
	@FXML
	private DuckHomeController mother;
	@FXML
	public JFXTextField user;
	@FXML
	public JFXTextField email;
	@FXML
	public JFXPasswordField password;
	@FXML
	public JFXPasswordField passwordConfirm;
	@FXML
    JFXButton signup;
	@FXML
    JFXButton login;
	@FXML
	AnchorPane registerPane;

	@FXML
	void makeLogin(ActionEvent event) {
		System.out.println("--CLICKED Create Login Button");
		mother.tabPaneMain.getSelectionModel().select(6);
	}

	@FXML
	void makeSignup(ActionEvent event) {
		String username = user.getText();
		String userEmail = email.getText();
		String pass = password.getText();
		String passConfirm = passwordConfirm.getText();

		if(userEmail != null && userEmail != null && pass != null && passConfirm != null && pass.equals(passConfirm))
		{
			mother.database.Register(userEmail,pass,username);
		}

		mother.tabPaneMain.getSelectionModel().select(mother.getTabLogin());

		System.out.println("---SignUp Clicked\n" + "Username: " + username + "\nemail: " + userEmail + "\nPassword: "
				+ pass + "\nPasswordConfirm: " + passConfirm);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// James

	}

	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;

	}

}
