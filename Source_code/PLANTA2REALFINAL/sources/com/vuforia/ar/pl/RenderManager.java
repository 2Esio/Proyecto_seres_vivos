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
    private static int viewId;
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

    /* JADX WARNING: Removed duplicated region for block: B:34:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setRenderMode(int r8) {
        /*
            r7 = this;
            com.vuforia.ar.pl.SurfaceManager r0 = r7.surfaceManager
            r1 = 6
            r2 = 0
            if (r0 != 0) goto L_0x000a
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)
            return r2
        L_0x000a:
            r0.retrieveGLSurfaceView()
            r0 = 4
            r3 = 1
            if (r8 == r3) goto L_0x0029
            r4 = 2
            if (r8 == r4) goto L_0x001d
            r5 = 3
            if (r8 == r5) goto L_0x0029
            if (r8 == r0) goto L_0x0029
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4)
            return r2
        L_0x001d:
            com.vuforia.ar.pl.SurfaceManager r0 = r7.surfaceManager
            boolean r0 = r0.setEnableRenderWhenDirty(r2)
            if (r0 == 0) goto L_0x005b
            r7.shutdownTimer()
            goto L_0x005b
        L_0x0029:
            com.vuforia.ar.pl.SurfaceManager r2 = r7.surfaceManager
            boolean r2 = r2.setEnableRenderWhenDirty(r3)
            if (r2 == 0) goto L_0x005a
            if (r8 == r3) goto L_0x0057
            if (r8 != r0) goto L_0x0036
            goto L_0x0057
        L_0x0036:
            int r0 = r7.renderMode
            if (r8 != r0) goto L_0x0042
            java.util.concurrent.ScheduledThreadPoolExecutor r0 = r7.timer
            boolean r0 = r0.isShutdown()
            if (r0 == 0) goto L_0x005a
        L_0x0042:
            boolean r0 = r7.synchronousMode
            if (r0 == 0) goto L_0x0049
            long r3 = r7.minMS
            goto L_0x004b
        L_0x0049:
            long r3 = r7.maxMS
        L_0x004b:
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x005a
            r7.delayMS = r3
            r7.startTimer()
            goto L_0x005a
        L_0x0057:
            r7.shutdownTimer()
        L_0x005a:
            r0 = r2
        L_0x005b:
            if (r0 != 0) goto L_0x0061
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)
            goto L_0x0063
        L_0x0061:
            r7.renderMode = r8
        L_0x0063:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.RenderManager.setRenderMode(int):boolean");
    }

    public boolean setRenderFpsLimits(boolean z, int i, int i2) {
        long j;
        this.synchronousMode = z;
        if (i == 0 || i2 == 0) {
            SystemTools.setSystemErrorCode(2);
            return false;
        }
        long j2 = 1;
        if (i > 1000) {
            j = 1;
        } else {
            j = 1000 / ((long) i);
        }
        this.minMS = j;
        if (i2 <= 1000) {
            j2 = 1000 / ((long) i2);
        }
        this.maxMS = j2;
        if (this.renderMode != 3) {
            return true;
        }
        if (this.synchronousMode) {
            j2 = this.minMS;
        }
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
