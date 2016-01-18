package com.example.joybar.myaskunagjia.demo.scroll.demo1;


        import android.app.Activity;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AbsListView;
        import android.widget.AbsListView.LayoutParams;
        import android.widget.AbsListView.OnScrollListener;
        import android.widget.Adapter;
        import android.widget.BaseAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.example.joybar.myaskunagjia.R;
        import android.os.Handler;


/**
 * Created by joybar on 15/11/18.
 */


public class WheelActivity extends Activity {

    private ListView listView = null;
    private MyViewGroup myViewGroup;
    private  TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll1);
        tv = (TextView) findViewById(R.id.tv);
        myViewGroup = (MyViewGroup) findViewById(R.id.myviewGroup);

        initData();



    }

    public void scroll(View view) {

        myViewGroup.beginScroll();

    }


    public void initData(){
        new Handler().postDelayed(new Runnable() {
            public void run() {

                tv.setText("getLeft"+tv.getLeft()+"\n"
                        +"getX"+tv.getX()+"\n"
                        +"getTranslationX"+tv.getTranslationX()+"\n"
                        +"getScrollX"+tv.getScrollX()+"\n"
                        +"getScrollY"+tv.getScrollY()+"\n");
//                tv.setText("getLeft"+myViewGroup.getLeft()+"\n"
//                        +"getX"+myViewGroup.getX()+"\n"
//                        +"getTranslationX"+myViewGroup.getTranslationX()+"\n"
//                        +"getScrollX"+myViewGroup.getScrollX()+"\n"
//                        +"getScrollY"+myViewGroup.getScrollY()+"\n");

            }
        }, 200);
    }
}