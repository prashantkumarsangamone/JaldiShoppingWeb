package com.jaldishopping.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.jaldishopping.ItemsDetailsActivity;
import com.jaldishopping.R;
import com.jaldishopping.adapter.ItemsLocateAdapter;
import com.jaldishopping.vo.PNameVO;
import com.jaldishopping.vo.PTypeVO;
import com.jaldishopping.vo.PayeeUtility;

import java.util.ArrayList;


public class LocateFragment extends Fragment {

    View view;

    ListView ls;
    ItemsLocateAdapter listAdapter;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;

    String price,quantity,offer,itemid,location;
    ArrayList<PTypeVO> newList;
    boolean f;
    EditText searchBox;
    public LocateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.locate_checkout_layout, container, false);
        boolean f =getActivity().getIntent().getBooleanExtra("f",false);

     newList = new ArrayList<PTypeVO>();
        newList = PayeeUtility.getPayeeDetails(getActivity());
        final ArrayList<PTypeVO> list = new ArrayList<PTypeVO>();

        for (PTypeVO ptvo : newList)
        {
                list.add(ptvo);
        }


        ls= (ListView)view.findViewById(R.id.list);
        pref = getActivity().getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
        editor = pref.edit();

        listAdapter = new ItemsLocateAdapter(getActivity(), R.layout.order_checkout_list_item, list);



        searchBox = (EditText) view.findViewById(R.id.editText3);

        // Add Text Change Listener to EditText
        searchBox.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {

                ls.setAdapter(listAdapter );
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                listAdapter.getFilter().filter(s);

                listAdapter.notifyDataSetChanged();
            }
        });

        searchBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                searchBox.setText("");
                listAdapter.clear();
                listAdapter.notifyDataSetChanged();
            }
        });

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String name = list.get(position).getItemName();
                for (PTypeVO ptvo : newList)
                {
                    if(ptvo.getItemName().equals(name)){
                       ArrayList<PNameVO> vo = new ArrayList<PNameVO>();
                        vo= ptvo.getPayeeDetails();
                        for (PNameVO pv : vo)
                        {
                            itemid= pv.getItemType();
                           offer= pv.getOffer();
                            price=pv.getPrice();
                            quantity=pv.getQuantity();
                            location=pv.getLocation();
                        }

                    }
                }


                Log.d("TestTag",""+itemid+offer+price+quantity+location);
                Intent detailsIntent = new Intent(getActivity(),ItemsDetailsActivity.class);
                detailsIntent.putExtra("offer",offer);
                detailsIntent.putExtra("price",price);
                detailsIntent.putExtra("itemname",name);
                detailsIntent.putExtra("location",location);
                detailsIntent.putExtra("quantity",quantity);
                detailsIntent.putExtra("brandName",itemid);
                startActivity(detailsIntent);

            }
        });

        return view;
    }

}


