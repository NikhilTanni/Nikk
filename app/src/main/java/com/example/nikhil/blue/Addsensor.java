package com.example.nikhil.blue;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addsensor extends AppCompatActivity {
    public Button new_sensor;
    public EditText sen_name, val1, val2;
    public Databasehelper db=new Databasehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsensor);
        sen_name=(EditText)findViewById(R.id.newsensorname);
        new_sensor=(Button)findViewById(R.id.createnewsensor_btn);
        val1=(EditText)findViewById(R.id.sen_value1);
        val2=(EditText)findViewById(R.id.sen_value2);
        new_sensor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if((val1.getText().toString()!="" && !val1.getText().toString().replaceAll(" ","").isEmpty()) && db.adddata(sen_name.getText().toString()))
                {
                    db.createtable(sen_name.getText().toString(),val1.getText().toString(),val2.getText().toString());
                    toastmsg("new sensor added\nvalue1 : "+val1.getText().toString()+"\nvalue2 : "+val2.getText().toString());
                    finish();
                }
                else
                {
                    toastmsg("failed to add sensor!!!!");
                }
            }
        });
    }


    public void toastmsg(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
