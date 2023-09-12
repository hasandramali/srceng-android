/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.MotionEvent
 *  android.view.View
 */
package org.libsdl.app;

import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import org.libsdl.app.SDLActivity;
import org.libsdl.app.SDLControllerManager;
import org.libsdl.app.SDLGenericMotionListener_API24;

class SDLGenericMotionListener_API26
extends SDLGenericMotionListener_API24 {
    private boolean mRelativeModeEnabled;

    SDLGenericMotionListener_API26() {
    }

    @Override
    public float getEventX(MotionEvent motionEvent) {
        return motionEvent.getX(0);
    }

    @Override
    public float getEventY(MotionEvent motionEvent) {
        return motionEvent.getY(0);
    }

    @Override
    public boolean inRelativeMode() {
        return this.mRelativeModeEnabled;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean onGenericMotion(View var1_1, MotionEvent var2_2) {
        switch (var2_2.getSource()) lbl-1000: // 2 sources:
        {
            do {
                default: {
                    do {
                        return false;
                        break;
                    } while (true);
                }
                break;
            } while (true);
            case 513: 
            case 1025: 
            case 16777232: {
                return SDLControllerManager.handleJoystickMotionEvent(var2_2);
            }
            case 8194: 
            case 12290: {
                var4_3 = var2_2.getActionMasked();
                switch (var4_3) {
                    default: {
                        ** continue;
                    }
                    case 7: {
                        SDLActivity.onNativeMouse(0, var4_3, var2_2.getX(0), var2_2.getY(0), false);
                        return true;
                    }
                    case 8: 
                }
                SDLActivity.onNativeMouse(0, var4_3, var2_2.getAxisValue(10, 0), var2_2.getAxisValue(9, 0), false);
                return true;
            }
            case 131076: 
        }
        var3_4 = var2_2.getActionMasked();
        switch (var3_4) {
            default: {
                return false;
            }
            case 7: {
                SDLActivity.onNativeMouse(0, var3_4, var2_2.getX(0), var2_2.getY(0), true);
                return true;
            }
            case 8: 
        }
        SDLActivity.onNativeMouse(0, var3_4, var2_2.getAxisValue(10, 0), var2_2.getAxisValue(9, 0), false);
        return true;
    }

    @Override
    public void reclaimRelativeMouseModeIfNeeded() {
        if (this.mRelativeModeEnabled && !SDLActivity.isDeXMode()) {
            SDLActivity.getContentView().requestPointerCapture();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean setRelativeMouseEnabled(boolean bl) {
        if (SDLActivity.isDeXMode() && Build.VERSION.SDK_INT < 27) {
            return false;
        }
        if (bl) {
            SDLActivity.getContentView().requestPointerCapture();
        } else {
            SDLActivity.getContentView().releasePointerCapture();
        }
        this.mRelativeModeEnabled = bl;
        return true;
    }

    @Override
    public boolean supportsRelativeMouse() {
        return !SDLActivity.isDeXMode() || Build.VERSION.SDK_INT >= 27;
    }
}

