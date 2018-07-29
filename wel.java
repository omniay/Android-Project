package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class wel extends AppCompatActivity {

    private static int timeout=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel);
        Button in=(Button)findViewById(R.id.In);
        Button up=(Button)findViewById(R.id.Up);

        in.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               Intent Signin=new Intent(wel.this,SignIn.class);
                startActivity(Signin);

            }
        });
        up.setOnClickListener(new View.OnClickListener()

                              {
                                  public void onClick(View v)
                                  {
                                      Intent Signup=new Intent(wel.this,SignUp.class);
                                      startActivity(Signup);
                                  }
                              }

        );

    }
}
