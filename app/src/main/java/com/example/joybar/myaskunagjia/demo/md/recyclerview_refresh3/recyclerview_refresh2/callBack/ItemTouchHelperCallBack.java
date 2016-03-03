package com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh3.recyclerview_refresh2.callBack;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh3.recyclerview_refresh2.User;

import java.util.Collections;
import java.util.List;

/**
 * Created by joybar on 15/12/3.
 */

//http://www.apkbus.com/android-246159-1-1.html?_dsign=46f0ee76
//http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0724/3219.html
public class ItemTouchHelperCallBack extends ItemTouchHelper.SimpleCallback {

    ItemTouchHelper.Callback mCallback;

    private List<User> mListData;
    private RecyclerView.Adapter mAdapter;

    public ItemTouchHelperCallBack(int dragDirs, int swipeDirs, List<User> listData, RecyclerView.Adapter mAdapter) {
        super(dragDirs, swipeDirs);
        this.mListData = listData;
        this.mAdapter = mAdapter;
    }
/*
ItemTouchHelper可以让你轻易得到一个事件的方向。你需要重写getMovementFlags()方法来指定可以支持的拖放和滑动的方向。
使用helperItemTouchHelper.makeMovementFlags(int, int)来构造返回的flag。
这里我们启用了上下左右两种方向。注：上下为拖动（drag），左右为滑动（swipe）。
 */

    @Override

    public int getMovementFlags(RecyclerView recyclerView,

                                RecyclerView.ViewHolder viewHolder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);

    }

    /*
    要支持长按RecyclerView item进入拖动操作，你必须在isLongPressDragEnabled()方法中返回true。
    或者，也可以调用ItemTouchHelper.startDrag(RecyclerView.ViewHolder) 方法来开始一个拖动。
     */

    //可以不用
    @Override

    public boolean isLongPressDragEnabled() {

        return true;

    }
/*
而要在view任意位置触摸事件发生时启用滑动操作，则直接在sItemViewSwipeEnabled()中返回true就可以了。
或者，你也主动调用ItemTouchHelper.startSwipe(RecyclerView.ViewHolder) 来开始滑动操作。
 */

    //可以不用

    @Override

    public boolean isItemViewSwipeEnabled() {

        return true;

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
        int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
        if (fromPosition < toPosition) {
            //分别把中间所有的item的位置重新交换
            for (int i = fromPosition; i < toPosition; i++) {
                //防止数组越界
                if((i + 1)<mListData.size()){
                    Collections.swap(mListData, i, i + 1);
                }
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mListData, i, i - 1);
            }
        }
        mAdapter.notifyItemMoved(fromPosition, toPosition);
        //返回true表示执行拖动
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mListData.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //滑动时改变Item的透明度
            final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        }
    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        //当选中Item时候会调用该方法，重写此方法可以实现选中时候的一些动画逻辑
        Log.v("zxy", "onSelectedChanged");
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        //当动画已经结束的时候调用该方法，重写此方法可以实现恢复Item的初始状态
        Log.v("zxy", "clearView");
    }

}
