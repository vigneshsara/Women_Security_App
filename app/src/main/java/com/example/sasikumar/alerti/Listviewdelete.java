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

public class Listviewdelete extends AppCompatActivity {
    DatabaseHelper SQLITEHELPER1;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> mobile = new ArrayList<String>();


    ListView LISTVIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewdelete);
        LISTVIEW = (ListView) findViewById(R.id.listView1);
        SQLITEHELPER1 = new DatabaseHelper(this);
        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                //Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_LONG).show();
               // Integer delete=SQLITEHELPER1.deletedata(""+(position+1));
                int temp=position+1;
                SQLiteDatabase db=SQLITEHELPER1.getWritableDatabase();
                db.delete("contact","ID"+"="+(position+1),null);
              //  db.rawQuery("DELETE FROM contact WHERE id="+temp,null);
               /* if(delete>0)
                {
                    Toast.makeText(getApplicationContext(),"data deleted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"data not deleted",Toast.LENGTH_LONG).show();

                }*/
                Intent it=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(it);
            }
        });


        ShowSQLiteDBdata();
    }
    @Override
    protected void onResume() {

        super.onResume();
    }

    private void ShowSQLiteDBdata() {
        SQLITEDATABASE = SQLITEHELPER1.getWritableDatabase();
        cursor = SQLITEDATABASE.rawQuery("SELECT name,mobile FROM contact", null);
        while (cursor.moveToNext()) {
            name.add(cursor.getString(0));
            mobile.add(cursor.getString(1));

        }
        ListAdapter = new SQLiteListAdapter(Listviewdelete.this, name, mobile);
        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();

    }
}