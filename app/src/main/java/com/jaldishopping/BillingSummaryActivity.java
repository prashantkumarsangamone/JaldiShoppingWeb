package com.jaldishopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BillingSummaryActivity extends AppCompatActivity {

    Button proceedtopayBtn;
    TextView items,price;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billingsummary_layout);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color

        pref = this.getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
        editor = pref.edit();
        items  =(TextView) findViewById(R.id.textView14);
        price  =(TextView) findViewById(R.id.textView12);


        final String quantity= pref.getString("quantityCount", null);
        final String priceS= getIntent().getStringExtra("offer");

        final String s[] =priceS.split("\\$");

        price.setText("Total Price:$"+Integer.parseInt(s[1])*Integer.parseInt(quantity));

        proceedtopayBtn  = (Button)findViewById(R.id.proceed);
        proceedtopayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BillingSummaryActivity.this, Payment_OptionActivity.class);
                editor.putString("price",String.valueOf(Integer.parseInt(s[1])*Integer.parseInt(quantity)));
                editor.commit();
                startActivity(in);
            }
        });
    }

}
