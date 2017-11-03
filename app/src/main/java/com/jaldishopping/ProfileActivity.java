package com.jaldishopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    EditText nameEd, lastNameEd, mobileNumberEd, emailEd;
    String name, lastName, mobileNumber, email;
    TextView edit_Photo;
    ImageView profilePic;
    SharedPreferences pref;

    Boolean check;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color
        ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(
                R.layout.edit_profile_toolbar,
                null);
        bar.setDisplayShowHomeEnabled(false);// set your desired color
        bar.setDisplayShowTitleEnabled(false);
        bar.setDisplayShowCustomEnabled(true);
        bar.setCustomView(actionBarLayout);


        pref = getApplicationContext().getSharedPreferences("jaldi_shopping_pref", MODE_PRIVATE);
        editor = pref.edit();
        name = pref.getString("name", null);
        email = pref.getString("email", null);
        lastName = pref.getString("lastName", null);
        mobileNumber = pref.getString("mobileNumber", null);

        profilePic = (ImageView) findViewById(R.id.imageView);

        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
        String previouslyEncodedImage = shre.getString("image_data", "");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            profilePic.setImageBitmap(bitmap);
        }
        nameEd = (EditText) findViewById(R.id.first_name);
        lastNameEd = (EditText) findViewById(R.id.lastname);
        mobileNumberEd = (EditText) findViewById(R.id.mobile_numbered);
        emailEd = (EditText) findViewById(R.id.email);
        edit_Photo = (TextView) findViewById(R.id.edit_photo);

        nameEd.setText(name);
        lastNameEd.setText(lastName);
        mobileNumberEd.setText(mobileNumber);
        emailEd.setText(email);


        TextView back = (TextView) actionBarLayout.findViewById(R.id.back);
        TextView save = (TextView) actionBarLayout.findViewById(R.id.save);

        edit_Photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 0);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                editor.putString("name", name);
                editor.putString("lastName", lastName);
                editor.putString("mobileNumber", mobileNumber);
                editor.commit();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            profilePic.setImageBitmap(bmp);


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();

            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

            SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit=shre.edit();
            edit.putString("image_data",encodedImage);
            edit.commit();

        }

    }
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

}