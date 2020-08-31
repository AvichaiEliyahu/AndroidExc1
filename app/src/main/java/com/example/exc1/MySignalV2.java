package com.example.exc1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

public class MySignalV2 {
    private static MySignalV2 instance;
    private static Context appContext;

    public static MySignalV2 getInstance(Context context) {
        if (instance == null)
            instance = new MySignalV2(context);
        return instance;
    }

    private MySignalV2(Context context) {
        appContext = context;
    }


    public void showToast(final String message) {
        // If we put it into handler - we can call in from asynctask outside of main uithread
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ProgressDialog mProgressDialog;
    //For start progrss dialog
    public void nbStartDialog(Context activityContext, String message, boolean isCancelable) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();

        mProgressDialog = new ProgressDialog(activityContext);
        mProgressDialog.setCancelable(isCancelable);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();

//        TextView dialog_title = (TextView) dialog.findViewById(R.id.text_dialog_title);
//        dialog_title.setText(mVal);
    }

    //For close dialog
    public void nbCloseDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
