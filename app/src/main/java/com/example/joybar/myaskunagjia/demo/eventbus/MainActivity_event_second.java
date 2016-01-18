package com.example.joybar.myaskunagjia.demo.eventbus;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;

import de.greenrobot.event.EventBus;

/**
 * Created by joybar on 15/11/26.
 */
public class MainActivity_event_second extends Activity implements View.OnClickListener
{
    private String TAG = MainActivity_event_second.class.getName();
    private Context context;
    private ImageView imv_back;
    private TextView tv_title;
    private Button btn,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_eventbus_second);

        setupView();
        initValue();
        setLinstener();
        fillData();

    }

    private void setupView()
    {
//    	imv_back = (ImageView) this.findViewById(R.id.imv_back);
//    	tv_title =(TextView) this.findViewById(R.id.tv_title);
        btn = (Button) this.findViewById(R.id.btn);
        btn2 = (Button) this.findViewById(R.id.btn2);
        btn3 = (Button) this.findViewById(R.id.btn3);

    }

    private void initValue()
    {

        context = this;
    }



    private void setLinstener()
    {
        //imv_back.setOnClickListener(this);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    private void fillData()
    {
        // TODO Auto-generated method stub

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

            case R.id.btn:
                EventBus.getDefault().post(
                        new FirstEvent("FirstEvent btn clicked"));
                break;

            case R.id.btn2:
                EventBus.getDefault().post(
                        new SecondEvent ("SecondEvent btn clicked"));
                break;

            case R.id.btn3:
                EventBus.getDefault().post(
                        new ThirdEvent ("ThirdEvent  btn clicked"));
                break;

            default:
                break;
        }

    }

}
