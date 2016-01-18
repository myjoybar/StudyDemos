package com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh4;

/**
 * Created by joybar on 15/11/26.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh4.callBack.ItemTouchHelperCallBack;

import java.util.ArrayList;
import java.util.List;

//http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0724/3219.html
public class MainActivity extends Activity implements OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {
    private String TAG = MainActivity.class.getName();
    private Context context;
    private ImageView imv_back;
    private TextView tv_title;
    private Button btn;

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    private SwipeRefreshLayout swipeLayout;

    List<User> listData = new ArrayList<User>();
    List<User> listDataCache = new ArrayList<User>();

    private boolean flag_is_load = false;
    private boolean flag_is_refresh = false;
    private GridLayoutManager gridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_swip_recyclerview);

        initView();
        initValue();
        setLinstener();
        fillData();

        getListData(Constant.ON_CREATE);

    }

    private void initView() {
        // imv_back = (ImageView) this.findViewById(R.id.imv_back);
        // tv_title =(TextView) this.findViewById(R.id.tv_title);
        // btn = (Button) this.findViewById(R.id.btn);
        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);

        swipeLayout = (SwipeRefreshLayout) this
                .findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        // 顶部刷新的样式
        swipeLayout.setColorScheme(android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light);

    }

    private void initValue() {

        context = this;

        // 如果布局大小一致有利于优化
        recyclerView.setHasFixedSize(true);

//        // 创建一个线性布局管理器
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器


        gridLayoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);



        // 设置布局管理器
        recyclerView.setLayoutManager(gridLayoutManager);

        // 使用RecyclerView提供的默认的动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int scrollState) {
                super.onScrollStateChanged(recyclerView, scrollState);
                L.i(TAG, "scrollState=="+scrollState);
                L.i(TAG, "SCROLL_STATE_DRAGGING=="+RecyclerView.SCROLL_STATE_DRAGGING);
                L.i(TAG, "adapter.getItemCount()=="+adapter.getItemCount());

                if (scrollState == RecyclerView.SCROLL_STATE_DRAGGING
                        && gridLayoutManager.findLastVisibleItemPosition() + 1 == adapter.getItemCount()) {

                    L.i(TAG, "进来了");

                    getListData(Constant.ON_LOAD);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }

        });

        // 创建Adapter，并指定数据集
        adapter = new MyAdapter(context, listData);
        // 为Item具体实例点击3种事件
        adapter.setItemClickListener(new ItemClickListener() {

            @Override
            public void onItemSubViewClick(View view, int postion) {
                T.showShort(context, "亲，你点击了Image" + postion);

            }

            @Override
            public void onItemLongClick(View view, int postion) {
                T.showShort(context, "亲，你长按了Item" + postion);

            }

            @Override
            public void onItemClick(View view, int postion) {
                T.showShort(context, "亲，你点击了Item" + postion);

            }
        });
        // 设置Adapter
        recyclerView.setAdapter(adapter);



        //Item拖动和滑动删除
        ItemTouchHelperCallBack callBack = new ItemTouchHelperCallBack(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT,
                listData,adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);

//
//        //0则不执行拖动或者滑动
//        ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT) {
//            /**
//             * @param recyclerView
//             * @param viewHolder 拖动的ViewHolder
//             * @param target 目标位置的ViewHolder
//             * @return
//             */
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
//                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
//                if (fromPosition < toPosition) {
//                    //分别把中间所有的item的位置重新交换
//                    for (int i = fromPosition; i < toPosition; i++) {
//                        Collections.swap(listData, i, i + 1);
//                    }
//                } else {
//                    for (int i = fromPosition; i > toPosition; i--) {
//                        Collections.swap(listData, i, i - 1);
//                    }
//                }
//                adapter.notifyItemMoved(fromPosition, toPosition);
//                //返回true表示执行拖动
//                return true;
//            }
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                listData.remove(position);
//                adapter.notifyItemRemoved(position);
//            }
//        };
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);






    }

    private void setLinstener() {
        // imv_back.setOnClickListener(this);
        // btn.setOnClickListener(this);
    }

    private void fillData() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            // case R.id.imv_back:
            //
            // this.finish();
            // break;

            // case R.id.btn:
            // Intent intent11 = new Intent(MainActivity.this, MyActionbar1.class);
            // startActivity(intent11);
            // break;

            default:
                break;
        }

    }

    @Override
    public void onRefresh() {

        getListData(Constant.ON_REFRESH);

    }



    public void getListData(final int tag) {
        if (flag_is_load || flag_is_refresh) {

            return;
        }
        listDataCache.clear();
        if (tag == Constant.ON_CREATE) {
            for (int i = 0; i <20; ++i) {
                User uBean = new User();
                uBean.setUsername("我是Item" + i);
                listDataCache.add(uBean);

                showData(listDataCache,tag);
            }
        }else if (tag == Constant.ON_LOAD) {
            flag_is_load = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    // 第一种方式
                    for (int i = 0; i < 5; ++i) {
                        User uBean = new User();
                        uBean.setUsername("我是加载的Item" + i);
                        listDataCache.add(uBean);
                    }



                    // 第二种方式 不可以
//					User uBean = new User();
//					uBean.setUsername("我是加载的Item");
//					 adapter.itemRangeInserted(uBean, lastVisibleItem, 3);
                    //	 adapter.notifyDataSetChanged();


                    showData(listDataCache,tag);
                }
            }, 1000);

        }else if (tag == Constant.ON_REFRESH) {

            flag_is_refresh = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    swipeLayout.setRefreshing(false);


                    // 第一种方式
                    for (int i = 0; i < 5; ++i) {
                        User uBean = new User();
                        uBean.setUsername("我是刷新的Item");

                        listDataCache.add(i, uBean);
                    }




                    // 第二种方式 可以
//					User uBean1 = new User();
//					uBean1.setUsername("我是刷新的Item");
//					adapter.itemRangeInserted(uBean1, 0, 2);

                    showData(listDataCache,tag);
                }
            }, 1000);

        }



    }

    private void showData(List<User> itemsCache, int tag) {

        if (itemsCache != null && itemsCache.size() > 0) {

            if (tag == Constant.ON_CREATE) {

                listData.clear();
                listData.addAll(itemsCache);



            } else if (tag == Constant.ON_REFRESH) {

                listData.clear();
                listData.addAll(itemsCache);

            }else if (tag == Constant.ON_LOAD) {

                listData.addAll(itemsCache);
            }

            adapter.dataSetChanged();

        }else{
            L.i(TAG, "No Data");
        }

        flag_is_refresh = false;
        flag_is_load = false;
        swipeLayout.setRefreshing(false);


    }

}
