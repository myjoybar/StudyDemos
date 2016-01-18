package com.example.joybar.myaskunagjia.demo.md.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.demo.md.recyclerview.adapter.MyAdapter;
import com.example.joybar.myaskunagjia.demo.md.recyclerview.bean.User;
import com.example.joybar.myaskunagjia.demo.md.recyclerview.inter.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    private int type = 0;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private LinearLayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    protected StaggeredGridLayoutManager StaggeredGridLayoutManager;
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // private Button btn_login;

    private TextView tv, tv_state;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    // private TextView tv_register, tv_reget_pwd;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclerview);
        initView();
        initData();
        setLinstener();
        fillData();

        setRecyclerView(LinearLayoutManager.VERTICAL);
    }

    @Override
    protected void onStart() {
        L.i(TAG, "onStart");
        super.onStart();
    }
    @Override
    protected void onRestart() {
        L.i(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        L.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        L.i(TAG, "onPause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        L.i(TAG, "onStop");
        super.onStop();

    }
    @Override
    protected void onDestroy() {
        L.i(TAG, "onDestroy");
        super.onDestroy();
    }

    //初始化部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    @Override
    protected void initView() {
        btn1 = (Button) this.findViewById(R.id.btn1);
        btn2 = (Button) this.findViewById(R.id.btn2);
        btn3 = (Button) this.findViewById(R.id.btn3);
        btn4 = (Button) this.findViewById(R.id.btn4);
        btn5 = (Button) this.findViewById(R.id.btn5);
        btn6 = (Button) this.findViewById(R.id.btn6);
        btn7 = (Button) this.findViewById(R.id.btn7);
        btn8 = (Button) this.findViewById(R.id.btn8);
        tv = (TextView) this.findViewById(R.id.tv);
        tv_state = (TextView) this.findViewById(R.id.tv_state);
        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int scrollState) {
                updateState(scrollState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                String s = "";
                if (type == 0) {
                    s = "可见Item数量：" + layoutManager.getChildCount() + "\n"
                            + "可见Item第一个Position："
                            + layoutManager.findFirstVisibleItemPosition()
                            + "\n" + "可见Item最后一个Position："
                            + layoutManager.findLastVisibleItemPosition();

                } else if (type == 1) {
                    s = "可见Item数量：" + gridLayoutManager.getChildCount() + "\n"
                            + "可见Item第一个Position："
                            + gridLayoutManager.findFirstVisibleItemPosition()
                            + "\n" + "可见Item最后一个Position："
                            + gridLayoutManager.findLastVisibleItemPosition();
                } else {
                    s = "可见Item数量："
                            + StaggeredGridLayoutManager.getChildCount();

                }
                tv.setText(s);
            }
        });
    }





    @Override
    protected void fillData() {
        // TODO Auto-generated method stub

    }

    //网络请求部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //自定义方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    private void updateState(int scrollState) {
        String stateName = "Undefined";
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                stateName = "Idle";
                break;

            case SCROLL_STATE_DRAGGING:
                stateName = "Dragging";
                break;

            case SCROLL_STATE_SETTLING:
                stateName = "Flinging";
                break;
        }

        tv_state.setText("滑动状态：" + stateName);
    }

    public void setRecyclerView(int oritation) {

        // 如果布局大小一致有利于优化
        recyclerView.setHasFixedSize(true);

        // 创建一个线性布局管理器
        layoutManager = new LinearLayoutManager(this);

        switch (oritation) {
            case LinearLayoutManager.VERTICAL:

                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                break;
            case LinearLayoutManager.HORIZONTAL:

                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                break;

            default:
                break;
        }

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        // 创建数据集
        List<User> listData = new ArrayList<User>();
        for (int i = 0; i < 20; ++i) {
            User uBean = new User();
            uBean.setUsername("我是Item" + i);
            listData.add(uBean);
        }
        // 使用RecyclerView提供的默认的动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 为Item添加分割线
        recyclerView.addItemDecoration(new ItemDecorationDivider(mContext,
                R.drawable.item_divider, oritation));
        // recyclerView.addItemDecoration(new
        // DividerItemDecoration(context,oritation));

        // 创建Adapter，并指定数据集
        adapter = new MyAdapter(mContext, listData);
        // 为Item具体实例点击3种事件
        adapter.setItemClickListener(new ItemClickListener() {

            @Override
            public void onItemSubViewClick(View view, int postion) {
                T.showShort(mContext, "亲，你点击了Image" + postion);

            }

            @Override
            public void onItemLongClick(View view, int postion) {
                T.showShort(mContext, "亲，你长按了Item" + postion);

            }

            @Override
            public void onItemClick(View view, int postion) {
                T.showShort(mContext, "亲，你点击了Item" + postion);

            }
        });
        // 设置Adapter
        recyclerView.setAdapter(adapter);
    }

    private void setGridLayoutRecyclerView() {

        gridLayoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        // 设置布局管理器
        recyclerView.setLayoutManager(gridLayoutManager);
        // 创建数据集
        List<User> listData = new ArrayList<User>();
        for (int i = 0; i < 20; ++i) {
            User uBean = new User();
            uBean.setUsername("我是Item" + i);
            listData.add(uBean);
        }
        // 使用RecyclerView提供的默认的动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 为Item添加分割线
        recyclerView.addItemDecoration(new ItemDecorationDivider(mContext,
                R.drawable.item_divider, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new ItemDecorationDivider(mContext,
                R.drawable.item_divider, LinearLayoutManager.HORIZONTAL));
        // recyclerView.addItemDecoration(new
        // DividerItemDecoration(context,oritation));

        // 创建Adapter，并指定数据集
        adapter = new MyAdapter(mContext, listData);
        // 为Item具体实例点击3种事件
        adapter.setItemClickListener(new ItemClickListener() {

            @Override
            public void onItemSubViewClick(View view, int postion) {
                T.showShort(mContext, "亲，你点击了Image" + postion);

            }

            @Override
            public void onItemLongClick(View view, int postion) {
                T.showShort(mContext, "亲，你长按了Item" + postion);

            }

            @Override
            public void onItemClick(View view, int postion) {
                T.showShort(mContext, "亲，你点击了Item" + postion);

            }
        });
        // 设置Adapter
        recyclerView.setAdapter(adapter);
    }

    private void setStaggeredGridLayoutRecyclerView() {

        StaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(StaggeredGridLayoutManager);
        // 创建数据集
        List<User> listData = new ArrayList<User>();
        for (int i = 0; i < 20; ++i) {
            User uBean = new User();
            uBean.setUsername("我是Item" + i);
            listData.add(uBean);
        }
        // 使用RecyclerView提供的默认的动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 为Item添加分割线
        recyclerView.addItemDecoration(new ItemDecorationDivider(mContext,
                R.drawable.item_divider, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new ItemDecorationDivider(mContext,
                R.drawable.item_divider, LinearLayoutManager.HORIZONTAL));
        // recyclerView.addItemDecoration(new
        // DividerItemDecoration(context,oritation));

        // 创建Adapter，并指定数据集
        adapter = new MyAdapter(mContext, listData);
        // 为Item具体实例点击3种事件
        adapter.setItemClickListener(new ItemClickListener() {

            @Override
            public void onItemSubViewClick(View view, int postion) {
                T.showShort(mContext, "亲，你点击了Image" + postion);

            }

            @Override
            public void onItemLongClick(View view, int postion) {
                T.showShort(mContext, "亲，你长按了Item" + postion);

            }

            @Override
            public void onItemClick(View view, int postion) {
                T.showShort(mContext, "亲，你点击了Item" + postion);

            }
        });
        // 设置Adapter
        recyclerView.setAdapter(adapter);
    }
    //回调方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    public void onClick(View v){
        switch (v.getId()) {

            case R.id.btn1:
                type = 0;
                setRecyclerView(LinearLayoutManager.VERTICAL);

                break;
            case R.id.btn2:
                type = 0;
                setRecyclerView(LinearLayoutManager.HORIZONTAL);
                break;
            case R.id.btn3:
                type = 0;
                User uBean = new User();
                uBean.setUsername("我是增加的Item");
                adapter.addData(uBean, 1);// 添加到第二个
                break;
            case R.id.btn4:
                type = 0;
                adapter.daleteData(1); // 删除第二个
                break;
            case R.id.btn5:
                //Demo 只是测试RecyclerView功能，注意可能发生数组下标越界错误
                type = 0;
                User uBean1 = new User();
                uBean1.setUsername("我是连续添加的Item");
                adapter.itemRangeInserted(uBean1, 1, 5);
                break;
            case R.id.btn6:
                //Demo 只是测试RecyclerView功能，注意可能发生数组下标越界错误
                type = 0;
                adapter.itemRangeRemoved(1, 5);
                break;
            case R.id.btn7:
                type = 1;
                setGridLayoutRecyclerView();
                break;
            case R.id.btn8:
                type = 2;
                setStaggeredGridLayoutRecyclerView();
                break;

            default:
                break;
        }

    }






}

