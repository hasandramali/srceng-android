/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.inputmethod.BaseInputConnection
 *  java.lang.CharSequence
 *  java.lang.String
 */
package org.libsdl.app;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import org.libsdl.app.SDLActivity;

class SDLInputConnection
extends BaseInputConnection {
    public SDLInputConnection(View view, boolean bl) {
        super(view, bl);
    }

    public static native void nativeCommitText(String var0, int var1);

    public boolean commitText(CharSequence charSequence, int n) {
        for (int i = 0; i < charSequence.length(); ++i) {
            char c = charSequence.charAt(i);
            if (c == '\n' && SDLActivity.onNativeSoftReturnKey()) {
                return true;
            }
            this.nativeGenerateScancodeForUnichar(c);
        }
        SDLInputConnection.nativeCommitText(charSequence.toString(), n);
        return super.commitText(charSequence, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean deleteSurroundingText(int n, int n2) {
        if (n <= 0) return super.deleteSurroundingText(n, n2);
        if (n2 != 0) return super.deleteSurroundingText(n, n2);
        boolean bl = true;
        int n3 = n;
        do {
            int n4 = n3 - 1;
            if (n3 <= 0) return bl;
            boolean bl2 = this.sendKeyEvent(new KeyEvent(0, 67)) && this.sendKeyEvent(new KeyEvent(1, 67));
            bl = bl && bl2;
            n3 = n4;
        } while (true);
    }

    public native void nativeGenerateScancodeForUnichar(char var1);

    public native void nativeSetComposingText(String var1, int var2);

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 66 && SDLActivity.onNativeSoftReturnKey()) {
            return true;
        }
        return super.sendKeyEvent(keyEvent);
    }

    public boolean setComposingText(CharSequence charSequence, int n) {
        this.nativeSetComposingText(charSequence.toString(), n);
        return super.setComposingText(charSequence, n);
    }
}

