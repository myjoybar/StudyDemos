package com.joybar.appcommponentlib.router1.routerlib;

import android.content.Context;

/**
 * Created by joybar on 04/11/2017.
 */

public interface Router<T, V> {

    void addRouter(Rule rule);

    V invokeRouter(Context context, Rule.RuleKey ruleKey);

}