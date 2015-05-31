package nz.ac.otago.telecom.speedmath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NewHighscore extends Activity {

    MDatabaseAdapter vikahelper;
    EditText username;
    TextView highscore;
    Button button;
    HTTPManager client;
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_highscore);
        client = new HTTPManager();
        username = (EditText) findViewById(R.id.username);
        button = (Button) findViewById(R.id.sumbit_user_btn);
        highscore = (TextView) findViewById(R.id.new_highscore_txt);
        highscore.setText(GameOver.newHighscore);
        vikahelper = new MDatabaseAdapter(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = username.getText().toString();
                Double nh = Double.parseDouble((String) highscore.getText());
                long id = vikahelper.addUser(user,nh);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        player = new Player(user,user);
                        Log.v("vika", "user added successfully");
                        client.addNewPlayer(player);
                    }
                }).start();

                if(id < 0) {
                    Log.v("vika", "Unsuccessful");
                }else{
                    Log.v("vika", "Successfully added row");
                }
                Intent i = new Intent();
                i.setClassName("nz.ac.otago.telecom.speedmath", "nz.ac.otago.telecom.speedmath.MainActivity");
                startActivity(i);
            }
        });
    }

    public void viewHighscores(View view){
        String data = vikahelper.getAllHighscores();
        Message.message(this, data);
    }
}
