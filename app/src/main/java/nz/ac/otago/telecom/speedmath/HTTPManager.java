package nz.ac.otago.telecom.speedmath;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Tavita on 12/05/2015.
 */
public class HTTPManager {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    private final String baseURI = "http://tele303-scores-04.appspot.com/rest";
    private final Gson gson = new Gson();
    private Response response;

    public static void main(String[] args) throws IOException{
        HTTPManager client = new HTTPManager();
//        client.addNewPlayer();
        client.getPlayers();
//        client.newGame();
        client.checkUserName();
    }

    public void getPlayers() throws IOException {
        System.out.println("\n-- Get Players --");

        Request request = new Request.Builder()
                .url(baseURI + "/players")
                .build();

        Response response = client.newCall(request).execute();

        Player[] players = gson.fromJson(response.body().string(), Player[].class);

        for (Player player : players) {
            System.out.println(player);
        }
    }

    public void addNewPlayer(Player p){
        System.out.println("\n-- Add new Player --");

        String json = gson.toJson(p);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(baseURI + "/players")
                .addHeader("content-type", "application/json")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.priorResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newGame() {
        System.out.println("\n-- Create a new game --");
        try {
            Game game = new Game(null, 300, 60, new Date().getTime());  // game ID is server-assigned, so leave null here

            // equation ids should represent the order in which the equations were shown the player
            game.addEquation(new Equation(1, "1 + 1 = 4", Boolean.TRUE, Boolean.FALSE));
            game.addEquation(new Equation(2, "3 + 4 = 7", Boolean.TRUE, Boolean.TRUE));
            game.addEquation(new Equation(3, "4 + 2 = 6", Boolean.FALSE, Boolean.TRUE));

            String json = gson.toJson(game);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(baseURI + "/players/fred/games")
                    .addHeader("content-type", "application/json")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println("Response: " + response.priorResponse());
        } catch (IOException ex) {
            System.out.println("Joe doesn't exist");
        }
    }

    public void checkUserName() {
        System.out.println("\n-- Check User Name --");
        try {

            Request request = new Request.Builder()
                    .url(baseURI + "/players/fred")
                    .build();

            Response response = client.newCall(request).execute();
            Player kid = gson.fromJson(response.body().string(), Player.class);
            System.out.println(kid);
        } catch (IOException ex) {
            System.out.println("'wilma' is available for use.");
        }
    }

    public String run(String url){

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}