package com.example.joybar.myaskunagjia.demo.lifecycle;

import com.example.joybar.myaskunagjia.demo.lifecycle.interfaces.Lifecycle;
import com.example.joybar.myaskunagjia.demo.lifecycle.interfaces.LifecycleListener;

/**
 * Created by joybar on 2017/6/5.
 */

public class ApplicationLifecycle implements Lifecycle {
	@Override
	public void addListener(LifecycleListener listener) {
		listener.onStart();
	}
}
