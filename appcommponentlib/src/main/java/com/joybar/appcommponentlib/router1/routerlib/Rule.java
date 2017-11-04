package com.joybar.appcommponentlib.router1.routerlib;

/**
 * Created by joybar on 04/11/2017.
 */

public class Rule {

    private String pattern;
    private String scheme;
    private Class classz;



    public Rule(String pattern, String scheme, Class classz) {
        this.pattern = pattern;
        this.scheme = scheme;
        this.classz = classz;
    }

    public Class getClassz() {
        return classz;
    }

    public void setClassz(Class classz) {
        this.classz = classz;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
