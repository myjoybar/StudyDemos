package com.example.joybar.myaskunagjia.demo.lifecycle;

import android.support.v4.app.FragmentActivity;

/**
 * Created by joybar on 2017/6/6.
 */

public class Glide {

	public static RequestManager with(FragmentActivity activity) {
		RequestManagerRetriever retriever = RequestManagerRetriever.get();
		return retriever.get(activity);
	}
}
