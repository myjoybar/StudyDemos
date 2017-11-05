package com.joybar.appcommponentlib.router1.exception;

/**
 * Created by joybar on 17/01/2017.
 */

public class ActivityNotRouteException extends NotRouteException {

    public ActivityNotRouteException(String pattern) {
        super("activity", pattern);
    }
}
