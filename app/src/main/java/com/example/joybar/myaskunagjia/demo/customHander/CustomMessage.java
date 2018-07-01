package com.example.joybar.myaskunagjia.demo.customHander;

/**
 * Created by joybar on 01/07/2018.
 */

public class CustomMessage {

    public int what;

    public Object obj;

    CustomMessage next;
    private static CustomMessage sPool;
    private static int sPoolSize = 0;
    private static final int MAX_POOL_SIZE = 50;

    long when;

    CustomHandler target;

    Runnable callback;


}
