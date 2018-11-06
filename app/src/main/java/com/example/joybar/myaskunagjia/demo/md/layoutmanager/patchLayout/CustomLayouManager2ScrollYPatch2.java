package com.example.joybar.myaskunagjia.demo.md.layoutmanager.patchLayout;

import android.graphics.Path;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by joybar on 2018/10/31.
 * 在显示每列的基础上，添加滑动功能
 * https://blog.csdn.net/user11223344abc/article/details/78080671
 */

public class CustomLayouManager2ScrollYPatch2 extends RecyclerView.LayoutManager {

	public static final String TAG = "PathLayouManager";

	Path path;
	PathManager2 pathManager;
	private int mItemOffset; //Item间距

	private int mTotalHeight = -1;
	//手指 从上往下move是   下拉  dy是-
	//手指 从下往上move是   上拉  dy是+
	int mTheMoveDistance = 0;
	int mOrientation = RecyclerView.VERTICAL;

	int mOffsetY;

	public CustomLayouManager2ScrollYPatch2(Path path) {
		this(path, RecyclerView.VERTICAL);
	}

	public CustomLayouManager2ScrollYPatch2(Path path, int mOrientation) {
		this.path = path;
		this.mOrientation = mOrientation;
		pathManager = new PathManager2(path);

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


//		for (int i = 0; i < getItemCount(); i++) { //第一步
////			View scrap = recycler.getViewForPosition(i);
////			addView(scrap); //第二步
////			measureChildWithMargins(scrap, 0, 0);
////			int perItemWidth = getDecoratedMeasuredWidth(scrap);
////			int perItemHeight = getDecoratedMeasuredHeight(scrap);
////			layoutDecorated(scrap, 0, offsetY, perItemWidth, offsetY + perItemHeight); //第三步
////			offsetY += perItemHeight;
////			mTotalHeight = offsetY;
//
//
//			View scrap = recycler.getViewForPosition(i);
//			addView(scrap); //第二步
//			measureChildWithMargins(scrap, 0, 0);
//			Point currentPoint = pathManager.getPoints().get(i);
//
//			int perItemWidth = getDecoratedMeasuredWidth(scrap);
//			int perItemHeight = getDecoratedMeasuredHeight(scrap);
//
//			int itemX = (int) (currentPoint.getX() - perItemWidth / 2);
//			int itemY = (int) (currentPoint.getY() - perItemHeight / 2);
////
////			Log.e(TAG, "itemX"+ i+",="+itemX);
////			Log.e(TAG, "itemY"+ i+",="+itemY);
//
//			Log.e(TAG, "perItemWidth" + i + ",=" + perItemWidth);
//			Log.e(TAG, "perItemHeight" + i + ",=" + perItemHeight);
//
//			layoutDecorated(scrap, itemX, itemY, perItemWidth + itemX, itemY + perItemHeight); //第三步
//			//旋转item
//			//scrap.setRotation(currentPoint.getDegrees());
//		}

		if (state.getItemCount() == 0) {
			removeAndRecycleAllViews(recycler);
			return;
		}
		detachAndScrapAttachedViews(recycler);

		int maxVisibleCount = calculateMaxVisibleCount(recycler);

		for (int i = 0; i < maxVisibleCount; i++) {
			View scrap = recycler.getViewForPosition(i);
			addView(scrap); //第二步
			measureChildWithMargins(scrap, 0, 0);
			int perItemWidth = getDecoratedMeasuredWidth(scrap);
			int perItemHeight = getDecoratedMeasuredHeight(scrap);

			Log.e(TAG, "perItemHeight" + i + ",=" + perItemHeight + ",perItemWidth" + i + ",=" + perItemWidth);


			int canContainsCount = pathManager.getLength() / perItemHeight;

			float percent = i / (float) canContainsCount;
			int selectPointIndex = (int) (pathManager.getLength() * percent);
			Point currentPoint = pathManager.getPoints().get(selectPointIndex);

			Log.e(TAG, "currentPoint.getTan()[0]" + i + ",=" + currentPoint.getTan()[0] + ",currentPoint.getTan()[1]" + i + ",=" + currentPoint
					.getTan()[1]);

			//			int itemX = (int) (currentPoint.getX() - perItemWidth / 2);
//			int itemY = (int) (currentPoint.getY() - perItemHeight / 2);


			int itemX = (int) currentPoint.getX() - perItemWidth / 2;
			int itemY = (int) (currentPoint.getY() / currentPoint.getTan()[0]);

			Log.e(TAG, "itemX" + i + ",=" + itemX + ", itemY" + i + ",=" + itemY);
			layoutDecorated(scrap, itemX, itemY, perItemWidth + itemX, itemY + perItemHeight); //第三步
			//scrap.setRotation(currentPoint.getDegrees());
		}


	}


	private void reallyLayout(RecyclerView.Recycler recycler, RecyclerView.State state) {
		ArrayList<Point> points = pathManager.getPoints();
		int itemCount = getItemCount();
		// item 长度大于path，无限滚动
		if (itemNumberIsOverPathLength()) {
			Log.e(TAG, "item 长度大于path，无限滚动");
		}else {
			Log.e(TAG, "item 长度小于path");
		}
	}


	boolean itemNumberIsOverPathLength() {
		int itemCount = getItemCount();
		int itemLength = itemCount * mItemOffset;
		int pathLength = pathManager.getLength();
		Log.e(TAG, "mItemOffset="+mItemOffset+"，itemLength="+itemLength+"，pathLength="+pathLength);
		return  itemLength - pathLength > mItemOffset;

	}

	private int calculateMaxVisibleCount(RecyclerView.Recycler recycler) {
		View scrap = recycler.getViewForPosition(0);
		addView(scrap); //第二步
		measureChildWithMargins(scrap, 0, 0);
		int perItemWidth = getDecoratedMeasuredWidth(scrap);
		int perItemHeight = getDecoratedMeasuredHeight(scrap);
		Point currentPoint = pathManager.getPoints().get(1);

		int maxVisibleCount = (int) (pathManager.getLength() / perItemHeight);
		return maxVisibleCount;

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
		offsetChildrenVertical(dy);
		return super.scrollVerticallyBy(dy, recycler, state);

//		dy = handleScroll(dy, recycler, state);
//		return dy;
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

		mOffsetY += dy;
		int theRvVisibleHeight = getVerticalVisibleHeight();
		int theMoreHeight = mTotalHeight - theRvVisibleHeight;

		Log.e(TAG, " handleScroll, mOffsetY  == " + mOffsetY + ",theRvVisibleHeight+" + theRvVisibleHeight + ",theMoreHeight+" + theMoreHeight);

//
//		int theRvVisibleHeight = getVerticalVisibleHeight();
//		int theMoreHeight = mTotalHeight - theRvVisibleHeight;

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
