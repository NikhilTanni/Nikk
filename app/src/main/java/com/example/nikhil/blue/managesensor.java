package com.example.nikhil.blue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class managesensor extends AppCompatActivity {
    public TextView t;
    public Databasehelper db=new Databasehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managesensor);
//        t=(TextView)findViewById(R.id.textView);
//        t.setText(db.getdata()+"\n\n -------continue developing here-------------");
        ArrayList<String> data=new ArrayList<String>();
        data=db.getdata_array();
        for (int i = 0; i < data.size(); i+=2) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.activity_managesensor);
            layout.setOrientation(LinearLayout.VERTICAL);
            Button btn = new Button(this);
            btn.setId(i);
            final String name_ =data.get(i+1);
            btn.setText("Sensor id : "+data.get(i)+"\nName : "+data.get(i+1));
            layout.addView(btn);

            btn.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getApplicationContext(),conn_mgr.class);
                    i.putExtra("1",name_);
                    startActivity(i);
                }
            });
        }


    }


}
