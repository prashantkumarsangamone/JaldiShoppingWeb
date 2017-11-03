package com.jaldishopping;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderVerifyActivity extends AppCompatActivity {

    Button payBtn;
    TextView items,price;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_verify_layout);
        payBtn = (Button)findViewById(R.id.button9);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color

        pref = this.getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
        editor = pref.edit();
        items=(TextView) findViewById(R.id.textView20);
        price  =(TextView) findViewById(R.id.textView22);

        String number = pref.getString("price", null);
        final String quantity= pref.getString("quantityCount", null);
        Log.d("TestTag","number:"+number);
        int totalPrice = Integer.parseInt(number);//*Integer.parseInt(number);
        price.setText("Price:"+"$"+String.valueOf(totalPrice));
        items.setText("Quantity:"+quantity);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(OrderVerifyActivity.this, OrderVerifiedActivity.class);
                startActivity(in);
            }
        });
    }
}
