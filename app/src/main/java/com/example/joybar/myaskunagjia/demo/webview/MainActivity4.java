package com.example.joybar.myaskunagjia.demo.webview;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.T;

public class MainActivity4 extends Activity   implements View.OnClickListener {
    private WebView webView;
    private Button btn_back,btn_refresh;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        init();

    }


    private void init() {

        btn_back = (Button) findViewById(R.id.back);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);
        tv_title = (TextView) findViewById(R.id.tv_title);

        btn_back.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);


        webView = (WebView) findViewById(R.id.wv_webview);





        // 获取WebView配置
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "msg");





         String url="https://tr.pubnative.net/click/native?aid=1007435&t=91r7P46ctGJJ5OlF5nElaBos_s2xuVktNXgXt66M_SXDsNmpbLVVkFdYqCfl89GbKlnAjT2Fx4YztuDg3bpZN1sggd-6pf8BbiaiBpV1YtGmj_KUHKNNw8YgaZbhOnipH4KekfLytpBXNRA1boNGRX1y8OGqHRcSKbLJvLiFph3LSpFoxLAD6iIoBhXOEnN4l9dEXcCYI6hOTpYcjOcNVe3C0iy7gCjG7dlirX6sYDoJLX-WUmm9X9w89Rk6VAqeyrMOMQ12f0SJkFw7yFr_qYf1wXkvFY-6WMGirBKEkJvHlqbJ_tCr7lWOY2YoSoXyuCScMjPB2QkIi91jfoImPwoV1w7-MN-IiYx_kxtFgilvj6DkMcL9dKaOWVV8LP7R_84cYi6Q-6WApubQlbUe7kS52GUAGqFtydC3kwJ4p8peGw8HXWEH9znglEUsKI7Rk4-0n9tm1NP5j0-Ig5DoF19mc3_43Jh0Q2nVXK8u8C6GzQLrqOAzB64-XZFCMPjUcn7cwqZxBM_OMSnJ3a9l5p8KH0pRlZXNlwjtEGAQV5Tg3zAkEhlBZsZtUYv99YXPi-deUyFiwGX1k7cO0d-53w8eCLZCsVf9oqQ-1kWNCiKCCJE7ferMYyLmGZpLu3haJIMLqrxWl9WcUWtQao-wObADOT8q3duxFqlD77u-8koM8mQk7osDcIe5CQ5Z3_3FN71A5T7OlDVrD3d5DmO4BGXXiulKBzJY0rWvm-awPRD9lsDxWhVTzjr0ynPKZxZ4XBgK9vw0VPIM3rIrLKVdvzAQhPaxJRnn3CuCURanD0LQVToJtpNUFV0vw2BEz1EQYYHSd_1kEF-cDEnB_5x7Qz5Pn6YJPtee0Hq2Q_geUEuAlRnFhabLfDBWm7aW8ElIpETcxJk2Zp5doO-ophXgxwXUT0MN9hsFoQJ0GIsaFgtH_f8AvDH2ZtT-HjK_EZhkFRkS0gfOMAMhRvXgISBC9NjDPWGVxQUzw1kzYA-TKAcZUyHflW6uF_VEmVrdmqAKX4zBP2TPpDzGw3XVgwblPNadrsW7__Rfce8DJgrP3IEA7ltFMbAjZmDPhuA0eYfa67_72rKmP0485QpFqMK-Q0odrEXZHVd1bVos8Y0LGlgU_fPBKQhYqTXq1WNXgHIKwlZDIGZYAFdsMpCQkDWUXzteT_421RiKVRgJ0CnWChBnxJXz0tYJKv0lVks&aff_sub=%7B%22rewardType%22%3A%22pubnative%22%2C%22userid%22%3A%22And.7111643893274700-And.7725bbd003aab1882eeed450df65a7e7%22%2C%22md5%22%3A%22ed096bd73f3e4498e43022cef6ecf34b%22%2C%22uniqueKey%22%3A%22c4b6ba9c-ebaf-4106-9f56-af36c313cecb%22%2C%22offerName%22%3A%22Collage%20Master%22%2C%22type%22%3A1%2C%22offerid%22%3A%227408482%22%2C%22rewards%22%3A22%7D";

 String url2="http://mobilegames.com/index.html?affiliate_id=19223&offer_id=43984";
        //WebView加载web资源
        //webView.loadUrl("http://android.dev.bai.ai/share/share/1?android=1");
         webView.loadUrl(url2);


        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是false的时候控制去WebView打开，为true调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                 return false;

              //  return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (title.length()>15) {
                    title = title.substring(0, 15)+"···";
                }
                tv_title.setText(title);
                super.onReceivedTitle(view, title);
            }
        });
    }

    @JavascriptInterface
    public void message(){
        T.showLong(this,"点击分享");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.btn_refresh:
               webView.reload();
            break;
            case R.id.back:
             finish();
                break;
            default:
                break;
        }

    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) &&   webView .canGoBack()) {
//            webView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
//            webView.goBack(); // goBack()表示返回WebView的上一页面
//            webView.postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
////					mWebView.reload();
//                }
//            },100);
//            return true;
//        }else if ((keyCode == KeyEvent.KEYCODE_BACK) && !webView.canGoBack()) {
//            finish();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

    // 打开页面时， 自适应屏幕：
    public void initSet(){
        WebSettings webSettings =   webView .getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
    }

    //  便页面支持缩放：
    public void initSet1(){
        WebSettings webSettings =   webView .getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
    }

    //  如果webView中需要用户手动输入用户名、密码或其他，则webview必须设置支持获取手势焦点。
    public void initSet2(){
        webView.requestFocusFromTouch();
    }

    public void initSet3(){
      //http://www.cnblogs.com/zgz345/p/3768174.html
        WebSettings webSettings =   webView .getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setJavaScriptEnabled(true);  //支持js



        webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小

        webSettings. setSupportZoom(true);  //支持缩放

        webSettings. setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局

        webSettings. supportMultipleWindows();  //多窗口

        webSettings. setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存

        webSettings.  setAllowFileAccess(true);  //设置可以访问文件

        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点


    }
}