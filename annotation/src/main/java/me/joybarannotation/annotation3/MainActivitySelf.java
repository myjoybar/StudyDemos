package me.joybarannotation.annotation3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.joybarannotation.R;
import me.joybarannotation.annotation3.annotation.ContentViewInject;
import me.joybarannotation.annotation3.annotation.ViewClickInject;
import me.joybarannotation.annotation3.annotation.ViewIdInject;
import me.joybarannotation.annotation3.annotation.ViewLongClickInject;


@ContentViewInject(id = R.layout.activity_main_self)
public class MainActivitySelf extends AppCompatActivity implements View.OnClickListener {

 //http://blog.csdn.net/qq_22271479/article/details/65113028
	public static final String TAG = "MainActivity3";

	@ViewIdInject(id = R.id.btn_action_click1,isClickable = true)
	private Button btn_action_click1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewInjectorIml.getInstance().initInject(this);
	}

	@ViewClickInject(id = {R.id.btn_click1})
	public void onBtnClick1() {
		Toast.makeText(this, "ActionClick"+" 事件发生了 ActionClick2", Toast.LENGTH_SHORT).show();

	}

	@ViewLongClickInject(id = R.id.btn_long_click1)
	public void onLongBtnClick() {
		Toast.makeText(this, "onLongBtnClick"+" 事件发生了 onLongBtnClick", Toast.LENGTH_SHORT).show();
		btn_action_click1.setText("aaaaaaaaabbbb");
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();

	}


}
