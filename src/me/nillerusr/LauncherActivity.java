/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Environment
 *  android.text.Editable
 *  android.text.util.Linkify
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.Window
 *  android.widget.Button
 *  android.widget.CheckBox
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ScrollView
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.io.File
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package me.nillerusr;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import me.nillerusr.DirchActivity;
import org.libsdl.app.SDLActivity;

public class LauncherActivity
extends Activity {
    static EditText EnvEdit;
    static EditText GamePath;
    public static String PKG_NAME;
    static final int REQUEST_PERMISSIONS = 42;
    public static boolean can_write;
    static CheckBox check_updates;
    static EditText cmdArgs;
    static EditText res_height;
    static EditText res_width;
    public static final int sdk;
    static CheckBox useVolumeButtons;
    public SharedPreferences mPref;

    static {
        can_write = true;
        GamePath = null;
        sdk = Integer.valueOf((String)Build.VERSION.SDK);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void changeButtonsStyle(ViewGroup viewGroup) {
        if (sdk < 21) {
            for (int i = -1 + viewGroup.getChildCount(); i >= 0; --i) {
                try {
                    View view = viewGroup.getChildAt(i);
                    if (view == null) continue;
                    if (view instanceof ViewGroup) {
                        LauncherActivity.changeButtonsStyle((ViewGroup)view);
                        continue;
                    }
                    if (view instanceof Button) {
                        Button button = (Button)view;
                        Drawable drawable2 = button.getBackground();
                        if (drawable2 != null) {
                            drawable2.setAlpha(96);
                        }
                        button.setTextColor(-1);
                        button.setTextSize(15.0f);
                        button.setTypeface(button.getTypeface(), 1);
                        continue;
                    }
                    if (!(view instanceof EditText)) continue;
                    EditText editText = (EditText)view;
                    editText.setBackgroundColor(-14211289);
                    editText.setTextColor(-1);
                    editText.setTextSize(15.0f);
                    continue;
                }
                catch (Exception exception) {}
            }
        }
    }

    public static String getAndroidDataDir() {
        String string2 = LauncherActivity.getDefaultDir() + "/Android/data/" + PKG_NAME + "/files";
        File file = new File(string2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return string2;
    }

    public static String getDefaultDir() {
        File file = Environment.getExternalStorageDirectory();
        if (file == null || !file.exists()) {
            return "/sdcard/";
        }
        return file.getPath();
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
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PKG_NAME = this.getApplication().getPackageName();
        this.requestWindowFeature(1);
        if (sdk >= 21) {
            super.setTheme(16974372);
        } else {
            super.setTheme(16973829);
        }
        this.mPref = this.getSharedPreferences("mod", 0);
        this.setContentView(2130903041);
        (LinearLayout)this.findViewById(2131099659);
        cmdArgs = (EditText)this.findViewById(2131099660);
        EnvEdit = (EditText)this.findViewById(2131099661);
        GamePath = (EditText)this.findViewById(2131099662);
        ((Button)this.findViewById(2131099657)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                LauncherActivity.this.startSource(view);
            }
        });
        ((Button)this.findViewById(2131099656)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Dialog dialog = new Dialog((Context)LauncherActivity.this);
                dialog.setTitle(2130968578);
                ScrollView scrollView = new ScrollView((Context)LauncherActivity.this);
                scrollView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
                scrollView.setPadding(5, 5, 5, 5);
                TextView textView = new TextView((Context)LauncherActivity.this);
                textView.setText(2130968579);
                textView.setLinksClickable(true);
                textView.setTextIsSelectable(true);
                Linkify.addLinks((TextView)textView, (int)3);
                scrollView.addView((View)textView);
                dialog.setContentView((View)scrollView);
                dialog.show();
            }
        });
        ((Button)this.findViewById(2131099663)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent((Context)LauncherActivity.this, DirchActivity.class);
                intent.addFlags(268435456);
                LauncherActivity.this.startActivity(intent);
            }
        });
        this.getResources().getString(2130968576);
        cmdArgs.setText((CharSequence)this.mPref.getString("argv", "-console"));
        GamePath.setText((CharSequence)this.mPref.getString("gamepath", LauncherActivity.getDefaultDir() + "/srceng"));
        EnvEdit.setText((CharSequence)this.mPref.getString("env", "LIBGL_USEVBO=0"));
        LauncherActivity.changeButtonsStyle((ViewGroup)this.getWindow().getDecorView());
        if (sdk >= 23) {
            this.applyPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, 42);
        }
    }

    public void onPause() {
        Log.v((String)"SRCAPK", (String)"onPause");
        this.saveSettings(this.mPref.edit());
        super.onPause();
    }

    public void onRequestPermissionsResult(int n, String[] arrstring, int[] arrn) {
        if (n == 42 && arrn[0] == -1) {
            Toast.makeText((Context)this, (int)2130968587, (int)1).show();
            this.finish();
        }
    }

    public void saveSettings(SharedPreferences.Editor editor) {
        String string2 = cmdArgs.getText().toString();
        String string3 = GamePath.getText().toString();
        String string4 = EnvEdit.getText().toString();
        editor.putString("argv", string2);
        editor.putString("gamepath", string3);
        editor.putString("env", string4);
        editor.commit();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void startSource(View view) {
        GamePath.getText().toString();
        SharedPreferences.Editor editor = this.mPref.edit();
        this.saveSettings(editor);
        if (sdk >= 19) {
            editor.putBoolean("immersive_mode", true);
        } else {
            editor.putBoolean("immersive_mode", false);
        }
        editor.commit();
        Intent intent = new Intent((Context)this, SDLActivity.class);
        intent.addFlags(268435456);
        this.startActivity(intent);
    }

}

