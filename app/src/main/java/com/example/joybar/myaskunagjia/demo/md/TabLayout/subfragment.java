package com.example.joybar.myaskunagjia.demo.md.TabLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.commom.T;


/**
 * Created by admin on 2015/8/11.
 */
public class subfragment extends Fragment {

    private String mContent="subfragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (null != bundle) {
            mContent = bundle.getString("content");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        T.showShort(getActivity(), "SubFragment1==onCreateView");
        TextView tv = new TextView(getActivity());
        tv.setTextSize(25);
        tv.setBackgroundColor(Color.parseColor("#FFA07A"));
        tv.setText(mContent);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
