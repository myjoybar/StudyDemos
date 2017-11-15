package com.joybar.appcommponentuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.RegisterRouter;
import com.joybar.appcommponentlib.router1.routerlib.RouterActivity;
import com.joybar.appcommponentlib.router1.routerlib.Rule;
import com.joybar.appcommponentlib.router1.routermanager.RouterManager;
import com.joybar.appcommponentlib.router3.RouterManager3;
import com.joybar.appcommponentlib.router3.Rule3;

@RegisterRouter(module = "user", scheme = "user_main")
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
       // setClickForRouter1(tv);
        setClickForRouter3(tv);

    }


    private void  setClickForRouter1(TextView tv){
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = RouterManager.getInstance().invokeRouter(MainActivity.this, new Rule("shop", RouterActivity.ACTIVITY_PATTERN, "shop_main"));

                startActivity(it);
                finish();


            }
        });

    }

    private void  setClickForRouter3(TextView tv){
        final Bundle bundle = new Bundle();
        bundle.putInt("id",10000);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterManager3.with(MainActivity.this)
                        .buildRule(new Rule3("shop","shop_main"))
                        //.withData("id",999)
                        .withBundle(bundle)
                        .go();
                finish();


            }
        });

    }
}
