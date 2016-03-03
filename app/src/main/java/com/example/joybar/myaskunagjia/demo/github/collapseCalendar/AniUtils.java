package com.example.joybar.myaskunagjia.demo.github.collapseCalendar;

import android.widget.LinearLayout;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by joybar on 1/19/16.
 */
public class AniUtils {

    public static int DURATION_LONG = 200;
    public static int DURATION_SHORT = 250;

    public static  void up(LinearLayout lvCalendar,int distance1,LinearLayout linBottom,int distance2){

        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(lvCalendar,
                "translationY", 0, -distance1);
        fViewTransYAnim.setDuration(DURATION_SHORT);
        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(linBottom,
                "translationY", 0,  -distance2);
        sViewTransYAnim.setDuration(DURATION_SHORT);

        AnimatorSet showAnim = new AnimatorSet();
        showAnim.playTogether(fViewTransYAnim, sViewTransYAnim);

        showAnim.start();
    }

    public static  void up(LinearLayout linBottom,int distance){


        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(linBottom,
                "translationY", 0,  -distance);
        sViewTransYAnim.setDuration(DURATION_SHORT);


        sViewTransYAnim.start();
    }
}
