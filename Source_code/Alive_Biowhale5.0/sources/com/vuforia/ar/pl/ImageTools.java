package com.vuforia.ar.pl;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.YuvImage;
import java.io.ByteArrayOutputStream;

public class ImageTools {
    private static final int CAMERA_IMAGE_FORMAT_LUM = 268439809;
    private static final int CAMERA_IMAGE_FORMAT_NV12 = 268439815;
    private static final int CAMERA_IMAGE_FORMAT_NV21 = 268439817;
    private static final int CAMERA_IMAGE_FORMAT_RGB565 = 268439810;
    private static final int CAMERA_IMAGE_FORMAT_RGB888 = 268439811;
    private static final int CAMERA_IMAGE_FORMAT_RGBA8888 = 268439812;
    private static final String MODULENAME = "ImageTools";

    public static byte[] encodeImage(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        if (bArr == null) {
            return null;
        }
        if (i3 == CAMERA_IMAGE_FORMAT_NV21) {
            YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, (int[]) null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (yuvImage.compressToJpeg(new Rect(0, 0, i, i2), i5, byteArrayOutputStream)) {
                return byteArrayOutputStream.toByteArray();
            }
            return null;
        } else if (i3 != CAMERA_IMAGE_FORMAT_LUM && i3 != CAMERA_IMAGE_FORMAT_RGB888 && i3 != CAMERA_IMAGE_FORMAT_RGBA8888) {
            return null;
        } else {
            int[] iArr = new int[(i * i2)];
            switch (i3) {
                case CAMERA_IMAGE_FORMAT_LUM /*268439809*/:
                    int i6 = i4 - i;
                    for (int i7 = 0; i7 < i2; i7++) {
                        for (int i8 = 0; i8 < i; i8++) {
                            int i9 = (i * i7) + i8;
                            iArr[i9] = (bArr[(i7 * i6) + i9] << 24) | 16777215;
                        }
                    }
                    break;
                case CAMERA_IMAGE_FORMAT_RGB888 /*268439811*/:
                    int i10 = i4 - (i * 3);
                    for (int i11 = 0; i11 < i2; i11++) {
                        for (int i12 = 0; i12 < i; i12++) {
                            int i13 = (i * i11) + i12;
                            int i14 = (i13 * 3) + (i11 * i10);
                            int i15 = i14 + 1;
                            iArr[i13] = ((bArr[i14] & 255) << 16) | -16777216 | ((bArr[i15] & 255) << 8) | (bArr[i15 + 1] & 255);
                        }
                    }
                    break;
                case CAMERA_IMAGE_FORMAT_RGBA8888 /*268439812*/:
                    int i16 = i4 - (i * 4);
                    for (int i17 = 0; i17 < i2; i17++) {
                        for (int i18 = 0; i18 < i; i18++) {
                            int i19 = (i * i17) + i18;
                            int i20 = (i19 * 4) + (i17 * i16);
                            int i21 = i20 + 1;
                            int i22 = i21 + 1;
                            iArr[i19] = ((bArr[i20] & 255) << 16) | ((bArr[i21] & 255) << 8) | (bArr[i22] & 255) | ((bArr[i22 + 1] & 255) << 24);
                        }
                    }
                    break;
                default:
                    return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(iArr, 0, i, i, i2, Bitmap.Config.ARGB_8888);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            if (createBitmap.compress(Bitmap.CompressFormat.JPEG, i5, byteArrayOutputStream2)) {
                return byteArrayOutputStream2.toByteArray();
            }
            return null;
        }
    }
}
