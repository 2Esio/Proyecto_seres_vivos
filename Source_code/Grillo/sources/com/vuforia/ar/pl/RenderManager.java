package com.vuforia.ar.pl;

import android.app.Activity;
import android.view.View;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class RenderManager {
    private static final int AR_RENDERING_MODE_CONTINUOUS = 2;
    private static final int AR_RENDERING_MODE_DISABLED = 1;
    private static final int AR_RENDERING_MODE_SYNC_WITH_CAMERA = 4;
    private static final int AR_RENDERING_MODE_UNKNOWN = 0;
    private static final int AR_RENDERING_MODE_WHENDIRTY = 3;
    private static final String MODULENAME = "RenderManager";
    private static int viewId = 0;
    long delayMS = 0;
    ScheduledFuture<?> fixedFrameRateRunnerTask;
    long maxMS = 0;
    long minMS = 0;
    int renderMode;
    AtomicBoolean renderRequestServiced;
    ScheduledFuture<?> renderRequestWatcherTask;
    AtomicBoolean renderRequested;
    SurfaceManager surfaceManager;
    boolean synchronousMode;
    ScheduledThreadPoolExecutor timer;

    private final class RenderRequestWatcher implements Runnable {
        private RenderRequestWatcher() {
        }

        public void run() {
            if (RenderManager.this.renderRequested.compareAndSet(true, false) && RenderManager.this.surfaceManager != null) {
                RenderManager.this.surfaceManager.requestRender();
                RenderManager.this.renderRequestServiced.set(true);
                if (RenderManager.this.fixedFrameRateRunnerTask == null) {
                    RenderManager renderManager = RenderManager.this;
                    renderManager.fixedFrameRateRunnerTask = renderManager.timer.scheduleAtFixedRate(new FixedFrameRateRunner(), 0, RenderManager.this.delayMS, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    private final class FixedFrameRateRunner implements Runnable {
        private FixedFrameRateRunner() {
        }

        public void run() {
            if (!RenderManager.this.renderRequestServiced.getAndSet(false) && RenderManager.this.surfaceManager != null) {
                RenderManager.this.surfaceManager.requestRender();
                if (!RenderManager.this.synchronousMode && !RenderManager.this.renderRequestWatcherTask.isCancelled()) {
                    RenderManager.this.renderRequestWatcherTask.cancel(false);
                }
            }
        }
    }

    public RenderManager(SurfaceManager surfaceManager2) {
        this.surfaceManager = surfaceManager2;
        this.renderMode = 2;
        this.timer = new ScheduledThreadPoolExecutor(1);
        this.synchronousMode = false;
        this.renderRequestServiced = new AtomicBoolean(false);
        this.renderRequested = new AtomicBoolean(false);
    }

    /* access modifiers changed from: package-private */
    public void startTimer() {
        if (this.timer.isShutdown()) {
            this.timer = new ScheduledThreadPoolExecutor(1);
        }
        ScheduledFuture<?> scheduledFuture = this.fixedFrameRateRunnerTask;
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            this.fixedFrameRateRunnerTask.cancel(true);
        }
        ScheduledFuture<?> scheduledFuture2 = this.renderRequestWatcherTask;
        if (scheduledFuture2 != null && !scheduledFuture2.isCancelled()) {
            this.renderRequestWatcherTask.cancel(true);
        }
        this.fixedFrameRateRunnerTask = null;
        this.renderRequestWatcherTask = null;
        long j = this.delayMS;
        this.renderRequestWatcherTask = this.timer.scheduleWithFixedDelay(new RenderRequestWatcher(), 0, j < 4 ? 1 : j / 4, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: package-private */
    public void shutdownTimer() {
        if (!this.timer.isShutdown()) {
            this.timer.shutdown();
        }
    }

    public boolean canSetRenderMode() {
        boolean retrieveGLSurfaceView = this.surfaceManager.retrieveGLSurfaceView();
        if (!retrieveGLSurfaceView) {
            DebugLog.LOGD(MODULENAME, "Could not retrieve a valid GLSurfaceView in view hierarchy, therefore cannot set any render mode");
        }
        return retrieveGLSurfaceView;
    }

    public int getRenderMode() {
        return this.renderMode;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setRenderMode(int r8) {
        /*
            r7 = this;
            com.vuforia.ar.pl.SurfaceManager r0 = r7.surfaceManager
            r1 = 6
            r2 = 0
            if (r0 != 0) goto L_0x000b
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)
            return r2
        L_0x000b:
            r0.retrieveGLSurfaceView()
            r0 = 4
            r3 = 1
            if (r8 == r3) goto L_0x002a
            r4 = 2
            if (r8 == r4) goto L_0x001e
            r5 = 3
            if (r8 == r5) goto L_0x002a
            if (r8 == r0) goto L_0x002a
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4)
            return r2
        L_0x001e:
            com.vuforia.ar.pl.SurfaceManager r0 = r7.surfaceManager
            boolean r0 = r0.setEnableRenderWhenDirty(r2)
            if (r0 == 0) goto L_0x005c
            r7.shutdownTimer()
            goto L_0x005c
        L_0x002a:
            com.vuforia.ar.pl.SurfaceManager r2 = r7.surfaceManager
            boolean r2 = r2.setEnableRenderWhenDirty(r3)
            if (r2 == 0) goto L_0x005b
            if (r8 == r3) goto L_0x0058
            if (r8 != r0) goto L_0x0037
            goto L_0x0058
        L_0x0037:
            int r0 = r7.renderMode
            if (r8 != r0) goto L_0x0043
            java.util.concurrent.ScheduledThreadPoolExecutor r0 = r7.timer
            boolean r0 = r0.isShutdown()
            if (r0 == 0) goto L_0x005b
        L_0x0043:
            boolean r0 = r7.synchronousMode
            if (r0 == 0) goto L_0x004a
            long r3 = r7.minMS
            goto L_0x004c
        L_0x004a:
            long r3 = r7.maxMS
        L_0x004c:
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x0057
            r7.delayMS = r3
            r7.startTimer()
        L_0x0057:
            goto L_0x005b
        L_0x0058:
            r7.shutdownTimer()
        L_0x005b:
            r0 = r2
        L_0x005c:
            if (r0 != 0) goto L_0x0062
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)
            goto L_0x0064
        L_0x0062:
            r7.renderMode = r8
        L_0x0064:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.RenderManager.setRenderMode(int):boolean");
    }

    public boolean setRenderFpsLimits(boolean z, int i, int i2) {
        this.synchronousMode = z;
        if (i == 0 || i2 == 0) {
            SystemTools.setSystemErrorCode(2);
            return false;
        }
        long j = 1;
        this.minMS = i > 1000 ? 1 : 1000 / ((long) i);
        if (i2 <= 1000) {
            j = 1000 / ((long) i2);
        }
        this.maxMS = j;
        if (this.renderMode != 3) {
            return true;
        }
        long j2 = this.synchronousMode ? this.minMS : this.maxMS;
        if (j2 == this.delayMS) {
            return true;
        }
        this.delayMS = j2;
        startTimer();
        return true;
    }

    public boolean requestRender() {
        if (this.renderMode == 4) {
            SurfaceManager surfaceManager2 = this.surfaceManager;
            if (surfaceManager2 != null) {
                surfaceManager2.requestRender();
            }
        } else {
            this.renderRequested.set(true);
        }
        return true;
    }

    public View addOverlay(byte[] bArr, int i, int i2, float[] fArr, int[] iArr) {
        final Activity activityFromNative = SystemTools.getActivityFromNative();
        if (activityFromNative == null) {
            DebugLog.LOGE(MODULENAME, "drawOverlay could not get access to an activity");
            return null;
        }
        final DrawOverlayView drawOverlayView = new DrawOverlayView(activityFromNative, bArr, i, i2, fArr, iArr);
        activityFromNative.runOnUiThread(new Runnable() {
            public void run() {
                drawOverlayView.addOverlay(activityFromNative);
            }
        });
        return drawOverlayView;
    }

    public boolean removeOverlay(final View view) {
        final Activity activityFromNative = SystemTools.getActivityFromNative();
        if (activityFromNative == null || view == null) {
            return false;
        }
        activityFromNative.runOnUiThread(new Runnable() {
            public void run() {
                new DrawOverlayView(activityFromNative).removeOverlay(activityFromNative, view);
            }
        });
        return true;
    }
}
