

        package com.jaldishopping.adapter;

        import android.content.Context;
        import android.content.SharedPreferences;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
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
        import android.widget.ImageView;
        import android.widget.Spinner;
        import android.widget.TextView;

public class OrderItemsAdapter extends ArrayAdapter<String>{

    private Context context;
    private Filter filter;
    private ArrayList<String> list;
    private ArrayList<String> searchList;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;

    public OrderItemsAdapter(Context context, int textViewResourceId, ArrayList<String> blist) {
        super(context, textViewResourceId, blist);
        this.context = context;
        this.list = new ArrayList<String>();
        this.list.addAll(blist);
        this.searchList = new ArrayList<String>();
        this.searchList.addAll(blist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.order_checkout_list_items, null);
        }

        TextView bankName = (TextView) convertView.findViewById(R.id.textView6);
        final TextView price = (TextView) convertView.findViewById(R.id.textView23);
        final TextView s =(TextView) convertView.findViewById(R.id.spinner);
        ImageView removeItem = (ImageView) convertView.findViewById(R.id.imageView8);
        /*String[] arraySpinner;

        arraySpinner = new String[] {
                "1", "2", "3", "4", "5"
        };*/
        pref = context.getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
        editor = pref.edit();

        s.setText("Qty:"+pref.getString("quantityCount", null));
        editor.putString("offer",price.getText().toString().trim());
        editor.commit();

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinnerlayout, arraySpinner);

        s.setAdapter(adapter);

        s.setSelection(0);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String quantity = parent.getItemAtPosition(position).toString();
                editor.putString("quantity",quantity);
                editor.putString("offer",price.getText().toString().trim());
                editor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });*/

        return convertView;
    }

  /*  @Override
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
	                   *//*else
	                    {
	                	   final Toast toast= Toast.makeText(context, "No Items Found", Toast.LENGTH_SHORT);
		                	toast.show();
		                	 Handler handler = new Handler();
		                     handler.postDelayed(new Runnable() {
		                        @Override
		                        public void run() {
		                            toast.cancel();
		                        }
		                 }, 1000);
	                    }*//*
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
*/
    // }



}