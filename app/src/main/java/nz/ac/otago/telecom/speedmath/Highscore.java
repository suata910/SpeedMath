package nz.ac.otago.telecom.speedmath;

/**
 * Created by Tavita on 12/05/2015.
 */

public class Highscore {
    String name;
    String highscore;

    public Highscore(){
    }

    public Highscore(String name, String highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    public String getName() {
        return name;
    }

    public String getHighscore() {
        return highscore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHighscore(String highscore) {
        this.highscore = highscore;
    }

    @Override
    public String toString() {
        return "Highscore{" +
                "name='" + name + '\'' +
                ", highscore='" + highscore + '\'' +
                '}';
    }
}
