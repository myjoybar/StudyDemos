package com.joybar.appcommponentlib.router3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by joybar on 12/11/2017.
 */

public class RouterRequest {

    private Context context;

    private Rule rule;
    private ICallBack callBack;
    private Object object;

    private Bundle bundle;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    private Intent getIntent() {
        Intent intent = new Intent(context, Rule.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }


    public void go() {
        Intent intent = getIntent();
        context.startActivity(intent);
    }


}
