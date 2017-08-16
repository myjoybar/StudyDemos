package me.joybar.loaderapp;

import android.os.Bundle;

import me.joybar.plugin.PluginActivity;


public class PluginMainActivity extends PluginActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_plugin);
	}
}
