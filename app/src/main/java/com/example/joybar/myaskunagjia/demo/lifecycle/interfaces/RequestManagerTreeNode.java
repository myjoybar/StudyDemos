package com.example.joybar.myaskunagjia.demo.lifecycle.interfaces;

import com.example.joybar.myaskunagjia.demo.lifecycle.RequestManager;

import java.util.Set;

/**
 * Created by joybar on 2017/6/5.
 */

public interface RequestManagerTreeNode {
	/**
	 * Returns all descendant {@link RequestManager}s relative to the context of the current {@link RequestManager}.
	 */
	Set<RequestManager> getDescendants();
}
