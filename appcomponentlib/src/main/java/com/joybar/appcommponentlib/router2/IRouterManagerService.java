package com.joybar.appcommponentlib.router2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

/**
 * Created by joybar on 05/11/2017.
 */

public interface IRouterManagerService {

    void setRouterKey();

    void go(Context context);
    void go(Activity activity);
    void go(FragmentActivity activity);
    void go(Fragment fragment);
    void go(android.support.v4.app.Fragment fragment);


}
