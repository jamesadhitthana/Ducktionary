package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class Database
{
    //Text files
    File folder = new File(System.getProperty("user.dir"));

    File[] matchingFiles = folder.listFiles(new FilenameFilter() {
        public boolean accept(File dir, String name)
        {
            return name.startsWith("Profile") && name.endsWith("txt"); //Change if file name is different
        }
    });

    private File profileTxtFile = matchingFiles[0];

    //Objects
    private ArrayList<Profile> userProfiles = new ArrayList<>();

    private Profile loggedInAs;

    //Constructor

    public Database()
    {
        InitializeProfiles();
    }

    //On startup
    private void InitializeProfiles() //Reads text file and creates new profile objects accordingly on app startup
    {
        try
        {
            //Variables for Profile constructor
            String email = "";
            String password = "";
            String username = "";
            ObservableList<String> stats = FXCollections.observableArrayList("");
            ObservableList<String> history = FXCollections.observableArrayList("");
            ObservableList<String> favorite = FXCollections.observableArrayList("");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(profileTxtFile));

            for(String line; (line = bufferedReader.readLine()) != null; )
            {
                if(!line.equals(""))
                {
                    String splitResult[] = line.split("%");

                    if(splitResult[0].equals("email"))
                    {
                        email = splitResult[1];
                    }
                    else if(splitResult[0].equals("password"))
                    {
                        password = splitResult[1];
                    }
                    else if(splitResult[0].equals("username"))
                    {
                        username = splitResult[1];
                    }
                    else if(splitResult[0].equals("stats"))
                    {
                        stats.clear();

                        for (int i = 1; i < splitResult.length; i++)
                        {
                            stats.add(splitResult[i]);
                        }
                    }
                    else if(splitResult[0].equals("history"))
                    {
                        history.clear();

                        for (int i = 1; i < splitResult.length; i++)
                        {
                            history.add(splitResult[i]);
                        }
                    }
                    else if(splitResult[0].equals("favorite"))
                    {
                        favorite.clear();

                        for (int i = 1; i < splitResult.length; i++)
                        {
                            favorite.add(splitResult[i]);
                        }

                        userProfiles.add(new Profile(email,password,username,stats,history,favorite));
                    }
                }
            }

            bufferedReader.close();
        }
        catch (Exception e)
        {
            System.out.println("Something goofed up : " + e);
        }
    }

    //On Exit
    public void UpdateProfileTextFile()
    {
        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(profileTxtFile));

            for (Profile f : userProfiles)
            {
                if(!f.getUsername().equals(loggedInAs.getUsername()))
                {
                    bufferedWriter.write("email%" + f.getUsername());
                    bufferedWriter.newLine();
                    bufferedWriter.write("password%" + f.getPassword());
                    bufferedWriter.newLine();
                    bufferedWriter.write("username%" + f.getUsername());
                    bufferedWriter.newLine();
                    bufferedWriter.write("stats");
                    for (String s : f.getStats())
                    {
                        bufferedWriter.write("%" + s);
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.write("history");
                    for (String s : f.getHistory())
                    {
                        bufferedWriter.write("%" + s);
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.write("favorite");
                    for (String s : f.getHistory())
                    {
                        bufferedWriter.write("%" + s);
                    }
                    bufferedWriter.newLine();
                }

            }

            bufferedWriter.write("email%" + loggedInAs.getUsername());
            bufferedWriter.newLine();
            bufferedWriter.write("password%" + loggedInAs.getPassword());
            bufferedWriter.newLine();
            bufferedWriter.write("username%" + loggedInAs.getUsername());
            bufferedWriter.newLine();
            bufferedWriter.write("stats");
            for (String s : loggedInAs.getStats())
            {
                bufferedWriter.write("%" + s);
            }
            bufferedWriter.newLine();
            bufferedWriter.write("history");
            for (String s : loggedInAs.getHistory())
            {
                bufferedWriter.write("%" + s);
            }
            bufferedWriter.newLine();
            bufferedWriter.write("favorite");
            for (String s : loggedInAs.getFavorite())
            {
                bufferedWriter.write("%" + s);
            }

            bufferedWriter.close();
        }
        catch (Exception e)
        {
            System.out.println("Goof ball : " + e);
        }

    }

    //Methods

    //Register will just create a new Profile object and adding it to userProfiles arraylist
    public void Register(String email, String password, String username)
    {
        try
        {
            ObservableList<String> stats = FXCollections.observableArrayList();
            stats.add("search:0");
            stats.add("add:0");
            stats.add("remove:0");
            ObservableList<String> history = FXCollections.observableArrayList();
            ObservableList<String> favorite = FXCollections.observableArrayList();

            userProfiles.add(new Profile(email,password,username,stats,history,favorite));
        }
        catch (Exception e)
        {
            System.out.println("Something goofed up : " + e);
        }
    }

    //Login will check every existing profiles in database if any inserted credential matches, returns true if found else false
    public boolean Login(String username, String password)
    {
        //If there are no users
        if(userProfiles == null)
        {
            return false;
        }

        for (Profile f: userProfiles)
        {
            if(f.getUsername().equals(username) && f.getPassword().equals(password))
            {
                loggedInAs = f;

                return true;
            }
        }

        return false;
    }

    public void Logout()
    {
        loggedInAs = null;
    }

    //Getters and setters

    public ArrayList<Profile> returnUserProfiles()
    {
        return this.userProfiles;
    }

    public Profile getLoggedInAs() {
        return loggedInAs;
    }
}
