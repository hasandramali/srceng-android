/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Application
 *  android.app.UiModeManager
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.DialogInterface$OnKeyListener
 *  android.content.Intent
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.LocaleList
 *  android.os.Message
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.util.SparseArray
 *  android.view.Display
 *  android.view.InputDevice
 *  android.view.KeyEvent
 *  android.view.PointerIcon
 *  android.view.Surface
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnSystemUiVisibilityChangeListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.Button
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.io.PrintStream
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.UnsatisfiedLinkError
 *  java.lang.reflect.Field
 *  java.util.ArrayList
 *  java.util.Hashtable
 *  java.util.Locale
 *  java.util.Set
 */
package org.libsdl.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.valvesoftware.ValveActivity2;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Set;
import me.nillerusr.DirchActivity;
import org.libsdl.app.DummyEdit;
import org.libsdl.app.SDL;
import org.libsdl.app.SDLClipboardHandler;
import org.libsdl.app.SDLGenericMotionListener_API12;
import org.libsdl.app.SDLGenericMotionListener_API24;
import org.libsdl.app.SDLGenericMotionListener_API26;
import org.libsdl.app.SDLMain;
import org.libsdl.app.SDLSurface;

public class SDLActivity
extends Activity
implements View.OnSystemUiVisibilityChangeListener {
    static final int COMMAND_CHANGE_TITLE = 1;
    static final int COMMAND_CHANGE_WINDOW_STYLE = 2;
    static final int COMMAND_SET_KEEP_SCREEN_ON = 5;
    static final int COMMAND_TEXTEDIT_HIDE = 3;
    protected static final int COMMAND_USER = 32768;
    static final int REQUEST_PERMISSIONS = 42;
    protected static final int SDL_ORIENTATION_LANDSCAPE = 1;
    protected static final int SDL_ORIENTATION_LANDSCAPE_FLIPPED = 2;
    protected static final int SDL_ORIENTATION_PORTRAIT = 3;
    protected static final int SDL_ORIENTATION_PORTRAIT_FLIPPED = 4;
    protected static final int SDL_ORIENTATION_UNKNOWN = 0;
    private static final int SDL_SYSTEM_CURSOR_ARROW = 0;
    private static final int SDL_SYSTEM_CURSOR_CROSSHAIR = 3;
    private static final int SDL_SYSTEM_CURSOR_HAND = 11;
    private static final int SDL_SYSTEM_CURSOR_IBEAM = 1;
    private static final int SDL_SYSTEM_CURSOR_NO = 10;
    private static final int SDL_SYSTEM_CURSOR_SIZEALL = 9;
    private static final int SDL_SYSTEM_CURSOR_SIZENESW = 6;
    private static final int SDL_SYSTEM_CURSOR_SIZENS = 8;
    private static final int SDL_SYSTEM_CURSOR_SIZENWSE = 5;
    private static final int SDL_SYSTEM_CURSOR_SIZEWE = 7;
    private static final int SDL_SYSTEM_CURSOR_WAIT = 2;
    private static final int SDL_SYSTEM_CURSOR_WAITARROW = 4;
    private static final String TAG = "SDL";
    public static boolean mBrokenLibraries;
    protected static SDLClipboardHandler mClipboardHandler;
    protected static Locale mCurrentLocale;
    public static NativeState mCurrentNativeState;
    protected static int mCurrentOrientation;
    protected static Hashtable<Integer, PointerIcon> mCursors;
    protected static boolean mFullscreenModeActive;
    public static boolean mHasFocus;
    public static final boolean mHasMultiWindow;
    public static boolean mIsInitCalled;
    public static boolean mIsResumedCalled;
    protected static int mLastCursorID;
    protected static ViewGroup mLayout;
    protected static SDLGenericMotionListener_API12 mMotionListener;
    public static NativeState mNextNativeState;
    protected static Thread mSDLThread;
    protected static boolean mScreenKeyboardShown;
    protected static SDLActivity mSingleton;
    protected static SDLSurface mSurface;
    protected static View mTextEdit;
    Handler commandHandler = new SDLCommandHandler();
    protected final int[] messageboxSelection = new int[1];
    private final Runnable rehideSystemUi = new Runnable(){

        public void run() {
            if (Build.VERSION.SDK_INT >= 19) {
                SDLActivity.this.getWindow().getDecorView().setSystemUiVisibility(5894);
            }
        }
    };

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = Build.VERSION.SDK_INT >= 24;
        mHasMultiWindow = bl;
        mBrokenLibraries = true;
    }

    public static String clipboardGetText() {
        return mClipboardHandler.clipboardGetText();
    }

    public static boolean clipboardHasText() {
        return mClipboardHandler.clipboardHasText();
    }

    public static void clipboardSetText(String string2) {
        mClipboardHandler.clipboardSetText(string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int createCustomCursor(int[] arrn, int n, int n2, int n3, int n4) {
        Bitmap bitmap = Bitmap.createBitmap((int[])arrn, (int)n, (int)n2, (Bitmap.Config)Bitmap.Config.ARGB_8888);
        mLastCursorID = 1 + mLastCursorID;
        int n5 = Build.VERSION.SDK_INT;
        int n6 = 0;
        if (n5 < 24) return n6;
        try {
            mCursors.put((Object)mLastCursorID, (Object)PointerIcon.create((Bitmap)bitmap, (float)n3, (float)n4));
            return mLastCursorID;
        }
        catch (Exception exception) {
            return 0;
        }
    }

    public static View getContentView() {
        return mLayout;
    }

    public static Context getContext() {
        return SDL.getContext();
    }

    public static int getCurrentOrientation() {
        switch (((WindowManager)SDLActivity.getContext().getSystemService("window")).getDefaultDisplay().getRotation()) {
            default: {
                return 0;
            }
            case 0: {
                return 3;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 4;
            }
            case 3: 
        }
        return 2;
    }

    public static double getDiagonal() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Activity activity = (Activity)SDLActivity.getContext();
        if (activity == null) {
            return 0.0;
        }
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        double d = (double)displayMetrics.widthPixels / (double)displayMetrics.xdpi;
        double d2 = (double)displayMetrics.heightPixels / (double)displayMetrics.ydpi;
        return Math.sqrt((double)(d * d + d2 * d2));
    }

    public static DisplayMetrics getDisplayDPI() {
        return SDLActivity.getContext().getResources().getDisplayMetrics();
    }

    public static boolean getManifestEnvironmentVariables() {
        boolean bl;
        block6 : {
            block5 : {
                if (SDLActivity.getContext() != null) break block5;
                return false;
            }
            Bundle bundle = SDLActivity.getContext().getPackageManager().getApplicationInfo((String)SDLActivity.getContext().getPackageName(), (int)128).metaData;
            bl = false;
            if (bundle == null) break block6;
            try {
                int n = "SDL_ENV.".length();
                for (String string2 : bundle.keySet()) {
                    if (!string2.startsWith("SDL_ENV.")) continue;
                    SDLActivity.nativeSetenv(string2.substring(n), bundle.get(string2).toString());
                }
            }
            catch (Exception exception) {
                Log.v((String)TAG, (String)("exception " + exception.toString()));
                return false;
            }
            bl = true;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static SDLGenericMotionListener_API12 getMotionListener() {
        block6 : {
            block5 : {
                if (mMotionListener != null) break block5;
                if (Build.VERSION.SDK_INT < 26) break block6;
                mMotionListener = new SDLGenericMotionListener_API26();
            }
            do {
                return mMotionListener;
                break;
            } while (true);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            mMotionListener = new SDLGenericMotionListener_API24();
            return mMotionListener;
        }
        mMotionListener = new SDLGenericMotionListener_API12();
        return mMotionListener;
    }

    public static Surface getNativeSurface() {
        if (mSurface == null) {
            return null;
        }
        return mSurface.getNativeSurface();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void handleNativeState() {
        block10 : {
            block9 : {
                if (mNextNativeState == mCurrentNativeState) break block9;
                if (mNextNativeState == NativeState.INIT) {
                    mCurrentNativeState = mNextNativeState;
                    return;
                }
                if (mNextNativeState == NativeState.PAUSED) {
                    if (mSDLThread != null) {
                        SDLActivity.nativePause();
                    }
                    if (mSurface != null) {
                        mSurface.handlePause();
                    }
                    mCurrentNativeState = mNextNativeState;
                    return;
                }
                if (mNextNativeState == NativeState.RESUMED && SDLActivity.mSurface.mIsSurfaceReady && mHasFocus && mIsResumedCalled) break block10;
            }
            return;
        }
        if (mSDLThread == null) {
            mSDLThread = new Thread((Runnable)new SDLMain(), "SDLThread");
            mSurface.enableSensor(1, true);
            mSDLThread.start();
        } else {
            SDLActivity.nativeResume();
        }
        mSurface.handleResume();
        mCurrentNativeState = mNextNativeState;
    }

    public static void initTouch() {
        int[] arrn = InputDevice.getDeviceIds();
        int n = arrn.length;
        for (int i = 0; i < n; ++i) {
            InputDevice inputDevice = InputDevice.getDevice((int)arrn[i]);
            if (inputDevice == null || (4098 & inputDevice.getSources()) == 0) continue;
            SDLActivity.nativeAddTouch(inputDevice.getId(), inputDevice.getName());
        }
    }

    public static void initialize() {
        mSingleton = null;
        mSurface = null;
        mTextEdit = null;
        mLayout = null;
        mClipboardHandler = null;
        mCursors = new Hashtable();
        mLastCursorID = 0;
        mSDLThread = null;
        mIsResumedCalled = false;
        mHasFocus = true;
        mNextNativeState = NativeState.INIT;
        mCurrentNativeState = NativeState.INIT;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isAndroidTV() {
        return ((UiModeManager)SDLActivity.getContext().getSystemService("uimode")).getCurrentModeType() == 4 || Build.MANUFACTURER.equals((Object)"MINIX") && Build.MODEL.equals((Object)"NEO-U1") || Build.MANUFACTURER.equals((Object)"Amlogic") && Build.MODEL.equals((Object)"X96-W") || Build.MANUFACTURER.equals((Object)"Amlogic") && Build.MODEL.startsWith("TV");
    }

    public static boolean isChromebook() {
        if (SDLActivity.getContext() == null) {
            return false;
        }
        return SDLActivity.getContext().getPackageManager().hasSystemFeature("org.chromium.arc.device_management");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean isDeXMode() {
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        try {
            int n;
            Configuration configuration = SDLActivity.getContext().getResources().getConfiguration();
            Class class_ = configuration.getClass();
            int n2 = class_.getField("SEM_DESKTOP_MODE_ENABLED").getInt((Object)class_);
            if (n2 != (n = class_.getField("semDesktopModeEnabled").getInt((Object)configuration))) return false;
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isScreenKeyboardShown() {
        if (mTextEdit == null || !mScreenKeyboardShown) {
            return false;
        }
        return ((InputMethodManager)SDL.getContext().getSystemService("input_method")).isAcceptingText();
    }

    public static boolean isTablet() {
        return SDLActivity.getDiagonal() >= 7.0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isTextInputEvent(KeyEvent keyEvent) {
        return !keyEvent.isCtrlPressed() && (keyEvent.isPrintingKey() || keyEvent.getKeyCode() == 62);
    }

    public static void manualBackButton() {
        mSingleton.pressBackButton();
    }

    public static void minimizeWindow() {
        if (mSingleton == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        mSingleton.startActivity(intent);
    }

    public static native void nativeAddTouch(int var0, String var1);

    public static native void nativeFocusChanged(boolean var0);

    public static native String nativeGetHint(String var0);

    public static native void nativeLowMemory();

    public static native void nativePause();

    public static native void nativePermissionResult(int var0, boolean var1);

    public static native void nativeQuit();

    public static native void nativeResume();

    public static native int nativeRunMain(String var0, String var1, Object var2);

    public static native void nativeSendQuit();

    public static native void nativeSetScreenResolution(int var0, int var1, int var2, int var3, float var4);

    public static native void nativeSetenv(String var0, String var1);

    public static native int nativeSetupJNI();

    public static native void onNativeAccel(float var0, float var1, float var2);

    public static native void onNativeClipboardChanged();

    public static native void onNativeDropFile(String var0);

    public static native void onNativeKeyDown(int var0);

    public static native void onNativeKeyUp(int var0);

    public static native void onNativeKeyboardFocusLost();

    public static native void onNativeLocaleChanged();

    public static native void onNativeMouse(int var0, int var1, float var2, float var3, boolean var4);

    public static native void onNativeOrientationChanged(int var0);

    public static native void onNativeResize();

    public static native boolean onNativeSoftReturnKey();

    public static native void onNativeSurfaceChanged();

    public static native void onNativeSurfaceCreated();

    public static native void onNativeSurfaceDestroyed();

    public static native void onNativeTouch(int var0, int var1, int var2, float var3, float var4, float var5);

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static int openURL(String string2) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse((String)string2));
            int n = Build.VERSION.SDK_INT >= 21 ? 1207959552 | 524288 : 1207959552 | 524288;
            intent.addFlags(n);
            mSingleton.startActivity(intent);
            return 0;
        }
        catch (Exception exception) {
            return -1;
        }
    }

    public static void requestPermission(String string2, int n) {
        if (Build.VERSION.SDK_INT < 23) {
            SDLActivity.nativePermissionResult(n, true);
            return;
        }
        Activity activity = (Activity)SDLActivity.getContext();
        if (activity.checkSelfPermission(string2) != 0) {
            activity.requestPermissions(new String[]{string2}, n);
            return;
        }
        SDLActivity.nativePermissionResult(n, true);
    }

    public static boolean sendMessage(int n, int n2) {
        if (mSingleton == null) {
            return false;
        }
        return mSingleton.sendCommand(n, n2);
    }

    public static boolean setActivityTitle(String string2) {
        return mSingleton.sendCommand(1, string2);
    }

    public static boolean setCustomCursor(int n) {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                mSurface.setPointerIcon((PointerIcon)mCursors.get((Object)n));
                return true;
            }
            catch (Exception exception) {
                return false;
            }
        }
        return false;
    }

    public static void setOrientation(int n, int n2, boolean bl, String string2) {
        if (mSingleton != null) {
            mSingleton.setOrientationBis(n, n2, bl, string2);
        }
    }

    public static boolean setRelativeMouseEnabled(boolean bl) {
        if (bl && !SDLActivity.supportsRelativeMouse()) {
            return false;
        }
        return SDLActivity.getMotionListener().setRelativeMouseEnabled(bl);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean setSystemCursor(int n) {
        block16 : {
            n2 = 0;
            switch (n) {
                case 0: {
                    n2 = 1000;
                    ** break;
                }
                case 1: {
                    n2 = 1008;
                    ** break;
                }
                case 2: {
                    n2 = 1004;
                    ** break;
                }
                case 3: {
                    n2 = 1007;
                    ** break;
                }
                case 4: {
                    n2 = 1004;
                    ** break;
                }
                case 5: {
                    n2 = 1017;
                    ** break;
                }
                case 6: {
                    n2 = 1016;
                    ** break;
                }
                case 7: {
                    n2 = 1014;
                    ** break;
                }
                case 8: {
                    n2 = 1015;
                    ** break;
                }
                case 9: {
                    n2 = 1020;
                    ** break;
                }
                case 10: {
                    n2 = 1012;
                }
lbl35: // 12 sources:
                default: {
                    break block16;
                }
                case 11: 
            }
            n2 = 1002;
        }
        if (Build.VERSION.SDK_INT < 24) return true;
        try {
            SDLActivity.mSurface.setPointerIcon(PointerIcon.getSystemIcon((Context)SDL.getContext(), (int)n2));
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setWindowStyle(boolean bl) {
        SDLActivity sDLActivity = mSingleton;
        int n = bl ? 1 : 0;
        sDLActivity.sendCommand(2, n);
    }

    public static boolean shouldMinimizeOnFocusLoss() {
        return false;
    }

    public static boolean showTextInput(int n, int n2, int n3, int n4) {
        return SDLActivity.mSingleton.commandHandler.post((Runnable)new ShowTextInputTask(n, n2, n3, n4));
    }

    public static int showToast(String string2, int n, int n2, int n3, int n4) {
        if (mSingleton == null) {
            return -1;
        }
        try {
            mSingleton.runOnUiThread((Runnable)new 1OneShotTask(string2, n, n2, n3, n4));
            return 0;
        }
        catch (Exception exception) {
            return -1;
        }
    }

    public static boolean supportsRelativeMouse() {
        if (Build.VERSION.SDK_INT < 27 && SDLActivity.isDeXMode()) {
            return false;
        }
        return SDLActivity.getMotionListener().supportsRelativeMouse();
    }

    public void applyPermissions(String[] arrstring, int n) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < arrstring.length; ++i) {
            if (this.checkSelfPermission(arrstring[i]) == 0) continue;
            arrayList.add((Object)arrstring[i]);
        }
        if (!arrayList.isEmpty()) {
            String[] arrstring2 = new String[arrayList.size()];
            for (int i = 0; i < arrayList.size(); ++i) {
                arrstring2[i] = (String)arrayList.get(i);
            }
            this.requestPermissions(arrstring2, n);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int n;
        if (mBrokenLibraries || (n = keyEvent.getKeyCode()) == 25 || n == 24 || n == 27 || n == 168 || n == 169) {
            return false;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    protected String[] getArguments() {
        return new String[0];
    }

    protected String[] getLibraries() {
        return new String[]{"SDL2", "launcher"};
    }

    protected String getMainFunction() {
        return "LauncherMainAndroid";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected String getMainSharedObject() {
        String string2;
        String[] arrstring = mSingleton.getLibraries();
        if (arrstring.length > 0) {
            string2 = "lib" + arrstring[-1 + arrstring.length] + ".so";
            do {
                return SDLActivity.getContext().getApplicationInfo().nativeLibraryDir + "/" + string2;
                break;
            } while (true);
        }
        string2 = "liblauncher.so";
        return SDLActivity.getContext().getApplicationInfo().nativeLibraryDir + "/" + string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void init() {
        String string2;
        block22 : {
            block21 : {
                Intent intent;
                block20 : {
                    if (mIsInitCalled) break block21;
                    mIsInitCalled = true;
                    try {
                        Thread.currentThread().setName("SDLActivity");
                    }
                    catch (Exception exception) {
                        Log.v((String)"SDL", (String)("modify thread properties failed " + exception.toString()));
                    }
                    String string3 = "";
                    try {
                        this.loadLibraries();
                        mBrokenLibraries = false;
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                        System.err.println(unsatisfiedLinkError.getMessage());
                        mBrokenLibraries = true;
                        string3 = unsatisfiedLinkError.getMessage();
                    }
                    catch (Exception exception) {
                        System.err.println(exception.getMessage());
                        mBrokenLibraries = true;
                        string3 = exception.getMessage();
                    }
                    if (mBrokenLibraries) {
                        mSingleton = this;
                        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
                        builder.setMessage((CharSequence)("An error occurred while trying to start the application. Please try again and/or reinstall." + System.getProperty((String)"line.separator") + System.getProperty((String)"line.separator") + "Error: " + string3));
                        builder.setTitle((CharSequence)"SDL Error");
                        builder.setPositiveButton((CharSequence)"Exit", new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialogInterface, int n) {
                                SDLActivity.mSingleton.finish();
                            }
                        });
                        builder.setCancelable(false);
                        builder.create().show();
                        return;
                    }
                    int n = ValveActivity2.preInit((Context)this, this.getIntent());
                    if (n != 1) {
                        mBrokenLibraries = true;
                        mSingleton = this;
                        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
                        builder.setTitle((CharSequence)this.getResources().getString(2130968583));
                        if (n == 0) {
                            builder.setMessage((CharSequence)this.getResources().getString(2130968588));
                        } else {
                            builder.setMessage((CharSequence)this.getResources().getString(2130968606));
                        }
                        if (n == 0) {
                            builder.setNegativeButton(2130968582, new DialogInterface.OnClickListener(){

                                public void onClick(DialogInterface dialogInterface, int n) {
                                    Intent intent = new Intent((Context)SDLActivity.this, DirchActivity.class);
                                    intent.addFlags(268435456);
                                    SDLActivity.this.startActivity(intent);
                                    SDLActivity.mSingleton.finish();
                                }
                            });
                        }
                        builder.setPositiveButton(2130968581, new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialogInterface, int n) {
                                SDLActivity.mSingleton.finish();
                            }
                        });
                        builder.setCancelable(false);
                        builder.create().show();
                        return;
                    }
                    SDL.setupJNI();
                    SDL.initialize();
                    mSingleton = this;
                    SDL.setContext((Context)this);
                    intent = this.getIntent();
                    ValveActivity2.initNatives((Context)this, this.getIntent());
                    mClipboardHandler = new SDLClipboardHandler();
                    if (Build.VERSION.SDK_INT >= 19) {
                        this.getWindow().getDecorView().setSystemUiVisibility(5894);
                    }
                    if (Build.VERSION.SDK_INT >= 28) {
                        this.getWindow().getAttributes().layoutInDisplayCutoutMode = 1;
                    }
                    mSurface = new SDLSurface((Context)this.getApplication());
                    mLayout = new RelativeLayout((Context)this);
                    mLayout.addView((View)mSurface);
                    mCurrentOrientation = SDLActivity.getCurrentOrientation();
                    SDLActivity.onNativeOrientationChanged(mCurrentOrientation);
                    try {
                        if (Build.VERSION.SDK_INT < 24) {
                            mCurrentLocale = SDLActivity.getContext().getResources().getConfiguration().locale;
                            break block20;
                        }
                        mCurrentLocale = SDLActivity.getContext().getResources().getConfiguration().getLocales().get(0);
                    }
                    catch (Exception exception) {}
                }
                this.setContentView((View)mLayout);
                SDLActivity.setWindowStyle(false);
                this.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener((View.OnSystemUiVisibilityChangeListener)this);
                if (intent != null && intent.getData() != null && (string2 = intent.getData().getPath()) != null) break block22;
            }
            return;
        }
        Log.v((String)"SDL", (String)("Got filename: " + string2));
        SDLActivity.onNativeDropFile(string2);
    }

    public void loadLibraries() {
        String[] arrstring = this.getLibraries();
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            SDL.loadLibrary(arrstring[i]);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void messageboxCreateAndShow(Bundle bundle) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int[] arrn = bundle.getIntArray("colors");
        if (arrn != null) {
            int n7 = -1 + 1;
            n2 = arrn[n7];
            int n8 = n7 + 1;
            n = arrn[n8];
            int n9 = n8 + 1;
            n6 = arrn[n9];
            int n10 = n9 + 1;
            n3 = arrn[n10];
            n5 = arrn[n10 + 1];
        } else {
            n2 = 0;
            n3 = 0;
            n6 = 0;
            n5 = 0;
            n = 0;
        }
        final AlertDialog alertDialog = new AlertDialog.Builder((Context)this).create();
        alertDialog.setTitle((CharSequence)bundle.getString("title"));
        alertDialog.setCancelable(false);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onDismiss(DialogInterface dialogInterface) {
                int[] arrn;
                int[] arrn2 = arrn = SDLActivity.this.messageboxSelection;
                synchronized (arrn2) {
                    SDLActivity.this.messageboxSelection.notify();
                    return;
                }
            }
        });
        TextView textView = new TextView((Context)this);
        textView.setGravity(17);
        textView.setText((CharSequence)bundle.getString("message"));
        if (n != 0) {
            textView.setTextColor(n);
        }
        int[] arrn2 = bundle.getIntArray("buttonFlags");
        int[] arrn3 = bundle.getIntArray("buttonIds");
        String[] arrstring = bundle.getStringArray("buttonTexts");
        final SparseArray sparseArray = new SparseArray();
        LinearLayout linearLayout = new LinearLayout((Context)this);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        for (int i = 0; i < (n4 = arrstring.length); ++i) {
            Button button = new Button((Context)this);
            final int n11 = arrn3[i];
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    SDLActivity.this.messageboxSelection[0] = n11;
                    alertDialog.dismiss();
                }
            };
            button.setOnClickListener(onClickListener);
            if (arrn2[i] != 0) {
                if ((1 & arrn2[i]) != 0) {
                    sparseArray.put(66, (Object)button);
                }
                if ((2 & arrn2[i]) != 0) {
                    sparseArray.put(111, (Object)button);
                }
            }
            button.setText((CharSequence)arrstring[i]);
            if (n != 0) {
                button.setTextColor(n);
            }
            if (n6 != 0) {
                // empty if block
            }
            if (n3 != 0) {
                Drawable drawable2 = button.getBackground();
                if (drawable2 == null) {
                    button.setBackgroundColor(n3);
                } else {
                    drawable2.setColorFilter(n3, PorterDuff.Mode.MULTIPLY);
                }
            }
            if (n5 != 0) {
                // empty if block
            }
            linearLayout.addView((View)button);
        }
        LinearLayout linearLayout2 = new LinearLayout((Context)this);
        linearLayout2.setOrientation(1);
        linearLayout2.addView((View)textView);
        linearLayout2.addView((View)linearLayout);
        if (n2 != 0) {
            linearLayout2.setBackgroundColor(n2);
        }
        alertDialog.setView((View)linearLayout2);
        DialogInterface.OnKeyListener onKeyListener = new DialogInterface.OnKeyListener(){

            public boolean onKey(DialogInterface dialogInterface, int n, KeyEvent keyEvent) {
                Button button = (Button)sparseArray.get(n);
                if (button != null) {
                    if (keyEvent.getAction() == 1) {
                        button.performClick();
                    }
                    return true;
                }
                return false;
            }
        };
        alertDialog.setOnKeyListener(onKeyListener);
        alertDialog.show();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int messageboxShowMessageBox(int n, String string2, String string3, int[] arrn, int[] arrn2, String[] arrstring, int[] arrn3) {
        int[] arrn4;
        this.messageboxSelection[0] = -1;
        if (arrn.length != arrn2.length && arrn2.length != arrstring.length) {
            return -1;
        }
        final Bundle bundle = new Bundle();
        bundle.putInt("flags", n);
        bundle.putString("title", string2);
        bundle.putString("message", string3);
        bundle.putIntArray("buttonFlags", arrn);
        bundle.putIntArray("buttonIds", arrn2);
        bundle.putStringArray("buttonTexts", arrstring);
        bundle.putIntArray("colors", arrn3);
        this.runOnUiThread(new Runnable(){

            public void run() {
                SDLActivity.this.messageboxCreateAndShow(bundle);
            }
        });
        int[] arrn5 = arrn4 = this.messageboxSelection;
        synchronized (arrn5) {
            try {
                this.messageboxSelection.wait();
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
                return -1;
            }
            return this.messageboxSelection[0];
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onBackPressed() {
        String string2 = SDLActivity.nativeGetHint("SDL_ANDROID_TRAP_BACK_BUTTON");
        if (string2 != null && string2.equals((Object)"1") || this.isFinishing()) {
            return;
        }
        super.onBackPressed();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onConfigurationChanged(Configuration configuration) {
        Log.v((String)"SDL", (String)"onConfigurationChanged()");
        super.onConfigurationChanged(configuration);
        if (mBrokenLibraries || mCurrentLocale != null && mCurrentLocale.equals((Object)configuration.locale)) {
            return;
        }
        mCurrentLocale = configuration.locale;
        SDLActivity.onNativeLocaleChanged();
    }

    protected void onCreate(Bundle bundle) {
        Log.v((String)"SDL", (String)("Device: " + Build.DEVICE));
        Log.v((String)"SDL", (String)("Model: " + Build.MODEL));
        Log.v((String)"SDL", (String)"onCreate()");
        super.onCreate(bundle);
        mIsInitCalled = false;
        if (Build.VERSION.SDK_INT >= 23) {
            this.applyPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, 42);
        }
        if (this.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0 && this.checkSelfPermission("android.permission.RECORD_AUDIO") == 0) {
            this.init();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onDestroy() {
        Log.v((String)"SDL", (String)"onDestroy()");
        if (mBrokenLibraries) {
            super.onDestroy();
            return;
        }
        if (mSDLThread != null) {
            SDLActivity.nativeSendQuit();
            try {
                mSDLThread.join();
            }
            catch (Exception exception) {
                Log.v((String)"SDL", (String)("Problem stopping SDLThread: " + (Object)((Object)exception)));
            }
        }
        SDLActivity.nativeQuit();
        super.onDestroy();
    }

    public void onLowMemory() {
        Log.v((String)"SDL", (String)"onLowMemory()");
        super.onLowMemory();
        if (mBrokenLibraries) {
            return;
        }
        SDLActivity.nativeLowMemory();
    }

    protected void onPause() {
        Log.v((String)"SDL", (String)"onPause()");
        super.onPause();
        if (!mHasMultiWindow) {
            this.pauseNativeThread();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onRequestPermissionsResult(int n, String[] arrstring, int[] arrn) {
        if (n != 42) return;
        int n2 = arrn.length;
        for (int i = 0; i < n2; ++i) {
            if (arrn[i] != -1) continue;
            Toast.makeText((Context)this, (int)2130968587, (int)1).show();
            this.finish();
            return;
        }
        this.init();
    }

    protected void onResume() {
        Log.v((String)"SDL", (String)"onResume()");
        super.onResume();
        if (!mHasMultiWindow) {
            this.resumeNativeThread();
        }
    }

    protected void onStart() {
        Log.v((String)"SDL", (String)"onStart()");
        super.onStart();
        if (mHasMultiWindow) {
            this.resumeNativeThread();
        }
    }

    protected void onStop() {
        Log.v((String)"SDL", (String)"onStop()");
        super.onStop();
        if (mHasMultiWindow) {
            this.pauseNativeThread();
        }
    }

    public void onSystemUiVisibilityChange(int n) {
        Handler handler;
        if (mFullscreenModeActive && ((n & 4) == 0 || (n & 2) == 0) && (handler = this.getWindow().getDecorView().getHandler()) != null) {
            handler.removeCallbacks(this.rehideSystemUi);
            handler.postDelayed(this.rehideSystemUi, 2000L);
        }
    }

    protected boolean onUnhandledMessage(int n, Object object) {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onWindowFocusChanged(boolean bl) {
        block5 : {
            block4 : {
                super.onWindowFocusChanged(bl);
                Log.v((String)"SDL", (String)("onWindowFocusChanged(): " + bl));
                if (mBrokenLibraries) break block4;
                mHasFocus = bl;
                if (bl) {
                    mNextNativeState = NativeState.RESUMED;
                    SDLActivity.getMotionListener().reclaimRelativeMouseModeIfNeeded();
                    SDLActivity.handleNativeState();
                    SDLActivity.nativeFocusChanged(true);
                    return;
                }
                SDLActivity.nativeFocusChanged(false);
                if (!mHasMultiWindow) break block5;
            }
            return;
        }
        mNextNativeState = NativeState.PAUSED;
        SDLActivity.handleNativeState();
    }

    protected void pauseNativeThread() {
        mNextNativeState = NativeState.PAUSED;
        mIsResumedCalled = false;
        if (mBrokenLibraries) {
            return;
        }
        SDLActivity.handleNativeState();
    }

    public void pressBackButton() {
        this.runOnUiThread(new Runnable(){

            public void run() {
                if (!SDLActivity.this.isFinishing()) {
                    SDLActivity.this.superOnBackPressed();
                }
            }
        });
    }

    protected void resumeNativeThread() {
        mNextNativeState = NativeState.RESUMED;
        mIsResumedCalled = true;
        if (mBrokenLibraries) {
            return;
        }
        SDLActivity.handleNativeState();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    boolean sendCommand(int n, Object object) {
        Message message = this.commandHandler.obtainMessage();
        message.arg1 = n;
        message.obj = object;
        boolean bl = this.commandHandler.sendMessage(message);
        if (Build.VERSION.SDK_INT >= 19 && n == 2) {
            boolean bl2 = object instanceof Integer;
            boolean bl3 = false;
            if (bl2) {
                Display display = ((WindowManager)this.getSystemService("window")).getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                display.getRealMetrics(displayMetrics);
                boolean bl4 = displayMetrics.widthPixels == mSurface.getWidth() && displayMetrics.heightPixels == mSurface.getHeight();
                bl3 = (Integer)object == 1 ? !bl4 : bl4;
            }
            if (bl3 && SDLActivity.getContext() != null) {
                Context context;
                Context context2 = context = SDLActivity.getContext();
                synchronized (context2) {
                    try {
                        SDLActivity.getContext().wait(500L);
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    return bl;
                }
            }
        }
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setOrientationBis(int n, int n2, boolean bl, String string2) {
        boolean bl2 = true;
        int n3 = -1;
        int n4 = -1;
        if (string2.contains((CharSequence)"LandscapeRight") && string2.contains((CharSequence)"LandscapeLeft")) {
            n3 = 6;
        } else if (string2.contains((CharSequence)"LandscapeRight")) {
            n3 = 0;
        } else if (string2.contains((CharSequence)"LandscapeLeft")) {
            n3 = 8;
        }
        if (string2.contains((CharSequence)"Portrait") && string2.contains((CharSequence)"PortraitUpsideDown")) {
            n4 = 7;
        } else if (string2.contains((CharSequence)"Portrait")) {
            n4 = 1;
        } else if (string2.contains((CharSequence)"PortraitUpsideDown")) {
            n4 = 9;
        }
        boolean bl3 = n3 != -1 ? bl2 : false;
        if (n4 == -1) {
            bl2 = false;
        }
        int n5 = !bl2 && !bl3 ? (bl ? 10 : (n > n2 ? 6 : 7)) : (bl ? (bl2 && bl3 ? 10 : (bl3 ? n3 : n4)) : (bl2 && bl3 ? (n > n2 ? n3 : n4) : (bl3 ? n3 : n4)));
        Log.v((String)"SDL", (String)("setOrientation() requestedOrientation=" + n5 + " width=" + n + " height=" + n2 + " resizable=" + bl + " hint=" + string2));
        mSingleton.setRequestedOrientation(n5);
    }

    public void superOnBackPressed() {
        super.onBackPressed();
    }

    class 1OneShotTask
    implements Runnable {
        int mDuration;
        int mGravity;
        String mMessage;
        int mXOffset;
        int mYOffset;

        1OneShotTask(String string2, int n, int n2, int n3, int n4) {
            this.mMessage = string2;
            this.mDuration = n;
            this.mGravity = n2;
            this.mXOffset = n3;
            this.mYOffset = n4;
        }

        public void run() {
            try {
                Toast toast = Toast.makeText((Context)SDLActivity.mSingleton, (CharSequence)this.mMessage, (int)this.mDuration);
                if (this.mGravity >= 0) {
                    toast.setGravity(this.mGravity, this.mXOffset, this.mYOffset);
                }
                toast.show();
                return;
            }
            catch (Exception exception) {
                Log.e((String)SDLActivity.TAG, (String)exception.getMessage());
                return;
            }
        }
    }

    public static final class NativeState
    extends Enum<NativeState> {
        private static final /* synthetic */ NativeState[] $VALUES;
        public static final /* enum */ NativeState INIT = new NativeState();
        public static final /* enum */ NativeState PAUSED;
        public static final /* enum */ NativeState RESUMED;

        static {
            RESUMED = new NativeState();
            PAUSED = new NativeState();
            NativeState[] arrnativeState = new NativeState[]{INIT, RESUMED, PAUSED};
            $VALUES = arrnativeState;
        }

        public static NativeState valueOf(String string2) {
            return (NativeState)Enum.valueOf(NativeState.class, (String)string2);
        }

        public static NativeState[] values() {
            return (NativeState[])$VALUES.clone();
        }
    }

    protected static class SDLCommandHandler
    extends Handler {
        protected SDLCommandHandler() {
        }

        /*
         * Enabled aggressive block sorting
         */
        public void handleMessage(Message message) {
            Window window;
            Context context = SDL.getContext();
            if (context == null) {
                Log.e((String)SDLActivity.TAG, (String)"error handling message, getContext() returned null");
                return;
            }
            switch (message.arg1) {
                default: {
                    if (!(context instanceof SDLActivity) || ((SDLActivity)context).onUnhandledMessage(message.arg1, message.obj)) return;
                    Log.e((String)SDLActivity.TAG, (String)("error handling message, command is " + message.arg1));
                    return;
                }
                case 1: {
                    if (context instanceof Activity) {
                        ((Activity)context).setTitle((CharSequence)((String)message.obj));
                        return;
                    }
                    Log.e((String)SDLActivity.TAG, (String)"error handling message, getContext() returned no Activity");
                    return;
                }
                case 2: {
                    if (Build.VERSION.SDK_INT < 19) return;
                    if (!(context instanceof Activity)) {
                        Log.e((String)SDLActivity.TAG, (String)"error handling message, getContext() returned no Activity");
                        return;
                    }
                    Window window2 = ((Activity)context).getWindow();
                    if (window2 == null) return;
                    window2.getDecorView().setSystemUiVisibility(5894);
                    window2.addFlags(1024);
                    window2.clearFlags(2048);
                    SDLActivity.mFullscreenModeActive = true;
                    return;
                }
                case 3: {
                    if (SDLActivity.mTextEdit == null) return;
                    SDLActivity.mTextEdit.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(0, 0));
                    ((InputMethodManager)context.getSystemService("input_method")).hideSoftInputFromWindow(SDLActivity.mTextEdit.getWindowToken(), 0);
                    SDLActivity.mScreenKeyboardShown = false;
                    SDLActivity.mSurface.requestFocus();
                    return;
                }
                case 5: {
                    if (!(context instanceof Activity) || (window = ((Activity)context).getWindow()) == null) return;
                    if (message.obj instanceof Integer && (Integer)message.obj != 0) {
                        window.addFlags(128);
                        return;
                    } else {
                        break;
                    }
                }
            }
            window.clearFlags(128);
        }
    }

    static class ShowTextInputTask
    implements Runnable {
        static final int HEIGHT_PADDING = 15;
        public int h;
        public int w;
        public int x;
        public int y;

        public ShowTextInputTask(int n, int n2, int n3, int n4) {
            this.x = n;
            this.y = n2;
            this.w = n3;
            this.h = n4;
            if (this.w <= 0) {
                this.w = 1;
            }
            if (15 + this.h <= 0) {
                this.h = -14;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void run() {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.w, 15 + this.h);
            layoutParams.leftMargin = this.x;
            layoutParams.topMargin = this.y;
            if (SDLActivity.mTextEdit == null) {
                SDLActivity.mTextEdit = new DummyEdit(SDL.getContext());
                SDLActivity.mLayout.addView(SDLActivity.mTextEdit, (ViewGroup.LayoutParams)layoutParams);
            } else {
                SDLActivity.mTextEdit.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            }
            SDLActivity.mTextEdit.setVisibility(0);
            SDLActivity.mTextEdit.requestFocus();
            ((InputMethodManager)SDL.getContext().getSystemService("input_method")).showSoftInput(SDLActivity.mTextEdit, 0);
            SDLActivity.mScreenKeyboardShown = true;
        }
    }

}

