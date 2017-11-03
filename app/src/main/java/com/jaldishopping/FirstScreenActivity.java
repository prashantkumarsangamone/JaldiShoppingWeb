package com.jaldishopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class FirstScreenActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        new Thread(){
            public void run(){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        doInitialSetup();
                    }
                });
            }
        }.start();
    }

    private void doInitialSetup(){

        pref = this.getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
        editor = pref.edit();
        String emailPref = pref.getString("email", null);
        Boolean check = pref.getBoolean("check",false);
        if(emailPref!=null&&check.equals(true)){
            Intent i = new Intent(FirstScreenActivity.this, HomePageActivity.class);

            editor.putBoolean("check", true);
            editor.commit();
            startActivity(i);
            finish();
        }
        else{
            Intent i = new Intent(FirstScreenActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}
