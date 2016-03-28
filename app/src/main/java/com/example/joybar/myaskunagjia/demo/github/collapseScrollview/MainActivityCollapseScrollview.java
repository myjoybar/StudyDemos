package com.example.joybar.myaskunagjia.demo.github.collapseScrollview;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivityCollapseScrollview extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
     private Button btn1,btn2,btn3,btn4,btn5;
     private TextView tv1, tv2;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    List<String> list2 = new ArrayList<>();
    public static int width;
    public int height;

    private MyScrollView3 scrollView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_collapse_scrollview);
        initView();
        initData();
        setLinstener();
        fillData();




        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");

        scrollView3 = (MyScrollView3) findViewById(R.id.scroll3);
        scrollView3.setData(list2);


        new Handler().post(new Runnable() {

            @Override
            public void run() {
              //  scrollView3.setSelection(0);
                scrollView3.setmCurrentPosition(2);

            }
        });

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        width = metric.widthPixels;     // 屏幕宽度（像素）
        height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
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
//        btn1 = fvById(R.id.btn1);
//        btn2 = fvById(R.id.btn2);
//        btn3 = fvById(R.id.btn3);
//        btn4 = fvById(R.id.btn4);
//        tv1 = fvById(R.id.tv1);
//        tv2 = fvById(R.id.tv2);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {
//         btn1.setOnClickListener(this);
//         btn2.setOnClickListener(this);
//         btn3.setOnClickListener(this);
//         btn4.setOnClickListener(this);

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
             case R.id.btn1:

              break;
            case R.id.btn2:

                break;
            case R.id.btn3:

                break;
            case R.id.btn4:

                break;

            default:
                break;
        }

    }




}

