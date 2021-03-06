package com.example.joybar.myaskunagjia.demo.webview;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.T;

public class MainActivity extends Activity   implements View.OnClickListener {
    private final static String TAG ="MainActivity";
    private WebView webView;
    private Button btn_back,btn_refresh;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        init();

    }

    private void testLaunchStore( WebView webView){
        String url = "market://details?id=com.crowdstar.covetfashion&referrer=adjust_reftag%3DcAvFeayZTEoXY%26utm_source%3DAppLift%26utm_campaign%3DCF_Android_ALL_Phone_undefined_RON_CID9196%26utm_content%3D%257Baffilliate_id%257D";
        String  url1 = new String(url);
        String marketTag = "market://details?";
        String urlTag = "https://play.google.com/store/apps/details?";
        url1 = url1.replace(marketTag,urlTag);
        Log.d(TAG, "url ="+url );
        Log.d(TAG, "url1 ="+url1 );
        try {
            Log.d(TAG, "launch " );
            MainActivity.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

        }catch (Exception e){
            Log.d(TAG, "launch error" );

           // webView.loadUrl(url1);
        }

        String ss = "https://play.google.com/store/apps/details?id=com.crowdstar.covetfashion&referrer=adjust_reftag%3DcAvFeayZTEoXY%26utm_source%3DAppLift%26utm_campaign%3DCF_Android_ALL_Phone_undefined_RON_CID9196%26utm_content%3D%257Baffilliate_id%257D";
        webView.loadUrl(url1);
//        Log.d(TAG, "ss = "+ss );
        webView.loadUrl(ss);

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







        //WebView加载web资源
//        webView.loadUrl("http://android.dev.bai.ai/share/share/1?android=1");
        // webView.loadUrl("http://baidu.com");
        String url2 = "https://play.google.com/store/apps/details?id=com.scopely.wheeloffortune&referrer=adjust_reftag%3DcE9U5lXYszRvQ%26utm_source%3Dadaction%2Binteractive%26utm_campaign%3Dwof-adaction-US-whitelist-a-android-a-a-a-m-null-s-cpi%26utm_content%3D1790_18133830";
      //  testLaunchStore(webView);
         webView.loadUrl(url2);



        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是false的时候控制去WebView打开，为true调用系统浏览器或第三方浏览器
                Log.d(TAG, "url="+url);
                view.loadUrl(url);
                 return true;

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

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Log.d(TAG, "download_url="+url);
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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