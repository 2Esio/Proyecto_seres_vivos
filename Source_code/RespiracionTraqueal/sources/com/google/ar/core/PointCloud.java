package com.google.ar.core;

import com.google.ar.core.exceptions.DeadlineExceededException;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class PointCloud implements Closeable {
    private long nativeHandle;
    private final Session session;

    PointCloud(Session session2, long j) {
        this.nativeHandle = 0;
        this.session = session2;
        this.nativeHandle = j;
    }

    private native ByteBuffer nativeGetData(long j, long j2);

    private native ByteBuffer nativeGetIds(long j, long j2);

    private native long nativeGetTimestamp(long j, long j2);

    private native void nativeReleasePointCloud(long j);

    protected PointCloud() {
        this.nativeHandle = 0;
        this.session = null;
        this.nativeHandle = 0;
    }

    public void release() {
        nativeReleasePointCloud(this.nativeHandle);
        this.nativeHandle = 0;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        long j = this.nativeHandle;
        if (j != 0) {
            nativeReleasePointCloud(j);
        }
        super.finalize();
    }

    public FloatBuffer getPoints() {
        if (this.nativeHandle != 0) {
            return Session.directByteBufferOrDefault(nativeGetData(this.session.nativeWrapperHandle, this.nativeHandle)).asFloatBuffer();
        }
        throw new DeadlineExceededException();
    }

    public IntBuffer getIds() {
        if (this.nativeHandle != 0) {
            return Session.directByteBufferOrDefault(nativeGetIds(this.session.nativeWrapperHandle, this.nativeHandle)).asIntBuffer();
        }
        throw new DeadlineExceededException();
    }

    public long getTimestamp() {
        if (this.nativeHandle != 0) {
            return nativeGetTimestamp(this.session.nativeWrapperHandle, this.nativeHandle);
        }
        throw new DeadlineExceededException();
    }

    public void close() {
        release();
    }
}
