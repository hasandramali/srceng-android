/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.AsyncTask
 *  java.io.BufferedReader
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.net.URL
 *  java.net.URLConnection
 */
package me.nillerusr;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import me.nillerusr.UpdateService;

public class UpdateSystem
extends AsyncTask<String, Integer, String> {
    private static final String app = "srceng-debug.apk";
    private static final String git_url = "https://raw.githubusercontent.com/nillerusr/srceng-deploy";
    String deploy_branch;
    String last_commit;
    Context mContext;

    public UpdateSystem(Context context) {
        this.mContext = context;
        this.deploy_branch = context.getResources().getString(2130968577);
        this.last_commit = context.getResources().getString(2130968576);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static String toString(InputStream inputStream) {
        try {
            String string2;
            BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            while ((string2 = bufferedReader.readLine()) != null) {
                stringBuilder.append(string2);
            }
            return stringBuilder.toString();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return "";
        }
    }

    protected /* varargs */ String doInBackground(String ... arrstring) {
        try {
            String string2 = UpdateSystem.toString(new URL("https://raw.githubusercontent.com/nillerusr/srceng-deploy/" + this.deploy_branch + "/version").openConnection().getInputStream());
            return string2;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(String string2) {
        if (string2 != null && !string2.equals((Object)"") && !this.last_commit.equals((Object)string2)) {
            Intent intent = new Intent(this.mContext, UpdateService.class);
            intent.putExtra("update_url", "https://raw.githubusercontent.com/nillerusr/srceng-deploy/" + this.deploy_branch + "/" + app);
            this.mContext.startService(intent);
        }
    }
}

