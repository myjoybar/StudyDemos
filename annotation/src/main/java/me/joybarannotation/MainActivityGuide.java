package me.joybarannotation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import me.joybarannotation.annotation1.MainActivityClick;
import me.joybarannotation.annotation2.MainActivityInject;
import me.joybarannotation.annotation3.MainActivitySelf;

public class MainActivityGuide extends AppCompatActivity {

	private Button btn1;
	private Button btn2;
	private Button btn3;
	public static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_guide);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivityGuide.this,MainActivityClick.class);
				startActivity(intent);
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivityGuide.this,MainActivityInject.class);
				startActivity(intent);
			}
		});
		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivityGuide.this,MainActivitySelf.class);
				startActivity(intent);
			}
		});
	}


	@Override
	protected void onResume() {
		super.onResume();

	}


}
