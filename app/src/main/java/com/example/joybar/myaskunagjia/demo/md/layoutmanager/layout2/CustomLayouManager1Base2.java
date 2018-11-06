package com.example.joybar.myaskunagjia.demo.md.layoutmanager.layout2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by joybar on 2018/10/31.
 * 可以显示每列
 * https://blog.csdn.net/qq_33505655/article/details/80300649
 */

public class CustomLayouManager1Base2 extends RecyclerView.LayoutManager {

	private int offset = 0;
	private int mDecoratedChildWidth;
	private int mDecoratedChildHeight;
	private List<Integer> offsetList;
	private int itemInterval = 10;//item间隔

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

		if (getItemCount() == 0) {
			offset = 0;
			detachAndScrapAttachedViews(recycler);
			return;
		}

		//初始化的过程，还没有childView，先取出一个测绘。 认为每个item的大小是一样的
		if (getChildCount() == 0) {
			View scrap = recycler.getViewForPosition(0);
			addView(scrap);
			measureChildWithMargins(scrap, 0, 0);
			mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap);
			mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap);
			//	middle = (getVerticalSpace() - mDecoratedChildHeight) / 2;
			detachAndScrapView(scrap, recycler);
		}


//		//回收全部attach 的 view 到 recycler 并重新排列
//		int property = 0;
//		for (int i = 0; i < getItemCount(); i++) {
//			offsetList.add(property);
//			property += mDecoratedChildHeight;
//		}
//		detachAndScrapAttachedViews(recycler);
//		//layoutItems(recycler, state, 0);


		int offsetY = 0;
		int left = 0;
		for (int i = 0; i < getItemCount(); i++) {
			if (outOfRange(offsetY)) {
				continue;
			}

			View scrap = recycler.getViewForPosition(i);
			measureChildWithMargins(scrap, 0, 0);
			addView(scrap);
			int perItemWidth = getDecoratedMeasuredWidth(scrap);
			int perItemHeight = getDecoratedMeasuredHeight(scrap);

			layoutDecorated(scrap, left, offsetY, left + perItemWidth, offsetY + perItemHeight);
			offsetY += mDecoratedChildHeight+itemInterval;
		}


//
//		int offsetY = 0;
//			for (int i = 0; i < getItemCount(); i++) { //第一步
//				View scrap = recycler.getViewForPosition(i);
//				addView(scrap); //第二步
//				measureChildWithMargins(scrap, 0, 0);
//				int perItemWidth = getDecoratedMeasuredWidth(scrap);
//				int perItemHeight = getDecoratedMeasuredHeight(scrap);
//				layoutDecorated(scrap, 0, offsetY, perItemWidth, offsetY + perItemHeight); //第三步
//				offsetY += perItemHeight;
//			}


	}


	private void reallyLayout() {
	}


	private int getHorizontalSpace() {
		return getWidth() - getPaddingLeft() - getPaddingRight();
	}

	private int getVerticalSpace() {
		return getHeight() - getPaddingTop() - getPaddingBottom();
	}

	private boolean outOfRange(float targetOffSet) {
		return targetOffSet > getVerticalSpace() + mDecoratedChildHeight || targetOffSet < -mDecoratedChildHeight;
	}

}
