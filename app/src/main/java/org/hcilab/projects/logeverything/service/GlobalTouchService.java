package org.hcilab.projects.logeverything.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import org.hcilab.projects.logeverything.R;

public class GlobalTouchService extends Service implements OnTouchListener{

    private String TAG = this.getClass().getSimpleName();
    // window manager
    private WindowManager mWindowManager;
    // linear layout will use to detect touch event
    private LinearLayout touchLayout;

    private WindowManager.LayoutParams mParams;

    @Override public IBinder onBind(Intent arg0) {  return null; }

    @Override public void onCreate() {
        super.onCreate();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // set the layout parameters of the window
            mParams = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                // width of layout 30 px
                WindowManager.LayoutParams.MATCH_PARENT,
                // height is equal to full screen
                    // Shrink the window to wrap the content rather
                    // than filling the screen
              //      WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    // Display it on top of other application windows
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    // Don't let it grab the input focus
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
               //     WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    // Make the underlying application window visible
                    // through any transparent parts
                    PixelFormat.TRANSLUCENT);

        }
        // getting a LayoutInflater
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        // inflating the view with the custom layout we created
//        mView = layoutInflater.inflate(R.layout.popup_window, null);
//        // set onClickListener on the remove button, which removes
//        // the view from the window
//        mView.findViewById(R.id.window_close).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                close();
//            }
//        });
//        // Define the position of the
//        // window within the screen
//        mParams.gravity = Gravity.CENTER;
        mWindowManager = (WindowManager)this.getSystemService(WINDOW_SERVICE);




        // create linear layout
        touchLayout = new LinearLayout(this);
// set layout width 30 px and height is equal to full screen
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        touchLayout.setLayoutParams(lp);
        // set color if you want layout visible on screen//
      //  touchLayout.setBackgroundColor(Color.CYAN);
        // set on touch listener
        touchLayout.setOnTouchListener(this);
// fetch window manager object
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // set layout parameter of window manager
//        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
//                30,
//                // width of layout 30 px
//                WindowManager.LayoutParams.MATCH_PARENT,
//                // height is equal to full screen
//               // WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
//                // Type Ohone, These are non-application windows providing user interaction with the phone (in particular incoming calls).
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                // this window won't ever get key input focus
//                PixelFormat.TRANSLUCENT
//        );
        mParams.gravity = Gravity.LEFT | Gravity.TOP;
        Log.i(TAG, "add View");
        mWindowManager.addView(touchLayout, mParams);

    }

    @Override public void onDestroy() {
        if(mWindowManager != null) {
            if(touchLayout != null) mWindowManager.removeView(touchLayout);
        }
        super.onDestroy();
    }

    @Override public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP) {
            Log.i(TAG, "Action :" + event.getAction() + "\t X :" + event.getRawX() + "\t Y :"+ event.getRawY());
        }
        return false;
    }

}