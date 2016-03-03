package com.example.joybar.myaskunagjia.demo.okhttp.demo1.Builder;

import com.example.joybar.myaskunagjia.demo.okhttp.demo1.Request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by joybar on 1/18/16.
 */
public class GetBuilder extends OkHttpRequestBuilder {
    @Override
    public GetBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public GetBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public GetBuilder addParams(String key, String val) {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public GetBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public GetBuilder addHeader(String key, String val) {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }

    @Override
    public RequestCall build() {
        return null;
    }
}
