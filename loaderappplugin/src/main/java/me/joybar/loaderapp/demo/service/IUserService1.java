package me.joybar.loaderapp.demo.service;

/**
 * Created by joybar on 2017/8/15.
 */

public interface IUserService1 {
	//　jar cvf IUserService.jar IUserService.class
	String login(String username, String password);
	String logout();
}