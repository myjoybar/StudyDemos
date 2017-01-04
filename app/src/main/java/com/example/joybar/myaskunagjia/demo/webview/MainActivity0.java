package com.example.joybar.myaskunagjia.demo.webview;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivity0 extends BaseActivity {


    private WebView contentWebView = null;
    private TextView msgView = null;
    private Button button;

    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview2);
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
        contentWebView = (WebView) findViewById(R.id.webview);
        msgView = (TextView) findViewById(R.id.msg);
        button = (Button) findViewById(R.id.button);
    }

    @Override
    protected void initData() {
        // 启用javascript
        contentWebView.getSettings().setJavaScriptEnabled(true);
        // 从assets目录下面的加载html
        //  contentWebView.loadUrl("assets/wst.html");
        //     contentWebView.loadUrl(file:///android_asset/index.html);



        contentWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });


     //   contentWebView.loadUrl("file:///android_asset/wst.html");
//
       String  url = "http://xiangzhuankecheng.com";
        contentWebView.loadUrl(url);
    }

    @Override
    protected void setLinstener() {
        button.setOnClickListener(this);
        contentWebView.addJavascriptInterface(MainActivity0.this, "wst");

    }

    @Override
    protected void fillData() {
        // TODO Auto-generated method stub

    }

    //网络请求部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //自定义方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    @JavascriptInterface
    public void startFunction() {
        Toast.makeText(this, "js调用了java函数", Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                msgView.setText(msgView.getText() + "\njs调用了java函数");

            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                msgView.setText(msgView.getText() + "\njs调用了java函数传递参数：" + str);

            }
        });
    }


    @JavascriptInterface
    public void startReturnFunction(final String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                msgView.setText(msgView.getText() + "\njs调用了java函数传递参数：" + str);

            }
        });
    }


    //回调方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

//                // 无参数调用
//                contentWebView.loadUrl("javascript:javacalljs()");
////                // 传递参数调用
//                contentWebView.loadUrl("javascript:javacalljswithargs(" + "'hello world'" + ")");

                contentWebView.loadUrl("javascript:showindex()");
                break;

            default:
                break;
        }

    }


}

