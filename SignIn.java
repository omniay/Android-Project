package com.example.mohamed.e_commerceproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    mydb db;
    TextView ForgetPassword;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private CheckBox saveLoginCheckBox;
    EditText use;
    EditText password;
    String u;
    String p;
    Button log;
    String custid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        use =(EditText)findViewById(R.id.user) ;

        password=(EditText)findViewById(R.id.pass);

        log=(Button)findViewById(R.id.login1);

        ForgetPassword=(TextView)findViewById(R.id.forget);

        saveLoginCheckBox=(CheckBox)findViewById(R.id.remeber);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            use.setText(loginPreferences.getString("username", ""));
            password.setText(loginPreferences.getString("password", ""));
            //saveLoginCheckBox.setChecked(true);
        }
        db=new mydb(getApplicationContext());
        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignIn.this,forgetpassword.class);
                startActivity(i);
            }
        });


        log.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                db=new mydb(getApplicationContext());
                p= password.getText().toString();

                u= use.getText().toString();

                if(u!=null&&p!=null)
                {
                    if (db.signin(u,p))
                    {
                        if(saveLoginCheckBox.isChecked()) {
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("username", u);
                            loginPrefsEditor.putString("password", p);
                            loginPrefsEditor.commit();
                        }
                        else
                        {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.commit();
                        }
                        Toast.makeText(getApplicationContext(), "Welcome "+u, Toast.LENGTH_LONG).show();
                        Intent in = new Intent(SignIn.this, searchordisplay.class);
                        //in.putExtra("User", u);
                        custid=db.getcustomerID(u);
                        in.putExtra("id", custid);
                        startActivity(in);
                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                        // Intent in = new Intent(SignIn.this, SignUP.class);
                        // startActivity(in);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Fill all data", Toast.LENGTH_LONG).show();

            }
        });
    }
}
