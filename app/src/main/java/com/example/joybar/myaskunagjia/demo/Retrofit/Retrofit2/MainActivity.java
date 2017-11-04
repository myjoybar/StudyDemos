package com.example.joybar.myaskunagjia.demo.Retrofit.Retrofit2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.demo.Retrofit.Retrofit2.Model.ImageShowMode;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
     private Button btn1,btn2,btn3,btn4,btn5;
     private TextView tv, tv2;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    ProgressBar pbar;

    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_retrofit2);
        initView();
        initData();
        setLinstener();
        fillData();


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        tv = (TextView) findViewById(R.id.tv);
        pbar = (ProgressBar) findViewById(R.id.pb);
        pbar.setVisibility(View.INVISIBLE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1();
            }
        });
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

    private void test1(){

        pbar.setVisibility(View.VISIBLE);
      GitHubClient client =
                new ServiceGenerator().createService(GitHubClient.class);

        final Call<ImageShowMode> call =
                client.getpicByID(18);
        call.enqueue(new Callback<ImageShowMode>() {
            @Override
            public void onResponse(Call<ImageShowMode> call, Response<ImageShowMode> response) {
                pbar.setVisibility(View.INVISIBLE);
                ImageShowMode modelBean = response.body();

                if (null != modelBean) {

                    tv.setText(modelBean.toString());

                } else {
                    //  404 or the response cannot be converted
                    //response存在什么问题，比如404什么的，onResponse也会被调用
                    //从response.errorBody().string()中获取错误信息
                    //不能背解析的情况下，response.body()会返回null

                    ResponseBody responseBody = response.errorBody();

                    if (responseBody!=null) {
                        try {
                            tv.setText("responseBody = " + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        //200
                        L.i("AAA", modelBean.toString() + "");

                        L.i("AAA", "null == modelBean");

                        tv.setText("null == modelBean");
                    }

                }
            }

            @Override
            public void onFailure(Call<ImageShowMode> call, Throwable t) {

            }


        });
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

