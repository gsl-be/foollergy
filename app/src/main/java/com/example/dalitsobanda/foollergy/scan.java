package com.example.dalitsobanda.foollergy;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dalitsobanda.foollery.ui.CameraSourcePreview;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;


public class scan extends AppCompatActivity {

    private static  final String TAG="scan_activity";
    // Intent request code to handle updating play services if needed.
    private static final int RC_HANDLE_GMS = 9001;

    // Permission request codes need to be < 256
    private static final int RC_HANDLE_CAMERA_PERM = 2;


    private CameraSource cameraSource;
    private CameraSourcePreview preview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preview = (CameraSourcePreview) findViewById(R.id.preview);

        // Set good defaults for capturing text.
        boolean autoFocus = true;

        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.
        int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource(autoFocus);
        } else {
            requestCameraPermission();
        }

    }

    private void requestCameraPermission() {
        Log.w(TAG, "Camera permission is not granted. Requesting permission");

        final String[] permissions = new String[]{Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
            return;
        }

        final Activity thisActivity = this;

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(thisActivity, permissions,
                        RC_HANDLE_CAMERA_PERM);
            }
        };


    }

    private void createCameraSource(boolean autoFocus) {
        Context context = getApplicationContext();

        // TODO: Create the TextRecognizer
        TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();
        // TODO: Set the TextRecognizer's Processor.
//        textRecognizer.setProcessor(new OcrDetectorProcessor(graphicOverlay));
        textRecognizer.setProcessor(new OcrDetectorProcessor(this));

        // TODO: Check if the TextRecognizer is operational.
        if (!textRecognizer.isOperational()) {
            Log.w(TAG, "Detector dependencies are not yet available.");
            Toast.makeText(this, "not yet operational", Toast.LENGTH_LONG).show();
        }

        // TODO: Create the mCameraSource using the TextRecognizer.

        cameraSource =
                new CameraSource.Builder(getApplicationContext(), textRecognizer)
                        .setFacing(CameraSource.CAMERA_FACING_BACK)
                        .setRequestedPreviewSize(1280, 1024)
                        .setRequestedFps(15.0f).setAutoFocusEnabled(autoFocus)
                        .build();
    }

    /**
     * Restarts the camera.
     */
    @Override
    protected void onResume() {
        super.onResume();
        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (preview != null) {
            preview.stop();
        }
    }

    private void startCameraSource() throws SecurityException {
        // check that the device has play services available.
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                getApplicationContext());
        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg =
                    GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS);
            dlg.show();
        }

        if (cameraSource != null) {
            try {
                preview.start(cameraSource);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                cameraSource.release();
                cameraSource = null;
            }
        }
    }

//    private boolean onTap(float rawX, float rawY) {
//        // TODO: Speak the text when the user taps on screen.
//        return false;
//    }
//
//    private class CaptureGestureListener extends GestureDetector.SimpleOnGestureListener {
//
//        @Override
//        public boolean onSingleTapConfirmed(MotionEvent e) {
//            return onTap(e.getRawX(), e.getRawY()) || super.onSingleTapConfirmed(e);
//        }
//    }

//    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {
//
//        /**
//         * Responds to scaling events for a gesture in progress.
//         * Reported by pointer motion.
//         *
//         * @param detector The detector reporting the event - use this to
//         *                 retrieve extended info about event state.
//         * @return Whether or not the detector should consider this event
//         * as handled. If an event was not handled, the detector
//         * will continue to accumulate movement until an event is
//         * handled. This can be useful if an application, for example,
//         * only wants to update scaling factors if the change is
//         * greater than 0.01.
//         */
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            return false;
//        }
//
//        /**
//         * Responds to the beginning of a scaling gesture. Reported by
//         * new pointers going down.
//         *
//         * @param detector The detector reporting the event - use this to
//         *                 retrieve extended info about event state.
//         * @return Whether or not the detector should continue recognizing
//         * this gesture. For example, if a gesture is beginning
//         * with a focal point outside of a region where it makes
//         * sense, onScaleBegin() may return false to ignore the
//         * rest of the gesture.
//         */
//        @Override
//        public boolean onScaleBegin(ScaleGestureDetector detector) {
//            return true;
//        }
//
//        /**
//         * Responds to the end of a scale gesture. Reported by existing
//         * pointers going up.
//         * <p/>
//         * Once a scale has ended, {@link ScaleGestureDetector#getFocusX()}
//         * and {@link ScaleGestureDetector#getFocusY()} will return focal point
//         * of the pointers remaining on the screen.
//         *
//         * @param detector The detector reporting the event - use this to
//         *                 retrieve extended info about event state.
//         */
//        @Override
//        public void onScaleEnd(ScaleGestureDetector detector) {
//            if (cameraSource != null) {
////                cameraSource.doZoom(detector.getScaleFactor());
//            }
//        }
//    }
}
