package com.example.joybar.myaskunagjia.demo.okhttp.demo1.Request;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

import static com.example.joybar.myaskunagjia.demo.okhttp.demo1.ExceptionUtils.Exceptions.illegalArgument;


/**
 * Created by joybar on 1/18/16.
 */
public abstract class OkHttpRequest {
    protected String url;
    protected Map<String, String> params;
    protected Map<String, String> headers;

    protected OkHttpRequest(String url,
                            Map<String, String> params, Map<String, String> headers)
    {
        this.url = url;
        this.params = params;
        this.headers = headers;

        if (url == null)
        {
            illegalArgument("url can not be null.");
        }
    }

    protected abstract RequestBody buildRequestBody();

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

}
