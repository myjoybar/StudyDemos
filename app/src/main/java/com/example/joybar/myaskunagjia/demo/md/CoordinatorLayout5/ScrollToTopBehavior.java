package com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout5;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by joybar on 15/12/3.
 */
public class ScrollToTopBehavior extends CoordinatorLayout.Behavior<View>{

    public ScrollToTopBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        //offset(child, dyConsumed);
    }

}
