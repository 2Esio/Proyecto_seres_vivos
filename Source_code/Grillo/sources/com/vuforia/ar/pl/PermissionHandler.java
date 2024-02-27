package com.vuforia.ar.pl;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.ArrayList;

public class PermissionHandler {
    private static final int AR_PERMISSIONS_STATUS_DENIED = 2;
    private static final int AR_PERMISSIONS_STATUS_FAILED = 0;
    private static final int AR_PERMISSIONS_STATUS_GRANTED = 3;
    private static final int AR_PERMISSIONS_STATUS_REQUESTED = 1;
    private static final String MODULENAME = "PermissionHandler";
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    /* access modifiers changed from: private */
    public static boolean mIsPermissionsRequested = false;
    /* access modifiers changed from: private */
    public static String[] mPermissionsArrayToRequest;
    /* access modifiers changed from: private */
    public static int mPermissionsStatus = 0;

    public static class PermissionsRequestFragment extends Fragment {
        public void onStart() {
            super.onStart();
            if (!PermissionHandler.mIsPermissionsRequested) {
                requestPermissions(PermissionHandler.mPermissionsArrayToRequest, PermissionHandler.PERMISSIONS_REQUEST_CODE);
                boolean unused = PermissionHandler.mIsPermissionsRequested = true;
            }
        }

        private void removeSelf() {
            FragmentTransaction beginTransaction = getActivity().getFragmentManager().beginTransaction();
            beginTransaction.remove(this);
            beginTransaction.commit();
        }

        public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
            if (i == PermissionHandler.PERMISSIONS_REQUEST_CODE) {
                int i2 = 0;
                while (true) {
                    if (i2 >= iArr.length) {
                        break;
                    } else if (iArr[i2] == -1) {
                        DebugLog.LOGE(PermissionHandler.MODULENAME, "Permission denied for " + strArr[i2]);
                        int unused = PermissionHandler.mPermissionsStatus = 2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (PermissionHandler.mPermissionsStatus != 2) {
                    int unused2 = PermissionHandler.mPermissionsStatus = 3;
                }
                removeSelf();
            }
        }
    }

    public int requestPermissions(Activity activity, String[] strArr) {
        int i = mPermissionsStatus;
        if (i == 3 || i == 1 || i == 2) {
            return mPermissionsStatus;
        }
        if (activity == null) {
            return 0;
        }
        PackageManager packageManager = activity.getPackageManager();
        ArrayList arrayList = new ArrayList(strArr.length);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (packageManager.checkPermission(strArr[i2], activity.getPackageName()) != 0) {
                arrayList.add(strArr[i2]);
            }
        }
        if (arrayList.isEmpty()) {
            mPermissionsStatus = 3;
        }
        if (Build.VERSION.SDK_INT >= 23 && !mIsPermissionsRequested && !arrayList.isEmpty()) {
            try {
                FragmentManager fragmentManager = activity.getFragmentManager();
                mPermissionsArrayToRequest = (String[]) arrayList.toArray(new String[arrayList.size()]);
                PermissionsRequestFragment permissionsRequestFragment = new PermissionsRequestFragment();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(0, permissionsRequestFragment);
                beginTransaction.commit();
                mPermissionsStatus = 1;
            } catch (Exception e) {
                DebugLog.LOGE(MODULENAME, "Failed to request permissions. Exception: " + e.getMessage());
                return 0;
            }
        }
        return mPermissionsStatus;
    }
}
