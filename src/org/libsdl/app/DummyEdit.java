/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnKeyListener
 *  android.view.inputmethod.EditorInfo
 *  android.view.inputmethod.InputConnection
 *  java.lang.CharSequence
 *  java.lang.String
 */
package org.libsdl.app;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import org.libsdl.app.SDLActivity;
import org.libsdl.app.SDLInputConnection;

class DummyEdit
extends View
implements View.OnKeyListener {
    InputConnection ic;

    public DummyEdit(Context context) {
        super(context);
        this.setFocusableInTouchMode(true);
        this.setFocusable(true);
        this.setOnKeyListener((View.OnKeyListener)this);
    }

    public boolean onCheckIsTextEditor() {
        return true;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        this.ic = new SDLInputConnection(this, true);
        editorInfo.inputType = 145;
        editorInfo.imeOptions = 301989888;
        return this.ic;
    }

    public boolean onKey(View view, int n, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (SDLActivity.isTextInputEvent(keyEvent)) {
                this.ic.commitText((CharSequence)String.valueOf((char)((char)keyEvent.getUnicodeChar())), 1);
                return true;
            }
            SDLActivity.onNativeKeyDown(n);
            return true;
        }
        if (keyEvent.getAction() == 1) {
            SDLActivity.onNativeKeyUp(n);
            return true;
        }
        return false;
    }

    public boolean onKeyPreIme(int n, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && n == 4 && SDLActivity.mTextEdit != null && SDLActivity.mTextEdit.getVisibility() == 0) {
            SDLActivity.onNativeKeyboardFocusLost();
        }
        return super.onKeyPreIme(n, keyEvent);
    }
}

