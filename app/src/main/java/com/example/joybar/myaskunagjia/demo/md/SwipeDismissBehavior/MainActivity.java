package com.example.joybar.myaskunagjia.demo.md.SwipeDismissBehavior;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.view.View;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;

import static android.support.design.widget.CoordinatorLayout.*;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    //http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/1103/3650.html
    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // private Button btn_login;
    // private TextView tv_register, tv_reget_pwd;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_swipe_dismiss_behavior);
        initView();
        initData();
        setLinstener();
        fillData();

        TextView textView = (TextView)findViewById(R.id.swip);
        final SwipeDismissBehavior<View> swipe
                = new SwipeDismissBehavior();
        swipe.setSwipeDirection(
                SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        swipe.setListener(
                new SwipeDismissBehavior.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {

                    }

                    @Override
                    public void onDragStateChanged(int state) {
                    }
                });

        CoordinatorLayout.LayoutParams coordinatorParams =
                (CoordinatorLayout.LayoutParams) textView.getLayoutParams();

        coordinatorParams.setBehavior(swipe);
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
        // btn_login = fvById(R.id.btn_login);
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

