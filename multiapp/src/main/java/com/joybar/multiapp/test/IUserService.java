package com.joybar.multiapp.test;

/**
 * Created by joybar on 2017/8/15.
 */

public interface IUserService {
	//　jar cvf IUserService.jar IUserService.class
	String login(String username, String password);
	String logout();
}