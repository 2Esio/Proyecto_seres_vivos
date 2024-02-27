package com.google.ar.core;

import android.media.ImageReader;

final /* synthetic */ class ak implements ImageReader.OnImageAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    static final ImageReader.OnImageAvailableListener f18a = new ak();

    private ak() {
    }

    public final void onImageAvailable(ImageReader imageReader) {
        SharedCamera.lambda$setDummyOnImageAvailableListener$0$SharedCamera(imageReader);
    }
}
