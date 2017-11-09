package com.jaldishopping;


import android.content.Context;
import android.content.Intent;

import com.jaldishopping.adapter.OrderItemsAdapter;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingCartPageActivity extends AppCompatActivity {
    View view;
    Button scanBtn, checkoutBtn;
    ListView ls;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    boolean f;
    String barcodeValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scanbarcode_checkout_layout);



        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color

        pref = this.getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
        editor = pref.edit();
        f= pref.getBoolean("f", false);
        ls = (ListView) findViewById(R.id.list);


        barcodeValue = this.getIntent().getStringExtra("barcodeValue");

        if (f == true) {
               /* final Dialog dialog = new Dialog(getActivity());
                // Include dialog.xml file
                dialog.setContentView(R.layout.checkout_dialog);
                dialog.show();

                Button wishlistButton = (Button) dialog.findViewById(R.id.button3);
                Button cartButton = (Button) dialog.findViewById(R.id.button4);
                // if decline button is clicked, close the custom dialog
                wishlistButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        Toast.makeText(getActivity(), "Added to WishList.", Toast.LENGTH_LONG).show();
                    }
                });
                cartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {*/
            // Close dialog
            Toast.makeText(this, "Added to Cart.", Toast.LENGTH_LONG).show();
            ArrayList<String> planetList = new ArrayList<String>();
            planetList.add("1");
            OrderItemsAdapter adapter = new OrderItemsAdapter(this, R.layout.order_checkout_list_item, planetList);
            ls.setAdapter(adapter);
                    /*    dialog.dismiss();
                    }
                });*/
        }
        else
        {
            setContentView(R.layout.scanbarcode_checkout_empty_layout);

            scanBtn = (Button) findViewById(R.id.scan_barcode);

            scanBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent(ShoppingCartPageActivity.this, DetailsActivity.class);
                    startActivity(in);
                    finish();
                }
            });

        }

        scanBtn = (Button) findViewById(R.id.scan_barcode);

        checkoutBtn = (Button) findViewById(R.id.check_out);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(ShoppingCartPageActivity.this, DetailsActivity.class);

                startActivity(in);
            }
        });
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(ShoppingCartPageActivity.this, BillingSummaryActivity.class);
                String quantity = pref.getString("quantity", null);
                String offer = pref.getString("offer", null);
                in.putExtra("offer", "Price:" + offer);
                in.putExtra("quantity", quantity);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, HomePageActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        this.finish();
    }
}



