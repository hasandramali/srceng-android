/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Environment
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  java.io.File
 *  java.io.FileFilter
 *  java.io.IOException
 *  java.lang.CharSequence
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.Comparator
 *  java.util.List
 */
package me.nillerusr;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import me.nillerusr.LauncherActivity;

public class DirchActivity
extends Activity
implements View.OnTouchListener {
    static LinearLayout body;
    public static String cur_dir;
    public static final int sdk;
    public SharedPreferences mPref;

    static {
        sdk = Integer.valueOf((String)Build.VERSION.SDK);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void ListDirectory(String string2) {
        TextView textView = (TextView)this.findViewById(2131099649);
        File file = new File(string2);
        Object[] arrobject = file.listFiles(new FileFilter(){

            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        if (arrobject != null && arrobject.length > 1) {
            Arrays.sort((Object[])arrobject, (Comparator)new Comparator<File>(){

                public int compare(File file, File file2) {
                    return file.getName().toUpperCase().compareTo(file2.getName().toUpperCase());
                }
            });
        }
        LayoutInflater layoutInflater = this.getLayoutInflater();
        if (arrobject != null) {
            try {
                cur_dir = file.getCanonicalPath();
                textView.setText((CharSequence)cur_dir);
            }
            catch (IOException iOException) {}
            body.removeAllViews();
            View view = layoutInflater.inflate(2130903042, (ViewGroup)body, false);
            ((TextView)view.findViewById(2131099664)).setText((CharSequence)"..");
            body.addView(view);
            view.setOnTouchListener((View.OnTouchListener)this);
            for (Object object : arrobject) {
                View view2 = layoutInflater.inflate(2130903042, (ViewGroup)body, false);
                ((TextView)view2.findViewById(2131099664)).setText((CharSequence)object.getName());
                body.addView(view2);
                view2.setOnTouchListener((View.OnTouchListener)this);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public List<String> getExtStoragePaths() {
        ArrayList arrayList = new ArrayList();
        File[] arrfile = new File("/storage/").listFiles();
        if (arrfile != null) {
            for (File file : arrfile) {
                if (file.getAbsolutePath().equalsIgnoreCase(Environment.getExternalStorageDirectory().getAbsolutePath()) || !file.isDirectory() || !file.canRead()) continue;
                arrayList.add((Object)file.getAbsolutePath());
            }
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPref = this.getSharedPreferences("mod", 0);
        this.requestWindowFeature(1);
        if (sdk >= 21) {
            super.setTheme(16974372);
        } else {
            super.setTheme(16973829);
        }
        this.setContentView(2130903040);
        cur_dir = null;
        body = (LinearLayout)this.findViewById(2131099652);
        ((TextView)this.findViewById(2131099649)).setText((CharSequence)"");
        ((Button)this.findViewById(2131099651)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (DirchActivity.cur_dir != null) {
                    if (LauncherActivity.GamePath != null) {
                        LauncherActivity.GamePath.setText((CharSequence)(DirchActivity.cur_dir + "/"));
                    }
                    SharedPreferences.Editor editor = DirchActivity.this.mPref.edit();
                    editor.putString("gamepath", DirchActivity.cur_dir + "/");
                    editor.commit();
                    DirchActivity.this.finish();
                }
            }
        });
        LauncherActivity.changeButtonsStyle((ViewGroup)this.getWindow().getDecorView());
        List<String> list = this.getExtStoragePaths();
        if (list == null || list.isEmpty()) {
            this.ListDirectory(LauncherActivity.getDefaultDir());
            return;
        } else {
            LayoutInflater layoutInflater = this.getLayoutInflater();
            View view = layoutInflater.inflate(2130903042, (ViewGroup)body, false);
            ((TextView)view.findViewById(2131099664)).setText((CharSequence)LauncherActivity.getDefaultDir());
            body.addView(view);
            view.setOnTouchListener((View.OnTouchListener)this);
            for (String string2 : list) {
                View view2 = layoutInflater.inflate(2130903042, (ViewGroup)body, false);
                ((TextView)view2.findViewById(2131099664)).setText((CharSequence)string2);
                body.addView(view2);
                view2.setOnTouchListener((View.OnTouchListener)this);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView textView;
        block4 : {
            block3 : {
                if (motionEvent.getAction() != 1) break block3;
                textView = (TextView)view.findViewById(2131099664);
                if (cur_dir != null) break block4;
                this.ListDirectory("" + (Object)textView.getText());
            }
            do {
                return false;
                break;
            } while (true);
        }
        this.ListDirectory(cur_dir + "/" + (Object)textView.getText());
        return false;
    }

}

