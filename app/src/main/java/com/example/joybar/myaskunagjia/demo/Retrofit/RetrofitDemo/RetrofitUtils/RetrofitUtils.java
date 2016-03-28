package com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo.RetrofitUtils;

import com.example.joybar.myaskunagjia.application.MyApplication;
import com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo.BaseModel;
import com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo.RetrofitServiceApi;
import com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo.ServiceGernerator2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by joybar on 3/9/16.
 */
public class RetrofitUtils<T> {
     private  RetrofitServiceApi mClient;
     private static RetrofitUtils retrofitUtils;
     private Call<BaseModel<T>> mCall;
    private DataCallBackListener dataCallBackListener;

    public static RetrofitUtils getRetrofitInstance(){
        if (null == retrofitUtils){
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils;
    }


    public  RetrofitServiceApi getmClient(){
        if (null == mClient){
            mClient = new ServiceGernerator2().createRestApi();
          //  mClient = new ServiceGernerator2<RetrofitServiceApi>().createRestApi(RetrofitServiceApi);
        }
        return mClient;
    }


    public Call<BaseModel<T>> getmCall() {
        return mCall;
    }



    public void setDataCallBackListener(DataCallBackListener dataCallBackListener) {
        this.dataCallBackListener = dataCallBackListener;
    }

    public RetrofitUtils setmCall(Call<BaseModel<T>> mCall) {
        this.mCall = mCall;
        return  this;
    }

    public  void request(){
        dataCallBackListener.onRequestStart();

        getmCall().enqueue(new Callback<BaseModel<T>>() {
            @Override
            public void onResponse(Call<BaseModel<T>> call, Response<BaseModel<T>> response) {
                if(null!=response.body()){
                    dataCallBackListener.onRequestSuccess(response.body());
                }else{
                    dataCallBackListener.onRequestFail();
                }

                    dataCallBackListener.onRequestFinish();

            }

            @Override
            public void onFailure(Call<BaseModel<T>> call, Throwable t) {

                com.example.joybar.myaskunagjia.commom.T.showShort(MyApplication.getContext(),"网络异常，请稍后再试");
                dataCallBackListener.onRequestFinish();

            }
        });
    }


    public  void request(final DataCallBackListener dataCallBackListener){

        dataCallBackListener.onRequestStart();
        getmCall().enqueue(new Callback<BaseModel<T>>() {
            @Override
            public void onResponse(Call<BaseModel<T>> call, Response<BaseModel<T>> response) {
                if(null!=response.body()){
                    dataCallBackListener.onRequestSuccess(response.body());
                }else{
                    dataCallBackListener.onRequestFail();
                }

                dataCallBackListener.onRequestFinish();

            }

            @Override
            public void onFailure(Call<BaseModel<T>> call, Throwable t) {
                com.example.joybar.myaskunagjia.commom.T.showShort(MyApplication.getContext(),"网络异常，请稍后再试");
                dataCallBackListener.onRequestFinish();

            }
        });
    }

    public interface DataCallBackListener{
        public void onRequestStart();
        public void onRequestSuccess(BaseModel baseModel);
        public void onRequestFail();
        public void onRequestFinish();
    }
}
