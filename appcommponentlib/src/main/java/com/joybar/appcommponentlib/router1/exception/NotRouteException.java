package com.joybar.appcommponentlib.router1.exception;

/**
 * Created by joybar on 17/01/2017.
 */

public class NotRouteException extends RuntimeException {

    public NotRouteException(String name, String pattern) {
        super(String.format("%s cannot be resolved with pattern %s, have you declared it in your Router?", name, pattern));
    }
}
