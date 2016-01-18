package com.example.joybar.myaskunagjia.demo.gesture.gesture3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by joybar on 15/12/3.
 */
public class MoveImageView extends ImageView {

    public MoveImageView(Context context) {
        super(context);
    }

    public MoveImageView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MoveImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setLocation(int x, int y) {
        this.setFrame(x, y - this.getHeight(), x + this.getWidth(), y);
    }

    // 移动
    public boolean autoMouse(MotionEvent event) {
        boolean rb = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                this.setLocation((int) event.getX(), (int) event.getY());
                rb = true;
                break;
        }
        return rb;
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 按下
              //  autoMouse(event);
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动
                autoMouse(event);
                break;
            case MotionEvent.ACTION_UP:
                // 抬起
            //    autoMouse(event);
                break;
        }

            /*
             * 备注1：次处一定要将return super.onTouchEvent(event)修改为return true，原因是：
             * 1）父类的onTouchEvent(event)方法可能没有做任何处理，但是返回了false。
             * 2)一旦返回false，在该方法中再也不会收到MotionEvent.ACTION_MOVE及MotionEvent.ACTION_UP事件。
             */
        //return super.onTouchEvent(event);
        return true;
    }




}