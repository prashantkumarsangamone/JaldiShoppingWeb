package com.jaldishopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaldishopping.adapter.SlidingImage_Adapter;
import com.jaldishopping.vo.ImageModel;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProductDetailsCartActivity extends AppCompatActivity {

    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();
    private static ViewPager mPager;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.almonds1, R.drawable.almonds1
            };
    private FloatingActionButton fab;
    Button incrTxt, decrTxt;
    TextView num;
    ImageView wishList;
    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanned_product_details_layout);

        wishList = (ImageView)findViewById(R.id.imageView9);

        decrTxt  = (Button)findViewById(R.id.increment);
        incrTxt  = (Button)findViewById(R.id.textView28);
        num = (TextView)findViewById(R.id.button7);
        incrTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = num.getText().toString();
                int in = Integer.parseInt(quantity);
                if(in>=1){
                    in++;
                    num.setText(String.valueOf(in));
                }
            }
        });


        decrTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = num.getText().toString();
                int in = Integer.parseInt(quantity);
                if(in>1){
                    in--;
                    num.setText(String.valueOf(in));
                }
            }
        });

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        wishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i==1) {
                    wishList.setImageResource(R.drawable.wishlist_red);

                    i=2;
                    Toast.makeText(getApplicationContext(),"Added to Wishlist.",Toast.LENGTH_LONG).show();

                } else {
                    wishList.setImageResource(R.drawable.wishlist_grey);

                    i=1;
                    Toast.makeText(getApplicationContext(),"Removed from Wishlist.",Toast.LENGTH_LONG).show();

                }
            }
        });
        init();
    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 2; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }
        return list;
    }

    private void init() {

        pref = this.getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
        editor = pref.edit();
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ProductDetailsCartActivity.this, ShoppingCartPageActivity.class);
                String quantity = num.getText().toString();
                editor.putString("quantityCount", quantity);
                editor.putBoolean("check", true);
                editor.putBoolean("f", true);
                editor.commit();

                startActivity(in);
            }
        });
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setAdapter(new SlidingImage_Adapter(ProductDetailsCartActivity.this,imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(3 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
       /* Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
*/
        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, DetailsActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        this.finish();
    }
}