package com.example.joybar.myaskunagjia.demo.animation.demo1;

import android.view.View;

/**
 * Created by joybar on 15/11/25.
 */
public class ViewWrapper {
    private View mTarget;

    public ViewWrapper(View target) {
        mTarget = target;
    }
    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }
    public void setWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();
    }
}
