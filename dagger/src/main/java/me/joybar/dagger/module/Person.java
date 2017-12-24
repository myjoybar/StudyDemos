package me.joybar.dagger.module;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by joybar on 23/12/2017.
 */

public class Person {

    private String name;

    @Inject
    @Singleton
    public Person(){
        Log.i("dagger","person create!!!");
    }

    public String getName(){
        return "zhang";
    }

}
