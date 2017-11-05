package com.joybar.appcommponentuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.RegisterRouter;
import com.joybar.appcommponentlib.router1.routerlib.RouterActivity;
import com.joybar.appcommponentlib.router1.routermanager.RouterManager;

@RegisterRouter(patten =RouterActivity.ACTIVITY_SCHEME,scheme = "user_main" )
public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);




        TextView tv = new TextView(this);
        tv.setTextSize(50);
        tv.setText("this is  User!!!, go to Shop");
        setContentView(tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =  RouterManager.getInstance().invokeRouter(MainActivity.this, RouterActivity.ACTIVITY_SCHEME,"shop_main");

                startActivity(it);
                finish();

            }
        });


    }
}
