/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Vibrator
 *  android.view.InputDevice
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Iterator
 */
package org.libsdl.app;

import android.os.Vibrator;
import android.view.InputDevice;
import java.util.ArrayList;
import java.util.Iterator;
import org.libsdl.app.SDL;
import org.libsdl.app.SDLControllerManager;

class SDLHapticHandler {
    private final ArrayList<SDLHaptic> mHaptics = new ArrayList();

    protected SDLHaptic getHaptic(int n) {
        for (SDLHaptic sDLHaptic : this.mHaptics) {
            if (sDLHaptic.device_id != n) continue;
            return sDLHaptic;
        }
        return null;
    }

    public void pollHapticDevices() {
        int[] arrn = InputDevice.getDeviceIds();
        for (int i = -1 + arrn.length; i > -1; --i) {
            InputDevice inputDevice;
            Vibrator vibrator;
            if (this.getHaptic(arrn[i]) != null || !(vibrator = (inputDevice = InputDevice.getDevice((int)arrn[i])).getVibrator()).hasVibrator()) continue;
            SDLHaptic sDLHaptic = new SDLHaptic();
            sDLHaptic.device_id = arrn[i];
            sDLHaptic.name = inputDevice.getName();
            sDLHaptic.vib = vibrator;
            this.mHaptics.add((Object)sDLHaptic);
            SDLControllerManager.nativeAddHaptic(sDLHaptic.device_id, sDLHaptic.name);
        }
        Vibrator vibrator = (Vibrator)SDL.getContext().getSystemService("vibrator");
        boolean bl = false;
        if (vibrator != null && (bl = vibrator.hasVibrator()) && this.getHaptic(999999) == null) {
            SDLHaptic sDLHaptic = new SDLHaptic();
            sDLHaptic.device_id = 999999;
            sDLHaptic.name = "VIBRATOR_SERVICE";
            sDLHaptic.vib = vibrator;
            this.mHaptics.add((Object)sDLHaptic);
            SDLControllerManager.nativeAddHaptic(sDLHaptic.device_id, sDLHaptic.name);
        }
        ArrayList arrayList = null;
        Iterator iterator = this.mHaptics.iterator();
        block1 : while (iterator.hasNext()) {
            int n = ((SDLHaptic)iterator.next()).device_id;
            int n2 = 0;
            do {
                if (n2 >= arrn.length || n == arrn[n2]) {
                    if (n == 999999 && bl || n2 != arrn.length) continue block1;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((Object)n);
                    continue block1;
                }
                ++n2;
            } while (true);
        }
        if (arrayList != null) {
            Iterator iterator2 = arrayList.iterator();
            block3 : while (iterator2.hasNext()) {
                int n = (Integer)iterator2.next();
                SDLControllerManager.nativeRemoveHaptic(n);
                for (int i = 0; i < this.mHaptics.size(); ++i) {
                    if (((SDLHaptic)this.mHaptics.get((int)i)).device_id != n) continue;
                    this.mHaptics.remove(i);
                    continue block3;
                }
            }
        }
    }

    public void run(int n, float f, int n2) {
        SDLHaptic sDLHaptic = this.getHaptic(n);
        if (sDLHaptic != null) {
            sDLHaptic.vib.vibrate((long)n2);
        }
    }

    public void stop(int n) {
        SDLHaptic sDLHaptic = this.getHaptic(n);
        if (sDLHaptic != null) {
            sDLHaptic.vib.cancel();
        }
    }

    static class SDLHaptic {
        public int device_id;
        public String name;
        public Vibrator vib;

        SDLHaptic() {
        }
    }

}

