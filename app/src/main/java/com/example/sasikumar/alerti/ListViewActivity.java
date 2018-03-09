package com.example.sasikumar.alerti;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    DatabaseHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;
    ArrayList<String>name = new ArrayList<String>();
    ArrayList<String>mobile = new ArrayList<String>();


    ListView LISTVIEW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        LISTVIEW = (ListView) findViewById(R.id.listView1);
        SQLITEHELPER = new DatabaseHelper(this);

    /*    Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_display);
        if(toolbar!=null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("display");
        }

*/
            LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Displaydetails.class);
                    intent.putExtra("getint", "" + position);
                    startActivity(intent);
                }
            });


        ShowSQLiteDBdata() ;
    }
    @Override
    protected void onResume() {



        super.onResume();
    }
    private void ShowSQLiteDBdata(){
        SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();
        cursor = SQLITEDATABASE.rawQuery("SELECT name,mobile FROM contact", null);
        while(cursor.moveToNext())
        {
            name.add(cursor.getString(0));
            mobile.add(cursor.getString(1));

        }
        ListAdapter = new SQLiteListAdapter(ListViewActivity.this,name,mobile);
        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();

    }



}
