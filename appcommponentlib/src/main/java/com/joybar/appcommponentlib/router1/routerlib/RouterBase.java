package com.joybar.appcommponentlib.router1.routerlib;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

import static android.R.attr.scheme;

/**
 * Created by joybar on 04/11/2017.
 */

public abstract class RouterBase<T> implements Router<T, Intent> {

    private HashMap<String, Rule> mRules;

    @Override
    public void addRouter(Rule rule) {
        if(mRules == null){
            mRules = new HashMap<>();
        }
        mRules.put(rule.getPattern()+rule.getScheme(), rule);
    }

    @Override
    public Intent invokeRouter(Context ctx,String pattern, String scheme) {

        Rule rule = mRules.get(pattern+scheme);
        if(rule == null){
            throwException(pattern+scheme);
        }
        Class<T> klass = rule.getClassz();
        if (klass == null) {
            throwException(pattern+scheme);
        }
        return new Intent(ctx, klass);

    }

    public abstract void throwException(String scheme);

}
