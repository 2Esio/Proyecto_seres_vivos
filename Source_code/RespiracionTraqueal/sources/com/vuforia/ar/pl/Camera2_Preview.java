package com.vuforia.ar.pl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Trace;
import android.util.Pair;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(21)
public class Camera2_Preview {
    private static final int AR_CAMERA_DIRECTION_BACK = 268443665;
    private static final int AR_CAMERA_DIRECTION_FRONT = 268443666;
    private static final int AR_CAMERA_DIRECTION_UNKNOWN = 268443664;
    private static final int AR_CAMERA_EXPOSUREMODE_AUTO = 805314560;
    private static final int AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO = 805322752;
    private static final int AR_CAMERA_EXPOSUREMODE_LOCKED = 805310464;
    private static final int AR_CAMERA_EXPOSUREMODE_MANUAL = 805339136;
    private static final int AR_CAMERA_EXPOSUREMODE_SHUTTER_PRIORITY = 805371904;
    private static final int AR_CAMERA_FOCUSMODE_AUTO = 805306400;
    private static final int AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO = 805306432;
    private static final int AR_CAMERA_FOCUSMODE_FIXED = 805306880;
    private static final int AR_CAMERA_FOCUSMODE_INFINITY = 805306624;
    private static final int AR_CAMERA_FOCUSMODE_MACRO = 805306496;
    private static final int AR_CAMERA_FOCUSMODE_NORMAL = 805306384;
    private static final int AR_CAMERA_IMAGE_FORMAT_ARGB32 = 268439813;
    private static final int AR_CAMERA_IMAGE_FORMAT_ARGB8888 = 268439813;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGR24 = 268439822;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGR888 = 268439822;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGRA32 = 268439814;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGRA8888 = 268439814;
    private static final int AR_CAMERA_IMAGE_FORMAT_LUM = 268439809;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV12 = 268439815;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV16 = 268439816;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV21 = 268439817;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB24 = 268439811;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB565 = 268439810;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB888 = 268439811;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA32 = 268439812;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA4444 = 268439821;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA5551 = 268439820;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA8888 = 268439812;
    private static final int AR_CAMERA_IMAGE_FORMAT_UNKNOWN = 268439808;
    private static final int AR_CAMERA_IMAGE_FORMAT_YUV420P = 268439828;
    private static final int AR_CAMERA_IMAGE_FORMAT_YV12 = 268439818;
    private static final int AR_CAMERA_IMAGE_FORMAT_YV16 = 268439819;
    private static final int AR_CAMERA_PARAMTYPE_BASE = 536870912;
    private static final int AR_CAMERA_PARAMTYPE_BRIGHTNESSRANGE = 537133056;
    private static final int AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE = 537001984;
    private static final int AR_CAMERA_PARAMTYPE_CONTRASTRANGE = 537919488;
    private static final int AR_CAMERA_PARAMTYPE_CONTRASTVALUE = 537395200;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREMODE = 536870944;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSURETIME = 536871168;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSURETIMERANGE = 536871424;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREVALUE = 536871936;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE = 536872960;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSMODE = 536870914;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSRANGE = 536870920;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSREGION = 536870928;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSVALUE = 536870916;
    private static final int AR_CAMERA_PARAMTYPE_ISO = 536870976;
    private static final int AR_CAMERA_PARAMTYPE_ISORANGE = 536871040;
    private static final int AR_CAMERA_PARAMTYPE_LENS_IS_ADJUSTING = 545259520;
    private static final int AR_CAMERA_PARAMTYPE_RECORDING_HINT = 541065216;
    private static final int AR_CAMERA_PARAMTYPE_ROTATION = 538968064;
    private static final int AR_CAMERA_PARAMTYPE_TORCHMODE = 536870913;
    private static final int AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION = 553648128;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCEMODE = 536875008;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCERANGE = 536887296;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCEVALUE = 536879104;
    private static final int AR_CAMERA_PARAMTYPE_ZOOMRANGE = 536936448;
    private static final int AR_CAMERA_PARAMTYPE_ZOOMVALUE = 536903680;
    private static final int AR_CAMERA_PARAMVALUE_BASE = 805306368;
    private static final int AR_CAMERA_STATUS_CAPTURE_RUNNING = 268443651;
    private static final int AR_CAMERA_STATUS_OPENED = 268443650;
    private static final int AR_CAMERA_STATUS_UNINITIALIZED = 268443649;
    private static final int AR_CAMERA_STATUS_UNKNOWN = 268443648;
    private static final int AR_CAMERA_TORCHMODE_AUTO = 805306372;
    private static final int AR_CAMERA_TORCHMODE_CONTINUOUSAUTO = 805306376;
    private static final int AR_CAMERA_TORCHMODE_OFF = 805306369;
    private static final int AR_CAMERA_TORCHMODE_ON = 805306370;
    private static final int AR_CAMERA_TYPE_MONO = 268447761;
    private static final int AR_CAMERA_TYPE_STEREO = 268447762;
    private static final int AR_CAMERA_TYPE_UNKNOWN = 268447760;
    private static final int AR_CAMERA_WHITEBALANCEMODE_AUTO = 807403520;
    private static final int AR_CAMERA_WHITEBALANCEMODE_CONTINUOUSAUTO = 809500672;
    private static final int AR_CAMERA_WHITEBALANCEMODE_LOCKED = 806354944;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_FRAMERATES = 4;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_IMAGEFORMATS = 5;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_IMAGESIZES = 3;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_PARAMVALUES = 2;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_QUERYABLE_PARAMS = 0;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_SETTABLE_PARAMS = 1;
    private static final int CAMERA_CAPTUREINFO_VALUE_FORMAT = 2;
    private static final int CAMERA_CAPTUREINFO_VALUE_FRAMERATE = 3;
    private static final int CAMERA_CAPTUREINFO_VALUE_HEIGHT = 1;
    private static final int CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED = 4;
    private static final int CAMERA_CAPTUREINFO_VALUE_WIDTH = 0;
    private static final int[] CAMERA_VALID_IMAGE_FORMAT_PL = {AR_CAMERA_IMAGE_FORMAT_NV21, AR_CAMERA_IMAGE_FORMAT_NV12, AR_CAMERA_IMAGE_FORMAT_YV12, AR_CAMERA_IMAGE_FORMAT_YUV420P};
    private static final Range<Integer> EMPTY_RANGE = new Range<>(0, 0);
    private static final String FOCUS_MODE_NORMAL = "normal";
    private static final int MAX_HIGHEST_FPS_ALLOWED = 300;
    private static final int MAX_LOWEST_FPS_ALLOWED = 150;
    private static final String MODULENAME = "Camera2_Preview";
    private static final int NUM_CAPTURE_BUFFERS = 2;
    private static final int NUM_MAX_CAMERAOPEN_RETRY = 10;
    private static final int TIME_CAMERAOPEN_RETRY_DELAY_MS = 250;
    private static final int _NUM_CAMERA_CAPSINFO_VALUE_ = 6;
    private static final int _NUM_CAMERA_CAPTUREINFO_VALUE_ = 5;
    /* access modifiers changed from: private */
    public HashMap<ImageReader, Integer> mCameraCacheInfoIndexCache = null;
    /* access modifiers changed from: private */
    public Vector<CameraCacheInfo> mCameraCacheInfos = null;
    /* access modifiers changed from: private */
    public Vector<CameraCacheInfo> mCameraCacheInfosInProgress = null;
    private CameraManager mCameraManager;
    private Context mContext;
    private int mIsPermissionGranted = -1;
    /* access modifiers changed from: private */
    public Semaphore mOpenCloseSemaphore = new Semaphore(1);

    /* access modifiers changed from: private */
    public native long getBufferAddress(ByteBuffer byteBuffer);

    /* access modifiers changed from: private */
    public native void newFrameAvailable(long j, int i, int i2, int i3, int[] iArr, int i4, ByteBuffer byteBuffer, Object obj);

    public class CameraCacheInfo {
        int bufferFormatPL;
        int bufferHeight;
        int bufferWidth;
        CaptureRequest.Builder builder;
        int[] caps;
        CameraCharacteristics characteristics;
        CameraDevice device;
        long deviceHandle;
        int deviceID;
        String deviceIDString;
        Handler handler;
        Semaphore imageSemaphore;
        Image[] images;
        boolean isAutoFocusing;
        CaptureResult lastResult;
        int overrideFormatAndroid;
        int overrideFormatPL;
        int overrideHeight;
        int overrideWidth;
        ImageReader reader;
        int requestFormatAndroid;
        int requestFormatPL;
        int requestFramerate;
        int requestHeight;
        int requestWidth;
        CameraCaptureSession session;
        int status;
        List<Surface> surfaces;
        HandlerThread thread;

        public CameraCacheInfo() {
        }
    }

    public class FrameInfo {
        long exposureTime;
        int iso;
        long timestamp;

        public FrameInfo() {
        }
    }

    private class OnCameraDataAvailable implements ImageReader.OnImageAvailableListener {
        private int[] actualBufferSize;
        private int actualCaptureFormat;
        FrameInfo info;
        private long prevTimestamp;

        private OnCameraDataAvailable() {
            this.actualCaptureFormat = Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            this.actualBufferSize = null;
            this.prevTimestamp = -1;
            this.info = new FrameInfo();
        }

        public void onImageAvailable(ImageReader imageReader) {
            Image acquireLatestImage;
            Trace.beginSection("onImageAvailable (java)");
            Integer num = (Integer) Camera2_Preview.this.mCameraCacheInfoIndexCache.get(imageReader);
            if (num == null) {
                Trace.endSection();
                return;
            }
            CameraCacheInfo cameraCacheInfo = (CameraCacheInfo) Camera2_Preview.this.mCameraCacheInfos.get(num.intValue());
            if (cameraCacheInfo == null) {
                Trace.endSection();
            } else if (!cameraCacheInfo.imageSemaphore.tryAcquire()) {
                DebugLog.LOGE(Camera2_Preview.MODULENAME, "Unable to aquire image semaphore, need to free some buffers!!");
                Trace.endSection();
            } else {
                if (imageReader.getMaxImages() > 0 && (acquireLatestImage = imageReader.acquireLatestImage()) != null) {
                    this.info.timestamp = acquireLatestImage.getTimestamp();
                    CaptureResult captureResult = cameraCacheInfo.lastResult;
                    if (captureResult != null) {
                        Long l = (Long) captureResult.get(CaptureResult.SENSOR_EXPOSURE_TIME);
                        if (l != null) {
                            this.info.exposureTime = l.longValue();
                            this.info.timestamp += l.longValue();
                        }
                        Integer num2 = (Integer) captureResult.get(CaptureResult.SENSOR_SENSITIVITY);
                        if (num2 != null) {
                            this.info.iso = num2.intValue();
                        }
                    }
                    if (this.info.timestamp > this.prevTimestamp) {
                        this.prevTimestamp = this.info.timestamp;
                        if (this.actualCaptureFormat == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN) {
                            this.actualCaptureFormat = getImageFormat(acquireLatestImage);
                        }
                        Camera2_Preview.this.newFrameAvailable(cameraCacheInfo.deviceHandle, num.intValue(), cameraCacheInfo.bufferWidth, cameraCacheInfo.bufferHeight, this.actualBufferSize, this.actualCaptureFormat, acquireLatestImage.getPlanes()[0].getBuffer(), this.info);
                    }
                    acquireLatestImage.close();
                }
                cameraCacheInfo.imageSemaphore.release();
                Trace.endSection();
            }
        }

        private int getImageFormat(Image image) {
            if (image == null || image.getPlanes().length != 3 || image.getFormat() != 35) {
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            }
            Image.Plane plane = image.getPlanes()[0];
            Image.Plane plane2 = image.getPlanes()[1];
            Image.Plane plane3 = image.getPlanes()[2];
            if (plane.getPixelStride() != 1 || plane2.getPixelStride() != plane3.getPixelStride() || plane2.getRowStride() != plane3.getRowStride()) {
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            }
            long[] jArr = {Camera2_Preview.this.getBufferAddress(plane.getBuffer()), Camera2_Preview.this.getBufferAddress(plane2.getBuffer()), Camera2_Preview.this.getBufferAddress(plane3.getBuffer())};
            if (jArr[0] == 0 || jArr[1] == 0 || jArr[2] == 0) {
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            }
            if (plane2.getPixelStride() == 2) {
                if (jArr[1] + 1 == jArr[2]) {
                    this.actualBufferSize = calculateActualBufferSize(jArr[0], jArr[1], jArr[2], plane.getRowStride(), plane2.getRowStride(), image.getWidth(), image.getHeight(), Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12);
                    return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12;
                } else if (jArr[2] + 1 == jArr[1]) {
                    this.actualBufferSize = calculateActualBufferSize(jArr[0], jArr[2], jArr[1], plane.getRowStride(), plane2.getRowStride(), image.getWidth(), image.getHeight(), Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21);
                    return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21;
                } else {
                    DebugLog.LOGE(Camera2_Preview.MODULENAME, "Detected an image with chroma pixel stride of two, but not NV12 or NV21. Unknown image format");
                }
            }
            if (plane2.getPixelStride() != 1) {
                DebugLog.LOGE(Camera2_Preview.MODULENAME, "Unable to detect a supported camera image format, Unknown image format");
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            } else if (jArr[1] > jArr[2]) {
                this.actualBufferSize = calculateActualBufferSize(jArr[0], jArr[2], jArr[1], plane.getRowStride(), plane2.getRowStride(), image.getWidth(), image.getHeight(), Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12);
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12;
            } else {
                this.actualBufferSize = calculateActualBufferSize(jArr[0], jArr[1], jArr[2], plane.getRowStride(), plane2.getRowStride(), image.getWidth(), image.getHeight(), Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P);
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P;
            }
        }

        private int[] calculateActualBufferSize(long j, long j2, long j3, int i, int i2, int i3, int i4, int i5) {
            boolean z;
            int i6 = i;
            int i7 = i2;
            int i8 = i5;
            if ((i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12 || i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21) && j + ((long) (i4 * i3)) != j2) {
                z = true;
            } else if ((i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12 || i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P) && !(j + ((long) (i4 * i3)) == j2 && j2 + ((long) ((i4 / 2) * (i3 / 2))) == j3)) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return null;
            }
            int[] iArr = new int[6];
            iArr[0] = i6;
            long j4 = j2 - j;
            iArr[1] = (int) (j4 / ((long) i6));
            iArr[4] = (int) (j4 - ((long) (iArr[1] * i6)));
            iArr[2] = i7;
            if (i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12 || i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21) {
                iArr[3] = i4 / 2;
                iArr[5] = 0;
                return iArr;
            } else if (i8 != Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12 && i8 != Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P) {
                return iArr;
            } else {
                long j5 = j3 - j2;
                iArr[3] = (int) (j5 / ((long) i7));
                iArr[5] = (int) (j5 - ((long) (iArr[3] * i7)));
                return iArr;
            }
        }
    }

    private class OnFrameCapturedCallback extends CameraCaptureSession.CaptureCallback {
        CameraCacheInfo mCCI;

        public OnFrameCapturedCallback(CameraCacheInfo cameraCacheInfo) {
            this.mCCI = cameraCacheInfo;
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            CameraCacheInfo cameraCacheInfo = this.mCCI;
            if (cameraCacheInfo != null) {
                cameraCacheInfo.lastResult = totalCaptureResult;
            }
        }
    }

    private class AutofocusRunner extends CameraCaptureSession.CaptureCallback {
        private CameraCacheInfo mCCI;
        private CaptureRequest mCancelRequest = null;
        private CaptureRequest mFocusRequest = null;

        public AutofocusRunner(CameraCacheInfo cameraCacheInfo) {
            this.mCCI = cameraCacheInfo;
        }

        public boolean triggerAutofocus() throws CameraAccessException {
            CameraCacheInfo cameraCacheInfo = this.mCCI;
            if (cameraCacheInfo == null || cameraCacheInfo.builder == null || this.mCCI.session == null) {
                return false;
            }
            Integer num = (Integer) this.mCCI.builder.get(CaptureRequest.CONTROL_AF_MODE);
            if (CaptureRequest.CONTROL_AF_MODE == null || num == null) {
                return false;
            }
            if (num.intValue() != 1 && num.intValue() != 2) {
                return false;
            }
            CameraCacheInfo cameraCacheInfo2 = this.mCCI;
            cameraCacheInfo2.isAutoFocusing = true;
            cameraCacheInfo2.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            this.mCancelRequest = this.mCCI.builder.build();
            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
            this.mFocusRequest = this.mCCI.builder.build();
            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
            this.mCCI.session.capture(this.mCancelRequest, this, this.mCCI.handler);
            return true;
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            super.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
            Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
            if (captureRequest.equals(this.mCancelRequest) && num.intValue() == 0) {
                try {
                    cameraCaptureSession.capture(this.mFocusRequest, this, this.mCCI.handler);
                } catch (CameraAccessException | IllegalArgumentException | IllegalStateException e) {
                }
            } else if (!captureRequest.equals(this.mFocusRequest)) {
            } else {
                if (num.intValue() == 4 || num.intValue() == 5) {
                    this.mCCI.isAutoFocusing = false;
                }
            }
        }
    }

    private boolean checkPermission() {
        if (this.mIsPermissionGranted == 0) {
            return true;
        }
        try {
            Activity activityFromNative = SystemTools.getActivityFromNative();
            this.mIsPermissionGranted = activityFromNative.getPackageManager().checkPermission("android.permission.CAMERA", activityFromNative.getPackageName());
            if (this.mIsPermissionGranted == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private int getCameraDeviceIndex(int i, int i2, int i3) {
        int i4;
        switch (i3) {
            case AR_CAMERA_DIRECTION_UNKNOWN /*268443664*/:
                i4 = -1;
                break;
            case AR_CAMERA_DIRECTION_BACK /*268443665*/:
                i4 = 1;
                break;
            case AR_CAMERA_DIRECTION_FRONT /*268443666*/:
                i4 = 0;
                break;
            default:
                SystemTools.setSystemErrorCode(2);
                return -1;
        }
        try {
            String[] cameraIdList = this.mCameraManager.getCameraIdList();
            for (int i5 = 0; i5 < cameraIdList.length; i5++) {
                CameraCharacteristics cameraCharacteristics = this.mCameraManager.getCameraCharacteristics(cameraIdList[i5]);
                if ((i4 < 0 || i4 == ((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue()) && (i < 0 || i == i5)) {
                    return i5;
                }
            }
        } catch (CameraAccessException | IllegalArgumentException e) {
        }
        SystemTools.setSystemErrorCode(6);
        return -1;
    }

    private CameraCacheInfo getCameraCacheInfo(int i) {
        if (i < 0 || i >= this.mCameraCacheInfos.size()) {
            return null;
        }
        return this.mCameraCacheInfos.get(i);
    }

    private boolean setCustomCameraParams(CameraCacheInfo cameraCacheInfo, String str) {
        if (cameraCacheInfo == null || cameraCacheInfo.builder == null || cameraCacheInfo.characteristics == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    Object obj = jSONObject.get(next);
                    Class<?> cls = obj.getClass();
                    if ((cls != String.class && cls != Integer.class) || mapStringToKey(next, cameraCacheInfo.characteristics, obj) == null) {
                        return false;
                    }
                    cameraCacheInfo.builder.set(mapStringToKey(next, cameraCacheInfo.characteristics, obj), obj);
                } catch (JSONException e) {
                    return false;
                }
            }
            return true;
        } catch (JSONException e2) {
            return false;
        }
    }

    private <T> CaptureRequest.Key<T> mapStringToKey(String str, CameraCharacteristics cameraCharacteristics, T t) {
        for (CaptureRequest.Key<T> next : cameraCharacteristics.getAvailableCaptureRequestKeys()) {
            if (next.getName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    private boolean setCameraCaptureParams(CameraCacheInfo cameraCacheInfo, int[] iArr, int[] iArr2) {
        int intValue;
        if (!(iArr == null && iArr2 == null)) {
            cameraCacheInfo.overrideWidth = iArr2 != null ? iArr2[0] : iArr[0];
            cameraCacheInfo.overrideHeight = iArr2 != null ? iArr2[1] : iArr[1];
            cameraCacheInfo.overrideFormatPL = iArr2 != null ? iArr2[2] : iArr[2];
            cameraCacheInfo.overrideFormatAndroid = translateImageFormatPLToAndroid(iArr2 != null ? iArr2[2] : iArr[2]);
        }
        if (iArr == null) {
            return true;
        }
        if (cameraCacheInfo == null || cameraCacheInfo.builder == null || cameraCacheInfo.characteristics == null) {
            return false;
        }
        cameraCacheInfo.requestWidth = iArr[0];
        cameraCacheInfo.requestHeight = iArr[1];
        cameraCacheInfo.requestFormatPL = iArr[2];
        cameraCacheInfo.requestFormatAndroid = translateImageFormatPLToAndroid(iArr[2]);
        cameraCacheInfo.requestFramerate = iArr[3];
        Size[] outputSizes = ((StreamConfigurationMap) cameraCacheInfo.characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(cameraCacheInfo.requestFormatAndroid);
        if (outputSizes == null) {
            return false;
        }
        boolean z = false;
        for (Size equals : outputSizes) {
            z = equals.equals(new Size(cameraCacheInfo.requestWidth, cameraCacheInfo.requestHeight));
            if (z) {
                break;
            }
        }
        if (!z) {
            return false;
        }
        Range[] rangeArr = (Range[]) cameraCacheInfo.characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        int length = rangeArr.length;
        Range range = null;
        int i = 0;
        int i2 = Integer.MAX_VALUE;
        while (true) {
            if (i >= length) {
                break;
            }
            Range range2 = rangeArr[i];
            if (((Integer) range2.getLower()).intValue() >= MAX_LOWEST_FPS_ALLOWED || ((Integer) range2.getUpper()).intValue() >= MAX_HIGHEST_FPS_ALLOWED) {
                DebugLog.LOGW(MODULENAME, String.format("Detected odd fps values from Camera2 API: low=%d, high=%d.  Using first fps range as default instead of searching for perfect fit.", new Object[]{range2.getLower(), range2.getUpper()}));
                range = rangeArr[0];
            } else {
                if (range2.contains(Integer.valueOf(cameraCacheInfo.requestFramerate)) && (intValue = ((Integer) range2.getUpper()).intValue() - ((Integer) range2.getLower()).intValue()) < i2) {
                    range = range2;
                    i2 = intValue;
                }
                i++;
            }
        }
        if (range == null) {
            return false;
        }
        cameraCacheInfo.builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, range);
        return true;
    }

    private boolean setupPreviewBuffer(CameraCacheInfo cameraCacheInfo) {
        cameraCacheInfo.reader = ImageReader.newInstance(cameraCacheInfo.requestWidth, cameraCacheInfo.requestHeight, cameraCacheInfo.requestFormatAndroid, 2);
        cameraCacheInfo.imageSemaphore = new Semaphore(2);
        cameraCacheInfo.images = new Image[2];
        cameraCacheInfo.bufferWidth = cameraCacheInfo.requestWidth == cameraCacheInfo.overrideWidth ? cameraCacheInfo.reader.getWidth() : cameraCacheInfo.overrideWidth;
        cameraCacheInfo.bufferHeight = cameraCacheInfo.requestHeight == cameraCacheInfo.overrideHeight ? cameraCacheInfo.reader.getHeight() : cameraCacheInfo.overrideHeight;
        if (cameraCacheInfo.requestFormatAndroid == cameraCacheInfo.overrideFormatAndroid) {
            cameraCacheInfo.reader.getImageFormat();
        } else {
            int i = cameraCacheInfo.overrideFormatAndroid;
        }
        cameraCacheInfo.bufferFormatPL = cameraCacheInfo.requestFormatPL == cameraCacheInfo.overrideFormatPL ? cameraCacheInfo.requestFormatPL : cameraCacheInfo.overrideFormatPL;
        cameraCacheInfo.reader.setOnImageAvailableListener(new OnCameraDataAvailable(), cameraCacheInfo.handler);
        if (cameraCacheInfo.surfaces == null) {
            cameraCacheInfo.surfaces = new LinkedList();
        }
        cameraCacheInfo.surfaces.clear();
        cameraCacheInfo.surfaces.add(cameraCacheInfo.reader.getSurface());
        return true;
    }

    private void setCameraCapsBit(CameraCacheInfo cameraCacheInfo, int i, int i2, boolean z) {
        int i3;
        if (i == 0 || i == 1) {
            i3 = AR_CAMERA_PARAMTYPE_BASE;
        } else if (i == 2) {
            i3 = AR_CAMERA_PARAMVALUE_BASE;
        } else {
            return;
        }
        int log = (int) (Math.log((double) (i2 & (i3 ^ -1))) / Math.log(2.0d));
        if (z) {
            int[] iArr = cameraCacheInfo.caps;
            iArr[i] = (1 << log) | iArr[i];
            return;
        }
        int[] iArr2 = cameraCacheInfo.caps;
        iArr2[i] = ((1 << log) ^ -1) & iArr2[i];
    }

    private int translateImageFormatPLToAndroid(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = CAMERA_VALID_IMAGE_FORMAT_PL;
            if (i2 >= iArr.length) {
                return 0;
            }
            if (i == iArr[i2]) {
                return 35;
            }
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    public int getBitsPerPixel(int i) {
        if (i == 4) {
            return 16;
        }
        if (i == 842094169) {
            return 12;
        }
        if (i == 16) {
            return 16;
        }
        if (i != 17) {
            return 0;
        }
        return 12;
    }

    private List<Integer> getSupportedPreviewFrameRates(CameraCharacteristics cameraCharacteristics) {
        Range[] rangeArr = (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (Range range : rangeArr) {
            i = Math.min(i, ((Integer) range.getLower()).intValue());
            i2 = Math.max(i2, ((Integer) range.getUpper()).intValue());
        }
        LinkedList linkedList = new LinkedList();
        if (i < 0 || i >= MAX_LOWEST_FPS_ALLOWED || i2 < 0 || i2 >= MAX_HIGHEST_FPS_ALLOWED) {
            DebugLog.LOGW(MODULENAME, String.format("Detected odd fps values from Camera2 API: low=%d, high=%d.  Using saner defaults instead.", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            linkedList.add(30);
        } else {
            while (i <= i2) {
                int length = rangeArr.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    } else if (rangeArr[i3].contains(Integer.valueOf(i))) {
                        linkedList.add(Integer.valueOf(i));
                        break;
                    } else {
                        i3++;
                    }
                }
                i++;
            }
        }
        return linkedList;
    }

    private boolean checkCameraManager() {
        Application application;
        if (this.mCameraManager != null) {
            return true;
        }
        Activity activityFromNative = SystemTools.getActivityFromNative();
        if (activityFromNative == null || (application = activityFromNative.getApplication()) == null) {
            return false;
        }
        this.mCameraManager = (CameraManager) application.getSystemService("camera");
        if (this.mCameraManager == null) {
            return false;
        }
        return true;
    }

    private static int compareHardwareSupportLevel(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        if (i == 2) {
            if (i2 >= 0) {
                return -1;
            }
            return 1;
        } else if (i2 != 2) {
            return i - i2;
        } else {
            if (i >= 0) {
                return 1;
            }
            return -1;
        }
    }

    private void cleanupHandlerThread(CameraCacheInfo cameraCacheInfo) {
        cameraCacheInfo.handler = null;
        cameraCacheInfo.thread.quitSafely();
        cameraCacheInfo.thread = null;
    }

    public boolean init() {
        this.mCameraCacheInfos = new Vector<>();
        this.mCameraCacheInfosInProgress = new Vector<>();
        this.mCameraCacheInfoIndexCache = new HashMap<>();
        return true;
    }

    public int getNumberOfCameras() {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (!checkCameraManager()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else {
            if (SystemTools.checkMinimumApiLevel(21)) {
                try {
                    return this.mCameraManager.getCameraIdList().length;
                } catch (Exception e) {
                }
            }
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
    }

    public int getOrientation(int i) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (!checkCameraManager()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else {
            if (SystemTools.checkMinimumApiLevel(21)) {
                try {
                    String[] cameraIdList = this.mCameraManager.getCameraIdList();
                    if (i < cameraIdList.length) {
                        return ((Integer) this.mCameraManager.getCameraCharacteristics(cameraIdList[i]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                    }
                } catch (Exception e) {
                }
            }
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
    }

    public int getDirection(int i) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (!checkCameraManager()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else {
            if (SystemTools.checkMinimumApiLevel(21)) {
                try {
                    String[] cameraIdList = this.mCameraManager.getCameraIdList();
                    if (i < cameraIdList.length) {
                        int intValue = ((Integer) this.mCameraManager.getCameraCharacteristics(cameraIdList[i]).get(CameraCharacteristics.LENS_FACING)).intValue();
                        if (intValue == 0) {
                            return AR_CAMERA_DIRECTION_FRONT;
                        }
                        if (intValue != 1) {
                            return AR_CAMERA_DIRECTION_UNKNOWN;
                        }
                        return AR_CAMERA_DIRECTION_BACK;
                    }
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                }
            }
            return AR_CAMERA_DIRECTION_BACK;
        }
    }

    public int getDeviceID(int i) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo != null) {
            return cameraCacheInfo.deviceID;
        }
        SystemTools.setSystemErrorCode(4);
        return -1;
    }

    public static boolean checkMinimumHardwareSupportLevel(int i, int i2) {
        int i3;
        if (i == AR_CAMERA_DIRECTION_BACK) {
            i3 = 1;
        } else if (i != AR_CAMERA_DIRECTION_FRONT) {
            return false;
        } else {
            i3 = 0;
        }
        try {
            CameraManager cameraManager = (CameraManager) SystemTools.getActivityFromNative().getSystemService("camera");
            String[] cameraIdList = cameraManager.getCameraIdList();
            int i4 = 0;
            while (i4 < cameraIdList.length) {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraIdList[i4]);
                if (((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() != i3) {
                    i4++;
                } else if (compareHardwareSupportLevel(((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue(), i2) >= 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    public int open(long j, int i, int i2, int i3, String str, int[] iArr, int[] iArr2) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (!checkCameraManager()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else {
            int cameraDeviceIndex = getCameraDeviceIndex(i, i2, i3);
            if (cameraDeviceIndex < 0) {
                return -1;
            }
            int size = this.mCameraCacheInfos.size();
            boolean z = false;
            CameraCacheInfo cameraCacheInfo = null;
            int i4 = 0;
            while (true) {
                if (i4 >= size) {
                    i4 = -1;
                    break;
                }
                cameraCacheInfo = this.mCameraCacheInfos.get(i4);
                if (cameraCacheInfo.deviceID == cameraDeviceIndex) {
                    break;
                }
                i4++;
            }
            if (i4 < 0) {
                try {
                    cameraCacheInfo = new CameraCacheInfo();
                    cameraCacheInfo.deviceID = cameraDeviceIndex;
                    cameraCacheInfo.deviceHandle = j;
                    cameraCacheInfo.deviceIDString = this.mCameraManager.getCameraIdList()[cameraCacheInfo.deviceID];
                    cameraCacheInfo.characteristics = this.mCameraManager.getCameraCharacteristics(cameraCacheInfo.deviceIDString);
                    cameraCacheInfo.device = null;
                    cameraCacheInfo.session = null;
                    cameraCacheInfo.builder = null;
                    cameraCacheInfo.surfaces = null;
                    cameraCacheInfo.reader = null;
                    cameraCacheInfo.images = null;
                    cameraCacheInfo.imageSemaphore = null;
                    cameraCacheInfo.overrideWidth = 0;
                    cameraCacheInfo.bufferWidth = 0;
                    cameraCacheInfo.overrideHeight = 0;
                    cameraCacheInfo.bufferHeight = 0;
                    cameraCacheInfo.bufferFormatPL = AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
                    cameraCacheInfo.overrideFormatPL = AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
                    cameraCacheInfo.overrideFormatAndroid = 0;
                    cameraCacheInfo.caps = null;
                    cameraCacheInfo.status = AR_CAMERA_STATUS_UNINITIALIZED;
                    cameraCacheInfo.isAutoFocusing = false;
                    cameraCacheInfo.requestFormatPL = AR_CAMERA_IMAGE_FORMAT_NV21;
                    cameraCacheInfo.requestFormatAndroid = 35;
                    Size[] outputSizes = ((StreamConfigurationMap) cameraCacheInfo.characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(cameraCacheInfo.requestFormatAndroid);
                    cameraCacheInfo.requestWidth = (outputSizes == null || outputSizes.length <= 0) ? 0 : outputSizes[0].getWidth();
                    cameraCacheInfo.requestHeight = (outputSizes == null || outputSizes.length <= 0) ? 0 : outputSizes[0].getHeight();
                } catch (CameraAccessException e) {
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                } catch (IllegalArgumentException e2) {
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                }
            }
            int i5 = NUM_MAX_CAMERAOPEN_RETRY;
            cameraCacheInfo.thread = new HandlerThread(cameraCacheInfo.deviceIDString + "_camera_thread");
            cameraCacheInfo.thread.start();
            cameraCacheInfo.handler = new Handler(cameraCacheInfo.thread.getLooper());
            boolean z2 = false;
            while (true) {
                try {
                    this.mOpenCloseSemaphore.acquire();
                    this.mCameraCacheInfosInProgress.add(cameraCacheInfo);
                    this.mCameraManager.openCamera(cameraCacheInfo.deviceIDString, new CameraDevice.StateCallback() {
                        public void onOpened(CameraDevice cameraDevice) {
                            CameraCacheInfo cameraCacheInfo;
                            try {
                                Iterator it = Camera2_Preview.this.mCameraCacheInfosInProgress.iterator();
                                cameraCacheInfo = null;
                                while (it.hasNext()) {
                                    try {
                                        CameraCacheInfo cameraCacheInfo2 = (CameraCacheInfo) it.next();
                                        if (cameraCacheInfo2.deviceIDString.equals(cameraDevice.getId())) {
                                            try {
                                                cameraCacheInfo2.device = cameraDevice;
                                                cameraCacheInfo2.builder = cameraDevice.createCaptureRequest(1);
                                                cameraCacheInfo = cameraCacheInfo2;
                                            } catch (CameraAccessException e) {
                                                cameraCacheInfo = cameraCacheInfo2;
                                                cameraCacheInfo.builder = null;
                                                cameraCacheInfo.device = null;
                                                Camera2_Preview.this.mOpenCloseSemaphore.release();
                                            } catch (IllegalArgumentException e2) {
                                                cameraCacheInfo = cameraCacheInfo2;
                                                cameraCacheInfo.builder = null;
                                                cameraCacheInfo.device = null;
                                                Camera2_Preview.this.mOpenCloseSemaphore.release();
                                            } catch (IllegalStateException e3) {
                                                cameraCacheInfo = cameraCacheInfo2;
                                                cameraCacheInfo.builder = null;
                                                cameraCacheInfo.device = null;
                                                Camera2_Preview.this.mOpenCloseSemaphore.release();
                                            }
                                        }
                                    } catch (CameraAccessException e4) {
                                        cameraCacheInfo.builder = null;
                                        cameraCacheInfo.device = null;
                                        Camera2_Preview.this.mOpenCloseSemaphore.release();
                                    } catch (IllegalArgumentException e5) {
                                        cameraCacheInfo.builder = null;
                                        cameraCacheInfo.device = null;
                                        Camera2_Preview.this.mOpenCloseSemaphore.release();
                                    } catch (IllegalStateException e6) {
                                        cameraCacheInfo.builder = null;
                                        cameraCacheInfo.device = null;
                                        Camera2_Preview.this.mOpenCloseSemaphore.release();
                                    }
                                }
                            } catch (CameraAccessException e7) {
                                cameraCacheInfo = null;
                                cameraCacheInfo.builder = null;
                                cameraCacheInfo.device = null;
                                Camera2_Preview.this.mOpenCloseSemaphore.release();
                            } catch (IllegalArgumentException e8) {
                                cameraCacheInfo = null;
                                cameraCacheInfo.builder = null;
                                cameraCacheInfo.device = null;
                                Camera2_Preview.this.mOpenCloseSemaphore.release();
                            } catch (IllegalStateException e9) {
                                cameraCacheInfo = null;
                                cameraCacheInfo.builder = null;
                                cameraCacheInfo.device = null;
                                Camera2_Preview.this.mOpenCloseSemaphore.release();
                            } catch (Throwable th) {
                                Camera2_Preview.this.mOpenCloseSemaphore.release();
                                throw th;
                            }
                            Camera2_Preview.this.mOpenCloseSemaphore.release();
                        }

                        public void onError(CameraDevice cameraDevice, int i) {
                            cameraDevice.close();
                            Camera2_Preview.this.mOpenCloseSemaphore.release();
                        }

                        public void onDisconnected(CameraDevice cameraDevice) {
                            cameraDevice.close();
                            Camera2_Preview.this.mOpenCloseSemaphore.release();
                        }
                    }, cameraCacheInfo.handler);
                    this.mOpenCloseSemaphore.acquire();
                    this.mCameraCacheInfosInProgress.remove(cameraCacheInfo);
                    this.mOpenCloseSemaphore.release();
                    z2 = (cameraCacheInfo.device == null || cameraCacheInfo.builder == null) ? false : true;
                } catch (Exception e3) {
                }
                if (!z2 && i5 > 0) {
                    try {
                        synchronized (this) {
                            wait(250);
                        }
                    } catch (Exception e4) {
                    }
                }
                if (z2) {
                    break;
                }
                int i6 = i5 - 1;
                if (i5 <= 0) {
                    break;
                }
                i5 = i6;
            }
            if (cameraCacheInfo.device == null || cameraCacheInfo.builder == null) {
                SystemTools.setSystemErrorCode(6);
                cleanupHandlerThread(cameraCacheInfo);
                return -1;
            }
            boolean z3 = (iArr != null && iArr.length > 0) || (iArr2 != null && iArr2.length > 0);
            if (str != null && str.length() > 0) {
                z = true;
            }
            if (z3 || z) {
                if (z3) {
                    if (iArr != null && iArr.length != 5) {
                        SystemTools.setSystemErrorCode(2);
                        cleanupHandlerThread(cameraCacheInfo);
                        return -1;
                    } else if (!setCameraCaptureParams(cameraCacheInfo, iArr, iArr2)) {
                        SystemTools.setSystemErrorCode(6);
                        cleanupHandlerThread(cameraCacheInfo);
                        return -1;
                    }
                }
                if (z && !setCustomCameraParams(cameraCacheInfo, str)) {
                    SystemTools.setSystemErrorCode(2);
                    cleanupHandlerThread(cameraCacheInfo);
                    return -1;
                }
            }
            cameraCacheInfo.status = AR_CAMERA_STATUS_OPENED;
            if (i4 >= 0) {
                return i4;
            }
            this.mCameraCacheInfos.add(cameraCacheInfo);
            return this.mCameraCacheInfos.size() - 1;
        }
    }

    public boolean close(int i) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        boolean z = false;
        if (cameraCacheInfo == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        this.mCameraCacheInfoIndexCache.remove(cameraCacheInfo.reader);
        try {
            if (cameraCacheInfo.session != null) {
                cameraCacheInfo.session.close();
            }
            if (cameraCacheInfo.device != null) {
                cameraCacheInfo.device.close();
            }
            if (cameraCacheInfo.reader != null) {
                cameraCacheInfo.reader.close();
            }
            z = true;
        } catch (Exception e) {
        }
        cameraCacheInfo.session = null;
        cameraCacheInfo.reader = null;
        cameraCacheInfo.images = null;
        cameraCacheInfo.status = AR_CAMERA_STATUS_UNINITIALIZED;
        cleanupHandlerThread(cameraCacheInfo);
        System.gc();
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0108, code lost:
        if (r5.length <= 1) goto L_0x010c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x025a  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02be  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x02c2  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x02e2 A[LOOP:2: B:140:0x02e0->B:141:0x02e2, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01f3  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int[] getCameraCapabilities(int r21) {
        /*
            r20 = this;
            r1 = r20
            boolean r0 = r20.checkCameraManager()
            r2 = 0
            r3 = 6
            if (r0 != 0) goto L_0x000e
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3)
            return r2
        L_0x000e:
            com.vuforia.ar.pl.Camera2_Preview$CameraCacheInfo r0 = r20.getCameraCacheInfo(r21)
            r4 = 4
            if (r0 != 0) goto L_0x0019
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4)
            return r2
        L_0x0019:
            int[] r5 = r0.caps
            if (r5 == 0) goto L_0x0020
            int[] r0 = r0.caps
            return r0
        L_0x0020:
            android.hardware.camera2.CameraManager r5 = r1.mCameraManager     // Catch:{ CameraAccessException -> 0x02ff, IllegalArgumentException -> 0x02f9 }
            java.lang.String[] r5 = r5.getCameraIdList()     // Catch:{ CameraAccessException -> 0x02ff, IllegalArgumentException -> 0x02f9 }
            int r6 = r0.deviceID     // Catch:{ CameraAccessException -> 0x02ff, IllegalArgumentException -> 0x02f9 }
            r5 = r5[r6]     // Catch:{ CameraAccessException -> 0x02ff, IllegalArgumentException -> 0x02f9 }
            android.hardware.camera2.CameraManager r6 = r1.mCameraManager     // Catch:{ CameraAccessException -> 0x02ff, IllegalArgumentException -> 0x02f9 }
            android.hardware.camera2.CameraCharacteristics r5 = r6.getCameraCharacteristics(r5)     // Catch:{ CameraAccessException -> 0x02ff, IllegalArgumentException -> 0x02f9 }
            android.hardware.camera2.CameraCharacteristics$Key r6 = android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP     // Catch:{ CameraAccessException -> 0x02ff, IllegalArgumentException -> 0x02f9 }
            java.lang.Object r5 = r5.get(r6)     // Catch:{ CameraAccessException -> 0x02ff, IllegalArgumentException -> 0x02f9 }
            android.hardware.camera2.params.StreamConfigurationMap r5 = (android.hardware.camera2.params.StreamConfigurationMap) r5     // Catch:{ CameraAccessException -> 0x02ff, IllegalArgumentException -> 0x02f9 }
            r2 = 35
            android.util.Size[] r2 = r5.getOutputSizes(r2)
            android.hardware.camera2.CameraCharacteristics r5 = r0.characteristics
            java.util.List r5 = r1.getSupportedPreviewFrameRates(r5)
            android.hardware.camera2.CameraCharacteristics r6 = r0.characteristics
            android.hardware.camera2.CameraCharacteristics$Key r7 = android.hardware.camera2.CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES
            java.lang.Object r6 = r6.get(r7)
            int[] r6 = (int[]) r6
            java.util.Arrays.sort(r6)
            android.hardware.camera2.CameraCharacteristics r7 = r0.characteristics
            android.hardware.camera2.CameraCharacteristics$Key r8 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES
            java.lang.Object r7 = r7.get(r8)
            int[] r7 = (int[]) r7
            java.util.Arrays.sort(r7)
            r8 = 0
            if (r2 == 0) goto L_0x0064
            int r9 = r2.length
            goto L_0x0065
        L_0x0064:
            r9 = 0
        L_0x0065:
            if (r5 == 0) goto L_0x006c
            int r10 = r5.size()
            goto L_0x006d
        L_0x006c:
            r10 = 0
        L_0x006d:
            int[] r11 = CAMERA_VALID_IMAGE_FORMAT_PL
            int r11 = r11.length
            android.hardware.camera2.CameraCharacteristics r12 = r0.characteristics
            android.hardware.camera2.CameraCharacteristics$Key r13 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS
            java.lang.Object r12 = r12.get(r13)
            float[] r12 = (float[]) r12
            if (r12 == 0) goto L_0x0081
            int r12 = r12.length
            if (r12 <= 0) goto L_0x0081
            r12 = 1
            goto L_0x0082
        L_0x0081:
            r12 = 0
        L_0x0082:
            android.hardware.camera2.CameraCharacteristics r14 = r0.characteristics
            android.hardware.camera2.CameraCharacteristics$Key r15 = android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE
            java.lang.Object r14 = r14.get(r15)
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            if (r14 != 0) goto L_0x0092
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r8)
        L_0x0092:
            android.hardware.camera2.CameraCharacteristics r15 = r0.characteristics
            android.hardware.camera2.CameraCharacteristics$Key r4 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF
            java.lang.Object r4 = r15.get(r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            if (r4 == 0) goto L_0x00a6
            int r4 = r4.intValue()
            if (r4 <= 0) goto L_0x00a6
            r4 = 1
            goto L_0x00a7
        L_0x00a6:
            r4 = 0
        L_0x00a7:
            android.hardware.camera2.CameraCharacteristics r15 = r0.characteristics
            android.hardware.camera2.CameraCharacteristics$Key r8 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE
            java.lang.Object r8 = r15.get(r8)
            android.util.Range r8 = (android.util.Range) r8
            if (r8 == 0) goto L_0x00bd
            android.util.Range<java.lang.Integer> r15 = EMPTY_RANGE
            boolean r8 = r15.equals(r8)
            if (r8 != 0) goto L_0x00bd
            r8 = 1
            goto L_0x00be
        L_0x00bd:
            r8 = 0
        L_0x00be:
            android.hardware.camera2.CameraCharacteristics r15 = r0.characteristics
            android.hardware.camera2.CameraCharacteristics$Key r3 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE
            java.lang.Object r3 = r15.get(r3)
            android.util.Range r3 = (android.util.Range) r3
            if (r3 == 0) goto L_0x00d4
            android.util.Range<java.lang.Integer> r15 = EMPTY_RANGE
            boolean r15 = r15.equals(r3)
            if (r15 != 0) goto L_0x00d4
            r15 = 1
            goto L_0x00d5
        L_0x00d4:
            r15 = 0
        L_0x00d5:
            android.hardware.camera2.CameraCharacteristics r13 = r0.characteristics
            r17 = r5
            android.hardware.camera2.CameraCharacteristics$Key r5 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE
            java.lang.Object r5 = r13.get(r5)
            android.util.Range r5 = (android.util.Range) r5
            if (r3 == 0) goto L_0x00ed
            android.util.Range<java.lang.Integer> r3 = EMPTY_RANGE
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L_0x00ed
            r3 = 1
            goto L_0x00ee
        L_0x00ed:
            r3 = 0
        L_0x00ee:
            android.hardware.camera2.CameraCharacteristics r5 = r0.characteristics
            android.hardware.camera2.CameraCharacteristics$Key r13 = android.hardware.camera2.CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES
            java.lang.Object r5 = r5.get(r13)
            int[] r5 = (int[]) r5
            android.hardware.camera2.CameraCharacteristics r13 = r0.characteristics
            r18 = r2
            android.hardware.camera2.CameraCharacteristics$Key r2 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION
            java.lang.Object r2 = r13.get(r2)
            int[] r2 = (int[]) r2
            if (r5 == 0) goto L_0x010b
            int r5 = r5.length
            r13 = 1
            if (r5 > r13) goto L_0x0111
            goto L_0x010c
        L_0x010b:
            r13 = 1
        L_0x010c:
            if (r2 == 0) goto L_0x0113
            int r2 = r2.length
            if (r2 <= r13) goto L_0x0113
        L_0x0111:
            r2 = 1
            goto L_0x0114
        L_0x0113:
            r2 = 0
        L_0x0114:
            int r5 = r9 * 2
            r13 = 6
            int r5 = r5 + r13
            int r5 = r5 + r10
            int r5 = r5 + r11
            int[] r5 = new int[r5]
            r0.caps = r5
            int[] r5 = r0.caps
            r13 = 536870912(0x20000000, float:1.0842022E-19)
            r19 = r11
            r11 = 0
            r5[r11] = r13
            boolean r5 = r14.booleanValue()
            r13 = 536870913(0x20000001, float:1.0842023E-19)
            r1.setCameraCapsBit(r0, r11, r13, r5)
            int r5 = r6.length
            if (r5 <= 0) goto L_0x0137
            r5 = 1
            goto L_0x0138
        L_0x0137:
            r5 = 0
        L_0x0138:
            r13 = 536870914(0x20000002, float:1.0842024E-19)
            r1.setCameraCapsBit(r0, r11, r13, r5)
            r5 = 536870916(0x20000004, float:1.0842027E-19)
            r1.setCameraCapsBit(r0, r11, r5, r12)
            r5 = 536870928(0x20000010, float:1.0842042E-19)
            r1.setCameraCapsBit(r0, r11, r5, r4)
            int r5 = r7.length
            if (r5 <= 0) goto L_0x014f
            r5 = 1
            goto L_0x0150
        L_0x014f:
            r5 = 0
        L_0x0150:
            r13 = 536870944(0x20000020, float:1.0842063E-19)
            r1.setCameraCapsBit(r0, r11, r13, r5)
            r5 = 536871936(0x20000400, float:1.0843345E-19)
            r1.setCameraCapsBit(r0, r11, r5, r8)
            r5 = 536872960(0x20000800, float:1.0844669E-19)
            r1.setCameraCapsBit(r0, r11, r5, r8)
            r5 = 536870976(0x20000040, float:1.0842104E-19)
            r1.setCameraCapsBit(r0, r11, r5, r15)
            r5 = 536871040(0x20000080, float:1.0842187E-19)
            r1.setCameraCapsBit(r0, r11, r5, r15)
            r5 = 536871168(0x20000100, float:1.0842353E-19)
            r1.setCameraCapsBit(r0, r11, r5, r3)
            r5 = 536871424(0x20000200, float:1.0842683E-19)
            r1.setCameraCapsBit(r0, r11, r5, r3)
            r5 = 536903680(0x20008000, float:1.0884373E-19)
            r1.setCameraCapsBit(r0, r11, r5, r12)
            r5 = 536936448(0x20010000, float:1.0926725E-19)
            r1.setCameraCapsBit(r0, r11, r5, r12)
            r5 = 553648128(0x21000000, float:4.3368087E-19)
            r1.setCameraCapsBit(r0, r11, r5, r2)
            int[] r5 = r0.caps
            r11 = 536870912(0x20000000, float:1.0842022E-19)
            r13 = 1
            r5[r13] = r11
            boolean r5 = r14.booleanValue()
            r11 = 536870913(0x20000001, float:1.0842023E-19)
            r1.setCameraCapsBit(r0, r13, r11, r5)
            int r5 = r6.length
            if (r5 <= 0) goto L_0x01a1
            r5 = 1
            goto L_0x01a2
        L_0x01a1:
            r5 = 0
        L_0x01a2:
            r11 = 536870914(0x20000002, float:1.0842024E-19)
            r1.setCameraCapsBit(r0, r13, r11, r5)
            r5 = 536870928(0x20000010, float:1.0842042E-19)
            r1.setCameraCapsBit(r0, r13, r5, r4)
            int r4 = r7.length
            if (r4 <= 0) goto L_0x01b3
            r4 = 1
            goto L_0x01b4
        L_0x01b3:
            r4 = 0
        L_0x01b4:
            r5 = 536870944(0x20000020, float:1.0842063E-19)
            r1.setCameraCapsBit(r0, r13, r5, r4)
            r4 = 536871936(0x20000400, float:1.0843345E-19)
            r1.setCameraCapsBit(r0, r13, r4, r8)
            r4 = 536870976(0x20000040, float:1.0842104E-19)
            r1.setCameraCapsBit(r0, r13, r4, r15)
            r4 = 536871168(0x20000100, float:1.0842353E-19)
            r1.setCameraCapsBit(r0, r13, r4, r3)
            r3 = 536903680(0x20008000, float:1.0884373E-19)
            r1.setCameraCapsBit(r0, r13, r3, r12)
            r3 = 553648128(0x21000000, float:4.3368087E-19)
            r1.setCameraCapsBit(r0, r13, r3, r2)
            int[] r2 = r0.caps
            r3 = 805306368(0x30000000, float:4.656613E-10)
            r4 = 2
            r2[r4] = r3
            boolean r2 = r14.booleanValue()
            if (r2 == 0) goto L_0x01f3
            r2 = 805306369(0x30000001, float:4.6566134E-10)
            r3 = 1
            r1.setCameraCapsBit(r0, r4, r2, r3)
            r2 = 805306370(0x30000002, float:4.656614E-10)
            r1.setCameraCapsBit(r0, r4, r2, r3)
            goto L_0x01f4
        L_0x01f3:
            r3 = 1
        L_0x01f4:
            r2 = -1
            if (r6 == 0) goto L_0x0258
            r5 = 805306384(0x30000010, float:4.656622E-10)
            int r8 = java.util.Arrays.binarySearch(r6, r3)
            if (r8 == r2) goto L_0x0202
            r8 = 1
            goto L_0x0203
        L_0x0202:
            r8 = 0
        L_0x0203:
            r1.setCameraCapsBit(r0, r4, r5, r8)
            r5 = 805306400(0x30000020, float:4.6566306E-10)
            int r8 = java.util.Arrays.binarySearch(r6, r3)
            if (r8 == r2) goto L_0x0211
            r3 = 1
            goto L_0x0212
        L_0x0211:
            r3 = 0
        L_0x0212:
            r1.setCameraCapsBit(r0, r4, r5, r3)
            r3 = 805306432(0x30000040, float:4.6566484E-10)
            r5 = 3
            int r5 = java.util.Arrays.binarySearch(r6, r5)
            if (r5 == r2) goto L_0x0221
            r5 = 1
            goto L_0x0222
        L_0x0221:
            r5 = 0
        L_0x0222:
            r1.setCameraCapsBit(r0, r4, r3, r5)
            r3 = 805306496(0x30000080, float:4.656684E-10)
            int r5 = java.util.Arrays.binarySearch(r6, r4)
            if (r5 == r2) goto L_0x0230
            r5 = 1
            goto L_0x0231
        L_0x0230:
            r5 = 0
        L_0x0231:
            r1.setCameraCapsBit(r0, r4, r3, r5)
            r3 = 805306624(0x30000100, float:4.656755E-10)
            r5 = 0
            int r8 = java.util.Arrays.binarySearch(r6, r5)
            if (r8 == r2) goto L_0x0244
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE
            if (r5 == 0) goto L_0x0244
            r5 = 1
            goto L_0x0245
        L_0x0244:
            r5 = 0
        L_0x0245:
            r1.setCameraCapsBit(r0, r4, r3, r5)
            r3 = 805306880(0x30000200, float:4.656897E-10)
            r5 = 0
            int r6 = java.util.Arrays.binarySearch(r6, r5)
            if (r6 == r2) goto L_0x0254
            r5 = 1
            goto L_0x0255
        L_0x0254:
            r5 = 0
        L_0x0255:
            r1.setCameraCapsBit(r0, r4, r3, r5)
        L_0x0258:
            if (r7 == 0) goto L_0x028a
            r3 = 805310464(0x30001000, float:4.6588866E-10)
            r5 = 0
            int r6 = java.util.Arrays.binarySearch(r7, r5)
            if (r6 == r2) goto L_0x0266
            r6 = 1
            goto L_0x0267
        L_0x0266:
            r6 = 0
        L_0x0267:
            r1.setCameraCapsBit(r0, r4, r3, r6)
            r3 = 805339136(0x30008000, float:4.674803E-10)
            int r6 = java.util.Arrays.binarySearch(r7, r5)
            if (r6 == r2) goto L_0x0275
            r6 = 1
            goto L_0x0276
        L_0x0275:
            r6 = 0
        L_0x0276:
            r1.setCameraCapsBit(r0, r4, r3, r6)
            r3 = 805322752(0x30004000, float:4.665708E-10)
            r6 = 1
            int r7 = java.util.Arrays.binarySearch(r7, r6)
            if (r7 == r2) goto L_0x0285
            r2 = 1
            goto L_0x0286
        L_0x0285:
            r2 = 0
        L_0x0286:
            r1.setCameraCapsBit(r0, r4, r3, r2)
            goto L_0x028b
        L_0x028a:
            r5 = 0
        L_0x028b:
            int[] r2 = r0.caps
            r3 = 3
            r2[r3] = r9
            int[] r2 = r0.caps
            r3 = 4
            r2[r3] = r10
            int[] r2 = r0.caps
            r3 = 5
            r2[r3] = r19
            if (r9 <= 0) goto L_0x02be
            r2 = r18
            int r3 = r2.length
            r4 = 0
            r16 = 6
        L_0x02a3:
            if (r4 >= r3) goto L_0x02c0
            r6 = r2[r4]
            int[] r7 = r0.caps
            int r8 = r6.getWidth()
            r7[r16] = r8
            int[] r7 = r0.caps
            int r8 = r16 + 1
            int r6 = r6.getHeight()
            r7[r8] = r6
            int r16 = r16 + 2
            int r4 = r4 + 1
            goto L_0x02a3
        L_0x02be:
            r16 = 6
        L_0x02c0:
            if (r10 <= 0) goto L_0x02dd
            java.util.Iterator r2 = r17.iterator()
        L_0x02c6:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x02dd
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int[] r4 = r0.caps
            int r3 = r3.intValue()
            r4[r16] = r3
            int r16 = r16 + 1
            goto L_0x02c6
        L_0x02dd:
            int[] r2 = CAMERA_VALID_IMAGE_FORMAT_PL
            int r3 = r2.length
        L_0x02e0:
            if (r5 >= r3) goto L_0x02f6
            r4 = r2[r5]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            int[] r6 = r0.caps
            int r4 = r4.intValue()
            r6[r16] = r4
            r4 = 1
            int r16 = r16 + 1
            int r5 = r5 + 1
            goto L_0x02e0
        L_0x02f6:
            int[] r0 = r0.caps
            return r0
        L_0x02f9:
            r0 = move-exception
            r3 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3)
            return r2
        L_0x02ff:
            r0 = move-exception
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.getCameraCapabilities(int):int[]");
    }

    public boolean setCaptureInfo(int i, int[] iArr, int[] iArr2) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (iArr.length != 5) {
            SystemTools.setSystemErrorCode(2);
            return false;
        } else if (setCameraCaptureParams(cameraCacheInfo, iArr, iArr2)) {
            return true;
        } else {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    public int[] getCaptureInfo(int i) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        try {
            int[] iArr = new int[5];
            if (cameraCacheInfo.reader != null) {
                iArr[0] = cameraCacheInfo.reader.getWidth();
                iArr[1] = cameraCacheInfo.reader.getHeight();
            } else {
                iArr[0] = cameraCacheInfo.requestWidth;
                iArr[1] = cameraCacheInfo.requestHeight;
            }
            iArr[2] = cameraCacheInfo.requestFormatPL;
            if (cameraCacheInfo.builder != null) {
                iArr[3] = ((Integer) ((Range) cameraCacheInfo.builder.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE)).getUpper()).intValue();
            } else {
                iArr[3] = cameraCacheInfo.requestFramerate;
            }
            iArr[4] = 1;
            return iArr;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
    }

    public boolean start(int i) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (!setupPreviewBuffer(cameraCacheInfo)) {
            SystemTools.setSystemErrorCode(6);
            return false;
        } else {
            try {
                if (cameraCacheInfo.session == null) {
                    this.mOpenCloseSemaphore.acquire();
                    cameraCacheInfo.device.createCaptureSession(cameraCacheInfo.surfaces, new CameraCaptureSession.StateCallback() {
                        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                            CameraCacheInfo cameraCacheInfo;
                            Iterator it = Camera2_Preview.this.mCameraCacheInfos.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    cameraCacheInfo = null;
                                    break;
                                }
                                cameraCacheInfo = (CameraCacheInfo) it.next();
                                if (cameraCacheInfo.deviceIDString.equals(cameraCaptureSession.getDevice().getId())) {
                                    break;
                                }
                            }
                            cameraCacheInfo.session = cameraCaptureSession;
                            for (Surface addTarget : cameraCacheInfo.surfaces) {
                                cameraCacheInfo.builder.addTarget(addTarget);
                            }
                            Camera2_Preview.this.mOpenCloseSemaphore.release();
                        }

                        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                            cameraCaptureSession.close();
                            Camera2_Preview.this.mOpenCloseSemaphore.release();
                        }
                    }, cameraCacheInfo.handler);
                    this.mOpenCloseSemaphore.acquire();
                    this.mOpenCloseSemaphore.release();
                    if (cameraCacheInfo.session == null) {
                        SystemTools.setSystemErrorCode(6);
                        return false;
                    }
                }
                cameraCacheInfo.session.setRepeatingRequest(cameraCacheInfo.builder.build(), new OnFrameCapturedCallback(cameraCacheInfo), cameraCacheInfo.handler);
                cameraCacheInfo.status = AR_CAMERA_STATUS_CAPTURE_RUNNING;
                this.mCameraCacheInfoIndexCache.put(cameraCacheInfo.reader, Integer.valueOf(i));
                return true;
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                return false;
            }
        }
    }

    public boolean stop(int i) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        try {
            cameraCacheInfo.session.abortCaptures();
            cameraCacheInfo.status = AR_CAMERA_STATUS_OPENED;
            return true;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    public boolean setBatchParameters(int i, String str) {
        if (str == null) {
            return false;
        }
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null || cameraCacheInfo.builder == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (!setCustomCameraParams(cameraCacheInfo, str)) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean setUntypedCameraParameter(int i, String str, Integer num) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null || cameraCacheInfo.builder == null || cameraCacheInfo.characteristics == null || str == null || num == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        List<CaptureRequest.Key<?>> availableCaptureRequestKeys = cameraCacheInfo.characteristics.getAvailableCaptureRequestKeys();
        int i2 = 0;
        while (i2 < availableCaptureRequestKeys.size()) {
            CaptureRequest.Key key = availableCaptureRequestKeys.get(i2);
            if (!key.getName().equals(str)) {
                i2++;
            } else {
                Object obj = cameraCacheInfo.builder.get(key);
                boolean z = obj instanceof Integer;
                if (!z && !(obj instanceof Float) && !(obj instanceof Boolean) && !(obj instanceof Byte) && !(obj instanceof Long)) {
                    return false;
                }
                if ((obj instanceof Byte) && (num instanceof Long)) {
                    num = new Byte(((Long) num).byteValue());
                }
                if (z && (num instanceof Long)) {
                    num = new Integer(((Long) num).intValue());
                }
                if (!obj.getClass().equals(num.getClass())) {
                    return false;
                }
                try {
                    cameraCacheInfo.builder.set(key, num);
                    if (cameraCacheInfo.session == null) {
                        return true;
                    }
                    cameraCacheInfo.session.setRepeatingRequest(cameraCacheInfo.builder.build(), new OnFrameCapturedCallback(cameraCacheInfo), cameraCacheInfo.handler);
                    return true;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
            }
        }
        SystemTools.setSystemErrorCode(6);
        return false;
    }

    /* access modifiers changed from: package-private */
    public Object getUntypedCameraParameter(int i, String str) {
        Object obj;
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null || cameraCacheInfo.builder == null || cameraCacheInfo.characteristics == null || str == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        List<CaptureRequest.Key<?>> availableCaptureRequestKeys = cameraCacheInfo.characteristics.getAvailableCaptureRequestKeys();
        int i2 = 0;
        while (true) {
            if (i2 >= availableCaptureRequestKeys.size()) {
                obj = null;
                break;
            }
            CaptureRequest.Key key = availableCaptureRequestKeys.get(i2);
            if (key.getName().equals(str)) {
                obj = cameraCacheInfo.builder.get(key);
                break;
            }
            i2++;
        }
        List<CameraCharacteristics.Key<?>> keys = cameraCacheInfo.characteristics.getKeys();
        int i3 = 0;
        while (true) {
            if (i3 >= keys.size()) {
                break;
            }
            CameraCharacteristics.Key key2 = keys.get(i3);
            if (key2.getName().equals(str)) {
                obj = cameraCacheInfo.characteristics.get(key2);
                break;
            }
            i3++;
        }
        if (obj == null) {
            SystemTools.setSystemErrorCode(6);
            return null;
        } else if ((obj instanceof Long) || (obj instanceof Float) || (obj instanceof Boolean) || (obj instanceof String)) {
            return obj;
        } else {
            if (obj instanceof Integer) {
                return new Long(((Integer) obj).longValue());
            }
            if (obj instanceof Byte) {
                return new Long(((Byte) obj).longValue());
            }
            if (obj instanceof Range) {
                Range range = (Range) obj;
                Comparable lower = range.getLower();
                Comparable upper = range.getUpper();
                if (lower instanceof Integer) {
                    return new long[]{((Integer) lower).longValue(), ((Integer) upper).longValue()};
                } else if (lower instanceof Long) {
                    return new long[]{((Long) lower).longValue(), ((Long) upper).longValue()};
                } else {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            } else {
                SystemTools.setSystemErrorCode(6);
                return null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getUntypedCameraParameterType(int i, String str) {
        boolean z;
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null || cameraCacheInfo.builder == null || cameraCacheInfo.characteristics == null || str == null) {
            SystemTools.setSystemErrorCode(4);
            return -1;
        }
        Object obj = null;
        List<CaptureRequest.Key<?>> availableCaptureRequestKeys = cameraCacheInfo.characteristics.getAvailableCaptureRequestKeys();
        int i2 = 0;
        while (true) {
            if (i2 >= availableCaptureRequestKeys.size()) {
                z = false;
                break;
            }
            CaptureRequest.Key key = availableCaptureRequestKeys.get(i2);
            if (key.getName().equals(str)) {
                obj = cameraCacheInfo.builder.get(key);
                z = true;
                break;
            }
            i2++;
        }
        List<CameraCharacteristics.Key<?>> keys = cameraCacheInfo.characteristics.getKeys();
        int i3 = 0;
        while (true) {
            if (i3 >= keys.size()) {
                break;
            }
            CameraCharacteristics.Key key2 = keys.get(i3);
            if (key2.getName().equals(str)) {
                obj = cameraCacheInfo.characteristics.get(key2);
                z = true;
                break;
            }
            i3++;
        }
        if (!z) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (obj == null) {
            return -1;
        } else {
            if ((obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Long)) {
                return 1;
            }
            if (obj instanceof Float) {
                return 2;
            }
            if (obj instanceof Boolean) {
                return 3;
            }
            if (obj instanceof String) {
                return 0;
            }
            if (obj instanceof Range) {
                Comparable lower = ((Range) obj).getLower();
                return (!(lower instanceof Integer) && !(lower instanceof Long)) ? -1 : 4;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getNamedParameterCount(int i) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null || cameraCacheInfo.builder == null || cameraCacheInfo.characteristics == null) {
            SystemTools.setSystemErrorCode(4);
            return -1;
        }
        return cameraCacheInfo.characteristics.getAvailableCaptureRequestKeys().size() + cameraCacheInfo.characteristics.getKeys().size();
    }

    /* access modifiers changed from: package-private */
    public String getNamedParameter(int i, int i2) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null || cameraCacheInfo.builder == null || cameraCacheInfo.characteristics == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        List<CaptureRequest.Key<?>> availableCaptureRequestKeys = cameraCacheInfo.characteristics.getAvailableCaptureRequestKeys();
        List<CameraCharacteristics.Key<?>> keys = cameraCacheInfo.characteristics.getKeys();
        if (i2 < availableCaptureRequestKeys.size()) {
            CaptureRequest.Key key = availableCaptureRequestKeys.get(i2);
            if (key == null) {
                return null;
            }
            return key.getName();
        } else if (i2 - availableCaptureRequestKeys.size() < keys.size()) {
            CameraCharacteristics.Key key2 = keys.get(i2 - availableCaptureRequestKeys.size());
            if (key2 == null) {
                return null;
            }
            return key2.getName();
        } else {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x047b, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x04ef, code lost:
        if (r0 != AR_CAMERA_PARAMTYPE_FOCUSREGION) goto L_0x056e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setTypedCameraParameter(int r17, int r18, java.lang.Object r19) {
        /*
            r16 = this;
            r1 = r16
            r0 = r18
            com.vuforia.ar.pl.Camera2_Preview$CameraCacheInfo r2 = r16.getCameraCacheInfo(r17)
            r3 = 4
            r4 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            if (r2 == 0) goto L_0x0578
            android.hardware.camera2.CaptureRequest$Builder r6 = r2.builder
            if (r6 == 0) goto L_0x0578
            android.hardware.camera2.CameraCharacteristics r6 = r2.characteristics
            if (r6 != 0) goto L_0x001b
            r2 = 0
            goto L_0x0579
        L_0x001b:
            r6 = 0
            r7 = 2
            r8 = 3
            r9 = 1
            r10 = 6
            switch(r0) {
                case 536870913: goto L_0x0499;
                case 536870914: goto L_0x03e4;
                case 536870916: goto L_0x03c5;
                case 536870920: goto L_0x03bf;
                case 536870928: goto L_0x02d0;
                case 536870944: goto L_0x0258;
                case 536870976: goto L_0x0220;
                case 536871040: goto L_0x021c;
                case 536871168: goto L_0x01d9;
                case 536871424: goto L_0x01d5;
                case 536871936: goto L_0x017e;
                case 536872960: goto L_0x017a;
                case 536875008: goto L_0x0121;
                case 536879104: goto L_0x011d;
                case 536887296: goto L_0x0119;
                case 536903680: goto L_0x00cd;
                case 536936448: goto L_0x00c9;
                case 537001984: goto L_0x00c5;
                case 537133056: goto L_0x00c1;
                case 537395200: goto L_0x00bd;
                case 537919488: goto L_0x00b9;
                case 538968064: goto L_0x00b5;
                case 541065216: goto L_0x0094;
                case 553648128: goto L_0x0026;
                default: goto L_0x0024;
            }
        L_0x0024:
            r2 = 0
            return r2
        L_0x0026:
            android.hardware.camera2.CameraCharacteristics r3 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r6 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r3 = r3.get(r6)     // Catch:{ Exception -> 0x0571 }
            int[] r3 = (int[]) r3     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x003b
            int r3 = r3.length     // Catch:{ Exception -> 0x0571 }
            if (r3 <= r9) goto L_0x003b
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x003b
            r3 = 1
            goto L_0x003c
        L_0x003b:
            r3 = 0
        L_0x003c:
            android.hardware.camera2.CameraCharacteristics r6 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r7 = android.hardware.camera2.CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r6 = r6.get(r7)     // Catch:{ Exception -> 0x0571 }
            int[] r6 = (int[]) r6     // Catch:{ Exception -> 0x0571 }
            if (r6 == 0) goto L_0x0051
            int r6 = r6.length     // Catch:{ Exception -> 0x0571 }
            if (r6 <= r9) goto L_0x0051
            android.hardware.camera2.CaptureRequest$Key r6 = android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE     // Catch:{ Exception -> 0x0571 }
            if (r6 == 0) goto L_0x0051
            r6 = 1
            goto L_0x0052
        L_0x0051:
            r6 = 0
        L_0x0052:
            if (r3 != 0) goto L_0x005a
            if (r6 != 0) goto L_0x005a
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x005a:
            r7 = r19
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ Exception -> 0x0571 }
            boolean r7 = r7.booleanValue()     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x006b
            android.hardware.camera2.CaptureRequest$Builder r8 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r11 = android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE     // Catch:{ Exception -> 0x0571 }
            r8.set(r11, r5)     // Catch:{ Exception -> 0x0571 }
        L_0x006b:
            if (r6 == 0) goto L_0x0074
            android.hardware.camera2.CaptureRequest$Builder r8 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r11 = android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE     // Catch:{ Exception -> 0x0571 }
            r8.set(r11, r5)     // Catch:{ Exception -> 0x0571 }
        L_0x0074:
            if (r7 == 0) goto L_0x0092
            if (r3 == 0) goto L_0x0085
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0571 }
            r3.set(r5, r6)     // Catch:{ Exception -> 0x0571 }
            goto L_0x04cc
        L_0x0085:
            if (r6 == 0) goto L_0x0092
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0571 }
            r3.set(r5, r6)     // Catch:{ Exception -> 0x0571 }
        L_0x0092:
            goto L_0x04cc
        L_0x0094:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_CAPTURE_INTENT     // Catch:{ Exception -> 0x0571 }
            if (r3 != 0) goto L_0x009c
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x009c:
            r3 = r19
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ Exception -> 0x0571 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Builder r5 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r6 = android.hardware.camera2.CaptureRequest.CONTROL_CAPTURE_INTENT     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x00ab
            goto L_0x00ac
        L_0x00ab:
            r8 = 1
        L_0x00ac:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x0571 }
            r5.set(r6, r3)     // Catch:{ Exception -> 0x0571 }
            goto L_0x04cc
        L_0x00b5:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x00b9:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x00bd:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x00c1:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x00c5:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x00c9:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x00cd:
            r3 = r19
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ Exception -> 0x0571 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics r5 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r6 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r5 = r5.get(r6)     // Catch:{ Exception -> 0x0571 }
            float[] r5 = (float[]) r5     // Catch:{ Exception -> 0x0571 }
            if (r5 == 0) goto L_0x0115
            android.hardware.camera2.CaptureRequest$Key r6 = android.hardware.camera2.CaptureRequest.LENS_FOCAL_LENGTH     // Catch:{ Exception -> 0x0571 }
            if (r6 != 0) goto L_0x00e6
            goto L_0x0115
        L_0x00e6:
            int r6 = r5.length     // Catch:{ Exception -> 0x0571 }
            r8 = 0
        L_0x00e9:
            if (r8 >= r6) goto L_0x010c
            r11 = r5[r8]     // Catch:{ Exception -> 0x0571 }
            float r12 = (float) r3     // Catch:{ Exception -> 0x0571 }
            float r12 = r11 - r12
            float r12 = java.lang.Math.abs(r12)     // Catch:{ Exception -> 0x0571 }
            r13 = 1008981770(0x3c23d70a, float:0.01)
            int r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            if (r12 >= 0) goto L_0x0109
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.LENS_FOCAL_LENGTH     // Catch:{ Exception -> 0x0571 }
            java.lang.Float r6 = java.lang.Float.valueOf(r11)     // Catch:{ Exception -> 0x0571 }
            r3.set(r5, r6)     // Catch:{ Exception -> 0x0571 }
            r3 = 1
            goto L_0x010d
        L_0x0109:
            int r8 = r8 + 1
            goto L_0x00e9
        L_0x010c:
            r3 = 0
        L_0x010d:
            if (r3 != 0) goto L_0x0113
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0113:
            goto L_0x04cc
        L_0x0115:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0119:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x011d:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0121:
            r3 = r19
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ Exception -> 0x0571 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0571 }
            r5 = 806354944(0x30100000, float:5.2386895E-10)
            if (r3 == r5) goto L_0x0164
            r5 = 809500672(0x30400000, float:6.9849193E-10)
            if (r3 == r5) goto L_0x0135
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0135:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x0154
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r3 = r3.get(r5)     // Catch:{ Exception -> 0x0571 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ Exception -> 0x0571 }
            boolean r3 = r3.booleanValue()     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x0154
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x0571 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x0571 }
            r3.set(r5, r6)     // Catch:{ Exception -> 0x0571 }
        L_0x0154:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x0178
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0571 }
            r3.set(r5, r6)     // Catch:{ Exception -> 0x0571 }
            goto L_0x0178
        L_0x0164:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x0571 }
            if (r3 != 0) goto L_0x016c
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x016c:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x0571 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r9)     // Catch:{ Exception -> 0x0571 }
            r3.set(r5, r6)     // Catch:{ Exception -> 0x0571 }
        L_0x0178:
            goto L_0x04cc
        L_0x017a:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x017e:
            r3 = r19
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ Exception -> 0x0571 }
            float r3 = r3.floatValue()     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics r5 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r6 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r5 = r5.get(r6)     // Catch:{ Exception -> 0x0571 }
            android.util.Range r5 = (android.util.Range) r5     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics r6 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r7 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r6 = r6.get(r7)     // Catch:{ Exception -> 0x0571 }
            android.util.Rational r6 = (android.util.Rational) r6     // Catch:{ Exception -> 0x0571 }
            android.util.Range<java.lang.Integer> r7 = EMPTY_RANGE     // Catch:{ Exception -> 0x0571 }
            boolean r7 = r7.equals(r5)     // Catch:{ Exception -> 0x0571 }
            if (r7 != 0) goto L_0x01d1
            android.hardware.camera2.CaptureRequest$Key r7 = android.hardware.camera2.CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION     // Catch:{ Exception -> 0x0571 }
            if (r7 == 0) goto L_0x01d1
            if (r6 == 0) goto L_0x01d1
            android.hardware.camera2.CameraCharacteristics$Key r7 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP     // Catch:{ Exception -> 0x0571 }
            if (r7 != 0) goto L_0x01ad
            goto L_0x01d1
        L_0x01ad:
            float r6 = r6.floatValue()     // Catch:{ Exception -> 0x0571 }
            float r3 = r3 / r6
            int r3 = java.lang.Math.round(r3)     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x0571 }
            boolean r5 = r5.contains(r6)     // Catch:{ Exception -> 0x0571 }
            if (r5 == 0) goto L_0x01cd
            android.hardware.camera2.CaptureRequest$Builder r5 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r6 = android.hardware.camera2.CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x0571 }
            r5.set(r6, r3)     // Catch:{ Exception -> 0x0571 }
            goto L_0x04cc
        L_0x01cd:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x01d1:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x01d5:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x01d9:
            android.hardware.camera2.CameraCharacteristics r3 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r5 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r3 = r3.get(r5)     // Catch:{ Exception -> 0x0571 }
            android.util.Range r3 = (android.util.Range) r3     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x0218
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.SENSOR_EXPOSURE_TIME     // Catch:{ Exception -> 0x0571 }
            if (r5 != 0) goto L_0x01ea
            goto L_0x0218
        L_0x01ea:
            r5 = r19
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ Exception -> 0x0571 }
            float r5 = r5.floatValue()     // Catch:{ Exception -> 0x0571 }
            double r5 = (double) r5
            r7 = 4741671816366391296(0x41cdcd6500000000, double:1.0E9)
            java.lang.Double.isNaN(r5)
            double r5 = r5 * r7
            long r5 = java.lang.Math.round(r5)     // Catch:{ Exception -> 0x0571 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0571 }
            boolean r3 = r3.contains(r5)     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x0214
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r6 = android.hardware.camera2.CaptureRequest.SENSOR_EXPOSURE_TIME     // Catch:{ Exception -> 0x0571 }
            r3.set(r6, r5)     // Catch:{ Exception -> 0x0571 }
            goto L_0x04cc
        L_0x0214:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0218:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x021c:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0220:
            android.hardware.camera2.CameraCharacteristics r3 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r5 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r3 = r3.get(r5)     // Catch:{ Exception -> 0x0571 }
            android.util.Range r3 = (android.util.Range) r3     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x0254
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.SENSOR_SENSITIVITY     // Catch:{ Exception -> 0x0571 }
            if (r5 != 0) goto L_0x0231
            goto L_0x0254
        L_0x0231:
            r5 = r19
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ Exception -> 0x0571 }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0571 }
            boolean r3 = r3.contains(r6)     // Catch:{ Exception -> 0x0571 }
            if (r3 == 0) goto L_0x0250
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r6 = android.hardware.camera2.CaptureRequest.SENSOR_SENSITIVITY     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0571 }
            r3.set(r6, r5)     // Catch:{ Exception -> 0x0571 }
            goto L_0x04cc
        L_0x0250:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0254:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0258:
            r3 = r19
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ Exception -> 0x0571 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics r6 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r7 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r6 = r6.get(r7)     // Catch:{ Exception -> 0x0571 }
            int[] r6 = (int[]) r6     // Catch:{ Exception -> 0x0571 }
            if (r6 == 0) goto L_0x02cc
            android.hardware.camera2.CaptureRequest$Key r7 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE     // Catch:{ Exception -> 0x0571 }
            if (r7 != 0) goto L_0x0271
            goto L_0x02cc
        L_0x0271:
            r7 = 805310464(0x30001000, float:4.6588866E-10)
            if (r3 == r7) goto L_0x02a9
            r7 = 805322752(0x30004000, float:4.665708E-10)
            if (r3 == r7) goto L_0x0284
            r7 = 805339136(0x30008000, float:4.674803E-10)
            if (r3 == r7) goto L_0x02a9
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0284:
            int r3 = r6.length     // Catch:{ Exception -> 0x0571 }
            r5 = 0
            r7 = 0
        L_0x0288:
            if (r5 >= r3) goto L_0x0297
            r7 = r6[r5]     // Catch:{ Exception -> 0x0571 }
            if (r7 != r9) goto L_0x0290
            r7 = 1
            goto L_0x0291
        L_0x0290:
            r7 = 0
        L_0x0291:
            if (r7 == 0) goto L_0x0294
            goto L_0x0297
        L_0x0294:
            int r5 = r5 + 1
            goto L_0x0288
        L_0x0297:
            if (r7 != 0) goto L_0x029d
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x029d:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0571 }
            r3.set(r5, r6)     // Catch:{ Exception -> 0x0571 }
            goto L_0x02ca
        L_0x02a9:
            int r3 = r6.length     // Catch:{ Exception -> 0x0571 }
            r7 = 0
            r8 = 0
        L_0x02ad:
            if (r7 >= r3) goto L_0x02bc
            r8 = r6[r7]     // Catch:{ Exception -> 0x0571 }
            if (r8 != 0) goto L_0x02b5
            r8 = 1
            goto L_0x02b6
        L_0x02b5:
            r8 = 0
        L_0x02b6:
            if (r8 == 0) goto L_0x02b9
            goto L_0x02bc
        L_0x02b9:
            int r7 = r7 + 1
            goto L_0x02ad
        L_0x02bc:
            if (r8 != 0) goto L_0x02c2
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x02c2:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r6 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE     // Catch:{ Exception -> 0x0571 }
            r3.set(r6, r5)     // Catch:{ Exception -> 0x0571 }
        L_0x02ca:
            goto L_0x04cc
        L_0x02cc:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x02d0:
            r5 = r19
            float[] r5 = (float[]) r5     // Catch:{ Exception -> 0x0571 }
            float[] r5 = (float[]) r5     // Catch:{ Exception -> 0x0571 }
            int r11 = r5.length     // Catch:{ Exception -> 0x0571 }
            r12 = 5
            if (r11 == r12) goto L_0x02de
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x02de:
            r11 = r5[r4]     // Catch:{ Exception -> 0x0571 }
            int r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r11 < 0) goto L_0x03ba
            r11 = r5[r4]     // Catch:{ Exception -> 0x0571 }
            r12 = 1065353216(0x3f800000, float:1.0)
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r11 > 0) goto L_0x03ba
            r11 = r5[r9]     // Catch:{ Exception -> 0x0571 }
            int r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r11 < 0) goto L_0x03ba
            r11 = r5[r9]     // Catch:{ Exception -> 0x0571 }
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r11 > 0) goto L_0x03ba
            r11 = r5[r7]     // Catch:{ Exception -> 0x0571 }
            int r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r11 < 0) goto L_0x03ba
            r11 = r5[r7]     // Catch:{ Exception -> 0x0571 }
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r11 > 0) goto L_0x03ba
            r11 = r5[r8]     // Catch:{ Exception -> 0x0571 }
            int r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r11 < 0) goto L_0x03ba
            r11 = r5[r8]     // Catch:{ Exception -> 0x0571 }
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r11 > 0) goto L_0x03ba
            r11 = r5[r3]     // Catch:{ Exception -> 0x0571 }
            int r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r11 < 0) goto L_0x03ba
            r11 = r5[r3]     // Catch:{ Exception -> 0x0571 }
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r11 <= 0) goto L_0x031e
            goto L_0x03ba
        L_0x031e:
            android.hardware.camera2.CameraCharacteristics r11 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r12 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r11 = r11.get(r12)     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r11 = (java.lang.Integer) r11     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r12 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x0571 }
            if (r12 == 0) goto L_0x03b4
            if (r11 == 0) goto L_0x03b4
            int r11 = r11.intValue()     // Catch:{ Exception -> 0x0571 }
            if (r11 != 0) goto L_0x0336
            goto L_0x03b4
        L_0x0336:
            android.hardware.camera2.CameraCharacteristics r11 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r12 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r11 = r11.get(r12)     // Catch:{ Exception -> 0x0571 }
            android.graphics.Rect r11 = (android.graphics.Rect) r11     // Catch:{ Exception -> 0x0571 }
            if (r11 != 0) goto L_0x0346
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0346:
            android.hardware.camera2.params.MeteringRectangle[] r12 = new android.hardware.camera2.params.MeteringRectangle[r9]     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.params.MeteringRectangle r13 = new android.hardware.camera2.params.MeteringRectangle     // Catch:{ Exception -> 0x0571 }
            android.graphics.Rect r14 = new android.graphics.Rect     // Catch:{ Exception -> 0x0571 }
            r15 = r5[r4]     // Catch:{ Exception -> 0x0571 }
            int r10 = r11.width()     // Catch:{ Exception -> 0x0571 }
            float r10 = (float) r10     // Catch:{ Exception -> 0x0571 }
            float r15 = r15 * r10
            int r10 = (int) r15     // Catch:{ Exception -> 0x0571 }
            int r15 = r11.width()     // Catch:{ Exception -> 0x0571 }
            int r15 = r15 - r9
            int r10 = java.lang.Math.min(r10, r15)     // Catch:{ Exception -> 0x0571 }
            r15 = r5[r9]     // Catch:{ Exception -> 0x0571 }
            int r4 = r11.height()     // Catch:{ Exception -> 0x0571 }
            float r4 = (float) r4     // Catch:{ Exception -> 0x0571 }
            float r15 = r15 * r4
            int r4 = (int) r15     // Catch:{ Exception -> 0x0571 }
            int r15 = r11.height()     // Catch:{ Exception -> 0x0571 }
            int r15 = r15 - r9
            int r4 = java.lang.Math.min(r4, r15)     // Catch:{ Exception -> 0x0571 }
            r7 = r5[r7]     // Catch:{ Exception -> 0x0571 }
            int r15 = r11.width()     // Catch:{ Exception -> 0x0571 }
            float r15 = (float) r15     // Catch:{ Exception -> 0x0571 }
            float r7 = r7 * r15
            int r7 = (int) r7     // Catch:{ Exception -> 0x0571 }
            int r15 = r11.width()     // Catch:{ Exception -> 0x0571 }
            int r15 = r15 - r9
            int r7 = java.lang.Math.min(r7, r15)     // Catch:{ Exception -> 0x0571 }
            r8 = r5[r8]     // Catch:{ Exception -> 0x0571 }
            int r15 = r11.height()     // Catch:{ Exception -> 0x0571 }
            float r15 = (float) r15     // Catch:{ Exception -> 0x0571 }
            float r8 = r8 * r15
            int r8 = (int) r8     // Catch:{ Exception -> 0x0571 }
            int r11 = r11.height()     // Catch:{ Exception -> 0x0571 }
            int r11 = r11 - r9
            int r8 = java.lang.Math.min(r8, r11)     // Catch:{ Exception -> 0x0571 }
            r14.<init>(r10, r4, r7, r8)     // Catch:{ Exception -> 0x0571 }
            r3 = r5[r3]     // Catch:{ Exception -> 0x0571 }
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            float r3 = r3 + r6
            int r3 = (int) r3     // Catch:{ Exception -> 0x0571 }
            r13.<init>(r14, r3)     // Catch:{ Exception -> 0x0571 }
            r3 = 0
            r12[r3] = r13     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x0571 }
            r3.set(r4, r12)     // Catch:{ Exception -> 0x0571 }
            r4 = 1
            goto L_0x04cd
        L_0x03b4:
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x03ba:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x03bf:
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x03c5:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE     // Catch:{ Exception -> 0x0571 }
            if (r3 != 0) goto L_0x03cf
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x03cf:
            r3 = r19
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ Exception -> 0x0571 }
            float r3 = r3.floatValue()     // Catch:{ Exception -> 0x0571 }
            java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Builder r4 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE     // Catch:{ Exception -> 0x0571 }
            r4.set(r5, r3)     // Catch:{ Exception -> 0x0571 }
            goto L_0x04cc
        L_0x03e4:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x0571 }
            if (r3 != 0) goto L_0x03ee
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x03ee:
            android.hardware.camera2.CameraCharacteristics r3 = r2.characteristics     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CameraCharacteristics$Key r4 = android.hardware.camera2.CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES     // Catch:{ Exception -> 0x0571 }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x0571 }
            int[] r3 = (int[]) r3     // Catch:{ Exception -> 0x0571 }
            java.util.Arrays.sort(r3)     // Catch:{ Exception -> 0x0571 }
            r4 = r19
            java.lang.Number r4 = (java.lang.Number) r4     // Catch:{ Exception -> 0x0571 }
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x0571 }
            r10 = -1
            switch(r4) {
                case 805306384: goto L_0x047d;
                case 805306400: goto L_0x047d;
                case 805306432: goto L_0x0463;
                case 805306496: goto L_0x0449;
                case 805306624: goto L_0x0420;
                case 805306880: goto L_0x040c;
                default: goto L_0x0407;
            }     // Catch:{ Exception -> 0x0571 }
        L_0x0407:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x0571 }
            goto L_0x0497
        L_0x040c:
            r4 = 0
            int r3 = java.util.Arrays.binarySearch(r3, r4)     // Catch:{ Exception -> 0x0571 }
            if (r3 != r10) goto L_0x0418
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0418:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x0571 }
            r3.set(r4, r5)     // Catch:{ Exception -> 0x0571 }
            goto L_0x047b
        L_0x0420:
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE     // Catch:{ Exception -> 0x0571 }
            if (r4 != 0) goto L_0x042a
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            r4 = 0
            return r4
        L_0x042a:
            r4 = 0
            int r3 = java.util.Arrays.binarySearch(r3, r4)     // Catch:{ Exception -> 0x0571 }
            if (r3 != r10) goto L_0x0436
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            return r4
        L_0x0436:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x0571 }
            r3.set(r4, r5)     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE     // Catch:{ Exception -> 0x0571 }
            java.lang.Float r5 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0571 }
            r3.set(r4, r5)     // Catch:{ Exception -> 0x0571 }
            goto L_0x047b
        L_0x0449:
            int r3 = java.util.Arrays.binarySearch(r3, r7)     // Catch:{ Exception -> 0x0571 }
            if (r3 != r10) goto L_0x0455
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x0455:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x0571 }
            r3.set(r4, r5)     // Catch:{ Exception -> 0x0571 }
            r4 = 1
            goto L_0x0496
        L_0x0463:
            int r3 = java.util.Arrays.binarySearch(r3, r8)     // Catch:{ Exception -> 0x0571 }
            if (r3 != r10) goto L_0x046f
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x046f:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x0571 }
            r3.set(r4, r5)     // Catch:{ Exception -> 0x0571 }
        L_0x047b:
            r4 = 0
            goto L_0x0496
        L_0x047d:
            int r3 = java.util.Arrays.binarySearch(r3, r9)     // Catch:{ Exception -> 0x0571 }
            if (r3 != r10) goto L_0x0489
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x0489:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0571 }
            r3.set(r4, r5)     // Catch:{ Exception -> 0x0571 }
            r4 = 1
        L_0x0496:
            goto L_0x04cd
        L_0x0497:
            r2 = 0
            return r2
        L_0x0499:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.FLASH_MODE     // Catch:{ Exception -> 0x0571 }
            if (r3 != 0) goto L_0x04a3
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x04a3:
            r3 = r19
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ Exception -> 0x0571 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0571 }
            switch(r3) {
                case 805306369: goto L_0x04c4;
                case 805306370: goto L_0x04b8;
                case 805306371: goto L_0x04ae;
                case 805306372: goto L_0x04b3;
                default: goto L_0x04ae;
            }     // Catch:{ Exception -> 0x0571 }
        L_0x04ae:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x0571 }
            goto L_0x056f
        L_0x04b3:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x0571 }
            r2 = 0
            return r2
        L_0x04b8:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.FLASH_MODE     // Catch:{ Exception -> 0x0571 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x0571 }
            r3.set(r4, r5)     // Catch:{ Exception -> 0x0571 }
            goto L_0x04cc
        L_0x04c4:
            android.hardware.camera2.CaptureRequest$Builder r3 = r2.builder     // Catch:{ Exception -> 0x0571 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.FLASH_MODE     // Catch:{ Exception -> 0x0571 }
            r3.set(r4, r5)     // Catch:{ Exception -> 0x0571 }
        L_0x04cc:
            r4 = 0
        L_0x04cd:
            android.hardware.camera2.CameraCaptureSession r3 = r2.session
            if (r3 == 0) goto L_0x056e
            android.hardware.camera2.CameraCaptureSession r3 = r2.session     // Catch:{ CameraAccessException -> 0x0567, IllegalArgumentException -> 0x0560, IllegalStateException -> 0x0559 }
            android.hardware.camera2.CaptureRequest$Builder r5 = r2.builder     // Catch:{ CameraAccessException -> 0x0567, IllegalArgumentException -> 0x0560, IllegalStateException -> 0x0559 }
            android.hardware.camera2.CaptureRequest r5 = r5.build()     // Catch:{ CameraAccessException -> 0x0567, IllegalArgumentException -> 0x0560, IllegalStateException -> 0x0559 }
            com.vuforia.ar.pl.Camera2_Preview$OnFrameCapturedCallback r6 = new com.vuforia.ar.pl.Camera2_Preview$OnFrameCapturedCallback     // Catch:{ CameraAccessException -> 0x0567, IllegalArgumentException -> 0x0560, IllegalStateException -> 0x0559 }
            r6.<init>(r2)     // Catch:{ CameraAccessException -> 0x0567, IllegalArgumentException -> 0x0560, IllegalStateException -> 0x0559 }
            android.os.Handler r7 = r2.handler     // Catch:{ CameraAccessException -> 0x0567, IllegalArgumentException -> 0x0560, IllegalStateException -> 0x0559 }
            r3.setRepeatingRequest(r5, r6, r7)     // Catch:{ CameraAccessException -> 0x0567, IllegalArgumentException -> 0x0560, IllegalStateException -> 0x0559 }
            if (r4 == 0) goto L_0x056e
            r3 = 536870914(0x20000002, float:1.0842024E-19)
            if (r0 == r3) goto L_0x04f2
            r3 = 536870928(0x20000010, float:1.0842042E-19)
            if (r0 == r3) goto L_0x053f
            goto L_0x0551
        L_0x04f2:
            android.hardware.camera2.CameraCharacteristics r0 = r2.characteristics     // Catch:{ Exception -> 0x0552 }
            android.hardware.camera2.CameraCharacteristics$Key r3 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF     // Catch:{ Exception -> 0x0552 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ Exception -> 0x0552 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x0552 }
            if (r0 == 0) goto L_0x053f
            android.hardware.camera2.CameraCharacteristics$Key r3 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF     // Catch:{ Exception -> 0x0552 }
            if (r3 == 0) goto L_0x053f
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x0552 }
            if (r0 <= 0) goto L_0x053f
            android.hardware.camera2.CaptureRequest$Key r0 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x0552 }
            if (r0 == 0) goto L_0x053f
            android.hardware.camera2.CaptureRequest$Builder r0 = r2.builder     // Catch:{ Exception -> 0x0552 }
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x0552 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ Exception -> 0x0552 }
            android.hardware.camera2.params.MeteringRectangle[] r0 = (android.hardware.camera2.params.MeteringRectangle[]) r0     // Catch:{ Exception -> 0x0552 }
            if (r0 == 0) goto L_0x053f
            int r3 = r0.length     // Catch:{ Exception -> 0x0552 }
            if (r3 <= 0) goto L_0x053f
            int r3 = r0.length     // Catch:{ Exception -> 0x0552 }
            android.hardware.camera2.params.MeteringRectangle[] r3 = new android.hardware.camera2.params.MeteringRectangle[r3]     // Catch:{ Exception -> 0x0552 }
            int r4 = r0.length     // Catch:{ Exception -> 0x0552 }
            r5 = 0
            r6 = 0
        L_0x0522:
            if (r5 >= r4) goto L_0x0538
            r7 = r0[r5]     // Catch:{ Exception -> 0x0552 }
            int r8 = r6 + 1
            android.hardware.camera2.params.MeteringRectangle r10 = new android.hardware.camera2.params.MeteringRectangle     // Catch:{ Exception -> 0x0552 }
            android.graphics.Rect r7 = r7.getRect()     // Catch:{ Exception -> 0x0552 }
            r11 = 0
            r10.<init>(r7, r11)     // Catch:{ Exception -> 0x0552 }
            r3[r6] = r10     // Catch:{ Exception -> 0x0552 }
            int r5 = r5 + 1
            r6 = r8
            goto L_0x0522
        L_0x0538:
            android.hardware.camera2.CaptureRequest$Builder r0 = r2.builder     // Catch:{ Exception -> 0x0552 }
            android.hardware.camera2.CaptureRequest$Key r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x0552 }
            r0.set(r4, r3)     // Catch:{ Exception -> 0x0552 }
        L_0x053f:
            com.vuforia.ar.pl.Camera2_Preview$AutofocusRunner r0 = new com.vuforia.ar.pl.Camera2_Preview$AutofocusRunner     // Catch:{ Exception -> 0x0552 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0552 }
            boolean r0 = r0.triggerAutofocus()     // Catch:{ Exception -> 0x0552 }
            if (r0 != 0) goto L_0x0550
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)     // Catch:{ Exception -> 0x0552 }
            r2 = 0
            return r2
        L_0x0550:
        L_0x0551:
            goto L_0x056e
        L_0x0552:
            r0 = move-exception
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)
            r2 = 0
            return r2
        L_0x0559:
            r0 = move-exception
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)
            r3 = 0
            return r3
        L_0x0560:
            r0 = move-exception
            r2 = 6
            r3 = 0
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)
            return r3
        L_0x0567:
            r0 = move-exception
            r2 = 6
            r3 = 0
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)
            return r3
        L_0x056e:
            return r9
        L_0x056f:
            r2 = 0
            return r2
        L_0x0571:
            r0 = move-exception
            r2 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2)
            r2 = 0
            return r2
        L_0x0578:
            r2 = 0
        L_0x0579:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.setTypedCameraParameter(int, int, java.lang.Object):boolean");
    }

    /* access modifiers changed from: package-private */
    public Object getTypedCameraParameter(int i, int i2) {
        boolean z;
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null || cameraCacheInfo.characteristics == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        CaptureResult captureResult = cameraCacheInfo.lastResult;
        switch (i2) {
            case AR_CAMERA_PARAMTYPE_TORCHMODE /*536870913*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer num = (Integer) captureResult.get(CaptureResult.FLASH_MODE);
                if (num != null) {
                    if (CaptureResult.FLASH_MODE != null) {
                        if (num.equals(2)) {
                            return Integer.valueOf(AR_CAMERA_TORCHMODE_ON);
                        }
                        if (num.equals(0)) {
                            return Integer.valueOf(AR_CAMERA_TORCHMODE_OFF);
                        }
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSMODE /*536870914*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AF_MODE);
                if (num2 != null) {
                    if (CaptureResult.CONTROL_AF_MODE != null) {
                        if (num2.equals(1)) {
                            return Integer.valueOf(cameraCacheInfo.isAutoFocusing ? AR_CAMERA_FOCUSMODE_AUTO : AR_CAMERA_FOCUSMODE_NORMAL);
                        } else if (num2.equals(3)) {
                            return Integer.valueOf(AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO);
                        } else {
                            if (num2.equals(0)) {
                                Float f = (Float) captureResult.get(CaptureResult.LENS_FOCUS_DISTANCE);
                                if (!(f == null || CaptureResult.LENS_FOCUS_DISTANCE == null)) {
                                    if (f.equals(Float.valueOf(0.0f))) {
                                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_INFINITY);
                                    }
                                }
                                return Integer.valueOf(AR_CAMERA_FOCUSMODE_FIXED);
                            } else if (num2.equals(2)) {
                                return Integer.valueOf(AR_CAMERA_FOCUSMODE_MACRO);
                            } else {
                                SystemTools.setSystemErrorCode(6);
                                return null;
                            }
                        }
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSVALUE /*536870916*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Float f2 = (Float) captureResult.get(CaptureResult.LENS_FOCAL_LENGTH);
                if (f2 != null) {
                    if (CaptureResult.LENS_FOCAL_LENGTH != null) {
                        return f2;
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSRANGE /*536870920*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Pair pair = (Pair) captureResult.get(CaptureResult.LENS_FOCUS_RANGE);
                if (pair == null || CaptureResult.LENS_FOCUS_RANGE == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                return new float[]{((Float) pair.first).floatValue(), ((Float) pair.second).floatValue()};
            case AR_CAMERA_PARAMTYPE_FOCUSREGION /*536870928*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer num3 = (Integer) cameraCacheInfo.characteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
                if (num3 == null || CameraCharacteristics.CONTROL_MAX_REGIONS_AF == null || num3.intValue() <= 0 || CaptureResult.CONTROL_AF_REGIONS == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                MeteringRectangle[] meteringRectangleArr = (MeteringRectangle[]) captureResult.get(CaptureResult.CONTROL_AF_REGIONS);
                if (meteringRectangleArr != null) {
                    if (meteringRectangleArr.length != 0) {
                        Rect rect = (Rect) cameraCacheInfo.characteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
                        if (rect == null) {
                            SystemTools.setSystemErrorCode(6);
                            return false;
                        }
                        Rect rect2 = meteringRectangleArr[0].getRect();
                        return new float[]{(float) (rect2.left / (rect.width() - 1)), (float) (rect2.top / (rect.height() - 1)), (float) (rect2.right / (rect.width() - 1)), (float) (rect2.bottom / (rect.height() - 1)), (float) ((meteringRectangleArr[0].getMeteringWeight() - 0) / 1000)};
                    }
                }
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREMODE /*536870944*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_MODE);
                if (num4 != null) {
                    if (CaptureResult.CONTROL_AE_MODE != null) {
                        if (num4.equals(0)) {
                            return Integer.valueOf(AR_CAMERA_EXPOSUREMODE_MANUAL);
                        }
                        if (num4.equals(1)) {
                            return Integer.valueOf(AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO);
                        }
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ISO /*536870976*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer num5 = (Integer) captureResult.get(CaptureResult.SENSOR_SENSITIVITY);
                if (num5 != null && CaptureResult.SENSOR_SENSITIVITY != null) {
                    return Float.valueOf(num5.floatValue());
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ISORANGE /*536871040*/:
                Range range = (Range) cameraCacheInfo.characteristics.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE);
                if (range != null) {
                    if (CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE != null) {
                        return new float[]{((Integer) range.getLower()).floatValue(), ((Integer) range.getUpper()).floatValue()};
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSURETIME /*536871168*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Long l = (Long) captureResult.get(CaptureResult.SENSOR_EXPOSURE_TIME);
                if (l != null && CaptureResult.SENSOR_EXPOSURE_TIME != null) {
                    return Float.valueOf((float) (l.doubleValue() / 1.0E9d));
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSURETIMERANGE /*536871424*/:
                Range range2 = (Range) cameraCacheInfo.characteristics.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE);
                if (range2 != null) {
                    if (CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE != null) {
                        return new float[]{(float) (((Long) range2.getLower()).doubleValue() / 1.0E9d), (float) (((Long) range2.getUpper()).doubleValue() / 1.0E9d)};
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUE /*536871936*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer num6 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION);
                Rational rational = (Rational) cameraCacheInfo.characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
                if (num6 != null && CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION != null && rational != null && CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP != null) {
                    return Float.valueOf(rational.floatValue() * ((float) num6.intValue()));
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE /*536872960*/:
                Range range3 = (Range) cameraCacheInfo.characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
                Rational rational2 = (Rational) cameraCacheInfo.characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
                if (!(range3 == null || CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE == null || rational2 == null)) {
                    if (CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP != null) {
                        return new float[]{rational2.floatValue() * ((float) ((Integer) range3.getLower()).intValue()), rational2.floatValue() * ((float) ((Integer) range3.getUpper()).intValue())};
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEMODE /*536875008*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEVALUE /*536879104*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCERANGE /*536887296*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ZOOMVALUE /*536903680*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Float f3 = (Float) captureResult.get(CaptureResult.LENS_FOCAL_LENGTH);
                if (f3 != null) {
                    if (CaptureResult.LENS_FOCAL_LENGTH != null) {
                        return f3;
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ZOOMRANGE /*536936448*/:
                SystemTools.setSystemErrorCode(6);
                break;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE /*537001984*/:
                break;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSRANGE /*537133056*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_CONTRASTVALUE /*537395200*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_CONTRASTRANGE /*537919488*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ROTATION /*538968064*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION /*553648128*/:
                if (captureResult == null) {
                    try {
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    } catch (Exception e) {
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                } else {
                    Integer num7 = (Integer) captureResult.get(CaptureResult.CONTROL_VIDEO_STABILIZATION_MODE);
                    if (num7 == null || CaptureResult.CONTROL_VIDEO_STABILIZATION_MODE == null) {
                        z = false;
                    } else if (num7.equals(1)) {
                        return true;
                    } else {
                        z = true;
                    }
                    Integer num8 = (Integer) captureResult.get(CaptureResult.LENS_OPTICAL_STABILIZATION_MODE);
                    if (!(num8 == null || CaptureResult.LENS_OPTICAL_STABILIZATION_MODE == null)) {
                        if (num8.equals(1)) {
                            return true;
                        }
                        z = true;
                    }
                    if (z) {
                        return false;
                    }
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            default:
                return null;
        }
        SystemTools.setSystemErrorCode(6);
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getStatus(int i) {
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo != null) {
            return cameraCacheInfo.status;
        }
        SystemTools.setSystemErrorCode(4);
        return AR_CAMERA_STATUS_UNKNOWN;
    }
}
