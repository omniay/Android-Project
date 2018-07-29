package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Listofproducts extends AppCompatActivity {

    mydb db;
    Integer catid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofproducts);

        catid=getIntent().getExtras().getInt("CategoryID");
        ListView mylist=(ListView)findViewById(R.id.pro);
        ArrayAdapter<String> myad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        mylist.setAdapter(myad);
        myad.clear();
        db=new mydb (getApplicationContext());
        if(catid==1)
        {

            String ID = db.getCatID("Mobiles");

            Cursor cursor = db.getProducts(ID);
            while (!cursor.isAfterLast()) {
                myad.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        else if(catid==2)
        {

            String ID = db.getCatID("Laptops");
            Cursor cursor = db.getProducts(ID);
            while (!cursor.isAfterLast()) {
                myad.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        else if(catid==3)
        {

            String ID = db.getCatID("makeup");
            Cursor cursor = db.getProducts(ID);
            while (!cursor.isAfterLast()) {
                myad.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        else if(catid==4)
        {

            String ID = db.getCatID("Clothes");
            Cursor cursor = db.getProducts(ID);
            while (!cursor.isAfterLast()) {
                myad.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String Selceted= ((TextView)view).getText().toString();
                Intent i = new Intent(Listofproducts.this , Details.class);
                i.putExtra( "Sel" , Selceted);
                startActivity(i);
            }
        });

    }
}
