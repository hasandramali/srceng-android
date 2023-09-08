/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Process
 *  android.util.Log
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 */
package org.libsdl.app;

import android.os.Process;
import android.util.Log;
import org.libsdl.app.SDLActivity;

class SDLMain
implements Runnable {
    SDLMain() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void run() {
        String string2 = SDLActivity.mSingleton.getMainSharedObject();
        String string3 = SDLActivity.mSingleton.getMainFunction();
        String[] arrstring = SDLActivity.mSingleton.getArguments();
        try {
            Process.setThreadPriority((int)-4);
        }
        catch (Exception exception) {
            Log.v((String)"SDL", (String)("modify thread properties failed " + exception.toString()));
        }
        Log.v((String)"SDL", (String)("Running main function " + string3 + " from library " + string2));
        SDLActivity.nativeRunMain(string2, string3, arrstring);
        Log.v((String)"SDL", (String)"Finished main function");
        if (SDLActivity.mSingleton != null && !SDLActivity.mSingleton.isFinishing()) {
            SDLActivity.mSDLThread = null;
            SDLActivity.mSingleton.finish();
        }
    }
}

