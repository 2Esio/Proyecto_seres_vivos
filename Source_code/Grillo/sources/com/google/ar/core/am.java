package com.google.ar.core;

import android.hardware.camera2.CameraDevice;
import android.os.Handler;

/* compiled from: SharedCamera */
final class am extends CameraDevice.StateCallback {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ Handler f20a;
    private final /* synthetic */ CameraDevice.StateCallback b;
    private final /* synthetic */ SharedCamera c;

    am(SharedCamera sharedCamera, Handler handler, CameraDevice.StateCallback stateCallback) {
        this.c = sharedCamera;
        this.f20a = handler;
        this.b = stateCallback;
    }

    public final void onClosed(CameraDevice cameraDevice) {
        this.f20a.post(new al(this.b, cameraDevice));
        this.c.onDeviceClosed(cameraDevice);
    }

    public final void onOpened(CameraDevice cameraDevice) {
        this.c.sharedCameraInfo.a(cameraDevice);
        this.f20a.post(new ao(this.b, cameraDevice));
        this.c.onDeviceOpened(cameraDevice);
        this.c.sharedCameraInfo.a(this.c.getGpuSurfaceTexture());
        this.c.sharedCameraInfo.a(this.c.getGpuSurface());
    }

    public final void onDisconnected(CameraDevice cameraDevice) {
        this.f20a.post(new an(this.b, cameraDevice));
        this.c.onDeviceDisconnected(cameraDevice);
    }

    public final void onError(CameraDevice cameraDevice, int i) {
        this.f20a.post(new aq(this.b, cameraDevice, i));
        this.c.close();
    }
}
