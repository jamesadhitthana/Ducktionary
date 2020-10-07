package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXButton;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class OnboardingController implements Initializable {
	private double xOffset = 0; // used for draggable borderless window//
	private double yOffset = 0; // used for draggable borderless window//
	@FXML
	private AnchorPane anchorRoot;
	@FXML
	private JFXButton buttonNext;

	@FXML
	private JFXButton buttonSkipOnboarding;

	@FXML
	private Label labelTitle;

	@FXML
	private Label labelDescription;

	@FXML
	private Circle circleSlide5;

	@FXML
	private Circle circleSlide4;

	@FXML
	private Circle circleSlide3;

	@FXML
	private Circle circleSlide2;

	@FXML
	private Circle circleSlide1;

	@FXML
	void goToSlide1(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/onboarding/OnboardingSlide1.fxml"));
			Scene scene = buttonNext.getScene();

			animateSwipe(root, scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goToSlide2(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/onboarding/OnboardingSlide2.fxml"));
			Scene scene = buttonNext.getScene();

			animateSwipe(root, scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goToSlide3(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/onboarding/OnboardingSlide3.fxml"));
			Scene scene = buttonNext.getScene();

			animateSwipe(root, scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goToSlide4(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/onboarding/OnboardingSlide4.fxml"));
			Scene scene = buttonNext.getScene();

			animateSwipe(root, scene);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void goToSlide5(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/onboarding/OnboardingSlide5.fxml"));
			Scene scene = buttonNext.getScene();

			animateSwipe(root, scene);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void skipOnboarding(MouseEvent event) {
		Stage stage = (Stage) buttonNext.getScene().getWindow();
		stage.close();
		System.gc();
	}

	@FXML
	void getStarted(MouseEvent event) {
		Stage stage = (Stage) buttonNext.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setDraggableWindow();
	}

	private void animateSwipe(Parent root, Scene scene) {
		root.translateXProperty().set(335.20001220703125); // width of
															// onboarding
		StackPane parentContainer = (StackPane) scene.getRoot();
		parentContainer.getChildren().add(root);
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		timeline.getKeyFrames().add(kf);
		timeline.setOnFinished(event1 -> {
			parentContainer.getChildren().remove(anchorRoot);
		});
		timeline.play();
	}

	public void setDraggableWindow() {
		// Set the Onboarding window so that it is draggable when clicked
		// anywhere on the screen//
		anchorRoot.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		anchorRoot.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				anchorRoot.getScene().getWindow().setX(event.getScreenX() - xOffset);
				anchorRoot.getScene().getWindow().setY(event.getScreenY() - yOffset);
			}
		});
	}
}
