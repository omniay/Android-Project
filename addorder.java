package com.example.mohamed.e_commerceproject;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class addorder extends AppCompatActivity {

    Date datee;
    String date;
    int day;
    int month;
    int year;
    int hours;
    int mins;
    int seconds;
    mydb db;
    String quant;
    String custid;
    String Name;
    EditText Quantity ;
    Button add;
    String ID;
    String productname;
    int productquan;
    int buyquant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addorder);
        add=(Button)findViewById(R.id.addtoshop);

        custid=getIntent().getExtras().getString("id");

        productname=getIntent().getExtras().getString("proname");

        ID=getIntent().getExtras().getString("pid");

        // ID=db.getProductID(Name);

        datee=new Date();

        seconds=datee.getSeconds();

        mins=datee.getMinutes();

        hours=datee.getHours();

        day=datee.getDate();

        month=datee.getMonth()+1;

        year=datee.getYear()+1900;


        date=String.valueOf(seconds)+":"+String.valueOf(mins)+":"+String.valueOf(hours)+":"+String.valueOf(day)+"-"+String.valueOf(month)+"-"+String.valueOf(year);

        Quantity=(EditText)findViewById(R.id.quantity);
        date="";


        add.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        quant = Quantity.getText().toString();
                        buyquant = Integer.parseInt(quant);
                        db = new mydb(getApplicationContext());
                        productquan = db.getquantity(ID);


                        if (buyquant <= productquan)
                        {
                          final   long id=db.addorder(date, Integer.parseInt(custid), "aaaaa");
                            int x= ((int) id);
                            if (db.addorder(date, Integer.parseInt(custid), "aaaaa")!=-1) ;
                            {  // ordid=orderID.intValue();
                                // String orderID = db.getorderID(date);
                                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();
                                //int newquan = productquan - buyquant;
                                //  db.updateorderqu(orderID, String.valueOf(newquan));

                            //db.orderdetails(x, Integer.parseInt(ID), Integer.parseInt(quant));

                              //  Toast.makeText(getApplicationContext(), "done2", Toast.LENGTH_LONG).show();


                                    //  Boolean bool= db.orderdetails(orderID, Long.getLong(PROid), quant);
                                    if (db.orderdetails(x, Integer.parseInt(ID), Integer.parseInt(quant)))
                                    {
                                        Toast.makeText(getApplicationContext(), "done3", Toast.LENGTH_LONG).show();
                                        int newquan = productquan - buyquant;
                                        db.updatequantity(ID, String.valueOf(newquan));
                                    }


                                }

                            }else
                            Toast.makeText(getApplicationContext(),"Error2",Toast.LENGTH_LONG).show();
                    }});
    }}



