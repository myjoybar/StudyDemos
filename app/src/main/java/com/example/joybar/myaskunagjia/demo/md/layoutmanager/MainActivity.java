package com.example.joybar.myaskunagjia.demo.md.layoutmanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.demo.md.layoutmanager.layout2.CustomLayouManager1Base2222;

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
      //  mLayoutManager = new LinearLayoutManager(this);




       // mRecyclerView.setLayoutManager(new CustomLayouManager1());  // 显示
       // mRecyclerView.setLayoutManager(new CustomLayouManager1Base2());  // 显示
         mRecyclerView.setLayoutManager(new CustomLayouManager1Base2222());  // 显示
        //// mRecyclerView.setLayoutManager(new CustomLayouManager2ScrollY());  // 滑动
        // mRecyclerView.setLayoutManager(new CustomLayouManager2ScrollXY(CustomLayouManager2ScrollXY.HORIZONAL));  // 回收
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

        for (int i = 1; i <=20; i++) {
            list.add(lastPosition + i);
        }

        return list;
    }


}
