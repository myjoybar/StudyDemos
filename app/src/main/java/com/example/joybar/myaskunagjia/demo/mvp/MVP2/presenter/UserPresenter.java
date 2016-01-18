package com.example.joybar.myaskunagjia.demo.mvp.MVP2.presenter;

import com.example.joybar.myaskunagjia.demo.mvp.MVP2.View.IUserView;
import com.example.joybar.myaskunagjia.demo.mvp.MVP2.bean.UserBean;
import com.example.joybar.myaskunagjia.demo.mvp.MVP2.model.IUserModel;
import com.example.joybar.myaskunagjia.demo.mvp.MVP2.model.impl.UserModel;

/**
 * Created by joybar on 15/12/3.
 */
public class UserPresenter {

    private IUserView mUserView ;
    private IUserModel mUserModel ;


    public UserPresenter (IUserView view) {
        mUserView = view;
        mUserModel = new UserModel();
    }

    //业务操作
    public void checkUserName(String name){

        UserBean user = mUserModel.checkNameFormServer(name);

        mUserView.resultDataCallBack(user.getName());


    }
}
