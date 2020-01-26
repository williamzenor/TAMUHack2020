package com.example.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestManager rq = new RequestManager();

        Button button = findViewById(R.id.inputconfirm);
        final TextView txt = findViewById(R.id.textView2);
        final MainActivity a = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serviceResult = "Error in connecting to service";
                //try{
                rq.get("http://10.228.118.215:5000/users/myuser/15", a);
                /*}catch(Exception e){
                    Log.e("Error: ", "Exception", e);
                }*/
                //txt.setText(serviceResult);
            }
        });
    }

}
