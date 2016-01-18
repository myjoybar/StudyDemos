package com.example.joybar.myaskunagjia.demo.xiangji2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import com.example.joybar.myaskunagjia.R;

/**
 * Created by Administrator on 2015/2/1 0001.
 */
public class Activity_ImageMatrixTest extends Activity {

    private GridLayout mGridLayout;
    private ImageMatrixView mImageMatrixView;
    private int mEdWidth;
    private int mEdHeight;
    private float[] mImageMatrix = new float[9];
    private EditText[] mEts = new EditText[9];

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        mImageMatrixView = (ImageMatrixView) findViewById(R.id.view);
        mGridLayout = (GridLayout) findViewById(R.id.grid_group);

        mGridLayout.post(new Runnable() {
            @Override
            public void run() {
                mEdWidth = mGridLayout.getWidth() / 3;
                mEdHeight = mGridLayout.getHeight() / 3;
                addEts();
                initImageMatrix();
            }
        });
    }

    @SuppressLint("NewApi")
    private void addEts() {
        for (int i = 0; i < 9; i++) {
            EditText et = new EditText(Activity_ImageMatrixTest.this);
            et.setGravity(Gravity.CENTER);
            mEts[i] = et;
            mGridLayout.addView(et, mEdWidth, mEdHeight);
        }
    }

    private void initImageMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    private void getImageMatrix() {
        for (int i = 0; i < 9; i++) {
            EditText ed = mEts[i];
            mImageMatrix[i] = Float.parseFloat(ed.getText().toString());
        }
    }

    public void change(View view) {
        getImageMatrix();
        Matrix matrix = new Matrix();
        matrix.setValues(mImageMatrix);
//        matrix.setTranslate(150, 150);
        //     matrix.setScale(2, 2);
        //      matrix.postTranslate(200, 200);
        mImageMatrixView.setImageMatrix(matrix);
        mImageMatrixView.invalidate();
    }

    public void reset(View view) {
        initImageMatrix();
        getImageMatrix();
        Matrix matrix = new Matrix();
        matrix.setValues(mImageMatrix);
        mImageMatrixView.setImageMatrix(matrix);
        mImageMatrixView.invalidate();
    }
}
