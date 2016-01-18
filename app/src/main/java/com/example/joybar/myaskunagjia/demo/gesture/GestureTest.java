package com.example.joybar.myaskunagjia.demo.gesture;

/**
 * Created by joybar on 15/11/26.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;

/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class GestureTest extends Activity
        implements OnGestureListener
{
    // 定义手势检测器实例
    GestureDetector detector;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gesture);
        //创建手势检测器
        detector = new GestureDetector(this);
    }
    //将该Activity上的触碰事件交给GestureDetector处理
    @Override
    public boolean onTouchEvent(MotionEvent me)
    {
        return detector.onTouchEvent(me);
    }
    @Override
    public boolean onDown(MotionEvent arg0)
    {

        T.showShort(this, "onDown");
        return false;
    }

    final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY)
    {

        T.showShort(this, "onFling");



        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // Fling left
            L.i("MyGesture", "Fling left");

            T.showShort(this, "Fling left");
        } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // Fling right
            L.i("MyGesture", "Fling right");

            T.showShort(this, "Fling Right");
        }


        return false;
    }
    @Override
    public void onLongPress(MotionEvent e)
    {

        T.showShort(this, "onLongPress");
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY)
    {

        T.showShort(this, "onScroll");
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e)
    {

        T.showShort(this, "onShowPress");
    }
    @Override
    public boolean onSingleTapUp(MotionEvent e)
    {

        T.showShort(this, "onSingleTapUp");
        return false;
    }
}