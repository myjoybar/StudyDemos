package com.example.joybar.myaskunagjia.demo.Adapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.demo.Adapter.MultiType.MultiItemCommonAdapter;
import com.example.joybar.myaskunagjia.demo.Adapter.MultiType.MultiItemTypeSupport;
import com.example.joybar.myaskunagjia.demo.Adapter.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
     private Button btn1,btn2,btn3,btn4,btn5;
     private TextView tv1, tv2;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adapter);
        initView();
        initData();
        setLinstener();
        fillData();

        String url1 = "http://img2.mtime.cn/up/188/1070188/2C2031CB-5886-4D06-8784-4C69B3B73090_500.jpg";
        String url2 = "http://imgsrc.baidu.com/forum/pic/item/e61190ef76c6a7ef786a4bbcfdfaaf51f2de66d8.jpg";

        List<UserBean> mList = new ArrayList<>();
        for(int i= 1;i<=60;i++){
            UserBean user = new UserBean();
            if(i%2==0){

                user.setName(i+""+i+""+i);
                user.setUrl(url1);
                user.setType(1);

            }else{
                user.setName(i+""+i+""+i);
                user.setUrl(url2);
                user.setType(2);
            }
            mList.add(user);
        }




        CommonAdapter mAdapter = new CommonAdapter<UserBean>(getApplicationContext(),mList,R.layout.item_user_friend) {
            @Override
            public void convert(ViewHolder viewHolder, final UserBean item, final int position) {
                viewHolder.setText(R.id.tv_friend_name, item.getName());
                viewHolder.getView(R.id.tv_friend_name).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showShort(MainActivity.this, item + " position=" + position);
                    }
                });

                viewHolder.setImageUrl(R.id.img_friend_avatar, item.getUrl());

            }
        };

       ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(mAdapter);





        //多种type
        MultiItemCommonAdapter multiItemCommonAdapter = new MultiItemCommonAdapter<UserBean>(getApplicationContext(), mList, new MultiItemTypeSupport<UserBean>() {
            @Override
            public int getLayoutId(int position, UserBean userBean) {

                if (userBean.getType()==1)
                {
                    return R.layout.item_user_friend;
                }else{
                    return R.layout.item_view;

                }
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int postion, UserBean userBean) {
                if (userBean.getType()==1)
                {
                    return 0;
                }else{
                    return 1;

                }
            }
        }){
            @Override
            public void convert(ViewHolder holder, final UserBean item, final int position) {
                // super.convert(holder, item, position);



                switch (holder.getLayoutId())
                {
                    case R.layout.item_user_friend:
                        holder.setText(R.id.tv_friend_name, item.getName());
                        holder.getView(R.id.tv_friend_name).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                T.showShort(MainActivity.this, item + " position=" + position);
                            }
                        });

                        holder.setImageUrl(R.id.img_friend_avatar, item.getUrl());
                        break;
                    case R.layout.item_view:
                        holder.setText(R.id.text, item.getName());

                        break;
                }


            }
        };


        ListView lv2 = (ListView) findViewById(R.id.lv2);
        lv2.setAdapter(multiItemCommonAdapter);

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
//        btn1 = fvById(R.id.btn1);
//        btn2 = fvById(R.id.btn2);
//        btn3 = fvById(R.id.btn3);
//        btn4 = fvById(R.id.btn4);
//        tv1 = fvById(R.id.tv1);
//        tv2 = fvById(R.id.tv2);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {
//         btn1.setOnClickListener(this);
//         btn2.setOnClickListener(this);
//         btn3.setOnClickListener(this);
//         btn4.setOnClickListener(this);

    }

    @Override
    protected void fillData() {
        // TODO Auto-generated method stub

    }

    //网络请求部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //自定义方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //回调方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
             case R.id.btn1:

              break;
            case R.id.btn2:

                break;
            case R.id.btn3:

                break;
            case R.id.btn4:

                break;

            default:
                break;
        }

    }




}

