package com.example.joybar.myaskunagjia.demo.github.collapseCalendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.utils.ScreenUtils;

/**
 * Created by joybar on 1/19/16.
 */
public class CollapseViewGroup2 extends RelativeLayout implements View.OnTouchListener, View.OnClickListener {

    private static String TAG = "CollapseViewGroup";
    private Context mContext;
    private Scroller mScroller;

    private int mScreenHeigh = 0;
    private int mScreenWidth = 0;
    private float downY = 0;
    private float moveY = 0;
    private float scrollY = 0;
    private int upY = 0;

    private LinearLayout mLinCalendar;
    private LinearLayout mLinBottom;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private boolean isMove = false;//是否在移动
    private boolean isOpen = true;//是否展开

    private int mCalendarHeight = 0;
    private int mMoveHeight = 0;
    private int mItemHeight;
    private int clickIndex = 0;//从0开始
    private final static int MOVE_TIME_DURATION = 200;

    private onCollapseListener onCollapseListener;

    public void setCollapseListener(onCollapseListener onCollapseListener) {
        this.onCollapseListener = onCollapseListener;
    }

    public CollapseViewGroup2(Context context) {
        super(context);
        init(context);
    }

    public CollapseViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CollapseViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mScroller = new Scroller(context);
        mScreenHeigh = ScreenUtils.getScreenHeight(context);
        mScreenWidth = ScreenUtils.getScreenWidth(context);
        this.setBackgroundColor(Color.argb(0, 0, 0, 0));
        //innit view
        final View view = LayoutInflater.from(mContext).inflate(R.layout.activity_main_calendar_collapse2, null);
        mLinCalendar = (LinearLayout) view.findViewById(R.id.lin_calendar);
        mLinBottom = (LinearLayout) view.findViewById(R.id.lin_bottom);
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);
        addView(view);


        mLinCalendar.post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mCalendarHeight = mLinCalendar.getHeight();
                mItemHeight = btn1.getHeight();
                mMoveHeight = mItemHeight * clickIndex;
                Log.d(TAG, "mCalendarHeight= " + mCalendarHeight);
                //  CurtainView.this.scrollTo(0, curtainHeigh);

            }
        });
//        btn1.setOnTouchListener(this);
//        btn2.setOnTouchListener(this);
//        btn3.setOnTouchListener(this);
//        btn4.setOnTouchListener(this);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        mLinCalendar.setOnTouchListener(this);
        mLinBottom.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!isMove) {
            int offViewY = 0;//屏幕顶部和该布局顶部的距离
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downY = (int) event.getRawY();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    moveY = (int) event.getRawY();
                    scrollY = moveY - downY;

//                    if (mSelectView.getTop() > 0) {
//                        if (mLinBottom.getHeight() + mSelectView.getTop() + mSelectView.getHeight() < ScreenUtils.getScreenHeight(getContext())) {
//                            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLinBottom.getLayoutParams();
//                            float a = moveY / downY;
//                            params.topMargin = (int) (0 - Math.abs(moveY * (1f - a)));
//                            mLinBottom.requestLayout();
//                        }
//                        float a = moveY / downY;
//                        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) mLinCalendar.getLayoutParams();
//                        params1.topMargin = (int) (0 - Math.abs(mLinCalendar.getHeight() * (1f - a)));
//                        mLinCalendar.requestLayout();
//                    } else {
//                        // no
//                    }

                    if (scrollY < 0) {
                        // 向上滑动
                        if (isOpen) {
                            if (Math.abs(scrollY) <= mMoveHeight) {
                                scrollTo(0, -(int)scrollY);
                            } else {
                                //动画
                                //  AniUtils.up(mLinBottom,mItemHeight*3-mMoveHeight);
                              //  MarginLayoutParams params = (MarginLayoutParams) mLinBottom.getLayoutParams();
                              //  params.height = tempH + Math.abs(scrollY);
                            //    mLinBottom.requestLayout();

                            }
                            if (Math.abs(scrollY) >= mMoveHeight) {
                                //  onCollapseListener.onCollapse();
                            }
                        }
//
                        // AniUtils.up(mLinCalendar,mItemHeight,mLinBottom,mItemHeight*3);


                    } else {
                        // 向下滑动
                        if (!isOpen) {
                            if (scrollY <= mMoveHeight) {
                                scrollTo(0, (int)(mMoveHeight - scrollY));
                            }
                        }

                    }
                    break;
                case MotionEvent.ACTION_UP:
                    upY = (int) event.getRawY();
                    if (downY > upY) {
                        // 向上滑动
                        if (isOpen) {
                            if (Math.abs(scrollY) > mMoveHeight / 2) {
                                startMoveAnim(this.getScrollY(),
                                        (mMoveHeight - this.getScrollY()), MOVE_TIME_DURATION);
                                isOpen = false;
                            } else {
                                startMoveAnim(this.getScrollY(), -this.getScrollY(), MOVE_TIME_DURATION);
                                isOpen = true;
                            }
                        }
                    } else {
                        // 向下滑动

                        if (scrollY > mMoveHeight / 2) {
                            startMoveAnim(this.getScrollY(), -this.getScrollY(), MOVE_TIME_DURATION);
                            isOpen = true;
                        } else {
                            startMoveAnim(this.getScrollY(), (mMoveHeight - this.getScrollY()), MOVE_TIME_DURATION);
                            isOpen = false;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    /**
     * 拖动动画
     *
     * @param startY
     * @param dy       垂直距离, 滚动的y距离
     * @param duration 时间
     */
    public void startMoveAnim(int startY, int dy, int duration) {
        isMove = true;
        mScroller.startScroll(0, startY, 0, dy, duration);
        invalidate();//通知UI线程的更新
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void computeScroll() {
        //判断是否还在滚动，还在滚动为true
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //更新界面
            postInvalidate();
            isMove = true;
        } else {
            isMove = false;
        }
        super.computeScroll();
    }


//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        switch (action)
//        {
//            case MotionEvent.ACTION_DOWN:
//                return false ;
//            case MotionEvent.ACTION_MOVE:
//                return true ;
//            case MotionEvent.ACTION_UP:
//                return false ;
//        }
//
//        return false;
//    }

    private View mSelectView;

    @Override
    public void onClick(View v) {
        mSelectView = v;
        switch (v.getId()) {
            case R.id.btn1:
                clickIndex = 0;
                mMoveHeight = clickIndex * mItemHeight;
                T.showShort(mContext, mMoveHeight + "");
                break;
            case R.id.btn2:
                clickIndex = 1;
                mMoveHeight = clickIndex * mItemHeight;
                T.showShort(mContext, mMoveHeight + "");
                break;
            case R.id.btn3:
                clickIndex = 2;
                mMoveHeight = clickIndex * mItemHeight;
                T.showShort(mContext, mMoveHeight + "");
                break;
            case R.id.btn4:
                clickIndex = 3;
                mMoveHeight = clickIndex * mItemHeight;
                T.showShort(mContext, mMoveHeight + "");
                break;

            default:
                break;
        }
    }

    public interface onCollapseListener {
        public void onCollapse();
    }
}
