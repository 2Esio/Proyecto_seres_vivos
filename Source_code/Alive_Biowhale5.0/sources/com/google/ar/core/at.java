package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;
import android.os.Handler;

/* compiled from: SharedCamera */
final class at extends CameraCaptureSession.StateCallback {
    public static final /* synthetic */ int d = 0;

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Handler f26a;
    final /* synthetic */ CameraCaptureSession.StateCallback b;
    final /* synthetic */ SharedCamera c;

    at(SharedCamera sharedCamera, Handler handler, CameraCaptureSession.StateCallback stateCallback) {
        this.c = sharedCamera;
        this.f26a = handler;
        this.b = stateCallback;
    }

    public final void onActive(CameraCaptureSession cameraCaptureSession) {
        this.f26a.post(new as(this.b, cameraCaptureSession));
        this.c.onCaptureSessionActive(cameraCaptureSession);
    }

    public final void onClosed(CameraCaptureSession cameraCaptureSession) {
        this.f26a.post(new ao(this.b, cameraCaptureSession));
        this.c.onCaptureSessionClosed(cameraCaptureSession);
    }

    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        this.f26a.post(new aq(this.b, cameraCaptureSession));
        this.c.onCaptureSessionConfigureFailed(cameraCaptureSession);
    }

    public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
        au unused = this.c.sharedCameraInfo;
        this.f26a.post(new ap(this.b, cameraCaptureSession));
        this.c.onCaptureSessionConfigured(cameraCaptureSession);
        if (this.c.sharedCameraInfo.a() != null) {
            this.c.setDummyListenerToAvoidImageBufferStarvation();
        }
    }

    public final void onReady(CameraCaptureSession cameraCaptureSession) {
        this.f26a.post(new ar(this.b, cameraCaptureSession));
        this.c.onCaptureSessionReady(cameraCaptureSession);
    }
}
