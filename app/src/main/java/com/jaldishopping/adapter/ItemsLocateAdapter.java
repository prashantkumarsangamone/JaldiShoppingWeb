package com.jaldishopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jaldishopping.R;
import com.jaldishopping.vo.ItemVO;
import com.jaldishopping.vo.PTypeVO;

import java.util.ArrayList;
import java.util.Locale;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class ItemsLocateAdapter extends ArrayAdapter<PTypeVO>{

    private Context context;
    private Filter filter;
    private ArrayList<PTypeVO> list;
    private ArrayList<PTypeVO> searchList;

    public ItemsLocateAdapter(Context context, int textViewResourceId, ArrayList<PTypeVO> blist) {
        super(context, textViewResourceId, blist);
        this.context = context;
        this.list = new ArrayList<PTypeVO>();
        this.list.addAll(blist);
        this.searchList = new ArrayList<PTypeVO>();
        this.searchList.addAll(blist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.order_checkout_list_item, null);
        }

        TextView bankName = (TextView) convertView.findViewById(R.id.textView6);

        Log.d("TestTag", "Bank Name : "+bankName);

        bankName.setText(list.get(position).getItemName());

        return convertView;
    }

    @Override
    public Filter getFilter()
    {
        if (filter == null)
            filter = new PkmnNameFilter();

        return filter;
    }

    private class PkmnNameFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();  // Holds the results of a filtering operation in values
            String prefix = constraint.toString().toLowerCase(Locale.getDefault());

            if (prefix == null || prefix.length() == 0)
            {
                ArrayList<PTypeVO> slist = new ArrayList<PTypeVO>(searchList);
                results.values = slist;
                results.count = slist.size();
            }
            else
            {
                final ArrayList<PTypeVO> slist = new ArrayList<PTypeVO>(searchList);
                final ArrayList<PTypeVO> nlist = new ArrayList<PTypeVO>();
                int count = slist.size();

                for (int i=0; i<count; i++)
                {
                    final PTypeVO pkmn = slist.get(i);
                    final String value = pkmn.getItemName().toLowerCase(Locale.getDefault());

                    if (value.startsWith(prefix))
                    {
                        nlist.add(pkmn);
                    }
                }
                // set the Filtered result to return
                results.values = nlist;
                results.count = nlist.size();

            }
            return results;
        }



        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list = (ArrayList<PTypeVO>)results.values; // has the filtered values

            clear();
            int count = list.size();
            for (int i=0; i<count; i++)
            {
                PTypeVO pkmn = (PTypeVO)list.get(i);
                add(pkmn);

                notifyDataSetChanged(); // notifies the data with new filtered values
            }
        }
    }
}