package test.joybar.com.aop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import test.joybar.com.aop.login.CheckLogin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CheckLoginAspectJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_net).setOnClickListener(this);
        findViewById(R.id.btn_permission).setOnClickListener(this);
    }


    @CheckLogin
    private void loginCheck() {
        Log.i(TAG, "已经登陆，执行登陆后的逻辑");
    }

    private void netCheck() {
    }

    private void permissionCheck() {

    }
   // @CheckLogin
    @Override
    public void onClick(View v) {
        String result = "";
        switch (v.getId()) {

            case R.id.btn_login:
                loginCheck();
                break;
            case R.id.btn_net:
                netCheck();
                break;
            case R.id.btn_permission:
                netCheck();
                break;
            default:
                break;
        }

    }
}
