package com.joybar.appcommponentlib.router1.exception;

/**
 * Created by joybar on 17/01/2017.
 */

public class BroadcastNotRouteException extends NotRouteException {

    public BroadcastNotRouteException(String pattern) {
        super("receiver", pattern);
    }
}
