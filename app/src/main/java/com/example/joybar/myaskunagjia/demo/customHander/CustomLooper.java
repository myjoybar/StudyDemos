package com.example.joybar.myaskunagjia.demo.customHander;

import android.util.Log;

/**
 * Created by joybar on 01/07/2018.
 */

public class CustomLooper {

    private static CustomLooper sCustomLooper;  // guarded by Looper.class

    static final ThreadLocal<CustomLooper> sThreadLocal = new ThreadLocal<CustomLooper>();


    final CustomMessageQueue mQueue;
    final Thread mThread;


    private CustomLooper() {
        mQueue = new CustomMessageQueue();
        mThread = Thread.currentThread();
    }

    public static CustomLooper getCustomLooper() {
        return sCustomLooper;
    }

    public static CustomLooper myLooper() {
        return sThreadLocal.get();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new CustomLooper());
    }

    public static void loop() {
        final CustomLooper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }

        final CustomMessageQueue queue = me.mQueue;

        for (;;) {

            Log.d(CustomHandlerActivity.TAG," loop..........");
            CustomMessage msg = queue.next(); // might block

            if (msg == null) {
                Log.d(CustomHandlerActivity.TAG," loop..........return");
                // No message indicates that the message queue is quitting.
                return;
            }
            msg.target.dispatchMessage(msg);
        }


    }
}
