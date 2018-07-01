package com.example.joybar.myaskunagjia.demo.customHander;


import android.os.SystemClock;
import android.util.Log;

/**
 * Created by joybar on 01/07/2018.
 */

public class CustomHandler {

    final CustomHandler.Callback mCallback;

    CustomLooper mLooper;

    final CustomMessageQueue mQueue;


    public CustomHandler(Callback callback) {

        mLooper = CustomLooper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Can't create handler inside thread that has not called " +
                    "Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
        mCallback = callback;
    }

    public CustomHandler() {
        this(null);
    }

    public interface Callback {
        public boolean handleMessage(CustomMessage msg);
    }

    /**
     * Subclasses must implement this to receive messages.
     */
    public void handleMessage(CustomMessage msg) {
    }

    public final boolean post(Runnable r)
    {
        return  sendMessageDelayed(getPostMessage(r), 0);
    }

    public final boolean postDelayed(Runnable r, long delayMillis)
    {
        return sendMessageDelayed(getPostMessage(r), delayMillis);
    }
    private static CustomMessage getPostMessage(Runnable r) {
        CustomMessage m = new CustomMessage();
        m.callback = r;
        return m;
    }


    public final boolean sendMessage(CustomMessage msg)
    {
        return sendMessageDelayed(msg, 0);
    }

    public final boolean sendMessageDelayed(CustomMessage msg, long delayMillis)
    {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis);
    }

    public boolean sendMessageAtTime(CustomMessage msg, long uptimeMillis) {
        CustomMessageQueue queue = mQueue;
        if (queue == null) {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no mQueue");
            Log.w("Looper", e.getMessage(), e);
            return false;
        }
        return enqueueMessage(queue, msg, uptimeMillis);
    }

    private boolean enqueueMessage(CustomMessageQueue queue, CustomMessage msg, long uptimeMillis) {
        msg.target = this;
        return queue.enqueueMessage(msg, uptimeMillis);
    }

    /**
     * Handle system messages here.
     */
    public void dispatchMessage(CustomMessage msg) {
        if (msg.callback != null) {
            handleCallback(msg);
        } else {
            if (mCallback != null) {
                if (mCallback.handleMessage(msg)) {
                    return;
                }
            }
            handleMessage(msg);
        }
    }

    private static void handleCallback(CustomMessage message) {
        message.callback.run();
    }


}
