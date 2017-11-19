package me.joybar.appcomponent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.joybar.appcommponentlib.router1.routerlib.RouterActivity;
import com.joybar.appcommponentlib.router1.routerlib.Rule;
import com.joybar.appcommponentlib.router1.routermanager.RouterManager;
import com.joybar.appcommponentlib.router3.RouterManager3;
import com.joybar.appcommponentlib.router3.Rule3;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // initRouter1();

        initRouter3();


    }

    private void initRouter1() {

//        InjectComponentUtil.inject("com.joybar.appcommponentuser.MainActivity");
//        InjectComponentUtil.inject("com.joybar.appcommponentshop.MainActivity");

        RouterManager.getInstance().addActivityRouter("user", "user_main", com.joybar.appcommponentuser.MainActivity.class);
        RouterManager.getInstance().addActivityRouter("shop", "shop_main", com.joybar.appcommponentshop.MainActivity.class);

        findViewById(R.id.btn_main_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = RouterManager.getInstance().invokeRouter(MainActivity.this,  new Rule("user",RouterActivity.ACTIVITY_PATTERN,"user_main"));
                startActivity(it);
                finish();
            }
        });

    }




    private void initRouter3() {
        RouterManager3.registerRouter(new Rule3("user","user_main",com.joybar.appcommponentuser.MainActivity.class));
        RouterManager3.registerRouter(new Rule3("shop","shop_main",com.joybar.appcommponentshop.MainActivity.class));

        findViewById(R.id.btn_main_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RouterManager3
                        .with(MainActivity.this)
                        .buildRule(new Rule3("user","user_main"))
                        .go();
            }
        });
    }
}
