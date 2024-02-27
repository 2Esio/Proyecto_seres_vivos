package com.google.ar.core;

public class CameraIntrinsics {
    long nativeHandle;
    final Session session;

    CameraIntrinsics(long j, Session session2) {
        this.nativeHandle = 0;
        this.nativeHandle = j;
        this.session = session2;
    }

    private native long nativeCreateIntrinsics(long j, float f, float f2, float f3, float f4, int i, int i2);

    private native void nativeDestroyCameraIntrinsics(long j);

    private native void nativeGetFocalLength(long j, long j2, float[] fArr, int i);

    private native void nativeGetImageDimensions(long j, long j2, int[] iArr, int i);

    private native void nativeGetPrincipalPoint(long j, long j2, float[] fArr, int i);

    protected CameraIntrinsics() {
        this.nativeHandle = 0;
        this.session = null;
        this.nativeHandle = 0;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        long j = this.nativeHandle;
        if (j != 0) {
            nativeDestroyCameraIntrinsics(j);
        }
        super.finalize();
    }

    public void getFocalLength(float[] fArr, int i) {
        nativeGetFocalLength(this.session.nativeWrapperHandle, this.nativeHandle, fArr, i);
    }

    public float[] getFocalLength() {
        float[] fArr = new float[2];
        getFocalLength(fArr, 0);
        return fArr;
    }

    public void getPrincipalPoint(float[] fArr, int i) {
        nativeGetPrincipalPoint(this.session.nativeWrapperHandle, this.nativeHandle, fArr, i);
    }

    public float[] getPrincipalPoint() {
        float[] fArr = new float[2];
        getPrincipalPoint(fArr, 0);
        return fArr;
    }

    public void getImageDimensions(int[] iArr, int i) {
        nativeGetImageDimensions(this.session.nativeWrapperHandle, this.nativeHandle, iArr, i);
    }

    public int[] getImageDimensions() {
        int[] iArr = new int[2];
        getImageDimensions(iArr, 0);
        return iArr;
    }
}
