package com.jaldishopping.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jaldishopping.BarCodeScanActivity;
import com.jaldishopping.BillingSummaryActivity;
import com.jaldishopping.R;
import com.jaldishopping.adapter.OrderItemsAdapter;

import java.util.ArrayList;

public class ScanFragment extends Fragment {

    View view;
    Button scanBtn, checkoutBtn;
    ListView ls;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    boolean f;
    String barcodeValue;
    public ScanFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        boolean f =getActivity().getIntent().getBooleanExtra("f",false);

            view = inflater.inflate(R.layout.scanbarcode_checkout_layout, container, false);


            ls = (ListView) view.findViewById(R.id.list);
            pref = getActivity().getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
            editor = pref.edit();

            barcodeValue = getActivity().getIntent().getStringExtra("barcodeValue");

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
                        Toast.makeText(getActivity(), "Added to Cart.", Toast.LENGTH_LONG).show();
                        ArrayList<String> planetList = new ArrayList<String>();
                        planetList.add("1");
                        OrderItemsAdapter adapter = new OrderItemsAdapter(getActivity(), R.layout.order_checkout_list_item, planetList);
                        ls.setAdapter(adapter);
                    /*    dialog.dismiss();
                    }
                });*/
            }
           else
            {

                LayoutInflater lf1 = getActivity().getLayoutInflater();
                View rootview1 = lf1.inflate(R.layout.scanbarcode_checkout_empty_layout,container, false);
                scanBtn = (Button) rootview1.findViewById(R.id.scan_barcode);

                scanBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent in = new Intent(getActivity(), BarCodeScanActivity.class);

                        startActivity(in);
                        getActivity().finish();
                    }
                });
                return rootview1;
            }

            scanBtn = (Button) view.findViewById(R.id.scan_barcode);

            checkoutBtn = (Button) view.findViewById(R.id.check_out);

            scanBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent(getActivity(), BarCodeScanActivity.class);

                    startActivity(in);
                }
            });
            checkoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent(getActivity(), BillingSummaryActivity.class);
                    String quantity = pref.getString("quantity", null);
                    String offer = pref.getString("offer", null);
                    in.putExtra("offer", "Price:" + offer);
                    in.putExtra("quantity", quantity);
                    startActivity(in);

                }
            });

            return view;

    }

    }


