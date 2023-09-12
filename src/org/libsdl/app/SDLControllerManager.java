/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.InputDevice
 *  android.view.MotionEvent
 *  java.lang.Object
 *  java.lang.String
 */
package org.libsdl.app;

import android.os.Build;
import android.view.InputDevice;
import android.view.MotionEvent;
import org.libsdl.app.SDLHapticHandler;
import org.libsdl.app.SDLHapticHandler_API26;
import org.libsdl.app.SDLJoystickHandler;
import org.libsdl.app.SDLJoystickHandler_API16;
import org.libsdl.app.SDLJoystickHandler_API19;

public class SDLControllerManager {
    private static final String TAG = "SDLControllerManager";
    protected static SDLHapticHandler mHapticHandler;
    protected static SDLJoystickHandler mJoystickHandler;

    public static boolean handleJoystickMotionEvent(MotionEvent motionEvent) {
        return mJoystickHandler.handleMotionEvent(motionEvent);
    }

    public static void hapticRun(int n, float f, int n2) {
        mHapticHandler.run(n, f, n2);
    }

    public static void hapticStop(int n) {
        mHapticHandler.stop(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void initialize() {
        if (mJoystickHandler == null) {
            mJoystickHandler = Build.VERSION.SDK_INT >= 19 ? new SDLJoystickHandler_API19() : new SDLJoystickHandler_API16();
        }
        if (mHapticHandler == null) {
            if (Build.VERSION.SDK_INT < 26) {
                mHapticHandler = new SDLHapticHandler();
                return;
            }
            mHapticHandler = new SDLHapticHandler_API26();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isDeviceSDLJoystick(int n) {
        int n2;
        InputDevice inputDevice = InputDevice.getDevice((int)n);
        return inputDevice != null && n >= 0 && (((n2 = inputDevice.getSources()) & 16) != 0 || (n2 & 513) == 513 || (n2 & 1025) == 1025);
    }

    public static native int nativeAddHaptic(int var0, String var1);

    public static native int nativeAddJoystick(int var0, String var1, String var2, int var3, int var4, boolean var5, int var6, int var7, int var8, int var9);

    public static native int nativeRemoveHaptic(int var0);

    public static native int nativeRemoveJoystick(int var0);

    public static native int nativeSetupJNI();

    public static native void onNativeHat(int var0, int var1, int var2, int var3);

    public static native void onNativeJoy(int var0, int var1, float var2);

    public static native int onNativePadDown(int var0, int var1);

    public static native int onNativePadUp(int var0, int var1);

    public static void pollHapticDevices() {
        mHapticHandler.pollHapticDevices();
    }

    public static void pollInputDevices() {
        mJoystickHandler.pollInputDevices();
    }
}

