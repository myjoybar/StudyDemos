package com.example.joybar.myaskunagjia.demo.Retrofit.demoTest;

import com.google.gson.annotations.Expose;

/**
 * Created by joybar on 1/9/16.
 */
public class ModelBean {

    public int result;
    public  Date data = new Date();
    public Error error = new Error();
    class Date{
        String reg_app;
        String reg_time;

        @Override
        public String toString() {
            return "Date{" +
                    "reg_app='" + reg_app + '\'' +
                    ", reg_time='" + reg_time + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "ModelBean{" +
                "result=" + result +
                ", data=" + data.toString() +
                ", error=" + error.toString() +
                '}';
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
