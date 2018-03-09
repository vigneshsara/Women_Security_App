package com.example.sasikumar.alerti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_contacts extends AppCompatActivity {
    private DatabaseHelper mydb;
    EditText e1,e2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        mydb=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.editTextname);
        e2=(EditText)findViewById(R.id.editTextmobile);
        b1=(Button)findViewById(R.id.buttonsave);
        AddData();

    }
    public void AddData(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isres = mydb.insertdata(e1.getText().toString(), e2.getText().toString());
                if (isres == true){
                    Toast.makeText(Add_contacts.this,"data inserted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Add_contacts.this,"data  not inserted",Toast.LENGTH_SHORT).show();
                }


                e2.setText(null);
                e1.setText(null);
            }
        });
    }

}
