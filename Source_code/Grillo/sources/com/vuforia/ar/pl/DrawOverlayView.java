package com.vuforia.ar.pl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import java.nio.ByteBuffer;

public class DrawOverlayView extends View {
    private static final String MODULENAME = "DrawOverlayView";
    private Drawable drawable = null;
    private double mLeft;
    private float[] mScale;
    private int[] mSize;
    private double mTop;
    private DisplayMetrics metrics;
    private Bitmap overlayBitmap;

    public DrawOverlayView(Context context) {
        super(context);
    }

    public DrawOverlayView(Context context, byte[] bArr, int i, int i2, float[] fArr, int[] iArr) {
        super(context);
        this.mLeft = (double) i;
        this.mTop = (double) i2;
        this.mScale = fArr;
        this.mSize = iArr;
        byte[] bArr2 = new byte[(bArr.length * 4)];
        int i3 = 0;
        while (true) {
            int[] iArr2 = this.mSize;
            if (i3 < iArr2[0] * iArr2[1]) {
                int i4 = i3 * 4;
                bArr2[i4] = bArr[i3];
                bArr2[i4 + 1] = bArr[i3];
                bArr2[i4 + 2] = bArr[i3];
                bArr2[i4 + 3] = -1;
                i3++;
            } else {
                this.overlayBitmap = Bitmap.createBitmap(iArr2[0], iArr2[1], Bitmap.Config.ARGB_8888);
                this.overlayBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr2));
                this.drawable = new BitmapDrawable(this.overlayBitmap);
                this.metrics = new DisplayMetrics();
                ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.overlayBitmap == null) {
            super.dispatchDraw(canvas);
            return;
        }
        double d = (double) this.metrics.heightPixels;
        double intrinsicHeight = (double) (((float) this.drawable.getIntrinsicHeight()) * this.mScale[1]);
        Double.isNaN(d);
        Double.isNaN(intrinsicHeight);
        double d2 = d - intrinsicHeight;
        if (d2 < this.mTop) {
            this.mTop = d2;
        }
        double d3 = this.mLeft;
        double intrinsicWidth = (double) (((float) this.drawable.getIntrinsicWidth()) * this.metrics.density * this.mScale[0]);
        Double.isNaN(intrinsicWidth);
        double d4 = this.mTop;
        double intrinsicHeight2 = (double) (((float) this.drawable.getIntrinsicHeight()) * this.metrics.density * this.mScale[1]);
        Double.isNaN(intrinsicHeight2);
        this.drawable.setBounds((int) this.mLeft, (int) this.mTop, (int) (d3 + intrinsicWidth), (int) (d4 + intrinsicHeight2));
        this.drawable.setAlpha(100);
        this.drawable.draw(canvas);
        super.dispatchDraw(canvas);
    }

    public void addOverlay(Activity activity) {
        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
        setVisibility(0);
    }

    public void removeOverlay(Activity activity, View view) {
        try {
            ((ViewGroup) activity.getWindow().getDecorView()).removeView(view);
        } catch (Exception e) {
        }
    }
}
