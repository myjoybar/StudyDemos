package com.example.joybar.myaskunagjia.demo.md.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by joybar on 2018/10/31.
 * 在显示每列的基础上，添加滑动功能
 * https://blog.csdn.net/user11223344abc/article/details/78080671
 */

public class CustomLayouManager2ScrollXY extends RecyclerView.LayoutManager {

	public static final int VERTICAL = 1;
	public static final int HORIZONAL = 0;
	int direction = VERTICAL;

	public CustomLayouManager2ScrollXY(int direction) {
		this.direction = direction;

	}

	private static final String TAG = "CustomLayouManager2Scroll";
	private int mTotalHeight = -1;
	private int mTotalWidth = -1;
	//手指 从上往下move是   下拉  dy是-
	//手指 从下往上move是   上拉  dy是+
	int mTheMoveYDistance = 0;
	int mTheMoveXDistance = 0;

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
		int offsetX = 0;
		for (int i = 0; i < getItemCount(); i++) { //第一步
			View scrap = recycler.getViewForPosition(i);
			addView(scrap); //第二步
			measureChildWithMargins(scrap, 0, 0);
			int perItemWidth = getDecoratedMeasuredWidth(scrap);
			int perItemHeight = getDecoratedMeasuredHeight(scrap);
			if(direction==VERTICAL){
				layoutDecorated(scrap, 0, offsetY, perItemWidth, offsetY + perItemHeight); //第三步
			}else{
				layoutDecorated(scrap, offsetX, 0, offsetX + perItemWidth,perItemHeight ); //第三步

			}
			offsetY += perItemHeight;
			offsetX += perItemWidth;
			mTotalHeight = offsetY;
			mTotalWidth = offsetX;
		}

	}

	@Override
	public boolean canScrollHorizontally() {
		return direction == HORIZONAL;
	}

	@Override
	public int scrollHorizontallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

		//这样子是可以的，但是有问题
//		offsetChildrenVertical(dy);
//		return super.scrollVerticallyBy(dy,recycler,state);

		dy = handleScrollHorizontally(dy, recycler, state);
		return dy;
	}


	/**
	 * 处理Vertically滑动
	 *
	 * @param dX
	 * @param recycler
	 * @param state
	 * @return
	 */
	private int handleScrollHorizontally(int dX, RecyclerView.Recycler recycler, RecyclerView.State state) {
		int theRvVisibleWidth = getHorizontalVisibleWidth();
		int theMoreWidth = mTotalWidth - theRvVisibleWidth;

		Log.e(TAG, "handleScroll, mRealMoveDistance == " + mTheMoveXDistance+",dX="+dX);
		if (mTheMoveXDistance + dX < 0) { //抵达左边边界
			Log.e(TAG, "handleScroll, 抵达左边边界 ");
			dX = -mTheMoveXDistance;
		} else if (mTotalWidth > theRvVisibleWidth && mTheMoveXDistance + dX > theMoreWidth) {//抵达下边界
			dX = theMoreWidth - mTheMoveXDistance;
			Log.e(TAG, "handleScroll, 抵达右边边界 ");
		} else {

		}
		mTheMoveXDistance += dX;
		offsetChildrenHorizontal(-dX);
		Log.e(TAG, " handleScroll, mTheMoveXDistance  == " + mTheMoveXDistance);
		return dX;
	}


	@Override
	public boolean canScrollVertically() {
		return direction == VERTICAL;
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

		dy = handleScrollVertically(dy, recycler, state);
		return dy;
	}


	/**
	 * 处理Vertically滑动
	 *
	 * @param dy
	 * @param recycler
	 * @param state
	 * @return
	 */
	private int handleScrollVertically(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
		int theRvVisibleHeight = getVerticalVisibleHeight();
		int theMoreHeight = mTotalHeight - theRvVisibleHeight;

		Log.e(TAG, "handleScroll, mRealMoveDistance == " + mTheMoveYDistance);
		if (mTheMoveYDistance + dy < 0) { //抵达上边界
			dy = -mTheMoveYDistance;
			arriveToUpEdge(recycler, state);
		} else if (mTotalHeight > theRvVisibleHeight && mTheMoveYDistance + dy > theMoreHeight) {//抵达下边界
			dy = theMoreHeight - mTheMoveYDistance;
			arriveToDownEdge(recycler, state);
		} else {

		}
		mTheMoveYDistance += dy;
		offsetChildrenVertical(-dy);
		Log.e(TAG, " handleScroll, mTheMoveYDistance  == " + mTheMoveYDistance);
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

	private int getHorizontalVisibleWidth() {
		return getWidth() - getPaddingLeft() - getPaddingRight();
	}

}
