package com.jaldishopping;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jaldishopping.adapter.RecyclerAdapterViewFirst;
import com.jaldishopping.vo.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Uri> arrayListapkFilepath;
    SharedPreferences pref ;
    Boolean check;
    String value;
    SharedPreferences.Editor editor;
    static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 125;
    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;

    RecyclerView horizontal_recycler_vieww;
    RecyclerAdapterViewFirst horizontalAdapterr;

    private List<Data> data,datasecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pref = getApplicationContext().getSharedPreferences("jaldi_shopping_pref", MODE_PRIVATE);
        arrayListapkFilepath = new ArrayList<Uri>();
        LayoutInflater mInflater = LayoutInflater.from(HomePageActivity.this);
        View mCustomView = mInflater.inflate(R.layout.toolbar_layout, null);
        toolbar.addView(mCustomView);
        ImageView notificationView = (ImageView)findViewById(R.id.imageView4);
        ImageView shoppingCartView = (ImageView)findViewById(R.id.imageView3);




        notificationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, NotificationActivity.class);
                startActivity(i);

            }
        });
        shoppingCartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, ShoppigListActivity.class);
                startActivity(i);
            }
        });

        Spinner locationSpinner = (Spinner) findViewById(R.id.spinner2);
        List<String> categories = new ArrayList<String>();
        categories.add("Bangalore");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if(checkAndRequestPermissions()) {

            }
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        locationSpinner.setAdapter(dataAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                    Intent i = new Intent(HomePageActivity.this, SignInActivity.class);
                    i.putExtra("check",true);
                    startActivity(i);
                }
            });
        }
        else{

            String user = pref.getString("name", null);
            String email_id = pref.getString("email", null);
            userNameTx.setText(user);
            emailTx.setText(email_id);
            SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
            String previouslyEncodedImage = shre.getString("image_data", "");

            if( !previouslyEncodedImage.equalsIgnoreCase("") ){
                byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                profilePicture.setImageBitmap(bitmap);
            }
        }

        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(HomePageActivity.this, DividerItemDecoration.VERTICAL));

        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontal_recycler_vieww= (RecyclerView) findViewById(R.id.horizontal_recycler_vieww);

        data = fill_with_data();
        datasecond = fill_with_dataSecond();


        horizontalAdapter=new HorizontalAdapter(data, getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomePageActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);


        horizontalAdapterr=new RecyclerAdapterViewFirst(datasecond, getApplication());

        LinearLayoutManager horizontalLayoutManagerr = new LinearLayoutManager(HomePageActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_vieww.setLayoutManager(horizontalLayoutManagerr);
        horizontal_recycler_vieww.setAdapter(horizontalAdapterr);
        horizontal_recycler_view.addOnItemTouchListener(new RecyclerTouchListener(this,
                horizontal_recycler_view, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                int pos = horizontal_recycler_view.indexOfChild(view);
                if(pos==0  ||  pos==2||pos==4  ||  pos==6){
                    value="Big Bazaar";
                }
                else
                {
                    value="Hyper City";
                }
                Intent i = new Intent(HomePageActivity.this, DetailsActivity.class);
                i.putExtra("check",true);
                i.putExtra("value",value);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(HomePageActivity.this, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));
        horizontal_recycler_vieww.addOnItemTouchListener(new RecyclerTouchListener(this,
                horizontal_recycler_vieww, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                int pos = horizontal_recycler_vieww.indexOfChild(view);

                Intent i = new Intent(HomePageActivity.this, ProductDetailsCartActivity.class);
                i.putExtra("check",true);
                i.putExtra("value",value);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(HomePageActivity.this, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));
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
            Intent i = new Intent(HomePageActivity.this, HomeActivity.class);
            i.putExtra("check",true);
            startActivity(i);
        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(HomePageActivity.this, ProfileActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_wishlist) {
            Intent i = new Intent(HomePageActivity.this, WishListActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_shoppinglist) {
            Intent i = new Intent(HomePageActivity.this, ShoppigListActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_purchase) {
            Intent i = new Intent(HomePageActivity.this, HistoryActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_offers) {
            Intent i = new Intent(HomePageActivity.this, OffersActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_logout) {
            Intent i = new Intent(HomePageActivity.this, LoginActivity.class);
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
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }



    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    private  boolean checkAndRequestPermissions() {

        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int storagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<String>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<String, Integer>();

                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED&& perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED&&perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                    } else {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)|| ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)|| ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                            showDialogOK("Sorry, Location,Camera and Storage Permission required for this app. So please ensure the Location ,Camera and Storage call permissions are enabled in settings",
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions();
                                                    break;

                                            }
                                        }
                                    });
                        }

                        else {
								/* Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
			                                            .show();*/
                            showDialogOK("Sorry, Location,Camera and Storage Permissions required for this app. So please ensure the Location,Camera and Storage permissions are enabled in settings",
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    appSettings();
                                                    break;
											/*case DialogInterface.BUTTON_NEGATIVE:
			                                                                // proceed with logic by disabling the related features or quit the app.
			                                                                break;*/
                                            }
                                        }
                                    });


                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
            }
        }

    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("LET'S DO THIS", okListener)

                .create()
                .show();
    }

    public void appSettings()
    {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null)); intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); startActivity(intent);
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



        data.add(new Data( R.drawable.almonds_image));
        data.add(new Data( R.drawable.almonds_image));
        data.add(new Data( R.drawable.almonds_image));
        data.add(new Data( R.drawable.almonds_image));
        data.add(new Data( R.drawable.almonds_image));
        data.add(new Data( R.drawable.almonds_image));
        data.add(new Data( R.drawable.almonds_image));
        data.add(new Data( R.drawable.almonds_image));

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
        public HorizontalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_menu, parent, false);

            return new HorizontalAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final HorizontalAdapter.MyViewHolder holder, final int position) {

            holder.imageView.setImageResource(horizontalList.get(position).imageId);
            // holder.txtview.setText(horizontalList.get(position).txt);



        }


        @Override
        public int getItemCount()
        {
            return horizontalList.size();
        }
    }


}

