package nz.ac.otago.telecom.speedmath;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CountdownTimer extends Activity {
	public TextView countdown;
	private long remainingTime;
	private final long startTime = 4 * 1000;
	private final long interval = 1 * 500;
	CountDownTimer countDownTimer;

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		System.out.println("orientation time");
		if(savedInstanceState == null){
			remainingTime = 4000;
		}else{
			remainingTime = savedInstanceState.getLong("time",4000);
			System.out.println(remainingTime);
		}
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_countdown_timer);
		if(savedInstanceState == null){
			remainingTime = 4000;
		}else{
			remainingTime = savedInstanceState.getLong("time");
		}
		countdown = (TextView) findViewById(R.id.countdown_txt);
		countDownTimer = new CountDownTimer(remainingTime, interval) {
			
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				remainingTime = millisUntilFinished;
				countdown.setText("" + millisUntilFinished / 1000);
				
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				countdown.setText("Begin!");
				Intent i = new Intent();
				//i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.GameScreen");
				i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.PlayGame");
				startActivity(i);
			}
		};
		//countdown.setText(countdown.getText() + String.valueOf(startTime / 1000));
		countDownTimer.start();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putLong("time", remainingTime);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		countDownTimer.cancel();
	}

}
