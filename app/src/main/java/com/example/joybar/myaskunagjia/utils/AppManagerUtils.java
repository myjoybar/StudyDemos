package com.example.joybar.myaskunagjia.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by joybar on 3/7/16.
 */
public class AppManagerUtils {
    /**
     * 检查快捷方式是否存在 <br/>
     * <font color=red>注意：</font> 有些手机无法判断是否已经创建过快捷方式<br/>
     * 因此，在创建快捷方式时，请添加<br/>
     * shortcutIntent.putExtra("duplicate", false);// 不允许重复创建<br/>
     * 最好使用{@link #isShortCutExist(Context, String, Intent)}
     * 进行判断，因为可能有些应用生成的快捷方式名称是一样的的<br/>
     */
//    public static boolean isShortCutExist(Context context, String title) {
//        boolean result = false;
//        try {
//            ContentResolver cr = context.getContentResolver();
//            Uri uri = getUriFromLauncher(context);
//            Cursor c = cr.query(uri, new String[]{"title"}, "title=? ", new String[]{title}, null);
//            if (c != null && c.getCount() > 0) {
//                result = true;
//            }
//            if (c != null && !c.isClosed()) {
//                c.close();
//            }
//        } catch (Exception e) {
//            result = false;
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 不一定所有的手机都有效，因为国内大部分手机的桌面不是系统原生的<br/>
//     * 更多请参考{@link #isShortCutExist(Context, String)}<br/>
//     * 桌面有两种，系统桌面(ROM自带)与第三方桌面，一般只考虑系统自带<br/>
//     * 第三方桌面如果没有实现系统响应的方法是无法判断的，比如GO桌面<br/>
//     */
//    public static boolean isShortCutExist(Context context, String title, Intent intent) {
//        boolean result = false;
//        try {
//            ContentResolver cr = context.getContentResolver();
//            Uri uri = getUriFromLauncher(context);
//            Cursor c = cr.query(uri, new String[]{"title", "intent"}, "title=?  and intent=?",
//                    new String[]{title, intent.toUri(0)}, null);
//            if (c != null && c.getCount() > 0) {
//                result = true;
//            }
//            if (c != null && !c.isClosed()) {
//                c.close();
//            }
//        } catch (Exception ex) {
//            result = false;
//            ex.printStackTrace();
//        }
//        return result;
//    }
//
//    private static Uri getUriFromLauncher(Context context) {
//        StringBuilder uriStr = new StringBuilder();
//        String authority = LauncherUtil.getAuthorityFromPermissionDefault(context);
//        if (authority == null || authority.trim().equals("")) {
//            authority = LauncherUtil.getAuthorityFromPermission(context, LauncherUtil.getCurrentLauncherPackageName(context) + ".permission.READ_SETTINGS");
//        }
//        uriStr.append("content://");
//        if (TextUtils.isEmpty(authority)) {
//            int sdkInt = android.os.Build.VERSION.SDK_INT;
//            if (sdkInt < 8) { // Android 2.1.x(API 7)以及以下的
//                uriStr.append("com.android.launcher.settings");
//            } else if (sdkInt < 19) {// Android 4.4以下
//                uriStr.append("com.android.launcher2.settings");
//            } else {// 4.4以及以上
//                uriStr.append("com.android.launcher3.settings");
//            }
//        } else {
//            uriStr.append(authority);
//        }
//        uriStr.append("/favorites?notify=true");
//        return Uri.parse(uriStr.toString());
//    }

    /**
     * 为PackageName的App添加快捷方式
     *
     * @param context context
     * @param pkg     待添加快捷方式的应用包名
     * @return 返回true为正常执行完毕
     */
    public static boolean addShortcutByPackageName(Context context, String pkg) {
        // 快捷方式名
        String title = "unknown";
        // MainActivity完整名
        String mainAct = null;
        // 应用图标标识
        int iconIdentifier = 0;
        // 根据包名寻找MainActivity
        PackageManager pkgMag = context.getPackageManager();
        Intent queryIntent = new Intent(Intent.ACTION_MAIN, null);
        queryIntent.addCategory(Intent.CATEGORY_LAUNCHER);// 重要，添加后可以进入直接已经打开的页面
        queryIntent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        queryIntent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);

        List<ResolveInfo> list = pkgMag.queryIntentActivities(queryIntent,
                PackageManager.GET_ACTIVITIES);
        for (int i = 0; i < list.size(); i++) {
            ResolveInfo info = list.get(i);
            if (info.activityInfo.packageName.equals(pkg)) {
                title = info.loadLabel(pkgMag).toString();
                mainAct = info.activityInfo.name;
                iconIdentifier = info.activityInfo.applicationInfo.icon;
                break;
            }
        }
        if (mainAct == null) {
            // 没有启动类
            return false;
        }
        Intent shortcut = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // 快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        // 不允许重复创建
        shortcut.putExtra("duplicate", false);
        ComponentName comp = new ComponentName(pkg, mainAct);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
                queryIntent.setComponent(comp));
        // 快捷方式的图标
        Context pkgContext = null;
        if (context.getPackageName().equals(pkg)) {
            pkgContext = context;
        } else {
            // 创建第三方应用的上下文环境，为的是能够根据该应用的图标标识符寻找到图标文件。
            try {
                pkgContext = context.createPackageContext(pkg,
                        Context.CONTEXT_IGNORE_SECURITY
                                | Context.CONTEXT_INCLUDE_CODE);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (pkgContext != null) {
            Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource
                    .fromContext(pkgContext, iconIdentifier);
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        }
        // 发送广播，让接收者创建快捷方式
        // 需权限<uses-permission
        // android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
        context.sendBroadcast(shortcut);
        return true;
    }

    /*
      * 获取程序 图标
      */
    public static  Drawable getAppIcon(Context context, String packname) {
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packname, 0);
            return info.loadIcon(context.getPackageManager());
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /*
     *获取程序的版本号
     */
    public static String getAppVersion(Context context, String packname) {

        try {
            PackageInfo packinfo = context.getPackageManager().getPackageInfo(packname, 0);
            return packinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return "";
    }


    /*
      * 获取本app包名
      */
    public static String getAppName(Context context) {
        try {
            String pkName = context.getPackageName();
            return pkName;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return "";
    }



    /*
     * 获取本app版本Name
     */
    public static String getAppVersionName(Context context) {
        try {
            String versionName = context.getPackageManager().getPackageInfo(
                    getAppName(context), 0).versionName;
            return  versionName;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return "";
    }


    /*
      * 获取本app版本Code
      */
    public static int getAppVersionCode(Context context) {
        try {
            int versionCode = context.getPackageManager().getPackageInfo(getAppName(context), 0).versionCode;;
            return versionCode;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return 0;
    }


    /*
     * 获取程序的名字
     */
    public static String getAppName(Context context, String packname) {
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packname, 0);
            return info.loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return "";
    }

    /*
     * 获取程序的权限
     */
    public static String[] getAppPremission(Context context, String packname) {
        try {
            PackageInfo packinfo = context.getPackageManager().getPackageInfo(packname, PackageManager.GET_PERMISSIONS);
            //获取到所有的权限
            return packinfo.requestedPermissions;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return null;
    }


    /*
     * 获取程序的签名
     */
    public static  String getAppSignature(Context context, String packname) {
        try {
            PackageInfo packinfo = context.getPackageManager().getPackageInfo(packname, PackageManager.GET_SIGNATURES);
            //获取到所有的权限
            return packinfo.signatures[0].toCharsString();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return "";
    }

}
