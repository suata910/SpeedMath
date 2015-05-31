package nz.ac.otago.telecom.speedmath;

/**
 * Created by Tavita on 13/05/2015.
 */
public class Player {
    private String username;
    private String name;

    public Player(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public Player(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
