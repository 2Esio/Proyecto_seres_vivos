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
        } catch (Exception e) {
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
                } catch (Exception e) {
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
        } catch (Exception e) {
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
                } catch (JSONException e) {
                    return false;
                }
            }
            return true;
        } catch (JSONException e2) {
            return false;
        }
    }

    private boolean setCameraPreviewFps(int i, Camera.Parameters parameters) {
        int[] iArr;
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        int i2 = i * 1000;
        if ((i == 60 || i == 120) && "true".equalsIgnoreCase(parameters.get(SAMSUNG_PARAM_VRMODE_SUPPORTED))) {
            int[] iArr2 = new int[2];
            parameters.set(SAMSUNG_PARAM_VRMODE, 1);
            parameters.setRecordingHint(true);
            parameters.set("focus-mode", "continuous-video");
            if (i == 60) {
                parameters.set(SAMSUNG_PARAM_FAST_FPS_MODE, 1);
                iArr2[0] = 60000;
                iArr2[1] = 60000;
            }
            if (i == 120) {
                parameters.set(SAMSUNG_PARAM_FAST_FPS_MODE, 2);
                iArr2[0] = 120000;
                iArr2[1] = 120000;
            }
            iArr = iArr2;
        } else {
            if (!(!"true".equalsIgnoreCase(parameters.get(SAMSUNG_PARAM_VRMODE_SUPPORTED)) || parameters.get(SAMSUNG_PARAM_FAST_FPS_MODE) == null || parameters.getInt(SAMSUNG_PARAM_FAST_FPS_MODE) == 0)) {
                parameters.set(SAMSUNG_PARAM_VRMODE, 0);
                parameters.set(SAMSUNG_PARAM_FAST_FPS_MODE, 0);
            }
            iArr = null;
            for (int[] next : supportedPreviewFpsRange) {
                if (next[0] == i2 && next[1] - next[0] < Integer.MAX_VALUE) {
                    iArr = next;
                }
            }
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
                        } catch (Exception e) {
                        }
                    } catch (Exception e2) {
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
        } catch (Exception e3) {
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
            } catch (Exception e) {
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
            } catch (Exception e) {
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
        } catch (Exception e2) {
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
        int log = (int) (Math.log((double) (i2 & (i3 ^ -1))) / Math.log(2.0d));
        if (z) {
            int[] iArr = cameraCacheInfo2.caps;
            iArr[i] = (1 << log) | iArr[i];
            return;
        }
        int[] iArr2 = cameraCacheInfo2.caps;
        iArr2[i] = ((1 << log) ^ -1) & iArr2[i];
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
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
        } else {
            try {
                return SystemTools.getActivityFromNative().getPackageManager().hasSystemFeature("android.hardware.camera") ? 1 : 0;
            } catch (Exception e2) {
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
            } catch (Exception e) {
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
                if (i2 == 0) {
                    return AR_CAMERA_DIRECTION_BACK;
                }
                if (i2 != 1) {
                    return AR_CAMERA_DIRECTION_UNKNOWN;
                }
                return AR_CAMERA_DIRECTION_FRONT;
            } catch (Exception e) {
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
        boolean z2 = false;
        int i5 = NUM_MAX_CAMERAOPEN_RETRY;
        while (true) {
            try {
                if (SystemTools.checkMinimumApiLevel(9)) {
                    cameraCacheInfo2.camera = Camera.open(cameraCacheInfo2.deviceID);
                } else if (cameraCacheInfo2.deviceID == 0) {
                    cameraCacheInfo2.camera = Camera.open();
                }
                z2 = cameraCacheInfo2.camera != null;
            } catch (Exception e) {
            }
            if (!z2 && i5 > 0) {
                try {
                    synchronized (this) {
                        wait(250);
                    }
                } catch (Exception e2) {
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
                } catch (Exception e3) {
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
        } catch (Exception e) {
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
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_TORCHMODE, supportedFlashModes != null && (supportedFlashModes.contains("torch") || supportedFlashModes.contains("on")));
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_FOCUSMODE, true);
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_FOCUSREGION, SystemTools.checkMinimumApiLevel(14));
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_EXPOSUREVALUE, SystemTools.checkMinimumApiLevel(8));
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_ZOOMVALUE, SystemTools.checkMinimumApiLevel(8) && cameraParameters.isZoomSupported());
            setCameraCapsBit(cameraCacheInfo2, 1, AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION, SystemTools.checkMinimumApiLevel(15));
            cameraCacheInfo2.caps[2] = AR_CAMERA_PARAMVALUE_BASE;
            if (supportedFlashModes == null) {
                z = true;
            } else if (supportedFlashModes.contains("torch") || supportedFlashModes.contains("on")) {
                z = true;
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_TORCHMODE_OFF, true);
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_TORCHMODE_ON, true);
            } else {
                z = true;
            }
            if (supportedFocusModes != null) {
                setCameraCapsBit(cameraCacheInfo2, 2, AR_CAMERA_FOCUSMODE_NORMAL, z);
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
                i2 = 6;
                while (listIterator.hasNext()) {
                    Camera.Size next = listIterator.next();
                    cameraCacheInfo2.caps[i2] = next.width;
                    cameraCacheInfo2.caps[i2 + 1] = next.height;
                    i2 += 2;
                }
            } else {
                i2 = 6;
            }
            if (size2 > 0) {
                ListIterator<Integer> listIterator2 = supportedPreviewFrameRates.listIterator();
                while (listIterator2.hasNext()) {
                    cameraCacheInfo2.caps[i2] = listIterator2.next().intValue();
                    i2++;
                }
            }
            if (size3 > 0) {
                ListIterator<Integer> listIterator3 = supportedPreviewFormats.listIterator();
                while (listIterator3.hasNext()) {
                    cameraCacheInfo2.caps[i2] = translateImageFormat(listIterator3.next().intValue(), true);
                    i2++;
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
                } catch (Exception e) {
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
        } catch (Exception e) {
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
            } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setTypedCameraParameter(int r19, int r20, java.lang.Object r21) {
        /*
            r18 = this;
            r1 = r18
            r0 = r20
            java.lang.String r2 = "normal"
            java.lang.String r3 = "torch"
            com.vuforia.ar.pl.Camera1_Preview$CameraCacheInfo r4 = r18.getCameraCacheInfo(r19)
            r5 = 4
            r6 = 0
            if (r4 == 0) goto L_0x02f2
            android.hardware.Camera r7 = r4.camera
            if (r7 != 0) goto L_0x0016
            goto L_0x02f2
        L_0x0016:
            android.hardware.Camera r7 = r4.camera
            android.hardware.Camera$Parameters r7 = r1.getCameraParameters(r7)
            r8 = 6
            if (r7 != 0) goto L_0x0023
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)
            return r6
        L_0x0023:
            r9 = 8
            r10 = 14
            r11 = 0
            r12 = 3
            r13 = 1
            switch(r0) {
                case 536870913: goto L_0x0296;
                case 536870914: goto L_0x023c;
                case 536870916: goto L_0x0238;
                case 536870920: goto L_0x0234;
                case 536870928: goto L_0x017c;
                case 536870944: goto L_0x0150;
                case 536870976: goto L_0x0115;
                case 536871936: goto L_0x00ed;
                case 536872960: goto L_0x00e9;
                case 536875008: goto L_0x00a6;
                case 536879104: goto L_0x00a2;
                case 536887296: goto L_0x009e;
                case 536903680: goto L_0x0081;
                case 536936448: goto L_0x007d;
                case 537001984: goto L_0x0079;
                case 537133056: goto L_0x0075;
                case 537395200: goto L_0x0071;
                case 537919488: goto L_0x006d;
                case 538968064: goto L_0x0069;
                case 541065216: goto L_0x0043;
                case 553648128: goto L_0x002f;
                default: goto L_0x002e;
            }
        L_0x002e:
            return r6
        L_0x002f:
            r2 = r21
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Exception -> 0x02ed }
            boolean r2 = r2.booleanValue()     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x003e
            r7.setVideoStabilization(r13)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x003e:
            r7.setVideoStabilization(r6)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x0043:
            r2 = r21
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x02ed }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x02ed }
            boolean r3 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r10)     // Catch:{ Exception -> 0x02ed }
            if (r3 == 0) goto L_0x005b
            if (r2 == 0) goto L_0x0055
            r2 = 1
            goto L_0x0056
        L_0x0055:
            r2 = 0
        L_0x0056:
            r7.setRecordingHint(r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x005b:
            java.lang.String r3 = "recording-hint"
            if (r2 == 0) goto L_0x0062
            java.lang.String r2 = "true"
            goto L_0x0064
        L_0x0062:
            java.lang.String r2 = "false"
        L_0x0064:
            r7.set(r3, r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x0069:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x006d:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0071:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0075:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0079:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x007d:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0081:
            boolean r2 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r9)     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x009a
            boolean r2 = r7.isZoomSupported()     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x009a
            r2 = r21
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x02ed }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x02ed }
            r7.setZoom(r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x009a:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x009e:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x00a2:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x00a6:
            r2 = r21
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x02ed }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x02ed }
            r3 = 806354944(0x30100000, float:5.2386895E-10)
            if (r2 == r3) goto L_0x00de
            r3 = 809500672(0x30400000, float:6.9849193E-10)
            if (r2 == r3) goto L_0x00d3
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r12)     // Catch:{ Exception -> 0x02ed }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02ed }
            r0.<init>()     // Catch:{ Exception -> 0x02ed }
            java.lang.String r3 = "Cannot set unknown white balance mode ("
            r0.append(r3)     // Catch:{ Exception -> 0x02ed }
            r0.append(r2)     // Catch:{ Exception -> 0x02ed }
            java.lang.String r2 = ")"
            r0.append(r2)     // Catch:{ Exception -> 0x02ed }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02ed }
            com.vuforia.ar.pl.SystemTools.logSystemError(r0)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x00d3:
            boolean r2 = r7.isAutoWhiteBalanceLockSupported()     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x02c3
            r7.setAutoWhiteBalanceLock(r6)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x00de:
            boolean r2 = r7.isAutoWhiteBalanceLockSupported()     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x02c3
            r7.setAutoWhiteBalanceLock(r13)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x00e9:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x00ed:
            boolean r2 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r9)     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x0111
            r2 = r21
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x02ed }
            float r2 = r2.floatValue()     // Catch:{ Exception -> 0x02ed }
            float r3 = r7.getExposureCompensationStep()     // Catch:{ Exception -> 0x02ed }
            int r5 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r5 != 0) goto L_0x0107
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0107:
            float r2 = r2 / r3
            int r2 = java.lang.Math.round(r2)     // Catch:{ Exception -> 0x02ed }
            r7.setExposureCompensation(r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x0111:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0115:
            r2 = r21
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x02ed }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x02ed }
            java.lang.String r2 = java.lang.Integer.toString(r2)     // Catch:{ Exception -> 0x02ed }
            java.lang.String r3 = "iso-values"
            java.lang.String r3 = r7.get(r3)     // Catch:{ Exception -> 0x02ed }
            if (r3 == 0) goto L_0x0149
            java.lang.String r5 = ","
            java.lang.String[] r3 = r3.split(r5)     // Catch:{ Exception -> 0x02ed }
            r5 = 0
        L_0x0130:
            int r9 = r3.length     // Catch:{ Exception -> 0x02ed }
            if (r5 >= r9) goto L_0x0149
            r9 = r3[r5]     // Catch:{ Exception -> 0x02ed }
            java.lang.String r9 = r9.toLowerCase()     // Catch:{ Exception -> 0x02ed }
            java.lang.String r10 = r2.toLowerCase()     // Catch:{ Exception -> 0x02ed }
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x02ed }
            if (r9 == 0) goto L_0x0146
            r2 = r3[r5]     // Catch:{ Exception -> 0x02ed }
            goto L_0x0149
        L_0x0146:
            int r5 = r5 + 1
            goto L_0x0130
        L_0x0149:
            java.lang.String r3 = "iso"
            r7.set(r3, r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x0150:
            r2 = r21
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x02ed }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x02ed }
            r3 = 805310464(0x30001000, float:4.6588866E-10)
            if (r2 == r3) goto L_0x0171
            r3 = 805322752(0x30004000, float:4.665708E-10)
            if (r2 == r3) goto L_0x0166
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r12)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0166:
            boolean r2 = r7.isAutoExposureLockSupported()     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x02c3
            r7.setAutoExposureLock(r6)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x0171:
            boolean r2 = r7.isAutoExposureLockSupported()     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x02c3
            r7.setAutoExposureLock(r13)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x017c:
            boolean r2 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r10)     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x0230
            r2 = r21
            float[] r2 = (float[]) r2     // Catch:{ Exception -> 0x02ed }
            float[] r2 = (float[]) r2     // Catch:{ Exception -> 0x02ed }
            int r3 = r2.length     // Catch:{ Exception -> 0x02ed }
            r9 = 5
            r10 = 2
            if (r3 == r9) goto L_0x0191
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0191:
            r3 = r2[r6]     // Catch:{ Exception -> 0x02ed }
            int r3 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r3 < 0) goto L_0x022c
            r3 = r2[r6]     // Catch:{ Exception -> 0x02ed }
            r9 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 > 0) goto L_0x022c
            r3 = r2[r13]     // Catch:{ Exception -> 0x02ed }
            int r3 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r3 < 0) goto L_0x022c
            r3 = r2[r13]     // Catch:{ Exception -> 0x02ed }
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 > 0) goto L_0x022c
            r3 = r2[r10]     // Catch:{ Exception -> 0x02ed }
            int r3 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r3 < 0) goto L_0x022c
            r3 = r2[r10]     // Catch:{ Exception -> 0x02ed }
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 > 0) goto L_0x022c
            r3 = r2[r12]     // Catch:{ Exception -> 0x02ed }
            int r3 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r3 < 0) goto L_0x022c
            r3 = r2[r12]     // Catch:{ Exception -> 0x02ed }
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 > 0) goto L_0x022c
            r3 = r2[r5]     // Catch:{ Exception -> 0x02ed }
            int r3 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r3 < 0) goto L_0x022c
            r3 = r2[r5]     // Catch:{ Exception -> 0x02ed }
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x01d0
            goto L_0x022c
        L_0x01d0:
            android.graphics.Rect r3 = new android.graphics.Rect     // Catch:{ Exception -> 0x02ed }
            r9 = r2[r6]     // Catch:{ Exception -> 0x02ed }
            double r14 = (double) r9
            r16 = 4656510908468559872(0x409f400000000000, double:2000.0)
            java.lang.Double.isNaN(r14)
            double r14 = r14 * r16
            int r9 = (int) r14
            int r9 = r9 + -1000
            r11 = r2[r13]     // Catch:{ Exception -> 0x02ed }
            double r14 = (double) r11
            java.lang.Double.isNaN(r14)
            double r14 = r14 * r16
            int r11 = (int) r14
            int r11 = r11 + -1000
            r10 = r2[r10]     // Catch:{ Exception -> 0x02ed }
            double r14 = (double) r10
            java.lang.Double.isNaN(r14)
            double r14 = r14 * r16
            int r10 = (int) r14
            int r10 = r10 + -1000
            r12 = r2[r12]     // Catch:{ Exception -> 0x02ed }
            double r14 = (double) r12
            java.lang.Double.isNaN(r14)
            double r14 = r14 * r16
            int r12 = (int) r14
            int r12 = r12 + -1000
            r3.<init>(r9, r11, r10, r12)     // Catch:{ Exception -> 0x02ed }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Exception -> 0x02ed }
            r9.<init>()     // Catch:{ Exception -> 0x02ed }
            android.hardware.Camera$Area r10 = new android.hardware.Camera$Area     // Catch:{ Exception -> 0x02ed }
            r2 = r2[r5]     // Catch:{ Exception -> 0x02ed }
            double r11 = (double) r2
            r14 = 4652007308841189376(0x408f400000000000, double:1000.0)
            java.lang.Double.isNaN(r11)
            double r11 = r11 * r14
            int r2 = (int) r11
            r10.<init>(r3, r2)     // Catch:{ Exception -> 0x02ed }
            r9.add(r10)     // Catch:{ Exception -> 0x02ed }
            int r2 = r7.getMaxNumFocusAreas()     // Catch:{ Exception -> 0x02ed }
            if (r2 <= 0) goto L_0x022a
            r7.setFocusAreas(r9)     // Catch:{ Exception -> 0x02ed }
        L_0x022a:
            goto L_0x02c3
        L_0x022c:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0230:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0234:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x0238:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x023c:
            android.hardware.Camera r3 = r4.camera     // Catch:{ Exception -> 0x02ed }
            r3.cancelAutoFocus()     // Catch:{ Exception -> 0x02ed }
            r3 = r21
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ Exception -> 0x02ed }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x02ed }
            java.lang.String r5 = "auto"
            switch(r3) {
                case 805306384: goto L_0x0281;
                case 805306400: goto L_0x027b;
                case 805306432: goto L_0x0265;
                case 805306496: goto L_0x025f;
                case 805306624: goto L_0x0259;
                case 805306880: goto L_0x0252;
                default: goto L_0x024e;
            }
        L_0x024e:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r12)     // Catch:{ Exception -> 0x02ed }
            goto L_0x0295
        L_0x0252:
            java.lang.String r2 = "fixed"
            r7.setFocusMode(r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x0259:
            java.lang.String r2 = "infinity"
            r7.setFocusMode(r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x025f:
            java.lang.String r2 = "macro"
            r7.setFocusMode(r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x0265:
            java.util.List r2 = r7.getSupportedFocusModes()     // Catch:{ Exception -> 0x02ed }
            java.lang.String r3 = "continuous-video"
            boolean r2 = r2.contains(r3)     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x0277
            java.lang.String r2 = "continuous-video"
            r7.setFocusMode(r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x0277:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x027b:
            r7.setFocusMode(r5)     // Catch:{ Exception -> 0x02ed }
            r2 = 1
            goto L_0x02c4
        L_0x0281:
            java.util.List r3 = r7.getSupportedFocusModes()     // Catch:{ Exception -> 0x02ed }
            boolean r3 = r3.contains(r2)     // Catch:{ Exception -> 0x02ed }
            if (r3 == 0) goto L_0x028f
            r7.setFocusMode(r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x028f:
            r7.setFocusMode(r5)     // Catch:{ Exception -> 0x02ed }
            r2 = 1
            goto L_0x02c4
        L_0x0295:
            return r6
        L_0x0296:
            r2 = r21
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ Exception -> 0x02ed }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x02ed }
            switch(r2) {
                case 805306369: goto L_0x02bd;
                case 805306370: goto L_0x02a9;
                case 805306371: goto L_0x02a1;
                case 805306372: goto L_0x02a5;
                default: goto L_0x02a1;
            }     // Catch:{ Exception -> 0x02ed }
        L_0x02a1:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r12)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02ec
        L_0x02a5:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r12)     // Catch:{ Exception -> 0x02ed }
            return r6
        L_0x02a9:
            java.util.List r2 = r7.getSupportedFlashModes()     // Catch:{ Exception -> 0x02ed }
            boolean r2 = r2.contains(r3)     // Catch:{ Exception -> 0x02ed }
            if (r2 == 0) goto L_0x02b7
            r7.setFlashMode(r3)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x02b7:
            java.lang.String r2 = "on"
            r7.setFlashMode(r2)     // Catch:{ Exception -> 0x02ed }
            goto L_0x02c3
        L_0x02bd:
            java.lang.String r2 = "off"
            r7.setFlashMode(r2)     // Catch:{ Exception -> 0x02ed }
        L_0x02c3:
            r2 = 0
        L_0x02c4:
            android.hardware.Camera r3 = r4.camera     // Catch:{ Exception -> 0x02e7 }
            r3.setParameters(r7)     // Catch:{ Exception -> 0x02e7 }
            if (r2 == 0) goto L_0x02e6
            r2 = 536870914(0x20000002, float:1.0842024E-19)
            if (r0 == r2) goto L_0x02d3
            goto L_0x02e0
        L_0x02d3:
            r4.isAutoFocusing = r13     // Catch:{ Exception -> 0x02e1 }
            android.hardware.Camera r0 = r4.camera     // Catch:{ Exception -> 0x02e1 }
            com.vuforia.ar.pl.Camera1_Preview$1 r2 = new com.vuforia.ar.pl.Camera1_Preview$1     // Catch:{ Exception -> 0x02e1 }
            r2.<init>()     // Catch:{ Exception -> 0x02e1 }
            r0.autoFocus(r2)     // Catch:{ Exception -> 0x02e1 }
        L_0x02e0:
            goto L_0x02e6
        L_0x02e1:
            r0 = move-exception
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)
            return r6
        L_0x02e6:
            return r13
        L_0x02e7:
            r0 = move-exception
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)
            return r6
        L_0x02ec:
            return r6
        L_0x02ed:
            r0 = move-exception
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8)
            return r6
        L_0x02f2:
            com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r5)
            return r6
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
                } catch (Exception e) {
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
