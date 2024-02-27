package com.vuforia.ar.pl;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

public class Camera1_Preview implements Camera.PreviewCallback {
    private static final int AR_CAMERA_DIRECTION_BACK = 268443665;
    private static final int AR_CAMERA_DIRECTION_FRONT = 268443666;
    private static final int AR_CAMERA_DIRECTION_UNKNOWN = 268443664;
    private static final int AR_CAMERA_EXPOSUREMODE_AUTO = 805314560;
    private static final int AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO = 805322752;
    private static final int AR_CAMERA_EXPOSUREMODE_LOCKED = 805310464;
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
    private static final int[] CAMERA_IMAGE_FORMAT_CONVERSIONTABLE = {16, AR_CAMERA_IMAGE_FORMAT_NV16, 17, AR_CAMERA_IMAGE_FORMAT_NV21, 4, AR_CAMERA_IMAGE_FORMAT_RGB565, 842094169, AR_CAMERA_IMAGE_FORMAT_YV12};
    private static boolean CONVERT_FORMAT_TO_ANDROID = false;
    private static boolean CONVERT_FORMAT_TO_PL = true;
    private static final String FOCUS_MODE_NORMAL = "normal";
    private static final String MODULENAME = "Camera1_Preview";
    private static final int NUM_CAPTURE_BUFFERS = 2;
    private static final int NUM_CAPTURE_BUFFERS_TO_ADD = 2;
    private static final int NUM_MAX_CAMERAOPEN_RETRY = 10;
    private static final String SAMSUNG_PARAM_FAST_FPS_MODE = "fast-fps-mode";
    private static final String SAMSUNG_PARAM_VRMODE = "vrmode";
    private static final String SAMSUNG_PARAM_VRMODE_SUPPORTED = "vrmode-supported";
    private static final int TIME_CAMERAOPEN_RETRY_DELAY_MS = 250;
    private static final int _NUM_CAMERA_CAPSINFO_VALUE_ = 6;
    private static final int _NUM_CAMERA_CAPTUREINFO_VALUE_ = 5;
    private Vector<CameraCacheInfo> cameraCacheInfo = null;
    /* access modifiers changed from: private */
    public HashMap<Camera, Integer> cameraCacheInfoIndexCache = null;
    private SurfaceManager surfaceManager = null;

    private native void newFrameAvailable(long j, int i, int i2, int i3, int i4, byte[] bArr, long j2);

    /* access modifiers changed from: package-private */
    public int getBitsPerPixel(int i) {
        if (i == 4) {
            return 16;
        }
        if (i == 842094169) {
            return 12;
        }
        if (i != 16) {
            return i != 17 ? 0 : 12;
        }
        return 16;
    }

    public class CameraCacheInfo {
        byte[][] buffer;
        int bufferFormatPL;
        int bufferHeight;
        int bufferSize;
        int bufferWidth;
        Camera camera;
        int[] caps;
        long deviceHandle;
        int deviceID;
        boolean isAutoFocusing;
        int overrideFormatAndroid;
        int overrideHeight;
        int overrideWidth;
        int requestFormatAndroid;
        int requestHeight;
        int requestWidth;
        int status;
        CameraSurface surface;
        SurfaceTexture surfaceTexture;

        public CameraCacheInfo() {
        }
    }

    private boolean checkPermission() {
        try {
            Activity activityFromNative = SystemTools.getActivityFromNative();
            if (activityFromNative != null && activityFromNative.getPackageManager().checkPermission("android.permission.CAMERA", activityFromNative.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private int getCameraDeviceIndex(int i, int i2, int i3) {
        int i4 = 1;
        if (SystemTools.checkMinimumApiLevel(9)) {
            switch (i3) {
                case AR_CAMERA_DIRECTION_UNKNOWN /*268443664*/:
                    i4 = -1;
                    break;
                case AR_CAMERA_DIRECTION_BACK /*268443665*/:
                    i4 = 0;
                    break;
                case AR_CAMERA_DIRECTION_FRONT /*268443666*/:
                    break;
                default:
                    SystemTools.setSystemErrorCode(2);
                    return -1;
            }
            int numberOfCameras = Camera.getNumberOfCameras();
            for (int i5 = 0; i5 < numberOfCameras; i5++) {
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                try {
                    Camera.getCameraInfo(i5, cameraInfo);
                    if ((i4 < 0 || i4 == cameraInfo.facing) && (i < 0 || i == i5)) {
                        return i5;
                    }
                } catch (Exception unused) {
                }
            }
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (i3 == AR_CAMERA_DIRECTION_FRONT) {
            SystemTools.setSystemErrorCode(2);
            return -1;
        } else if (i < 1) {
            return 0;
        } else {
            SystemTools.setSystemErrorCode(2);
            return -1;
        }
    }

    private Camera.Parameters getCameraParameters(Camera camera) {
        try {
            return camera.getParameters();
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public CameraCacheInfo getCameraCacheInfo(int i) {
        if (i < 0 || i >= this.cameraCacheInfo.size()) {
            return null;
        }
        return this.cameraCacheInfo.get(i);
    }

    private boolean setCustomCameraParams(Camera.Parameters parameters, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    Object obj = jSONObject.get(next);
                    if (obj.getClass() == String.class) {
                        parameters.set(next, (String) obj);
                    } else if (obj.getClass() != Integer.class) {
                        return false;
                    } else {
                        parameters.set(next, ((Integer) obj).intValue());
                    }
                } catch (JSONException unused) {
                }
            }
            return true;
        } catch (JSONException unused2) {
            return false;
        }
    }

    private boolean setCameraPreviewFps(int i, Camera.Parameters parameters) {
        int[] iArr;
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        int i2 = i * 1000;
        if ((i == 60 || i == 120) && "true".equalsIgnoreCase(parameters.get(SAMSUNG_PARAM_VRMODE_SUPPORTED))) {
            iArr = new int[2];
            parameters.set(SAMSUNG_PARAM_VRMODE, 1);
            parameters.setRecordingHint(true);
            parameters.set("focus-mode", "continuous-video");
            if (i == 60) {
                parameters.set(SAMSUNG_PARAM_FAST_FPS_MODE, 1);
                iArr[0] = 60000;
                iArr[1] = 60000;
            }
            if (i == 120) {
                parameters.set(SAMSUNG_PARAM_FAST_FPS_MODE, 2);
                iArr[0] = 120000;
                iArr[1] = 120000;
            }
        } else {
            if (!(!"true".equalsIgnoreCase(parameters.get(SAMSUNG_PARAM_VRMODE_SUPPORTED)) || parameters.get(SAMSUNG_PARAM_FAST_FPS_MODE) == null || parameters.getInt(SAMSUNG_PARAM_FAST_FPS_MODE) == 0)) {
                parameters.set(SAMSUNG_PARAM_VRMODE, 0);
                parameters.set(SAMSUNG_PARAM_FAST_FPS_MODE, 0);
            }
            int[] iArr2 = null;
            for (int[] next : supportedPreviewFpsRange) {
                if (next[0] == i2 && next[1] - next[0] < Integer.MAX_VALUE) {
                    iArr2 = next;
                }
            }
            iArr = iArr2;
        }
        if (iArr == null) {
            return false;
        }
        parameters.setPreviewFpsRange(iArr[0], iArr[1]);
        return true;
    }

    private boolean setCameraCaptureParams(CameraCacheInfo cameraCacheInfo2, Camera.Parameters parameters, int[] iArr, int[] iArr2) {
        if (!(iArr == null && iArr2 == null)) {
            cameraCacheInfo2.overrideWidth = iArr2 != null ? iArr2[0] : iArr[0];
            cameraCacheInfo2.overrideHeight = iArr2 != null ? iArr2[1] : iArr[1];
            cameraCacheInfo2.overrideFormatAndroid = translateImageFormat(iArr2 != null ? iArr2[2] : iArr[2], CONVERT_FORMAT_TO_ANDROID);
        }
        if (iArr == null) {
            return true;
        }
        cameraCacheInfo2.requestWidth = iArr[0];
        cameraCacheInfo2.requestHeight = iArr[1];
        cameraCacheInfo2.requestFormatAndroid = translateImageFormat(iArr[2], CONVERT_FORMAT_TO_ANDROID);
        int i = iArr[3];
        try {
            if (cameraCacheInfo2.requestWidth > 0 && cameraCacheInfo2.requestHeight > 0) {
                parameters.setPreviewSize(cameraCacheInfo2.requestWidth, cameraCacheInfo2.requestHeight);
            }
            if (i > 0) {
                if (!SystemTools.checkMinimumApiLevel(8)) {
                    parameters.setPreviewFrameRate(i);
                } else if (!setCameraPreviewFps(i, parameters)) {
                    parameters.setPreviewFrameRate(i);
                }
            }
            if (cameraCacheInfo2.requestFormatAndroid != 0) {
                parameters.setPreviewFormat(cameraCacheInfo2.requestFormatAndroid);
            }
            if (iArr[4] > 0) {
                if (SystemTools.checkMinimumApiLevel(11)) {
                    try {
                        cameraCacheInfo2.surfaceTexture = new SurfaceTexture(-1);
                        try {
                            cameraCacheInfo2.camera.setPreviewTexture(cameraCacheInfo2.surfaceTexture);
                        } catch (Exception unused) {
                        }
                    } catch (Exception unused2) {
                        return false;
                    }
                } else {
                    SurfaceManager surfaceManager2 = this.surfaceManager;
                    if (surfaceManager2 == null || !surfaceManager2.addCameraSurface(cameraCacheInfo2)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception unused3) {
            return false;
        }
    }

    private boolean checkSamsungHighFPS(CameraCacheInfo cameraCacheInfo2) {
        Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
        if (cameraParameters == null) {
            SystemTools.setSystemErrorCode(6);
            return false;
        } else if (!"true".equalsIgnoreCase(cameraParameters.get(SAMSUNG_PARAM_VRMODE_SUPPORTED)) || cameraCacheInfo2.requestWidth <= 0 || cameraCacheInfo2.requestHeight <= 0 || cameraParameters.get(SAMSUNG_PARAM_FAST_FPS_MODE) == null || cameraParameters.getInt(SAMSUNG_PARAM_FAST_FPS_MODE) == 0) {
            return true;
        } else {
            if (cameraCacheInfo2.requestWidth == cameraParameters.getPreviewSize().width && cameraCacheInfo2.requestHeight == cameraParameters.getPreviewSize().height) {
                return true;
            }
            DebugLog.LOGW(MODULENAME, "Detected Samsung high fps camera driver bug.");
            DebugLog.LOGW(MODULENAME, "Preview size doesn't match request; width " + cameraCacheInfo2.requestWidth + "!=" + cameraParameters.getPreviewSize().width + " or height " + cameraCacheInfo2.requestHeight + "!=" + cameraParameters.getPreviewSize().height);
            setCameraPreviewFps(30, cameraParameters);
            cameraParameters.setPreviewSize(cameraCacheInfo2.requestWidth, cameraCacheInfo2.requestHeight);
            try {
                cameraCacheInfo2.camera.setParameters(cameraParameters);
                Camera.Parameters cameraParameters2 = getCameraParameters(cameraCacheInfo2.camera);
                if (cameraCacheInfo2.requestWidth == cameraParameters2.getPreviewSize().width && cameraCacheInfo2.requestHeight == cameraParameters2.getPreviewSize().height) {
                    return true;
                }
                DebugLog.LOGE(MODULENAME, "Unable to workaround Samsung high fps camera driver bug.");
                DebugLog.LOGE(MODULENAME, "Preview size doesn't match request; width " + cameraCacheInfo2.requestWidth + "!=" + cameraParameters2.getPreviewSize().width + " or height " + cameraCacheInfo2.requestHeight + "!=" + cameraParameters2.getPreviewSize().height);
                return false;
            } catch (Exception unused) {
                SystemTools.setSystemErrorCode(6);
                return false;
            }
        }
    }

    private boolean setupPreviewBuffer(CameraCacheInfo cameraCacheInfo2) {
        int i;
        Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
        if (cameraParameters == null) {
            return false;
        }
        try {
            cameraCacheInfo2.bufferWidth = cameraCacheInfo2.requestWidth == cameraCacheInfo2.overrideWidth ? cameraParameters.getPreviewSize().width : cameraCacheInfo2.overrideWidth;
            cameraCacheInfo2.bufferHeight = cameraCacheInfo2.requestHeight == cameraCacheInfo2.overrideHeight ? cameraParameters.getPreviewSize().height : cameraCacheInfo2.overrideHeight;
            int previewFormat = cameraCacheInfo2.requestFormatAndroid == cameraCacheInfo2.overrideFormatAndroid ? cameraParameters.getPreviewFormat() : cameraCacheInfo2.overrideFormatAndroid;
            cameraCacheInfo2.bufferFormatPL = translateImageFormat(previewFormat, CONVERT_FORMAT_TO_PL);
            try {
                PixelFormat pixelFormat = new PixelFormat();
                PixelFormat.getPixelFormatInfo(previewFormat, pixelFormat);
                i = pixelFormat.bitsPerPixel;
            } catch (Exception unused) {
                i = getBitsPerPixel(previewFormat);
                if (i == 0) {
                    return false;
                }
            }
            int i2 = (((cameraCacheInfo2.bufferWidth * cameraCacheInfo2.bufferHeight) * i) / 8) + 4096;
            if (i2 <= cameraCacheInfo2.bufferSize) {
                cameraCacheInfo2.camera.setPreviewCallbackWithBuffer(this);
                return true;
            }
            cameraCacheInfo2.buffer = new byte[2][];
            for (int i3 = 0; i3 < 2; i3++) {
                cameraCacheInfo2.buffer[i3] = new byte[i2];
                if (i3 < 2) {
                    cameraCacheInfo2.camera.addCallbackBuffer(cameraCacheInfo2.buffer[i3]);
                }
            }
            cameraCacheInfo2.bufferSize = i2;
            cameraCacheInfo2.camera.setPreviewCallbackWithBuffer(this);
            System.gc();
            return true;
        } catch (Exception unused2) {
            return false;
        }
    }

    private void setCameraCapsBit(CameraCacheInfo cameraCacheInfo2, int i, int i2, boolean z) {
        int i3;
        if (i == 0 || i == 1) {
            i3 = AR_CAMERA_PARAMTYPE_BASE;
        } else if (i == 2) {
            i3 = AR_CAMERA_PARAMVALUE_BASE;
        } else {
            return;
        }
        int log = (int) (Math.log((double) (i2 & (~i3))) / Math.log(2.0d));
        if (z) {
            int[] iArr = cameraCacheInfo2.caps;
            iArr[i] = (1 << log) | iArr[i];
            return;
        }
        int[] iArr2 = cameraCacheInfo2.caps;
        iArr2[i] = (~(1 << log)) & iArr2[i];
    }

    private int translateImageFormat(int i, boolean z) {
        int i2 = 0;
        while (true) {
            int[] iArr = CAMERA_IMAGE_FORMAT_CONVERSIONTABLE;
            if (i2 < iArr.length / 2) {
                if (i == (z == CONVERT_FORMAT_TO_PL ? iArr[i2 * 2] : iArr[(i2 * 2) + 1])) {
                    return z == CONVERT_FORMAT_TO_PL ? CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[(i2 * 2) + 1] : CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[i2 * 2];
                }
                i2++;
            } else if (z == CONVERT_FORMAT_TO_PL) {
                return AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            } else {
                return 0;
            }
        }
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        long nanoTime = System.nanoTime();
        SystemTools.checkMinimumApiLevel(18);
        Integer num = this.cameraCacheInfoIndexCache.get(camera);
        if (num == null) {
            SystemTools.checkMinimumApiLevel(18);
            return;
        }
        int intValue = num.intValue();
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(intValue);
        if (cameraCacheInfo2 == null) {
            SystemTools.checkMinimumApiLevel(18);
            return;
        }
        newFrameAvailable(cameraCacheInfo2.deviceHandle, intValue, cameraCacheInfo2.bufferWidth, cameraCacheInfo2.bufferHeight, cameraCacheInfo2.bufferFormatPL, bArr, nanoTime);
        camera.addCallbackBuffer(bArr);
        SystemTools.checkMinimumApiLevel(18);
    }

    public boolean init() {
        this.cameraCacheInfo = new Vector<>();
        this.cameraCacheInfoIndexCache = new HashMap<>();
        return true;
    }

    public void setSurfaceManager(SurfaceManager surfaceManager2) {
        this.surfaceManager = surfaceManager2;
    }

    public int getNumberOfCameras() {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (SystemTools.checkMinimumApiLevel(9)) {
            try {
                return Camera.getNumberOfCameras();
            } catch (Exception unused) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
        } else {
            try {
                return SystemTools.getActivityFromNative().getPackageManager().hasSystemFeature("android.hardware.camera") ? 1 : 0;
            } catch (Exception unused2) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
        }
    }

    public int getOrientation(int i) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (SystemTools.checkMinimumApiLevel(9)) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            try {
                Camera.getCameraInfo(i, cameraInfo);
                return cameraInfo.orientation;
            } catch (Exception unused) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
        } else {
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
    }

    public int getDirection(int i) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (!SystemTools.checkMinimumApiLevel(9)) {
            return AR_CAMERA_DIRECTION_BACK;
        } else {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            try {
                Camera.getCameraInfo(i, cameraInfo);
                int i2 = cameraInfo.facing;
                if (i2 != 0) {
                    return i2 != 1 ? AR_CAMERA_DIRECTION_UNKNOWN : AR_CAMERA_DIRECTION_FRONT;
                }
                return AR_CAMERA_DIRECTION_BACK;
            } catch (Exception unused) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
        }
    }

    public int getDeviceID(int i) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 != null) {
            return cameraCacheInfo2.deviceID;
        }
        SystemTools.setSystemErrorCode(4);
        return -1;
    }

    public int open(long j, int i, int i2, int i3, String str, int[] iArr, int[] iArr2) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
        int cameraDeviceIndex = getCameraDeviceIndex(i, i2, i3);
        if (cameraDeviceIndex < 0) {
            return -1;
        }
        int size = this.cameraCacheInfo.size();
        boolean z = false;
        CameraCacheInfo cameraCacheInfo2 = null;
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                i4 = -1;
                break;
            }
            cameraCacheInfo2 = this.cameraCacheInfo.get(i4);
            if (cameraCacheInfo2.deviceID == cameraDeviceIndex) {
                break;
            }
            i4++;
        }
        if (i4 < 0) {
            cameraCacheInfo2 = new CameraCacheInfo();
            cameraCacheInfo2.deviceID = cameraDeviceIndex;
            cameraCacheInfo2.deviceHandle = j;
            cameraCacheInfo2.camera = null;
            cameraCacheInfo2.surface = null;
            cameraCacheInfo2.buffer = null;
            cameraCacheInfo2.overrideWidth = 0;
            cameraCacheInfo2.requestWidth = 0;
            cameraCacheInfo2.bufferWidth = 0;
            cameraCacheInfo2.overrideHeight = 0;
            cameraCacheInfo2.requestHeight = 0;
            cameraCacheInfo2.bufferHeight = 0;
            cameraCacheInfo2.bufferFormatPL = AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            cameraCacheInfo2.overrideFormatAndroid = 0;
            cameraCacheInfo2.requestFormatAndroid = 0;
            cameraCacheInfo2.caps = null;
            cameraCacheInfo2.status = AR_CAMERA_STATUS_UNINITIALIZED;
            cameraCacheInfo2.isAutoFocusing = false;
        }
        cameraCacheInfo2.bufferSize = 0;
        int i5 = NUM_MAX_CAMERAOPEN_RETRY;
        boolean z2 = false;
        while (true) {
            try {
                if (SystemTools.checkMinimumApiLevel(9)) {
                    cameraCacheInfo2.camera = Camera.open(cameraCacheInfo2.deviceID);
                } else if (cameraCacheInfo2.deviceID == 0) {
                    cameraCacheInfo2.camera = Camera.open();
                }
                z2 = cameraCacheInfo2.camera != null;
            } catch (Exception unused) {
            }
            if (!z2 && i5 > 0) {
                try {
                    synchronized (this) {
                        wait(250);
                    }
                } catch (Exception unused2) {
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
        if (cameraCacheInfo2.camera == null) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
        boolean z3 = (iArr != null && iArr.length > 0) || (iArr2 != null && iArr2.length > 0);
        if (str != null && str.length() > 0) {
            z = true;
        }
        if (z3 || z) {
            Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
            if (cameraParameters == null) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
            if (z3) {
                if (iArr != null && iArr.length != 5) {
                    SystemTools.setSystemErrorCode(2);
                    return -1;
                } else if (!setCameraCaptureParams(cameraCacheInfo2, cameraParameters, iArr, iArr2)) {
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                }
            }
            if (!z || setCustomCameraParams(cameraParameters, str)) {
                try {
                    cameraCacheInfo2.camera.setParameters(cameraParameters);
                    if (!checkSamsungHighFPS(cameraCacheInfo2)) {
                        return -1;
                    }
                } catch (Exception unused3) {
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                }
            } else {
                SystemTools.setSystemErrorCode(2);
                return -1;
            }
        }
        cameraCacheInfo2.status = AR_CAMERA_STATUS_OPENED;
        if (i4 < 0) {
            this.cameraCacheInfo.add(cameraCacheInfo2);
            i4 = this.cameraCacheInfo.size() - 1;
        }
        this.cameraCacheInfoIndexCache.put(cameraCacheInfo2.camera, Integer.valueOf(i4));
        return i4;
    }

    public boolean close(int i) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        boolean z = false;
        if (cameraCacheInfo2 == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        this.cameraCacheInfoIndexCache.remove(cameraCacheInfo2.camera);
        try {
            cameraCacheInfo2.camera.release();
            z = true;
        } catch (Exception unused) {
        }
        cameraCacheInfo2.camera = null;
        cameraCacheInfo2.buffer = null;
        cameraCacheInfo2.status = AR_CAMERA_STATUS_UNINITIALIZED;
        System.gc();
        return z;
    }

    public int[] getCameraCapabilities(int i) {
        boolean z;
        int i2;
        boolean z2;
        int i3;
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        } else if (cameraCacheInfo2.caps != null) {
            return cameraCacheInfo2.caps;
        } else {
            Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
            if (cameraParameters == null) {
                SystemTools.setSystemErrorCode(6);
                return null;
            }
            List<Camera.Size> supportedPreviewSizes = cameraParameters.getSupportedPreviewSizes();
            List<Integer> supportedPreviewFrameRates = cameraParameters.getSupportedPreviewFrameRates();
            List<Integer> supportedPreviewFormats = cameraParameters.getSupportedPreviewFormats();
            List<String> supportedFlashModes = cameraParameters.getSupportedFlashModes();
            List<String> supportedFocusModes = cameraParameters.getSupportedFocusModes();
            int size = supportedPreviewSizes != null ? supportedPreviewSizes.size() : 0;
            int size2 = supportedPreviewFrameRates != null ? supportedPreviewFrameRates.size() : 0;
            int size3 = supportedPreviewFormats != null ? supportedPreviewFormats.size() : 0;
            cameraCacheInfo2.caps = new int[((size * 2) + 6 + size2 + size3)];
            cameraCacheInfo2.caps[0] = AR_CAMERA_PARAMTYPE_BASE;
            setCameraCapsBit(cameraCacheInfo2, 0, AR_CAMERA_PARAMTYPE_TORCHMODE, supportedFlashModes != null && (supportedFlashModes.contains("torch") || supportedFlashModes.contains("on")));
            setCameraCapsBit(cameraCacheInfo2, 0, AR_CAMERA_PARAMTYPE_FOCUSMODE, true);
            setCameraCapsBit(cameraCacheInfo2, 0, AR_CAMERA_PARAMTYPE_FOCUSVALUE, SystemTools.checkMinimumApiLevel(8));
            setCameraCapsBit(cameraCacheInfo2, 0, AR_CAMERA_PARAMTYPE_FOCUSREGION, SystemTools.checkMinimumApiLevel(14));
            setCameraCapsBit(cameraCacheInfo2, 0, AR_CAMERA_PARAMTYPE_EXPOSUREVALUE, SystemTools.checkMinimumApiLevel(8));
            setCameraCapsBit(cameraCacheInfo2, 0, AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE, SystemTools.checkMinimumApiLevel(8));
            setCameraCapsBit(cameraCacheInfo2, 0, AR_CAMERA_PARAMTYPE_ZOOMVALUE, SystemTools.checkMinimumApiLevel(8) && cameraParameters.isZoomSupported());
            setCameraCapsBit(cameraCacheInfo2, 0, AR_CAMERA_PARAMTYPE_ZOOMRANGE, SystemTools.checkMinimumApiLevel(8) && cameraParameters.isZoomSupported());
            setCameraCapsBit(cameraCacheInfo2, 0, AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION, SystemTools.checkMinimumApiLevel(15));
            cameraCacheInfo2.caps[1] = AR_CAMERA_PARAMTYPE_BASE;
            if (supportedFlashModes == null || (!supportedFlashModes.contains("torch") && !supportedFlashModes.contains("on"))) {
                i2 = AR_CAMERA_PARAMTYPE_TORCHMODE;
                z = false;
            } else {
                z = true;
                i2 = AR_CAMERA_PARAMTYPE_TORCHMODE;
            }
            setCameraCapsBit(cameraCacheInfo2, 1, i2, z);
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_FOCUSMODE, true);
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_FOCUSREGION, SystemTools.checkMinimumApiLevel(14));
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_EXPOSUREVALUE, SystemTools.checkMinimumApiLevel(8));
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_ZOOMVALUE, SystemTools.checkMinimumApiLevel(8) && cameraParameters.isZoomSupported());
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION, SystemTools.checkMinimumApiLevel(15));
            cameraCacheInfo2.caps[2] = AR_CAMERA_PARAMVALUE_BASE;
            if (supportedFlashModes == null || (!supportedFlashModes.contains("torch") && !supportedFlashModes.contains("on"))) {
                z2 = true;
            } else {
                z2 = true;
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_TORCHMODE_OFF, true);
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_TORCHMODE_ON, true);
            }
            if (supportedFocusModes != null) {
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_FOCUSMODE_NORMAL, z2);
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_FOCUSMODE_AUTO, supportedFocusModes.contains("auto"));
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO, supportedFocusModes.contains("continuous-video"));
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_FOCUSMODE_MACRO, supportedFocusModes.contains("macro"));
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_FOCUSMODE_INFINITY, supportedFocusModes.contains("infinity"));
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_FOCUSMODE_FIXED, supportedFocusModes.contains("fixed"));
            }
            cameraCacheInfo2.caps[3] = size;
            cameraCacheInfo2.caps[4] = size2;
            cameraCacheInfo2.caps[5] = size3;
            if (size > 0) {
                ListIterator<Camera.Size> listIterator = supportedPreviewSizes.listIterator();
                i3 = 6;
                while (listIterator.hasNext()) {
                    Camera.Size next = listIterator.next();
                    cameraCacheInfo2.caps[i3] = next.width;
                    cameraCacheInfo2.caps[i3 + 1] = next.height;
                    i3 += 2;
                }
            } else {
                i3 = 6;
            }
            if (size2 > 0) {
                ListIterator<Integer> listIterator2 = supportedPreviewFrameRates.listIterator();
                while (listIterator2.hasNext()) {
                    cameraCacheInfo2.caps[i3] = listIterator2.next().intValue();
                    i3++;
                }
            }
            if (size3 > 0) {
                ListIterator<Integer> listIterator3 = supportedPreviewFormats.listIterator();
                while (listIterator3.hasNext()) {
                    cameraCacheInfo2.caps[i3] = translateImageFormat(listIterator3.next().intValue(), true);
                    i3++;
                }
            }
            return cameraCacheInfo2.caps;
        }
    }

    public boolean setCaptureInfo(int i, int[] iArr, int[] iArr2) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (iArr.length != 5) {
            SystemTools.setSystemErrorCode(2);
            return false;
        } else {
            Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
            if (cameraParameters == null) {
                SystemTools.setSystemErrorCode(6);
                return false;
            } else if (!setCameraCaptureParams(cameraCacheInfo2, cameraParameters, iArr, iArr2)) {
                SystemTools.setSystemErrorCode(6);
                return false;
            } else {
                try {
                    cameraCacheInfo2.camera.setParameters(cameraParameters);
                    if (!checkSamsungHighFPS(cameraCacheInfo2)) {
                        return false;
                    }
                    return true;
                } catch (Exception unused) {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
            }
        }
    }

    public int[] getCaptureInfo(int i) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
        if (cameraParameters == null) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
        try {
            int[] iArr = new int[5];
            int i2 = 0;
            iArr[0] = cameraParameters.getPreviewSize().width;
            iArr[1] = cameraParameters.getPreviewSize().height;
            iArr[2] = translateImageFormat(cameraParameters.getPreviewFormat(), CONVERT_FORMAT_TO_PL);
            iArr[3] = cameraParameters.getPreviewFrameRate();
            if (!(cameraCacheInfo2.surface == null && cameraCacheInfo2.surfaceTexture == null)) {
                i2 = 1;
            }
            iArr[4] = i2;
            return iArr;
        } catch (Exception unused) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
    }

    public boolean start(int i) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (!setupPreviewBuffer(cameraCacheInfo2)) {
            SystemTools.setSystemErrorCode(6);
            return false;
        } else {
            try {
                cameraCacheInfo2.camera.startPreview();
                cameraCacheInfo2.status = AR_CAMERA_STATUS_CAPTURE_RUNNING;
                return true;
            } catch (Exception unused) {
                SystemTools.setSystemErrorCode(6);
                return false;
            }
        }
    }

    public boolean stop(int i) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        try {
            cameraCacheInfo2.camera.stopPreview();
            cameraCacheInfo2.status = AR_CAMERA_STATUS_OPENED;
            return true;
        } catch (Exception unused) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    public boolean setBatchParameters(int i, String str) {
        if (str == null) {
            return false;
        }
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null || cameraCacheInfo2.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
        if (cameraParameters == null) {
            SystemTools.setSystemErrorCode(6);
            return false;
        } else if (!setCustomCameraParams(cameraParameters, str)) {
            return false;
        } else {
            cameraCacheInfo2.camera.setParameters(cameraParameters);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean setUntypedCameraParameter(int i, String str, String str2) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null || cameraCacheInfo2.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
        if (cameraParameters == null) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
        try {
            cameraParameters.set(str, str2);
            cameraCacheInfo2.camera.setParameters(cameraParameters);
            return true;
        } catch (Exception unused) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public String getUntypedCameraParameter(int i, String str) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null || cameraCacheInfo2.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
        if (cameraParameters == null) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
        String str2 = cameraParameters.get(str);
        if (str2 == null) {
            SystemTools.setSystemErrorCode(6);
        }
        return str2;
    }

    /* access modifiers changed from: package-private */
    public String getFlattenedParameters(int i) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null || cameraCacheInfo2.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return "";
        }
        Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
        if (cameraParameters != null) {
            return cameraParameters.flatten();
        }
        SystemTools.setSystemErrorCode(6);
        return "";
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x026d, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x02af, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:?, code lost:
        r5.camera.setParameters(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x02b5, code lost:
        if (r2 == false) goto L_0x02cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x02ba, code lost:
        if (r1 == AR_CAMERA_PARAMTYPE_FOCUSMODE) goto L_0x02bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:?, code lost:
        r5.isAutoFocusing = true;
        r5.camera.autoFocus(new com.vuforia.ar.pl.Camera1_Preview.AnonymousClass1(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x02ca, code lost:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x02ce, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x02cf, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x02d0, code lost:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x02d4, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setTypedCameraParameter(int r18, int r19, java.lang.Object r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            java.lang.String r2 = "continuous-video"
            java.lang.String r3 = "normal"
            java.lang.String r4 = "torch"
            com.vuforia.ar.pl.Camera1_Preview$CameraCacheInfo r5 = r17.getCameraCacheInfo(r18)
            r6 = 4
            r7 = 0
            if (r5 == 0) goto L_0x02da
            android.hardware.Camera r8 = r5.camera
            if (r8 != 0) goto L_0x0018
            goto L_0x02da
        L_0x0018:
            android.hardware.Camera r8 = r5.camera
            android.hardware.Camera$Parameters r8 = r0.getCameraParameters(r8)
            r9 = 6
            if (r8 != 0) goto L_0x0025
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)
            return r7
        L_0x0025:
            r10 = 8
            r11 = 14
            r12 = 0
            r13 = 3
            r14 = 1
            switch(r1) {
                case 536870913: goto L_0x0282;
                case 536870914: goto L_0x022f;
                case 536870916: goto L_0x022a;
                case 536870920: goto L_0x0225;
                case 536870928: goto L_0x017d;
                case 536870944: goto L_0x0151;
                case 536870976: goto L_0x0116;
                case 536871936: goto L_0x00ee;
                case 536872960: goto L_0x00ea;
                case 536875008: goto L_0x00a7;
                case 536879104: goto L_0x00a3;
                case 536887296: goto L_0x009f;
                case 536903680: goto L_0x0082;
                case 536936448: goto L_0x007e;
                case 537001984: goto L_0x007a;
                case 537133056: goto L_0x0076;
                case 537395200: goto L_0x0072;
                case 537919488: goto L_0x006e;
                case 538968064: goto L_0x006a;
                case 541065216: goto L_0x0044;
                case 553648128: goto L_0x0030;
                default: goto L_0x002f;
            }
        L_0x002f:
            return r7
        L_0x0030:
            r2 = r20
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Exception -> 0x0222 }
            boolean r2 = r2.booleanValue()     // Catch:{ Exception -> 0x0222 }
            if (r2 == 0) goto L_0x003f
            r8.setVideoStabilization(r14)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x003f:
            r8.setVideoStabilization(r7)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x0044:
            r2 = r20
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x0222 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x0222 }
            boolean r3 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r11)     // Catch:{ Exception -> 0x0222 }
            if (r3 == 0) goto L_0x005c
            if (r2 == 0) goto L_0x0056
            r2 = r14
            goto L_0x0057
        L_0x0056:
            r2 = r7
        L_0x0057:
            r8.setRecordingHint(r2)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x005c:
            java.lang.String r3 = "recording-hint"
            if (r2 == 0) goto L_0x0063
            java.lang.String r2 = "true"
            goto L_0x0065
        L_0x0063:
            java.lang.String r2 = "false"
        L_0x0065:
            r8.set(r3, r2)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x006a:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x006e:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x0072:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x0076:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x007a:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x007e:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x0082:
            boolean r2 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r10)     // Catch:{ Exception -> 0x0222 }
            if (r2 == 0) goto L_0x009b
            boolean r2 = r8.isZoomSupported()     // Catch:{ Exception -> 0x0222 }
            if (r2 == 0) goto L_0x009b
            r2 = r20
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x0222 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x0222 }
            r8.setZoom(r2)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x009b:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x009f:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x00a3:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x00a7:
            r2 = r20
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x0222 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x0222 }
            r3 = 806354944(0x30100000, float:5.2386895E-10)
            if (r2 == r3) goto L_0x00df
            r3 = 809500672(0x30400000, float:6.9849193E-10)
            if (r2 == r3) goto L_0x00d4
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r13)     // Catch:{ Exception -> 0x0222 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0222 }
            r1.<init>()     // Catch:{ Exception -> 0x0222 }
            java.lang.String r3 = "Cannot set unknown white balance mode ("
            r1.append(r3)     // Catch:{ Exception -> 0x0222 }
            r1.append(r2)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r2 = ")"
            r1.append(r2)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0222 }
            com.vuforia.ar.pl.SystemTools.logSystemError(r1)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x00d4:
            boolean r2 = r8.isAutoWhiteBalanceLockSupported()     // Catch:{ Exception -> 0x0222 }
            if (r2 == 0) goto L_0x02af
            r8.setAutoWhiteBalanceLock(r7)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x00df:
            boolean r2 = r8.isAutoWhiteBalanceLockSupported()     // Catch:{ Exception -> 0x0222 }
            if (r2 == 0) goto L_0x02af
            r8.setAutoWhiteBalanceLock(r14)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x00ea:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x00ee:
            boolean r2 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r10)     // Catch:{ Exception -> 0x0222 }
            if (r2 == 0) goto L_0x0112
            r2 = r20
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x0222 }
            float r2 = r2.floatValue()     // Catch:{ Exception -> 0x0222 }
            float r3 = r8.getExposureCompensationStep()     // Catch:{ Exception -> 0x0222 }
            int r4 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r4 != 0) goto L_0x0108
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x0108:
            float r2 = r2 / r3
            int r2 = java.lang.Math.round(r2)     // Catch:{ Exception -> 0x0222 }
            r8.setExposureCompensation(r2)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x0112:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x0116:
            r2 = r20
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x0222 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x0222 }
            java.lang.String r2 = java.lang.Integer.toString(r2)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r3 = "iso-values"
            java.lang.String r3 = r8.get(r3)     // Catch:{ Exception -> 0x0222 }
            if (r3 == 0) goto L_0x014a
            java.lang.String r4 = ","
            java.lang.String[] r3 = r3.split(r4)     // Catch:{ Exception -> 0x0222 }
            r4 = r7
        L_0x0131:
            int r6 = r3.length     // Catch:{ Exception -> 0x0222 }
            if (r4 >= r6) goto L_0x014a
            r6 = r3[r4]     // Catch:{ Exception -> 0x0222 }
            java.lang.String r6 = r6.toLowerCase()     // Catch:{ Exception -> 0x0222 }
            java.lang.String r10 = r2.toLowerCase()     // Catch:{ Exception -> 0x0222 }
            boolean r6 = r6.contains(r10)     // Catch:{ Exception -> 0x0222 }
            if (r6 == 0) goto L_0x0147
            r2 = r3[r4]     // Catch:{ Exception -> 0x0222 }
            goto L_0x014a
        L_0x0147:
            int r4 = r4 + 1
            goto L_0x0131
        L_0x014a:
            java.lang.String r3 = "iso"
            r8.set(r3, r2)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x0151:
            r2 = r20
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x0222 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x0222 }
            r3 = 805310464(0x30001000, float:4.6588866E-10)
            if (r2 == r3) goto L_0x0172
            r3 = 805322752(0x30004000, float:4.665708E-10)
            if (r2 == r3) goto L_0x0167
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r13)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x0167:
            boolean r2 = r8.isAutoExposureLockSupported()     // Catch:{ Exception -> 0x0222 }
            if (r2 == 0) goto L_0x02af
            r8.setAutoExposureLock(r7)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x0172:
            boolean r2 = r8.isAutoExposureLockSupported()     // Catch:{ Exception -> 0x0222 }
            if (r2 == 0) goto L_0x02af
            r8.setAutoExposureLock(r14)     // Catch:{ Exception -> 0x0222 }
            goto L_0x02af
        L_0x017d:
            boolean r2 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r11)     // Catch:{ Exception -> 0x0222 }
            if (r2 == 0) goto L_0x021d
            r2 = r20
            float[] r2 = (float[]) r2     // Catch:{ Exception -> 0x0222 }
            float[] r2 = (float[]) r2     // Catch:{ Exception -> 0x0222 }
            int r3 = r2.length     // Catch:{ Exception -> 0x0222 }
            r4 = 5
            r10 = 2
            if (r3 == r4) goto L_0x0192
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x0222 }
            return r7
        L_0x0192:
            r3 = r2[r7]     // Catch:{ Exception -> 0x0222 }
            int r3 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r3 < 0) goto L_0x0219
            r3 = r2[r7]     // Catch:{ Exception -> 0x0222 }
            r4 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 > 0) goto L_0x0219
            r3 = r2[r14]     // Catch:{ Exception -> 0x0222 }
            int r3 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r3 < 0) goto L_0x0219
            r3 = r2[r14]     // Catch:{ Exception -> 0x0222 }
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 > 0) goto L_0x0219
            r3 = r2[r10]     // Catch:{ Exception -> 0x0222 }
            int r3 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r3 < 0) goto L_0x0219
            r3 = r2[r10]     // Catch:{ Exception -> 0x0222 }
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 > 0) goto L_0x0219
            r3 = r2[r13]     // Catch:{ Exception -> 0x0222 }
            int r3 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r3 < 0) goto L_0x0219
            r3 = r2[r13]     // Catch:{ Exception -> 0x0222 }
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 > 0) goto L_0x0219
            r3 = r2[r6]     // Catch:{ Exception -> 0x0222 }
            int r3 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r3 < 0) goto L_0x0219
            r3 = r2[r6]     // Catch:{ Exception -> 0x0222 }
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x01d1
            goto L_0x0219
        L_0x01d1:
            android.graphics.Rect r3 = new android.graphics.Rect     // Catch:{ Exception -> 0x0222 }
            r4 = r2[r7]     // Catch:{ Exception -> 0x0222 }
            double r11 = (double) r4     // Catch:{ Exception -> 0x0222 }
            r15 = 4656510908468559872(0x409f400000000000, double:2000.0)
            double r11 = r11 * r15
            int r4 = (int) r11     // Catch:{ Exception -> 0x0222 }
            int r4 = r4 + -1000
            r11 = r2[r14]     // Catch:{ Exception -> 0x0222 }
            double r11 = (double) r11     // Catch:{ Exception -> 0x0222 }
            double r11 = r11 * r15
            int r11 = (int) r11     // Catch:{ Exception -> 0x0222 }
            int r11 = r11 + -1000
            r10 = r2[r10]     // Catch:{ Exception -> 0x0222 }
            double r9 = (double) r10
            double r9 = r9 * r15
            int r9 = (int) r9
            int r9 = r9 + -1000
            r10 = r2[r13]     // Catch:{ Exception -> 0x02d5 }
            double r12 = (double) r10     // Catch:{ Exception -> 0x02d5 }
            double r12 = r12 * r15
            int r10 = (int) r12     // Catch:{ Exception -> 0x02d5 }
            int r10 = r10 + -1000
            r3.<init>(r4, r11, r9, r10)     // Catch:{ Exception -> 0x02d5 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x02d5 }
            r4.<init>()     // Catch:{ Exception -> 0x02d5 }
            android.hardware.Camera$Area r9 = new android.hardware.Camera$Area     // Catch:{ Exception -> 0x02d5 }
            r2 = r2[r6]     // Catch:{ Exception -> 0x02d5 }
            double r10 = (double) r2     // Catch:{ Exception -> 0x02d5 }
            r12 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r10 = r10 * r12
            int r2 = (int) r10     // Catch:{ Exception -> 0x02d5 }
            r9.<init>(r3, r2)     // Catch:{ Exception -> 0x02d5 }
            r4.add(r9)     // Catch:{ Exception -> 0x02d5 }
            int r2 = r8.getMaxNumFocusAreas()     // Catch:{ Exception -> 0x02d5 }
            if (r2 <= 0) goto L_0x02af
            r8.setFocusAreas(r4)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x02af
        L_0x0219:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x02d5 }
            return r7
        L_0x021d:
            r1 = r9
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)     // Catch:{ Exception -> 0x02d6 }
            return r7
        L_0x0222:
            r1 = r9
            goto L_0x02d6
        L_0x0225:
            r1 = r9
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)     // Catch:{ Exception -> 0x02d6 }
            return r7
        L_0x022a:
            r1 = r9
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)     // Catch:{ Exception -> 0x02d6 }
            return r7
        L_0x022f:
            android.hardware.Camera r4 = r5.camera     // Catch:{ Exception -> 0x02d5 }
            r4.cancelAutoFocus()     // Catch:{ Exception -> 0x02d5 }
            r4 = r20
            java.lang.Number r4 = (java.lang.Number) r4     // Catch:{ Exception -> 0x02d5 }
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x02d5 }
            java.lang.String r6 = "auto"
            switch(r4) {
                case 805306384: goto L_0x026f;
                case 805306400: goto L_0x026a;
                case 805306432: goto L_0x0257;
                case 805306496: goto L_0x0251;
                case 805306624: goto L_0x024b;
                case 805306880: goto L_0x0245;
                default: goto L_0x0241;
            }
        L_0x0241:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r13)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x0281
        L_0x0245:
            java.lang.String r2 = "fixed"
            r8.setFocusMode(r2)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x02af
        L_0x024b:
            java.lang.String r2 = "infinity"
            r8.setFocusMode(r2)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x02af
        L_0x0251:
            java.lang.String r2 = "macro"
            r8.setFocusMode(r2)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x02af
        L_0x0257:
            java.util.List r3 = r8.getSupportedFocusModes()     // Catch:{ Exception -> 0x02d5 }
            boolean r3 = r3.contains(r2)     // Catch:{ Exception -> 0x02d5 }
            if (r3 == 0) goto L_0x0265
            r8.setFocusMode(r2)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x02af
        L_0x0265:
            r1 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)     // Catch:{ Exception -> 0x02d6 }
            return r7
        L_0x026a:
            r8.setFocusMode(r6)     // Catch:{ Exception -> 0x02d5 }
        L_0x026d:
            r2 = r14
            goto L_0x02b0
        L_0x026f:
            java.util.List r2 = r8.getSupportedFocusModes()     // Catch:{ Exception -> 0x02d5 }
            boolean r2 = r2.contains(r3)     // Catch:{ Exception -> 0x02d5 }
            if (r2 == 0) goto L_0x027d
            r8.setFocusMode(r3)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x02af
        L_0x027d:
            r8.setFocusMode(r6)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x026d
        L_0x0281:
            return r7
        L_0x0282:
            r2 = r20
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x02d5 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x02d5 }
            switch(r2) {
                case 805306369: goto L_0x02aa;
                case 805306370: goto L_0x0296;
                case 805306371: goto L_0x028d;
                case 805306372: goto L_0x0292;
                default: goto L_0x028d;
            }
        L_0x028d:
            r1 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r13)     // Catch:{ Exception -> 0x02d6 }
            goto L_0x02d4
        L_0x0292:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r13)     // Catch:{ Exception -> 0x02d5 }
            return r7
        L_0x0296:
            java.util.List r2 = r8.getSupportedFlashModes()     // Catch:{ Exception -> 0x02d5 }
            boolean r2 = r2.contains(r4)     // Catch:{ Exception -> 0x02d5 }
            if (r2 == 0) goto L_0x02a4
            r8.setFlashMode(r4)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x02af
        L_0x02a4:
            java.lang.String r2 = "on"
            r8.setFlashMode(r2)     // Catch:{ Exception -> 0x02d5 }
            goto L_0x02af
        L_0x02aa:
            java.lang.String r2 = "off"
            r8.setFlashMode(r2)     // Catch:{ Exception -> 0x02d5 }
        L_0x02af:
            r2 = r7
        L_0x02b0:
            android.hardware.Camera r3 = r5.camera     // Catch:{ Exception -> 0x02d0 }
            r3.setParameters(r8)     // Catch:{ Exception -> 0x02d0 }
            if (r2 == 0) goto L_0x02cf
            r2 = 536870914(0x20000002, float:1.0842024E-19)
            if (r1 == r2) goto L_0x02bd
            goto L_0x02cf
        L_0x02bd:
            r5.isAutoFocusing = r14     // Catch:{ Exception -> 0x02ca }
            android.hardware.Camera r1 = r5.camera     // Catch:{ Exception -> 0x02ca }
            com.vuforia.ar.pl.Camera1_Preview$1 r2 = new com.vuforia.ar.pl.Camera1_Preview$1     // Catch:{ Exception -> 0x02ca }
            r2.<init>()     // Catch:{ Exception -> 0x02ca }
            r1.autoFocus(r2)     // Catch:{ Exception -> 0x02ca }
            goto L_0x02cf
        L_0x02ca:
            r1 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)
            return r7
        L_0x02cf:
            return r14
        L_0x02d0:
            r1 = 6
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)
        L_0x02d4:
            return r7
        L_0x02d5:
            r1 = 6
        L_0x02d6:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1)
            return r7
        L_0x02da:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.setTypedCameraParameter(int, int, java.lang.Object):boolean");
    }

    /* access modifiers changed from: package-private */
    public Object getTypedCameraParameter(int i, int i2) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 == null || cameraCacheInfo2.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        Camera.Parameters cameraParameters = getCameraParameters(cameraCacheInfo2.camera);
        if (cameraParameters == null) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
        switch (i2) {
            case AR_CAMERA_PARAMTYPE_TORCHMODE /*536870913*/:
                String flashMode = cameraParameters.getFlashMode();
                if (!flashMode.equals("torch")) {
                    if (!flashMode.equals("on")) {
                        if (flashMode.equals("off")) {
                            return Integer.valueOf(AR_CAMERA_TORCHMODE_OFF);
                        }
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                }
                return Integer.valueOf(AR_CAMERA_TORCHMODE_ON);
            case AR_CAMERA_PARAMTYPE_FOCUSMODE /*536870914*/:
                String focusMode = cameraParameters.getFocusMode();
                if (focusMode.equals("auto")) {
                    return Integer.valueOf(cameraCacheInfo2.isAutoFocusing ? AR_CAMERA_FOCUSMODE_AUTO : AR_CAMERA_FOCUSMODE_NORMAL);
                } else if (focusMode.equals("continuous-video")) {
                    return Integer.valueOf(AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO);
                } else {
                    if (focusMode.equals("infinity")) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_INFINITY);
                    }
                    if (focusMode.equals("macro")) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_MACRO);
                    }
                    if (focusMode.equals("fixed")) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_FIXED);
                    }
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            case AR_CAMERA_PARAMTYPE_FOCUSVALUE /*536870916*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    return Float.valueOf(cameraParameters.getFocalLength());
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSRANGE /*536870920*/:
                if (SystemTools.checkMinimumApiLevel(9)) {
                    float[] fArr = new float[3];
                    cameraParameters.getFocusDistances(fArr);
                    return new float[]{fArr[0], fArr[2]};
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSREGION /*536870928*/:
                if (SystemTools.checkMinimumApiLevel(14) && cameraParameters.getMaxNumFocusAreas() > 0) {
                    List<Camera.Area> focusAreas = cameraParameters.getFocusAreas();
                    if (focusAreas.size() > 0) {
                        Camera.Area area = focusAreas.get(0);
                        return new float[]{(float) area.rect.left, (float) area.rect.top, (float) area.rect.right, (float) area.rect.bottom, (float) area.weight};
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREMODE /*536870944*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUE /*536871936*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    return Float.valueOf(cameraParameters.getExposureCompensationStep() * ((float) cameraParameters.getExposureCompensation()));
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE /*536872960*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    return new float[]{cameraParameters.getExposureCompensationStep() * ((float) cameraParameters.getMinExposureCompensation()), cameraParameters.getExposureCompensationStep() * ((float) cameraParameters.getMaxExposureCompensation())};
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
                if (SystemTools.checkMinimumApiLevel(8) && cameraParameters.isZoomSupported()) {
                    return Integer.valueOf(cameraParameters.getZoom());
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ZOOMRANGE /*536936448*/:
                if (!SystemTools.checkMinimumApiLevel(8) || !cameraParameters.isZoomSupported()) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                return new int[]{0, cameraParameters.getMaxZoom()};
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE /*537001984*/:
                SystemTools.setSystemErrorCode(6);
                return null;
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
                try {
                    if (cameraParameters.getVideoStabilization()) {
                        return true;
                    }
                    return false;
                } catch (Exception unused) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            default:
                return null;
        }
    }

    /* access modifiers changed from: package-private */
    public int getStatus(int i) {
        CameraCacheInfo cameraCacheInfo2 = getCameraCacheInfo(i);
        if (cameraCacheInfo2 != null) {
            return cameraCacheInfo2.status;
        }
        SystemTools.setSystemErrorCode(4);
        return AR_CAMERA_STATUS_UNKNOWN;
    }
}
