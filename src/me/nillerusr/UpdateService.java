/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.app.Service
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.widget.RemoteViews
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package me.nillerusr;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;

public class UpdateService
extends Service {
    static boolean service_work = false;
    Bundle extras;
    NotificationManager nm;

    private void sendNotif() {
        Notification notification = new Notification(2130837505, (CharSequence)"Update avalible", System.currentTimeMillis());
        notification.contentView = new RemoteViews(this.getPackageName(), 2130903044);
        notification.contentIntent = PendingIntent.getActivity((Context)this, (int)0, (Intent)new Intent("android.intent.action.VIEW", Uri.parse((String)this.extras.get("update_url").toString())), (int)0);
        notification.flags = 16 | notification.flags;
        notification.defaults = 4 | notification.defaults;
        notification.defaults = 2 | notification.defaults;
        notification.defaults = 1 | notification.defaults;
        notification.priority = 1 | notification.priority;
        this.nm.notify(1, notification);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.nm = (NotificationManager)this.getSystemService("notification");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int onStartCommand(Intent intent, int n, int n2) {
        if (service_work) return 2;
        service_work = true;
        try {
            this.extras = intent.getExtras();
            this.sendNotif();
            return 2;
        }
        catch (Exception exception) {
            return 2;
        }
    }
}

