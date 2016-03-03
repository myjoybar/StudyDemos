package com.example.joybar.myaskunagjia.demo.okhttp.demo1.Builder;

import com.example.joybar.myaskunagjia.demo.okhttp.demo1.Request.RequestCall;

import java.util.Map;

/**
 * Created by joybar on 1/18/16.
 */
public abstract class OkHttpRequestBuilder {

    protected String url;
    protected Map<String, String> headers;
    protected Map<String, String> params;

    public abstract OkHttpRequestBuilder url(String url);

    public abstract OkHttpRequestBuilder params(Map<String, String> params);

    public abstract OkHttpRequestBuilder addParams(String key, String val);

    public abstract OkHttpRequestBuilder headers(Map<String, String> headers);

    public abstract OkHttpRequestBuilder addHeader(String key, String val);

    public abstract RequestCall build();
}
