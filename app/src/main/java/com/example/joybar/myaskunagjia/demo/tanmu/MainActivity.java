package com.example.joybar.myaskunagjia.demo.tanmu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.utils.ScreenUtils;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    private MyHandler handler;

    //弹幕内容
    private TanmuBean tanmuBean;
    //放置弹幕内容的父组件
    private RelativeLayout containerVG;

    //父组件的高度
    private int validHeightSpace;



    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // private Button btn_login;
   private TextView tv;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanmu);
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
        // tv = fvById(R.id.btn_login);

        tv = (TextView) this.findViewById(R.id.tv);
        containerVG = (RelativeLayout) findViewById(R.id.tanmu_container);
    }

    @Override
    protected void initData() {
        tanmuBean = new TanmuBean();
        tanmuBean.setItems(new String[]{"测试一下", "弹幕这东西真不好做啊", "总是出现各种问题~~", "也不知道都是为什么？麻烦！", "哪位大神可以帮帮我啊？", "I need your help.",
                "测试一下", "弹幕这东西真不好做啊", "总是出现各种问题~~", "也不知道都是为什么？麻烦！", "哪位大神可以帮帮我啊？", "I need your help.",
                "测试一下", "弹幕这东西真不好做啊", "总是出现各种问题~~", "也不知道都是为什么？麻烦！", "哪位大神可以帮帮我啊？", "I need your help."});

        handler = new MyHandler(this);
    }

    @Override
    protected void setLinstener() {
        tv.setOnClickListener(this);

    }

    @Override
    protected void fillData() {
        // TODO Auto-generated method stub

    }

    //网络请求部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //自定义方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //需要在主线城中添加组件
    private static class MyHandler extends Handler {
        private final WeakReference<Activity> activityWeakReference;

        MyHandler(MainActivity activity) {
            activityWeakReference =new WeakReference<Activity>(activity);

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {

                MainActivity ac = (MainActivity)activityWeakReference.get();
                if (ac != null && ac.tanmuBean != null) {
                    int index = msg.arg1;
                    String content = ac.tanmuBean.getItems()[index];
                    float textSize = (float) (ac.tanmuBean.getMinTextSize() * (1 + Math.random() * ac.tanmuBean.getRange()));
                    int textColor = ac.tanmuBean.getColor();


                   ac.showTanmu(content, textSize, textColor);
                }
            }
        }
    }
    //每2s自动添加一条弹幕
    private class CreateTanmuThread implements Runnable {
        @Override
        public void run() {
            int N = tanmuBean.getItems().length;
            for (int i = 0; i < N; i++) {
                handler.obtainMessage(1, i, 0).sendToTarget();
                SystemClock.sleep(2000);
            }
        }
    }

    //记录当前仍在显示状态的弹幕的位置（避免重复）
    private Set existMarginValues = new HashSet<>();
    private int linesCount;
    private int getRandomTopMargin() {
        //计算用于弹幕显示的空间高度
        if (validHeightSpace == 0) {
            validHeightSpace = containerVG.getBottom() - containerVG.getTop()
                    - containerVG.getPaddingTop() - containerVG.getPaddingBottom();
        }

        //计算可用的行数
        if (linesCount == 0) {
            linesCount = validHeightSpace / ScreenUtils.convertDipOrPx(this,(int) tanmuBean.getMinTextSize() * (int)(1 + tanmuBean.getRange()));
            if (linesCount == 0) {
                throw new RuntimeException("Not enough space to show text.");
            }
        }

        //检查重叠
        while (true) {
            int randomIndex = (int) (Math.random() * linesCount);
            int marginValue = randomIndex * (validHeightSpace / linesCount);

            if (!existMarginValues.contains(marginValue)) {
                existMarginValues.add(marginValue);
                return marginValue;
            }
        }
    }


    private void showTanmu(String content, float textSize, int textColor) {
        final TextView textView = new TextView(this);

        textView.setTextSize(textSize);
        textView.setText(content);
//        textView.setSingleLine();
        textView.setTextColor(textColor);

        int leftMargin = containerVG.getRight() - containerVG.getLeft() - containerVG.getPaddingLeft();
        //计算本条弹幕的topMargin(随机值，但是与屏幕中已有的不重复)
        int verticalMargin = getRandomTopMargin();
        textView.setTag(verticalMargin);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.topMargin = verticalMargin;

        textView.setLayoutParams(params);
        Animation anim = AnimationHelper.createTranslateAnim(this, leftMargin, -ScreenUtils.getScreenWidth(this));
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //移除该组件
                containerVG.removeView(textView);
                //移除占位
                int verticalMargin = (int) textView.getTag();
                existMarginValues.remove(verticalMargin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        textView.startAnimation(anim);

        containerVG.addView(textView);
    }
    //回调方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
             case R.id.tv:
                 if (containerVG.getChildCount() > 0) {
                     return;
                 }
                 existMarginValues.clear();
                 new Thread(new CreateTanmuThread()).start();


            break;

            default:
                break;
        }

    }

}

