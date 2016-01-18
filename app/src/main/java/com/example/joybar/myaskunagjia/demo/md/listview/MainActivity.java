package com.example.joybar.myaskunagjia.demo.md.listview;

/**
 * Created by joybar on 15/11/26.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joybar.library_listview.Constant;
import com.example.joybar.library_listview.PullToRefreshView;
import com.example.joybar.library_listview.bean.User;
import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.L;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements OnClickListener ,PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener
{
    private String TAG = MainActivity.class.getName();
    private Context context;
    private ImageView imv_back;
    private TextView tv_title;
    private Button btn;


    // 列表
    ListView mListView;
    PullToRefreshView mPullToRefreshView;


    List<User> listData = new ArrayList<User>();
    List<User> listDataCache = new ArrayList<User>();
    UserFriendAdapter mAdapter;


    private boolean flag_is_load = false;
    private boolean flag_is_refresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_listview);

        setupView();
        initValue();
        setLinstener();
        fillData();

        getListData(Constant.ON_CREATE);

    }

    private void setupView()
    {
//    	imv_back = (ImageView) this.findViewById(R.id.imv_back);
//    	tv_title =(TextView) this.findViewById(R.id.tv_title);
        //	btn = (Button) this.findViewById(R.id.btn);
        mPullToRefreshView = (PullToRefreshView)
                findViewById(R.id.main_pull_refresh_view);

        mListView = (ListView) findViewById(R.id.xlistView);

    }

    private void initValue()
    {

        context = this;
    }



    private void setLinstener()
    {
        //imv_back.setOnClickListener(this);
        //	btn.setOnClickListener(this);
        // 添加监控 ,放这里的目的是，让 默认的数据加载完毕后，才可以 顶部和底部 拉刷新
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
    }

    private void fillData()
    {
        mAdapter = new UserFriendAdapter(this, listData);
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {

//        case R.id.imv_back:
//
//			 this.finish();
//             break;

//      case R.id.btn:
//			 Intent intent11 = new Intent(MainActivity.this, MyActionbar1.class);
            //startActivity(intent11);
//           break;

            default:
                break;
        }

    }



    public void getListData(final int tag) {
        if (flag_is_load || flag_is_refresh) {

            return;
        }
        listDataCache.clear();
        if (tag == Constant.ON_CREATE) {
            L.i(TAG, "ON_CREATE");
            for (int i = 0; i < 3; ++i) {
                User uBean = new User();
                uBean.setUsername("我是初始化Item" + i);
                listDataCache.add(uBean);

                showData(listDataCache,tag);
            }
        }else if (tag == Constant.ON_LOAD) {
            L.i(TAG, "ON_LOAD");
            flag_is_load = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    // 第一种方式
                    for (int i = 0; i < 3; ++i) {
                        User uBean = new User();
                        uBean.setUsername("我是加载的Item");
                        listDataCache.add(uBean);
                    }



                    showData(listDataCache,tag);
                }
            }, 1000);

        }else if (tag == Constant.ON_REFRESH) {
            L.i(TAG, "ON_REFRESH");
            flag_is_refresh = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {



                    // 第一种方式
                    for (int i = 0; i < 3; ++i) {
                        User uBean = new User();
                        uBean.setUsername("我是刷新的Item");

                        listDataCache.add(uBean);
                    }

                    showData(listDataCache,tag);
                }
            }, 1000);

        }



    }

    private void showData(List<User> itemsCache, int tag) {

        if (itemsCache != null && itemsCache.size() > 0) {
            L.i(TAG, "itemsCache != null && itemsCache.size() > 0");
            L.i(TAG, "tag= "+tag);

            if (tag == Constant.ON_CREATE) {
                L.i(TAG, "showData==ON_LOAD");
                listData.clear();
                listData.addAll(itemsCache);



            } else if (tag == Constant.ON_REFRESH) {
                L.i(TAG, "showData==ON_REFRESH");
                listData.clear();
                listData.addAll(itemsCache);



            }else if (tag == Constant.ON_LOAD) {

                L.i(TAG, "showData==ON_LOAD");
                listData.addAll(itemsCache);
            }

            mAdapter.updateListView(listData);

        }else{
            L.i(TAG, "No Data");
        }

        flag_is_refresh = false;
        flag_is_load = false;

        mPullToRefreshView.onFooterRefreshComplete();
        mPullToRefreshView.onHeaderRefreshComplete();

    }


    @Override
    public void onFooterRefresh(PullToRefreshView view) {

        getListData(Constant.ON_LOAD);



    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {

        getListData(Constant.ON_REFRESH);

    }

}
