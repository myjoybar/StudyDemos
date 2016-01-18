package com.example.joybar.myaskunagjia.demo.drawable;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.application.MyApplication;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.utils.DrawbleUtils;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
     private Button btn1,btn2,btn3,btn4,btn5;
      private TextView tv1, tv2;
    ImageView imv0,imv1;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

int Isd;
    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dawable);
        initView();
        initData();
        setLinstener();
        fillData();
    }

    @Override
    protected void onStart() {
        L.i(TAG, "onStart");
        super.onStart();
    }
    @Override
    protected void onRestart() {
        L.i(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        L.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        L.i(TAG, "onPause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        L.i(TAG, "onStop");
        super.onStop();

    }
    @Override
    protected void onDestroy() {
        L.i(TAG, "onDestroy");
        super.onDestroy();
    }

    //初始化部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    @Override
    protected void initView() {
        btn1 = fvById(R.id.btn1);
        btn2 = fvById(R.id.btn2);
        btn3 = fvById(R.id.btn3);
        btn4 = fvById(R.id.btn4);
        tv1 = fvById(R.id.tv1);
        tv2 = fvById(R.id.tv2);
        imv0= fvById(R.id.imv0);
        imv1= fvById(R.id.imv1);

    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    @Override
    protected void initData() {
        btn1.setBackgroundDrawable(DrawbleUtils.setGradientDrawable("#C71585", 5, 5, 5, 5));
        btn2.setBackground(DrawbleUtils.setStateDrawable("#A52A2A", "#8B0000", "#8B0000", 5));
        btn3.setBackground(DrawbleUtils.setStateDrawable(MyApplication.getContext(),R.drawable.android_log_green, R.drawable.android_log_blue, R.drawable.android_log_blue));

        btn4.setBackground(DrawbleUtils.setStateDrawableRoundedCorner(R.drawable.android_log_green, R.drawable.android_log_blue, R.drawable.android_log_blue,30));



        tv1.setOnClickListener(this);
        tv1.setBackground(DrawbleUtils.setStateDrawable("#A52A2A", "#8B0000", "#8B0000", 5));


        Drawable drawable = getResources().getDrawable(R.drawable.android_log_green);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        BitmapDrawable bbb = new BitmapDrawable(DrawbleUtils.getRoundedCornerBitmap(bitmap, 10));
        imv0.setBackground(bbb);

        imv1.setBackground(DrawbleUtils.getRoundedCornerBitmapDrawable(R.drawable.android_log_green, 10));

    }

    @Override
    protected void setLinstener() {
         btn1.setOnClickListener(this);
         btn2.setOnClickListener(this);
         btn3.setOnClickListener(this);
         btn4.setOnClickListener(this);

    }

    @Override
    protected void fillData() {
        // TODO Auto-generated method stub

    }

    //网络请求部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //自定义方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //回调方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // case R.id.btn_login:

            // break;

            default:
                break;
        }

    }




}

