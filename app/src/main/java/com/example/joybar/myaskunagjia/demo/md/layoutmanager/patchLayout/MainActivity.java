package com.example.joybar.myaskunagjia.demo.md.layoutmanager.patchLayout;

import android.app.Activity;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.demo.md.layoutmanager.CustomLayouManager2ScrollXY;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 15/11/26.
 */
public class MainActivity extends Activity {
	private SwipeRefreshLayout mSwipeRefreshWidget;
	private LinearLayoutManager mLayoutManager;
	private RecyclerView mRecyclerView;
	private SampleAdapter adapter;

	private int lastVisibleItem;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_path);

		mRecyclerView = (RecyclerView) findViewById(android.R.id.list);

		//   mSwipeRefreshWidget.setColorScheme(R.color.color1, R.color.color2, R.color.color3, R.color.color4);

//        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView,
//                                             int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                //滑动到最后
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
//                    handler.sendEmptyMessageDelayed(1, 3000);
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
//            }
//
//        });

		mRecyclerView.setHasFixedSize(true);


//		Path path = new Path();
//		path.moveTo(50, 0); //设定起始点
//		path.lineTo(50, 500);//第一条直线的终点，也是第二条直线的起点
//		path.lineTo(0, 1000);//第一条直线的终点，也是第二条直线的起点
//		//path.close();//闭环  // 不需要这句，否则getPosTan获取point x y坐标会出错


//		Path path = new Path();
//		RectF rect =  new RectF(50, 50, 250, 1050);
//		path.addArc(rect, 270, 180);



//		Path path = new Path();
//		path.moveTo(250,250);
//		path.rLineTo(600,300);
//		path.rLineTo(-600,300);
//		path.rLineTo(600,300);
//		path.rLineTo(-600,300);


		Path path = new Path();
		path.moveTo(400,0);
		path.lineTo(800,400);
		path.lineTo(400,800);
		path.lineTo(800,1200);
//		path.lineTo(0,1600);

//		Path path = new Path();
//		path.moveTo(400,0);
//		path.lineTo(400,400);
//		path.lineTo(400,800);
//		path.lineTo(400,1200);


		PathView pathView = new PathView(this, path);
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll_path_parent);
		ll.addView(pathView);


		CustomLayouManager2ScrollYPatch2 customLayouManager2ScrollYPatch2 = new CustomLayouManager2ScrollYPatch2(path);

		//  mLayoutManager = new LinearLayoutManager(this);
		// mRecyclerView.setLayoutManager(new CustomLayouManager1());  // 显示
		// mRecyclerView.setLayoutManager(new CustomLayouManager2Scroll());  // 滑动
		mRecyclerView.setLayoutManager(customLayouManager2ScrollYPatch2);  // 回收
		// mRecyclerView.setLayoutManager(new CustomLayouManager3Recycle());  // 回收


		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		adapter = new SampleAdapter(CustomLayouManager2ScrollXY.VERTICAL);
		mRecyclerView.setAdapter(adapter);

		addList();

	}


	private void addList() {
		List<Integer> list = getList();
		adapter.getList().addAll(list);
		adapter.notifyDataSetChanged();
	}

	private List<Integer> getList() {

		List<Integer> list = new ArrayList<Integer>();
		int size = adapter.getList().size();
		int lastPosition = size > 0 ? adapter.getList().get(size - 1) : 0;

		for (int i = 1; i <= 20; i++) {
			list.add(lastPosition + i);
		}

		return list;
	}


}
