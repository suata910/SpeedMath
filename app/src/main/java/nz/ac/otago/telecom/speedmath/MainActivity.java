package nz.ac.otago.telecom.speedmath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity{
	Button play, help, about, highscores;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		play = (Button) findViewById(R.id.play_btn);
		help = (Button) findViewById(R.id.help_btn);
		about = (Button) findViewById(R.id.about_btn);
		highscores = (Button) findViewById(R.id.highscores_btn);
		play.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent();
				i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.CountdownTimer");
				//i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.PlayGame");
				startActivity(i);
			}
		});
		help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.Help");
				startActivity(i);
			}
		});
		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.About");
				startActivity(i);
			}
		});
		highscores.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.Highscores");
				startActivity(i);
			}
		});
	}

	
}
