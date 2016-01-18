package com.example.joybar.myaskunagjia.demo.gesture;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Scroller;
import android.widget.Toast;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by joybar on 15/11/24.
 */
public class MyButton  extends Button {
    private Scroller mScroller;
    private GestureDetector mGesture;
    private OnDoubleClickListener onDoubleClickListener;

    //自定义监听器接口
    interface OnDoubleClickListener{
        void onDoubleClick(View view);
    }
    public void setOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener){

        this.onDoubleClickListener = onDoubleClickListener;

    };
    public MyButton(Context context) {
        super(context);
        initGesture(context);
    }

    public MyButton(final Context context, AttributeSet attrs) {
        super(context, attrs);
        initGesture(context);
    }
    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGesture(context);
    }

    private void initGesture(final Context context){
        mScroller = new Scroller(context);
        mGesture = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if(onDoubleClickListener!=null) {
                    onDoubleClickListener.onDoubleClick(MyButton.this);
                }
                Toast.makeText(context, "双击事件", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //滑动和拖拽最好不要一起实现，会产生矛盾
                if(Math.abs(e1.getX()-e2.getX())>50){
                    setTranslationX(e2.getX() - e1.getX());

                    ObjectAnimator.ofFloat(MyButton.this, "translationX", getTranslationX(), e2.getX() - e1.getX())

                            .setDuration(500).start();

                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //根据手势拖拽控件的相位而移动控件
                setTranslationX(getTranslationX()+e2.getX() - e1.getX());
                setTranslationY(getTranslationX() + e2.getY() - e1.getY());
             return super.onScroll(e1, e2, distanceX, distanceY);



            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //touch事件传给onTouchEvent()
        mGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {

        //设置mScroller的滚动偏移量
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {

        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {

            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
        super.computeScroll();
    }
}
