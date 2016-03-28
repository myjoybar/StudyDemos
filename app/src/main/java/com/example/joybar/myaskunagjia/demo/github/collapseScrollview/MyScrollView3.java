package com.example.joybar.myaskunagjia.demo.github.collapseScrollview;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 3/4/16.
 */
public class MyScrollView3 extends ScrollView  implements View.OnTouchListener {
    private List<String> mList = new ArrayList<>();
    private LinearLayout mRootView;
    private Context mContext;
    private int mItemHeight;
    private int mCurrentPosition = 0;

    private boolean isCollapse = false;

    public MyScrollView3(Context context) {
        super(context);
        init(context);
    }

    public MyScrollView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyScrollView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mRootView = new LinearLayout(context);
        mRootView.setOrientation(LinearLayout.VERTICAL);
        this.addView(mRootView);
    }

    public void setData(List<String> list) {
        mList = list;
//        mList.add(0, "");
//        mList.add("");
        for (int i = 0; i < mList.size(); i++) {
            View view = createItemView(mList.get(i), i);
            mRootView.addView(view);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, ScreenUtils.convertDipOrPx(mContext, 600));
        LinearLayout lin = new LinearLayout(mContext);
        lin.setLayoutParams(lp);//设置布局参数
        lin.setOrientation(LinearLayout.VERTICAL);//
        lin.setBackgroundColor(Color.parseColor("#D3EE22"));
        mRootView.addView(lin);
    }

    private View createItemView(final String item, final int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(R.layout.item_view, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
        layout.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
                params.width = ScreenUtils.getScreenWidth(mContext) / 3;
                params.height = ScreenUtils.convertDipOrPx(mContext, 50);
                layout.setLayoutParams(params);
                mItemHeight = ScreenUtils.convertDipOrPx(mContext, 50);
                //  mItemWidth = getViewMeasuredHeight(view);
            }
        });


        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "mCurrentPosition=" + mCurrentPosition, Toast.LENGTH_SHORT);
                Log.d("AAAAAAAAAAAA", "mCurrentPosition=" + mCurrentPosition);


                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        //  if (position != 0 && position != mList.size() - 1) {
                        mCurrentPosition = position;
                        scrollToSelection();
                        // }
                    }
                });
            }
        });
        textView.setText(item);
        return view;
    }

    private int getViewMeasuredHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredWidth();
    }

    public void setSelection(final int position) {

        MyScrollView3.this.post(new Runnable() {
            @Override
            public void run() {
              //  mCurrentPosition = position;
                scrollTo(0, (mCurrentPosition) * mItemHeight);

                //  refresh();
            }
        });


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (0 == position) {
                    isCollapse = false;
                } else {
                    isCollapse = true;
                }

            }
        }, 200);

    }

    private void scrollToSelection() {

        Log.d("AAAAAAAAAAAA", "mCurrentPosition=" + mCurrentPosition);
        smoothScrollTo(0, (mCurrentPosition) * mItemHeight);
        // smoothScrollTo(0,3 * mItemHeight);

        refresh();
    }

    private void refresh() {
        Log.e("HPG", "count=" + mRootView.getChildCount());
        for (int i = 0; i < mRootView.getChildCount() - 1; i++) {
            final View view = mRootView.getChildAt(i);
            TextView itemView = (TextView) view.findViewById(R.id.text);
            if (null == itemView) {
                return;
            }

            if (i < mCurrentPosition) {
                view.findViewById(R.id.left).setVisibility(View.VISIBLE);
                view.findViewById(R.id.right).setVisibility(View.VISIBLE);
            }
            if (i > mCurrentPosition) {
                view.findViewById(R.id.left).setVisibility(View.VISIBLE);
                view.findViewById(R.id.right).setVisibility(View.VISIBLE);
            }
            if (mCurrentPosition == i) {
                // itemView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                itemView.setTextColor(Color.parseColor("#000000"));
            } else {
                // itemView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

                itemView.setTextColor(Color.parseColor("#FF999999"));
                if (!isCollapse) {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            //    view.setVisibility(View.GONE);

                        }
                    }, 200);


                } else {
                    // view.setVisibility(View.VISIBLE);

                }


            }

//            if (i == 0 || i == mRootView.getChildCount()-1||i== mCurrentPosition) {
//                view.findViewById(R.id.left).setVisibility(View.GONE);
//                view.findViewById(R.id.right).setVisibility(View.GONE);
//            }
        }
//
//        if(!isCollapse){
//            isCollapse = true;
//
//        }else{
//            isCollapse = false;
//        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {


        super.onScrollChanged(l, t, oldl, oldt);
        // Log.d("AAAAAAAAAAAABBBBBB", "l=" + l);
        Log.d("AAAAAAAAAAAABBBBBB", "t=" + t);
        //   Log.d("AAAAAAAAAAAABBBBBB", "oldl=" + oldl);
        Log.d("AAAAAAAAAAAABBBBBB", "oldt=" + oldt);
        Log.d("AAAAAAAAAAAABBBBBB", "mCurrentPosition=" + mCurrentPosition);
        Log.d("AAAAAAAAAAAABBBBBB", "==============");

//        int scrollViewMeasuredHeight=this.getChildAt(0).getMeasuredHeight();
//        if(scrollY==0){
//            System.out.println("滑动到了顶端 view.getScrollY()="+scrollY);
//        }
//        if((scrollY+height)==scrollViewMeasuredHeight){
//            System.out.println("滑动到了底部 scrollY="+scrollY);
//            System.out.println("滑动到了底部 height="+height);
//            System.out.println("滑动到了底部 scrollViewMeasuredHeight="+scrollViewMeasuredHeight);
//        }


//        if (!isCollapse) {
//            isCollapse = true;
//
//            if (mCurrentPosition != 0) {
//                if (t > mItemHeight / 2) {
//                    setSelection(mCurrentPosition);
//
//                }
//            }
//
//        } else {
//           // setSelection(0);
//            isCollapse = false;
//        }


        if (!isCollapse) {
            //展开状态下
            Log.d("AAAAAAAAAAAABBBBBB", "==============展开状态下");

            if (mCurrentPosition != 0) {
                if (t > mItemHeight / 2) {
                  //  setSelection(mCurrentPosition);
                }
            }

        } else {
            //收缩状态下
            Log.d("AAAAAAAAAAAABBBBBB", "==============收缩状态下");

          //  setSelection(0);
        }
        Log.d("AAAAAAAAAAAABBBBBB", "getScaleY"+getScaleY());

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                int scrollY=view.getScrollY();
                int height=view.getHeight();
                int scrollViewMeasuredHeight=this.getChildAt(0).getMeasuredHeight();
                if(scrollY==0){
                    System.out.println("滑动到了顶端 view.getScrollY()="+scrollY);
                }
                if((scrollY+height)==scrollViewMeasuredHeight){
                    System.out.println("滑动到了底部 scrollY="+scrollY);
                    System.out.println("滑动到了底部 height="+height);
                    System.out.println("滑动到了底部 scrollViewMeasuredHeight="+scrollViewMeasuredHeight);
                }
                break;

            default:
                break;
        }
        return false;
    }

    interface OnTabClickListener {
        public void onTabClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {




       return super.onTouchEvent(motionEvent);
    }

    public int getmCurrentPosition() {
        return mCurrentPosition;
    }

    public void setmCurrentPosition(int mCurrentPosition) {
        this.mCurrentPosition = mCurrentPosition;
    }



}
