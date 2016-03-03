package com.example.joybar.myaskunagjia.demo.okhttp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by joybar on 15/11/4.
 */
public class OkhttpActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // private Button btn_login;
    private TextView tv;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
        initData();
        setLinstener();
        fillData();

        Log.d("AAAAAAA", "AAAA1=" + tv.getWidth() + "");

    }

    @Override
    protected void onStart() {
        L.i(TAG, "onStart");

        super.onStart();
        Log.d("AAAAAAA", "AAAA2=" + tv.getWidth() + "");

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
        Log.d("AAAAAAA", "AAAA3=" + tv.getWidth() + "");

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
        tv = (TextView) this.findViewById(R.id.tv);

    }

    @Override
    protected void initData() {
        initOkHttp();
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

    public void initOkHttp() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
//创建一个Request
        final Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .build();
//new call
        Call call = mOkHttpClient.newCall(request);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(final Response response) throws IOException {
               final String htmlStr =  response.body().string();
//                Looper.prepare();
//                T.showLong(mContext, htmlStr);
//                Looper.loop();

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tv.setText(htmlStr);
                    }

                });
            }
        });

    }

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

