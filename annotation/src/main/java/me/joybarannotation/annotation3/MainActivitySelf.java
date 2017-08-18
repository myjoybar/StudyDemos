package me.joybarannotation.annotation3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import me.joybarannotation.R;
import me.joybarannotation.annotation3.annotation.ContentViewInject;
import me.joybarannotation.annotation3.annotation.ViewClickInject;
import me.joybarannotation.annotation3.annotation.ViewIdInject;
import me.joybarannotation.annotation3.annotation.ViewLongClickInject;


@ContentViewInject(id = R.layout.activity_main_self)
public class MainActivitySelf extends AppCompatActivity {


	public static final String TAG = "MainActivity3";

	@ViewIdInject(id = R.id.btn_action_click1)
	private Button btn_action_click1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewInjectorIml.getInstance().initInject(this);
	}

	@ViewClickInject(id = {R.id.btn_action_click1, R.id.btn_click1})
	public void onBtnClick1() {
		Toast.makeText(this, "ActionClick"+" 事件发生了 ActionClick2", Toast.LENGTH_SHORT).show();

	}

	@ViewLongClickInject(id = R.id.btn_long_click1)
	public void onLongBtnClick() {
		Toast.makeText(this, "onLongBtnClick"+" 事件发生了 onLongBtnClick", Toast.LENGTH_SHORT).show();
		btn_action_click1.setText("aaaaaaaaabbbb");
	}




}
