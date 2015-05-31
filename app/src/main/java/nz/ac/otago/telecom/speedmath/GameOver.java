package nz.ac.otago.telecom.speedmath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class GameOver extends Activity {
	MDatabaseAdapter vikahelper;
	DecimalFormat df;
	Button home, retry;
	TextView timeCompleted, incorrectAns, penalty, finalScore;
	long finalTime = GameScreenFragmentA.timeElapsed; //get score from GameScreen Activity
	int totalWrongAns = GameScreenFragmentA.wrongAns;
	double timePenalty = totalWrongAns * 0.3;
	public static double calculatedTime;
	int hours = (int) (finalTime / 3600000);
	int minutes = (int) (finalTime - hours * 3600000) / 60000;
	int seconds = (int) (finalTime - hours * 3600000 - minutes * 60000) / 1000; //convert the score to seconds
	double temp;
	double score;
	public static String newHighscore;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		vikahelper = new MDatabaseAdapter(this);
		df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		home = (Button) findViewById(R.id.home_btn3);
		retry = (Button) findViewById(R.id.retry_btn);
		timeCompleted = (TextView) findViewById(R.id.time_completed_txt);
		incorrectAns = (TextView) findViewById(R.id.incorrect_ans_txt);
		penalty = (TextView) findViewById(R.id.time_penalty_txt);
		finalScore = (TextView) findViewById(R.id.final_score_txt);
		temp = Math.round(timePenalty*100)/100.0;

		timeCompleted.setText(String.valueOf(seconds) + " seconds");
		incorrectAns.setText(String.valueOf(totalWrongAns));
		calculatedTime = seconds + timePenalty;
		score = Math.round(calculatedTime*100)/100.0;
		penalty.setText(temp + " seconds " + "(" + totalWrongAns + "*0.3)");
		finalScore.setText(String.valueOf(score));
		addScore();
		if(isHighScore()){
			Intent i = new Intent();
			i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.NewHighscore");
			startActivity(i);
		}
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.MainActivity");
				startActivity(i);
			}
		});
		retry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.CountdownTimer");
				startActivity(i);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void addScore(){
		String sc = finalScore.getText().toString();
		long id = vikahelper.insertScore(sc);
		if(id < 0) {
			Message.message(this, "Unsuccessful");
		}else{
			Message.message(this, "Successfully added row: " + id);
		}
	}

	public boolean isHighScore(){
		String stonedScore = vikahelper.getHighestScore();
		double d = Double.parseDouble(stonedScore);
		if(this.score <= d){
			newHighscore = String.valueOf(score);
			return true;
		}
		return false;
	}

	public void viewScores(View view){
		String data = vikahelper.getAllScores();
		Message.message(this, data);
		String ds = vikahelper.getGameDetails();
		Message.message(this, ds);
	}
}
