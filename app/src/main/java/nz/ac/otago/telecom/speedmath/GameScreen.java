package nz.ac.otago.telecom.speedmath;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class GameScreen extends Activity {
	CountDownTimer cd;
	MathLogic mathL;
	TextView equation, feedback, questions;
	Button trueBtn, falseBtn;
	int wrongAnswerCount = 0, total = 20;
	long startTime = 0;
	public Chronometer timer;
	public static long timeElapsed;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);
		timer = (Chronometer) findViewById(R.id.timer_chr);
		feedback =  (TextView) findViewById(R.id.feedback_txt);
		trueBtn = (Button) findViewById(R.id.true_btn);
		falseBtn = (Button) findViewById(R.id.false_btn);
		equation = (TextView) findViewById(R.id.math_statement_txt);
		questions = (TextView) findViewById(R.id.questions_txt);
		mathL = new MathLogic();
		timer.start();
		equation.setText(mathL.printEquation());
		questions.setText(String.valueOf(total));
		cd = new CountDownTimer(300,100) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				feedback.setText("");
			}
		};
		trueBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(mathL.correct){
					feedback.setTextColor(Color.GREEN);
					feedback.setText("Correct!");
					cd.start();
					mathL = new MathLogic();
					equation.setText(mathL.printEquation());
				}else{
					feedback.setTextColor(Color.RED);
					feedback.setText("Incorrect!");
					cd.start();
					mathL = new MathLogic();
					equation.setText(mathL.printEquation());
					wrongAnswerCount++;
				}
				total--;
				if(total>=0){
				questions.setText(String.valueOf(total));
				}else{
					timer.stop();
					timeElapsed = SystemClock.elapsedRealtime() - timer.getBase();
				}
			}
		});
		falseBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!mathL.correct){
					feedback.setTextColor(Color.GREEN);
					feedback.setText("Correct!");
					cd.start();
					mathL = new MathLogic();
					equation.setText(mathL.printEquation());
				}else{
					feedback.setTextColor(Color.RED);
					feedback.setText("Incorrect!");
					cd.start();
					mathL = new MathLogic();
					equation.setText(mathL.printEquation());
					wrongAnswerCount++;
				}
				total--;
				if(total>=0){
				questions.setText(String.valueOf(total));
				}else{
					timer.stop();
					timeElapsed = SystemClock.elapsedRealtime() - timer.getBase();
				}
			}
		});
	}

}
