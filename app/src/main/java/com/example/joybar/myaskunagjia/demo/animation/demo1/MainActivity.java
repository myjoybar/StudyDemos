package com.example.joybar.myaskunagjia.demo.animation.demo1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.utils.ScreenUtils;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
     private Button btn,btn2,btn3,btn4,btn5,btn6,btn7;
    // private TextView tv_register, tv_reget_pwd;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani1);
        initView();
        initData();
        setLinstener();
        fillData();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                String s1 = btn2.getLeft()+"";
                String s2 = btn2.getTranslationX()+"";
                String s3 = btn2.getX()+"";

               T.showLong(MainActivity.this,"getLeft="+s1+"getTranslationX="+s2+"getX="+s3);
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
        btn2 = fvById(R.id.btn2);
        btn = fvById(R.id.btn);

        btn3 = fvById(R.id.btn3);
        btn4 = fvById(R.id.btn4);
        btn5 = fvById(R.id.btn5);
        btn6 = fvById(R.id.btn6);
        btn7 = fvById(R.id.btn7);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {
        btn.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
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
           case R.id.btn:
               ViewWrapper wrapper = new ViewWrapper(btn6);
               ObjectAnimator.ofInt(wrapper, "width", btn6.getWidth(),600).setDuration(5000).start();
               break;
            case R.id.btn3:
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn2, "translationX", 0, ScreenUtils.getScreenWidth(MainActivity.this)-btn2.getLeft()-btn2.getWidth());
                animator.setDuration(5000);
                animator.start();
                //和上面一样的效果
             //   ViewHelper.setTranslationX(btn2,ScreenUtils.getScreenWidth(MainActivity.this)-btn2.getLeft()-btn2.getWidth());
                break;
            case R.id.btn4:

                ViewHelper.setX(btn2, 180);

                break;

            case R.id.btn5:

                T.showLong(mContext, btn2.getScrollX() + "");
                btn2.scrollTo(-180, 0);//和  ViewHelper.setScrollX(btn2, -180)效果一样

                break;

            case R.id.btn6:
                btn2.scrollTo(0,0);
                T.showLong(mContext, btn2.getScrollX() + "");
                break;

            case R.id.btn7:
                btn2.scrollBy(-180, 0);
                break;
            default:
                break;
        }


    }




}

