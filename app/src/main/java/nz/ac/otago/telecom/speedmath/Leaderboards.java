package nz.ac.otago.telecom.speedmath;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Leaderboards extends Activity {

    TextView output;
    ProgressBar pb;
    List<MyTask> tasks;
    HTTPManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        manager = new HTTPManager();
        output = (TextView) findViewById(R.id.async_txt);
        pb = (ProgressBar) findViewById(R.id.async_progress);
        output.setMovementMethod(new ScrollingMovementMethod());
        pb.setVisibility(View.INVISIBLE);
        tasks = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_leaderboards,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==  R.id.action_settings){
            if (isOnline()){
                RequestData("http://tele303-backend-mg.appspot.com/rest");
            }else{
                Message.message(this, "network isn't available");
            }

        }
        return true;
    }

    private void RequestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    public void updateDisplay(String message){
        output.append(message + "\n");
    }

    protected boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    class MyTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            updateDisplay("Starting task");
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {
            String content = manager.run(params[0]);
            return content;
        }


        @Override
        protected void onPostExecute(String message) {
            updateDisplay(message);
            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            updateDisplay(values[0]);
        }
    }


}


