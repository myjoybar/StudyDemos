package me.joybar.appcomponent.InjectComponentUtil;

import android.app.Activity;
import android.util.Log;

import com.example.Config;
import com.example.RouterHelper;

/**
 * Created by joybar on 05/11/2017.
 */

public class InjectComponentUtil {

    public static void inject(String className) {
        String fullName = className.replace(".", "_")+ Config.ROUTER_MANAGER_CLASS_NAME;
        RouterHelper.Init(fullName);
    }


}
