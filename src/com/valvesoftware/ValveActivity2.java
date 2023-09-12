/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.pm.ApplicationInfo
 *  android.util.Log
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 */
package com.valvesoftware;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import java.io.File;
import java.util.Locale;
import me.nillerusr.ExtractAssets;
import me.nillerusr.LauncherActivity;

public class ValveActivity2 {
    public static SharedPreferences mPref;
    private static Activity mSingleton;

    /*
     * Enabled aggressive block sorting
     */
    public static int findGameinfo(String string2) {
        boolean bl;
        block8 : {
            block7 : {
                File file = new File(string2);
                bl = false;
                boolean bl2 = false;
                if (!file.isDirectory()) break block7;
                for (File file2 : file.listFiles()) {
                    if (file2.isDirectory()) {
                        File[] arrfile = file2.listFiles();
                        int n = arrfile.length;
                        for (int i = 0; i < n; ++i) {
                            if (!arrfile[i].getName().toLowerCase().equals((Object)"gameinfo.txt")) continue;
                            bl2 = true;
                        }
                    }
                    if (!file2.getName().toLowerCase().equals((Object)"platform")) continue;
                    bl = true;
                }
                if (bl2) break block8;
            }
            return 0;
        }
        if (!bl) {
            return -1;
        }
        return 1;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void initNatives(Context context, Intent intent) {
        mPref = context.getSharedPreferences("mod", 0);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String string2 = mPref.getString("gamepath", LauncherActivity.getDefaultDir() + "/srceng");
        String string3 = intent.getStringExtra("argv");
        String string4 = intent.getStringExtra("gamedir");
        String string5 = intent.getStringExtra("gamelibdir");
        String string6 = intent.getStringExtra("vpk");
        Log.v((String)"SRCAPK", (String)("argv=" + string3));
        if (string4 == null || string4.isEmpty()) {
            string4 = "hl2";
        }
        if (string3 == null || string3.isEmpty()) {
            string3 = mPref.getString("argv", "-console");
        }
        String string7 = "-game " + string4 + " " + string3;
        if (string5 != null && !string5.isEmpty()) {
            ValveActivity2.setenv("APP_MOD_LIB", string5, 1);
        }
        ExtractAssets.extractAssets(context);
        String string8 = context.getFilesDir().getPath() + "/" + "extras_dir.vpk";
        if (string6 != null && !string6.isEmpty()) {
            string8 = string6 + "," + string8;
        }
        Log.v((String)"SRCAPK", (String)("vpks=" + string8));
        ValveActivity2.setenv("EXTRAS_VPK_PATH", string8, 1);
        ValveActivity2.setenv("LANG", Locale.getDefault().toString(), 1);
        ValveActivity2.setenv("APP_DATA_PATH", applicationInfo.dataDir, 1);
        ValveActivity2.setenv("APP_LIB_PATH", applicationInfo.nativeLibraryDir, 1);
        if (mPref.getBoolean("rodir", false)) {
            ValveActivity2.setenv("VALVE_GAME_PATH", LauncherActivity.getAndroidDataDir(), 1);
        } else {
            ValveActivity2.setenv("VALVE_GAME_PATH", string2, 1);
        }
        Log.v((String)"SRCAPK", (String)("argv=" + string7));
        ValveActivity2.setArgs(string7);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isModGameinfoExists(String string2) {
        File file = new File(string2);
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!file2.isFile() || !file2.getName().toLowerCase().equals((Object)"gameinfo.txt")) continue;
                return true;
            }
        }
        return false;
    }

    private static native void nativeOnActivityResult(Activity var0, int var1, int var2, Intent var3);

    public static int preInit(Context context, Intent intent) {
        mPref = context.getSharedPreferences("mod", 0);
        String string2 = mPref.getString("gamepath", LauncherActivity.getDefaultDir() + "/srceng");
        String string3 = intent.getStringExtra("gamedir");
        if (string3 == null || string3.isEmpty()) {
            string3 = "hl2";
        }
        if (!ValveActivity2.isModGameinfoExists(string2 + "/" + string3)) {
            return 0;
        }
        return ValveActivity2.findGameinfo(string2);
    }

    public static native void setArgs(String var0);

    public static native int setenv(String var0, String var1, int var2);
}

