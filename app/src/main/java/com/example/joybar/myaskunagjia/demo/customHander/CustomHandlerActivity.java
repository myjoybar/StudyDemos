package com.example.joybar.myaskunagjia.demo.customHander;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by joybar on 15/11/4.
 */
public class CustomHandlerActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    private Button btn1, btn2, btn3, btn4, btn5;
    private TextView tv1, tv2;
    // 成员变量adapter
    // 部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acopy_main);
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
        btn1.setOnClickListener(this);
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
                  test();
               // test0();
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


    private void test0() {

        Timer mTimer = new Timer(true);
        TimerTask mTimerTask;
        mTimerTask = new TimerTask() {
            public void run() {
                Log.v(TAG, getTreadName()+"time:" + System.currentTimeMillis() / 1000);
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    private static String getTreadName() {
        return "[Thread: " + Thread.currentThread().getName() + "]_";
    }
    private void test() {


        Log.d(TAG, " test()=");
        CustomLooper.prepare();

        CustomHandler customHandler = new CustomHandler() {
            @Override
            public void handleMessage(CustomMessage msg) {
                super.handleMessage(msg);
                Log.d(TAG, "msg=" + msg.what);
            }
        };

        customHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "I am msg,delay 0");
            }
        }, 0);

        customHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "I am msg,delay 1000");
            }
        }, 1000);

        for (int i = 0; i < 100; i++) {
            CustomMessage customMessage = new CustomMessage();
            customMessage.what = i;

            customHandler.sendMessage(customMessage);
        }


        CustomLooper.loop();


        Log.d(TAG, " after loop"); // 永远不会执行

    }


}
