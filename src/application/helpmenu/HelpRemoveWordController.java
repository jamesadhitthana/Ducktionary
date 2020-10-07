package application.helpmenu;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class HelpRemoveWordController implements Initializable {
	private double xOffset = 0; // used for draggable borderless window//
	private double yOffset = 0; // used for draggable borderless window//

	String pathVideoAddWord = new File("src/application/helpmenu/helpgif/remove words.mp4").getAbsolutePath();
	Media mediaAddWord = new Media(new File(pathVideoAddWord).toURI().toString());
	MediaPlayer mediaPlayerAddWord = new MediaPlayer(mediaAddWord);// TODO:
																	// change
																	// this
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private JFXButton closeButton;

	@FXML
	private MediaView media1;

	@FXML
	void closeDuckTour(ActionEvent event) {
		mediaPlayerAddWord.dispose();

		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		System.gc();
	}

	@FXML
	void playVideo1(MouseEvent event) {
		mediaPlayerAddWord.play();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		media1.setMediaPlayer(mediaPlayerAddWord);
		mediaPlayerAddWord.setAutoPlay(true);
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