package com.chs.smartupdate.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;

import java.io.File;


public class IntentUtils {
    public static void startActivity(Context ctx, Class clz) {
        Intent intent = new Intent(ctx, clz);
        ctx.startActivity(intent);
    }

    public static void startActivity(Context ctx, Intent intent) {
        ctx.startActivity(intent);
    }

    public static void startActivity(Context ctx, Class clz, Bundle bundle) {
        Intent intent = new Intent(ctx, clz);
        intent.putExtras(bundle);
        ctx.startActivity(intent);
    }

    public static boolean installApk(final Context context, String filePath) {
        try {
            File appFile = new File(filePath);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri fileUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileProvider", appFile);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(appFile), "application/vnd.android.package-archive");
            }
            //兼容8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                boolean hasInstallPermission = context.getPackageManager().canRequestPackageInstalls();
                android.util.Log.d("huasong", "hasInstallPermission:" + hasInstallPermission);
                if (!hasInstallPermission) {
                    //Toast.makeText(context, "需要打开安装未知应用开关", Toast.LENGTH_LONG).show();
                    startInstallPermissionSettingActivity(context);
                    return false;
                }
            }
            if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                context.startActivity(intent);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            android.util.Log.d("huasong", "exception", e);
        }
        return false;
    }
    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void startInstallPermissionSettingActivity(Context context) {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
