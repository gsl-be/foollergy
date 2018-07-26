package com.example.dalitsobanda.foollergy;


import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {
    private Activity parentActivity;

    OcrDetectorProcessor(Activity parent) {
        parentActivity = parent;
    }


    // TODO:  Once this implements Detector.Processor<TextBlock>, implement the abstract methods.
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        SparseArray<TextBlock> items = detections.getDetectedItems();
        String msg = "";
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("Processor", "Text detected! " + item.getValue());
                msg += " " + item.getValue();
            }
        }
        TextView txtView = (TextView) parentActivity.findViewById(R.id.scantxt);
        txtView.setText(msg);

    }

    @Override
    public void release() {
    }
}

