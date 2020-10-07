package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class Dictionary
{
	File folder = new File(System.getProperty("user.dir"));

	File[] matchingFiles = folder.listFiles(new FilenameFilter() {
	    public boolean accept(File dir, String name)
	    {
	        return name.startsWith("NewDictionary") && name.endsWith("txt"); //Change if file name is different
	    }
	});

	private String englishFileLocation = matchingFiles[0].getPath();

	//Use this if the way above doesn't work
	//private String englishFileLocation = "Dictionary.txt";

	private EnglishWordLibrary english;

	//extend the shortened forms in the dictionary
	private static String[] shortForms = {"n", "adj", "v", "gram", "abbr","suffix", "prefix", "predic", "austral", "naut", "pron"};
	private static String[] longForms = {"NOUN", "ADJECTIVE", "VERB", "GRAMMAR", "ABBREVIATION", "SUFFIX", "PREFIX", "PREDICATE", "AUSTRAL", "NAUT", "PRONOUNCIATION"};


	//Constructor
	public Dictionary()
	{
		try
		{
			this.english = new EnglishWordLibrary(englishFileLocation);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//Methods

	public static String extendForm(String shortForm)
	{
		String includeDash = "";
		if (shortForm.charAt(0) == '—')
		{
			includeDash += "—";
			shortForm = shortForm.substring(1);
		}
		for (int i = 0; i < shortForms.length; ++i)
		{
			if (shortForms[i].matches(shortForm))
			{
				return includeDash + longForms[i];
			}
		}
		return includeDash + shortForm;
	}

	public static String shortenForm(String longForm)
	{
		String includeDash = "";
		if (longForm.charAt(0) == '—')
		{
			includeDash += "—";
			longForm = longForm.substring(1);
		}
		for (int i = 0; i < longForms.length; ++i)
		{
			if (longForms[i].matches(longForm))
			{
				return includeDash + shortForms[i];
			}
		}
		return includeDash + longForm;
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


	//Making a list that only contains the words used for the search bar
	public ObservableList<String> returnWordList(EnglishWordLibrary wordLibrary)
	{
		ArrayList<String> list;
		ArrayList<String> wordList = new ArrayList<>();

		list = wordLibrary.getList();

		for(int i = 0; i  < list.size(); i++)
		{
			StringBuilder sb = new StringBuilder(list.get(i));
			String s[] = sb.toString().split("%", 2);

			wordList.add(s[0]);
		}

		ObservableList<String> options = FXCollections.observableArrayList(wordList);

		return options;
	}

	public ObservableList<String> returnWordList()
	{
		return returnWordList(english);
	}

	/*
	public ArrayList<ArrayList<String>> returnDisplayDictionary()
	{
		ArrayList<String> list;
		ArrayList<ArrayList<String>> wordList = new ArrayList<>();
		ArrayList<ArrayList<String>> splitWordContent;
		ArrayList<ArrayList<String>> definitionContent = new ArrayList<>();
		list = english.getList();

		for(int i = 0; i  < list.size(); i++)
		{

			wordList.add(new ArrayList<>());
			String pos = "";
			splitWordContent = splitLine(list.get(i));
			wordList.get(i).add(splitWordContent.get(0).get(0)); //word itself
			//System.out.println(splitWordContent.get(1).get(0));
			definitionContent.add(splitDefinition(splitWordContent.get(1).get(0)));


			String definition = definitionContent.get(0).get(1);
			pos += extendForm(definitionContent.get(0).get(0));
			if (extendForm(definitionContent.get(0).get(0)).charAt(0) == '—')
			{
				definitionContent.add(splitDefinition(splitWordContent.get(1).get(1)));
				pos += ", " + extendForm(definitionContent.get(1).get(0));
				definition += definitionContent.get(1).get(1);
			}
			wordList.get(i).add(pos); //place of use
			wordList.get(i).add(splitWordContent.get(2).get(0).substring(1, splitWordContent.get(2).get(0).length()-1)); //origin
			wordList.get(i).add(definition); //definition

			splitWordContent.removeAll(splitWordContent);
			definitionContent.removeAll(definitionContent);
		}


		return wordList;
	}*/

	public ArrayList<ArrayList<String>> returnDisplayDictionary(String s)
	{
		ArrayList<String> list;
		ArrayList<ArrayList<String>> wordList = new ArrayList<>();
		ArrayList<ArrayList<String>> splitWordContent;
		ArrayList<ArrayList<String>> definitionContent = new ArrayList<>();
		list = english.getList();

		int returnI = 0;

		for(int i = 0; i  < list.size(); i++)
		{
			splitWordContent = splitLine(list.get(i));

			if(s.length() == 0 || splitWordContent.get(0).get(0).toLowerCase().contains(s.toLowerCase()))
			{
				wordList.add(new ArrayList<>());
				String pos = "";
				splitWordContent = splitLine(list.get(i));
				wordList.get(returnI).add(splitWordContent.get(0).get(0)); //word itself
				//System.out.println(splitWordContent.get(1).get(0));
				definitionContent.add(splitDefinition(splitWordContent.get(1).get(0)));


				String definition = definitionContent.get(0).get(1);
				pos += extendForm(definitionContent.get(0).get(0));
				if (extendForm(definitionContent.get(0).get(0)).charAt(0) == '—')
				{
					definitionContent.add(splitDefinition(splitWordContent.get(1).get(1)));
					pos += ", " + extendForm(definitionContent.get(1).get(0));
					definition += definitionContent.get(1).get(1);
				}
				wordList.get(returnI).add(pos); //place of use
				wordList.get(returnI).add(splitWordContent.get(2).get(0).substring(1, splitWordContent.get(2).get(0).length()-1)); //origin
				wordList.get(returnI).add(definition); //definition

				++returnI;
				splitWordContent.removeAll(splitWordContent);
				definitionContent.removeAll(definitionContent);
			}
		}


		return wordList;
	}

	public ArrayList<ArrayList<String>> returnDisplayDictionary()
	{
		return returnDisplayDictionary("");
	}

	//Searches if any of the word in the list fits the toSearch and then returns the whole string
	public String returnWordLine(String toSearch, Dictionary dictionary)
	{
		ArrayList <String> dictionaryList = dictionary.getEnglishWordLibrary().getList();

		for (String string : dictionaryList)
		{
			StringBuilder sb = new StringBuilder(string);
			String s[] = sb.toString().split("%", 2);

			if(s[0].equals(toSearch))
			{
				return string; //Result string = word:abbreviation:meaning:origin
			}
		}

		return "nan"; //Not found
	}

	public String returnWordLine(String toSearch)
	{
		return returnWordLine(toSearch, this);
	}



	//File related methods

	//This just takes a string and finds the file
	public static File getTextFile(String fileLocation)
	{
		File file = new File(fileLocation);

		return file;
	}

	//This reads the file and then adding each line in the file to an arraylist one by one
	public static ArrayList<String> readTextFile(File textFile)
	{
		ArrayList<String> list = new ArrayList<String>();

		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));

			try
			{
				for(String line; (line = bufferedReader.readLine()) != null; )
				{
					//System.out.println(line);
					list.add(line);
				}
				list.remove(0);
				bufferedReader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return list;
	}

	//Getters

	public EnglishWordLibrary getEnglishWordLibrary()
	{
		return english;
	}
}
