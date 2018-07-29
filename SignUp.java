package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    mydb db;
    CalendarView c;
    String date;
    Button b;
    Date datee;
    int day;
    int month;
    int year;
    public  boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        date=":";
        c=(CalendarView)findViewById(R.id.calendarView);
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                String day=String.valueOf(dayOfMonth);
                String Month=String.valueOf(month);
                String Year=String.valueOf(year);
                date=day+"/"+Month+"/"+Year;



            }
        });
     //   Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();

        //   final mydb db;
       b=(Button)findViewById(R.id.next);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //  mydb db;

                db=new mydb(getApplicationContext());
                EditText name = (EditText) findViewById(R.id.Fullname);
                String fullname = name.getText().toString();

                EditText user = (EditText) findViewById(R.id.username);
                String username = user.getText().toString();


                EditText pass = (EditText) findViewById(R.id.Password);
                String password = pass.getText().toString();

                EditText work = (EditText) findViewById(R.id.job);
                String job = work.getText().toString();

                EditText mail = (EditText) findViewById(R.id.Email);
                String email = mail.getText().toString();
                // if(!isEmailValid(email))

                final Spinner sp = (Spinner) findViewById(R.id.spinner);
                String Gender = sp.getSelectedItem().toString();

               // Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();


               db = new mydb(getApplicationContext());
                if (fullname != null && username != null && email != null && password!=null&&Gender != null && job != null && date != null)
                {
                    db.Signup(fullname, username, email, password, Gender, job, date);
                    String ID=db.getcustomerID(username);
                    Intent i = new Intent(SignUp.this, searchordisplay.class);
                    i.putExtra("id",ID);
                    startActivity(i);

                    //  String bool = db.emailexist(email);
                    // String u=db.getuser(username);
                    /*f (db.emailexist(email))
                    {
                        Toast.makeText(getApplicationContext(), "Email is exist try another one or sign in", Toast.LENGTH_LONG).show();
                    }
                     if (db.getuser(username) != null)
                     {
                        Toast.makeText(getApplicationContext(), "Username is exist try another one ", Toast.LENGTH_LONG).show();
                    }
                    else if (isEmailValid(email))
                    {
                        db.Signup(fullname, username, email, password, Gender, job, date);

                            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                            String ID=db.getcustomerID(username);
                           Intent i = new Intent(SignUp.this, searchordisplay.class);
                            i.putExtra("id",ID);
                           startActivity(i);

                    }else if (!isEmailValid(email))
                    {
                        Toast.makeText(getApplicationContext(), "Email is invalid", Toast.LENGTH_LONG).show();

                    }

/*/
                }
                else
                    Toast.makeText(getApplicationContext(), "please fill all data", Toast.LENGTH_LONG).show();
            }

        });
    }
}
