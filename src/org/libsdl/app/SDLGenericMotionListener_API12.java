/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnGenericMotionListener
 *  java.lang.Object
 */
package org.libsdl.app;

import android.view.MotionEvent;
import android.view.View;
import org.libsdl.app.SDLActivity;
import org.libsdl.app.SDLControllerManager;

class SDLGenericMotionListener_API12
implements View.OnGenericMotionListener {
    SDLGenericMotionListener_API12() {
    }

    public float getEventX(MotionEvent motionEvent) {
        return motionEvent.getX(0);
    }

    public float getEventY(MotionEvent motionEvent) {
        return motionEvent.getY(0);
    }

    public boolean inRelativeMode() {
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onGenericMotion(View view, MotionEvent motionEvent) {
        switch (motionEvent.getSource()) {
            default: {
                do {
                    return false;
                    break;
                } while (true);
            }
            case 513: 
            case 1025: 
            case 16777232: {
                return SDLControllerManager.handleJoystickMotionEvent(motionEvent);
            }
            case 8194: 
        }
        int n = motionEvent.getActionMasked();
        switch (n) {
            default: {
                return false;
            }
            case 7: {
                SDLActivity.onNativeMouse(0, n, motionEvent.getX(0), motionEvent.getY(0), false);
                return true;
            }
            case 8: 
        }
        SDLActivity.onNativeMouse(0, n, motionEvent.getAxisValue(10, 0), motionEvent.getAxisValue(9, 0), false);
        return true;
    }

    public void reclaimRelativeMouseModeIfNeeded() {
    }

    public boolean setRelativeMouseEnabled(boolean bl) {
        return false;
    }

    public boolean supportsRelativeMouse() {
        return false;
    }
}

