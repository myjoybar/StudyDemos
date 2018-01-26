package me.joybar.dagger.demo1.module;

import android.content.Context;
import android.util.Log;

/**
 * Created by joybar on 23/12/2017.
 */

public class Person {

 //   private String name;

//    @Inject
//    @Singleton
//    public Person(){
//        Log.i("dagger","person create!!!");
//    }
//
//    public String getName(){
//        return "zhang";
//    }


    private Context mContext;

    public Person(Context context){
        mContext = context;
        Log.i("dagger","create");
    }



}
