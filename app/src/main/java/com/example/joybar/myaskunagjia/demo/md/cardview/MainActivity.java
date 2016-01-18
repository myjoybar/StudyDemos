package com.example.joybar.myaskunagjia.demo.md.cardview;

/**
 * Created by joybar on 15/11/26.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {
    private String TAG = MainActivity.class.getName();
    private Context context;
    private ImageView imv_back;
    private TextView tv;
    private Button btn;

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    private SwipeRefreshLayout swipeLayout;

    List<User> listData = new ArrayList<User>();
    List<User> listDataCache = new ArrayList<User>();



    private boolean flag_is_load = false;
    private boolean flag_is_refresh = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_swip_recyclerview_cardview_palette);
        context =this;
        initView();
        initValue();
        setLinstener();
        fillData();

        getListData(Constant.ON_CREATE);

    }

    private void initView() {
        // imv_back = (ImageView) this.findViewById(R.id.imv_back);
         tv =(TextView) this.findViewById(R.id.tv);


       innitPalette();





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

public void innitPalette(){
    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg1);
    //异步获得bitmap图片颜色值
    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette palette) {
            Palette.Swatch vibrant = palette.getVibrantSwatch();//有活力
            Palette.Swatch c = palette.getDarkVibrantSwatch();//有活力 暗色
            Palette.Swatch d = palette.getLightVibrantSwatch();//有活力 亮色
            Palette.Swatch f = palette.getMutedSwatch();//柔和
            Palette.Swatch a = palette.getDarkMutedSwatch();//柔和 暗色
            Palette.Swatch b = palette.getLightMutedSwatch();//柔和 亮色

            if (vibrant != null) {
                int color1 = vibrant.getBodyTextColor();//内容颜色
                int color2 = vibrant.getTitleTextColor();//标题颜色
                int color3 = vibrant.getRgb();//rgb颜色

                tv.setBackgroundColor(
                        vibrant.getRgb());

            }
        }
    });


}



    private void initValue() {

        context = this;

        // 如果布局大小一致有利于优化
        recyclerView.setHasFixedSize(true);

        // 创建一个线性布局管理器
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

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
                        && layoutManager.findLastVisibleItemPosition() + 1 == adapter.getItemCount()) {

                    L.i(TAG, "进来了");

                    getListData(com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.Constant.ON_LOAD);
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

        getListData(com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.Constant.ON_REFRESH);

    }



    public void getListData(final int tag) {
        if (flag_is_load || flag_is_refresh) {

            return;
        }
        listDataCache.clear();
        if (tag == com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.Constant.ON_CREATE) {
            for (int i = 0; i < 3; ++i) {
                User uBean = new User();
                uBean.setUsername("我是Item" + i);
                listDataCache.add(uBean);

                showData(listDataCache,tag);
            }
        }else if (tag == com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.Constant.ON_LOAD) {
            flag_is_load = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    // 第一种方式
                    for (int i = 0; i < 1; ++i) {
                        User uBean = new User();
                        uBean.setUsername("我是加载的Item");
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

        }else if (tag == com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.Constant.ON_REFRESH) {

            flag_is_refresh = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    swipeLayout.setRefreshing(false);


                    // 第一种方式
                    for (int i = 0; i < 1; ++i) {
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

            if (tag == com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.Constant.ON_CREATE) {

                listData.clear();
                listData.addAll(itemsCache);



            } else if (tag == com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.Constant.ON_REFRESH) {

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
