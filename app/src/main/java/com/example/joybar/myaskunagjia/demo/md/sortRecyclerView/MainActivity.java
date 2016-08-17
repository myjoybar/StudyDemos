package com.example.joybar.myaskunagjia.demo.md.sortRecyclerView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.adapter.ContactAdapter;
import com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.model.MembersEntity;
import com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.pinyin.CharacterParser;
import com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.pinyin.PinyinComparator;
import com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.widget.DividerDecoration;
import com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // private Button btn_login;
    // private TextView tv_register, tv_reget_pwd;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    private List<MembersEntity> members = new ArrayList<>();

    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;

    RecyclerView recyclerview;
    private SideBar mSideBar;
    private TextView mUserDialog;


    private ContactAdapter mAdapter;
    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_recyclerview);
        initView();
        initData();
        setLinstener();
        fillData();
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
        // btn_login = fvById(R.id.btn_login);
        // tv_register = fvById(R.id.tv_register);

    }

    @Override
    protected void initData() {

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSideBar = (SideBar) findViewById(R.id.contact_sidebar);
        mUserDialog = (TextView) findViewById(R.id.contact_dialog);
        mSideBar.setTextView(mUserDialog);


        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();



        for (int i = 0; i < 40; i++) {
            MembersEntity membersEntity = new MembersEntity(i + "", "" + (char) (i + 64), "AAA" + i);

            String pinyin = characterParser.getSelling(membersEntity.getUsername());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                membersEntity.setSortLetters(sortString.toUpperCase());
            } else {
                membersEntity.setSortLetters("#");
            }

            members.add(membersEntity);

        }

        Collections.sort(members, pinyinComparator);


        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {

                int position = mAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    recyclerview.scrollToPosition(position);
                }

            }
        });
        if (mAdapter == null) {
            mAdapter = new ContactAdapter(members);
            int orientation = LinearLayoutManager.VERTICAL;
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this, orientation, false);
            recyclerview.setLayoutManager(layoutManager);

            recyclerview.setAdapter(mAdapter);
            final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
            recyclerview.addItemDecoration(headersDecor);
            recyclerview.addItemDecoration(new DividerDecoration(this));

            //   setTouchHelper();
            mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    headersDecor.invalidateHeaders();
                }
            });
        } else {
            mAdapter.notifyDataSetChanged();
        }


    }

    @Override
    protected void setLinstener() {
        // btn_login.setOnClickListener(this);

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
            // case R.id.btn_login:

            // break;

            default:
                break;
        }

    }


}

