package me.joybar.loaderapp.loadplugin;

/**
 * Created by joybar on 2017/8/16.
 */

/**
 * 统一含有attach
 * @param <T>
 */
public interface Attachable<T> {
	void attach(T data);
}