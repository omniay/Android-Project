package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class onlineshopping extends AppCompatActivity {

    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onlineshopping);

         ImageButton MakeUP=(ImageButton)findViewById(R.id.makeup);

        ImageButton mobile=(ImageButton)findViewById(R.id.mobiles);

        ImageButton laptop=(ImageButton)findViewById(R.id.laptops);

        ImageButton clothe=(ImageButton)findViewById(R.id.clothes);

        ID=getIntent().getExtras().getString("id");
       MakeUP.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {
                Intent i=new Intent(onlineshopping.this,Listofproducts.class);
                i.putExtra("id",ID);
                i.putExtra("CategoryID",3);
                startActivity(i);

            }
        });
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {
                Intent i=new Intent(onlineshopping.this,Listofproducts.class);
                // String cat="mobile";
                i.putExtra("CategoryID",1);
                i.putExtra("id",ID);
                startActivity(i);

            }
        });
        laptop.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {
                Intent i=new Intent(onlineshopping.this,Listofproducts.class);
                i.putExtra("CategoryID",2);
                i.putExtra("id",ID);
                startActivity(i);

            }
        });
        clothe.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {
                Intent i=new Intent(onlineshopping.this,Listofproducts.class);
                i.putExtra("CategoryID",4);
                i.putExtra("id",ID);
                startActivity(i);

            }
        });
    }
}
