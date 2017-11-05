package com.example;

public class Config {

    public static final String ROUTER_MANAGER_CLASS_NAME_SUFFIX = "$RouterAnnotation";
    public static final String ROUTER_MANAGER_PKN = "com.joybar.routermanager.helper";
    public static final String ROUTER_MANAGER_METHOD_NAME = "addRouter";

    public static final String PATTEN_ACTIVITY = "activity";
    public static final String PATTEN_SERVICE = "service";
    public static final String PATTEN_BROADCAST = "broadcast";


    static String getMethodName(String patten){

        if(PATTEN_ACTIVITY.equals(patten)){
            return "addActivityRouter";
        }else if(PATTEN_SERVICE.equals(patten)){
            return "addServiceRouter";
        }else if(PATTEN_BROADCAST.equals(patten)){
            return "addBroadcastRouter";
        }
        return "";

    }

}
