package com.example.joybar.myaskunagjia.demo.md.RecyclerTabLayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 3/8/16.
 */
public class RecyclerTabLayout extends RecyclerView {

    protected Paint mIndicatorPaint;

    protected LinearLayoutManager mLinearLayoutManager;

    protected int mIndicatorPosition;

    protected DefaultAdapter mAdapter;

    protected RecyclerOnScrollListener mRecyclerOnScrollListener;

    private int mItemWidth;

    public RecyclerTabLayout(Context context) {
        this(context, null);
    }

    public RecyclerTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init(){
        setWillNotDraw(false);

        mIndicatorPaint = new Paint();
        mIndicatorPaint.setColor(Color.BLUE);


        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(mLinearLayoutManager);
        setItemAnimator(null);
        List<Model> listData = new ArrayList<>();
        for(int i = 0;i<20;i++){
            Model model = new Model();
            model.setStr((i+1)+"");
            model.setIsSelceted(false);
            listData.add(model);
        }
        setAdapter(mAdapter = new DefaultAdapter(listData){
            @Override
            public void onBindViewHolder(final ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.getItemView().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mIndicatorPosition = position;
                        T.showShort(v.getContext(), "AAAAAAA" + position);
                     //   mLinearLayoutManager.scrollToPosition(position);
                        mItemWidth = holder.getItemView().getMeasuredWidth();
                        holder.getItemView().setBackgroundColor(Color.parseColor("#CCCCCC"));
                        mLinearLayoutManager.scrollToPositionWithOffset(position, position * holder.getItemView().getMeasuredWidth());
                    }
                });
            }
        });

      //  mRecyclerOnScrollListener = new RecyclerOnScrollListener(this, mLinearLayoutManager);
      //  addOnScrollListener(mRecyclerOnScrollListener);
    }


    @Override
    public void onDraw(Canvas canvas) {

        View view = mLinearLayoutManager.findViewByPosition(mIndicatorPosition);
        if ( null == view ) {
             //  scrollToTab(mAdapter.getCurrentIndicatorPosition());
           // mLinearLayoutManager.scrollToPositionWithOffset(mIndicatorPosition, mIndicatorPosition*mItemWidth);
            L.i("AAAAAAAAAAAAAAAAA"," null == view没有"+mIndicatorPosition  );
            return;
        }
        L.i("AAAAAAAAAAAAAAAAA"," null != view,绘制+"+mIndicatorPosition );

        int left;
        int right;

        left = view.getLeft() ;
        right = view.getRight() ;

        int top = getHeight() - ScreenUtils.convertDipOrPx(getContext(),8);
        int bottom = getHeight();

        canvas.drawRect(left, top, right, bottom, mIndicatorPaint);
    }

//    protected void scrollToTab(int position) {
//        scrollToTab(position, 0, false);
//        mAdapter.setCurrentIndicatorPosition(position);
//        mAdapter.notifyDataSetChanged();
//    }
//
//    protected void scrollToTab(int position, float positionOffset, boolean fitIndicator) {
//        int scrollOffset = 0;
//
//        View selectedView = mLinearLayoutManager.findViewByPosition(position);
//        View nextView = mLinearLayoutManager.findViewByPosition(position + 1);
//
//        if (selectedView != null) {
//            int width = getMeasuredWidth();
//            float scroll1 = width / 2.f - selectedView.getMeasuredWidth() / 2.f;
//
//            if (nextView != null) {
//                float scroll2 = width / 2.f - nextView.getMeasuredWidth() / 2.f;
//
//                float scroll = scroll1 + (selectedView.getMeasuredWidth() - scroll2);
//                float dx = scroll * positionOffset;
//                scrollOffset = (int) (scroll1 - dx);
//
//                mScrollOffset = (int) dx;
//                mIndicatorOffset = (int) ((scroll1 - scroll2) * positionOffset);
//
//            } else {
//                scrollOffset = (int) scroll1;
//                mScrollOffset = 0;
//                mIndicatorOffset = 0;
//            }
//            if (fitIndicator) {
//                mScrollOffset = 0;
//                mIndicatorOffset = 0;
//            }
//
//            if (mAdapter != null && mIndicatorPosition == position) {
//                updateCurrentIndicatorPosition(position, positionOffset - mOldPositionOffset,
//                        positionOffset);
//            }
//
//            mIndicatorPosition = position;
//
//        } else {
//            if (getMeasuredWidth() > 0 && mTabMinWidth == mTabMaxWidth) { //fixed size
//                int width = mTabMinWidth;
//                int offset = (int) (positionOffset * -width);
//                int leftOffset = (int) ((getMeasuredWidth() - width) / 2.f);
//                scrollOffset = offset + leftOffset;
//            }
//            mRequestScrollToTab = true;
//        }
//
//        mLinearLayoutManager.scrollToPositionWithOffset(position, scrollOffset);
//
//        if (mIndicatorHeight > 0) {
//            invalidate();
//        }
//
//        mOldPositionOffset = positionOffset;
//    }



    protected static class RecyclerOnScrollListener extends OnScrollListener {

        protected RecyclerTabLayout mRecyclerTabLayout;
        protected LinearLayoutManager mLinearLayoutManager;

        public RecyclerOnScrollListener(RecyclerTabLayout recyclerTabLayout,
                                        LinearLayoutManager linearLayoutManager) {
            mRecyclerTabLayout = recyclerTabLayout;
            mLinearLayoutManager = linearLayoutManager;
        }

        public int mDx;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            mDx += dx;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            switch (newState) {
                case SCROLL_STATE_IDLE:
                    if (mDx > 0) {
                        selectCenterTabForRightScroll();
                    } else {
                        selectCenterTabForLeftScroll();
                    }
                    mDx = 0;
                    break;
                case SCROLL_STATE_DRAGGING:
                case SCROLL_STATE_SETTLING:
            }
        }

        protected void selectCenterTabForRightScroll() {
            int first = mLinearLayoutManager.findFirstVisibleItemPosition();
            int last = mLinearLayoutManager.findLastVisibleItemPosition();
            int center = mRecyclerTabLayout.getWidth() / 2;
            for (int position = first; position <= last; position++) {
                View view = mLinearLayoutManager.findViewByPosition(position);
                if (view.getLeft() + view.getWidth() >= center) {
                    mRecyclerTabLayout.setCurrentItem(position, false);
                    break;
                }
            }
        }

        protected void selectCenterTabForLeftScroll() {
            int first = mLinearLayoutManager.findFirstVisibleItemPosition();
            int last = mLinearLayoutManager.findLastVisibleItemPosition();
            int center = mRecyclerTabLayout.getWidth() / 2;
            for (int position = last; position >= first; position--) {
                View view = mLinearLayoutManager.findViewByPosition(position);
                if (view.getLeft() <= center) {
                    mRecyclerTabLayout.setCurrentItem(position, false);
                    break;
                }
            }
        }
    }

    public void setCurrentItem(int position, boolean smoothScroll) {
//        if (mViewPager != null) {
//            mViewPager.setCurrentItem(position, smoothScroll);
//            scrollToTab(mViewPager.getCurrentItem());
//            return;
//        }

        if (smoothScroll && position != mIndicatorPosition) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                startAnimation(position);
            } else {
             //   scrollToTab(position); //FIXME add animation
            }

        } else {
          //  scrollToTab(position);
        }
    }
    protected void startAnimation(final int position) {

        float distance = 1;

        View view = mLinearLayoutManager.findViewByPosition(position);
        if (view != null) {
            float currentX = view.getX() + view.getMeasuredWidth() / 2.f;
            float centerX = getMeasuredWidth() / 2.f;
            distance = Math.abs(centerX - currentX) / view.getMeasuredWidth();
        }

        ValueAnimator animator;
        if (position < mIndicatorPosition) {
            animator = ValueAnimator.ofFloat(distance, 0);
        } else {
            animator = ValueAnimator.ofFloat(-distance, 0);
        }
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
              //  scrollToTab(position, (float) animation.getAnimatedValue(), true);
            }
        });
        animator.start();
    }
}
