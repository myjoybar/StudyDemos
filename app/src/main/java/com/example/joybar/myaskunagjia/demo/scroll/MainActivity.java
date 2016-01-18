package com.example.joybar.myaskunagjia.demo.scroll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joybar.myaskunagjia.R;
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
        setContentView(R.layout.activity_main_scroll);
        initView();
        initData();
        setLinstener();
        fillData();
    }

    @Override
    protected void initView() {

        initButton(btn1,R.id.btn1,com.example.joybar.myaskunagjia.demo.scroll.demo1.WheelActivity.class);
        initButton(btn2,R.id.btn2,com.example.joybar.myaskunagjia.demo.scroll.demo2.MainActivity.class);
        initButton(btn3,R.id.btn3,com.example.joybar.myaskunagjia.demo.scroll.demo2.MultiScreenActivity.class);
        initButton(btn4,R.id.btn4,com.example.joybar.myaskunagjia.demo.scroll.demo3.MultiScreenActivity.class);
        initButton(btn5,R.id.btn5,com.example.joybar.myaskunagjia.demo.scroll.demo3.MainActivity.class);
        initButton(btn6,R.id.btn6,com.example.joybar.myaskunagjia.demo.scroll.demo4.MainActivity.class);
        initButton(btn7,R.id.btn7,com.example.joybar.myaskunagjia.demo.scroll.demo5.MultiScreenActivity.class);
        initButton(btn8,R.id.btn8,com.example.joybar.myaskunagjia.demo.scroll.demo6.MainActivity.class);
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
