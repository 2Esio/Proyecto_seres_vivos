package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;

public class Camera2Wrapper implements d {

    /* renamed from: a  reason: collision with root package name */
    private Context f53a;
    private b b = null;
    private final int c = 100;

    public Camera2Wrapper(Context context) {
        this.f53a = context;
        initCamera2Jni();
    }

    private static int a(float f) {
        return (int) Math.min(Math.max((f * 2000.0f) - 0.0040893555f, -900.0f), 900.0f);
    }

    private final native void deinitCamera2Jni();

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    private final native void nativeSurfaceTextureReady(Object obj);

    public final void a() {
        deinitCamera2Jni();
        closeCamera2();
    }

    public final void a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    public final void a(Object obj, Object obj2, Object obj3, int i, int i2, int i3) {
        nativeFrameReady(obj, obj2, obj3, i, i2, i3);
    }

    /* access modifiers changed from: protected */
    public void closeCamera2() {
        b bVar = this.b;
        if (bVar != null) {
            bVar.b();
        }
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public int getCamera2Count() {
        if (j.f113a) {
            return b.a(this.f53a);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int[] getCamera2Resolutions(int i) {
        if (j.f113a) {
            return b.d(this.f53a, i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getCamera2SensorOrientation(int i) {
        if (j.f113a) {
            return b.a(this.f53a, i);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public Object getCameraFocusArea(float f, float f2) {
        int a2 = a(f);
        int a3 = a(1.0f - f2);
        return new Camera.Area(new Rect(a2 - 100, a3 - 100, a2 + 100, a3 + 100), 1000);
    }

    /* access modifiers changed from: protected */
    public Rect getFrameSizeCamera2() {
        b bVar = this.b;
        return bVar != null ? bVar.a() : new Rect();
    }

    /* access modifiers changed from: protected */
    public boolean initializeCamera2(int i, int i2, int i3, int i4, int i5) {
        if (!j.f113a || this.b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        this.b = new b(this);
        return this.b.a(this.f53a, i, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public boolean isCamera2AutoFocusPointSupported(int i) {
        if (j.f113a) {
            return b.c(this.f53a, i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isCamera2FrontFacing(int i) {
        if (j.f113a) {
            return b.b(this.f53a, i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void pauseCamera2() {
        b bVar = this.b;
        if (bVar != null) {
            bVar.d();
        }
    }

    /* access modifiers changed from: protected */
    public boolean setAutoFocusPoint(float f, float f2) {
        b bVar;
        if (!j.f113a || (bVar = this.b) == null) {
            return false;
        }
        return bVar.a(f, f2);
    }

    /* access modifiers changed from: protected */
    public void startCamera2() {
        b bVar = this.b;
        if (bVar != null) {
            bVar.c();
        }
    }

    /* access modifiers changed from: protected */
    public void stopCamera2() {
        b bVar = this.b;
        if (bVar != null) {
            bVar.e();
        }
    }
}
