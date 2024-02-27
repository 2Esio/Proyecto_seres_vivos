package com.vuforia.ar.pl;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {
    private static final String MODULENAME = "CameraSurface";
    Camera camera = null;
    SurfaceHolder surfaceHolder = getHolder();

    public void surfaceChanged(SurfaceHolder surfaceHolder2, int i, int i2, int i3) {
    }

    public CameraSurface(Context context) {
        super(context);
        this.surfaceHolder.addCallback(this);
        this.surfaceHolder.setType(3);
    }

    public void setCamera(Camera camera2) {
        this.camera = camera2;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder2) {
        try {
            if (this.camera != null) {
                this.camera.setPreviewDisplay(surfaceHolder2);
            }
        } catch (IOException unused) {
            this.camera = null;
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder2) {
        this.camera = null;
    }
}
