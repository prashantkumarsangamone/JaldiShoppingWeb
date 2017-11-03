package com.jaldishopping;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Payment_OptionActivity extends AppCompatActivity {

    Button payBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_options_layout);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color


        payBtn = (Button)findViewById(R.id.pay);
        final RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        RadioButton radioButton = (RadioButton) findViewById(R.id.paytm);
        radioButton.setChecked(true);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                Log.d("TestTag","id"+selectedId);
                // find the radiobutton by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);

                    Toast.makeText(Payment_OptionActivity.this,
                            radioButton.getText(), Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Payment_OptionActivity.this, PaymentStatusActivity.class);

                    startActivity(in);
            }
        });
    }
}
