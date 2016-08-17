package com.example.joybar.myaskunagjia.demo.Retrofit.Retrofit2.Model;

/**
 * Created by joybar on 4/20/16.
 */
public class BaseModel<T> {

    private int httpState;
    private int code;
    private String message;
    private T data;

    public int getHttpState() {
        return httpState;
    }

    public void setHttpState(int httpState) {
        this.httpState = httpState;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
