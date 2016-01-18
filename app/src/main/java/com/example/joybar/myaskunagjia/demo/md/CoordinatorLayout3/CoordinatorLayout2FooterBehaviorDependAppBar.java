package com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout3;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by joybar on 15/11/30.
 */
public class CoordinatorLayout2FooterBehaviorDependAppBar extends CoordinatorLayout.Behavior<View> {

//
//    public FooterBehaviorDependAppBar(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }


    public CoordinatorLayout2FooterBehaviorDependAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTranslationY());
        child.setTranslationY(translationY);
        return true;
    }
}