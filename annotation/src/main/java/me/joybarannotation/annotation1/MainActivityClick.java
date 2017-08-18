package me.joybarannotation.annotation1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import me.joybarannotation.R;

import static me.joybarannotation.R.id.btn_long_click;

//http://blog.csdn.net/czhpxl007/article/details/50677112
public class MainActivityClick extends AppCompatActivity {

	@ActionId(id =R.id.btn_action_click)
	private Button btn_action_click;
	public static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_click);
		//btn_action_click = (Button) findViewById(R.id.btn_action_click);
		ActionClickHandler.processAnnotations(this);
		ActionClick2Handler.processAnnotations(this);
		ActionLongClickHandler.processAnnotations(this);
		ActionIdHandler.processAnnotations(this);
	}


	@Override
	protected void onResume() {
		super.onResume();

	}


	@ActionClick2(value = {R.id.btn_action_click, R.id.btn_click2})
	public void onBtnClick1() {
		Toast.makeText(this, "ActionClick"+" 事件发生了 ActionClick2", Toast.LENGTH_SHORT).show();
		btn_action_click.setText("aaaaaaaaabbbb");
	}

	@ActionLongClick(value = btn_long_click)
	public void onLongBtnClick() {
		Toast.makeText(this, "onLongBtnClick"+" 事件发生了 onLongBtnClick", Toast.LENGTH_SHORT).show();
	}


}
