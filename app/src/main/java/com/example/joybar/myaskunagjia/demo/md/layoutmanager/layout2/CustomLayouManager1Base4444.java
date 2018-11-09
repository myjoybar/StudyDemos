package com.example.joybar.myaskunagjia.demo.md.layoutmanager.layout2;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 2018/10/31.
 * 可以显示每列
 * https://blog.csdn.net/qq_33505655/article/details/80300649
 */

public class CustomLayouManager1Base4444 extends RecyclerView.LayoutManager {

	private static final String TAG = "CustomLayouManager1Base4444";
	private List<Integer> offsetList = new ArrayList<>();
	private int itemScrollOffsetY = 0;
	private int itemInterval = 200;//item间隔
	int itemLeft = 0;

	private int defaultItemHeight;
	private int defaultItemWidth;
	private float maxScale = 1.5f;
	private float minScale = 1.0f;
	private float scaleInterval = 0.1f;
	private int scaleItemCount = 5;
	private List<Float> firstScreenScaleList = new ArrayList<>();

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
			itemScrollOffsetY = 0;
			detachAndScrapAttachedViews(recycler);
			return;
		}

		//初始化的过程，还没有childView，先取出一个测绘。 认为每个item的大小是一样的
		if (getChildCount() == 0) {
			View scrap = recycler.getViewForPosition(0);
			addView(scrap);
			measureChildWithMargins(scrap, 0, 0);
			defaultItemWidth = getDecoratedMeasuredWidth(scrap);
			defaultItemHeight = getDecoratedMeasuredHeight(scrap);
			detachAndScrapView(scrap, recycler);
		}

		initOffsetY();


		//回收全部attach 的 view 到 recycler 并重新排列
		//记录每个Item的Y轴偏移
//		int property = 0;
//		for (int i = 0; i <= getItemCount(); i++) {
//			offsetList.add(property);
//			property += defaultItemHeight + itemInterval;
//
//		}
		detachAndScrapAttachedViews(recycler);
		reallyLayout(recycler, state, 0);


	}

	@Override
	public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
		Log.e(TAG, "dy=" + dy + ",itemScrollOffsetY=" + itemScrollOffsetY + ",getVerticalSpace()=" + getVerticalSpace());
		// 手指向上，dy>0
		int totalHeight = offsetList.get(getItemCount());
		int theMoreHeight = totalHeight - getVerticalSpace();
		if (itemScrollOffsetY + dy < 0) { //抵达上边界
			Log.e(TAG, "抵达上边界");
			dy = -itemScrollOffsetY;
		} else if (totalHeight > getVerticalSpace() && itemScrollOffsetY + dy > theMoreHeight) {//抵达下边界
			Log.e(TAG, "抵达下边界");
			dy = theMoreHeight - itemScrollOffsetY;
		} else {

		}
		itemScrollOffsetY += dy;
		reallyLayout(recycler, state, dy);
		return dy;

	}

	private void reallyLayout(RecyclerView.Recycler recycler, RecyclerView.State state, int dy) {


		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			int pos = getPosition(view);
			if (outOfRange(offsetList.get(pos) - itemScrollOffsetY)) {
				removeAndRecycleView(view, recycler);
			}
		}

		detachAndScrapAttachedViews(recycler);

		int offsetY = 0;

		for (int i = 0; i < getItemCount(); i++) {

			if (outOfRange(offsetList.get(i) - itemScrollOffsetY)) {
				Log.e(TAG, "outOfRange " + i + ",");
				//continue;
			}
			View scrap = recycler.getViewForPosition(i);

			if (dy >= 0) {
				addView(scrap);
				//Log.e(TAG, "dy >= 0  addView(scrap)");
			} else {
				addView(scrap, 0);
				//Log.e(TAG, "dy < 0  addView(scrap, 0)");
			}
			//float scale = getScale(i, itemScrollOffsetY);
			float scale = getItemScale(i);

			//float scale = 1f;
			int theFirstVisibleItemPosition = findTheFirstVisibleItemPosition();
			int positionOffset = Math.abs((i - theFirstVisibleItemPosition) % getVisibleRealItemCount());

			scrap.setScaleY(scale);
			measureChildWithMargins(scrap, 0, 0);
//			scrap.setScaleX(scale);

			int scaleItemHeight = (int) (defaultItemHeight * scale);
			//int scaleItemWidth = (int) (defaultItemWidth*scale);
			int scaleItemWidth = (int) (defaultItemWidth);
			int top = offsetY - itemScrollOffsetY;
			int bottom = top + scaleItemHeight;

			int right = itemLeft + scaleItemWidth;



			Log.e(TAG, "i=" + i + ",top=" + top + ",scale=" + scale + ",scaleItemHeight=" + scaleItemHeight + ", bottom=" + bottom + ", offsetY=" +
					offsetY + ", itemScrollOffsetY=" + itemScrollOffsetY + ",  offsetList.get(position)=" + offsetList.get(i) + ", " +
					"theFirstVisibleItemPosition=" + theFirstVisibleItemPosition + ", positionOffset=" + positionOffset);
			//Log.e(TAG, "getVisibleRealItemCount()=" + getVisibleRealItemCount()  );

			//layoutDecorated(scrap, itemLeft, top, right, bottom);
			layoutDecorated(scrap, itemLeft, top, right, bottom);
			offsetY += scaleItemHeight + itemInterval;
		}


	}


	private int findNearItemFromMidIndex(int offset) {

		float minDistance = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < getItemCount(); i++) {
			float midOfVisible = getVerticalSpace() / 2 + offset;
//			float distance = offsetList.get(i) - midOfVisible;
//			if (distance > 0) {
//				distance = distance + defaultItemHeight / 2;
//			} else {
//				distance = -distance;
//				distance = Math.abs(distance - defaultItemHeight / 2);
//			}

			float distance = Math.abs(offsetList.get(i) - defaultItemHeight / 2 - midOfVisible);

//			Log.e(TAG, ",midOfVisible=" + midOfVisible + ",offsetList=" + offsetList.get(i) + ",i=" + (i) + ",distance=" + distance + ",
// itemScrollOffsetY=" +
//					itemScrollOffsetY);

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

		float scale = (float) (getVisibleRealItemCount() - distance) / getVisibleRealItemCount() * maxScale;

		float midOfVisible = getVerticalSpace() / 2;
		Log.e(TAG, ",midOfVisible=" + midOfVisible + ",nearItemFromMidIndex=" + nearItemFromMidIndex + ",offsetList=" + offsetList.get(index) + ","
				+ "i=" + (index) + ",distance=" + distance + ",itemScrollOffsetY=" + offset + ",scale=" + scale);

		if (scale < 1.0f) {
			scale = minScale;
		}
		return scale;


	}

	private int findTheFirstVisibleItemPosition() {
		for (int i = 0; i < getItemCount(); i++) {

			int top = offsetList.get(i) - itemScrollOffsetY;
			if(outOfRange(top)){
				continue;
			}
			int bottom = (int) (top + defaultItemHeight*firstScreenScaleList.get(0)+itemInterval);
			if (bottom >= 0) {
				Log.d(TAG,"top="+top+",firstScreenScaleList.get(0)="+firstScreenScaleList.get(0)+",bottom="+bottom+",iiiiii="+i);
				return i;
			}
		}
		return 0;
	}




	private float getItemScale(int position) {
		if (outOfRange(offsetList.get(position) - itemScrollOffsetY)) {
			return 1f;
		}
		int theFirstVisibleItemPosition = findTheFirstVisibleItemPosition();
		int positionOffset = Math.abs((position - theFirstVisibleItemPosition) % getVisibleRealItemCount());
		return firstScreenScaleList.get(positionOffset);

	}

	public int getVisibleItemCount() {
		int visibleItemCount = getVerticalSpace() / defaultItemHeight;
		return visibleItemCount;
	}

	private void initOffsetY() {
		if (firstScreenScaleList.size() != 0) {
			return;
		}
		int visibleRealItemCount = getVisibleRealItemCount();
		int midVisibleRealItemPosition = visibleRealItemCount / 2;
		for (int k = 0; k < visibleRealItemCount; k++) {
			float scale = maxScale - Math.abs(k - midVisibleRealItemPosition) * scaleInterval;
			if (scale < minScale) {
				scale = minScale;
			}
			firstScreenScaleList.add(scale);
		}

		for (Float scale : firstScreenScaleList) {
			Log.d(TAG, "scale=" + scale + "，getItemCount()=" + getItemCount());
		}

		int property = 0;
		// 最后一个用于记录整个列表的高度
		for (int i = 0; i <= getItemCount(); i++) {

			offsetList.add(property);
			float scale = 1;
			if (i < visibleRealItemCount) {
				scale = firstScreenScaleList.get(i);
			}
			Log.d(TAG, "i=" + i + ",scale=" + scale + ",defaultItemHeight*scale=" + defaultItemHeight * scale + ",property=" + property);
			property += defaultItemHeight * scale + itemInterval;
		}

//		for(int offset:offsetList){
//			Log.d(TAG,"offset="+offset);
//		}
	}


	private int getVisibleRealItemCount() {
		int count = 0;
		float totalHeight = 0;
		float increasedHeight = 0;
		float realScale = 0f;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			if (count <= scaleItemCount) {

				if (i == 0) {
					realScale = maxScale;
					increasedHeight = defaultItemHeight * realScale + itemInterval;
				} else {
					realScale = maxScale - (i + 1) / 2 * scaleInterval;
					if (realScale < minScale) {
						realScale = minScale;
					}
					increasedHeight = defaultItemHeight * realScale + itemInterval;
				}
//				Log.e(TAG, "i = " + i + ",realScale=" + realScale + ",totalHeight=" + totalHeight + ",increasedHeight=" + increasedHeight + ",
// getVerticalSpace()=" +
//						getVerticalSpace());
				totalHeight = totalHeight + increasedHeight;
			} else {
				increasedHeight = defaultItemHeight;
				totalHeight = totalHeight + increasedHeight;
			}
			count = count + 1;
			if (totalHeight >= getVerticalSpace()) {
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
		//return targetOffSet > getVerticalSpace() + defaultItemHeight || targetOffSet < -defaultItemHeight;
		return targetOffSet < 0 ||targetOffSet > getVerticalSpace();
	}

	private boolean outOfRange(float targetOffSet, int position) {
		//return targetOffSet > getVerticalSpace() + defaultItemHeight || targetOffSet < -defaultItemHeight;
		return targetOffSet > getVerticalSpace();
	}


	@Override
	public boolean canScrollVertically() {
		return true;
	}


}
