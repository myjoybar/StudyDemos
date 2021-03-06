package com.joybar.appcommponentlib.router3;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

import com.joybar.appcommponentlib.router3.interceptor.RouteInterceptor;
import com.joybar.appcommponentlib.router3.utils.CheckUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 12/11/2017.
 */

public class RouterService implements IRouterManagerService {

    RouterRequest routerRequest;
    private static List<RouteInterceptor> routeInterceptors;

    public RouterService(Context context) {
        routerRequest = new RouterRequest();
        routeInterceptors = new ArrayList<>();
        routerRequest.setContext(context);
    }

    @Override
    public IRouterManagerService buildRule(Rule3 rule) {
        CheckUtils.checkNotNull(rule);
        Rule3 registerRule = RouterManager3.ruleMap.get(rule);
        if (registerRule == null) {
            throw new IllegalArgumentException("You cannot build an unRegisterRule,have you register it?");
        }
        routerRequest.setRule(registerRule);
        return this;
    }


    @Override
    public void go() {
        if (!isIntercepted()) {
            Context context = routerRequest.getContext();
            CheckUtils.checkNotNull(context);
            routerRequest.getContext().startActivity(buildIntent(context));
        }
    }

    @Override
    public void goForResult(int requestCode) {
        if (!isIntercepted()) {
            Context context = routerRequest.getContext();
            CheckUtils.checkNotNull(context);
            ((Activity) routerRequest.getContext()).startActivityForResult(buildIntent(context), requestCode);
        }
    }

    private Intent buildIntent(Context context) {
        Class klass = routerRequest.getRule().getClassz();
        if (klass == null) {
            throw new RuntimeException("class can  not be  null in RouterRequest,have your set it in  your RouterService");
        }
        Intent intent = new Intent(context, routerRequest.getRule().getClassz());
        Bundle bundle = routerRequest.getBundle();
        if (bundle != null) {
            intent.putExtras(routerRequest.getBundle());
        }
        return intent;
    }


    @Override
    public IRouterManagerService withInterceptorCallback(InterceptorCallback interceptorCallback) {
        routerRequest.setInterceptorCallback(interceptorCallback);
        return this;
    }

    @Override
    public IRouterManagerService addInterceptor(RouteInterceptor routeInterceptor) {
        routeInterceptors.add(routeInterceptor);
        return this;
    }

    @Override
    public boolean isIntercepted() {
        for (RouteInterceptor interceptor : routeInterceptors) {
            if (interceptor.isIntercepted(routerRequest)) {
                InterceptorCallback interceptorCallback = routerRequest.getInterceptorCallback();
                if(null!=interceptorCallback){
                    interceptorCallback.onIntercept("被拦截");
                }
                return true;
            }
        }
        InterceptorCallback interceptorCallback = routerRequest.getInterceptorCallback();
        if(null!=interceptorCallback){
            interceptorCallback.onContinue();
        }
        return false;
    }

    @Override
    public IRouterManagerService withExtra(Bundle bundle) {
        routerRequest.setBundle(bundle);
        return this;
    }

    @Override
    public IRouterManagerService withData(String key, Object value) {
        if (value == null) {
            return this;
        }
        Bundle bundle = routerRequest.getBundle();
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (value instanceof Bundle) {
            bundle.putBundle(key, (Bundle) value);
        } else if (value instanceof Byte) {
            bundle.putByte(key, (byte) value);
        } else if (value instanceof Short) {
            bundle.putShort(key, (short) value);
        } else if (value instanceof Integer) {
            bundle.putInt(key, (int) value);
        } else if (value instanceof Long) {
            bundle.putLong(key, (long) value);
        } else if (value instanceof Character) {
            bundle.putChar(key, (char) value);
        } else if (value instanceof Boolean) {
            bundle.putBoolean(key, (boolean) value);
        } else if (value instanceof Float) {
            bundle.putFloat(key, (float) value);
        } else if (value instanceof Double) {
            bundle.putDouble(key, (double) value);
        } else if (value instanceof String) {
            bundle.putString(key, (String) value);
        } else if (value instanceof CharSequence) {
            bundle.putCharSequence(key, (CharSequence) value);
        } else if (value instanceof byte[]) {
            bundle.putByteArray(key, (byte[]) value);
        } else if (value instanceof short[]) {
            bundle.putShortArray(key, (short[]) value);
        } else if (value instanceof int[]) {
            bundle.putIntArray(key, (int[]) value);
        } else if (value instanceof long[]) {
            bundle.putLongArray(key, (long[]) value);
        } else if (value instanceof char[]) {
            bundle.putCharArray(key, (char[]) value);
        } else if (value instanceof boolean[]) {
            bundle.putBooleanArray(key, (boolean[]) value);
        } else if (value instanceof float[]) {
            bundle.putFloatArray(key, (float[]) value);
        } else if (value instanceof double[]) {
            bundle.putDoubleArray(key, (double[]) value);
        } else if (value instanceof String[]) {
            bundle.putStringArray(key, (String[]) value);
        } else if (value instanceof CharSequence[]) {
            bundle.putCharSequenceArray(key, (CharSequence[]) value);
        } else if (value instanceof ArrayList) {
            if (!((ArrayList) value).isEmpty()) {
                Object obj = ((ArrayList) value).get(0);
                if (obj instanceof Integer) {
                    bundle.putIntegerArrayList(key, (ArrayList<Integer>) value);
                } else if (obj instanceof String) {
                    bundle.putStringArrayList(key, (ArrayList<String>) value);
                } else if (obj instanceof CharSequence) {
                    bundle.putCharSequenceArrayList(key, (ArrayList<CharSequence>) value);
                } else if (obj instanceof Parcelable) {
                    bundle.putParcelableArrayList(key, (ArrayList<? extends Parcelable>) value);
                }
            }
        } else if (value instanceof SparseArray) {
            bundle.putSparseParcelableArray(key, (SparseArray<? extends Parcelable>) value);
        } else if (value instanceof Parcelable) {
            bundle.putParcelable(key, (Parcelable) value);
        } else if (value instanceof Parcelable[]) {
            bundle.putParcelableArray(key, (Parcelable[]) value);
        } else if (value instanceof Serializable) {
            bundle.putSerializable(key, (Serializable) value);
        } else {
            throw new RuntimeException("do not match the bundle type");
        }
        routerRequest.setBundle(bundle);
        return this;
    }


}
