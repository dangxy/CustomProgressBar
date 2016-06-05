package com.dxy.customprogressbar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dxy.customprogressbar.view.CircleProgressBar;
import com.dxy.customprogressbar.view.HorizontalProgressBar;

public class MainActivity extends AppCompatActivity {

    private  HorizontalProgressBar horizontalProgressBar;
    private  HorizontalProgressBar horizontalProgressBar1;
    private  HorizontalProgressBar horizontalProgressBar2;

    private CircleProgressBar circleProgressBar;
    private CircleProgressBar circleProgressBar1;

    public static  final  int MESSAGE_UPDATE=0X11;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int progress = horizontalProgressBar.getProgress();

            horizontalProgressBar.setProgress(++progress);
            horizontalProgressBar1.setProgress(++progress);
            horizontalProgressBar2.setProgress(++progress);
            circleProgressBar.setProgress(++progress);
            circleProgressBar1.setProgress(++progress);
            if(progress>=100){
                handler.removeMessages(MESSAGE_UPDATE);
            }
            handler.sendEmptyMessageDelayed(MESSAGE_UPDATE,200);



        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void  initView(){


        horizontalProgressBar=  (HorizontalProgressBar)findViewById(R.id.hpb_progress_bar);
        horizontalProgressBar1=  (HorizontalProgressBar)findViewById(R.id.hpb_progress_bar1);
        horizontalProgressBar2=  (HorizontalProgressBar)findViewById(R.id.hpb_progress_bar2);

        circleProgressBar =(CircleProgressBar)findViewById(R.id.cpb_progress_bar);
        circleProgressBar1 =(CircleProgressBar)findViewById(R.id.cpb_progress_bar1);

        handler.sendEmptyMessage(MESSAGE_UPDATE);
    }
}
