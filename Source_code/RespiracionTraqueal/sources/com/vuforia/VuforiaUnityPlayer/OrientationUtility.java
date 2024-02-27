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
        int displayRotation;
        if (activity == null) {
            return -1;
        }
        Configuration config = activity.getResources().getConfiguration();
        Display display = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 8) {
            displayRotation = display.getRotation();
        } else {
            displayRotation = display.getOrientation();
        }
        int i = config.orientation;
        int activityOrientation = 2;
        int activityOrientation2 = 3;
        if (i != 1) {
            if (i == 2) {
                if (!(displayRotation == 0 || displayRotation == 1)) {
                    activityOrientation2 = 4;
                }
                return activityOrientation2;
            } else if (i != 3) {
                return 0;
            }
        }
        if (displayRotation == 0 || displayRotation == 3) {
            activityOrientation = 1;
        }
        return activityOrientation;
    }
}
