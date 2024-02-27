package com.vuforia.ar.pl;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;

public class SystemTools {
    public static final int AR_DEVICE_ORIENTATION_0 = 268455954;
    public static final int AR_DEVICE_ORIENTATION_180 = 268455955;
    public static final int AR_DEVICE_ORIENTATION_270 = 268455957;
    public static final int AR_DEVICE_ORIENTATION_90 = 268455956;
    public static final int AR_DEVICE_ORIENTATION_UNKNOWN = 268455952;
    public static final int AR_ERROR_INVALID_ENUM = 3;
    public static final int AR_ERROR_INVALID_HANDLE = 4;
    public static final int AR_ERROR_INVALID_OPERATION = 5;
    public static final int AR_ERROR_INVALID_VALUE = 2;
    public static final int AR_ERROR_NONE = 0;
    public static final int AR_ERROR_OPERATION_CANCELED = 7;
    public static final int AR_ERROR_OPERATION_FAILED = 6;
    public static final int AR_ERROR_OPERATION_TIMEOUT = 8;
    public static final int AR_ERROR_UNKNOWN = 1;
    public static final int AR_RENDERING_TEXTURE_ROTATION_AUTO = 268455953;
    public static final int AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_LEFT = 268455956;
    public static final int AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_RIGHT = 268455957;
    public static final int AR_RENDERING_TEXTURE_ROTATION_PORTRAIT = 268455954;
    public static final int AR_RENDERING_TEXTURE_ROTATION_PORTRAIT_UPSIDEDOWN = 268455955;
    public static final int AR_VIDEOTEXTURE_ROTATION_UNKNOWN = 268455952;
    private static final String MODULENAME = "SystemTools";

    public static native Activity getActivityFromNative();

    public static native void logSystemError(String str);

    public static native void setSystemErrorCode(int i);

    public static boolean checkMinimumApiLevel(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public static void sendKillSignal(final int i) {
        final Activity activityFromNative = getActivityFromNative();
        if (activityFromNative != null) {
            activityFromNative.runOnUiThread(new Runnable() {
                public void run() {
                    activityFromNative.setResult(i);
                    activityFromNative.finish();
                }
            });
        }
    }

    public static Method retrieveClassMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getDeviceOrientation(Activity activity) {
        int i;
        if (activity == null) {
            return 268455952;
        }
        activity.getResources().getConfiguration();
        Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (checkMinimumApiLevel(8)) {
            i = defaultDisplay.getRotation();
        } else {
            i = defaultDisplay.getOrientation();
        }
        if (i == 0) {
            return 268455954;
        }
        if (i == 1) {
            return 268455956;
        }
        if (i == 2) {
            return 268455955;
        }
        if (i == 3) {
            return 268455957;
        }
        return 268455952;
    }

    public static int getActivityOrientation(Activity activity) {
        int i;
        if (activity == null) {
            return 268455952;
        }
        Configuration configuration = activity.getResources().getConfiguration();
        Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (checkMinimumApiLevel(8)) {
            i = defaultDisplay.getRotation();
        } else {
            i = defaultDisplay.getOrientation();
        }
        int i2 = configuration.orientation;
        if (i2 != 1) {
            if (i2 == 2) {
                return (i == 0 || i == 1) ? 268455956 : 268455957;
            }
            if (i2 != 3) {
                return 268455952;
            }
        }
        return (i == 0 || i == 3) ? 268455954 : 268455955;
    }

    public static String getNativeLibraryPath(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = activity.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            if (checkMinimumApiLevel(9)) {
                String str = applicationInfo.nativeLibraryDir;
                if (str == null || str.length() <= 0 || str.charAt(str.length() - 1) == '/') {
                    return str;
                }
                return str + '/';
            }
            return "/data/data/" + activity.getPackageName() + "/lib/";
        } catch (Exception e) {
            return null;
        }
    }

    public static int[] getActivitySize(Activity activity) {
        if (activity == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        return new int[]{i, i2};
    }

    public static float[] getDisplayDpi(Activity activity) {
        if (activity == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (checkMinimumApiLevel(17)) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        float f = displayMetrics.xdpi;
        float f2 = displayMetrics.ydpi;
        if (f <= 0.0f || f2 <= 0.0f) {
            return null;
        }
        return new float[]{f, f2};
    }

    public static int[] getDisplaySize(Activity activity) {
        if (activity == null) {
            return null;
        }
        Point point = new Point();
        try {
            activity.getWindowManager().getDefaultDisplay().getRealSize(point);
            if (point.x > 0 && point.y > 0) {
                int[] iArr = new int[2];
                if (point.y > point.x) {
                    iArr[0] = point.y;
                    iArr[1] = point.x;
                } else {
                    iArr[0] = point.x;
                    iArr[1] = point.y;
                }
                return iArr;
            }
        } catch (NoSuchMethodError e) {
            DebugLog.LOGE(MODULENAME, "Display.getRealSize is not supported on this platform");
        }
        return null;
    }
}
