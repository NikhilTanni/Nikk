package com.example.nikhil.blue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by Nikhil on 10/12/2017.
 */

public class Databasehelper extends SQLiteOpenHelper {

    public static final String table_name="sensor_info";
    public static final String col1="id";
    public static final String col2="name";
    public Databasehelper(Context context) {
        super(context,table_name,null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF table exists " + table_name);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable="create table " + table_name + " ( id integer primary key autoincrement, " + col2 + " text)";
        db.execSQL(createtable);
    }

    public void createtable(String sensorname, String val1, String val2)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String createtable="create table " + sensorname + " ( id integer primary key autoincrement, " + val1 + " text";
        createtable+=" , time text)";
        db.execSQL(createtable);
        return;
    }

    public void insert(String sensorname,String name1, String val1)
    {
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        SQLiteDatabase db=this.getWritableDatabase();
        String createtable="insert into " + sensorname + " ( " +name1+", time ) values ( '" + val1 + "','"+mydate+"' )";
        System.out.println(createtable);
        db.execSQL(createtable);
        return;
    }

    public boolean adddata(String item)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalue=new ContentValues();
        contentvalue.put(col2,item);

        Log.d(TAG , "Add data : "+item+" to "+table_name);
        long result=db.insert(table_name,null,contentvalue);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public String getdata(String tab)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String temp="";
        Cursor c=db.rawQuery("SELECT * FROM "+tab,null);
        if(c.moveToFirst())
        {
            do {
                temp += "\n\nid : " + c.getInt(0) + "\ntime : "+c.getString(2)+"\nvalue : " + c.getString(1);
            }while(c.moveToNext());
            c.close();
            return temp;
        }
        return null;
    }

    public ArrayList<String> getdata_array()
    {
        ArrayList<String> data=new ArrayList<String>();
        SQLiteDatabase db=this.getWritableDatabase();
//        String temp="";
        Cursor c=db.rawQuery("SELECT * FROM "+table_name,null);
        if(c.moveToFirst())
        {
            do {
//                temp += "\n\nid : " + c.getInt(0) + "\nsen name : " + c.getString(1);
                data.add(c.getInt(0)+"");
                data.add(c.getString(1));
            }while(c.moveToNext());
            c.close();
            return data;
        }
        return null;
    }
}
