package com.google.ar.core;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedCamera {
    private static final String TAG = "ArSdk-SharedCamera";
    private boolean cameraSharedWithAr = false;
    private final Session session;
    private Handler sharedCameraHandler;
    /* access modifiers changed from: private */
    public final a sharedCameraInfo = new a((byte) 0);

    SharedCamera(Session session2) {
        HandlerThread handlerThread = new HandlerThread("SharedCameraHandlerThread");
        handlerThread.start();
        this.sharedCameraHandler = new Handler(handlerThread.getLooper());
        this.session = session2;
    }

    private native void nativeSharedCameraCaptureSessionActive(long j, CameraCaptureSession cameraCaptureSession);

    private native void nativeSharedCameraCaptureSessionClosed(long j, CameraCaptureSession cameraCaptureSession);

    private native void nativeSharedCameraCaptureSessionConfigureFailed(long j, CameraCaptureSession cameraCaptureSession);

    private native void nativeSharedCameraCaptureSessionConfigured(long j, CameraCaptureSession cameraCaptureSession);

    private native void nativeSharedCameraCaptureSessionReady(long j, CameraCaptureSession cameraCaptureSession);

    private native ImageReader nativeSharedCameraGetImageReader(long j, CameraDevice cameraDevice);

    private native ImageReader nativeSharedCameraGetImageReaderMotionTracking(long j, CameraDevice cameraDevice);

    private native Surface nativeSharedCameraGetSurface(long j, CameraDevice cameraDevice);

    private native SurfaceTexture nativeSharedCameraGetSurfaceTexture(long j, CameraDevice cameraDevice);

    private native void nativeSharedCameraOnClosed(long j, CameraDevice cameraDevice);

    private native void nativeSharedCameraOnDisconnected(long j, CameraDevice cameraDevice);

    private native void nativeSharedCameraOnOpened(long j, CameraDevice cameraDevice);

    private native void nativeSharedCameraSetAppSurfaces(long j, String str, List<Surface> list);

    private native void nativeSharedCameraSetCaptureCallback(long j, CameraCaptureSession.CaptureCallback captureCallback, Handler handler);

    static class a {

        /* renamed from: a  reason: collision with root package name */
        private CameraDevice f14a;
        private final Map<String, List<Surface>> b;
        private SurfaceTexture c;
        private Surface d;

        private a() {
            this.f14a = null;
            this.b = new HashMap();
            this.c = null;
            this.d = null;
        }

        public final CameraDevice a() {
            return this.f14a;
        }

        public final void a(CameraDevice cameraDevice) {
            this.f14a = cameraDevice;
        }

        public final void a(String str, List<Surface> list) {
            this.b.put(str, list);
        }

        public final SurfaceTexture b() {
            return this.c;
        }

        public final void a(SurfaceTexture surfaceTexture) {
            this.c = surfaceTexture;
        }

        public final Surface c() {
            return this.d;
        }

        public final void a(Surface surface) {
            this.d = surface;
        }

        /* synthetic */ a(byte b2) {
            this();
        }
    }

    /* access modifiers changed from: private */
    public void close() {
        this.sharedCameraHandler.removeCallbacksAndMessages((Object) null);
        this.sharedCameraHandler.getLooper().quit();
        this.sharedCameraHandler = null;
    }

    public void setAppSurfaces(String str, List<Surface> list) {
        this.sharedCameraInfo.a(str, list);
        nativeSharedCameraSetAppSurfaces(this.session.nativeWrapperHandle, str, list);
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.sharedCameraInfo.b();
    }

    public List<Surface> getArCoreSurfaces() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.sharedCameraInfo.c());
        ImageReader cpuImageReaderMotionTracking = getCpuImageReaderMotionTracking();
        if (cpuImageReaderMotionTracking != null) {
            arrayList.add(cpuImageReaderMotionTracking.getSurface());
        }
        arrayList.add(getCpuImageReader().getSurface());
        return arrayList;
    }

    public CameraDevice.StateCallback createARDeviceStateCallback(CameraDevice.StateCallback stateCallback, Handler handler) {
        return new am(this, handler, stateCallback);
    }

    public CameraCaptureSession.StateCallback createARSessionStateCallback(CameraCaptureSession.StateCallback stateCallback, Handler handler) {
        return new ap(this, handler, stateCallback);
    }

    /* access modifiers changed from: package-private */
    public void pause() {
        if (this.sharedCameraInfo.a() != null) {
            setDummyListenerToAvoidImageBufferStarvation();
        }
    }

    private void setDummyOnImageAvailableListener(ImageReader imageReader) {
        if (imageReader != null) {
            imageReader.setOnImageAvailableListener(ak.f18a, this.sharedCameraHandler);
        }
    }

    /* access modifiers changed from: private */
    public void setDummyListenerToAvoidImageBufferStarvation() {
        setDummyOnImageAvailableListener(getCpuImageReader());
        setDummyOnImageAvailableListener(getCpuImageReaderMotionTracking());
    }

    /* access modifiers changed from: private */
    public void onDeviceOpened(CameraDevice cameraDevice) {
        this.sharedCameraInfo.a(cameraDevice);
        this.cameraSharedWithAr = true;
        nativeSharedCameraOnOpened(this.session.nativeWrapperHandle, cameraDevice);
    }

    /* access modifiers changed from: private */
    public void onDeviceClosed(CameraDevice cameraDevice) {
        nativeSharedCameraOnClosed(this.session.nativeWrapperHandle, cameraDevice);
        this.cameraSharedWithAr = false;
    }

    /* access modifiers changed from: private */
    public void onDeviceDisconnected(CameraDevice cameraDevice) {
        nativeSharedCameraOnDisconnected(this.session.nativeWrapperHandle, cameraDevice);
        this.cameraSharedWithAr = false;
        this.sharedCameraInfo.a((CameraDevice) null);
    }

    /* access modifiers changed from: private */
    public void onCaptureSessionActive(CameraCaptureSession cameraCaptureSession) {
        nativeSharedCameraCaptureSessionActive(this.session.nativeWrapperHandle, cameraCaptureSession);
    }

    /* access modifiers changed from: private */
    public void onCaptureSessionClosed(CameraCaptureSession cameraCaptureSession) {
        nativeSharedCameraCaptureSessionClosed(this.session.nativeWrapperHandle, cameraCaptureSession);
    }

    /* access modifiers changed from: private */
    public void onCaptureSessionConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        nativeSharedCameraCaptureSessionConfigureFailed(this.session.nativeWrapperHandle, cameraCaptureSession);
    }

    /* access modifiers changed from: private */
    public void onCaptureSessionConfigured(CameraCaptureSession cameraCaptureSession) {
        nativeSharedCameraCaptureSessionConfigured(this.session.nativeWrapperHandle, cameraCaptureSession);
    }

    /* access modifiers changed from: private */
    public void onCaptureSessionReady(CameraCaptureSession cameraCaptureSession) {
        nativeSharedCameraCaptureSessionReady(this.session.nativeWrapperHandle, cameraCaptureSession);
    }

    private ImageReader getCpuImageReader() {
        return nativeSharedCameraGetImageReader(this.session.nativeWrapperHandle, this.sharedCameraInfo.a());
    }

    private ImageReader getCpuImageReaderMotionTracking() {
        return nativeSharedCameraGetImageReaderMotionTracking(this.session.nativeWrapperHandle, this.sharedCameraInfo.a());
    }

    /* access modifiers changed from: private */
    public SurfaceTexture getGpuSurfaceTexture() {
        return nativeSharedCameraGetSurfaceTexture(this.session.nativeWrapperHandle, this.sharedCameraInfo.a());
    }

    /* access modifiers changed from: private */
    public Surface getGpuSurface() {
        return nativeSharedCameraGetSurface(this.session.nativeWrapperHandle, this.sharedCameraInfo.a());
    }

    public void setCaptureCallback(CameraCaptureSession.CaptureCallback captureCallback, Handler handler) {
        nativeSharedCameraSetCaptureCallback(this.session.nativeWrapperHandle, captureCallback, handler);
    }

    static final /* synthetic */ void lambda$setDummyOnImageAvailableListener$0$SharedCamera(ImageReader imageReader) {
        Image acquireLatestImage = imageReader.acquireLatestImage();
        if (acquireLatestImage != null) {
            acquireLatestImage.close();
        }
    }
}
