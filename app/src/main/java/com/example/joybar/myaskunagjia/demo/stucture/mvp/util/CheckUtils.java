package com.example.joybar.myaskunagjia.demo.stucture.mvp.util;

import android.support.annotation.Nullable;

/**
 * Created by joybar on 06/01/2017.
 */

public class CheckUtils {

    public static <T> T checkNotNull(T reference) {
        if(reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if(reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }


}
