package com.example.joybar.myaskunagjia.demo.customHander;

import android.os.SystemClock;

/**
 * Created by joybar on 01/07/2018.
 */

public class CustomMessageQueue {

    CustomMessage mMessages;//Message 是链表结构的


    boolean enqueueMessage(CustomMessage msg, long when) {
        synchronized (this) {

            msg.when = when;
            CustomMessage p = mMessages; //mMessages 相当于头指针
            if (p == null || when == 0 || when < p.when) {
                msg.next = p;
                mMessages = msg;
            } else {

                CustomMessage prev;
                for (; ; ) {
                    prev = p;
                    p = p.next;
                    if (p == null || when < p.when) {
                        break;
                    }

                }
                msg.next = p; // invariant: p == prev.next
                prev.next = msg;
            }

        }
        return true;
    }

    CustomMessage next() {
        int nextPollTimeoutMillis = 0;
        for (; ; ) {

            synchronized (this) {

                final long now = SystemClock.uptimeMillis();
                CustomMessage prevMsg = null;
                CustomMessage msg = mMessages;
                if (msg != null && msg.target == null) {
                    // Stalled by a barrier.  Find the next asynchronous message in the queue.
                    do {
                        prevMsg = msg;
                        msg = msg.next;
                    } while (msg != null);
                }

                if (msg != null) {
                    if (now < msg.when) {
                        // Next message is not ready.  Set a timeout to wake up when it is ready.
                        nextPollTimeoutMillis = (int) Math.min(msg.when - now, Integer.MAX_VALUE);
                    } else {
                        // Got a message.
                        if (prevMsg != null) {
                            prevMsg.next = msg.next;
                        } else {
                            mMessages = msg.next;
                        }
                        msg.next = null;
                        return msg;
                    }
                } else {
                    // No more messages.
                    nextPollTimeoutMillis = -1;
                }
            }
        }
    }
}
