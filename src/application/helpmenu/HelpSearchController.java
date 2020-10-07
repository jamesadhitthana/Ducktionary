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

public class HelpSearchController implements Initializable {
	private double xOffset = 0; // used for draggable borderless window//
	private double yOffset = 0; // used for draggable borderless window//

	// All the media files
	String pathVideoSearchWords = new File("src/application/helpmenu/helpgif/search.mp4").getAbsolutePath();
	Media mediaSearchWords = new Media(new File(pathVideoSearchWords).toURI().toString());

	String pathVideoViewWord = new File("src/application/helpmenu/helpgif/listed words.mp4").getAbsolutePath();
	Media mediaViewWordVideo = new Media(new File(pathVideoViewWord).toURI().toString());

	MediaPlayer mediaPlayerHowSearch = new MediaPlayer(mediaSearchWords);
	MediaPlayer mediaPlayerViewWord = new MediaPlayer(mediaViewWordVideo);

	@FXML
	private JFXButton closeButton;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private MediaView mediaHowSearch;
	@FXML
	private MediaView mediaViewWord;

	@FXML
	void playVideoHowSearch(MouseEvent event) {
		mediaPlayerHowSearch.play();
	}

	@FXML
	void playVideoViewWord(MouseEvent event) {
		mediaPlayerViewWord.play();
	}

	@FXML
	void closeDuckTour(ActionEvent event) {
		mediaPlayerHowSearch.dispose();
		mediaPlayerViewWord.dispose();

		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		System.gc();

	} // this

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mediaHowSearch.setMediaPlayer(mediaPlayerHowSearch);
		mediaViewWord.setMediaPlayer(mediaPlayerViewWord);
		// TODO Auto-generated method stub
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
