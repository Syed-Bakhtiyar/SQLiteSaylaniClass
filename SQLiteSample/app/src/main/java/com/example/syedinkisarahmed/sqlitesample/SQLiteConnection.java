package com.example.syedinkisarahmed.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Syed Bakhtiyar on 11/6/2016.
 */
public class SQLiteConnection extends SQLiteOpenHelper {

    public static final String DB_NAME ="MyDb.db";
    public static final int VERSION =1;
    public static final String TABLE_NAME ="Record";

    public static final String _ID ="_ID";
    public static final String NAME ="Name";
    public static final String FNAME ="FName";
    public static final String MARKS ="Marks";

    ArrayList<Record> record;

    ContentValues values;

    SQLiteDatabase db;

    Context cont;

    String createTable = "CREATE TABLE "+TABLE_NAME+" ( "+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+NAME+" TEXT, "+FNAME+" TEXT, "+MARKS+" INTEGER)";


    public SQLiteConnection(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.cont = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);



    }



public void insertData(String name,String fName,int marks){

    db = this.getWritableDatabase();
    values = new ContentValues();

    values.put(NAME,name);
    values.put(FNAME,fName);
    values.put(MARKS,marks);
    long check = db.insert(TABLE_NAME,null,values);
    if(check==-1){
        Toast.makeText(cont, "Not Inserted", Toast.LENGTH_SHORT).show();


    }
    else {

        Toast.makeText(cont, "Inserted", Toast.LENGTH_SHORT).show();

    }




}


    public ArrayList<Record> readData(){

        db = this.getReadableDatabase();
        String name, fName;
        int marks;
        record = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        while (c.moveToNext()){
            name = c.getString(c.getColumnIndex(NAME));
            fName = c.getString(c.getColumnIndex(FNAME));
            marks = c.getInt(c.getColumnIndex(MARKS));

            record.add(new Record(name,fName,marks));

        }

        c.close();

        return  record;
    }



    public ArrayList<Record> searchData(String find){

        db = this.getReadableDatabase();
        String name, fName;
        int marks;
        record = new ArrayList<>();
        int flag =0;

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+NAME+" = "+"'"+find+"'",null);
        while (c.moveToNext()){
            flag++;
            name = c.getString(c.getColumnIndex(NAME));
            fName = c.getString(c.getColumnIndex(FNAME));
            marks = c.getInt(c.getColumnIndex(MARKS));

            record.add(new Record(name,fName,marks));

        }

        c.close();
        if(flag ==0){
            return null;

        }

        return  record;
    }






}
