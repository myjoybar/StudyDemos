package com.example.joybar.myaskunagjia.utils;

import com.example.joybar.myaskunagjia.commom.L;

import java.util.List;

/**
 * Created by joybar on 4/20/16.
 */
public class PrintLogUtils {

    public  void logList(List list, String tag){
        for (int i = 0; i < list.size(); i++) {
            L.i(tag+"listdata", list.get(i)+"");
        }

    }
    public  StringBuffer ListToString(List list){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb = sb.append(list.get(i).toString()).append("---");
        }
       return  sb;
    }

}
