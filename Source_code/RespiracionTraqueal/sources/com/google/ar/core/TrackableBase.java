package com.google.ar.core;

import com.google.ar.core.annotations.UsedByNative;
import java.util.Collection;

@UsedByNative("trackable_base_jni.cc")
class TrackableBase implements Trackable {
    long nativeHandle;
    final Session session;

    TrackableBase(long j, Session session2) {
        this.session = session2;
        this.nativeHandle = j;
    }

    private native long nativeCreateAnchor(long j, long j2, Pose pose);

    private native long[] nativeGetAnchors(long j, long j2);

    private native int nativeGetTrackingState(long j, long j2);

    private static native int nativeGetType(long j, long j2);

    private static native void nativeReleaseTrackable(long j);

    public boolean equals(Object obj) {
        if ((obj instanceof TrackableBase) && ((TrackableBase) obj).nativeHandle == this.nativeHandle) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Long.valueOf(this.nativeHandle).hashCode();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        long j = this.nativeHandle;
        if (j != 0) {
            nativeReleaseTrackable(j);
            this.nativeHandle = 0;
        }
        super.finalize();
    }

    public TrackingState getTrackingState() {
        return TrackingState.forNumber(nativeGetTrackingState(this.session.nativeWrapperHandle, this.nativeHandle));
    }

    public Anchor createAnchor(Pose pose) {
        return new Anchor(nativeCreateAnchor(this.session.nativeWrapperHandle, this.nativeHandle, pose), this.session);
    }

    public Collection<Anchor> getAnchors() {
        Session session2 = this.session;
        return session2.convertNativeAnchorsToCollection(nativeGetAnchors(session2.nativeWrapperHandle, this.nativeHandle));
    }

    static void internalReleaseNativeHandle(long j) {
        nativeReleaseTrackable(j);
    }

    static int internalGetType(long j, long j2) {
        return nativeGetType(j, j2);
    }
}
