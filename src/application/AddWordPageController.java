package application;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AddWordPageController implements Initializable {
	@FXML
    private DuckHomeController mother;
	@FXML
	public AnchorPane AddWordPane;

	private DatabaseFunction data = new DatabaseFunction();

	@FXML
	public JFXTextArea defInput;
	@FXML
	public JFXTextField wordInput;
	@FXML
	public JFXTextField posInput;
	@FXML
	public JFXTextField originInput;

	@FXML
	private JFXButton publishButton;
	@FXML
	private JFXButton cancelButton;
	@FXML
	private JFXButton previewButton;

	@FXML
	public Label labelTitle;
	@FXML
	public Label labelWord;
	@FXML
	public Label labelPos;
	@FXML
	public Label labelOrigin;
	@FXML
	public Label labelDefinition;

	@FXML
	void previewGo(ActionEvent event) {
		System.out.println("previewGo");

		mother.setDefinitionPageWord(wordInput.getText());
		mother.setDefinitionPageDefinition1(defInput.getText());
		mother.setDefinitionPagePos1(posInput.getText());
		mother.setDefinitionPageOrigin1(originInput.getText());

		if (wordInput.getText().equals("") || defInput.getText().equals("") || posInput.getText().equals("")
				|| originInput.getText().equals("")) {
			// Show alert dialog//
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("You Forgot Something!");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all of the fields to continue!");
			alert.showAndWait();
			// END OF: Show alert Dialog//
		} else {
			mother.tabPaneMain.getSelectionModel().select(5); // go to
			// definition
			// tab
		}

	}

	@FXML
	void cancelButtonGo(ActionEvent event) {
		System.out.println("cancelButtonGo");
		mother.tabPaneMain.getSelectionModel().select(3); // go to customize tab
	}

	@FXML
	void publishGo(ActionEvent event) {

        //not mine
		mother.returnDictionary();
		System.out.println("Publish button clicked");

        //mine
        System.out.println(mother.returnDictionary().getEnglishWordLibrary().getList().get(0));

        if (wordInput.getText().equals("") || defInput.getText().equals("") || posInput.getText().equals("")
                || originInput.getText().equals("")) {
            // Show alert dialog//
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("You Forgot Something!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all of the fields to continue!");
            alert.showAndWait();
            // END OF: Show alert Dialog//
        } else {
            try{
                //The new line that will be added to the txt
                ///Word%places&definition%[origin]
                String newline="";
                String wordName = ""+ Character.toUpperCase(wordInput.getText().charAt(0)) + wordInput.getText().substring(1);
                newline+=wordName+"%"+posInput.getText()+"&"+defInput.getText()+"%["+originInput.getText() +"]";

                String kata = wordInput.getText(), pos = posInput.getText(), origin = originInput.getText(), def = defInput.getText() ;

                if(DatabaseFunction.testConnect()){
                data.addWordToDatabase(kata, pos, origin, def);
                }

                //System.out.println(newline.compareToIgnoreCase(the word here));
                //System.out.println(newline);

                //index to append
                int i=0;
                String word=wordInput.getText();
                for(String line : mother.returnDictionary().getEnglishWordLibrary().getList())
                {
                    //if duplicate
                    if(word.equalsIgnoreCase(mother.getWordList().get(i))==true){
                        //System.out.println(line);
                        //System.out.println(word);
                        //System.out.println(i);
                        System.out.println("Word already existed");
            			Alert alert = new Alert(AlertType.WARNING);
            			alert.setTitle("Word Already Exists");
            			alert.setHeaderText(null);
            			alert.setContentText("Oops! The word you are trying to add already exists!");
            			alert.showAndWait();
                        return;
                    }

                    //Find the index here
                    if(word.compareToIgnoreCase(line)<0){
                        //System.out.println(line);
                        //System.out.println(word);
                        //System.out.println(i);
                        break;
                    }
                    i++;
                }

                //Append the line to wordlist
                mother.returnDictionary().getEnglishWordLibrary().getList().add(i,newline);

                //for(String tom: mother.returnDictionary().getEnglishWordLibrary().getList()){
                    //System.out.println(tom);
                //}

                //Replace txt with the new wordlist
                PrintWriter writer = new PrintWriter(mother.returnDictionary().matchingFiles[0].getPath().toString(), "UTF-8");
                writer.println(""); //ALWAYS MAKE SURE THERE'S AN EMPTY LINE AT THE TOP BECAUSE NOTEPAD HAS A GLITCH WHERE IT ADDS A RANDOM ASS CHARACTER AT THE START OF THE FIRST LINE
                for(String line : mother.returnDictionary().getEnglishWordLibrary().getList())
                {
                    writer.println(line);
                }
                writer.close();

                System.out.println("successfully added word");
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Successfully added word");
    			alert.setHeaderText(null);
    			alert.setContentText("Word added successfuly!");
    			alert.showAndWait();
            }catch (Exception e){
                e.printStackTrace();
            }


            //Refresh
            System.out.println("successfully refreshed wordlist");
            mother.returnDictionary().getEnglishWordLibrary().readFile(mother.returnDictionary().matchingFiles[0].getPath().toString());
            mother.CustomizePageController.setCustomizePageList();
            mother.updateWordList();
            if(mother.database.getLoggedInAs() != null)
            {
            	String toSplit[] = mother.database.getLoggedInAs().getStats().get(1).split(":");

            	int newInt = Integer.parseInt(toSplit[1]) + 1;

            	mother.database.getLoggedInAs().getStats().set(1, toSplit[0] + ":" + Integer.toString(newInt));
            	mother.DashboardPageController.atAGlanceList.setItems(mother.database.getLoggedInAs().getStats());
            }
        }

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// James
	}

	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;

	}

}
