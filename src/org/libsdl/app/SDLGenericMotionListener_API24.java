/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.view.MotionEvent
 *  android.view.View
 */
package org.libsdl.app;

import android.view.MotionEvent;
import android.view.View;
import org.libsdl.app.SDLActivity;
import org.libsdl.app.SDLGenericMotionListener_API12;

class SDLGenericMotionListener_API24
extends SDLGenericMotionListener_API12 {
    private boolean mRelativeModeEnabled;

    SDLGenericMotionListener_API24() {
    }

    @Override
    public float getEventX(MotionEvent motionEvent) {
        if (this.mRelativeModeEnabled) {
            return motionEvent.getAxisValue(27);
        }
        return motionEvent.getX(0);
    }

    @Override
    public float getEventY(MotionEvent motionEvent) {
        if (this.mRelativeModeEnabled) {
            return motionEvent.getAxisValue(28);
        }
        return motionEvent.getY(0);
    }

    @Override
    public boolean inRelativeMode() {
        return this.mRelativeModeEnabled;
    }

    @Override
    public boolean onGenericMotion(View view, MotionEvent motionEvent) {
        int n;
        if (this.mRelativeModeEnabled && motionEvent.getSource() == 8194 && (n = motionEvent.getActionMasked()) == 7) {
            SDLActivity.onNativeMouse(0, n, motionEvent.getAxisValue(27), motionEvent.getAxisValue(28), true);
            return true;
        }
        return super.onGenericMotion(view, motionEvent);
    }

    @Override
    public boolean setRelativeMouseEnabled(boolean bl) {
        this.mRelativeModeEnabled = bl;
        return true;
    }

    @Override
    public boolean supportsRelativeMouse() {
        return true;
    }
}

