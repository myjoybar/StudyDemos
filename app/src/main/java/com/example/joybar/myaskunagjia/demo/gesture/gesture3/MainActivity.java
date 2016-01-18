package com.example.joybar.myaskunagjia.demo.gesture.gesture3;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.utils.ScreenUtils;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // private Button btn_login;
    // private TextView tv_register, tv_reget_pwd;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    MoveImageView2 moveImageView2,moveImageView3;
    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gesture3);
        initView();
        initData();
        setLinstener();
        fillData();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
             getStatusheight();

            }
        }, 500);
    }


    public  void getStatusheight(){



        L.i("MoveImageView2", "statusBarHeight=" + ScreenUtils.getStatusHeight(MainActivity.this) + "");
        L.i("MoveImageView2", "setScreenW=" + ScreenUtils.getScreenWidth(MainActivity.this) + "");
        L.i("MoveImageView2", "setStatusH=" + ScreenUtils.getScreenHeight(MainActivity.this) + "");
        L.i("MoveImageView2", "getNavigationBarHeight=" + ScreenUtils.getNavigationBarHeight(MainActivity.this) + "");

        moveImageView2.setStatusH(ScreenUtils.getStatusHeight(MainActivity.this));

        moveImageView2.setScreenW(ScreenUtils.getScreenWidth(MainActivity.this));
        moveImageView2.setScreenH(ScreenUtils.getScreenHeight(MainActivity.this));
        moveImageView2.setavigation(ScreenUtils.getNavigationBarHeight(MainActivity.this));

        //        screenW = ScreenUtils.getScreenWidth(context);
//        // 获取屏幕高度
//        screenH = ScreenUtils.getScreenHeight(context);
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
        moveImageView2 = fvById(R.id.first);
        // tv_register = fvById(R.id.tv_register);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {
        // btn_login.setOnClickListener(this);

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

