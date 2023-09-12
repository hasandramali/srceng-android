/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.VibrationEffect
 *  android.os.Vibrator
 *  android.util.Log
 *  java.lang.Exception
 *  java.lang.Math
 *  java.lang.String
 */
package org.libsdl.app;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import org.libsdl.app.SDLHapticHandler;

class SDLHapticHandler_API26
extends SDLHapticHandler {
    SDLHapticHandler_API26() {
    }

    @Override
    public void run(int n, float f, int n2) {
        SDLHapticHandler.SDLHaptic sDLHaptic;
        block8 : {
            block7 : {
                sDLHaptic = this.getHaptic(n);
                if (sDLHaptic == null) break block7;
                Log.d((String)"SDL", (String)("Rtest: Vibe with intensity " + f + " for " + n2));
                if (f != 0.0f) break block8;
                this.stop(n);
            }
            return;
        }
        int n3 = Math.round((float)(255.0f * f));
        if (n3 > 255) {
            n3 = 255;
        }
        if (n3 < 1) {
            this.stop(n);
            return;
        }
        try {
            sDLHaptic.vib.vibrate(VibrationEffect.createOneShot((long)n2, (int)n3));
            return;
        }
        catch (Exception exception) {
            sDLHaptic.vib.vibrate((long)n2);
            return;
        }
    }
}

