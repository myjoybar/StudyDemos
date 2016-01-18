package com.example.joybar.myaskunagjia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.demo.webview.MainActivity;
import com.example.joybar.myaskunagjia.demo.webview.MainActivity2;
import com.example.joybar.myaskunagjia.demo.webview.MainActivity3;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivityGuide extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    Button btn1;

    Button btn2;

    Button btn3;

    Button btn4;

    Button btn5;

    Button btn6;

    Button btn7;

    Button btn8;

    Button btn9;

    Button btn10;

    Button btn11;
    Button btn12;
    Button btn13;
    Button btn14;
    Button btn15;
    // private TextView tv_register, tv_reget_pwd;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acopy_guide);
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
        L.i(TAG, "onStop");
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


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {
        initButton(btn1, R.id.btn1, MainActivity.class);
        initButton(btn2,R.id.btn2,MainActivity2.class);
        initButton(btn3,R.id.btn3,MainActivity3.class);
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


    }

    public void initButton(Button btn, int id, final Class c) {

        btn = (Button) this.findViewById(id);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityGuide.this, c);
                startActivity(intent);

            }
        });
    }


}

