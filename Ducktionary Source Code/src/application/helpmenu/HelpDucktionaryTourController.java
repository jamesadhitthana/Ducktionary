package application.helpmenu;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HelpDucktionaryTourController implements Initializable {
	private double xOffset = 0; // used for draggable borderless window//
	private double yOffset = 0; // used for draggable borderless window//
	//All the media files
	String pathVideoSearch = new File("src/application/helpmenu/helpgif/ducktour-search.mp4").getAbsolutePath();
	Media mediaSearchVideo = new Media(new File(pathVideoSearch).toURI().toString());
	String pathVideoHome = new File("src/application/helpmenu/helpgif/ducktour-home.mp4").getAbsolutePath();
	Media mediaHomeVideo = new Media(new File(pathVideoHome).toURI().toString());
	String pathVideoHelp = new File("src/application/helpmenu/helpgif/ducktour-help.mp4").getAbsolutePath();
	Media mediaHelpVideo = new Media(new File(pathVideoHelp).toURI().toString());
	String pathVideoCustomize = new File("src/application/helpmenu/helpgif/ducktour-customize.mp4").getAbsolutePath();
	Media mediaCustomizeVideo = new Media(new File(pathVideoCustomize).toURI().toString());
	String pathVideoProfile = new File("src/application/helpmenu/helpgif/ducktour-profile.mp4").getAbsolutePath();
	Media mediaProfileVideo = new Media(new File(pathVideoProfile).toURI().toString());





	MediaPlayer mediaPlayerHome = new MediaPlayer(mediaHomeVideo);//TODO: change this
	MediaPlayer mediaPlayerHelp = new MediaPlayer(mediaHelpVideo);//TODO: change this
	MediaPlayer mediaPlayerSearch = new MediaPlayer(mediaSearchVideo);//TODO: change this
	MediaPlayer mediaPlayerProfile = new MediaPlayer(mediaProfileVideo);//TODO: change this
	MediaPlayer mediaPlayerCustomize = new MediaPlayer(mediaCustomizeVideo);//TODO: change this

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Accordion accordion;

	@FXML
	private TitledPane titledPaneHome;

	@FXML
	private MediaView mediaHome;
	@FXML
	private MediaView mediaSearch;
	@FXML
	private MediaView mediaCustomize;
	@FXML
	private MediaView mediaProfile;
	@FXML
	private MediaView mediaHelp;
	@FXML
	private TitledPane titledPaneSearch;

	@FXML
	private TitledPane titledPaneCustomize;

	@FXML
	private TitledPane titledPaneProfile;

	@FXML
	private TitledPane titledPaneHelp;

	@FXML
	private JFXButton closeButton;

	@FXML
	void playVideoCustomize(MouseEvent event) {
		mediaPlayerCustomize.play();
	}

	@FXML
	void playVideoHelp(MouseEvent event) {
		mediaPlayerHelp.play();
	}

	@FXML
	void playVideoProfile(MouseEvent event) {
		mediaPlayerProfile.play();
	}

	@FXML
	void playVideoSearch(MouseEvent event) {
		mediaPlayerSearch.play();
	}

	@FXML
	void playVideoHome(MouseEvent event) {
		mediaPlayerHome.play();
	}

	@FXML
	void closeDuckTour(ActionEvent event) {
		mediaPlayerHome.dispose();
		mediaPlayerCustomize.dispose();
		mediaPlayerProfile.dispose();
		mediaPlayerSearch.dispose();
		mediaPlayerHelp.dispose();

		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		System.gc();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// mediaPlayer.setAutoPlay(true);
		mediaHome.setMediaPlayer(mediaPlayerHome);
		mediaHelp.setMediaPlayer(mediaPlayerHelp);
		mediaCustomize.setMediaPlayer(mediaPlayerCustomize);
		mediaSearch.setMediaPlayer(mediaPlayerSearch);
		mediaProfile.setMediaPlayer(mediaPlayerProfile);

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
