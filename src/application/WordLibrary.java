package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WordLibrary
{
	//File = txt file
	//list = contains txt content, each line in txt file is an array in the arraylist

	private File file;

	private String fileLocation;

	private ArrayList<String> list;

	//Constructor

	public WordLibrary(String input) throws IOException
	{
		this.fileLocation = input;

		this.file = Dictionary.getTextFile(fileLocation);

		this.list = Dictionary.readTextFile(file);
	}

	//Getters and setters

	public File getFile()
	{
		return file;
	}

	public String getFileLocation()
	{
		return fileLocation;
	}

	public ArrayList<String> getList()
	{
		return list;
	}
}
