package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.collections.ObservableList;
import org.controlsfx.control.textfield.TextFields;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DuckHomeController implements Initializable {
    //Profile
    Database database = new Database();
	// Dictionary
	private Dictionary dictionary = new Dictionary();
	private ObservableList<String> wordList = dictionary.returnWordList(dictionary.getEnglishWordLibrary());
	private ArrayList<ArrayList<String>> definitionContent = new ArrayList<>();
	// public ArrayList<ArrayList<String>> wordList =
	// dictionary.returnWordContent(dictionary.returnWord("A", dictionary));

	private ObservableList<String> history = FXCollections.observableArrayList();
	private ObservableList<String> favorite = FXCollections.observableArrayList();
	private ObservableList<String> atAGlance = FXCollections.observableArrayList();
	public Boolean isUserLoggedIn = false;//bool to check if user is logged in
	@FXML
	private SplitPane splitterMainNav;
	@FXML
	private AnchorPane mainPane; // mainpane
	@FXML
	private AnchorPane navbar;
	@FXML
	private ImageView topLogo;
	@FXML
	private JFXHamburger hamburger;
	@FXML
	private JFXTextField searchBar;
	@FXML
	private JFXButton navHomeButton;
	@FXML
	private JFXButton navCustomizeButton;
	@FXML
	private JFXButton navHelpButton;
	@FXML
	private JFXButton navProfileButton;
	@FXML
	private JFXButton navSearchButton;
	@FXML
	private JFXButton buttonKillApp;
	@FXML
	private ImageView searchButton;

	@FXML
	public TabPane tabPaneMain; // Check the fxml file to see the tab numbering
	@FXML
	private Tab tabDash;
	@FXML
	private Tab tabSearch;
	@FXML
	private Tab tabHelp;
	@FXML
	private Tab tabCustomize;
	@FXML
	private Tab tabTesting;
	@FXML
	private Tab tabLogin;
	@FXML
	private Tab tabRegister;
	@FXML
	private Tab tabDefinition;
	@FXML
	private Tab tabWordNotFound;
	@FXML
	private AnchorPane blueBar;

	/////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	void killApp(ActionEvent event) {
		if(isUserLoggedIn==true) {
	        database.UpdateProfileTextFile();
	        database.Logout();
	        DashboardPageController.getUsername().setText("");
		}

		Stage stage = (Stage) buttonKillApp.getScene().getWindow();
		stage.close();
		System.gc();

	}
	@FXML
	private void handleOnKeyReleased(KeyEvent event) {
		// System.out.println(event.getCode());
		if (event.getCode().equals(KeyCode.F1)) {
			tabPaneMain.getSelectionModel().select(tabHelp);
		} else if (event.getCode().equals(KeyCode.F2)) {
			tabPaneMain.getSelectionModel().select(tabDash);
		} else if (event.getCode().equals(KeyCode.F3)) {
			tabPaneMain.getSelectionModel().select(tabSearch);
		} else if (event.getCode().equals(KeyCode.F4)) {
			tabPaneMain.getSelectionModel().select(tabCustomize);
		} else if (event.getCode().equals(KeyCode.F5)) {
			tabPaneMain.getSelectionModel().select(tabDefinition);
		}
	}

	@FXML
	void navGoSearch(ActionEvent event) {
		System.out.println("navGoSearch");
		// Trying to switch Tabs instead of loading from a resource//
		tabPaneMain.getSelectionModel().select(tabSearch);
	}

	@FXML
	void topLogoClicked(MouseEvent event) {
		System.out.println("Clicked top logo");
		// Trying to switch Tabs instead of loading from a resource//
		tabPaneMain.getSelectionModel().select(tabDash);
	}

	@FXML
	void searchBarEnterKey(ActionEvent event) {
		System.out.println("Enter Key PRESSED");
		search();
	}

	@FXML
	void searchPressed(MouseEvent event) {
		System.out.println("SEARCH BUTTON PRESSED");

		search();
	}

	@FXML
	void navGoHelp(ActionEvent event) {
		System.out.println("pressed navGoHelp");
		// Trying to switch Tabs instead of loading from a resource//
		tabPaneMain.getSelectionModel().select(tabHelp);
	}

	@FXML
	void navGoCustomize(ActionEvent event) {
		System.out.println("pressed navGoCustomize");
		// Trying to switch Tabs instead of loading from a resource//
		tabPaneMain.getSelectionModel().select(tabCustomize);
	}

	@FXML
	void navGoHome(ActionEvent event) {
		System.out.println("pressed navGoHome with new one");
		// Trying to switch Tabs instead of loading from a resource//
		tabPaneMain.getSelectionModel().select(tabDash);
	}

	@FXML
	void navGoProfile(ActionEvent event) {
		System.out.println("pressed navGoProfile with new one");
		// Trying to switch Tabs instead of loading from a resource//
		tabPaneMain.getSelectionModel().select(tabTesting);
	}

	// ------------------Class Declarations---------------------//
	@FXML
	DebuggingPageController DebuggingPageController;
	@FXML
	DefinitionPageController DefinitionPageController;
	@FXML
	DashboardPageController DashboardPageController;
	@FXML
	CustomizePageController CustomizePageController;
	@FXML
	HelpPageController HelpPageController;
	@FXML
	SearchPageController SearchPageController;
	@FXML
	LoginPageController LoginPageController;
	@FXML
	RegisterPageController RegisterPageController;
	@FXML
	AddWordPageController AddWordPageController;
	//HERE JAMES
	@FXML
	RemoveWordPageController RemoveWordPageController;
	@FXML
	ViewSearchedWordsPageController ViewSearchedWordsPageController;
	@FXML
	WordNotFoundPageController WordNotFoundPageController;
	// continued in initialize method

	// ------------------DIVIDER---------------------//

	// --------Function for sending messages through different
	// controllers------------//
	public void setLogoutButtonNotClickable() {
		isUserLoggedIn=false;
		DashboardPageController.setLogoutButtonEnabled(false);
	}
	public void setLogoutButtonClickable() {
		isUserLoggedIn=true;
		DashboardPageController.setLogoutButtonEnabled(true);
	}

	public void putCursorOnSearchBar() {
		searchBar.requestFocus();
	}
	public void triggerAddWordButton() {
		CustomizePageController.add.fire();
	}

	public void setDefinitionPageWord(String word) {
		DefinitionPageController.word.setText(word);
	}

	public void setDefinitionPagePos1(String word) {
		DefinitionPageController.pos1.setText(word);
	}

	public void setDefinitionPagePos2(String word) {
		DefinitionPageController.pos2.setText(word);
	}

	public void setDefinitionPageVisiblePos2(boolean check) {
		DefinitionPageController.pos2.setVisible(check);
	}

	public void setDefinitionPageDefinition1(String word) {
		DefinitionPageController.definition1.setText(word);
	}

	public void setDefinitionPageOrigin1(String word) {
		// DefinitionPageController.origin1.setText("["+word+"]");//NOTICE HOW I
		// added the side bars//
		DefinitionPageController.origin1.setText(word);
	}

	public void setDefinitionPageDefinition2(String word) {
		DefinitionPageController.definition2.setText(word);
	}

	public void setDefinitionPageVisibleDefinition2(boolean check) {
		DefinitionPageController.defnum2.setVisible(check);
		DefinitionPageController.definition2.setVisible(check);
		DefinitionPageController.origin2.setVisible(check);
	}

	public void setDefinitionPageOrigin2(String word) {
		// DefinitionPageController.origin2.setText("["+word+"]");
		DefinitionPageController.origin2.setText(word);
	}

	public void setDefinitionPageDefinition3(String word) {
		DefinitionPageController.definition3.setText(word);
	}

	public void setDefinitionPageVisibleDefinition3(boolean check) {
		DefinitionPageController.defnum3.setVisible(check);
		DefinitionPageController.definition3.setVisible(check);
		DefinitionPageController.origin3.setVisible(check);
	}

	public void setDefinitionPageOrigin3(String word) {
		// DefinitionPageController.origin3.setText("["+word+"]");
		DefinitionPageController.origin3.setText(word);
	}

	public void setDashboardPageTitle(String word) {
		DashboardPageController.titleLabel.setText(word);
	}

	public void setCustomizePageTitle(String word) {
		CustomizePageController.labelBig.setText(word);
	}

	public void setHelpPageTitle(String word) {
		HelpPageController.labelBig.setText(word);
	}

	public void setSearchPageTitle(String word) {
		SearchPageController.labelBig.setText(word);
	}

	public void setDefinitions(String form) {

		int formIndex = 0;
		for (int i = 0; i < definitionContent.size(); ++i) {
			if (definitionContent.get(i).get(0).equals(Dictionary.shortenForm(form))) {
				formIndex = i;
			}
		}

		setDefinitionPageDefinition1(definitionContent.get(formIndex).get(1));
		if (definitionContent.get(formIndex).size() <= 2) {
			setDefinitionPageVisibleDefinition2(false);
			setDefinitionPageVisibleDefinition3(false);
		} else {
			setDefinitionPageVisibleDefinition2(true);
			setDefinitionPageDefinition2(definitionContent.get(formIndex).get(2));
			if (definitionContent.get(formIndex).size() <= 3) {

				setDefinitionPageVisibleDefinition3(false);
			} else {
				setDefinitionPageDefinition3(definitionContent.get(formIndex).get(3));
				setDefinitionPageVisibleDefinition3(true);
			}
		}
	}

	public void goToDefinitionPage(String wordKey) {
		String wordLine = dictionary.returnWordLine(wordKey);
		System.out.println("returned word line "+dictionary.returnWordLine(wordKey));
		if(!wordLine.equals("nan")) {
			ArrayList<ArrayList<String>> wordContent = dictionary.splitLine(wordLine);
			definitionContent.removeAll(definitionContent);
			definitionContent.add(dictionary.splitDefinition(wordContent.get(1).get(0)));
			setDefinitionPageWord(wordContent.get(0).get(0));
			setDefinitionPageOrigin1(wordContent.get(2).get(0));
			setDefinitionPageOrigin2(wordContent.get(2).get(0));
			setDefinitionPageOrigin3(wordContent.get(2).get(0));

			setDefinitionPagePos1(Dictionary.extendForm(definitionContent.get(0).get(0)));
			if (Dictionary.extendForm(definitionContent.get(0).get(0)).charAt(0) == '—') {
				setDefinitionPageVisiblePos2(true);
				definitionContent.add(dictionary.splitDefinition(wordContent.get(1).get(1)));
				setDefinitionPagePos2(Dictionary.extendForm(definitionContent.get(1).get(0)));
			} else {
				setDefinitionPageVisiblePos2(false);
			}
			setDefinitions(Dictionary.extendForm(definitionContent.get(0).get(0)));
			tabPaneMain.getSelectionModel().select(5);
		}else {
			tabPaneMain.getSelectionModel().select(tabWordNotFound);
		}
	}

	public void setViewSearchedWordsPageSelectedAlphabets(String alphabets) {
		ViewSearchedWordsPageController.labelSelectedAlphabets.setText(alphabets);
		ViewSearchedWordsPageController.updateList(alphabets);

	}

	// Search functions
	public void setSearchBarOption() {
		TextFields.bindAutoCompletion(searchBar, wordList).setVisibleRowCount(5);
	}

    public void search() {
        if (searchBar.getText().equals("")) {
            System.out.println("search bar is empty!!");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Search bar is empty");
			alert.setHeaderText(null);
			alert.setContentText("You forgot to fill the search bar with your word!");
			alert.showAndWait();
        } else {
            // tabPaneMain.getSelectionModel().select(tabDefinition);

            String searchedWord = ""+ Character.toUpperCase(searchBar.getText().charAt(0)) + searchBar.getText().substring(1);

            if (!searchedWord.equals("nan")) {

                goToDefinitionPage(searchedWord);

                if(database.getLoggedInAs() != null)
                {
                    database.getLoggedInAs().getHistory().add(searchedWord);

                    String toSplit[] = database.getLoggedInAs().getStats().get(0).split(":");

                    int newInt = Integer.parseInt(toSplit[1]) + 1;

                    database.getLoggedInAs().getStats().set(0, toSplit[0] + ":" + Integer.toString(newInt));
                    DashboardPageController.historyList.setItems(database.getLoggedInAs().getHistory());
                    DashboardPageController.atAGlanceList.setItems(database.getLoggedInAs().getStats());
                }
                else
                {
                    history.add(searchedWord);
                }

                //I moved the tab not found in the goToDefinitionPage() method
            }
            System.out.println("Searching word: " + searchBar.getText());
        }

    }//END OF: Search functions

	// Dashboard stuff
	public void setDashboardSearchHistory() {
		if (history.size() > 5) {
			history.remove(0);
		}

		DashboardPageController.historyList.setItems(history);
	}

	public void setDashboardAtAGlance() {
		if (atAGlance.size() > 5) {
			atAGlance.remove(0);
		}

		DashboardPageController.atAGlanceList.setItems(atAGlance);
	}

	public void setDashboardFavorite() {
		if (favorite.size() > 5) {
			favorite.remove(0);
		}

		DashboardPageController.favoriteList.setItems(favorite);
	}

	//splash method
	private void callSplash(){
        try {
            Stage theStage = new Stage();
            theStage.setTitle("Splash Screen");
            theStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("splash_screen.fxml"))));

            theStage.initStyle(StageStyle.UNDECORATED);
            theStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error at splash");
        }
	}

	// Dashboard stuff

	// ------------------DIVIDER---------------------//
	//TODO:james
	private double xOffset = 0; // used for draggable borderless window//
	private double yOffset = 0; // used for draggable borderless window//
	public void setDraggableWindow() {
		// top blue bar draggable
		// anywhere on the screen//
		blueBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		blueBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				blueBar.getScene().getWindow().setX(event.getScreenX() - xOffset);
				blueBar.getScene().getWindow().setY(event.getScreenY() - yOffset);
			}
		});
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// --Class Declarations Part 2--//
		System.out.println("Initializing app..");

        //Show splash
        callSplash();

        //Close splash
		// for (int i =0; i < wordList.size(); ++i)
		{
			// System.out.println(wordList.get(i));
		}
		DebuggingPageController.init(this);
		DefinitionPageController.init(this);
		DashboardPageController.init(this);
		SearchPageController.init(this);
		CustomizePageController.init(this);
		HelpPageController.init(this);
		LoginPageController.init(this);
		RegisterPageController.init(this);
		AddWordPageController.init(this);
		ViewSearchedWordsPageController.init(this);
        RemoveWordPageController.init(this);
        WordNotFoundPageController.init(this);
		// Search bar
		setSearchBarOption();
		// Search bar

		// Dashboard stuff
		setDashboardAtAGlance();
		setDashboardFavorite();
		// Dashboard stuff

		System.out.println("-Successfuly loaded child classes- [James]");
		// END OF: Class Declarations Part 2//

		// --Hamburger On Click Back Animation--//
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();

			// Setting up navigation bar so that it can expand and hide//
			if (navbar.getWidth() == 45) {
				navbar.setPrefWidth(navbar.getWidth() + 100);
				splitterMainNav.setDividerPositions(0.8);
			} else {
				navbar.setPrefWidth(45);
				splitterMainNav.setDividerPositions(0.0803);
			}
			// END OF: Setting up navigation bar--//
			System.out.println("Hamburger clicked");

		});
		// END OF: Hamburger On Click Back Animation//

		// Onboarding Popup//
		try {//
			Parent root = FXMLLoader.load(getClass().getResource("/application/onboarding/OnboardingSlide1.fxml")); // main

			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			System.out.println("popup");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("fail popup");
		}
		// END OF: Onboarding Popup//
		setDraggableWindow();
	}

	public Dictionary returnDictionary() {
		return dictionary;
	}

	ObservableList<String> getWordList() {
		return wordList;
	}
	void updateWordList() {
		wordList = dictionary.returnWordList(dictionary.getEnglishWordLibrary());
		setSearchBarOption();
	}

    //Getters and setters

    public Tab getTabDash() {
        return tabDash;
    }

    public Tab getTabSearch() {
        return tabSearch;
    }

    public Tab getTabHelp() {
        return tabHelp;
    }

    public Tab getTabCustomize() {
        return tabCustomize;
    }

    public Tab getTabTesting() {
        return tabTesting;
    }

    public Tab getTabLogin() {
        return tabLogin;
    }

    public Tab getTabRegister() {
        return tabRegister;
    }

    public Tab getTabDefinition() {
        return tabDefinition;
    }

    public Tab getTabWordNotFound() {
        return tabWordNotFound;
    }
}
