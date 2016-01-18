package com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joybar.myaskunagjia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 15/11/26.
 */
//http://blog.csdn.net/tiankong1206/article/details/48394393
public class MainActivity extends Activity {

    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private SampleAdapter adapter;

    private int lastVisibleItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_coordinator_layout2);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //   mSwipeRefreshWidget.setColorScheme(R.color.color1, R.color.color2, R.color.color3, R.color.color4);




        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new SampleAdapter();
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

        for (int i = 1; i < 20; i++) {
            list.add(lastPosition + i);
        }

        return list;
    }



}
