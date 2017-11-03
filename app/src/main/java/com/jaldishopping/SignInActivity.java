package com.jaldishopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText emailEd,passwordEd;
    String email,password;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    TextView forgotPasswordTx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color

        pref = getApplicationContext().getSharedPreferences("jaldi_shopping_pref", MODE_PRIVATE);
        editor = pref.edit();
        emailEd = (EditText)findViewById(R.id.email);
        passwordEd = (EditText)findViewById(R.id.password);

        Button startShoppingBtn = (Button)findViewById(R.id.start_shopping);
        Button signUpBtn = (Button)findViewById(R.id.signup);
        forgotPasswordTx  =(TextView)findViewById(R.id.forgot_password);
        forgotPasswordTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
        startShoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, HomePageActivity.class);

                editor.putBoolean("check", false);
                editor.commit();
                startActivity(i);

            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailEd.getText().toString();
                password = passwordEd.getText().toString();

                String emailPref=pref.getString("email", null);
                String passwordPref=pref.getString("password", null);

                if(email.equals(emailPref)){
                    if(password.equals(passwordPref)){
                        Intent i = new Intent(SignInActivity.this, HomePageActivity.class);
                        editor.putBoolean("check", true);
                        editor.commit();
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Please enter correct password",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please enter correct email",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
