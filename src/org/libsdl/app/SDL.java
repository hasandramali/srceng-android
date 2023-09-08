/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.SecurityException
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.lang.UnsatisfiedLinkError
 *  java.lang.reflect.Method
 */
package org.libsdl.app;

import android.content.Context;
import java.lang.reflect.Method;
import org.libsdl.app.SDLActivity;
import org.libsdl.app.SDLAudioManager;
import org.libsdl.app.SDLControllerManager;

public class SDL {
    protected static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void initialize() {
        SDL.setContext(null);
        SDLActivity.initialize();
        SDLAudioManager.initialize();
        SDLControllerManager.initialize();
    }

    public static void loadLibrary(String string2) throws UnsatisfiedLinkError, SecurityException, NullPointerException {
        if (string2 == null) {
            throw new NullPointerException("No library name provided.");
        }
        try {
            Class class_ = mContext.getClassLoader().loadClass("com.getkeepsafe.relinker.ReLinker");
            Class class_2 = mContext.getClassLoader().loadClass("com.getkeepsafe.relinker.ReLinker$LoadListener");
            Class class_3 = mContext.getClassLoader().loadClass("android.content.Context");
            Class class_4 = mContext.getClassLoader().loadClass("java.lang.String");
            Object object = class_.getDeclaredMethod("force", new Class[0]).invoke(null, new Object[0]);
            Method method = object.getClass().getDeclaredMethod("loadLibrary", new Class[]{class_3, class_4, class_4, class_2});
            Object[] arrobject = new Object[]{mContext, string2, null, null};
            method.invoke(object, arrobject);
            return;
        }
        catch (Throwable throwable) {
            try {
                System.loadLibrary((String)string2);
                return;
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                throw unsatisfiedLinkError;
            }
            catch (SecurityException securityException) {
                throw securityException;
            }
        }
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static void setupJNI() {
        SDLActivity.nativeSetupJNI();
        SDLAudioManager.nativeSetupJNI();
        SDLControllerManager.nativeSetupJNI();
    }
}

