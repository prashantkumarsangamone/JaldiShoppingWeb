package com.jaldishopping;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaldishopping.adapter.RecyclerAdapterViewFirst;
import com.jaldishopping.vo.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainRecycleActivity extends AppCompatActivity {
    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;

    RecyclerView horizontal_recycler_vieww;
    RecyclerAdapterViewFirst horizontalAdapterr;

    private List<Data> data,datasecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontal_recycler_vieww= (RecyclerView) findViewById(R.id.horizontal_recycler_vieww);

        data = fill_with_data();
        datasecond = fill_with_dataSecond();


        horizontalAdapter=new HorizontalAdapter(data, getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainRecycleActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);


        horizontalAdapterr=new RecyclerAdapterViewFirst(datasecond, getApplication());

        LinearLayoutManager horizontalLayoutManagerr = new LinearLayoutManager(MainRecycleActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_vieww.setLayoutManager(horizontalLayoutManagerr);
        horizontal_recycler_vieww.setAdapter(horizontalAdapterr);

    }

    public List<Data> fill_with_data() {

        List<Data> data = new ArrayList<>();



        data.add(new Data( R.drawable.hypercity_logo));
        data.add(new Data( R.drawable.bigbazaar_logo));
        data.add(new Data( R.drawable.hypercity_logo));
        data.add(new Data( R.drawable.bigbazaar_logo));
        data.add(new Data( R.drawable.hypercity_logo));
        data.add(new Data( R.drawable.bigbazaar_logo));
        data.add(new Data( R.drawable.hypercity_logo));
        data.add(new Data( R.drawable.bigbazaar_logo));



        return data;
    }
    public List<Data> fill_with_dataSecond() {

        List<Data> data = new ArrayList<>();



        data.add(new Data( R.drawable.allen));
        data.add(new Data( R.drawable.allen));
        data.add(new Data( R.drawable.allen));
        data.add(new Data( R.drawable.allen));
        data.add(new Data( R.drawable.allen));
        data.add(new Data( R.drawable.allen));
        data.add(new Data( R.drawable.allen));
        data.add(new Data( R.drawable.allen));

        return data;
    }












    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


        List<Data> horizontalList = Collections.emptyList();
        Context context;


        public HorizontalAdapter(List<Data> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }





        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView txtview;

            public MyViewHolder(View view) {
                super(view);
                imageView=(ImageView) view.findViewById(R.id.imageview);
                ///txtview=(TextView) view.findViewById(R.id.txtview);

            }
        }



        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_menu, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.imageView.setImageResource(horizontalList.get(position).imageId);
           // holder.txtview.setText(horizontalList.get(position).txt);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    String list = horizontalList.get(position).txt.toString();
                    Toast.makeText(MainRecycleActivity.this, list, Toast.LENGTH_SHORT).show();
                }

            });

        }


        @Override
        public int getItemCount()
        {
            return horizontalList.size();
        }
    }













}
