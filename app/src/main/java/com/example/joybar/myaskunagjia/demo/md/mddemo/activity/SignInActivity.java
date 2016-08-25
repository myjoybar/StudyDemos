package com.example.joybar.myaskunagjia.demo.md.mddemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.demo.md.mddemo.fragment.SignInFragment;

/**
 * Created by joybar on 8/25/16.
 */
public class SignInActivity extends AppCompatActivity {

    public static void start(Activity activity, Boolean edit) {
        Intent starter = new Intent(activity, SignInActivity.class);
        //noinspection unchecked
        ActivityCompat.startActivity(activity,
                starter,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.sign_in_container, SignInFragment.newInstance()).commit();
        }
    }
}
