package com.google.ar.core;

import android.util.Size;
import com.google.ar.core.exceptions.FatalException;

public class CameraConfig {
    long nativeHandle;
    final Session session;

    CameraConfig(Session session2, long j) {
        this.session = session2;
        this.nativeHandle = j;
    }

    private static native void nativeDestroyCameraConfig(long j);

    private native String nativeGetCameraId(long j, long j2);

    private native int nativeGetFacingDirection(long j, long j2);

    private native void nativeGetImageDimensions(long j, long j2, int[] iArr);

    private native void nativeGetTextureDimensions(long j, long j2, int[] iArr);

    public enum FacingDirection {
        BACK(0),
        FRONT(1);
        
        final int nativeCode;

        private FacingDirection(int i) {
            this.nativeCode = i;
        }

        static FacingDirection forNumber(int i) {
            for (FacingDirection facingDirection : values()) {
                if (facingDirection.nativeCode == i) {
                    return facingDirection;
                }
            }
            StringBuilder sb = new StringBuilder(62);
            sb.append("Unexpected value for native FacingDirection, value=");
            sb.append(i);
            throw new FatalException(sb.toString());
        }
    }

    protected CameraConfig() {
        this.session = null;
        this.nativeHandle = 0;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        long j = this.nativeHandle;
        if (j != 0) {
            nativeDestroyCameraConfig(j);
            this.nativeHandle = 0;
        }
        super.finalize();
    }

    public Size getImageSize() {
        int[] iArr = new int[2];
        nativeGetImageDimensions(this.session.nativeWrapperHandle, this.nativeHandle, iArr);
        return new Size(iArr[0], iArr[1]);
    }

    public Size getTextureSize() {
        int[] iArr = new int[2];
        nativeGetTextureDimensions(this.session.nativeWrapperHandle, this.nativeHandle, iArr);
        return new Size(iArr[0], iArr[1]);
    }

    public String getCameraId() {
        return nativeGetCameraId(this.session.nativeWrapperHandle, this.nativeHandle);
    }

    public FacingDirection getFacingDirection() {
        return FacingDirection.forNumber(nativeGetFacingDirection(this.session.nativeWrapperHandle, this.nativeHandle));
    }
}
