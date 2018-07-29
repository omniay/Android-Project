package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Shopcart extends AppCompatActivity {

    ListView shopcart;
    ArrayAdapter<String> shopaddapter;
    mydb db;
    String ID;
    ArrayList<String> array;
    Integer newquantity;
    Integer oldquantity;
    String orderid;
    String PRODUCTID;
    String ProductName;
    Integer quantity;
    Integer productquantity;
    Cursor c;
    Integer neworder;
    protected void sendEmail(String Email)
    {
        db = new mydb(getApplicationContext());
        String password = db.returnpasswrod(Email);
        // Log.i
        Log.i("Send email", "");
        String[] TO = {Email};
        String[] CC = {
                "omniay646@gmail.com"
        };
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse(" :"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "order is submitted ");
          emailIntent.putExtra(Intent.EXTRA_TEXT, "Order is submitted");
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(), password, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcart);

        ID = getIntent().getExtras().getString("id");
        final TextView name = (TextView) findViewById(R.id.textView10);

        final EditText quantit = (EditText) findViewById(R.id.proquan);

        final Button edit=(Button)findViewById(R.id.Edit);

        final Button remove=(Button)findViewById(R.id.Remove);


        shopcart = (ListView) findViewById(R.id.listoforder);

        shopaddapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);


        final Button Submit=(Button)findViewById(R.id.submit);
        //    final Button isSubmit=(Button)findViewById(R.id.issub);

        db = new mydb(getApplicationContext());

        shopcart.setAdapter(shopaddapter);
        shopaddapter.clear();

        Cursor c = db.getallorders(ID);


        while(!c.isAfterLast())
        {


            shopaddapter.add(c.getString(0));
            //  Toast.makeText(getApplicationContext(),"not",Toast.LENGTH_LONG).show();

            c.moveToNext();
        }
        shopcart.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                orderid = ((TextView) view).getText().toString();
                String c1=db.Issubmited(orderid);
                if(c1!="DONE") {

                    PRODUCTID = db.getproductid(orderid);

                    ProductName = db.getProductname(PRODUCTID);

                    quantity = db.orderquantity(orderid);

                    productquantity = db.getquantity(PRODUCTID);

                    oldquantity = quantity;

                    name.setText(ProductName);

                    quantit.setText(String.valueOf(quantity));

                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            neworder=quantity;
                             neworder = Integer.parseInt(quantit.getText().toString());

                            if (neworder < oldquantity) {
                                newquantity = oldquantity - neworder;
                                if (newquantity <= productquantity)
                                {
                                    db.updatequantity(PRODUCTID, String.valueOf(productquantity +newquantity));
                                    db.updateorderqu(PRODUCTID,String.valueOf(neworder));
                                    Toast.makeText(getApplicationContext(), "Quantity updated", Toast.LENGTH_LONG).show();}
                                else
                                    {
                                    Toast.makeText(getApplicationContext(), "Unavaliable quantity", Toast.LENGTH_LONG).show();
                                }

                            }
                            else if (neworder > oldquantity)
                            {
                                newquantity = neworder - oldquantity;
                                if (newquantity <=productquantity)
                                {
                                    db.updatequantity(PRODUCTID, String.valueOf(productquantity - newquantity));
                                    db.updateorderqu(PRODUCTID,String.valueOf(neworder));
                                    Toast.makeText(getApplicationContext(), "Quantity updated", Toast.LENGTH_LONG).show(); }
                                else

                                    {
                                    Toast.
                                            makeText(getApplicationContext(), "Unavaliable quantity", Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                    });

                    remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            db.deleteorder(orderid);
                            db.updatequantity(PRODUCTID, String.valueOf(productquantity + oldquantity));
                            db.updateorderqu(PRODUCTID,String.valueOf(0));

                            Toast.makeText(getApplicationContext(), "Order Deleted", Toast.LENGTH_LONG).show();


                        }
                    });
                    Submit.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (quantit.getText().toString() != null) {
                                if (db.sumbitorder(orderid)) {
                                    Toast.
                                            makeText(getApplicationContext(), "Order Submitted", Toast.LENGTH_LONG).show();
                                }

                                //  Intent i=new Intent(ShopCart.this,searchordisplay.class);
                                //startActivity(i);

                            } else {
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                               // quantit.setError("");

                            }
                            String user=db.getcustomername(ID);
                            String mail=db.getemail(user);
                            sendEmail(mail);
                        }

                    });


                }
                else
                    Toast.makeText(getApplicationContext(), "order is submitted", Toast.LENGTH_LONG).show();
            }
        });


    }
}
