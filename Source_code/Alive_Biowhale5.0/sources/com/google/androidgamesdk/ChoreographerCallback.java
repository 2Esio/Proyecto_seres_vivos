package com.google.androidgamesdk;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Choreographer;

public class ChoreographerCallback implements Choreographer.FrameCallback {
    private static final String LOG_TAG = "ChoreographerCallback";
    private long mCookie;
    private a mLooper = new a(this, (byte) 0);

    private class a extends Thread {

        /* renamed from: a  reason: collision with root package name */
        public Handler f3a;

        private a() {
        }

        /* synthetic */ a(ChoreographerCallback choreographerCallback, byte b2) {
            this();
        }

        public final void run() {
            Log.i(ChoreographerCallback.LOG_TAG, "Starting looper thread");
            Looper.prepare();
            this.f3a = new Handler();
            Looper.loop();
            Log.i(ChoreographerCallback.LOG_TAG, "Terminating looper thread");
        }
    }

    public ChoreographerCallback(long j) {
        this.mCookie = j;
        this.mLooper.start();
    }

    public void doFrame(long j) {
        nOnChoreographer(this.mCookie, j);
    }

    public native void nOnChoreographer(long j, long j2);

    public void postFrameCallback() {
        this.mLooper.f3a.post(new Runnable() {
            public final void run() {
                Choreographer.getInstance().postFrameCallback(ChoreographerCallback.this);
            }
        });
    }

    public void postFrameCallbackDelayed(long j) {
        Choreographer.getInstance().postFrameCallbackDelayed(this, j);
    }

    public void terminate() {
        this.mLooper.f3a.getLooper().quit();
    }
}
