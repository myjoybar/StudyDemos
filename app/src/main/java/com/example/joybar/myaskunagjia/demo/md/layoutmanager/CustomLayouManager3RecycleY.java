package com.example.joybar.myaskunagjia.demo.md.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by joybar on 2018/10/31.
 * 在滑动功能基础上，添加recycle
 */

public class CustomLayouManager3RecycleY extends RecyclerView.LayoutManager {

	private static final String TAG = "CustomLayouManager";
	private int mTotalHeight = -1;
	//手指 从上往下move是   下拉  dy是-
	//手指 从下往上move是   上拉  dy是+
	int mTheMoveDistance = 0;


	private SparseArray<Rect> itemRects = null;

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

//
//		int offsetY = 0;
//		for (int i = 0; i < getItemCount(); i++) { //第一步
//			View scrap = recycler.getViewForPosition(i);
//			addView(scrap); //第二步
//			measureChildWithMargins(scrap, 0, 0);
//			int perItemWidth = getDecoratedMeasuredWidth(scrap);
//			int perItemHeight = getDecoratedMeasuredHeight(scrap);
//			layoutDecorated(scrap, 0, offsetY, perItemWidth, offsetY + perItemHeight); //第三步
//			offsetY += perItemHeight;
//			mTotalHeight = offsetY;
//		}

		initFirst(recycler, state);

	}


	/**
	 * 首次初始化
	 *
	 * @param recycler
	 * @param state
	 */
	private void initFirst(RecyclerView.Recycler recycler, RecyclerView.State state) {

		//初始化步长
		int perItemHeight = initStepHeight(recycler);

		//* 记录所有条目的位置 *以及totalHeight
		initItemRectSparse(perItemHeight);

		//初始化步长可见条目数
		int visibleCount = initVisibleCounts(perItemHeight);
		Log.e(TAG, "visibleCount" + "    >>>  " + visibleCount + "");
		layoutChildren(recycler, visibleCount);

	}


	private int initStepHeight(RecyclerView.Recycler recycler) {
		View Adam = recycler.getViewForPosition(0);
		addView(Adam);
		int result = -1;
		measureChildWithMargins(Adam, 0, 0);
		result = getDecoratedMeasuredHeight(Adam);
		removeAndRecycleAllViews(recycler);
		return result;
	}


	/**
	 * 记录所有条目的位置
	 * 以及totalHeight
	 */
	private void initItemRectSparse(int stepItemHeight) {
		itemRects = new SparseArray<>();
		int offsetY = getPaddingTop();
		for (int i = 0; i < getItemCount(); i++) {
			Log.e(TAG, "initItemRectSparse," + "SparseArray item>>" + i + "__上：" + offsetY + "__下：" + (offsetY + stepItemHeight));
			itemRects.put(i, new Rect(getPaddingLeft(), offsetY, getWidth() + getPaddingRight(), offsetY + stepItemHeight));
			offsetY += stepItemHeight;
			mTotalHeight = offsetY;

		}
	}

	/**
	 * 初始化可见条目数，addview以及layout
	 */
	private int initVisibleCounts(int stepHeight) {
		int verticalVisibleHeight = getVerticalVisibleHeight();
		return verticalVisibleHeight / stepHeight;
	}

	/**
	 * addView * layoutDecorated
	 *
	 * @param recycler
	 * @param visibleCount
	 */
	private void layoutChildren(RecyclerView.Recycler recycler, int visibleCount) {

		detachAndScrapAttachedViews(recycler);

		for (int i = 0; i < visibleCount; i++) {
			View viewForPositionChild = recycler.getViewForPosition(i);
			measureChildWithMargins(viewForPositionChild, 0, 0);
			addView(viewForPositionChild);
			//TODO 可以判空
			layoutDecorated(viewForPositionChild, itemRects.get(i).left, itemRects.get(i).top, itemRects.get(i).right, itemRects.get(i).bottom);
		}

	}

	@Override
	public boolean canScrollVertically() {
		return true;
	}

	@Override
	public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

		//这样子是可以的，但是有问题
//		offsetChildrenVertical(dy);
//		return super.scrollVerticallyBy(dy,recycler,state);

		dy = handleScroll(dy, recycler, state);
		handleRecycle(recycler, state);
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


	/**
	 * 处理离屏回收
	 *
	 * @param recycler
	 * @param state
	 */
	private void handleRecycle(RecyclerView.Recycler recycler, RecyclerView.State state) {


		detachAndScrapAttachedViews(recycler);

		int childCount = getChildCount();
		Log.e("handleRecycle", childCount + "");
		Rect visibleRect = getVisibleArea();

		for (int i = 0; i < getItemCount(); i++) {
			View viewForPosition = recycler.getViewForPosition(i);
			Rect rect = itemRects.get(i);
			if (Rect.intersects(visibleRect, rect)) {
				addView(viewForPosition);
				measureChildWithMargins(viewForPosition, 0, 0);
				layoutDecorated(viewForPosition, rect.left, rect.top - mTheMoveDistance, rect.right, rect.bottom - mTheMoveDistance);
			} else {
				removeAndRecycleView(viewForPosition, recycler);
				Log.e(TAG, "handleRecycle," + "回收喽");
			}
		}
	}


	/**
	 * 获取可见的区域Rect
	 *
	 * @return
	 */
	private Rect getVisibleArea() {
		Rect result = new Rect(getPaddingLeft(), getPaddingTop() + mTheMoveDistance, getWidth() + getPaddingRight(), getVerticalVisibleHeight() +
				mTheMoveDistance);

		Log.e(TAG, "getVisibleArea," + "Top  >>>>>>>" + (getPaddingTop() + mTheMoveDistance) + "");
		Log.e(TAG, "getVisibleArea," + "Bottom  >>>>>>>" + (getVerticalVisibleHeight() + mTheMoveDistance) + "");

		return result;
	}
}
