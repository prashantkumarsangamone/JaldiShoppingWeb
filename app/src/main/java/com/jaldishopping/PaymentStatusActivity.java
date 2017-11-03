package com.jaldishopping;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;

public class PaymentStatusActivity extends AppCompatActivity {

    Button verifydoneBtn,vefiryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_status);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color


        verifydoneBtn = (Button)findViewById(R.id.verify_done);
        vefiryBtn = (Button)findViewById(R.id.verify);


        verifydoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PaymentStatusActivity.this, OrderVerifyActivity.class);
                startActivity(in);
            }
        });

        vefiryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PaymentStatusActivity.this, HomePageActivity.class);
                in.getBooleanExtra("check",true);
                startActivity(in);
            }
        });
    }
}
