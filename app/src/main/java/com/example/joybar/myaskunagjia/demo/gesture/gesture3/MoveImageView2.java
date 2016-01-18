package com.example.joybar.myaskunagjia.demo.gesture.gesture3;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.utils.ScreenUtils;

/**
 * Created by joybar on 15/12/3.
 */
public class MoveImageView2 extends ImageView {

    private static  String  TAG = "MoveImageView2";
    private int screenW;        //屏幕宽度
    private int screenH;        //屏幕高度
    private int statusH;        //状态栏高度
    private int navigationH;        //导航栏高度
    private int clickX;
    private int clickY;


    private int topTitleHeight;
    public MoveImageView2(Context context) {
        super(context);
        // 获取屏幕宽度

    }

    public MoveImageView2(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

    }

    public MoveImageView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        // 获取屏幕宽度
//        screenW = ScreenUtils.getScreenWidth(context);
//        // 获取屏幕高度
//        screenH = ScreenUtils.getScreenHeight(context);
     //   statusH = ScreenUtils.getStatusHeight(context);

    }


    public void setStatusH(int statusH){
        this.statusH = statusH;
    }

    public void setScreenH(int screenH){
        this.screenH = screenH;
    }
    public void setScreenW(int screenW){
        this.screenW = screenW;
    }
    public void setavigation(int navigationH){
        this.navigationH = navigationH;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int[] locations = new int[2];
                this.getLocationInWindow(locations);
                topTitleHeight = locations[1];
//                L.i(TAG,"X="+locations[0]+"");
//                L.i(TAG,"Y="+topTitleHeight+"");
                clickX = (int)event.getX();
                clickY = (int)event.getY();
                L.i(TAG,"clickX="+event.getX());
                L.i(TAG, "clickY=" + event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                moveViewByLayout(this, (int) event.getRawX(),
                        (int) event.getRawY());

                L.i(TAG,"MOVE_RX="+event.getRawX());
                L.i(TAG, "MOVE_RY=" + event.getRawY());

                break;
            case MotionEvent.ACTION_UP:
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


    //没用
    /**
     * 通过layout方法，移动view
     * 优点：对view所在的布局，要求不苛刻，不要是RelativeLayout，而且可以修改view的大小
     * 并且，layout方法可以修改view的大小。
     * @param view
     * @param rawX
     * @param rawY
     * @param scale
     */
    private void moveViewByLayout(View view, int rawX, int rawY, int scale) {
        int left = rawX - this.getWidth() / 2;
        int top = rawY - topTitleHeight - this.getHeight() / 2;
        int width = left + (int) (view.getWidth() + scale);
        int height = top + (int) (view.getHeight() + scale);
        view.layout(left, top, width, height);
    }

    /**
     * 通过layout方法，移动view
     * 优点：对view所在的布局，要求不苛刻，不要是RelativeLayout，而且可以修改view的大小
     *
     * @param view
     * @param rawX
     * @param rawY
     */
    private void moveViewByLayout(View view, int rawX, int rawY) {
//        int left = rawX - this.getWidth() / 2;
//        int top = rawY - topTitleHeight - this.getHeight() / 2;
//        int width = left + view.getWidth();
//        int height = top + view.getHeight();

     //   int left = rawX - this.getWidth() / 2;

        int left = rawX -clickX;
        if(left<0){
            left =0;
        }
     //   int top = rawY- this.getHeight() ;
        //不明白为什么要减去this.getHeight()/2
        int top = rawY- clickY - statusH;
        L.i(TAG, "statusH=" +statusH);
        L.i(TAG, "this.getHeight()=" +this.getHeight());
        if(top<0){
            top =0;
        }
        int Right = left + view.getWidth();
        if(screenW!=0&&Right>screenW){
          //  Right =screenW;
            L.i(TAG, "AAAAARight=" +Right);
            L.i(TAG, "AAAAAAAscreenW=" +screenW);
            return;
        }
       int Bottom = top + view.getHeight();

       if(screenH!=0&&Bottom>screenH+statusH+navigationH+view.getHeight()){

         //   Bottom =screenH;
            return;
        }


        view.layout(left, top, Right, Bottom);
    }

}