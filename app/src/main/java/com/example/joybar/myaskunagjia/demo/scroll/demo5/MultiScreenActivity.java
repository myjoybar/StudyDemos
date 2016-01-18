package com.example.joybar.myaskunagjia.demo.scroll.demo5;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.joybar.myaskunagjia.R;

/**
 * Created by joybar on 15/11/23.
 */
public class MultiScreenActivity extends Activity implements View.OnClickListener {

    //http://blog.csdn.net/bigconvience/article/details/26735705
    private Button mScrollLeftButton;
    private Button mScrollRightButton;
    private Button mStopScrollButton;
    private Button mLogDataButton;
    private MultiViewGroup mMulTiViewGroup;

    public static int mScreenWidth;
    public static int mScrrenHeight;

    private static final String TAG = MultiScreenActivity.class.getName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels ;
        mScrrenHeight = metric.heightPixels;

        setContentView(R.layout.activity_multiview);

        mMulTiViewGroup = (MultiViewGroup)findViewById(R.id.mymultiViewGroup);

        mScrollLeftButton = (Button) findViewById(R.id.scroll_left);
        mStopScrollButton = (Button) findViewById(R.id.stop_scroll);
        mLogDataButton = (Button) findViewById(R.id.log_data);
        mScrollRightButton = (Button) findViewById(R.id.scroll_right);

        mScrollLeftButton.setOnClickListener(this);
        mStopScrollButton.setOnClickListener(this);
        mLogDataButton.setOnClickListener(this);
        mScrollRightButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.scroll_left:
               mMulTiViewGroup.moveToLeftSide() ;
                break;
            case R.id.scroll_right:
            mMulTiViewGroup.moveToRightSide(); ;
                break;
            case R.id.stop_scroll:
            //    mMulTiViewGroup.stopMove() ;
                break;
            case R.id.log_data:
                logData();
                break;
        }
    }

    private void logData() {
        //  mMulTiViewGroup.scrollTo(200, 0);
        //  mMulTiViewGroup.offsetLeftAndRight(200);
    //    Log.d(TAG, "scroll in logData:" + mMulTiViewGroup.getScrollX());
    }

}
