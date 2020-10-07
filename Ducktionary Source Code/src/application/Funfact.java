package application;
import java.util.ArrayList;
import java.util.Calendar;

public class Funfact {
    public ArrayList<String> getFunfact() {
        return funfact;
    }

    public void setFunfact(ArrayList<String> funfact) {
        this.funfact = funfact;
    }

    private ArrayList<String> funfact = new ArrayList<>();

    public Funfact() {
        // FUNFACTS LISTS
        // line #11 = 1 - #41 = 31
        funfact.add("The Bible is the most widely translated book available in 2,454 different languages");
        funfact.add("Did you know, Learning a second language can make you smarter");
        funfact.add("Did you know, There are about 2,200 languages in Asia");
        funfact.add("There are about 7,000 languages in the world");
        funfact.add("There are about 2,200 languages in Asia");
        funfact.add("There are 2,400 languages classified as being ‘endangered’");
        funfact.add("There are 6 official UN languages: Arabic, Chinese, English, French, Russian, Spanish");
        funfact.add("Around 50% of English words come from French");
        funfact.add("You must able to speak russian if you want to be astronauts");
        funfact.add("There are 50,000 characters in the Chinese language");
        funfact.add("The word 'whatever' consistently ranks as the most annoying English word.");
        funfact.add("China has more English speakers than the United States.");
        funfact.add("There are now more Spanish speakers in the US than there are people in Spain");
        funfact.add("Did you know, German is the most spoken mother tongue in Europe");
        funfact.add("The first number spelled out that contains an 'a' is one thousand.");
        funfact.add("The longest common word with no vowels is 'rhythms'.");
        funfact.add("The word 'good' has the most synonyms of any other word in the English language, at 380.");
        funfact.add("The word 'queue' sounds the same even if the last four letters are removed. Before it meant 'line' a queue meant the tail of a beast in medieval pictures and designs.");
        funfact.add("The most complex word in the English language is 'set'. This small word has over 430 definitions and requires a 60,000 word definition that covers 24 pages in the Oxford English Dictionary.");
        funfact.add("English is not the official language of the United States.");
        funfact.add("Only one word in all of English has the letters X, Y, and Z in order: Hydroxyzine. This unique word is a type of medicine that prevents sneezing and anxiety.");
        funfact.add("The letter 'e' is the most commonly used letter in the English language.");
        funfact.add("A new word is created every 98 minutes, which is about 14.7 words a day.");
        funfact.add("Shakespeare added 1,700 words to the English language during his lifetime");
        funfact.add("Those who read fiction have a larger vocabulary than those who do not. Fiction usually contains a wider range of vocabulary than nonfiction does.");
        funfact.add("Most average adult English speakers know between 20,000–35,000 words.");
        funfact.add("The longest word in the English language is 45 letters long: 'Pneumonoultramicroscopic-silicovolcanoconiosis'. It is the scientific name for a type of lung disease.");
        funfact.add("If you wrote out all the numbers (e.g. one, two, three . . . ), you would not use the letter 'b' until the word 'billion'.");
        funfact.add("English is the third most spoken native language in the world. Standard Chinese and Spanish are first and second, respectively.");
        funfact.add("English is the official language for maritime and aeronautical communications.");
        funfact.add("An ambigram is a word that looks the same from various orientations. For example, the word 'swims' will be the same even when turned upside down.");

    }

    public String getFunFact(){
        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DATE);
        //System.out.println("Funfact for today: " +  funfact.get(date-1));

        return "Funfact for today: " +  funfact.get(date-1);
    }

}