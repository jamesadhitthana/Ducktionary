package application;

import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// Parent root =
			// FXMLLoader.load(getClass().getResource("/application/onboarding/OnboardingSlide1.fxml"));
			// // main
			System.out.println("Ducktionary V5");
			Parent root = FXMLLoader.load(getClass().getResource("/application/DuckHome.fxml")); // main
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setResizable(false);
			primaryStage.show();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}