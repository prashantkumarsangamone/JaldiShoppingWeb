package com.jaldishopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.jaldishopping.vo.Data;
import com.jaldishopping.R;

import java.util.Collections;
import java.util.List;

public class RecyclerAdapterViewFirst extends RecyclerView.Adapter<RecyclerAdapterViewFirst.MyViewHolder> {


    List<Data> horizontalList = Collections.emptyList();
    Context context;


    public RecyclerAdapterViewFirst(List<Data> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewItem;
        public ImageView imgViewIcon;
        public TextView valuePrice;
        public TextView textArea;

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewItem = (TextView) itemLayoutView.findViewById(R.id.textView6);
            valuePrice = (TextView) itemLayoutView.findViewById(R.id.textView23);
            textArea = (TextView) itemLayoutView.findViewById(R.id.textView7);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.imageView6);
        }
    }



    @Override
    public RecyclerAdapterViewFirst.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.showig_products_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterViewFirst.MyViewHolder holder, final int position) {

        holder.imgViewIcon.setImageResource(horizontalList.get(position).imageId);
        // holder.txtview.setText(horizontalList.get(position).txt);

    }


    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}







