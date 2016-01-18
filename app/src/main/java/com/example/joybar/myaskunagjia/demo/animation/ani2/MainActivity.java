package com.example.joybar.myaskunagjia.demo.animation.ani2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.utils.ScreenUtils;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

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

    private   LinearLayout firstView,secondView;
    private Button btn_anim3_show,btn_anim3_hidden;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani2);
        initView();
        initData();
        setLinstener();
        fillData();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                String s1 = secondView.getTop() + "";
                String s2 = secondView.getTranslationY() + "";
                String s3 = secondView.getY() + "";

                String s4 = firstView.getHeight() + "";

                String s5 =  ScreenUtils.getScreenHeight(MainActivity.this)+"";



                T.showLong(MainActivity.this, "getLeft=" + s1 + "getTranslationX=" + s2 + "getX=" + s3
                +"firstView.getHeight()="+ s4
                        +"getScreenWidth()="+ s5);
                //    T.showLong(MainActivity.this,s1+s2+s3);
            }
        }, 1000);
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
        firstView = fvById(R.id.first_view);
        secondView = fvById(R.id.second_view);
        btn_anim3_show = fvById(R.id.btn_anim3_show);
        btn_anim3_hidden = fvById(R.id.btn_anim3_hidden);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {
        btn_anim3_show.setOnClickListener(this);
        btn_anim3_hidden.setOnClickListener(this);

    }

    @Override
    protected void fillData() {
        // TODO Auto-generated method stub

    }

    //网络请求部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //自定义方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    private void initShowAnim(){
        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(firstView,"scaleX",1.0f,0.8f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(firstView,"scaleY",1.0f,0.8f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(firstView,"alpha",1.0f,0.5f);
        fViewAlphaAnim.setDuration(350);


        //旋转动画--关键
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(150);
        fViewResumeAnim.setStartDelay(200);




        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(firstView, "translationY", 0, -0.1f* firstView.getHeight());
        fViewTransYAnim.setDuration(350);

        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(secondView,"translationY",secondView.getHeight(),0);
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                secondView.setVisibility(View.VISIBLE);
            }
        });

        AnimatorSet  showAnim=new AnimatorSet();
        showAnim.playTogether(fViewScaleXAnim,fViewRotationXAnim,fViewResumeAnim,fViewTransYAnim,fViewAlphaAnim,fViewScaleYAnim,sViewTransYAnim);

        showAnim.start();

    }


    private void initHideAnim(){
        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(firstView,"scaleX",0.8f,1.0f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(firstView,"scaleY",0.8f,1.0f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(firstView,"alpha",0.5f,1.0f);
        fViewAlphaAnim.setDuration(350);


       //旋转动画--关键
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(150);
        fViewResumeAnim.setStartDelay(200);



        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(firstView, "translationY", -0.1f* firstView.getHeight(),0);
        fViewTransYAnim.setDuration(350);

        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(secondView,"translationY",0,secondView.getHeight());
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationStart(animation);
                secondView.setVisibility(View.INVISIBLE);
            }
        });

        AnimatorSet  showAnim=new AnimatorSet();
        showAnim.playTogether(fViewScaleXAnim,fViewRotationXAnim,fViewResumeAnim,fViewTransYAnim,fViewAlphaAnim,fViewScaleYAnim,sViewTransYAnim);

        showAnim.start();

    }






    //回调方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.btn_anim3_show:
               initShowAnim();
               new Handler().postDelayed(new Runnable() {

                   @Override
                   public void run() {
                       T.showLong(MainActivity.this, firstView.getTranslationY() + "");
                   }
               }, 1000);

               break;
            case R.id.btn_anim3_hidden:
                initHideAnim();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        T.showLong(MainActivity.this, firstView.getTranslationY() + "");
                    }
                }, 1000);
                break;
            default:
                break;
        }

    }




}

