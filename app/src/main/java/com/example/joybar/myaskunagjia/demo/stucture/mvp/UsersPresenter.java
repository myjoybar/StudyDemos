package com.example.joybar.myaskunagjia.demo.stucture.mvp;

import android.support.annotation.NonNull;

import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.User;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source.UsersDataSource;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source.UsersRepository;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.util.CheckUtils;

import java.util.ArrayList;

/**
 * Created by joybar on 06/01/2017.
 */

public class UsersPresenter implements UsersContract.Presenter{


    private final UsersRepository mUsersRepository;

    private final UsersContract.View mUsersView;

    private boolean mFirstLoad = true;

    public UsersPresenter(@NonNull UsersRepository UsersRepository, @NonNull UsersContract.View UsersView) {
        mUsersRepository = CheckUtils.checkNotNull(UsersRepository, "UsersRepository cannot be null");
        mUsersView = CheckUtils.checkNotNull(UsersView, "UsersView cannot be null!");

        mUsersView.setPresenter(this);
    }

    @Override
    public void start() {
        loadUsers(false);
    }

    @Override
    public void loadUsers(boolean forceUpdate) {
        loadUsers(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadUsers(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mUsersView.setLoadingIndicator(true);
        }
        if (forceUpdate) {
            mUsersRepository.refreshUsers();
        }

        mUsersRepository.getUsers(new UsersDataSource.LoadUsersCallback() {
            @Override
            public void onUsersLoaded(ArrayList<User> users) {
                ArrayList<User> usersToShow = new ArrayList<User>();
                for (User user : users) {
                    usersToShow.add(user);
                }

                if (showLoadingUI) {
                    mUsersView.setLoadingIndicator(false);
                }

                processUsers(usersToShow);
            }

            @Override
            public void onUserNotAvailable() {
                mUsersView.showLoadingUsersError();
            }
        });

    }

    private void processUsers(ArrayList<User> Users) {
        if (Users.isEmpty()) {
            // Show a message indicating there are no Users for that filter type.
            //processEmptyUsers();
            mUsersView.showNoUsers();
        } else {
            // Show the list of Users
            mUsersView.showUsers(Users);
        }
    } 
    
    
    
    @Override
    public void addNewUser() {
        mUsersView.showAddUser();
    }

    @Override
    public void openUserDetails(@NonNull User requestedUser) {
        CheckUtils.checkNotNull(requestedUser, "requestedUser cannot be null!");
        mUsersView.showUserDetailsUi(requestedUser.getUserId());
    }

    @Override
    public void clearUsers() {
        mUsersRepository.deleteAllUsers();
        loadUsers(false, false);
    }


}
