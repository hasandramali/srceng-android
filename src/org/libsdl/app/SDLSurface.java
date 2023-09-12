/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.hardware.Sensor
 *  android.hardware.SensorEvent
 *  android.hardware.SensorEventListener
 *  android.hardware.SensorManager
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Handler
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.Display
 *  android.view.InputDevice
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.Surface
 *  android.view.SurfaceHolder
 *  android.view.SurfaceHolder$Callback
 *  android.view.SurfaceView
 *  android.view.View
 *  android.view.View$OnGenericMotionListener
 *  android.view.View$OnKeyListener
 *  android.view.View$OnTouchListener
 *  android.view.WindowManager
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 */
package org.libsdl.app;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import java.lang.reflect.Method;
import org.libsdl.app.SDLActivity;
import org.libsdl.app.SDLControllerManager;
import org.libsdl.app.SDLGenericMotionListener_API12;
import org.libsdl.app.SDLInputConnection;

class SDLSurface
extends SurfaceView
implements SurfaceHolder.Callback,
View.OnKeyListener,
View.OnTouchListener,
SensorEventListener {
    protected Display mDisplay;
    protected float mHeight;
    public boolean mIsSurfaceReady;
    protected SensorManager mSensorManager;
    protected float mWidth;

    public SDLSurface(Context context) {
        super(context);
        this.getHolder().addCallback((SurfaceHolder.Callback)this);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.requestFocus();
        this.setOnKeyListener((View.OnKeyListener)this);
        this.setOnTouchListener((View.OnTouchListener)this);
        this.mDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        this.mSensorManager = (SensorManager)context.getSystemService("sensor");
        this.setOnGenericMotionListener((View.OnGenericMotionListener)SDLActivity.getMotionListener());
        this.mWidth = 1.0f;
        this.mHeight = 1.0f;
        this.mIsSurfaceReady = false;
    }

    public void enableSensor(int n, boolean bl) {
        if (bl) {
            this.mSensorManager.registerListener((SensorEventListener)this, this.mSensorManager.getDefaultSensor(n), 1, null);
            return;
        }
        this.mSensorManager.unregisterListener((SensorEventListener)this, this.mSensorManager.getDefaultSensor(n));
    }

    public Surface getNativeSurface() {
        return this.getHolder().getSurface();
    }

    public void handlePause() {
        this.enableSensor(1, false);
    }

    public void handleResume() {
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.requestFocus();
        this.setOnKeyListener((View.OnKeyListener)this);
        this.setOnTouchListener((View.OnTouchListener)this);
        this.enableSensor(1, true);
    }

    public void onAccuracyChanged(Sensor sensor, int n) {
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onCapturedPointerEvent(MotionEvent motionEvent) {
        int n = motionEvent.getActionMasked();
        switch (n) {
            default: {
                return false;
            }
            case 8: {
                SDLActivity.onNativeMouse(0, n, motionEvent.getAxisValue(10, 0), motionEvent.getAxisValue(9, 0), false);
                return true;
            }
            case 2: 
            case 7: {
                SDLActivity.onNativeMouse(0, n, motionEvent.getX(0), motionEvent.getY(0), true);
                return true;
            }
            case 11: 
            case 12: 
        }
        int n2 = n == 11 ? 0 : 1;
        float f = motionEvent.getX(0);
        float f2 = motionEvent.getY(0);
        SDLActivity.onNativeMouse(motionEvent.getButtonState(), n2, f, f2, true);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onKey(View view, int n, KeyEvent keyEvent) {
        InputDevice inputDevice;
        int n2 = keyEvent.getDeviceId();
        int n3 = keyEvent.getSource();
        if (n3 == 0 && (inputDevice = InputDevice.getDevice((int)n2)) != null) {
            n3 = inputDevice.getSources();
        }
        if (n == 4) {
            n = 111;
        }
        if (SDLControllerManager.isDeviceSDLJoystick(n2)) {
            if (keyEvent.getAction() == 0) {
                if (SDLControllerManager.onNativePadDown(n2, n) == 0) {
                    return true;
                }
            } else if (keyEvent.getAction() == 1) {
                if (SDLControllerManager.onNativePadUp(n2, n) == 0) return true;
            }
        }
        if ((n3 & 257) != 0) {
            if (keyEvent.getAction() == 0) {
                if (SDLActivity.isTextInputEvent(keyEvent)) {
                    SDLInputConnection.nativeCommitText(String.valueOf((char)((char)keyEvent.getUnicodeChar())), 1);
                }
                SDLActivity.onNativeKeyDown(n);
                return true;
            }
            if (keyEvent.getAction() == 1) {
                SDLActivity.onNativeKeyUp(n);
                return true;
            }
        }
        if ((n3 & 8194) == 0) return false;
        if (n != 4) {
            if (n != 125) return false;
        }
        switch (keyEvent.getAction()) {
            case 0: 
            case 1: {
                return true;
            }
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            float f;
            int n;
            float f2;
            switch (this.mDisplay.getRotation()) {
                default: {
                    f2 = sensorEvent.values[0];
                    f = sensorEvent.values[1];
                    n = 3;
                    break;
                }
                case 1: {
                    f2 = -sensorEvent.values[1];
                    f = sensorEvent.values[0];
                    n = 1;
                    break;
                }
                case 3: {
                    f2 = sensorEvent.values[1];
                    f = -sensorEvent.values[0];
                    n = 2;
                    break;
                }
                case 2: {
                    f2 = -sensorEvent.values[0];
                    f = -sensorEvent.values[1];
                    n = 4;
                }
            }
            if (n != SDLActivity.mCurrentOrientation) {
                SDLActivity.mCurrentOrientation = n;
                SDLActivity.onNativeOrientationChanged(n);
            }
            SDLActivity.onNativeAccel(-f2 / 9.80665f, f / 9.80665f, sensorEvent.values[2] / 9.80665f);
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int n;
        int n2 = motionEvent.getDeviceId();
        int n3 = motionEvent.getPointerCount();
        int n4 = motionEvent.getActionMasked();
        int n5 = -1;
        if (n2 < 0) {
            --n2;
        }
        if (motionEvent.getSource() == 8194 || motionEvent.getSource() == 12290) {
            n = 1;
            Object object = motionEvent.getClass().getMethod("getButtonState", new Class[0]).invoke((Object)motionEvent, new Object[0]);
            if (object != null) {
                int n6;
                n = n6 = ((Integer)object).intValue();
            }
        } else {
            switch (n4) {
                default: {
                    return true;
                }
                case 0: 
                case 1: {
                    n5 = 0;
                }
                case 5: 
                case 6: {
                    if (n5 == -1) {
                        n5 = motionEvent.getActionIndex();
                    }
                    int n7 = motionEvent.getPointerId(n5);
                    float f = motionEvent.getX(n5) / this.mWidth;
                    float f2 = motionEvent.getY(n5) / this.mHeight;
                    float f3 = motionEvent.getPressure(n5);
                    if (f3 > 1.0f) {
                        f3 = 1.0f;
                    }
                    SDLActivity.onNativeTouch(n2, n7, n4, f, f2, f3);
                    return true;
                }
                case 2: {
                    int n8 = 0;
                    while (n8 < n3) {
                        int n9 = motionEvent.getPointerId(n8);
                        float f = motionEvent.getX(n8) / this.mWidth;
                        float f4 = motionEvent.getY(n8) / this.mHeight;
                        float f5 = motionEvent.getPressure(n8);
                        if (f5 > 1.0f) {
                            f5 = 1.0f;
                        }
                        SDLActivity.onNativeTouch(n2, n9, n4, f, f4, f5);
                        ++n8;
                    }
                    return true;
                }
                case 3: 
            }
            int n10 = 0;
            while (n10 < n3) {
                int n11 = motionEvent.getPointerId(n10);
                float f = motionEvent.getX(n10) / this.mWidth;
                float f6 = motionEvent.getY(n10) / this.mHeight;
                float f7 = motionEvent.getPressure(n10);
                if (f7 > 1.0f) {
                    f7 = 1.0f;
                }
                SDLActivity.onNativeTouch(n2, n11, 1, f, f6, f7);
                ++n10;
            }
            return true;
            catch (Exception exception) {}
        }
        SDLGenericMotionListener_API12 sDLGenericMotionListener_API12 = SDLActivity.getMotionListener();
        SDLActivity.onNativeMouse(n, n4, sDLGenericMotionListener_API12.getEventX(motionEvent), sDLGenericMotionListener_API12.getEventY(motionEvent), sDLGenericMotionListener_API12.inRelativeMode());
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void surfaceChanged(SurfaceHolder surfaceHolder, int n, int n2, int n3) {
        boolean bl;
        block19 : {
            block20 : {
                int n4;
                block18 : {
                    Context context;
                    int n5;
                    int n6;
                    Log.v((String)"SDL", (String)"surfaceChanged()");
                    if (SDLActivity.mSingleton == null) {
                        return;
                    }
                    this.mWidth = n2;
                    this.mHeight = n3;
                    n6 = n2;
                    n5 = n3;
                    try {
                        if (Build.VERSION.SDK_INT >= 17) {
                            DisplayMetrics displayMetrics = new DisplayMetrics();
                            this.mDisplay.getRealMetrics(displayMetrics);
                            n6 = displayMetrics.widthPixels;
                            n5 = displayMetrics.heightPixels;
                        }
                    }
                    catch (Exception exception) {}
                    Context context2 = context = SDLActivity.getContext();
                    synchronized (context2) {
                        SDLActivity.getContext().notifyAll();
                    }
                    Log.v((String)"SDL", (String)("Window size: " + n2 + "x" + n3));
                    Log.v((String)"SDL", (String)("Device size: " + n6 + "x" + n5));
                    SDLActivity.nativeSetScreenResolution(n2, n3, n6, n5, this.mDisplay.getRefreshRate());
                    SDLActivity.onNativeResize();
                    n4 = SDLActivity.mSingleton.getRequestedOrientation();
                    if (n4 != 1 && n4 != 7) break block18;
                    float f = this.mWidth FCMPL this.mHeight;
                    bl = false;
                    if (f > 0) {
                        bl = true;
                    }
                    break block19;
                }
                if (n4 == 0) break block20;
                bl = false;
                if (n4 != 6) break block19;
            }
            float f = this.mWidth FCMPG this.mHeight;
            bl = false;
            if (f < 0) {
                bl = true;
            }
        }
        if (bl) {
            double d = Math.min((float)this.mWidth, (float)this.mHeight);
            if ((double)Math.max((float)this.mWidth, (float)this.mHeight) / d < 1.2) {
                Log.v((String)"SDL", (String)"Don't skip on such aspect-ratio. Could be a square resolution.");
                bl = false;
            }
        }
        if (bl && Build.VERSION.SDK_INT >= 24 && SDLActivity.mSingleton.isInMultiWindowMode()) {
            Log.v((String)"SDL", (String)"Don't skip in Multi-Window");
            bl = false;
        }
        if (bl) {
            Log.v((String)"SDL", (String)"Skip .. Surface is not ready.");
            this.mIsSurfaceReady = false;
            return;
        }
        SDLActivity.onNativeSurfaceChanged();
        this.mIsSurfaceReady = true;
        SDLActivity.mNextNativeState = SDLActivity.NativeState.RESUMED;
        SDLActivity.handleNativeState();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.v((String)"SDL", (String)"surfaceCreated()");
        SDLActivity.onNativeSurfaceCreated();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.v((String)"SDL", (String)"surfaceDestroyed()");
        SDLActivity.mNextNativeState = SDLActivity.NativeState.PAUSED;
        SDLActivity.handleNativeState();
        this.mIsSurfaceReady = false;
        SDLActivity.onNativeSurfaceDestroyed();
    }
}

