package nz.ac.otago.telecom.speedmath;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tavita on 11/05/2015.
 */
public class MDatabaseAdapter{
    VikaHelper helper;

    public MDatabaseAdapter(Context context){
        helper = new VikaHelper(context);
    }

    public long insertGameDetails(String equation, String answer, String correct){
        long id = 0;
        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(VikaHelper.EQUATION, equation);
            contentValues.put(VikaHelper.ANSWER, answer);
            contentValues.put(VikaHelper.CORRECT, correct);
            id = db.insert(VikaHelper.TABLE_2, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public long insertScore(String score){
        long id = 0;
        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(VikaHelper.SCORE, score);
            id = db.insert(VikaHelper.TABLE_0, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public long addUser(String user, Double score){
        long id = 0;
        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(VikaHelper.NAME, user);
            contentValues.put(VikaHelper.HIGHSCORE, score);

            id = db.insert(VikaHelper.TABLE_1, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getGameDetails(){
        SQLiteDatabase database = helper.getWritableDatabase();
        String[] columns = {VikaHelper.EID, VikaHelper.EQUATION, VikaHelper.ANSWER, VikaHelper.CORRECT};
        Cursor cursor = database.query(VikaHelper.TABLE_2, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(VikaHelper.EID);
            int index2 = cursor.getColumnIndex(VikaHelper.EQUATION);
            int index3 = cursor.getColumnIndex(VikaHelper.ANSWER);
            int index4 = cursor.getColumnIndex(VikaHelper.CORRECT);

            int cid = cursor.getInt(index1);
            String equation = cursor.getString(index2);
            String answer = cursor.getString(index3);
            String correct = cursor.getString(index4);
            buffer.append(cid + " " + equation + " "+ answer + "" + correct + "\n");
        }
        return buffer.toString();
    }

    public String getAllHighscores(){
        SQLiteDatabase database = helper.getWritableDatabase();
        String[] columns = {VikaHelper.NAME, VikaHelper.HIGHSCORE};
        int limit = 5;
        Cursor cursor = database.query(VikaHelper.TABLE_1, columns, null, null, null, null, VikaHelper.HIGHSCORE, String.valueOf(limit));
        StringBuffer buffer = new StringBuffer();
        int i = 1;
        while (cursor.moveToNext()){
//            int index1 = cursor.getColumnIndex(VikaHelper.UID);
//            int cid = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(VikaHelper.NAME);
            String name = cursor.getString(index2);
            int index3 = cursor.getColumnIndex(VikaHelper.HIGHSCORE);
            String highscore = cursor.getString(index3);
//            buffer.append(cid + " " + name + " "+ highscore + ", ");
            buffer.append(i + " " + name + " "+ highscore + ", ");
            i++;
        }
        return buffer.toString();
    }

    public String getAllScores(){
        SQLiteDatabase database = helper.getWritableDatabase();
        String[] columns = {VikaHelper.SID, VikaHelper.SCORE};
        Cursor cursor = database.query(VikaHelper.TABLE_0, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(VikaHelper.SID);
            int cid = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(VikaHelper.SCORE);
            String score = cursor.getString(index2);
            buffer.append(cid + " " + score + "\n");
        }
        return buffer.toString();
    }

    public String getHighestScore(){
        SQLiteDatabase database = helper.getWritableDatabase();
        String[] columns = {VikaHelper.SCORE};
        int limit = 1;
        Cursor cursor = database.query(VikaHelper.TABLE_0, columns, "score=(SELECT MIN(score))", null, null, null, null, String.valueOf(limit));
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index2 = cursor.getColumnIndex(VikaHelper.SCORE);
            String score = cursor.getString(index2);
            buffer.append(score + "\n");
        }
        return buffer.toString();
    }



    static class VikaHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "speedmathdatabase";
        private static final int DATABASE_VERSION = 7;
        private static final String TABLE_0 = "scores";
        private static final String TABLE_1 = "highscores";
        private static final String TABLE_2 = "gamedetails";
        private static final String SID = "id";
        private static final String SCORE = "score";
        private static final String UID = "_id";
        private static final String NAME = "name";
        private static final String HIGHSCORE = "highscore";
        private static final String EID = "id";
        private static final String EQUATION = "equation";
        private static final String ANSWER = "answer";
        private static final String CORRECT = "correct";
        private Context context;
        private static final String CREATE_TABLE_0 = "CREATE TABLE " + TABLE_0 + "(" + SID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SCORE + " VARCHAR(255));";
        private static final String DROP_TABLE_0 = "DROP TABLE IF EXISTS " + TABLE_0;

        private static final String CREATE_TABLE_1 = "CREATE TABLE " + TABLE_1 + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(255), "+ HIGHSCORE + " REAL);";

        private static final String DROP_TABLE_1 = "DROP TABLE IF EXISTS " + TABLE_1;

        private static final String CREATE_TABLE_2 = "CREATE TABLE " + TABLE_2 + "(" + EID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EQUATION + " VARCHAR(255), " + ANSWER + "  VARCHAR(5), "+ CORRECT + "  VARCHAR(5));";
        private static final String DROP_TABLE_2 = "DROP TABLE IF EXISTS " + TABLE_2;

        public VikaHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
            Message.message(context, "constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try {
                Log.v("vikahelper","Before table_0 was created");
                sqLiteDatabase.execSQL(CREATE_TABLE_0);
                Log.v("vikahelper", "Before table_1 was created");
                sqLiteDatabase.execSQL(CREATE_TABLE_1);
                sqLiteDatabase.execSQL(CREATE_TABLE_2);
                Message.message(context, "onCreate called");
            } catch (SQLException e) {
                Message.message(context, ""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try {
                sqLiteDatabase.execSQL(DROP_TABLE_0);
                sqLiteDatabase.execSQL(DROP_TABLE_1);
                sqLiteDatabase.execSQL(DROP_TABLE_2);
                onCreate(sqLiteDatabase);
                Message.message(context, "onUpgrade was called");
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }
    }
}
