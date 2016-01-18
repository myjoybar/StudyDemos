package com.example.joybar.myaskunagjia.demo.gesture.gesture4;

import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // private Button btn_login;
    // private TextView tv_register, tv_reget_pwd;

     LinearLayout lin_menu;
      private FrameLayout.LayoutParams lp;

    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    private int max_menu_margin = 0;
    private int min_menu_margin;

    private float beginX;
    private float latestX;
    private float diffX;


    private final static int SHOW_MENU = 1;
    private final static int HIDE_MENU = -1;
    private int swipe_tag = SHOW_MENU;

    private float latestMargin;

    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slide);
        initView();
        initData();
        setLinstener();
        fillData();
    }

    @Override
    protected void onStart() {
        L.i(TAG, "onStart");
        super.onStart();
    }
    @Override
    protected void onRestart() {
        L.i(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        L.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        L.i(TAG, "onPause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        L.i(TAG, "onStop");
        super.onStop();

    }
    @Override
    protected void onDestroy() {
        L.i(TAG, "onDestroy");
        super.onDestroy();
    }

    //初始化部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    @Override
    protected void initView() {
        lin_menu = fvById(R.id.menu);
        // tv_register = fvById(R.id.tv_register);

    }

    @Override
    protected void initData() {
        lp = (FrameLayout.LayoutParams) lin_menu.getLayoutParams();
        min_menu_margin = lp.leftMargin;

    }

    @Override
    protected void setLinstener() {
        lin_menu.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int action = MotionEventCompat.getActionMasked(event);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        beginX = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        latestX = event.getX();
                        diffX = latestX - beginX;
                        swipe_tag = diffX > 0 ? SHOW_MENU : HIDE_MENU;
                        latestMargin = lp.leftMargin + diffX;

                        if (latestMargin > min_menu_margin
                                && latestMargin < max_menu_margin) {
                            lp.leftMargin = (int) (latestMargin);
                            lin_menu.setLayoutParams(lp);
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        TranslateAnimation tAnim;
                        if (swipe_tag == SHOW_MENU) {
                            tAnim = new TranslateAnimation(0, max_menu_margin
                                    - latestMargin, 0, 0);
                            tAnim.setInterpolator(new DecelerateInterpolator());
                            tAnim.setDuration(250);
                            lin_menu.startAnimation(tAnim);
                        } else {
                            tAnim = new TranslateAnimation(0, min_menu_margin
                                    - latestMargin, 0, 0);
                            tAnim.setDuration(250);
                            lin_menu.startAnimation(tAnim);
                        }
                        //在动画结束的时刻，移动menu的位置，使menu真正移动。
                        tAnim.setAnimationListener(new Animation.AnimationListener() {

                            @Override
                            public void onAnimationStart(Animation animation) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                // TODO Auto-generated method stub
                                if (swipe_tag == SHOW_MENU) {
                                    lp.leftMargin = max_menu_margin;
                                    lin_menu.setLayoutParams(lp);
                                } else {
                                    lp.leftMargin = min_menu_margin;
                                    lin_menu.setLayoutParams(lp);
                                }
                                lin_menu.clearAnimation();
                            }
                        });

                        break;
                }
                return true;
            }
        });


    }

    @Override
    protected void fillData() {
        // TODO Auto-generated method stub

    }

    //网络请求部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //自定义方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //回调方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // case R.id.btn_login:

            // break;

            default:
                break;
        }

    }




}

