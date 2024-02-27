package com.google.ar.core;

import android.media.Image;
import android.view.MotionEvent;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.NotYetAvailableException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Frame {
    static final ArrayList<Anchor> ANCHORS_EMPTY_LIST = new ArrayList<>();
    static final ArrayList<Plane> PLANES_EMPTY_LIST = new ArrayList<>();
    private static final String TAG = "ARCore-Frame";
    private final LightEstimate lightEstimate;
    long nativeHandle;
    final Session session;

    Frame(Session session2) {
        this(session2, nativeCreateFrame(session2.nativeWrapperHandle));
    }

    private native long nativeAcquireCameraImage(long j, long j2);

    private native long nativeAcquireImageMetadata(long j, long j2);

    private native long[] nativeAcquireUpdatedAnchors(long j, long j2);

    private static native long nativeCreateFrame(long j);

    private static native void nativeDestroyFrame(long j);

    private native long nativeGetAndroidCameraTimestamp(long j, long j2);

    private native Pose nativeGetAndroidSensorPose(long j, long j2);

    private native void nativeGetLightEstimate(long j, long j2, long j3);

    private native long nativeGetTimestamp(long j, long j2);

    private native boolean nativeHasDisplayGeometryChanged(long j, long j2);

    private native void nativeTransformCoordinates2dFloatArrayOrBuffer(long j, long j2, int i, Object obj, int i2, Object obj2);

    private native void nativeTransformDisplayUvCoords(long j, long j2, FloatBuffer floatBuffer, FloatBuffer floatBuffer2);

    /* access modifiers changed from: package-private */
    public native long nativeAcquirePointCloud(long j, long j2);

    /* access modifiers changed from: package-private */
    public native long[] nativeAcquireUpdatedTrackables(long j, long j2, int i);

    /* access modifiers changed from: package-private */
    public native long[] nativeHitTest(long j, long j2, float f, float f2);

    /* access modifiers changed from: package-private */
    public native long[] nativeHitTestRay(long j, long j2, float[] fArr, int i, float[] fArr2, int i2);

    Frame(Session session2, long j) {
        this.nativeHandle = 0;
        this.session = session2;
        this.nativeHandle = j;
        this.lightEstimate = new LightEstimate(session2);
    }

    protected Frame() {
        this.nativeHandle = 0;
        this.session = null;
        this.nativeHandle = 0;
        this.lightEstimate = null;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        long j = this.nativeHandle;
        if (j != 0) {
            nativeDestroyFrame(j);
            this.nativeHandle = 0;
        }
        super.finalize();
    }

    public Camera getCamera() {
        return new Camera(this.session, this);
    }

    public boolean hasDisplayGeometryChanged() {
        return nativeHasDisplayGeometryChanged(this.session.nativeWrapperHandle, this.nativeHandle);
    }

    @Deprecated
    public void transformDisplayUvCoords(FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (!floatBuffer.isDirect() || !floatBuffer2.isDirect()) {
            throw new IllegalArgumentException("transformDisplayUvCoords currently requires direct buffers.");
        }
        nativeTransformDisplayUvCoords(this.session.nativeWrapperHandle, this.nativeHandle, floatBuffer, floatBuffer2);
    }

    public long getTimestamp() {
        return nativeGetTimestamp(this.session.nativeWrapperHandle, this.nativeHandle);
    }

    public long getAndroidCameraTimestamp() {
        return nativeGetAndroidCameraTimestamp(this.session.nativeWrapperHandle, this.nativeHandle);
    }

    public Pose getAndroidSensorPose() {
        return nativeGetAndroidSensorPose(this.session.nativeWrapperHandle, this.nativeHandle);
    }

    public LightEstimate getLightEstimate() {
        nativeGetLightEstimate(this.session.nativeWrapperHandle, this.nativeHandle, this.lightEstimate.nativeHandle);
        return this.lightEstimate;
    }

    public ImageMetadata getImageMetadata() throws NotYetAvailableException {
        if (!isSharedCameraUsed()) {
            return new ImageMetadata(nativeAcquireImageMetadata(this.session.nativeWrapperHandle, this.nativeHandle), this.session);
        }
        throw new IllegalStateException("Metadata access is not available from Frame when shared camera is in use.");
    }

    private boolean isSharedCameraUsed() {
        return this.session.isSharedCameraUsed();
    }

    public PointCloud acquirePointCloud() {
        return new PointCloud(this.session, nativeAcquirePointCloud(this.session.nativeWrapperHandle, this.nativeHandle));
    }

    public List<HitResult> hitTest(float f, float f2) {
        return convertNativeHitResultsToList(nativeHitTest(this.session.nativeWrapperHandle, this.nativeHandle, f, f2));
    }

    public List<HitResult> hitTest(MotionEvent motionEvent) {
        return hitTest(motionEvent.getX(), motionEvent.getY());
    }

    public List<HitResult> hitTest(float[] fArr, int i, float[] fArr2, int i2) {
        return convertNativeHitResultsToList(nativeHitTestRay(this.session.nativeWrapperHandle, this.nativeHandle, fArr, i, fArr2, i2));
    }

    private List<HitResult> convertNativeHitResultsToList(long[] jArr) {
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long hitResult : jArr) {
            HitResult hitResult2 = new HitResult(hitResult, this.session);
            if (hitResult2.getTrackable() != null) {
                arrayList.add(hitResult2);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Collection<Anchor> getUpdatedAnchors() {
        Session session2 = this.session;
        return session2.convertNativeAnchorsToCollection(nativeAcquireUpdatedAnchors(session2.nativeWrapperHandle, this.nativeHandle));
    }

    public <T extends Trackable> Collection<T> getUpdatedTrackables(Class<T> cls) {
        Session.b a2 = Session.b.a(cls);
        if (a2 == Session.b.UNKNOWN_TO_JAVA) {
            return Collections.emptyList();
        }
        return this.session.convertNativeTrackablesToCollection(cls, nativeAcquireUpdatedTrackables(this.session.nativeWrapperHandle, this.nativeHandle, a2.b));
    }

    public Image acquireCameraImage() throws NotYetAvailableException {
        return new ArImage(this.session, nativeAcquireCameraImage(this.session.nativeWrapperHandle, this.nativeHandle));
    }

    public void transformCoordinates2d(Coordinates2d coordinates2d, FloatBuffer floatBuffer, Coordinates2d coordinates2d2, FloatBuffer floatBuffer2) {
        nativeTransformCoordinates2dFloatArrayOrBuffer(this.session.nativeWrapperHandle, this.nativeHandle, coordinates2d.nativeCode, floatBuffer, coordinates2d2.nativeCode, floatBuffer2);
    }

    public void transformCoordinates2d(Coordinates2d coordinates2d, float[] fArr, Coordinates2d coordinates2d2, float[] fArr2) {
        nativeTransformCoordinates2dFloatArrayOrBuffer(this.session.nativeWrapperHandle, this.nativeHandle, coordinates2d.nativeCode, fArr, coordinates2d2.nativeCode, fArr2);
    }
}
