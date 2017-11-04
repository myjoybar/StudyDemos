package com.joybar.appcommponentlib.router1.routerlib;

import android.text.TextUtils;

import com.joybar.appcommponentlib.router1.exception.NotRouteException;

import java.util.HashMap;

import static android.R.attr.name;

/**
 * Created by joybar on 04/11/2017.
 */

public class RouterHolder {

    private HashMap<String, Rule> mRules;

    public RouterHolder() {
        mRules = new HashMap<>();
    }

    private static class RouterInHolder {
        public static RouterHolder INSTANCE = new RouterHolder();
    }

    public static RouterHolder getInstance() {
        return RouterInHolder.INSTANCE;
    }


    public final RouterHolder addRule(Rule rule) {
        mRules.put(rule.getScheme(), rule);
        return this;
    }

    public Rule getRule(String scheme){
        if(TextUtils.isEmpty(scheme)){
            throw new NullPointerException(String.format("%s cannot be resolved, have you declared it in your Router?", scheme));
        }
        return mRules.get(scheme);

    }






}
