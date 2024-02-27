package com.vuforia.ar.pl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.vuforia.ar.pl.Camera1_Preview;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SurfaceManager {
    private static final String MODULENAME = "SurfaceManager";
    Lock addSurfaceLock = new ReentrantLock();
    View cameraSurfaceParentView = null;
    Camera1_Preview.CameraCacheInfo cciForSurface;
    GLSurfaceView glSurfaceView = null;
    int glSurfaceViewChildPosition = 0;
    boolean renderWhenDirtyEnabled = false;
    Lock viewLock = new ReentrantLock();

    private GLSurfaceView searchForGLSurfaceView(View view) {
        int i = 0;
        this.glSurfaceViewChildPosition = 0;
        try {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            GLSurfaceView gLSurfaceView = null;
            while (i < childCount) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof GLSurfaceView) {
                    GLSurfaceView gLSurfaceView2 = (GLSurfaceView) childAt;
                    this.glSurfaceViewChildPosition = i;
                    return gLSurfaceView2;
                } else if ((childAt instanceof ViewGroup) && (gLSurfaceView = searchForGLSurfaceView(childAt)) != null) {
                    return gLSurfaceView;
                } else {
                    i++;
                }
            }
            return gLSurfaceView;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean applyRenderWhenDirty() {
        GLSurfaceView gLSurfaceView = this.glSurfaceView;
        if (gLSurfaceView == null) {
            return false;
        }
        gLSurfaceView.setRenderMode(this.renderWhenDirtyEnabled ^ true ? 1 : 0);
        return true;
    }

    /* access modifiers changed from: private */
    public void setupCameraSurface(Camera1_Preview.CameraCacheInfo cameraCacheInfo) {
        if (cameraCacheInfo.surface == null) {
            Activity activityFromNative = SystemTools.getActivityFromNative();
            if (activityFromNative != null) {
                cameraCacheInfo.surface = new CameraSurface(activityFromNative);
            } else {
                return;
            }
        } else if (cameraCacheInfo.surface.getParent() != null && ViewGroup.class.isInstance(cameraCacheInfo.surface.getParent())) {
            ((ViewGroup) cameraCacheInfo.surface.getParent()).removeView(cameraCacheInfo.surface);
        }
        cameraCacheInfo.surface.setCamera(cameraCacheInfo.camera);
    }

    public boolean retrieveGLSurfaceView() {
        try {
            Activity activityFromNative = SystemTools.getActivityFromNative();
            if (activityFromNative == null) {
                return false;
            }
            View decorView = activityFromNative.getWindow().getDecorView();
            this.glSurfaceView = searchForGLSurfaceView(decorView);
            if (this.glSurfaceView == null) {
                this.cameraSurfaceParentView = decorView;
            } else {
                this.cameraSurfaceParentView = (View) this.glSurfaceView.getParent();
            }
            if (this.glSurfaceView != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setEnableRenderWhenDirty(boolean z) {
        this.renderWhenDirtyEnabled = z;
        return applyRenderWhenDirty();
    }

    public void requestRender() {
        GLSurfaceView gLSurfaceView = this.glSurfaceView;
        if (gLSurfaceView != null) {
            gLSurfaceView.requestRender();
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean addCameraSurface(Camera1_Preview.CameraCacheInfo cameraCacheInfo) {
        Activity activityFromNative = SystemTools.getActivityFromNative();
        boolean z = false;
        if (activityFromNative == null) {
            return false;
        }
        this.cciForSurface = cameraCacheInfo;
        this.viewLock.lock();
        try {
            activityFromNative.runOnUiThread(new Runnable() {
                public void run() {
                    SurfaceManager.this.addSurfaceLock.lock();
                    SurfaceManager.this.retrieveGLSurfaceView();
                    try {
                        SurfaceManager.this.setupCameraSurface(SurfaceManager.this.cciForSurface);
                        ((ViewGroup) SurfaceManager.this.cameraSurfaceParentView).addView(SurfaceManager.this.cciForSurface.surface, SurfaceManager.this.glSurfaceViewChildPosition + 1, new FrameLayout.LayoutParams(-1, -1));
                        SurfaceManager.this.cciForSurface.surface.setVisibility(0);
                    } catch (Exception e) {
                    } catch (Throwable th) {
                        SurfaceManager.this.addSurfaceLock.unlock();
                        throw th;
                    }
                    SurfaceManager.this.addSurfaceLock.unlock();
                }
            });
            this.viewLock.unlock();
        } catch (Exception e) {
            this.viewLock.unlock();
            z = true;
        } catch (Throwable th) {
            this.viewLock.unlock();
            throw th;
        }
        return true ^ z;
    }
}
