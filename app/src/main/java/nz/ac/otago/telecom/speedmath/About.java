package nz.ac.otago.telecom.speedmath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class About extends Activity{
	Button home_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		home_btn = (Button) findViewById(R.id.home_btn);
		home_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.MainActivity");
				startActivity(i);
			}
		});
	}

}
