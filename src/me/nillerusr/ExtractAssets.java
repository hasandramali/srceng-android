/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.ApplicationInfo
 *  android.content.res.AssetManager
 *  android.util.Log
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.InputStream
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Process
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.reflect.Method
 */
package me.nillerusr;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class ExtractAssets {
    public static int PAK_VERSION = 0;
    public static String TAG = "ExtractAssets";
    public static final String VPK_NAME = "extras_dir.vpk";
    static SharedPreferences mPref;

    static {
        PAK_VERSION = 24;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static int chmod(String string2, int n) {
        try {
            int n2 = Runtime.getRuntime().exec("chmod " + Integer.toOctalString((int)n) + " " + string2).waitFor();
            Log.d((String)TAG, (String)("chmod " + Integer.toOctalString((int)n) + " " + string2 + ": " + n2));
        }
        catch (Exception exception) {
            Log.d((String)TAG, (String)("chmod: Runtime not worked: " + exception.toString()));
        }
        try {
            Class class_ = Class.forName((String)"android.os.FileUtils");
            Class[] arrclass = new Class[]{String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};
            Method method = class_.getMethod("setPermissions", arrclass);
            Object[] arrobject = new Object[]{string2, n, -1, -1};
            return (Integer)method.invoke(null, arrobject);
        }
        catch (Exception exception) {
            Log.d((String)TAG, (String)("chmod: FileUtils not worked: " + exception.toString()));
            return -1;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void extractAsset(Context context, String string2, Boolean bl) {
        AssetManager assetManager = context.getAssets();
        try {
            File file = new File(context.getFilesDir().getPath() + "/" + string2);
            Boolean bl2 = file.exists();
            if (!bl.booleanValue() && bl2.booleanValue()) {
                return;
            }
            InputStream inputStream = assetManager.open(string2);
            FileOutputStream fileOutputStream = new FileOutputStream(context.getFilesDir().getPath() + "/tmp");
            byte[] arrby = new byte[8192];
            do {
                int n;
                if ((n = inputStream.read(arrby)) <= 0) {
                    fileOutputStream.close();
                    File file2 = new File(context.getFilesDir().getPath() + "/tmp");
                    if (bl2.booleanValue()) {
                        file.delete();
                    }
                    file2.renameTo(new File(context.getFilesDir().getPath() + "/" + string2));
                    break;
                }
                fileOutputStream.write(arrby, 0, n);
            } while (true);
        }
        catch (Exception exception) {
            Log.e((String)"SRCAPK", (String)("Failed to extract vpk:" + exception.toString()));
        }
        ExtractAssets.chmod(context.getFilesDir().getPath() + "/" + string2, 511);
    }

    public static void extractAssets(Context context) {
        ExtractAssets.chmod(context.getApplicationInfo().dataDir, 511);
        ExtractAssets.chmod(context.getFilesDir().getPath(), 511);
        ExtractAssets.extractVPK(context);
        ExtractAssets.extractAsset(context, "DroidSansFallback.ttf", false);
        ExtractAssets.extractAsset(context, "LiberationMono-Regular.ttf", false);
        ExtractAssets.extractAsset(context, "dejavusans-boldoblique.ttf", false);
        ExtractAssets.extractAsset(context, "dejavusans-bold.ttf", false);
        ExtractAssets.extractAsset(context, "dejavusans-oblique.ttf", false);
        ExtractAssets.extractAsset(context, "dejavusans.ttf", false);
        ExtractAssets.extractAsset(context, "Itim-Regular.otf", false);
    }

    public static void extractVPK(Context context) {
        if (mPref == null) {
            mPref = context.getSharedPreferences("mod", 0);
        }
        int n = mPref.getInt("pakversion", 0);
        int n2 = PAK_VERSION;
        boolean bl = false;
        if (n != n2) {
            bl = true;
        }
        ExtractAssets.extractAsset(context, VPK_NAME, bl);
        SharedPreferences.Editor editor = mPref.edit();
        editor.putInt("pakversion", PAK_VERSION);
        editor.commit();
    }
}

