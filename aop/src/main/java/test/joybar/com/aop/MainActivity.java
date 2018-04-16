package test.joybar.com.aop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loginCheck();
		netCheck();
		permissionCheck();
	}
	private void loginCheck(){
		findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});


	}
	private void netCheck(){


		findViewById(R.id.btn_net).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});



	}

	private void permissionCheck(){

		findViewById(R.id.btn_permission).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}
