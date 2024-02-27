package com.google.ar.core;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraDevice;
import android.view.Surface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: SharedCamera */
final class au {

    /* renamed from: a  reason: collision with root package name */
    private CameraDevice f27a = null;
    private final Map<String, List<Surface>> b = new HashMap();
    private SurfaceTexture c = null;
    private Surface d = null;

    private au() {
    }

    public final CameraDevice a() {
        return this.f27a;
    }

    public final void a(SurfaceTexture surfaceTexture) {
        this.c = surfaceTexture;
    }

    public final void a(CameraDevice cameraDevice) {
        this.f27a = cameraDevice;
    }

    public final void a(Surface surface) {
        this.d = surface;
    }

    public final SurfaceTexture b() {
        return this.c;
    }

    public final Surface c() {
        return this.d;
    }

    /* synthetic */ au(byte[] bArr) {
    }

    public final void a(String str, List<Surface> list) {
        this.b.put(str, list);
    }
}
