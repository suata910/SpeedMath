package nz.ac.otago.telecom.speedmath;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class PlayGame extends Activity implements PGCommunicatorA, PGCommunicatorB{
	FragmentManager manager;
	GameScreenFragmentA fragA;
	GameScreenFragmentB fragB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		
//		GameScreenFragmentA fragA = new GameScreenFragmentA()
//		GameScreenFragmentB fragB = new GameScreenFragmentB();
//		FragmentManager manager = getFragmentManager();
//		FragmentTransaction transaction = manager.beginTransaction();
//		transaction.add(R.id.play_game_layout, fragA, "playGameFragmentA");
//		transaction.add(R.id.play_game_layout, fragB, "playGameFragmentB");
//		transaction.commit();
		manager = getFragmentManager();
		fragA = (GameScreenFragmentA) manager.findFragmentById(R.id.fragment_a);
		fragB = (GameScreenFragmentB) manager.findFragmentById(R.id.fragment_b);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
	}
	
	@Override
	public void clickCounter(int counter) {
		fragA.changeRemainingQuestions(counter);
	}


	@Override
	public void placePositiveFeedback() {
		fragA.leavePositiveFeedback();
	}

	@Override
	public void placeNegativeFeedback() {
		fragA.leaveNegativeFeedback();
	}

	@Override
	public void stopTimer() {
		fragA.stopTime();
	}

	@Override
	public int getRemainingQuestions() {
		return fragB.remainingQuestions();
	}

	@Override
	public void numCorrectAns(int num) {
		// TODO Auto-generated method stub
		fragA.changeScore(num);
	}
	
	@Override
	public int getCorrectAnswers() {
		// TODO Auto-generated method stub
		return fragB.correctAnsers();
	}

	@Override
	public String getMathStatement() {
		// TODO Auto-generated method stub
		return fragB.currentMathEquation();
	}

	
}
