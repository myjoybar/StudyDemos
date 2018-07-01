package com.example.joybar.myaskunagjia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joybar.myaskunagjia.base.BaseActivity;

public class MainActivity extends BaseActivity {
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
    //
    Button btn16;
    Button btn17;
    Button btn18;
    Button btn19;

    Button btn20;
    //
    Button btn21;
    Button btn22;
    Button btn23;
    Button btn24;
    Button btn25;

    Button btn26;
    Button btn27;
    Button btn28;
    Button btn29;

    Button btn30;
    Button btn31;
    Button btn32;
    Button btn33;
    Button btn34;
    Button btn35;
    Button btn36;
    Button btn37;
    Button btn38;
    Button btn39;

    Button btn40;
    Button btn41;
    Button btn42;
    Button btn43;
    Button btn44;
    Button btn45;
    Button btn46;
    Button btn47;
    Button btn48;
    Button btn49;

    Button btn50;
    Button btn51;
    Button btn52;
    Button btn53;
    Button btn54;
    Button btn55;
    Button btn56;
    Button btn57;
    Button btn58;
    Button btn59;

    Button btn60;
    Button btn61;
    Button btn62;
    Button btn63;
    Button btn64;
    Button btn65;
    Button btn66;
    Button btn67;
    Button btn68;
    Button btn69;

    Button btn70;
    Button btn71;
    Button btn72;
    Button btn73;
    Button btn74;
    Button btn75;
    Button btn76;
    Button btn77;
    Button btn78;
    Button btn79;

    Button btn80;
    Button btn81;
    Button btn82;
    Button btn83;
    Button btn84;
    Button btn85;
    Button btn86;
    Button btn87;
    Button btn88;
    Button btn89;

    Button btn90;
    Button btn91;
    Button btn92;
    Button btn93;
    Button btn94;
    Button btn95;
    Button btn96;
    Button btn97;
    Button btn98;
    Button btn99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setLinstener();
        fillData();
    }

    @Override
    protected void initView() {
        initButton(btn1,R.id.btn1,com.example.joybar.myaskunagjia.demo.okhttp.OkhttpActivity.class);
        initButton(btn2,R.id.btn2,com.example.joybar.myaskunagjia.demo.scroll.MainActivity.class);
        initButton(btn3,R.id.btn3,com.example.joybar.myaskunagjia.demo.tanmu.MainActivity.class);
     //   initButton(btn4,R.id.btn4,com.example.joybar.myaskunagjia.demo.pop.MainActivity.class);
        initButton(btn5,R.id.btn5,com.example.joybar.myaskunagjia.demo.gesture.MainActivityGuide.class);
        initButton(btn6,R.id.btn6,com.example.joybar.myaskunagjia.demo.titlebar.MainActivity.class);
        initButton(btn7,R.id.btn7,com.example.joybar.myaskunagjia.demo.webview.MainActivityGuide.class);
        initButton(btn8,R.id.btn8,com.example.joybar.myaskunagjia.demo.toast.MainActivity.class);
        initButton(btn9,R.id.btn9,com.example.joybar.myaskunagjia.demo.animation.MainActivityGuide.class);

      initButton(btn10,R.id.btn10,com.example.joybar.myaskunagjia.demo.md.MainActivityGuide.class);
     //   initButton(btn11,R.id.btn11,com.example.joybar.myaskunagjia.demo.gesture.GestureTest.class);
        initButton(btn12,R.id.btn12,com.example.joybar.myaskunagjia.demo.nicehair.MainActivity.class);
        initButton(btn13,R.id.btn13,com.example.joybar.myaskunagjia.demo.eventbus.MainActivity.class);
        initButton(btn14,R.id.btn14,com.example.joybar.myaskunagjia.demo.xiangji1.MainActivity.class);
        initButton(btn15,R.id.btn15,com.example.joybar.myaskunagjia.demo.xiangji2.MainActivity.class);
        initButton(btn16,R.id.btn16,com.example.joybar.myaskunagjia.demo.mvp.MainActivity.class);
        initButton(btn17,R.id.btn17,com.example.joybar.myaskunagjia.demo.github.MainActivityGuideGit.class);
        //initButton(btn18,R.id.btn18,com.example.joybar.myaskunagjia.demo.RXJava.MainActivityRx.class);
        initButton(btn19,R.id.btn19,com.example.joybar.myaskunagjia.demo.Retrofit.activity.MainActivity.class);
        initButton(btn20,R.id.btn20, com.example.joybar.myaskunagjia.demo.drawable.MainActivity.class);
        initButton(btn21,R.id.btn21, com.example.joybar.myaskunagjia.demo.databinding.MainActivity.class);
        initButton(btn22,R.id.btn22, com.example.joybar.myaskunagjia.demo.stucture.MainActivityStructure.class);
        initButton(btn23,R.id.btn23, com.example.joybar.myaskunagjia.demo.otherfunc.MainActivity.class);
        initButton(btn24,R.id.btn24, com.example.joybar.myaskunagjia.demo.testapplication.TestApplicationActivity.class);
        initButton(btn25,R.id.btn25, com.example.joybar.myaskunagjia.demo.lifecycle.MainActivityLifeCycle.class);
        initButton(btn26,R.id.btn26, com.example.joybar.myaskunagjia.demo.customHander.CustomHandlerActivity.class);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {

    }

    @Override
    protected void fillData() {

    }


    @Override
    public void onClick(View v) {

    }

    public void initButton(Button btn, int id, final Class c) {

        btn = (Button) this.findViewById(id);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, c);
                startActivity(intent);

            }
        });
    }

}
