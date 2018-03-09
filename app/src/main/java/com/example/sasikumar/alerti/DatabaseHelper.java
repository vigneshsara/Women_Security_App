package com.example.sasikumar.alerti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SASIKUMAR on 18-02-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
        public static final String Database_Name="student.db";
        public static final String Table_Name="contact";
        public static final String col1="id";
        public static final String  col2="name";
        public static final String col3="mobile";




        public DatabaseHelper(Context context) {
            super(context, Database_Name, null, 1);
            // SQLiteDatabase db=this.getWritableDatabase();

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+Table_Name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,mobile TEXT)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS"+Table_Name);
            onCreate(db);
        }
        public  boolean insertdata(String name,String mobile){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(col2,name);
            contentValues.put(col3,mobile);
            long result= db.insert(Table_Name,null,contentValues);
            if(result!=-1)
            {
                return  true;
            }
            else
            {
                return false;
            }

        }

        public Cursor mobileno()
        {
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor rer=db.rawQuery("select mobile from "+Table_Name,null);
            return rer;
        }
        public Integer deletedata(String id)
        {
            SQLiteDatabase db=this.getWritableDatabase();
            return db.delete(Table_Name,"ID = ?",new String[]{id});

        }


       public boolean updatedata(String id,String name,String mobile)
        {
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put("ID",id);
            contentValues.put("name",name);
            contentValues.put("mobile",mobile);
            db.update(Table_Name,contentValues,"ID= ?",new String[] {id});
            return true;
        }
    }
