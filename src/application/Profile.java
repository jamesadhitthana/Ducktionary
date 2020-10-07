package application;

import javafx.collections.ObservableList;

public class Profile
{
    private String email;
    private String password;
    private String username;
    private ObservableList<String> stats;
    private ObservableList<String> history;
    private ObservableList<String> favorite;

    public Profile(String e, String p, String u, ObservableList<String> s, ObservableList<String> h, ObservableList<String> f)
    {
        super();
        this.email = e;
        this.password = p;
        this.username = u;
        this.stats = s;
        this.history = h;
        this.favorite = f;
    }

    //Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ObservableList<String> getStats() {
        return stats;
    }

    public void setStats(ObservableList<String> stats) {
        this.stats = stats;
    }

    public ObservableList<String> getHistory() {
        return history;
    }

    public void setHistory(ObservableList<String> history) {
        this.history = history;
    }

    public ObservableList<String> getFavorite() {
        return favorite;
    }

    public void setFavorite(ObservableList<String> favorite) {
        this.favorite = favorite;
    }
}
