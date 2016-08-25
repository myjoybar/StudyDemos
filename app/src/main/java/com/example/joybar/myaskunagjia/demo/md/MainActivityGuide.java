package com.example.joybar.myaskunagjia.demo.md;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;


/**
 * Created by joybar on 15/11/4.
 */
public class MainActivityGuide extends BaseActivity {

    //http://blog.csdn.net/feiduclear_up/article/details/46457433


    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    private Button btn,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,

    btn16,btn17,btn18,btn19,btn20,btn21;


    // private TextView tv_register, tv_reget_pwd;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_md);
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

     //   btn5 = fvById(R.id.btn5);
        // tv_register = fvById(R.id.tv_register);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {
        initButton(btn, R.id.btn, com.example.joybar.myaskunagjia.demo.md.recyclerview.MainActivity.class);
        initButton(btn2,R.id.btn2,com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh.MainActivity.class);
        initButton(btn3,R.id.btn3,com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.MainActivity.class);



        initButton(btn4,R.id.btn4,com.example.joybar.myaskunagjia.demo.md.listview.MainActivity.class);
       initButton(btn5,R.id.btn5,com.example.joybar.myaskunagjia.demo.md.cardview.MainActivity.class);
        initButton(btn6,R.id.btn6,com.example.joybar.myaskunagjia.demo.md.toolbar.MainActivity_Toolbar.class);
        initButton(btn7,R.id.btn7,com.example.joybar.myaskunagjia.demo.md.FloatingActionButton.MainActivity_floating_button.class);

        initButton(btn8,R.id.btn8,com.example.joybar.myaskunagjia.demo.md.TabLayout.MainActivity_TabLayout.class);
        initButton(btn9,R.id.btn9,com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout.MainActivity.class);
        initButton(btn10,R.id.btn10,com.example.joybar.myaskunagjia.demo.md.NavigationView.MainActivity_navigation.class);
        initButton(btn11,R.id.btn11,com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout2.MainActivity.class);
        initButton(btn12,R.id.btn12,com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout3.MainActivity.class);

        initButton(btn13,R.id.btn13,com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout4.MainActivity.class);

        initButton(btn14,R.id.btn14,com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh3.MainActivity.class);
        initButton(btn15,R.id.btn15,com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh4.MainActivity.class);

        initButton(btn16,R.id.btn16,com.example.joybar.myaskunagjia.demo.md.SwipeDismissBehavior.MainActivity.class);

       initButton(btn17,R.id.btn17,com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout6.MainActivity.class);
       initButton(btn18,R.id.btn18,com.example.joybar.myaskunagjia.demo.md.RecyclerViewHeader.MainActivity.class);
       initButton(btn19,R.id.btn19,com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout7.MainActivity.class);
       initButton(btn20,R.id.btn20,com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.MainActivity.class);
       initButton(btn21,R.id.btn21,com.example.joybar.myaskunagjia.demo.md.mddemo.activity.SignInActivity.class);


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


    }

    public void initButton(Button btn, int id, final Class c) {

        btn = (Button) this.findViewById(id);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityGuide.this, c);
                startActivity(intent);

            }
        });
    }


}

