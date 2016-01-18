package com.example.joybar.myaskunagjia.demo.xiangji2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.joybar.myaskunagjia.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_main_xiangji2);
    }

    public void btnMesh(View view) {
        startActivity(new Intent(this, Activity_MeshViewTest.class));
    }

    public void btnReflect(View view) {
        startActivity(new Intent(this, Activity_ReflectViewTest.class));
    }

    public void btnShader(View view) {
        startActivity(new Intent(this, Activity_BitmapShaderTest.class));
    }

    public void btnXfermode(View view) {
        startActivity(new Intent(this, Activity_RoundRectXfermodTest.class));
    }

    public void btnMatrix(View view) {
        startActivity(new Intent(this, Activity_ImageMatrixTest.class));
    }
}
