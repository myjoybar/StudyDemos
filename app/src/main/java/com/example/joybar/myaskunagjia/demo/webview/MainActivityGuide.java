package com.example.joybar.myaskunagjia.demo.webview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivityGuide extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    private Button btn,btn2,btn3,btn4;
    // private TextView tv_register, tv_reget_pwd;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview1);
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
        btn = fvById(R.id.btn);
        btn2 = fvById(R.id.btn2);
        btn3 = fvById(R.id.btn3);
        // tv_register = fvById(R.id.tv_register);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {

    }

    @Override
    protected void fillData() {
        initButton(btn,R.id.btn,MainActivity.class);
        initButton(btn2,R.id.btn2,MainActivity2.class);
        initButton(btn3,R.id.btn3,MainActivity3.class);
        initButton(btn4,R.id.btn4,MainActivity4.class);
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

