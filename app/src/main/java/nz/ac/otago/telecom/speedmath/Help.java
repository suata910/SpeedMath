package nz.ac.otago.telecom.speedmath;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class Help extends Activity {
	Button viewInBrowser;
	Button home;
	private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		viewInBrowser = (Button) findViewById(R.id.open_browser_btn);
		viewInBrowser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW, 
						Uri.parse("http://www.mathsisfun.com/basic-math-definitions.html"));
				startActivity(i);
			}
		});
		this.webView = (WebView) findViewById(R.id.webView1);
		this.webView.loadUrl("http://www.mathsisfun.com/basic-math-definitions.html");
		home = (Button) findViewById(R.id.home_btn2);
	}

	public void openHome(View v){
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
