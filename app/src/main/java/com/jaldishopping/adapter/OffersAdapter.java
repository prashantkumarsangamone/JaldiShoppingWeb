package com.jaldishopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaldishopping.R;
import com.jaldishopping.vo.OffersVO;
import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private Context mContext;
    private List<OffersVO> offersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
        }
    }


    public OffersAdapter(Context mContext, List<OffersVO> offersList) {
        this.mContext = mContext;
        this.offersList = offersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offers_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        OffersVO offers = offersList.get(position);
        holder.title.setText(offers.getName());
        holder.count.setText(offers.getTexts());

    }



    @Override
    public int getItemCount() {
        return offersList.size();
    }
}