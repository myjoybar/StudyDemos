package me.joybarannotation.annotation2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import me.joybarannotation.R;

//http://blog.csdn.net/qq_31168123/article/details/52469652
@InjectContentView(R.layout.activity_main_inject)  //这里就是之前写的注解，参数是布局文件
public class MainActivityInject extends AppCompatActivity {


	@InjectViews(R.id.tv_main)
	private TextView tv_main;

	@InjectViews(R.id.btn_login)
	private Button btn_login;

	@InjectViews(R.id.btn_logoff)
	private Button btn_logoff;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InjectUtils.getInstance().InjectAll(this);//这行代码什么意思呢？用过xUtils的人都知道，也需要有这么一行代码

		tv_main.setText("我是一个改变了文字的TextView");
		btn_login.setText("登录再登录");
		btn_logoff.setText("注销在注销");
	}

	@InjectOnclick({R.id.btn_login, R.id.btn_logoff})
	public void showToast(View view) {
		switch (view.getId()) {
			case R.id.btn_login:
				Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btn_logoff:
				Toast.makeText(this, "注销", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}

}
