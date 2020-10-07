package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class RemoveWordPageController {
    @FXML
    private DuckHomeController mother;

    private DatabaseFunction data = new DatabaseFunction();

    @FXML
    private AnchorPane RemoveWordPane;

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelWord;

    @FXML
    private JFXTextField wordInput;

    @FXML
    private JFXButton confirmButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    void cancelAction(ActionEvent event) {
        //System.out.println("Pressed cancel button");
        mother.tabPaneMain.getSelectionModel().select(3); // go to customize tab
    }

    @FXML
    void confirmAction(ActionEvent event) {
        //mother.returnDictionary().getEnglishWordLibrary().getList();
        int i=0;
        String word;
        boolean found=false;
        for (String line: mother.returnDictionary().getEnglishWordLibrary().getList()){
            //word=mother.returnDictionary().getEnglishWordLibrary().getList().get(i);
            String[] parts = line.split("%");
            word=parts[0];

            if(wordInput.getText().toLowerCase().equals(word.toLowerCase())){
                found=true;
                break;
            }
            i++;
        }

        if(found==true){
        	String kata = wordInput.getText();
        	if(DatabaseFunction.testConnect()){
                data.deleteDataFromDatabase(kata);
                }

            mother.returnDictionary().getEnglishWordLibrary().getList().remove(i);
            System.out.println("successfully removed word");

            //Replace txt with a new one
            try {
                //Replace txt with the new wordlist
                PrintWriter writer = new PrintWriter(mother.returnDictionary().matchingFiles[0].getPath().toString(), "UTF-8");
                writer.println("");
                for(String line : mother.returnDictionary().getEnglishWordLibrary().getList())
                {
                    writer.println(line);
                }
                writer.close();

                System.out.println("successfully removed word");
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Successfully removed word");
    			alert.setHeaderText(null);
    			alert.setContentText("Word removed successfuly!");
    			alert.showAndWait();
            }catch (Exception e){
                System.out.println("error at replacing word at remove word");
                e.printStackTrace();
            }
            //Refresh
            System.out.println("successfully refreshed wordlist");
            mother.returnDictionary().getEnglishWordLibrary().readFile();
            mother.CustomizePageController.setCustomizePageList();
            mother.updateWordList();

            if(mother.database.getLoggedInAs() != null)
            {
            	String toSplit[] = mother.database.getLoggedInAs().getStats().get(2).split(":");

            	int newInt = Integer.parseInt(toSplit[1]) + 1;

            	mother.database.getLoggedInAs().getStats().set(2, toSplit[0] + ":" + Integer.toString(newInt));
            	mother.DashboardPageController.atAGlanceList.setItems(mother.database.getLoggedInAs().getStats());
            }
        }else{
            System.out.println("there is no such word in the english library");

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Failed to remove word");
			alert.setHeaderText(null);
			alert.setContentText("Oops! We can't find your word in the dictionary database!");
			alert.showAndWait();
        }

    }

    //@Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void init(DuckHomeController duckHomeController) {
        mother = duckHomeController;
    }






}
