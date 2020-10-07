package application;

import java.sql.*;
import java.util.ArrayList;
public class DatabaseFunction {

	public static boolean testConnect ()
	{
		String url = "jdbc:mysql://169.254.235.64:3306/";
        String user = "root";
        String password = "";

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Not connected to database. Will save to txt file");
            return false;
        }
	}

	public void addWordToDatabase (String a, String b, String c, String d)
	{
		String url = "jdbc:mysql://169.254.235.64:3306/";
        String user = "root";
        String password = "";

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stt = con.createStatement();

            stt.execute("USE Duck");

            /*
            stt.execute("DROP TABLE IF EXISTS word");
            stt.execute("CREATE TABLE word (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT,"
            		+ "word VARCHAR(50),"
                    + "part VARCHAR(4000),"
                    + "origin VARCHAR(4000),"
                    + "definition VARCHAR(4000),"
                    + "PRIMARY KEY(id),"
                    + "UNIQUE(word)"
                    + ")");
            */

            String sql = "INSERT INTO word (word, part, origin, definition) VALUES (?, ?, ?, ?) ";


            PreparedStatement ps = con.prepareStatement(
            		sql
            );



            	ps.setString(1, a);
                ps.setString(2, b);
                ps.setString(3, c);
                ps.setString(4, d);


            ps.execute();






        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

	void getDataFromDatabase()
	{
		String url = "jdbc:mysql://169.254.235.64:3306/";
        String user = "root";
        String password = "";

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stt = con.createStatement();


            stt.execute("USE Duck");

            /*
            stt.execute("DROP TABLE IF EXISTS word");
            stt.execute("CREATE TABLE word (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT,"
            		+ "kata VARCHAR(50),"
                    + "arti VARCHAR(4000),"
                    + "origin VARCHAR(4000),"
                    + "PRIMARY KEY(id),"
                    + "UNIQUE(kata)"
                    + ")");
            */
            String sql = "SELECT kata from word";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()){
            	System.out.println(rs.getString("origin"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

	void deleteDataFromDatabase(String word)
	{
		String url = "jdbc:mysql://169.254.235.64:3306/";
        String user = "root";
        String password = "";

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stt = con.createStatement();


            stt.execute("USE Duck");


            String sql = "DELETE ? FROM word";
            PreparedStatement ps = con.prepareStatement(
            		sql
            );

            ps.setString(1,word);
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}
