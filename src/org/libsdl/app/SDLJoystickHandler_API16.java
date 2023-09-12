/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.view.InputDevice
 *  android.view.InputDevice$MotionRange
 *  android.view.MotionEvent
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.Iterator
 *  java.util.List
 */
package org.libsdl.app;

import android.view.InputDevice;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.libsdl.app.SDLControllerManager;
import org.libsdl.app.SDLJoystickHandler;

class SDLJoystickHandler_API16
extends SDLJoystickHandler {
    private final ArrayList<SDLJoystick> mJoysticks = new ArrayList();

    public int getButtonMask(InputDevice inputDevice) {
        return -1;
    }

    protected SDLJoystick getJoystick(int n) {
        for (SDLJoystick sDLJoystick : this.mJoysticks) {
            if (sDLJoystick.device_id != n) continue;
            return sDLJoystick;
        }
        return null;
    }

    public String getJoystickDescriptor(InputDevice inputDevice) {
        String string2 = inputDevice.getDescriptor();
        if (string2 != null && !string2.isEmpty()) {
            return string2;
        }
        return inputDevice.getName();
    }

    public int getProductId(InputDevice inputDevice) {
        return 0;
    }

    public int getVendorId(InputDevice inputDevice) {
        return 0;
    }

    @Override
    public boolean handleMotionEvent(MotionEvent motionEvent) {
        if ((16777232 & motionEvent.getSource()) != 0) {
            SDLJoystick sDLJoystick;
            int n = motionEvent.getActionIndex();
            if (motionEvent.getActionMasked() == 2 && (sDLJoystick = this.getJoystick(motionEvent.getDeviceId())) != null) {
                for (int i = 0; i < sDLJoystick.axes.size(); ++i) {
                    InputDevice.MotionRange motionRange = (InputDevice.MotionRange)sDLJoystick.axes.get(i);
                    float f = 2.0f * ((motionEvent.getAxisValue(motionRange.getAxis(), n) - motionRange.getMin()) / motionRange.getRange()) - 1.0f;
                    SDLControllerManager.onNativeJoy(sDLJoystick.device_id, i, f);
                }
                for (int i = 0; i < sDLJoystick.hats.size() / 2; ++i) {
                    int n2 = Math.round((float)motionEvent.getAxisValue(((InputDevice.MotionRange)sDLJoystick.hats.get(i * 2)).getAxis(), n));
                    int n3 = Math.round((float)motionEvent.getAxisValue(((InputDevice.MotionRange)sDLJoystick.hats.get(1 + i * 2)).getAxis(), n));
                    SDLControllerManager.onNativeHat(sDLJoystick.device_id, i, n2, n3);
                }
            }
        }
        return true;
    }

    @Override
    public void pollInputDevices() {
        int[] arrn = InputDevice.getDeviceIds();
        int n = arrn.length;
        for (int i = 0; i < n; ++i) {
            int n2 = arrn[i];
            if (!SDLControllerManager.isDeviceSDLJoystick(n2) || this.getJoystick(n2) != null) continue;
            InputDevice inputDevice = InputDevice.getDevice((int)n2);
            SDLJoystick sDLJoystick = new SDLJoystick();
            sDLJoystick.device_id = n2;
            sDLJoystick.name = inputDevice.getName();
            sDLJoystick.desc = this.getJoystickDescriptor(inputDevice);
            sDLJoystick.axes = new ArrayList();
            sDLJoystick.hats = new ArrayList();
            List list = inputDevice.getMotionRanges();
            Collections.sort((List)list, (Comparator)new RangeComparator());
            for (InputDevice.MotionRange motionRange : list) {
                if ((16 & motionRange.getSource()) == 0) continue;
                if (motionRange.getAxis() == 15 || motionRange.getAxis() == 16) {
                    sDLJoystick.hats.add((Object)motionRange);
                    continue;
                }
                sDLJoystick.axes.add((Object)motionRange);
            }
            this.mJoysticks.add((Object)sDLJoystick);
            SDLControllerManager.nativeAddJoystick(sDLJoystick.device_id, sDLJoystick.name, sDLJoystick.desc, this.getVendorId(inputDevice), this.getProductId(inputDevice), false, this.getButtonMask(inputDevice), sDLJoystick.axes.size(), sDLJoystick.hats.size() / 2, 0);
        }
        ArrayList arrayList = null;
        Iterator iterator = this.mJoysticks.iterator();
        block2 : while (iterator.hasNext()) {
            int n3 = ((SDLJoystick)iterator.next()).device_id;
            int n4 = 0;
            do {
                if (n4 >= arrn.length || n3 == arrn[n4]) {
                    if (n4 != arrn.length) continue block2;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    Integer n5 = n3;
                    arrayList.add((Object)n5);
                    continue block2;
                }
                ++n4;
            } while (true);
        }
        if (arrayList != null) {
            Iterator iterator2 = arrayList.iterator();
            block4 : while (iterator2.hasNext()) {
                int n6 = (Integer)iterator2.next();
                SDLControllerManager.nativeRemoveJoystick(n6);
                for (int i = 0; i < this.mJoysticks.size(); ++i) {
                    if (((SDLJoystick)this.mJoysticks.get((int)i)).device_id != n6) continue;
                    this.mJoysticks.remove(i);
                    continue block4;
                }
            }
        }
    }

    static class RangeComparator
    implements Comparator<InputDevice.MotionRange> {
        RangeComparator() {
        }

        /*
         * Enabled aggressive block sorting
         */
        public int compare(InputDevice.MotionRange motionRange, InputDevice.MotionRange motionRange2) {
            int n = motionRange.getAxis();
            int n2 = motionRange2.getAxis();
            if (n == 22) {
                n = 23;
            } else if (n == 23) {
                n = 22;
            }
            if (n2 == 22) {
                n2 = 23;
                return n - n2;
            }
            if (n2 != 23) return n - n2;
            n2 = 22;
            return n - n2;
        }
    }

    static class SDLJoystick {
        public ArrayList<InputDevice.MotionRange> axes;
        public String desc;
        public int device_id;
        public ArrayList<InputDevice.MotionRange> hats;
        public String name;

        SDLJoystick() {
        }
    }

}

