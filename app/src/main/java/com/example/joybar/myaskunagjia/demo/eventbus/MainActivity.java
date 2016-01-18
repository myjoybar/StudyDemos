package com.example.joybar.myaskunagjia.demo.eventbus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.L;

import de.greenrobot.event.EventBus;

/**
 * Created by joybar on 15/11/26.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private String TAG = MainActivity.class.getName();
    private Context context;
    private ImageView imv_back;
    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_eventbus);

        setupView();
        initValue();
        setLinstener();
        fillData();

        // 注册EventBus
        EventBus.getDefault().register(this);

    }

    private void setupView() {
        // imv_back = (ImageView) this.findViewById(R.id.imv_back);
        tv =(TextView) this.findViewById(R.id.tv);
        btn = (Button) this.findViewById(R.id.btn);

    }

    private void initValue() {

        context = this;
    }

    private void setLinstener() {
        // imv_back.setOnClickListener(this);
        btn.setOnClickListener(this);
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

            case R.id.btn:
                Intent intent11 = new Intent(MainActivity.this,
                        MainActivity_event_second.class);
                startActivity(intent11);
                break;

            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);// 反注册EventBus
    }


    public void onEventMainThread(FirstEvent event) {

        String msg = "onEventMainThread收到了消息FirstEvent：" + event.getMsg();

        tv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //下面三个都会接收到


    //SecondEvent接收函数一
    public void onEventMainThread(SecondEvent event) {

        String msg = "onEventMainThread收到了消息SecondEvent：" + event.getMsg();

        tv.setText(msg);

        L.i(TAG, msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //SecondEvent接收函数二
    public void onEventBackgroundThread(SecondEvent event){
        String msg = "onEventBackgroundThread收到了消息SecondEvent：" + event.getMsg();

        tv.setText(msg);
        L.i(TAG, msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    //SecondEvent接收函数三
    public void onEventAsync(SecondEvent event){
        String msg = "onEventAsync收到了消息SecondEvent：" + event.getMsg();

        tv.setText(msg);
        L.i(TAG, msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }




    public void onEventMainThread(ThirdEvent event) {

        String msg = "onEventMainThread收到了消息ThirdEvent：" + event.getMsg();

        tv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


}
