package com.joybar.multiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		boolean deBugModel = BuildConfig.BUILD_TYPE_DEBUG;
		String deBugModelType = BuildConfig.BUILD_TYPE_MODE;
		String vsersionName1 = BuildConfig.BUILD_VERSION_NAME_1;
		tv.setText("deBugModel=" + deBugModel + "/n" + ",deBugModelType=" + deBugModelType);
	}
}
