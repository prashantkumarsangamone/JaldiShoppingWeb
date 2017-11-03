

        package com.jaldishopping.adapter;

        import java.util.ArrayList;

                import android.app.Activity;
                import android.view.LayoutInflater;
                import android.view.View;
                import android.view.View.OnClickListener;
                import android.view.ViewGroup;
                import android.widget.BaseExpandableListAdapter;
                import android.widget.CheckedTextView;
                import android.widget.TextView;
                import android.widget.Toast;

        import com.jaldishopping.R;

        public class ExpandableAdapter extends BaseExpandableListAdapter {

            private Activity activity;
            private ArrayList<Object> childtems;
            private LayoutInflater inflater;
            private ArrayList<String> parentItems, child;

            public ExpandableAdapter(ArrayList<String> parents, ArrayList<Object> childern) {
                this.parentItems = parents;
                this.childtems = childern;
            }

            public void setInflater(LayoutInflater inflater, Activity activity) {
                this.inflater = inflater;
                this.activity = activity;
            }

            @Override
            public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

                child = (ArrayList<String>) childtems.get(groupPosition);

                TextView textView = null;
                View view = convertView;

                if (view == null) {

                    if (groupPosition == 0) {
                        convertView = inflater.inflate(R.layout.activity_first_screen, null);

                    }
                    if (groupPosition == 1) {
                        convertView = inflater.inflate(R.layout.order_checkout_list_item, null);
                    }
                    if (groupPosition == 2) {
                        convertView = inflater.inflate(R.layout.fragment_barcode, null);
                    }
                }
                /*if (convertView == null) {
                    convertView = inflater.inflate(R.layout.group, null);
                }

                textView = (TextView) convertView.findViewById(R.id.textView1);
                textView.setText(child.get(childPosition));

                convertView.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Toast.makeText(activity, child.get(childPosition),
                                Toast.LENGTH_SHORT).show();
                    }
                });
*/
                return convertView;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.row, null);
                }

                ((CheckedTextView) convertView).setText(parentItems.get(groupPosition));
                ((CheckedTextView) convertView).setChecked(isExpanded);

                return convertView;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return null;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return 0;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return ((ArrayList<String>) childtems.get(groupPosition)).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return null;
            }

            @Override
            public int getGroupCount() {
                return parentItems.size();
            }

            @Override
            public void onGroupCollapsed(int groupPosition) {
                super.onGroupCollapsed(groupPosition);
            }

            @Override
            public void onGroupExpanded(int groupPosition) {
                super.onGroupExpanded(groupPosition);
            }

            @Override
            public long getGroupId(int groupPosition) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }

        }