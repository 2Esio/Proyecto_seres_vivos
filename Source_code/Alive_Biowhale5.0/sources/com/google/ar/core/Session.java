package com.google.ar.core;

import android.content.Context;
import android.os.Build;
import com.google.ar.core.Config;
import com.google.ar.core.annotations.UsedByNative;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.FatalException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class Session {
    private static final String TAG = "ARCore-Session";
    final n faceCache;
    final long nativeSymbolTableHandle;
    long nativeWrapperHandle;
    private SharedCamera sharedCamera;

    protected Session() {
        this.faceCache = new n();
        this.sharedCamera = null;
        this.nativeWrapperHandle = 0;
        this.nativeSymbolTableHandle = 0;
    }

    private native long[] nativeAcquireAllAnchors(long j);

    private native int nativeCheckModuleAvailability(long j, int i);

    private native void nativeCloseSession(long j);

    private native void nativeConfigure(long j, long j2);

    private native long nativeCreateAnchor(long j, Pose pose);

    private static native long nativeCreateSessionAndWrapperWithFeatures(Context context, int[] iArr) throws UnavailableArcoreNotInstalledException, UnavailableApkTooOldException, UnavailableSdkTooOldException, UnavailableDeviceNotCompatibleException;

    static native long nativeCreateSessionWrapperFromHandle(long j, long j2);

    private native long nativeGetCameraConfig(long j);

    private native void nativeGetConfig(long j, long j2);

    private native void nativeGetRandomAccessStats(long j, long j2);

    private native long[] nativeGetSupportedCameraConfigs(long j);

    private native long[] nativeGetSupportedCameraConfigsWithFilter(long j, long j2);

    private native long nativeGetSymbolTable(long j);

    private native long nativeHostCloudAnchor(long j, long j2);

    private native boolean nativeIsSupported(long j, long j2);

    private native void nativePause(long j);

    private native long nativeResolveCloudAnchor(long j, String str);

    private native void nativeResume(long j);

    private native int nativeSetCameraConfig(long j, long j2);

    private native void nativeSetCameraTextureName(long j, int i);

    private native void nativeSetCameraTextureNames(long j, int[] iArr);

    private native void nativeSetDisplayGeometry(long j, int i, int i2, int i3);

    private native void nativeUpdate(long j, long j2);

    /* access modifiers changed from: package-private */
    public boolean isSharedCameraUsed() {
        return this.sharedCamera != null;
    }

    /* access modifiers changed from: package-private */
    public native long[] nativeAcquireAllTrackables(long j, int i);

    /* access modifiers changed from: package-private */
    public native void nativeDestroySessionWrapper(long j);

    /* access modifiers changed from: package-private */
    public native long nativeGetSessionNativeHandle(long j);

    /* access modifiers changed from: package-private */
    public native boolean nativeIsDepthModeSupported(long j, int i);

    /* access modifiers changed from: package-private */
    public native long nativeReleaseSessionOwnership(long j);

    public enum Feature {
        FRONT_CAMERA(1),
        SHARED_CAMERA(1000);
        
        final int nativeCode;

        private Feature(int i) {
            this.nativeCode = i;
        }
    }

    Session(long j) {
        this.faceCache = new n();
        this.sharedCamera = null;
        this.nativeWrapperHandle = j;
        this.nativeSymbolTableHandle = nativeGetSymbolTable(j);
    }

    public Session(Context context) throws UnavailableArcoreNotInstalledException, UnavailableApkTooOldException, UnavailableSdkTooOldException, UnavailableDeviceNotCompatibleException {
        this(context, EnumSet.noneOf(Feature.class));
    }

    public Session(Context context, Set<Feature> set) throws UnavailableArcoreNotInstalledException, UnavailableApkTooOldException, UnavailableSdkTooOldException, UnavailableDeviceNotCompatibleException, IllegalArgumentException {
        this.faceCache = new n();
        this.sharedCamera = null;
        System.loadLibrary("arcore_sdk_jni");
        int[] iArr = new int[(set.size() + 1)];
        int i = 0;
        for (Feature feature : set) {
            iArr[i] = feature.nativeCode;
            i++;
        }
        iArr[i] = 0;
        long nativeCreateSessionAndWrapperWithFeatures = nativeCreateSessionAndWrapperWithFeatures(context, iArr);
        this.nativeWrapperHandle = nativeCreateSessionAndWrapperWithFeatures;
        this.nativeSymbolTableHandle = nativeGetSymbolTable(nativeCreateSessionAndWrapperWithFeatures);
        if (set.contains(Feature.SHARED_CAMERA)) {
            this.sharedCamera = new SharedCamera(this);
        }
        loadDynamicSymbolsAfterSessionCreate();
    }

    public void close() {
        nativeCloseSession(this.nativeWrapperHandle);
    }

    public void configure(Config config) {
        nativeConfigure(this.nativeWrapperHandle, config.nativeHandle);
    }

    /* access modifiers changed from: package-private */
    public Collection<Anchor> convertNativeAnchorsToCollection(long[] jArr) {
        ArrayList arrayList = new ArrayList(r1);
        for (long anchor : jArr) {
            arrayList.add(new Anchor(anchor, this));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: package-private */
    public List<CameraConfig> convertNativeCameraConfigsToCollection(long[] jArr) {
        ArrayList arrayList = new ArrayList(r1);
        for (long cameraConfig : jArr) {
            arrayList.add(new CameraConfig(this, cameraConfig));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: package-private */
    public <T extends Trackable> Collection<T> convertNativeTrackablesToCollection(Class<T> cls, long[] jArr) {
        ArrayList arrayList = new ArrayList(r1);
        for (long createTrackable : jArr) {
            Trackable createTrackable2 = createTrackable(createTrackable);
            if (createTrackable2 != null) {
                arrayList.add((Trackable) cls.cast(createTrackable2));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Anchor createAnchor(Pose pose) {
        return new Anchor(nativeCreateAnchor(this.nativeWrapperHandle, pose), this);
    }

    @Deprecated
    public static Session createForSharedCamera(Context context) throws UnavailableArcoreNotInstalledException, UnavailableApkTooOldException, UnavailableSdkTooOldException, UnavailableDeviceNotCompatibleException {
        return new Session(context, EnumSet.of(Feature.SHARED_CAMERA));
    }

    /* access modifiers changed from: package-private */
    public Trackable createTrackable(long j) {
        ah ahVar;
        int internalGetType = TrackableBase.internalGetType(this.nativeWrapperHandle, j);
        ah[] values = ah.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                ahVar = null;
                break;
            }
            ahVar = values[i];
            if (ahVar.g == internalGetType) {
                break;
            }
            i++;
        }
        if (ahVar == null) {
            TrackableBase.internalReleaseNativeHandle(this.nativeSymbolTableHandle, j);
            return null;
        }
        int ordinal = ahVar.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return null;
        }
        if (ordinal == 2) {
            return new Plane(j, this);
        }
        if (ordinal == 3) {
            return new Point(j, this);
        }
        if (ordinal == 4) {
            return new AugmentedImage(j, this);
        }
        if (ordinal == 5) {
            return this.faceCache.a(j, this);
        }
        throw null;
    }

    static ByteBuffer directByteBufferOrDefault(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());
        }
        return byteBuffer.order(ByteOrder.nativeOrder());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        long j = this.nativeWrapperHandle;
        if (j != 0) {
            nativeDestroySessionWrapper(j);
            this.nativeWrapperHandle = 0;
        }
        super.finalize();
    }

    public Collection<Anchor> getAllAnchors() {
        return convertNativeAnchorsToCollection(nativeAcquireAllAnchors(this.nativeWrapperHandle));
    }

    public <T extends Trackable> Collection<T> getAllTrackables(Class<T> cls) {
        ah a2 = ah.a(cls);
        if (a2 == ah.UNKNOWN_TO_JAVA) {
            return Collections.emptyList();
        }
        return convertNativeTrackablesToCollection(cls, nativeAcquireAllTrackables(this.nativeWrapperHandle, a2.g));
    }

    public CameraConfig getCameraConfig() {
        return new CameraConfig(this, nativeGetCameraConfig(this.nativeWrapperHandle));
    }

    public Config getConfig() {
        Config config = new Config(this);
        getConfig(config);
        return config;
    }

    public void getConfig(Config config) {
        nativeGetConfig(this.nativeWrapperHandle, config.nativeHandle);
    }

    public SharedCamera getSharedCamera() {
        SharedCamera sharedCamera2 = this.sharedCamera;
        if (sharedCamera2 != null) {
            return sharedCamera2;
        }
        throw new IllegalStateException("Shared camera is not in use, please create session using new Session(context, EnumSet.of(Session.Feature.SHARED_CAMERA)).");
    }

    @Deprecated
    public List<CameraConfig> getSupportedCameraConfigs() {
        return convertNativeCameraConfigsToCollection(nativeGetSupportedCameraConfigs(this.nativeWrapperHandle));
    }

    public List<CameraConfig> getSupportedCameraConfigs(CameraConfigFilter cameraConfigFilter) {
        if (cameraConfigFilter != null) {
            ArrayList arrayList = new ArrayList(r1);
            for (long cameraConfig : nativeGetSupportedCameraConfigsWithFilter(this.nativeWrapperHandle, cameraConfigFilter.nativeHandle)) {
                arrayList.add(new CameraConfig(this, cameraConfig));
            }
            return Collections.unmodifiableList(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public Anchor hostCloudAnchor(Anchor anchor) {
        return new Anchor(nativeHostCloudAnchor(this.nativeWrapperHandle, anchor.nativeHandle), this);
    }

    public boolean isDepthModeSupported(Config.DepthMode depthMode) {
        return nativeIsDepthModeSupported(this.nativeWrapperHandle, depthMode.nativeCode);
    }

    @Deprecated
    public boolean isSupported(Config config) {
        return nativeIsSupported(this.nativeWrapperHandle, config.nativeHandle);
    }

    static void loadDynamicSymbolsAfterSessionCreate() {
        if (Build.VERSION.SDK_INT >= 24) {
            ArImage.nativeLoadSymbols();
            ImageMetadata.nativeLoadSymbols();
        }
    }

    public void pause() {
        pauseSharedCameraIfInUse();
        nativePause(this.nativeWrapperHandle);
    }

    private void pauseSharedCameraIfInUse() {
        SharedCamera sharedCamera2 = this.sharedCamera;
        if (sharedCamera2 != null) {
            sharedCamera2.pause();
        }
    }

    public Anchor resolveCloudAnchor(String str) {
        return new Anchor(nativeResolveCloudAnchor(this.nativeWrapperHandle, str), this);
    }

    public void resume() throws CameraNotAvailableException {
        nativeResume(this.nativeWrapperHandle);
    }

    public void setCameraConfig(CameraConfig cameraConfig) {
        nativeSetCameraConfig(this.nativeWrapperHandle, cameraConfig.nativeHandle);
    }

    public void setCameraTextureName(int i) {
        nativeSetCameraTextureName(this.nativeWrapperHandle, i);
    }

    public void setCameraTextureNames(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("textureIds must be an array with at least 1 entry.");
        }
        nativeSetCameraTextureNames(this.nativeWrapperHandle, iArr);
    }

    public void setDisplayGeometry(int i, int i2, int i3) {
        nativeSetDisplayGeometry(this.nativeWrapperHandle, i, i2, i3);
    }

    static void throwExceptionFromArStatus(int i) throws Exception {
        throwExceptionFromArStatus((String) null, i, (String[]) null, (int[]) null);
    }

    @UsedByNative("session_jni.cc")
    static void throwExceptionFromArStatus(String str, int i, String[] strArr, int[] iArr) throws Exception {
        int i2;
        for (ag agVar : ag.values()) {
            if (agVar.A == i) {
                Class<? extends Exception> cls = agVar.B;
                if (cls != null) {
                    if (strArr == null || iArr == null || (i2 = strArr.length) != iArr.length) {
                        i2 = 0;
                    }
                    String str2 = agVar.C;
                    if (str2 == null && str == null) {
                        throw ((Exception) cls.getConstructor(new Class[0]).newInstance(new Object[0]));
                    }
                    if (str2 != null) {
                        if (str == null) {
                            str = str2;
                        } else {
                            String valueOf = String.valueOf(str2);
                            String valueOf2 = String.valueOf(str);
                            str = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                        }
                    }
                    Exception exc = (Exception) agVar.B.getConstructor(new Class[]{String.class}).newInstance(new Object[]{str});
                    StackTraceElement[] stackTrace = exc.getStackTrace();
                    StackTraceElement[] stackTraceElementArr = new StackTraceElement[(stackTrace.length + i2)];
                    int i3 = 0;
                    while (i3 < i2) {
                        stackTraceElementArr[i3] = new StackTraceElement("ARCore", "native", strArr[i3], iArr[i3]);
                        i3++;
                    }
                    for (StackTraceElement stackTraceElement : stackTrace) {
                        stackTraceElementArr[i3] = stackTraceElement;
                        i3++;
                    }
                    exc.setStackTrace(stackTraceElementArr);
                    throw exc;
                }
                return;
            }
        }
        StringBuilder sb = new StringBuilder(34);
        sb.append("Unexpected error code: ");
        sb.append(i);
        throw new FatalException(sb.toString());
    }

    public Frame update() throws CameraNotAvailableException {
        Frame frame = new Frame(this);
        nativeUpdate(this.nativeWrapperHandle, frame.nativeHandle);
        return frame;
    }
}
