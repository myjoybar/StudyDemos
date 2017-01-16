package com.example.joybar.myaskunagjia.demo.stucture.mvp;

import android.support.annotation.NonNull;

import com.example.joybar.myaskunagjia.demo.stucture.mvp.base.BasePresenter;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.base.BaseView;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.User;

import java.util.ArrayList;

/**
 * Created by joybar on 06/01/2017.
 */

public class UsersContract {

    interface Presenter extends BasePresenter {

        void loadUsers(boolean forceUpdate);

        void addNewUser();

        void openUserDetails(@NonNull User requestedUser);

        void clearUsers();

    }

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showUsers(ArrayList<User> Users);

        void showAddUser();

        void showUserDetailsUi(String UserId);


        void showLoadingUsersError();

        void showNoUsers();


        boolean isActive();

    }

}
