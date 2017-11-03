package com.jaldishopping;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;

public class BarCodeScanActivity extends AppCompatActivity {
   SharedPreferences pref ;
    SharedPreferences.Editor editor;
    int i;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private EditText barcodeValue;
    private Button sendOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scan_layout);
        barcodeValue = (EditText) findViewById(R.id.editText2);
        sendOk= (Button) findViewById(R.id.button2);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.body))); // set your desired color

        cameraView = (SurfaceView) findViewById(R.id.surface_view);



        pref = this.getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
        editor = pref.edit();

        sendOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(barcodeValue.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Can't Scan?.  Enter Barcode",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent in = new Intent(BarCodeScanActivity.this, ProductDetailsCartActivity.class);
                    startActivity(in);
                    /*Intent in = new Intent(BarCodeScanActivity.this, DetailsActivity.class);
                    in.putExtra("barcodeValue",barcodeValue.getText().toString());
                    editor.putBoolean("check", true);
                    editor.commit();
                    in.putExtra("f",true);
                    startActivity(in);*/
                }
            }
        });

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector).setRequestedPreviewSize(640,480).setAutoFocusEnabled(true).build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    //noinspection MissingPermission
                    cameraSource.start(cameraView.getHolder());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                   /* barcodeValue.post(new Runnable() {
                        @Override
                        public void run() {*/
                            //Update barcode value to TextView
                           /* Log.d("TestTag","size"+barcodes.size());
                            for(int i=0;i<barcodes.size();i++){*/
                                Barcode thisCode = barcodes.valueAt(0);
                                Log.d("TestTag","barcode value:"+thisCode.displayValue);
                                Intent in = new Intent(BarCodeScanActivity.this, ProductDetailsCartActivity.class);
                                startActivity(in);
                                finish();
                               //barcodeValue.setText(thisCode.displayValue +"\n");

                            //}

                    /*    }
                   });*/
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraSource.release();
        barcodeDetector.release();
    }
}
