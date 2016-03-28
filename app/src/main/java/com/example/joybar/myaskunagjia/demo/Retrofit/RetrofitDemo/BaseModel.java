package com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo;

/**
 * Created by joybar on 3/10/16.
 */
public class BaseModel <T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
