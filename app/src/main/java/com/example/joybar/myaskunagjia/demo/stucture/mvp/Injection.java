package com.example.joybar.myaskunagjia.demo.stucture.mvp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source.UsersRepository;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source.local.UsersLocalDataSource;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source.remote.UsersRemoteDataSource;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.util.CheckUtils;

/* Created by joybar on 06/01/2017.
 */

public class Injection {
    public static UsersRepository provideUsersRepository(@NonNull Context context) {
        CheckUtils.checkNotNull(context);
    return UsersRepository.getInstance(UsersRemoteDataSource.getInstance(),
                UsersLocalDataSource.getInstance() );
    }
}
