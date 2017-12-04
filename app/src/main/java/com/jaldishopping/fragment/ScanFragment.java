package com.jaldishopping.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.jaldishopping.ProductDetailsCartActivity;
import com.jaldishopping.R;

import java.io.IOException;

public class ScanFragment extends Fragment {

    View view;

    int i;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private EditText barcodeValue;
    private Button sendOk;
    public Vibrator v;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    boolean f;

    public ScanFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        boolean f =getActivity().getIntent().getBooleanExtra("f",false);

            view = inflater.inflate(R.layout.scan_layout, container, false);

            pref = getActivity().getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
            editor = pref.edit();
        barcodeValue = (EditText)view.findViewById(R.id.editText2);
        sendOk= (Button) view.findViewById(R.id.button2);

        v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        cameraView = (SurfaceView)view.findViewById(R.id.surface_view);

        pref = getActivity().getSharedPreferences("jaldi_shopping_pref", Context.MODE_PRIVATE);
        editor = pref.edit();

        sendOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(barcodeValue.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Can't Scan?.  Enter Barcode",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent in = new Intent(getActivity(), ProductDetailsCartActivity.class);
                    startActivity(in);
                }
            }
        });

        barcodeDetector = new BarcodeDetector.Builder(getActivity())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(getActivity(), barcodeDetector).setRequestedPreviewSize(640,480).setAutoFocusEnabled(true).build();

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

                    barcodeValue.post(new Runnable() {
                        @Override
                        public void run() {
                            v.vibrate(500);
                            ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                            toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                            Barcode thisCode = barcodes.valueAt(0);
                            Log.d("TestTag","barcode value:"+thisCode.displayValue);

                            Intent in = new Intent(getActivity(), ProductDetailsCartActivity.class);
                            startActivity(in);

                            cameraSource.stop();
                            getActivity().finish();

                        }

                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cameraSource.release();
        barcodeDetector.release();
    }


    }

