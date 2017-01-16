package com.example.joybar.myaskunagjia.demo.stucture.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source.UsersRepository;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.util.ActivityUtils;

/**
 * Created by joybar on 06/01/2017.
 */

public class UsersActivity extends AppCompatActivity {

    private UsersPresenter mUsersPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvp);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);


        UsersFragment UsersFragment =
                (UsersFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (UsersFragment == null) {
            // Create the fragment
            UsersFragment = UsersFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), UsersFragment, R.id.contentFrame);
        }


        // Create the presenter
        UsersRepository usersRepository = Injection.provideUsersRepository(getApplicationContext());
        mUsersPresenter = new UsersPresenter(
                usersRepository, UsersFragment);


    }

}
