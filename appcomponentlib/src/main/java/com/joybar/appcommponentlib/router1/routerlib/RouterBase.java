package com.joybar.appcommponentlib.router1.routerlib;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joybar on 04/11/2017.
 */

public abstract class RouterBase<T> implements Router<T, Intent> {

    private Map< Rule.RuleKey ,Rule> ruleMap;

    @Override
    public void addRouter(Rule rule) {
        if (ruleMap == null) {
            ruleMap = new HashMap(10);
        }
        ruleMap.put(new Rule.RuleKey(rule.getModule(),rule.getPattern(),rule.getScheme()),rule);
    }

    @Override
    public Intent invokeRouter(Context context, Rule.RuleKey ruleKey) {

        Rule rule = ruleMap.get(ruleKey);
        if (rule==null) {
            throwException(new Rule(ruleKey));
        }
        Class<T> klass = rule.getClassz();
        if (klass == null) {
            throwException(rule);
        }
        return new Intent(context, klass);

    }

    public abstract void throwException(Rule rule);

}
