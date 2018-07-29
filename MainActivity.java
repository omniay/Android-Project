package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static int timeout=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent go=new Intent(MainActivity.this,wel.class);
                startActivity(go);
                finish();
            }
        },timeout);
    }
}
