package com.joybar.appcommponentlib.router1.exception;

/**
 * Created by joybar on 17/01/2017.
 */

public class ServiceNotRouteException extends NotRouteException {

    public ServiceNotRouteException(String pattern) {
        super("service", pattern);
    }
}

