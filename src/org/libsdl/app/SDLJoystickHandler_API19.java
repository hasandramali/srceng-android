/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.view.InputDevice
 */
package org.libsdl.app;

import android.view.InputDevice;
import org.libsdl.app.SDLJoystickHandler_API16;

class SDLJoystickHandler_API19
extends SDLJoystickHandler_API16 {
    SDLJoystickHandler_API19() {
    }

    @Override
    public int getButtonMask(InputDevice inputDevice) {
        int n = 0;
        int[] arrn = new int[]{96, 97, 99, 100, 4, 110, 108, 106, 107, 102, 103, 19, 20, 21, 22, 109, 23, 104, 105, 98, 101, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203};
        int[] arrn2 = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 16, 1, 32768, 65536, 131072, 262144, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824, Integer.MIN_VALUE, -1, -1, -1, -1};
        boolean[] arrbl = inputDevice.hasKeys(arrn);
        for (int i = 0; i < arrn.length; ++i) {
            if (!arrbl[i]) continue;
            n |= arrn2[i];
        }
        return n;
    }

    @Override
    public int getProductId(InputDevice inputDevice) {
        return inputDevice.getProductId();
    }

    @Override
    public int getVendorId(InputDevice inputDevice) {
        return inputDevice.getVendorId();
    }
}

