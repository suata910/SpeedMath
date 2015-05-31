package nz.ac.otago.telecom.speedmath;

import java.util.Comparator;

/**
 * Created by Tavita on 12/05/2015.
 */
public class GameDetails implements Comparator<GameDetails>{
    private Integer equationID;
    private String equation;
    private String answer;
    private String correct;

    public GameDetails(){
    }

    public GameDetails(String equation, String correct, String answer) {
        this.equation = equation;
        this.correct = correct;
        this.answer = answer;
    }

    public String getEquation() {
        return equation;
    }

    public Integer getEquationID() {
        return equationID;
    }

    public void setEquationID(Integer equationID) {
        this.equationID = equationID;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    @Override
    public int compare(GameDetails gameDetails, GameDetails t1) {
        return gameDetails.getEquationID().compareTo(t1.getEquationID());
    }

    @Override
    public String toString() {
        return "GameDetails{" +
                "equationID=" + equationID +
                ", equation='" + equation + '\'' +
                ", answer='" + answer + '\'' +
                ", correct='" + correct + '\'' +
                '}';
    }
}
