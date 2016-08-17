package com.example.joybar.myaskunagjia.demo.Retrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.demo.Retrofit.demoTest.GitHubClient;
import com.example.joybar.myaskunagjia.demo.Retrofit.demoTest.Model2;
import com.example.joybar.myaskunagjia.demo.Retrofit.demoTest.Model3;
import com.example.joybar.myaskunagjia.demo.Retrofit.demoTest.ModelBean;
import com.example.joybar.myaskunagjia.demo.Retrofit.demoTest.ServiceGenerator;
import com.example.joybar.myaskunagjia.utils.logger.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6;
    TextView tv;
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_retrofit);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);

        tv = (TextView) findViewById(R.id.tv);
        pbar = (ProgressBar) findViewById(R.id.pb);
        pbar.setVisibility(View.INVISIBLE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test3();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test4();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,com.example.joybar.myaskunagjia.demo.Retrofit.Retrofit2.MainActivity.class);
               startActivity(intent);
            }
        });
    }

//get 传值
    public void test1(){
        pbar.setVisibility(View.VISIBLE);

        GitHubClient client = new ServiceGenerator().createService(GitHubClient.class);
        final   Call<ModelBean> call =
                client.setError("15269627973");

        call.enqueue(new Callback<ModelBean>() {
            @Override
            public void onResponse(Response<ModelBean> response) {
                pbar.setVisibility(View.INVISIBLE);
                ModelBean modelBean = response.body();


                if (null != modelBean) {
                    L.i("AAA", modelBean.getResult() + "");
                    L.i("AAA", modelBean.toString() + "");
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
            public void onFailure(Throwable t) {
                Logger.init("AAA");
                Logger.d("AA", "error");
                tv.setText("error");
                pbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    //get 参数
    public void test2(){
        pbar.setVisibility(View.VISIBLE);

        GitHubClient client = new ServiceGenerator().createService(GitHubClient.class);
        final   Call<ModelBean> call =
                client.setCheck("15269627973");

        call.enqueue(new Callback<ModelBean>() {
            @Override
            public void onResponse(Response<ModelBean> response) {
                pbar.setVisibility(View.INVISIBLE);
                ModelBean modelBean = response.body();
                if (null != modelBean) {
                    L.i("AAA", modelBean.getResult() + "");
                    L.i("AAA", modelBean.toString() + "");
                    tv.setText(modelBean.toString());

                } else {
                    L.i("AAA", "null == modelBean");
                    tv.setText("null == modelBean");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Logger.init("AAA");
                Logger.d("AA", "error");
                tv.setText("error");
                pbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    //post 参数 无Token
    public void test3(){
        pbar.setVisibility(View.VISIBLE);

        GitHubClient client = new ServiceGenerator().createService(GitHubClient.class);
        final   Call<Model2> call =
                client.setLoginService("15269627973", "123456", "consignor");
        call.enqueue(new Callback<Model2>() {
            @Override
            public void onResponse(Response<Model2> response) {
                pbar.setVisibility(View.INVISIBLE);
                L.i("AAA", " response.toString()= " + response.toString());
                L.i("AAA", "response.body().toString()= " + response.body().toString()
                );
                L.i("AAA", "response.body().result= " + response.body().result);

                Model2 modelBean = response.body();
                if (null != modelBean) {
                    tv.setText(modelBean.toString());

                } else {
                    L.i("AAA", "null == modelBean");
                    tv.setText("null == modelBean");
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Logger.d("AA", "error");
                tv.setText("error");
                pbar.setVisibility(View.INVISIBLE);
            }
        });
    }



    //post 参数  Token
    public void test4(){
        pbar.setVisibility(View.VISIBLE);

        String token = " oY/aqc46rEuBgnSyvcj8SFhuZLxHgvJkL7Y+acfSQjr7BrKrYRtawJ1te1DEOFh6aORBOr+ezWX3b3/d/AiwtoB45GF1BCiygNnhy9bFxJ" +
                "oDsrK8bnr744Z+qVJ1zeO7tu4phiR897vNHV2rO3HCVnLw1HScXs" +
                "4KThpHbRJIJsAtAJ0pzQzUaJsuJWtBJCQVeyHGldSe23l9h" +
                "j85+pmReg==";
//        GitHubClient client =
//                new ServiceGenerator().createService(GitHubClient.class,"sss");
        GitHubClient client = new ServiceGenerator().createService(GitHubClient.class);

        final   Call<Model3> call =
                client.melinkInfoService("15269627973", "melink");

        call.enqueue(new Callback<Model3>() {
            @Override
            public void onResponse(Response<Model3> response) {
                pbar.setVisibility(View.INVISIBLE);

                Model3 modelBean = response.body();
                if (null != modelBean) {
                    tv.setText(modelBean.toString());

                } else {
                    L.i("AAA", "null == modelBean");
                    tv.setText("null == modelBean");
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Logger.d("AA", "error");
                tv.setText("error");
                pbar.setVisibility(View.INVISIBLE);
            }
        });
    }


}
