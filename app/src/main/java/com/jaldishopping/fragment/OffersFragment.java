package com.jaldishopping.fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaldishopping.R;
import com.jaldishopping.adapter.OffersAdapter;
import com.jaldishopping.vo.OffersVO;

import java.util.ArrayList;
import java.util.List;


public class OffersFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private OffersAdapter adapter;
    private List<OffersVO> offersVOList;

    public OffersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.offers_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        offersVOList = new ArrayList<>();
        adapter = new OffersAdapter(getActivity(), offersVOList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareOffers();
return view;
    }

    /**
     * Adding few offers for testing
     */
    private void prepareOffers() {

        OffersVO a = new OffersVO("Offers", "a");
        offersVOList.add(a);

        a = new OffersVO("Offers2", "b");
        offersVOList.add(a);

        a = new OffersVO("Offers3", "c");
        offersVOList.add(a);

        a = new OffersVO("Offers4", "d");
        offersVOList.add(a);

        a = new OffersVO("Offers5", "b");
        offersVOList.add(a);

        a = new OffersVO("Offers6", "c");
        offersVOList.add(a);

        a = new OffersVO("Offers7", "d");
        offersVOList.add(a);

        adapter.notifyDataSetChanged();
    }
    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}