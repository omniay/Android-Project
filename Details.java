package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Details extends AppCompatActivity {

    mydb db;
    ListView mylist;
    ArrayAdapter<String> ad;
    String ID;
    Cursor c;
    Integer buyquantity;
    Integer proquantity;
    Button add;
    String custid;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        add=(Button)findViewById(R.id.addorders);
        name=getIntent().getExtras().getString("Sel");
        custid=getIntent().getExtras().getString("id");
        mylist=(ListView)findViewById(R.id.listView);
        ad= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        mylist.setAdapter(ad);
        ad.clear();
        db=new mydb(getApplicationContext());
        ID=db.getProductID(name);
        c=db.getProductDetails(ID);

        //productname
        ad.add(c.getString(1));

        //productprice
        ad.add("Price: "+c.getString(2)+"LE");

        //Quantity
        ad.add("Quantity: "+c.getString(3));


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(Details.this,addorder.class);
                i.putExtra("pid",c.getString(0));
                i.putExtra("id", custid);
                i.putExtra("proname",c.getString(1));
                startActivity(i);
            }
        });


    }
}
