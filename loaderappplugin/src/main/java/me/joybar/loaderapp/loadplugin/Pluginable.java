package me.joybar.loaderapp.loadplugin;

import android.os.Bundle;

/**
 * Created by joybar on 2017/8/16.
 */

public interface Pluginable {


	void onCreate(Bundle savedInstanceState);

	void onRestart();


	void onStart();

	void onResume();

	void onPause();

	void onStop();

	void onDestroy();
}
