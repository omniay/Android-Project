package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.database.Cursor;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class searchordisplay extends AppCompatActivity {

    String ID;
    mydb db;
    int voiceCode=1;
    ArrayAdapter<String> ad;
    EditText product;
    ListView mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchordisplay);
        ID=getIntent().getExtras().getString("id");



        Button dis = (Button) findViewById(R.id.Display);

        Button shop = (Button) findViewById(R.id.shopcart);

        ImageButton Searchbytext = (ImageButton) findViewById(R.id.searchbut);

        ImageButton Searchbyvoice = (ImageButton) findViewById(R.id.searchbyvoice);

        mylist = (ListView) findViewById(R.id.list1);

        ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);

        mylist.setAdapter(ad);

        ad.clear();

        product = (EditText) findViewById(R.id.prodedit);

        dis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(searchordisplay.this,onlineshopping.class);
                i.putExtra("id",ID);
                startActivity(i);

            }
        });

        shop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(searchordisplay.this, Shopcart.class);
                i.putExtra("id",ID);
                startActivity(i);

            }
        });



        Searchbytext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.clear();

                db = new mydb(getApplicationContext());
                String Product = product.getText().toString();
                Cursor cursor = db.search(Product);
                while (!cursor.isAfterLast())
                {
                    ad.add(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        });

        Searchbyvoice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(i,voiceCode);
            }
        });

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Name = ((TextView) view).getText().toString();
                String ProID = db.getProductID(Name);
                Intent i = new Intent(searchordisplay.this, Details.class);
                i.putExtra("Sel", Name);
                i.putExtra("id",ID);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == voiceCode && resultCode == RESULT_OK) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ad.clear();
            product.setText(text.get(0));
            String word = text.get(0);
            if (word != null) {
                db = new mydb(getApplicationContext());
                Cursor c = db.search(word);
                if (c.getCount() != 0) {
                    while (!c.isAfterLast()) {
                        ad.add(c.getString(0));
                        c.moveToNext();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Not found ...Try Again", Toast.LENGTH_LONG).show();

            }


            mylist.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String Name = ((TextView) view).getText().toString();
                    String ProID = db.getProductID(Name);
                    Intent i = new Intent(searchordisplay.this, Details.class);
                    i.putExtra("Sel", Name);
                    i.putExtra("id",ID);
                    startActivity(i);
                }
            });
        }
    }
}
