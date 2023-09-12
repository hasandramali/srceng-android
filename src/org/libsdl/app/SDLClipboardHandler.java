/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.ClipData
 *  android.content.ClipData$Item
 *  android.content.ClipboardManager
 *  android.content.ClipboardManager$OnPrimaryClipChangedListener
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package org.libsdl.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import org.libsdl.app.SDL;
import org.libsdl.app.SDLActivity;

class SDLClipboardHandler
implements ClipboardManager.OnPrimaryClipChangedListener {
    protected ClipboardManager mClipMgr = (ClipboardManager)SDL.getContext().getSystemService("clipboard");

    SDLClipboardHandler() {
        this.mClipMgr.addPrimaryClipChangedListener((ClipboardManager.OnPrimaryClipChangedListener)this);
    }

    public String clipboardGetText() {
        ClipData.Item item;
        CharSequence charSequence;
        ClipData clipData = this.mClipMgr.getPrimaryClip();
        if (clipData != null && (item = clipData.getItemAt(0)) != null && (charSequence = item.getText()) != null) {
            return charSequence.toString();
        }
        return null;
    }

    public boolean clipboardHasText() {
        return this.mClipMgr.hasPrimaryClip();
    }

    public void clipboardSetText(String string2) {
        this.mClipMgr.removePrimaryClipChangedListener((ClipboardManager.OnPrimaryClipChangedListener)this);
        ClipData clipData = ClipData.newPlainText(null, (CharSequence)string2);
        this.mClipMgr.setPrimaryClip(clipData);
        this.mClipMgr.addPrimaryClipChangedListener((ClipboardManager.OnPrimaryClipChangedListener)this);
    }

    public void onPrimaryClipChanged() {
        SDLActivity.onNativeClipboardChanged();
    }
}

