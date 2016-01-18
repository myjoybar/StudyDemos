package com.example.joybar.myaskunagjia.demo.Retrofit.demoTest;

/**
 * Created by joybar on 1/9/16.
 */
class Error{
    String code;
    String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "error{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}


