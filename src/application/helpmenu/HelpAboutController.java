package application.helpmenu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelpAboutController implements Initializable {
	private double xOffset = 0; // used for draggable borderless window//
	private double yOffset = 0; // used for draggable borderless window//

	@FXML
	private Pane pane;

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
		pane.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				pane.getScene().getWindow().setX(event.getScreenX() - xOffset);
				pane.getScene().getWindow().setY(event.getScreenY() - yOffset);
			}
		});
	}
}
