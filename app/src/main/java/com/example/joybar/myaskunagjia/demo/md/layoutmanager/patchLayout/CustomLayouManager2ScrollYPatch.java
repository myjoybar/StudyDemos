package com.example.joybar.myaskunagjia.demo.md.layoutmanager.patchLayout;

import android.graphics.Path;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by joybar on 2018/10/31.
 * 在显示每列的基础上，添加滑动功能
 * https://blog.csdn.net/user11223344abc/article/details/78080671
 */

public class CustomLayouManager2ScrollYPatch extends RecyclerView.LayoutManager {

	private static final String TAG = "CustomLayouManager2ScrollYPatch";

	Path path;


	private int mTotalHeight = -1;
	//手指 从上往下move是   下拉  dy是-
	//手指 从下往上move是   上拉  dy是+
	int mTheMoveDistance = 0;


	public CustomLayouManager2ScrollYPatch(Path path) {
		this.path = path;
	}

	/**
	 * 这是一个必须重写的方法，这个方法是给RecyclerView的子View创建一个默认的LayoutParams，实现起来也十分简单。
	 *
	 * @return
	 */
	@Override
	public RecyclerView.LayoutParams generateDefaultLayoutParams() {
		return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

	}

	/**
	 * 这个方法显然是用于放置子view的位置，十分重要的一个方法。
	 *
	 * @param recycler
	 * @param state    getItemCount: adapter中添加的数据的数目，
	 *                 getChildCount: 当前recyclerView中已经添加的子View的数目。
	 */
	@Override
	public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
		super.onLayoutChildren(recycler, state);
		int offsetY = 0;
		for (int i = 0; i < getItemCount(); i++) { //第一步
			View scrap = recycler.getViewForPosition(i);
			addView(scrap); //第二步
			measureChildWithMargins(scrap, 0, 0);
			int perItemWidth = getDecoratedMeasuredWidth(scrap);
			int perItemHeight = getDecoratedMeasuredHeight(scrap);
			layoutDecorated(scrap, 0, offsetY, perItemWidth, offsetY + perItemHeight); //第三步
			offsetY += perItemHeight;
			mTotalHeight = offsetY;
		}

	}


	@Override
	public boolean canScrollVertically() {
		return true;
	}

	/**
	 * @param dy,手指在屏幕上每次滑动的位移 手指由下往上滑时，dy值为 >0 的。手指由上往下滑时，dy值为 <0 的。
	 * @param recycler
	 * @param state
	 * @return
	 */
	@Override
	public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

		//这样子是可以的，但是有问题
//		offsetChildrenVertical(dy);
//		return super.scrollVerticallyBy(dy,recycler,state);

		dy = handleScroll(dy, recycler, state);
		return dy;
	}


	/**
	 * 处理滑动
	 *
	 * @param dy
	 * @param recycler
	 * @param state
	 * @return
	 */
	private int handleScroll(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
		int theRvVisibleHeight = getVerticalVisibleHeight();
		int theMoreHeight = mTotalHeight - theRvVisibleHeight;

		Log.e(TAG, "handleScroll, mRealMoveDistance == " + mTheMoveDistance);
		if (mTheMoveDistance + dy < 0) { //抵达上边界
			dy = -mTheMoveDistance;
			arriveToUpEdge(recycler, state);
		} else if (mTotalHeight > theRvVisibleHeight && mTheMoveDistance + dy > theMoreHeight) {//抵达下边界
			dy = theMoreHeight - mTheMoveDistance;
			arriveToDownEdge(recycler, state);
		} else {

		}
		mTheMoveDistance += dy;
		offsetChildrenVertical(-dy);
		Log.e(TAG, " handleScroll, mTheMoveYDistance  == " + mTheMoveDistance);
		return dy;
	}

	/**
	 * 抵达下边界
	 *
	 * @param recycler
	 * @param state
	 */
	private void arriveToDownEdge(RecyclerView.Recycler recycler, RecyclerView.State state) {
		Log.e(TAG, "抵达下边界");
	}


	/**
	 * 抵达上边界
	 *
	 * @param recycler
	 * @param state
	 */
	private void arriveToUpEdge(RecyclerView.Recycler recycler, RecyclerView.State state) {
		Log.e(TAG, "抵达上边界");
	}


	public int getVerticalVisibleHeight() {
		return getHeight() - getPaddingTop() - getPaddingBottom();
	}




}
