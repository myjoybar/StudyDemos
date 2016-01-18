package com.example.joybar.myaskunagjia.demo.nicehair;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.joybar.myaskunagjia.R;

/**
 * Created by joybar on 15/11/26.
 */
public class MainActivity  extends FragmentActivity {
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private LinearLayout mLayoutLoading;
    private ZoneFragmentAdapter mFragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        mViewPager = (ViewPager) findViewById(R.id.vp_zone_view);
        mRadioGroup = (RadioGroup) findViewById(R.id.zone_radioGroup);
        mLayoutLoading = (LinearLayout) findViewById(R.id.layout_loading);



        mLayoutLoading.setVisibility(View.GONE);
        mFragmentAdapter = new ZoneFragmentAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                switch(position) {
                    case 0:
                        mRadioGroup.check(R.id.btn_zone_topic_all);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.btn_zone_hot_tag);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch(checkedId) {
                    case R.id.btn_zone_topic_all:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.btn_zone_hot_tag:
                        mViewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    protected void replaceFragment(int viewId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commitAllowingStateLoss();
    }
}
