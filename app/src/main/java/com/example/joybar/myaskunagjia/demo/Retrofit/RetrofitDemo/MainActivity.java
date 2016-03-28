package com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo.RetrofitUtils.RetrofitUtils;
import com.example.joybar.myaskunagjia.utils.logger.Logger;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6;
    TextView tv;
    ImageView imv;
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_retrofit2);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        imv = (ImageView) findViewById(R.id.imv);
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
           //     test3();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    test4();
            }
        });
    }


    public void test1(){

        Call<Model> call = RetrofitUtils.getRetrofitInstance().getmClient().setGetId(1);
        RetrofitUtils.getRetrofitInstance().setmCall(call).request(new RetrofitUtils.DataCallBackListener() {
            @Override
            public void onRequestStart() {

            }

            @Override
            public void onRequestSuccess(BaseModel baseModel) {
                Model modelBean = (Model) baseModel.getData();
                if (null != modelBean) {
                    L.i("AAA", modelBean.getTotal() + "");
                    L.i("AAA", modelBean.getTngou().get(0).getImg() + "");
                    tv.setText(modelBean.getTngou().get(0).getTitle());

                    Picasso.with(MainActivity.this).load("http://tnfs.tngou.net/image"+modelBean.getTngou().get(0).getImg()).into(imv);

                }
            }

            @Override
            public void onRequestFail() {

            }

            @Override
            public void onRequestFinish() {

            }
        });

        /*
        pbar.setVisibility(View.VISIBLE);

     //   RetrofitServiceApi client = new ServiceGenerator().createService(RetrofitServiceApi.class);

        RetrofitServiceApi client = new ServiceGernerator2().createRestApi();

            final   Call<Model> call = client.setGetId(1);


        call.enqueue(new Callback<Model>() {





            @Override
            public void onResponse(Call<Model> call,Response<Model> response) {
                pbar.setVisibility(View.INVISIBLE);
                Model modelBean = response.body();
                //  List<Model> list= (List<Model>) response.body();

                if (null != modelBean) {

                    L.i("AAA", modelBean.getTotal() + "");
                    L.i("AAA", modelBean.getTngou().get(0).getImg() + "");
                    tv.setText(modelBean.getTngou().get(0).getTitle());

                    Picasso.with(MainActivity.this).load("http://tnfs.tngou.net/image"+modelBean.getTngou().get(0).getImg()).into(imv);
                } else {
                    //  404 or the response cannot be converted
                    //response存在什么问题，比如404什么的，onResponse也会被调用
                    //从response.errorBody().string()中获取错误信息
                    //不能背解析的情况下，response.body()会返回null

                    ResponseBody responseBody = response.errorBody();

                    if (responseBody != null) {
                        try {
                            tv.setText("responseBody = " + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //200
                        L.i("AAA", modelBean.toString() + "");

                        L.i("AAA", "null == modelBean");

                        tv.setText("null == modelBean");
                    }

                }
            }

            @Override
            public void onFailure(Call<Model> call,Throwable t) {
                T.showShort(MainActivity.this,"网络异常，请稍后再试");
                Logger.init("AAA");
                Logger.d("AA", "error");
                tv.setText("error");
                pbar.setVisibility(View.INVISIBLE);
            }
        });

        */



    }



    public void test2(){
        pbar.setVisibility(View.VISIBLE);

      //  RetrofitServiceApi client = new ServiceGenerator().createService(RetrofitServiceApi.class);

        RetrofitServiceApi client = new ServiceGernerator2().createRestApi();
        final   Call<Model> call =client.setPostId(10);


        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call,Response<Model> response) {
                pbar.setVisibility(View.INVISIBLE);
                Model modelBean = response.body();
                //  List<Model> list= (List<Model>) response.body();

                if (null != modelBean ) {

                    L.i("AAA", modelBean.getTotal() + "");
                    L.i("AAA", modelBean.getTngou().get(0).getImg() + "");
                    tv.setText(modelBean.getTngou().get(0).getTitle());
                    Picasso.with(MainActivity.this).load("http://tnfs.tngou.net/image"+modelBean.getTngou().get(0).getImg()).into(imv);

                } else {
                    //  404 or the response cannot be converted
                    //response存在什么问题，比如404什么的，onResponse也会被调用
                    //从response.errorBody().string()中获取错误信息
                    //不能背解析的情况下，response.body()会返回null

                    ResponseBody responseBody = response.errorBody();

                    if (responseBody != null) {
                        try {
                            tv.setText("responseBody = " + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //200
                        L.i("AAA", modelBean.toString() + "");

                        L.i("AAA", "null == modelBean");

                        tv.setText("null == modelBean");
                    }

                }
            }

            @Override
            public void onFailure(Call<Model> call,Throwable t) {
                T.showShort(MainActivity.this,"网络异常，请稍后再试");
                Logger.init("AAA");
                Logger.d("AA", "error");
                tv.setText("error");
                pbar.setVisibility(View.INVISIBLE);
            }
        });
    }

}
