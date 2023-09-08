/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.media.AudioRecord
 *  android.media.AudioTrack
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Process
 *  android.util.Log
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Thread
 */
package org.libsdl.app;

import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Process;
import android.util.Log;

public class SDLAudioManager {
    protected static final String TAG = "SDLAudio";
    protected static AudioRecord mAudioRecord;
    protected static AudioTrack mAudioTrack;

    public static void audioClose() {
        if (mAudioTrack != null) {
            mAudioTrack.stop();
            mAudioTrack.release();
            mAudioTrack = null;
        }
    }

    public static int[] audioOpen(int n, int n2, int n3, int n4) {
        return SDLAudioManager.open(false, n, n2, n3, n4);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void audioSetThreadPriority(boolean var0, int var1_1) {
        if (!var0) ** GOTO lbl5
        try {
            block2 : {
                Thread.currentThread().setName("SDLAudioC" + var1_1);
                break block2;
lbl5: // 1 sources:
                Thread.currentThread().setName("SDLAudioP" + var1_1);
            }
            Process.setThreadPriority((int)-16);
            return;
        }
        catch (Exception var2_2) {
            Log.v((String)"SDLAudio", (String)("modify thread properties failed " + var2_2.toString()));
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void audioWriteByteBuffer(byte[] arrby) {
        if (mAudioTrack == null) {
            Log.e((String)TAG, (String)"Attempted to make audio call with uninitialized audio!");
            return;
        }
        int n = 0;
        while (n < arrby.length) {
            int n2 = mAudioTrack.write(arrby, n, arrby.length - n);
            if (n2 > 0) {
                n += n2;
                continue;
            }
            if (n2 != 0) {
                Log.w((String)TAG, (String)"SDL audio: error return from write(byte)");
                return;
            }
            try {
                Thread.sleep((long)1L);
            }
            catch (InterruptedException interruptedException) {
                continue;
            }
            break;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void audioWriteFloatBuffer(float[] arrf) {
        if (mAudioTrack == null) {
            Log.e((String)TAG, (String)"Attempted to make audio call with uninitialized audio!");
            return;
        }
        int n = 0;
        while (n < arrf.length) {
            int n2 = mAudioTrack.write(arrf, n, arrf.length - n, 0);
            if (n2 > 0) {
                n += n2;
                continue;
            }
            if (n2 != 0) {
                Log.w((String)TAG, (String)"SDL audio: error return from write(float)");
                return;
            }
            try {
                Thread.sleep((long)1L);
            }
            catch (InterruptedException interruptedException) {
                continue;
            }
            break;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void audioWriteShortBuffer(short[] arrs) {
        if (mAudioTrack == null) {
            Log.e((String)TAG, (String)"Attempted to make audio call with uninitialized audio!");
            return;
        }
        int n = 0;
        while (n < arrs.length) {
            int n2 = mAudioTrack.write(arrs, n, arrs.length - n);
            if (n2 > 0) {
                n += n2;
                continue;
            }
            if (n2 != 0) {
                Log.w((String)TAG, (String)"SDL audio: error return from write(short)");
                return;
            }
            try {
                Thread.sleep((long)1L);
            }
            catch (InterruptedException interruptedException) {
                continue;
            }
            break;
        }
        return;
    }

    public static void captureClose() {
        if (mAudioRecord != null) {
            mAudioRecord.stop();
            mAudioRecord.release();
            mAudioRecord = null;
        }
    }

    public static int[] captureOpen(int n, int n2, int n3, int n4) {
        return SDLAudioManager.open(true, n, n2, n3, n4);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int captureReadByteBuffer(byte[] arrby, boolean bl) {
        int n;
        if (Build.VERSION.SDK_INT < 23) {
            return mAudioRecord.read(arrby, 0, arrby.length);
        }
        AudioRecord audioRecord = mAudioRecord;
        int n2 = arrby.length;
        if (bl) {
            n = 0;
            do {
                return audioRecord.read(arrby, 0, n2, n);
                break;
            } while (true);
        }
        n = 1;
        return audioRecord.read(arrby, 0, n2, n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int captureReadFloatBuffer(float[] arrf, boolean bl) {
        int n;
        AudioRecord audioRecord = mAudioRecord;
        int n2 = arrf.length;
        if (bl) {
            n = 0;
            do {
                return audioRecord.read(arrf, 0, n2, n);
                break;
            } while (true);
        }
        n = 1;
        return audioRecord.read(arrf, 0, n2, n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int captureReadShortBuffer(short[] arrs, boolean bl) {
        int n;
        if (Build.VERSION.SDK_INT < 23) {
            return mAudioRecord.read(arrs, 0, arrs.length);
        }
        AudioRecord audioRecord = mAudioRecord;
        int n2 = arrs.length;
        if (bl) {
            n = 0;
            do {
                return audioRecord.read(arrs, 0, n2, n);
                break;
            } while (true);
        }
        n = 1;
        return audioRecord.read(arrs, 0, n2, n);
    }

    protected static String getAudioFormatString(int n) {
        switch (n) {
            default: {
                return Integer.toString((int)n);
            }
            case 3: {
                return "8-bit";
            }
            case 2: {
                return "16-bit";
            }
            case 4: 
        }
        return "float";
    }

    public static void initialize() {
        mAudioTrack = null;
        mAudioRecord = null;
    }

    public static native int nativeSetupJNI();

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected static int[] open(boolean var0, int var1_1, int var2_2, int var3_3, int var4_4) {
        var5_5 = new StringBuilder().append("Opening ");
        var6_6 = var0 != false ? "capture" : "playback";
        Log.v((String)"SDLAudio", (String)var5_5.append(var6_6).append(", requested ").append(var4_4).append(" frames of ").append(var3_3).append(" channel ").append(SDLAudioManager.getAudioFormatString(var2_2)).append(" audio at ").append(var1_1).append(" Hz").toString());
        if (Build.VERSION.SDK_INT < 21) {
            if (var3_3 > 2) {
                var3_3 = 2;
            }
            if (var1_1 < 8000) {
                var1_1 = 8000;
            } else if (var1_1 > 48000) {
                var1_1 = 48000;
            }
        }
        if (var2_2 == 4 && Build.VERSION.SDK_INT < (var25_7 = var0 != false ? 23 : 21)) {
            var2_2 = 2;
        }
        switch (var2_2) {
            default: {
                Log.v((String)"SDLAudio", (String)("Requested format " + var2_2 + ", getting ENCODING_PCM_16BIT"));
                var2_2 = 2;
                var8_8 = 2;
                break;
            }
            case 3: {
                var8_8 = 1;
                break;
            }
            case 2: {
                var8_8 = 2;
                break;
            }
            case 4: {
                var8_8 = 4;
                break;
            }
        }
        if (var0) {
            switch (var3_3) {
                default: {
                    Log.v((String)"SDLAudio", (String)("Requested " + var3_3 + " channels, getting stereo"));
                    var3_3 = 2;
                    var10_9 = 12;
                    break;
                }
                case 1: {
                    var10_9 = 16;
                    ** break;
                }
                case 2: {
                    var10_9 = 12;
                    ** break;
lbl42: // 2 sources:
                    break;
                }
            }
        } else {
            switch (var3_3) {
                default: {
                    Log.v((String)"SDLAudio", (String)("Requested " + var3_3 + " channels, getting stereo"));
                    var3_3 = 2;
                    var10_9 = 12;
                    break;
                }
                case 1: {
                    var10_9 = 4;
                    break;
                }
                case 2: {
                    var10_9 = 12;
                    break;
                }
                case 3: {
                    var10_9 = 28;
                    break;
                }
                case 4: {
                    var10_9 = 204;
                    break;
                }
                case 5: {
                    var10_9 = 220;
                    break;
                }
                case 6: {
                    var10_9 = 252;
                    break;
                }
                case 7: {
                    var10_9 = 1276;
                    break;
                }
                case 8: {
                    if (Build.VERSION.SDK_INT >= 23) {
                        var10_9 = 6396;
                        break;
                    }
                    Log.v((String)"SDLAudio", (String)("Requested " + var3_3 + " channels, getting 5.1 surround"));
                    var3_3 = 6;
                    var10_9 = 252;
                }
            }
        }
        var11_10 = var8_8 * var3_3;
        var12_11 = var0 != false ? AudioRecord.getMinBufferSize((int)var1_1, (int)var10_9, (int)var2_2) : AudioTrack.getMinBufferSize((int)var1_1, (int)var10_9, (int)var2_2);
        var13_12 = Math.max((int)var4_4, (int)((-1 + (var12_11 + var11_10)) / var11_10));
        var14_13 = new int[4];
        if (var0) {
            if (SDLAudioManager.mAudioRecord == null) {
                var20_14 = var13_12 * var11_10;
                SDLAudioManager.mAudioRecord = new AudioRecord(0, var1_1, var10_9, var2_2, var20_14);
                if (SDLAudioManager.mAudioRecord.getState() != 1) {
                    Log.e((String)"SDLAudio", (String)"Failed during initialization of AudioRecord");
                    SDLAudioManager.mAudioRecord.release();
                    SDLAudioManager.mAudioRecord = null;
                    return null;
                }
                SDLAudioManager.mAudioRecord.startRecording();
            }
            var14_13[0] = SDLAudioManager.mAudioRecord.getSampleRate();
            var14_13[1] = SDLAudioManager.mAudioRecord.getAudioFormat();
            var14_13[2] = SDLAudioManager.mAudioRecord.getChannelCount();
        } else {
            if (SDLAudioManager.mAudioTrack == null) {
                var18_17 = var13_12 * var11_10;
                SDLAudioManager.mAudioTrack = new AudioTrack(3, var1_1, var10_9, var2_2, var18_17, 1);
                if (SDLAudioManager.mAudioTrack.getState() != 1) {
                    Log.e((String)"SDLAudio", (String)"Failed during initialization of Audio Track");
                    SDLAudioManager.mAudioTrack.release();
                    SDLAudioManager.mAudioTrack = null;
                    return null;
                }
                SDLAudioManager.mAudioTrack.play();
            }
            var14_13[0] = SDLAudioManager.mAudioTrack.getSampleRate();
            var14_13[1] = SDLAudioManager.mAudioTrack.getAudioFormat();
            var14_13[2] = SDLAudioManager.mAudioTrack.getChannelCount();
        }
        var14_13[3] = var13_12;
        var15_15 = new StringBuilder().append("Opening ");
        var16_16 = var0 != false ? "capture" : "playback";
        Log.v((String)"SDLAudio", (String)var15_15.append(var16_16).append(", got ").append(var14_13[3]).append(" frames of ").append(var14_13[2]).append(" channel ").append(SDLAudioManager.getAudioFormatString(var14_13[1])).append(" audio at ").append(var14_13[0]).append(" Hz").toString());
        return var14_13;
    }
}

