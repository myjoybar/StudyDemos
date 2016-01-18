package com.example.joybar.myaskunagjia.demo.scroll.demo5;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.joybar.myaskunagjia.R;

/**
 * Created by joybar on 15/11/23.
 */
public class MultiViewGroup extends ViewGroup {
    private Context mContext;

    private static String TAG = "MultiViewGroup";
    private int curScreen = 0 ;

    private Scroller mScroller = null ;
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private int mTouchState = TOUCH_STATE_REST;

    public static int  SNAP_VELOCITY = 600 ;
    private int mTouchSlop = 0 ;
    private float mLastionMotionX = 0 ;
    private float mLastMotionY = 0 ;

    private VelocityTracker mVelocityTracker = null ;



    public MultiViewGroup(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public MultiViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }
    private void init() {
        mScroller = new Scroller(mContext);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();


        LinearLayout oneLL = new LinearLayout(mContext);
       // oneLL.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg1));
        oneLL.setBackgroundResource(R.drawable.bg1);
        addView(oneLL);

        LinearLayout twoLL = new LinearLayout(mContext);
        twoLL.setBackgroundResource(R.drawable.bg2);
        addView(twoLL);

        LinearLayout threeLL = new LinearLayout(mContext);
        threeLL.setBackgroundResource(R.drawable.bg3);
        addView(threeLL);

        LinearLayout fourLL = new LinearLayout(mContext);
        fourLL.setBackgroundResource(R.drawable.bg4);
        addView(fourLL);

    }

    public void moveToLeftSide(){
        if (curScreen >= getChildCount() - 1) {
            return;
        }

        curScreen++;
        Log.i(TAG, "----moveToLeftSide---- curScreen " + curScreen);
        Log.i(TAG, "----width  " + getWidth());
    //    mScroller.startScroll((curScreen - 1) * getWidth(), 0, getWidth(), 0, 3000);
     //   scrollTo(curScreen * getWidth(), 0);

     //   invalidate();

        //自己的方法
        smoothScrollBy(getWidth(), 0);

    }




    public void moveToRightSide(){
        if (curScreen <= 0) {
            return;
        }
        curScreen-- ;
        Log.i(TAG, "----moveToRightSide---- curScreen " + curScreen);
//       mScroller.startScroll((curScreen + 1) * getWidth(), 0, -getWidth(), 0, 3000);
//    //  scrollTo(curScreen * getWidth(), 0);
//        invalidate();

        //自己的方法
        smoothScrollBy(-getWidth(), 0);
    }


    public boolean onTouchEvent(MotionEvent event){
        Log.i(TAG, "--- onTouchEvent--> " );

        // TODO Auto-generated method stub
        Log.e(TAG, "onTouchEvent start");
        if (mVelocityTracker == null) {

            Log.e(TAG, "onTouchEvent start-------** VelocityTracker.obtain");

            mVelocityTracker = VelocityTracker.obtain();
        }

        mVelocityTracker.addMovement(event);
        super.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(mScroller != null){
                    if(!mScroller.isFinished()){
                        mScroller.abortAnimation();
                    }
                }

                mLastionMotionX = x ;
                break ;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int)(mLastionMotionX - x );
                int deltaY = (int)(mLastMotionY - y);

                if (Math.abs(deltaX) < Math.abs(deltaY)) {
                    break;
                }
              scrollBy(deltaX, 0);
                //自己的方法
                //     smoothScrollBy(deltaX, 0);

                Log.e(TAG, "--- MotionEvent.ACTION_MOVE--> detaX is " + deltaX );
                mLastionMotionX = x ;
                break ;
            case MotionEvent.ACTION_UP:
                final VelocityTracker velocityTracker = mVelocityTracker  ;
                velocityTracker.computeCurrentVelocity(1000);
                int velocityX = (int) velocityTracker.getXVelocity() ;

                Log.e(TAG , "---velocityX---" + velocityX);

                if (velocityX > SNAP_VELOCITY && curScreen > 0) {
                    // Fling enough to move left
                    Log.e(TAG, "snap left");
                    snapToScreen(curScreen - 1);
                }
                else if(velocityX < -SNAP_VELOCITY && curScreen < (getChildCount()-1)){
                    Log.e(TAG, "snap right");
                    snapToScreen(curScreen + 1);
                }
                else{
                    snapToDestination();
                }

                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                mTouchState = TOUCH_STATE_REST ;
                break;
            case MotionEvent.ACTION_CANCEL:
                mTouchState = TOUCH_STATE_REST ;
                break;
        }
        return true ;
    }

    private void snapToDestination(){
        int scrollX = getScrollX() ;
        int scrollY = getScrollY() ;
        Log.e(TAG, "### onTouchEvent snapToDestination ### scrollX is " + scrollX);
        int destScreen = (getScrollX() + getWidth() / 2 ) / getWidth() ;
        Log.e(TAG, "### onTouchEvent  ACTION_UP### dx destScreen " + destScreen);
        snapToScreen(destScreen);
    }


    private void snapToScreen(int whichScreen){
        curScreen = whichScreen ;
        if(curScreen > getChildCount() - 1)
            curScreen = getChildCount() - 1 ;

        int dx = curScreen * getWidth() - getScrollX() ;

        Log.e(TAG, "### onTouchEvent  ACTION_UP### dx is " + dx);

     //   mScroller.startScroll(getScrollX(), 0, dx, 0, Math.abs(dx) * 2);
        mScroller.startScroll(getScrollX(), 0, dx, 0);
        invalidate();

        //自己的方法
      //  smoothScrollBy(dx, 0);
    }




    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Log.e(TAG, "onInterceptTouchEvent-slop:" + mTouchSlop);

        final int action = ev.getAction();

        if ((action == MotionEvent.ACTION_MOVE) && (mTouchState != TOUCH_STATE_REST)) {
            return true;
        }

        final float x = ev.getX();
        final float y = ev.getY();

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onInterceptTouchEvent move");
                final int xDiff = (int) Math.abs(mLastionMotionX - x);
                if (xDiff > mTouchSlop) {
                    mTouchState = TOUCH_STATE_SCROLLING;
                }
                break;

            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onInterceptTouchEvent down");
                mLastionMotionX = x;
                mLastMotionY = y;
                Log.e(TAG, "scroller is finished" + mScroller.isFinished() + "");
                mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST : TOUCH_STATE_SCROLLING;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onInterceptTouchEvent up or cancel");
                mTouchState = TOUCH_STATE_REST;
                break;
        }
        Log.e(TAG, mTouchState + "====" + TOUCH_STATE_REST);

        return mTouchState != TOUCH_STATE_REST;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.i(TAG, "--- start onMeasure --");

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);

        int childCount = getChildCount();
        Log.i(TAG, "--- onMeasure childCount is -->" + childCount);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.measure(getWidth(), MultiScreenActivity.mScrrenHeight);
        }
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
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        Log.i(TAG, "--- start onLayout --");
        int startLeft = 0;
        int startTop = 10;
        int childCount = getChildCount();
        Log.i(TAG, "--- onLayout childCount is -->" + childCount );

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            if(child.getVisibility() != View.GONE)
                Log.i(TAG, "--- start onLayout -- getWidth()="+ getWidth());
            Log.i(TAG, "--- start onLayout -- child.getWidth()="+ child.getWidth());
            child.layout(startLeft, startTop,
                    startLeft + getWidth(),
                    startTop + MultiScreenActivity.mScrrenHeight);

            startLeft = startLeft + getWidth() ;
        }
    }
}
