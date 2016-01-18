package com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout;

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
public class MainActivity extends Activity {

    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private  SampleAdapter adapter;

    private int lastVisibleItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_coordinator_layout);


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
