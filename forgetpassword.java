package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgetpassword extends AppCompatActivity {

    Button sendmail;
    EditText user;
    String username;
    String Email;
    mydb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        sendmail=(Button)findViewById(R.id.send);
        user=(EditText)findViewById(R.id.forgetpass);

        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user.getText().toString();
                db = new mydb(getApplicationContext());
                Email = db.getemail(username);
                sendEmail(Email);

            }
        });





    }
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
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "your password ");
        emailIntent.putExtra(Intent.EXTRA_TEXT, password);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(), password, Toast.LENGTH_LONG).show();
    }

}
