package com.vuforia.ar.pl;

public class ImageTools {
    private static final int CAMERA_IMAGE_FORMAT_LUM = 268439809;
    private static final int CAMERA_IMAGE_FORMAT_NV12 = 268439815;
    private static final int CAMERA_IMAGE_FORMAT_NV21 = 268439817;
    private static final int CAMERA_IMAGE_FORMAT_RGB565 = 268439810;
    private static final int CAMERA_IMAGE_FORMAT_RGB888 = 268439811;
    private static final int CAMERA_IMAGE_FORMAT_RGBA8888 = 268439812;
    private static final String MODULENAME = "ImageTools";

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] encodeImage(byte[] r8, int r9, int r10, int r11, int r12, int r13) {
        /*
            r12 = 0
            if (r8 != 0) goto L_0x0004
            return r12
        L_0x0004:
            r0 = 268439817(0x10001109, float:2.5256672E-29)
            r1 = 0
            if (r11 != r0) goto L_0x002c
            android.graphics.YuvImage r11 = new android.graphics.YuvImage
            r4 = 17
            r7 = 0
            r2 = r11
            r3 = r8
            r5 = r9
            r6 = r10
            r2.<init>(r3, r4, r5, r6, r7)
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream
            r8.<init>()
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>(r1, r1, r9, r10)
            boolean r9 = r11.compressToJpeg(r0, r13, r8)
            if (r9 == 0) goto L_0x002b
            byte[] r8 = r8.toByteArray()
            return r8
        L_0x002b:
            return r12
        L_0x002c:
            r0 = 268439809(0x10001101, float:2.5256648E-29)
            if (r11 == r0) goto L_0x003d
            r0 = 268439811(0x10001103, float:2.5256654E-29)
            if (r11 == r0) goto L_0x003d
            r0 = 268439812(0x10001104, float:2.5256657E-29)
            if (r11 != r0) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            return r12
        L_0x003d:
            int r0 = r9 * r10
            int[] r2 = new int[r0]
            switch(r11) {
                case 268439809: goto L_0x0096;
                case 268439810: goto L_0x0045;
                case 268439811: goto L_0x0071;
                case 268439812: goto L_0x0046;
                default: goto L_0x0045;
            }
        L_0x0045:
            return r12
        L_0x0046:
            r11 = 0
        L_0x0047:
            if (r1 >= r0) goto L_0x0070
            int r3 = r11 + 1
            byte r11 = r8[r11]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << 24
            int r4 = r3 + 1
            byte r3 = r8[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 16
            r11 = r11 | r3
            int r3 = r4 + 1
            byte r4 = r8[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 8
            r11 = r11 | r4
            int r4 = r3 + 1
            byte r3 = r8[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r11 = r11 | r3
            r2[r1] = r11
            int r1 = r1 + 1
            r11 = r4
            goto L_0x0047
        L_0x0070:
            goto L_0x00a7
        L_0x0071:
            r11 = 0
        L_0x0072:
            if (r1 >= r0) goto L_0x0095
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            int r4 = r11 + 1
            byte r11 = r8[r11]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << 16
            r11 = r11 | r3
            int r3 = r4 + 1
            byte r4 = r8[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 8
            r11 = r11 | r4
            int r4 = r3 + 1
            byte r3 = r8[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r11 = r11 | r3
            r2[r1] = r11
            int r1 = r1 + 1
            r11 = r4
            goto L_0x0072
        L_0x0095:
            goto L_0x00a7
        L_0x0096:
        L_0x0097:
            if (r1 >= r0) goto L_0x00a6
            byte r11 = r8[r1]
            int r11 = r11 << 24
            r3 = 16777215(0xffffff, float:2.3509886E-38)
            r11 = r11 | r3
            r2[r1] = r11
            int r1 = r1 + 1
            goto L_0x0097
        L_0x00a6:
        L_0x00a7:
            r3 = 0
            android.graphics.Bitmap$Config r7 = android.graphics.Bitmap.Config.ARGB_8888
            r4 = r9
            r5 = r9
            r6 = r10
            android.graphics.Bitmap r8 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7)
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream
            r9.<init>()
            android.graphics.Bitmap$CompressFormat r10 = android.graphics.Bitmap.CompressFormat.JPEG
            boolean r8 = r8.compress(r10, r13, r9)
            if (r8 == 0) goto L_0x00c3
            byte[] r8 = r9.toByteArray()
            return r8
        L_0x00c3:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.ImageTools.encodeImage(byte[], int, int, int, int, int):byte[]");
    }
}
