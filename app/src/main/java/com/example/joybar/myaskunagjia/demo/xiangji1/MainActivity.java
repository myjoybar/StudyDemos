package com.example.joybar.myaskunagjia.demo.xiangji1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.joybar.myaskunagjia.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_xiangji);
    }

    public void btnPixelsEffect(View view) {
        startActivity(new Intent(this, Activity_PixelsEffect.class));
    }

    public void btnColorMatrix(View view) {
        startActivity(new Intent(this, Activity_ColorMatrix.class));
    }

    public void btnPrimaryColor(View view) {
        startActivity(new Intent(this, Activity_PrimaryColor.class));
    }
}
