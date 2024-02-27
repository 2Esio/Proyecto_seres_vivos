package com.google.ar.core;

import com.google.ar.core.CameraConfig;
import java.util.EnumSet;
import java.util.Iterator;

public class CameraConfigFilter {
    private static final String TAG = "CameraConfigFilter";
    long nativeHandle;
    private final long nativeSymbolTableHandle;
    private final Session session;

    protected CameraConfigFilter() {
        this.session = null;
        this.nativeHandle = 0;
        this.nativeSymbolTableHandle = 0;
    }

    private static native long nativeCreateCameraConfigFilter(long j);

    private static native void nativeDestroyCameraConfigFilter(long j, long j2);

    private native int nativeGetDepthSensorUsage(long j, long j2);

    private native int nativeGetTargetFps(long j, long j2);

    private native void nativeSetDepthSensorUsage(long j, long j2, int i);

    private native void nativeSetTargetFps(long j, long j2, int i);

    public CameraConfigFilter(Session session2) {
        this.session = session2;
        this.nativeHandle = nativeCreateCameraConfigFilter(session2.nativeWrapperHandle);
        this.nativeSymbolTableHandle = session2.nativeSymbolTableHandle;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        long j = this.nativeHandle;
        if (j != 0) {
            nativeDestroyCameraConfigFilter(this.nativeSymbolTableHandle, j);
            this.nativeHandle = 0;
        }
        super.finalize();
    }

    public EnumSet<CameraConfig.DepthSensorUsage> getDepthSensorUsage() {
        return CameraConfig.DepthSensorUsage.forBitFlags(nativeGetDepthSensorUsage(this.session.nativeWrapperHandle, this.nativeHandle));
    }

    public EnumSet<CameraConfig.TargetFps> getTargetFps() {
        return CameraConfig.TargetFps.forBitFlags(nativeGetTargetFps(this.session.nativeWrapperHandle, this.nativeHandle));
    }

    public CameraConfigFilter setDepthSensorUsage(EnumSet<CameraConfig.DepthSensorUsage> enumSet) {
        Iterator it = enumSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            i |= ((CameraConfig.DepthSensorUsage) it.next()).nativeCode;
        }
        nativeSetDepthSensorUsage(this.session.nativeWrapperHandle, this.nativeHandle, i);
        return this;
    }

    public CameraConfigFilter setTargetFps(EnumSet<CameraConfig.TargetFps> enumSet) {
        Iterator it = enumSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            i |= ((CameraConfig.TargetFps) it.next()).nativeCode;
        }
        nativeSetTargetFps(this.session.nativeWrapperHandle, this.nativeHandle, i);
        return this;
    }
}
