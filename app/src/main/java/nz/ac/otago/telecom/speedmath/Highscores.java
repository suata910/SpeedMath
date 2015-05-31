package nz.ac.otago.telecom.speedmath;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Highscores extends Activity {
    ListView mainList;
    MDatabaseAdapter vikahelper;
    String [] highscores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_highscores);
        vikahelper = new MDatabaseAdapter(this);
        String data = vikahelper.getAllHighscores();
        highscores = data.split(", ");
//        Message.message(this, highscores[0]);
        mainList = (ListView) findViewById(R.id.leaderboards_lst);
        mainList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        new MyTask().execute();
    }



    class MyTask extends AsyncTask<Void, String, Void> {
        private ArrayAdapter<String> adapter;
        private int count = 0;
        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) mainList.getAdapter();
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(String item:highscores){
                publishProgress(item);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            setProgress((int)(((double)count/highscores.length)*10000));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setProgressBarVisibility(false);
            Message.message(Highscores.this, "All items added successfully");
        }
    }
}
