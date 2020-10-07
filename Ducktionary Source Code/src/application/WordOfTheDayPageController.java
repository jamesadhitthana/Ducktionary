package application;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class WordOfTheDayPageController implements Initializable {

	@FXML
    private DuckHomeController mother;

	String wordList[] = {

			"Leader%n&1 a person or thing that leads. B person followed by others. 2 principal player in a music group or of the first violins in an orchestra. 3 = *leading article. 4 shoot of a plant at the apex of a stem or of the main branch.",
			"Learning%n&Knowledge acquired by study.",
			"Machete%n&Broad heavy knife, esp. Of central america.%[spanish from latin]",
			"Madam%n&1 polite or respectful form of address or mode of reference to a woman. 2 colloq. Conceited or precocious girl or young woman. 3 woman brothel-keeper.%[related to *madame]",
			"Madhouse%n&1 colloq. Scene of confused uproar. 2 archaic mental home or hospital.",
			"Magnify%v&(-ies, -ied) 1 make (a thing) appear larger than it is, as with a lens. 2 exaggerate. 3 intensify. 4 archaic extol.%[latin: related to *magnificent]",
			"Maiden%n&1 a archaic or poet. Girl; young unmarried woman. B (attrib.) Unmarried (maiden aunt). 2  = *maiden over. 3 (attrib.) (of a female animal) unmated. 4 (often attrib.) A horse that has never won a race. B race open only to such horses. 5 (attrib.) First (maiden speech; maiden voyage).%[old english]",
			"Major%—adj&1 relatively great in size, intensity, scope, or importance. 2 (of surgery) serious. 3 mus. A (of a scale) having intervals of a semitone above its third and seventh notes. B (of an interval) greater by a semitone than a minor interval (major third). C (of a key) based on a major scale. 4 of full legal age.%—n&1  a army officer next below lieutenant-colonel. B officer in charge of a band section (drum major). 2 person of full legal age. 3 us a student's main subject or course. B student of this.%—v&(foll. By in) us study or qualify in (a subject) as one's main subject.%[latin, comparative of magnus great]",
			"Malicious%adj&Given to or arising from malice.",
			"Man%—n&(pl. Men) 1 adult human male. 2 a human being; person. B the human race. 3 a workman (the manager spoke to the men). B manservant, valet. 4 (usu. In pl.) Soldiers, sailors, etc., esp. Non-officers. 5 suitable or appropriate person; expert (he is your man; the man for the job). 6 a husband (man and wife). B colloq. Boyfriend, lover. 7 human being of a specified type or historical period (renaissance man; peking man). 8 piece in chess, draughts, etc. 9 colloq. As a form of address. 10 person pursued; opponent (police caught their man).%—v&(-nn-) 1 supply with a person or people for work or defence. 2 work, service, or defend (man the pumps). 3 fill (a post). as one man in unison. Be one's own man be independent. To a man without exception.%[old english]",
			"Manner%n&1 way a thing is done or happens. 2 (in pl.) A social behaviour (good manners). B polite behaviour (has no manners). C modes of life; social conditions. 3 outward bearing, way of speaking, etc. 4 style (in the manner of rembrandt). 5 kind, sort (not by any manner of means). in a manner of speaking in a way; so to speak. To the manner born colloq. Naturally at ease in a particular situation etc.%[latin manus hand]",
			"March%n&Third month of the year.%[latin martius of mars]",
			"Native%—n&1 a (usu. Foll. By of) person born in a specified place. B local inhabitant. 2 often offens. Member of a non-white indigenous people, as regarded by colonial settlers. 3 (usu. Foll. By of) indigenous animal or plant.%—adj&1 inherent; innate. 2 of one's birth (native country). 3 (usu. Foll. By to) belonging to a specified place. 4 (esp. Of a non-european) indigenous; born in a place. 5 (of metal etc.) Found in a pure or uncombined state.%[latin: related to *natal]",
			"Natural%—adj&1 a existing in or caused by nature (natural landscape). B uncultivated (in its natural state). 2 in the course of nature (died of natural causes). 3 not surprising; to be expected (natural for her to be upset). 4 unaffected, spontaneous. 5 innate (natural talent for music). 6 not disguised or altered (as by make-up etc.). 7 likely or suited by its or their nature to be such (natural enemies; natural leader). 8 physically existing (the natural world). 9 illegitimate. 10 mus. (of a note) not sharpened or flattened (b natural).%—n&1 colloq. (usu. Foll. By for) person or thing naturally suitable, adept, etc. 2 mus. A sign denoting a return to natural pitch. B natural note.%[latin: related to *nature]",
			"Navy%n&(pl. -ies) 1 (often the navy) a whole body of a state's ships of war, including crews, maintenance systems, etc. B officers and men of a navy. 2 (in full navy blue) dark-blue colour as of naval uniforms. 3 poet. Fleet of ships.%[romanic navia ship: related to *naval]",
			"Neighbour (us neighbor)%—n&1 person living next door to or near or nearest another. 2 fellow human being. 3 person or thing near or next to another.%—v&Border on; adjoin.%[old english: related to *nigh, *boor]",
			"Newcomer%n&1 person who has recently arrived. 2 beginner in some activity.",
			"Newt%n&Small amphibian with a well-developed tail.%[ewt, with n from an: var. Of evet *eft]",
			"Nightfall%n&End of daylight.",
			"Nocturne%n&1 mus. Short romantic composition, usu. For piano. 2 picture of a night scene.%[french]",
			"Nook%n&Corner or recess; secluded place.%[origin unknown]",
			"Notable%—adj&Worthy of note; remarkable, eminent.%—n&Eminent person.%[latin noto *note]" };

	
	@FXML
	private Label date;

	@FXML
	private Label wordName;

	@FXML
	private Label wordType;

	@FXML
	private Label wordDefinition;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		date.setText(dateFormat.format(cal.getTime()));

		int toReturn = new Random().nextInt(wordList.length);

		ArrayList<ArrayList<String>> toShow = splitLine(wordList[toReturn]);

		wordName.setText(toShow.get(0).get(0));

		ArrayList<String> toShowShow = splitDefinition(toShow.get(1).get(0));

		wordType.setText(toShowShow.get(0));

		wordDefinition.setText(toShowShow.get(1));

	}

	public void init(DuckHomeController duckHomeController) {
		mother = duckHomeController;

	}


	public ArrayList<ArrayList<String>> splitLine(String s)
	{
		//To return
		ArrayList<ArrayList<String>> toReturn = new ArrayList<>();
		toReturn.add(new ArrayList<>());
		toReturn.add(new ArrayList<>());
		toReturn.add(new ArrayList<>());

		//This
		String stringToSplit = s;
		String splitResult[];
		String origin = "[english]"; //If there's none, use english instead of nan

		//First split to get the word
		splitResult = stringToSplit.split("%", 2);
		stringToSplit = splitResult[1];

		//Place the word in the first array list
		toReturn.get(0).add(splitResult[0]);

		splitResult = stringToSplit.split("%");

		for(String stringInSplitResult: splitResult)
		{
			if(stringInSplitResult.charAt(0) == '[')
			{
				origin = stringInSplitResult;
			}
			else
			{
				toReturn.get(1).add(stringInSplitResult);
			}
		}

		toReturn.get(2).add(origin);

		return toReturn;
	}

	public ArrayList<String> splitDefinition(String s)
	{
		ArrayList<String> toReturn = new ArrayList<>();

		String stringToSplit = s;
		String splitResult[];
		String definitions[] = new String[0];
		//System.out.println(definitions.length);

		splitResult = stringToSplit.split("&", 2);
		definitions = splitResult[1].split("[2-9] ");

		toReturn.add(splitResult[0]);
		//toReturn.add(splitResult[1]);

		for (int i =0; i < definitions.length; ++i)
		{

			//System.out.println(definitions[i]);
			if (definitions[i].length() > 0)
			toReturn.add(definitions[i]);
		}

		return toReturn;
	}
}
