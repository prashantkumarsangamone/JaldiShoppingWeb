package com.jaldishopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jaldishopping.vo.OffersVO;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends ArrayAdapter<String> {

    private int layoutResource;

    public ItemsAdapter(Context context, int layoutResource, ArrayList<String> list) {
        super(context, layoutResource, list);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }
        return view;
    }
}