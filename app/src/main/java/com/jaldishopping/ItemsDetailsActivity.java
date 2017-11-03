package com.jaldishopping;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItemsDetailsActivity extends AppCompatActivity {

    Button okButton;
    TextView offerTx,priceTx,itemTx,locationTx;
    TextView quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdetails_layout);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color
        itemTx  =(TextView) findViewById(R.id.textView6);
        priceTx  =(TextView) findViewById(R.id.textView8);
        offerTx  =(TextView) findViewById(R.id.offer);
        locationTx  =(TextView) findViewById(R.id.textView21);
        quantity=(TextView) findViewById(R.id.textView17);

        locationTx.setText("Location: "+getIntent().getStringExtra("location"));
        itemTx.setText(""+getIntent().getStringExtra("itemname")+" - "+getIntent().getStringExtra("brandName"));
        offerTx.setText("Our Price: $"+getIntent().getStringExtra("offer"));
        priceTx.setText("MRP: $"+getIntent().getStringExtra("price"));
        quantity.setText("Quantity: "+getIntent().getStringExtra("quantity"));

        okButton = (Button)findViewById(R.id.button6);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(ItemsDetailsActivity.this,BillingSummaryActivity.class);
                detailsIntent.putExtra("offer","MRP:$"+getIntent().getStringExtra("price"));
                detailsIntent.putExtra("quantity","1");
                startActivity(detailsIntent);
            }
        });

    }
    }


