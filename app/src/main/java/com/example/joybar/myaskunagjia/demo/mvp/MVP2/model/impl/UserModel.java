package com.example.joybar.myaskunagjia.demo.mvp.MVP2.model.impl;

import com.example.joybar.myaskunagjia.demo.mvp.MVP2.bean.UserBean;
import com.example.joybar.myaskunagjia.demo.mvp.MVP2.model.IUserModel;

/**
 * Created by joybar on 15/12/3.
 */
public class UserModel implements IUserModel {

    @Override
    public UserBean checkNameFormServer(String name) {
        if("jim".equals(name)){
            UserBean userBean = new UserBean();
            userBean.setName("jim");
            return  userBean;
        }else{
            UserBean userBean = new UserBean();
            userBean.setName("登陆错误");
            return  userBean;



        }

    }
}
