package application.helpmenu;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelpTipsAndTricksController implements Initializable {
	private double xOffset = 0; // used for draggable borderless window//
	private double yOffset = 0; // used for draggable borderless window//

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private JFXButton closeButton;

	@FXML
	void closeDuckTour(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		System.gc();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
