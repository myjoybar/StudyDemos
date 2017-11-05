package com.joybar.appcommponentlib.router1.exception;

import com.joybar.appcommponentlib.router1.routerlib.Rule;

/**
 * Created by joybar on 17/01/2017.
 */

public class NoRouterException extends RuntimeException {

    public NoRouterException(Rule rule) {
        super(String.format("this rule: module = %s ,pattern = %s ,scheme = %s, className = %s ,which is note add into routerList, have you declared it in your Router?", rule.getModule(), rule.getPattern(), rule.getScheme(), rule.getClass().getName()));
    }
}
