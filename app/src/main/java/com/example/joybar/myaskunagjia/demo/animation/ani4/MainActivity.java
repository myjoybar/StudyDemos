package com.example.joybar.myaskunagjia.demo.animation.ani4;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public static final int DURATION = 200;
    public static final int MIDDLE = 3;


    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    private Button btn_show,btn_hide,btn_show2,btn_hide2;
    // private TextView tv_register, tv_reget_pwd;

    LinearLayout lin_parent,lin_parent2;
    ImageView imv_0;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

//http://blog.csdn.net/tiankong1206/article/details/44947495
    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani4);
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
        lin_parent = fvById(R.id.lin_parent);
        imv_0 = fvById(R.id.imv_0);
        btn_show = fvById(R.id.btn_show);
        btn_hide = fvById(R.id.btn_hide);

        lin_parent2 = fvById(R.id.lin_parent2);
        btn_show2 = fvById(R.id.btn_show2);

        btn_hide2 = fvById(R.id.btn_hide2);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {
        btn_show.setOnClickListener(this);
        btn_show2.setOnClickListener(this);
        btn_hide.setOnClickListener(this);
        btn_hide2.setOnClickListener(this);

        for (int i = 0; i < lin_parent.getChildCount(); i++) {
//            position = i;
//            lin_parent.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                 //   initHiddenAnim(lin_parent,position).start();//隐藏动画执行
//                    T.showShort(MainActivity.this,position+"");
//                }
//            });

            addChildrenListener(lin_parent.getChildAt(i), i);
        }

    }


    public void addChildrenListener(View view,final  int pos){

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                  initHiddenAnim(lin_parent,pos).start();//隐藏动画执行
                // T.showShort(MainActivity.this, pos + "");
            }
        });
    }
    @Override
    protected void fillData() {
        // TODO Auto-generated method stub

    }

    //网络请求部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //自定义方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //Y show 打开-第一个
    private ObjectAnimator createFlipYShowAnim(final View target) {
        target.setPivotX(0);
        target.setPivotY(0);
        //  target.setPivotY(target.getHeight() / 2);
        ObjectAnimator invisToVis = ObjectAnimator.ofFloat(target, "rotationY", 90f, 0f);
        invisToVis.setDuration(DURATION);
        invisToVis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                target.setVisibility(View.VISIBLE);
            }
        });
        return invisToVis;
    }

    //x show 打开-下半部(第一个之外的)
    private ObjectAnimator createFlipXShowAnim(final View target) {
        target.setPivotX(target.getWidth() / 2);
        target.setPivotY(0);
        ObjectAnimator invisToVis = ObjectAnimator.ofFloat(target, "rotationX", -90f, 0f);
        invisToVis.setDuration(DURATION);
        invisToVis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                target.setVisibility(View.VISIBLE);
            }
        });
        return invisToVis;
    }



    //x hidden before 隐藏-上半部
    private ObjectAnimator createFlipXBeforeHiddenAnim(final View target, int i) {
        target.setPivotX(target.getWidth() / 2);
        target.setPivotY(target.getHeight());
        ObjectAnimator visToInvis = ObjectAnimator.ofFloat(target, "rotationX", 0f, 90f);
        if (i == 0) {
            visToInvis.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //播放完后恢复位置
                    target.setVisibility(View.INVISIBLE);
                    target.setPivotX(target.getWidth() / 2);
                    target.setPivotY(target.getHeight());
                    ObjectAnimator openAnim = ObjectAnimator.ofFloat(target, "rotationX", 90f, 0f);
                    openAnim.start();
                }
            });
        }
        visToInvis.setDuration(DURATION);
        return visToInvis;
    }

    //x hidden after 隐藏-下半部
    private ObjectAnimator createFlipXAfterHiddenAnim(View target) {
        target.setPivotX(target.getWidth() / 2);
        target.setPivotY(0);
        ObjectAnimator visToInvis = ObjectAnimator.ofFloat(target, "rotationX", 0f, -90f);
        visToInvis.setDuration(DURATION);
        return visToInvis;
    }

    //Y hidden 隐藏-中间(点击部分)
    private ObjectAnimator createFlipYHiddenAnim(final View target) {
        target.setPivotX(0);
        target.setPivotY(target.getHeight() / 2);
        ObjectAnimator visToInvis = ObjectAnimator.ofFloat(target, "rotationY", 0f, 90f);
        visToInvis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                target.setVisibility(View.INVISIBLE);
                target.setPivotX(0);
                target.setPivotY(target.getHeight() / 2);
                ObjectAnimator openAnim = ObjectAnimator.ofFloat(target, "rotationY", 90f, 0f);
                openAnim.start();
            }
        });
        visToInvis.setDuration(DURATION);
        return visToInvis;
    }

    private Animator initHiddenAnim(ViewGroup parent) {
        AnimatorSet beforAnim = new AnimatorSet();
        AnimatorSet middleAnim = new AnimatorSet();
        AnimatorSet afterAnim = new AnimatorSet();
        ArrayList<Animator> beforeAnimList = new ArrayList<Animator>();
        ArrayList<Animator> middleAnimList = new ArrayList<Animator>();
        ArrayList<Animator> afterAnimList = new ArrayList<Animator>();
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (i < MIDDLE) {
                ObjectAnimator itemAnim = createFlipXBeforeHiddenAnim(parent.getChildAt(i), i);
                beforeAnimList.add(itemAnim);
            } else if (i > MIDDLE) {
                ObjectAnimator itemAnim = createFlipXAfterHiddenAnim(parent.getChildAt(i));
                afterAnimList.add(itemAnim);
            } else {
                ObjectAnimator itemAnim = createFlipYHiddenAnim(parent.getChildAt(i));
                middleAnimList.add(itemAnim);
            }
        }
        beforAnim.playSequentially(beforeAnimList);
        middleAnim.playSequentially(middleAnimList);
        Collections.reverse(afterAnimList);
        afterAnim.playSequentially(afterAnimList);
        AnimatorSet anim = new AnimatorSet();
        anim.play(beforAnim).with(afterAnim).before(middleAnim);
        return anim;
    }



    private Animator initHiddenAnim(ViewGroup parent,int position) {
      //  T.showShort(MainActivity.this,position+"");
        AnimatorSet beforAnim = new AnimatorSet();
     final    AnimatorSet middleAnim = new AnimatorSet();
        AnimatorSet afterAnim = new AnimatorSet();
        ArrayList<Animator> beforeAnimList = new ArrayList<Animator>();
        ArrayList<Animator> middleAnimList = new ArrayList<Animator>();
        ArrayList<Animator> afterAnimList = new ArrayList<Animator>();
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (i < position) {
                ObjectAnimator itemAnim = createFlipXBeforeHiddenAnim(parent.getChildAt(i), i);
                beforeAnimList.add(itemAnim);
            } else if (i > position) {
                ObjectAnimator itemAnim = createFlipXAfterHiddenAnim(parent.getChildAt(i));
                afterAnimList.add(itemAnim);
            } else {
                ObjectAnimator itemAnim = createFlipYHiddenAnim(parent.getChildAt(i));
                middleAnimList.add(itemAnim);
            }
        }
        beforAnim.playSequentially(beforeAnimList);
        middleAnim.playSequentially(middleAnimList);
       Collections.reverse(afterAnimList);
        afterAnim.playSequentially(afterAnimList);
        AnimatorSet anim = new AnimatorSet();

      //  anim.play(beforAnim).with(afterAnim).before(middleAnim);
        anim.play(beforAnim).with(afterAnim);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                middleAnim.start();
            }
        });
        return anim;
    }




    //回调方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                //     createFlipYShowAnim(lin_parent.getChildAt(0)).start();
                AnimatorSet showAnim = new AnimatorSet();
                List<Animator> list = new ArrayList<Animator>();
                for (int i = 0; i < lin_parent.getChildCount(); i++) {
                    if (i == 0) {
                        list.add(createFlipYShowAnim(lin_parent.getChildAt(0)));
                    }else{
                        list.add(createFlipXShowAnim(lin_parent.getChildAt(i)));
                    }


                }
                showAnim.playSequentially(list);
                showAnim.start();
                break;

            case R.id.btn_hide:
                initHiddenAnim(lin_parent).start();//隐藏动画执行
                break;
            case R.id.btn_show2:

                ArrayList<Animator> visAnimList = new ArrayList<Animator>();
                for (int i = 0; i < lin_parent2.getChildCount(); i++) {
                    visAnimList.add(createItemVisAnim(lin_parent2.getChildAt(i),i));
                }
                AnimatorSet  visAnimSet = new AnimatorSet();
                //   visAnimSet.playSequentially(visAnimList);
                visAnimSet.playTogether(visAnimList);
                visAnimSet.start();


                break;
            case R.id.btn_hide2:


                ArrayList<Animator> visAnimList2 = new ArrayList<Animator>();
                for (int i = 0; i < lin_parent2.getChildCount(); i++) {
                    visAnimList2.add(createItemInvisAnim(lin_parent2.getChildAt(i), i));
                }
                AnimatorSet  visAnimSet2 = new AnimatorSet();
                //    visAnimSet2.playSequentially(visAnimList2);
                visAnimSet2.playTogether(visAnimList2);
                visAnimSet2.start();


                break;
            default:
                break;
        }

    }

    //右边的动画


    //显示动画
    private ObjectAnimator createItemVisAnim(final View target, int index) {
       // target.setPivotX(0);
        target.setPivotX(target.getWidth());
        target.setPivotY(target.getHeight() / 2);
        ObjectAnimator invisToVis = ObjectAnimator.ofFloat(target, "rotationY", -90f, 0f);
//        int  MAX_DURATION = 150;
//      int  per_duration=MAX_DURATION/lin_parent2.getChildCount();
//       invisToVis.setDuration(MAX_DURATION-per_duration* index);
      //  invisToVis.setDuration(250-index*50);
        invisToVis.setDuration(250);
        invisToVis.setStartDelay(index*80);
        invisToVis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                target.setVisibility(View.VISIBLE);
            }
        });
        return invisToVis;
    }



    //隐藏动画
    private ObjectAnimator createItemInvisAnim(final View target, int index) {
      //  target.setPivotX(0);
        target.setPivotX(target.getWidth());
        target.setPivotY(target.getHeight() / 2);
        ObjectAnimator visToInvis = ObjectAnimator.ofFloat(target, "rotationY", 0f, -90f);
//        int  MAX_DURATION = 150;
//        int per_duration = MAX_DURATION/lin_parent2.getChildCount();
//        visToInvis.setDuration(MAX_DURATION+per_duration* index);
        visToInvis.setDuration(250);
        visToInvis.setStartDelay(index*80);
        return visToInvis;
    }


}

