package com.example.sasikumar.alerti;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Displaydetails extends AppCompatActivity {
    private DatabaseHelper mydb2;
     String index;
     Button b1;
    EditText e1,e2;
    static int temp=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydetails);
        b1=(Button)findViewById(R.id.buttonsave);
        e1=(EditText)findViewById(R.id.Texteditname);
        e2=(EditText)findViewById(R.id.Texteditmobile);
        mydb2=new DatabaseHelper(this);


        if(getIntent().getExtras().getString("getint")!=null)
        {
           index=getIntent().getExtras().getString("getint");
            Toast.makeText(getApplicationContext(),index,Toast.LENGTH_LONG).show();
        }
         getdata();
        update();
    }

    private void getdata(){

        String name1 = null,mobile1=null;
        SQLiteDatabase db=mydb2.getWritableDatabase();
        Cursor r=db.rawQuery("select name,mobile from contact where ID="+(Integer.parseInt(index)+temp),null);
        temp=temp+1;

        while(r.moveToNext())
        {
           name1=r.getString(0);
            mobile1=r.getString(1);
        }
       e1.setText(name1);
        e2.setText(mobile1);

    }

    public  void update(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean up = mydb2.updatedata(index+1, e1.getText().toString(), e2.getText().toString());
                if (up == true){
                    Toast.makeText(getApplicationContext(),"data is updated",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Data is Not Inserted",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}
