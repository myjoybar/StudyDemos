package com.example.joybar.myaskunagjia.demo.md.RecyclerViewHeader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.joybar.myaskunagjia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 15/11/26.
 */
public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private SampleAdapter adapter;

    private int lastVisibleItem;


    private RecyclerViewHeader recyclerHeader;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //刷新
                    Toast.makeText(MainActivity.this, "DOWN", 0).show();
                    mSwipeRefreshWidget.setRefreshing(false);

                    adapter.getList().clear();
                    addList();
                    break;
                case 1:
                    //加载
                    Toast.makeText(MainActivity.this, "UP", 0).show();
                    addList();
                    break;
                default:
                    break;
            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rr_header);

        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        //   mSwipeRefreshWidget.setColorScheme(R.color.color1, R.color.color2, R.color.color3, R.color.color4);
        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //滑动到最后
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    handler.sendEmptyMessageDelayed(1, 3000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

        });

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new SampleAdapter();
        mRecyclerView.setAdapter(adapter);


        recyclerHeader = (RecyclerViewHeader) this.findViewById(R.id.header);
        recyclerHeader.attachTo(mRecyclerView);

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

        for (int i = 1; i < 20; i++) {
            list.add(lastPosition + i);
        }

        return list;
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0, 3000);
    }

}
