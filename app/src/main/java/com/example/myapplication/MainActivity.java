package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Toast;

import com.e2ess.starprinter.MyListner;
import com.e2ess.starprinter.ToasterMessage;

public class MainActivity extends AppCompatActivity{

    public ToasterMessage test;
    public MyListner listner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listner = new MyListner() {
            @Override
            public void callback(String result) {
                Toast.makeText(getApplication(),result,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void callback() {

            }
        };

        test = new ToasterMessage(this.listner);
        test.s(getApplication(),"this is working...");

        test.connect();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}