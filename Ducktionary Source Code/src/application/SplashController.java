package application;

import com.jfoenix.controls.JFXSpinner;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable{
    private Timeline timeline;
    private FadeTransition fadeIn;
    private FadeTransition fadeOut;

    private int progressCur=0;

    @FXML
    private Label funfactlabel;

    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private JFXSpinner progressPercentageSpinner;

    @FXML
    private Label progressPercentageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Label word of the day
        funfactlabel.setText("'' "+new Funfact().getFunFact()+" ''");

        //Load splash screen with fade in effect
        fadeIn = new FadeTransition(Duration.seconds(2), rootAnchor);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        //Finish splash with fade out effect
        fadeOut = new FadeTransition(Duration.seconds(2), rootAnchor);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        //timeline for data loading check
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(
            Duration.millis(50),
            e -> {
                update();
            }
        ));
        timeline.play();


        //Splash finished
        fadeOut.setOnFinished((e) -> {
            Stage theStage = (Stage) rootAnchor.getScene().getWindow();
            theStage.close();
        });

    }

    private void update(){
        this.progressCur+=1;
        this.progressPercentageLabel.setText(Integer.toString(this.progressCur) + " %");
        //System.out.println(this.progressCur);

        if (progressCur==100){
            this.progressPercentageLabel.setText("Done");
            fadeOut.play();
            // later
            timeline.stop();
        }
    }

}