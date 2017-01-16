package com.example.joybar.myaskunagjia.demo.databinding.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.joybar.myaskunagjia.BR;


/**
 * Created by joybar on 04/01/2017.
 */
//http://www.jianshu.com/p/7c8b484cda91

public class User extends BaseObservable {
    private String firstName;
    private String lastName;
    private String avatar;

    public User(String avatar, String firstName, String lastName) {
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }
    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }
    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
