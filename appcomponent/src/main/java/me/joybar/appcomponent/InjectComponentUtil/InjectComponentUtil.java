package me.joybar.appcomponent.InjectComponentUtil;

import com.example.Config;
import com.example.RouterHelper;

/**
 * Created by joybar on 05/11/2017.
 */

public class InjectComponentUtil {

    public static void inject(String classPath) {
        String fullName = classPath.replace(".", "_")+ Config.ROUTER_MANAGER_CLASS_NAME_SUFFIX;
        RouterHelper.Init(fullName);
    }


}
