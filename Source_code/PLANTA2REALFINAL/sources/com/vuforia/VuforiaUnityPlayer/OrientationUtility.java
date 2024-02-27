package com.vuforia.VuforiaUnityPlayer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

public class OrientationUtility {
    static final int SCREEN_ORIENTATION_LANDSCAPELEFT = 3;
    static final int SCREEN_ORIENTATION_LANDSCAPERIGHT = 4;
    static final int SCREEN_ORIENTATION_PORTRAIT = 1;
    static final int SCREEN_ORIENTATION_PORTRAITUPSIDEDOWN = 2;
    static final int SCREEN_ORIENTATION_UNKNOWN = 0;

    public static int getSurfaceOrientation(Activity activity) {
        int i;
        if (activity == null) {
            return -1;
        }
        Configuration configuration = activity.getResources().getConfiguration();
        Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 8) {
            i = defaultDisplay.getRotation();
        } else {
            i = defaultDisplay.getOrientation();
        }
        int i2 = configuration.orientation;
        if (i2 != 1) {
            if (i2 == 2) {
                return (i == 0 || i == 1) ? 3 : 4;
            }
            if (i2 != 3) {
                return 0;
            }
        }
        return (i == 0 || i == 3) ? 1 : 2;
    }
}
