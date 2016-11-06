package com.example.syedinkisarahmed.sqlitesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtFname, txtMarks, txtSearch;

    Button submit, read, search;

    String name, fName;
    int marks;


    ListView listv;
    CustomListAdapter custom;

    ArrayList<Record> record;

    SQLiteConnection mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txtName = (EditText) findViewById(R.id.txtName);
        txtFname = (EditText) findViewById(R.id.txtFname);
        txtMarks = (EditText) findViewById(R.id.txtMarks);
        txtSearch = (EditText) findViewById(R.id.search);

        submit = (Button) findViewById(R.id.submit);
        read = (Button) findViewById(R.id.read);
        search = (Button) findViewById(R.id.searchbtn);


        mydb = new SQLiteConnection(this);

        listv = (ListView) findViewById(R.id.list);

        record = new ArrayList<>();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    name = txtName.getText().toString();
                    fName = txtFname.getText().toString();
                    marks = Integer.parseInt(txtMarks.getText().toString());

                    mydb.insertData(name, fName, marks);
                }catch (Exception e){

                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              record=  mydb.readData();
                custom = new CustomListAdapter(record,getApplicationContext());
                listv.setAdapter(custom);



            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key;
                key = txtSearch.getText().toString();
                record=  mydb.searchData(key);
                if(record==null){
                    Toast.makeText(MainActivity.this, "No Search Found", Toast.LENGTH_SHORT).show();
                }else {
                    custom = new CustomListAdapter(record, getApplicationContext());
                    listv.setAdapter(custom);
                }

            }
        });



    }
}
