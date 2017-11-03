package com.jaldishopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jaldishopping.adapter.ViewPagerAdapter;
import com.jaldishopping.fragment.LocateFragment;
import com.jaldishopping.fragment.MyListFragment;
import com.jaldishopping.fragment.NotificationFragment;
import com.jaldishopping.fragment.OffersFragment;
import com.jaldishopping.fragment.ScanFragment;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class DetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    BottomNavigationView bottomNavigationView;

    //This is our viewPager
    private ViewPager viewPager;
    SharedPreferences pref ;
    Boolean check;
    SharedPreferences.Editor editor;

    //Fragments
    LocateFragment locateFragment;
    MyListFragment myListFragment;
    OffersFragment offersFragment;
    ScanFragment scanFragment;
    NotificationFragment notificationFragment;

    MenuItem prevMenuItem;
    ArrayList<Uri> arrayListapkFilepath; // define global
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deatils_views_layout);
        pref = getApplicationContext().getSharedPreferences("jaldi_shopping_pref", MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        arrayListapkFilepath = new ArrayList<Uri>();

        LayoutInflater mInflater = LayoutInflater.from(DetailsActivity.this);
        View mCustomView = mInflater.inflate(R.layout.toolbar_details, null);
        toolbar.addView(mCustomView);
        ImageView shoppingCartView = (ImageView)findViewById(R.id.imageView3);
        shoppingCartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, ShoppigListActivity.class);
                startActivity(i);

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        String value = "Hyper City";
        Spinner locationSpinner = (Spinner) findViewById(R.id.spinner2);
        List<String> categories = new ArrayList<String>();
        categories.add(value);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        locationSpinner.setAdapter(dataAdapter);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(DetailsActivity.this);

        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(DetailsActivity.this, android.support.v7.widget.DividerItemDecoration.VERTICAL));

        LinearLayout navHeader=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        navigationView.addHeaderView(navHeader);

        ImageView profilePicture = (ImageView)navHeader.findViewById(R.id.picture);
        TextView userNameTx = (TextView)navHeader.findViewById(R.id.username);
        TextView emailTx = (TextView)navHeader.findViewById(R.id.email_id);

        check =pref.getBoolean("check",false);



        if(check.equals(false)){
            userNameTx.setText("SignIn");
            profilePicture.setVisibility(View.INVISIBLE);
            emailTx.setVisibility(View.INVISIBLE);
            userNameTx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(DetailsActivity.this, SignInActivity.class);
                    startActivity(i);
                }
            });
        }
        else{

            String user = pref.getString("name", null);
            String email_id = pref.getString("email", null);
            userNameTx.setText(user);
            emailTx.setText(email_id);
        }


        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

       disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_scan:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_locate:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_offers:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.action_notification:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.action_mylist:
                                viewPager.setCurrentItem(4);
                                break;

                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        setupViewPager(viewPager);
    }
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);

                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("TestTag", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("TestTag", "Unable to change value of shift mode", e);
        }
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        locateFragment=new LocateFragment();
        scanFragment=new ScanFragment();
        offersFragment=new OffersFragment();
        notificationFragment=new NotificationFragment();
        myListFragment=new MyListFragment();

        adapter.addFragment(scanFragment);
        adapter.addFragment(locateFragment);
        adapter.addFragment(offersFragment);
        adapter.addFragment(notificationFragment);
        adapter.addFragment(myListFragment);

        viewPager.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(DetailsActivity.this, HomeActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(DetailsActivity.this, ProfileActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_wishlist) {
            Intent i = new Intent(DetailsActivity.this, WishListActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_shoppinglist) {
            Intent i = new Intent(DetailsActivity.this, ShoppigListActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_purchase) {
            Intent i = new Intent(DetailsActivity.this, HistoryActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_offers) {
            Intent i = new Intent(DetailsActivity.this, OffersActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_logout) {
            Intent i = new Intent(DetailsActivity.this, LoginActivity.class);
            editor.remove("check");
            editor.commit();
            startActivity(i);
            finish();

        }
        else if (id == R.id.nav_invite) {
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String filePath = app.sourceDir;

            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setType("*/*");

            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
            startActivity(Intent.createChooser(intent, "Share app via"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
