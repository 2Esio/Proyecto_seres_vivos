package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;
import android.os.Handler;
import com.google.ar.core.SharedCamera;

/* compiled from: SharedCamera */
final class ap extends CameraCaptureSession.StateCallback {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ Handler f23a;
    private final /* synthetic */ CameraCaptureSession.StateCallback b;
    private final /* synthetic */ SharedCamera c;

    ap(SharedCamera sharedCamera, Handler handler, CameraCaptureSession.StateCallback stateCallback) {
        this.c = sharedCamera;
        this.f23a = handler;
        this.b = stateCallback;
    }

    public final void onClosed(CameraCaptureSession cameraCaptureSession) {
        this.f23a.post(new as(this.b, cameraCaptureSession));
        this.c.onCaptureSessionClosed(cameraCaptureSession);
    }

    public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
        SharedCamera.a unused = this.c.sharedCameraInfo;
        this.f23a.post(new ar(this.b, cameraCaptureSession));
        this.c.onCaptureSessionConfigured(cameraCaptureSession);
        if (this.c.sharedCameraInfo.a() != null) {
            this.c.setDummyListenerToAvoidImageBufferStarvation();
        }
    }

    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        this.f23a.post(new au(this.b, cameraCaptureSession));
        this.c.onCaptureSessionConfigureFailed(cameraCaptureSession);
    }

    public final void onReady(CameraCaptureSession cameraCaptureSession) {
        this.f23a.post(new at(this.b, cameraCaptureSession));
        this.c.onCaptureSessionReady(cameraCaptureSession);
    }

    public final void onActive(CameraCaptureSession cameraCaptureSession) {
        this.f23a.post(new av(this.b, cameraCaptureSession));
        this.c.onCaptureSessionActive(cameraCaptureSession);
    }
}
