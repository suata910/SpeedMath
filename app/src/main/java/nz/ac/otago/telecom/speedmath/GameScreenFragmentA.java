package nz.ac.otago.telecom.speedmath;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

public class GameScreenFragmentA extends Fragment{
	TextView feedback, questions, correctAnswers;
	public static int wrongAns;
	CountDownTimer cd;
	Chronometer timer;
	public static long timeElapsed;
	long startTime = 0;
	PGCommunicatorB comm;
	int numOfCorrectAns;
		
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.game_screen_fragment_a_layout, container, false);
		if(savedInstanceState != null){
			System.out.println("Orientation changed");
			String temp = savedInstanceState.getString("correct_ans");
			timeElapsed = savedInstanceState.getLong("saved_time");
			
	    	Chronometer tickTock = (Chronometer) view.findViewById(R.id.chronometer1);
	    	TextView rightAnswers = (TextView) view.findViewById(R.id.q_correct_txt);
	    	rightAnswers.setText(temp);
	    	
	    	System.out.println(rightAnswers.getText().toString());
	    	tickTock.setBase(timeElapsed);
	    	
	    }
		return view;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
//		long savedTime = SystemClock.elapsedRealtime() - timer.getBase();
		String temp = String.valueOf(numOfCorrectAns);
		System.out.println(temp);
		outState.putLong("saved_time", timer.getBase());
		outState.putString("correct_ans", temp);
		super.onSaveInstanceState(outState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		comm=(PGCommunicatorB) getActivity();
		questions = (TextView) getActivity().findViewById(R.id.remaining_questions_txt);
		feedback = (TextView) getActivity().findViewById(R.id.feedback_txt_2);
		numOfCorrectAns = comm.getCorrectAnswers();
		questions.setText(String.valueOf(comm.getRemainingQuestions()));
		timer = (Chronometer) getActivity().findViewById(R.id.chronometer1);
		correctAnswers = (TextView) getActivity().findViewById(R.id.q_correct_txt);
		correctAnswers.setText(String.valueOf(numOfCorrectAns));
		timer.start();
		cd = new CountDownTimer(300,100) {

			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {
				feedback.setText("");
			}
		};
	}
	
	
	public void changeRemainingQuestions(int data){
		questions.setText(String.valueOf(data));
	}
	
	public void leavePositiveFeedback(){
		//numOfCorrectAns++;
		feedback.setTextColor(Color.GREEN);
		feedback.setText("Correct!");
		cd.start();
	}
	
	public void leaveNegativeFeedback(){
		feedback.setTextColor(Color.RED);
		feedback.setText("Incorrect!");
		cd.start();
	}
	
	public void stopTime(){
		timer.stop();
		timeElapsed = SystemClock.elapsedRealtime() - timer.getBase();
		wrongAns = 20 - Integer.parseInt(correctAnswers.getText().toString());
		Intent i = new Intent();
		i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.GameOver");
		//i.setClassName("nz.ac.otago.telecom.speedmath","nz.ac.otago.telecom.speedmath.NewHighscore");
		startActivity(i);
	}
	
	public void changeScore(int num){
		numOfCorrectAns = num;
		correctAnswers.setText(String.valueOf(numOfCorrectAns));
	}
	
	
}
