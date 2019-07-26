package com.example.floatview;

import android.os.Build;
import android.view.View;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Service1 extends Service {
    LinearLayout mFloatLayout;
    LayoutParams wmParams;
    WindowManager mWindowManager;

    ImageButton mFloatView;

    @Override
    public void onCreate() {
        super.onCreate();
        createFloatView();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    private void createFloatView() {
        wmParams = new LayoutParams();

        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        wmParams.format = PixelFormat.RGBA_8888;

        wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;

        wmParams.gravity = Gravity.LEFT | Gravity.TOP;

        wmParams.x = 0;
        wmParams.y = 0;

        wmParams.width = LayoutParams.WRAP_CONTENT;
        wmParams.height = LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout, null);

        mWindowManager.addView(mFloatLayout, wmParams);

        mFloatView = (ImageButton) mFloatLayout.findViewById(R.id.float_id);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));


        mFloatView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                wmParams.x = (int) event.getRawX() - mFloatView.getMeasuredWidth() / 2;

                wmParams.y = (int) event.getRawY() - mFloatView.getMeasuredHeight() / 2 - 25;

                mWindowManager.updateViewLayout(mFloatLayout, wmParams);
                return false;
            }
        });

        mFloatView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), Service1.class);
                stopService(intent);
                Toast.makeText(getApplicationContext(),"截屏服务已经关闭，停止截图！",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (mFloatLayout != null) {
            mWindowManager.removeView(mFloatLayout);
        }
    }

}