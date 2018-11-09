package com.example.joybar.myaskunagjia.demo.md.layoutmanager.layout2;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 2018/10/31.
 * 可以显示每列
 * https://blog.csdn.net/qq_33505655/article/details/80300649
 */

public class CustomLayouManager1Base33333 extends RecyclerView.LayoutManager {

	private static final String TAG = "CustomLayouManager1Base2222";
	private int mDecoratedChildWidth;
	private int mDecoratedChildHeight;
	private List<Integer> offsetList = new ArrayList<>();
	private int itemIntervalX = 0;//item间隔
	private int itemOffsetY = 0;
	private int itemLeft = 0;
	private int defaultItemHeight;
	private float maxScale = 3.0f;
	private float minScale = 1.0f;
	private float scaleInterval = 0.2f;
	private int scaleItemCount = 5;


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
			itemOffsetY = 0;
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
			defaultItemHeight = getDecoratedMeasuredWidth(scrap);
			detachAndScrapView(scrap, recycler);
		}

		//回收全部attach 的 view 到 recycler 并重新排列
		//记录每个Item的Y轴偏移
		int property = 0;
		for (int i = 0; i <= getItemCount(); i++) {
			offsetList.add(property);
			property += mDecoratedChildHeight + itemIntervalX;

		}
		detachAndScrapAttachedViews(recycler);
		reallyLayout(recycler, state, 0);


	}

	@Override
	public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
		Log.e(TAG, "dy=" + dy + ",itemOffsetY=" + itemOffsetY + ",getVerticalSpace()=" + getVerticalSpace());
		// 手指向上，dy>0
		int totalHeight = offsetList.get(getItemCount());
		int theMoreHeight = totalHeight - getVerticalSpace();
		if (itemOffsetY + dy < 0) { //抵达上边界
			Log.e(TAG, "抵达上边界");
			dy = -itemOffsetY;
		} else if (totalHeight > getVerticalSpace() && itemOffsetY + dy > theMoreHeight) {//抵达下边界
			Log.e(TAG, "抵达下边界");
			dy = theMoreHeight - itemOffsetY;
		} else {

		}
		itemOffsetY += dy;
		reallyLayout(recycler, state, dy);
		return dy;

	}

	/**
	 * 当view 在进行setScale操作缩小后,view.getWidth 和view.getHeight 并不会有变化，原本是多少像素的，大小还是多少像素。
	 *
	 * @param recycler
	 * @param state
	 * @param dy
	 */

	private void reallyLayout(RecyclerView.Recycler recycler, RecyclerView.State state, int dy) {


		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			int pos = getPosition(view);
			if (outOfRange(offsetList.get(pos) - itemOffsetY)) {
				removeAndRecycleView(view, recycler);
			}
		}

		detachAndScrapAttachedViews(recycler);

		int offsetY = 0;
		for (int i = 0; i < getItemCount(); i++) {
			if (outOfRange(offsetY - itemOffsetY)) {
				continue;
			}
			View scrap = recycler.getViewForPosition(i);
			measureChildWithMargins(scrap, 0, 0);
			if (dy >= 0) {
				addView(scrap);
				//Log.e(TAG, "dy >= 0  addView(scrap)");
			} else {
				addView(scrap, 0);
				//Log.e(TAG, "dy < 0  addView(scrap, 0)");
			}

			Log.e(TAG, "before scale: " + getDecoratedMeasuredHeight(scrap));


			//float scale = getScale(i, itemOffsetY);
			float scale = 1f;
			scrap.setScaleY(scale);
			scrap.setScaleX(scale);
			//startScaleAnimator(scrap,1f,scale);

			measureChildWithMargins(scrap, 0, 0);
			Log.e(TAG, "after scale: " + getDecoratedMeasuredHeight(scrap));
			int itemScaleWidth = (int) (getDecoratedMeasuredWidth(scrap));
			int itemScaleHeight = (int) (getDecoratedMeasuredHeight(scrap) * scale);
			layoutDecorated(scrap, itemLeft, offsetY - itemOffsetY, itemLeft + itemScaleWidth, offsetY - itemOffsetY + itemScaleHeight);
//			offsetList.add(offsetY);
			offsetY += itemScaleHeight + itemIntervalX;
		}


	}


	private int findNearItemFromMidIndex(int offset) {

		float minDistance = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < getItemCount(); i++) {
			float midOfVisible = getVerticalSpace() / 2 + offset;
//			float distance = offsetList.get(i) - midOfVisible;
//			if (distance > 0) {
//				distance = distance + mDecoratedChildHeight / 2;
//			} else {
//				distance = -distance;
//				distance = Math.abs(distance - mDecoratedChildHeight / 2);
//			}

			float distance = Math.abs(offsetList.get(i) - mDecoratedChildHeight / 2 - midOfVisible);

//			Log.e(TAG, ",midOfVisible=" + midOfVisible + ",offsetList=" + offsetList.get(i) + ",i=" + (i) + ",distance=" + distance + ",
// itemOffsetY=" +
//					itemOffsetY);

			if (minDistance > distance) {
				minDistance = distance;
				index = i;
			}


		}

		return index;

	}


	private float getScale(int index, int offset) {
		float maxScale = 3;
		float minScale = 1;
		int nearItemFromMidIndex = findNearItemFromMidIndex(offset);

		int distance = Math.abs(index - nearItemFromMidIndex);

		float scale = (float) (getVisibleStandardItemCount() - distance) / getVisibleStandardItemCount() * maxScale;

		float midOfVisible = getVerticalSpace() / 2;
		Log.e(TAG, ",midOfVisible=" + midOfVisible + ",nearItemFromMidIndex=" + nearItemFromMidIndex + ",offsetList=" + offsetList.get(index) + ","
				+ "i=" + (index) + ",distance=" + distance + ",itemOffsetY=" + offset + ",scale=" + scale);

		if (scale < 1.0f) {
			scale = minScale;
		}
		return scale;


	}

	public int getVisibleStandardItemCount() {
		int visibleItemCount = getVerticalSpace() / defaultItemHeight;
		return visibleItemCount;
	}

	private float getItemScale(int index, int offset) {
		return 1;
	}

	private int getVisibleRealItemCount() {
		int count = 0;
		float totalHeight = 0;
		float increasedHeight = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			if (i == 0) {
				increasedHeight = defaultItemHeight * maxScale;
			} else {
				float scale = maxScale - i * scaleInterval;
				if (scale < minScale) {
					scale = minScale;
				}
				increasedHeight = defaultItemHeight * scale * 2;
			}
			totalHeight = totalHeight + increasedHeight;
			if (totalHeight <= getVerticalSpace()) {
				count++;
			} else {
				return count;
			}
		}
		return count;
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

	@Override
	public boolean canScrollVertically() {
		return true;
	}

	private void startScaleAnimator(View targetView, float formScale, float toScale) {
		AnimatorSet animatorSetsuofang = new AnimatorSet();
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(targetView, "scaleX", formScale, toScale);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(targetView, "scaleY", formScale, toScale);
		animatorSetsuofang.setDuration(10);
		animatorSetsuofang.setInterpolator(new DecelerateInterpolator());
		animatorSetsuofang.play(scaleX).with(scaleY);//两个动画同时开始
		animatorSetsuofang.start();


	}


}
