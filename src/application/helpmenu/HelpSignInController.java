package application.helpmenu;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class HelpSignInController implements Initializable {
	private double xOffset = 0; // used for draggable borderless window//
	private double yOffset = 0; // used for draggable borderless window//
	// All the media files
	String pathVideoLogin = new File("src/application/helpmenu/helpgif/login.mp4").getAbsolutePath();
	Media mediaLogin = new Media(new File(pathVideoLogin).toURI().toString());
	String pathVideoRegister = new File("src/application/helpmenu/helpgif/register-login.mp4").getAbsolutePath();
	Media mediaRegisterVideo = new Media(new File(pathVideoRegister).toURI().toString());
	String pathVideoLogout = new File("src/application/helpmenu/helpgif/logout.mp4").getAbsolutePath();
	Media mediaLogoutVideo = new Media(new File(pathVideoLogout).toURI().toString());

	MediaPlayer mediaPlayerSignIn = new MediaPlayer(mediaLogin);
	MediaPlayer mediaPlayerRegister = new MediaPlayer(mediaRegisterVideo);
	MediaPlayer mediaPlayerLogout = new MediaPlayer(mediaLogoutVideo);

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private MediaView mediaSignIn;

	@FXML
	private MediaView mediaRegister;

	@FXML
	private MediaView mediaLogout;

	@FXML
	private JFXButton closeButton;

	@FXML
	void closeDuckTour(ActionEvent event) {
		mediaPlayerRegister.dispose();
		mediaPlayerLogout.dispose();
		mediaPlayerSignIn.dispose();

		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		System.gc();
	}

	@FXML
	void playVideRegister(MouseEvent event) {
		mediaPlayerRegister.play();
	}

	@FXML
	void playVideoLogout(MouseEvent event) {
		mediaPlayerLogout.play();
	}

	@FXML
	void playVideoSignIn(MouseEvent event) {
		mediaPlayerSignIn.play();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		mediaSignIn.setMediaPlayer(mediaPlayerSignIn);
		mediaRegister.setMediaPlayer(mediaPlayerRegister);
		mediaLogout.setMediaPlayer(mediaPlayerLogout);

		setDraggableWindow();

	}

	public void setDraggableWindow() {
		// Set the Onboarding window so that it is draggable when clicked
		// anywhere on the screen//
		anchorPane.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		anchorPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				anchorPane.getScene().getWindow().setX(event.getScreenX() - xOffset);
				anchorPane.getScene().getWindow().setY(event.getScreenY() - yOffset);
			}
		});
	}
}
