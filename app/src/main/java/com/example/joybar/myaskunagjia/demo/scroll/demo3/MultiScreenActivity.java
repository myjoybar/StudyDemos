package com.example.joybar.myaskunagjia.demo.scroll.demo3;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joybar.myaskunagjia.R;

public class MultiScreenActivity extends Activity implements OnClickListener {

	private Button bt_scrollLeft;
	private Button bt_scrollRight;
	private MultiViewGroup mulTiViewGroup  ;
	
	public static int screenWidth  ;
	public static int scrrenHeight ;
	
	private int curscreen = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenWidth = metric.widthPixels ;
		scrrenHeight = metric.heightPixels;		
		System.out.println("screenWidth * scrrenHeight --->" + screenWidth + " * " +scrrenHeight);
		
		setContentView(R.layout.activity_multiview2);
 

		mulTiViewGroup = (MultiViewGroup)findViewById(R.id.mymultiViewGroup);
		
		bt_scrollLeft = (Button) findViewById(R.id.bt_scrollLeft);
		bt_scrollRight = (Button) findViewById(R.id.bt_scrollRight);

		bt_scrollLeft.setOnClickListener(this);
		bt_scrollRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bt_scrollLeft:
			mulTiViewGroup.startMove() ;
			break;
		case R.id.bt_scrollRight:
			mulTiViewGroup.stopMove() ;
			break;
		}
	}

}
