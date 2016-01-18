package com.example.joybar.myaskunagjia.demo.nicehair;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * Created by joybar on 15/11/26.
 */
public class Fragment2 extends Fragment{

    static Fragment fragment1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setTextSize(25);
        tv.setBackgroundColor(Color.parseColor("#FFA07A"));
        tv.setText("旅游");
        tv.setGravity(Gravity.CENTER);
        return tv;
    }


    public static android.support.v4.app.Fragment getInstance(){

        if(null == fragment1){
            fragment1 = new Fragment2();
        }
        return fragment1;
    }
}
