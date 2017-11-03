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
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    EditText nameEd,lastNameEd,mobileNumberEd,emailEd,passwordEd,zipCodeEd;
    String name,lastName,mobileNumber,email,password,zipCode;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color

        pref = getApplicationContext().getSharedPreferences("jaldi_shopping_pref", MODE_PRIVATE);
        editor = pref.edit();

        nameEd = (EditText)findViewById(R.id.name);
        lastNameEd = (EditText)findViewById(R.id.last_name);
        mobileNumberEd = (EditText)findViewById(R.id.mobile_number);
        emailEd = (EditText)findViewById(R.id.email);
        passwordEd = (EditText)findViewById(R.id.password);
        zipCodeEd = (EditText)findViewById(R.id.zip_code);

        Button signUpBtn = (Button)findViewById(R.id.signup);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEd.getText().toString();
                lastName = lastNameEd.getText().toString();
                mobileNumber = mobileNumberEd.getText().toString();
                email = emailEd.getText().toString();
                password = passwordEd.getText().toString();
                zipCode = zipCodeEd.getText().toString();
                if(name.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_LONG).show();
                }
                else if(lastName.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter Last name",Toast.LENGTH_LONG).show();
                }
                else if(mobileNumber.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter Mobile Number",Toast.LENGTH_LONG).show();
                }
                else if(email.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_LONG).show();
                }
                else if(password.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_LONG).show();
                }
                else if(zipCode.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter Zip Code",Toast.LENGTH_LONG).show();
                }
                else{
                    editor.putString("name", name);
                    editor.putString("lastName", lastName);
                    editor.putString("mobileNumber", mobileNumber);
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.putString("zipCode", zipCode);
                    editor.commit();

                    Intent i = new Intent(CreateAccountActivity.this, HomePageActivity.class);

                    editor.putBoolean("check", true);
                    editor.commit();
                    startActivity(i);
                }



            }
        });
    }

}
