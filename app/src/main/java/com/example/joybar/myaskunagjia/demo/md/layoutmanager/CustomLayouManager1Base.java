package com.example.joybar.myaskunagjia.demo.md.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by joybar on 2018/10/31.
 * 可以显示每列
 */

public class CustomLayouManager1Base extends RecyclerView.LayoutManager{

	/**
	 * 这是一个必须重写的方法，这个方法是给RecyclerView的子View创建一个默认的LayoutParams，实现起来也十分简单。
	 * @return
	 */
	@Override
	public RecyclerView.LayoutParams generateDefaultLayoutParams() {
		return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

	}

	/**
	 * 这个方法显然是用于放置子view的位置，十分重要的一个方法。
	 * @param recycler
	 * @param state
	 * getItemCount: adapter中添加的数据的数目，
	 * getChildCount: 当前recyclerView中已经添加的子View的数目。
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
		}

	}
}
