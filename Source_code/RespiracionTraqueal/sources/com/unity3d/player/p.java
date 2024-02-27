package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;
import java.io.FileInputStream;
import java.io.IOException;

public final class p extends FrameLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static boolean f126a = false;
    private final Context b;
    private final SurfaceView c;
    private final SurfaceHolder d;
    private final String e;
    private final int f;
    private final int g;
    private final boolean h;
    private final long i;
    private final long j;
    private final FrameLayout k;
    private final Display l;
    private int m;
    private int n;
    private int o;
    private int p;
    private MediaPlayer q;
    private MediaController r;
    private boolean s = false;
    private boolean t = false;
    private int u = 0;
    private boolean v = false;
    private boolean w = false;
    private a x;
    private b y;
    private volatile int z = 0;

    public interface a {
        void a(int i);
    }

    public class b implements Runnable {
        private p b;
        private boolean c = false;

        public b(p pVar) {
            this.b = pVar;
        }

        public final void a() {
            this.c = true;
        }

        public final void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (!this.c) {
                if (p.f126a) {
                    p.b("Stopping the video player due to timeout.");
                }
                this.b.CancelOnPrepare();
            }
        }
    }

    protected p(Context context, String str, int i2, int i3, int i4, boolean z2, long j2, long j3, a aVar) {
        super(context);
        this.x = aVar;
        this.b = context;
        this.k = this;
        this.c = new SurfaceView(context);
        this.d = this.c.getHolder();
        this.d.addCallback(this);
        this.k.setBackgroundColor(i2);
        this.k.addView(this.c);
        this.l = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
        this.e = str;
        this.f = i3;
        this.g = i4;
        this.h = z2;
        this.i = j2;
        this.j = j3;
        if (f126a) {
            b("fileName: " + this.e);
        }
        if (f126a) {
            b("backgroundColor: " + i2);
        }
        if (f126a) {
            b("controlMode: " + this.f);
        }
        if (f126a) {
            b("scalingMode: " + this.g);
        }
        if (f126a) {
            b("isURL: " + this.h);
        }
        if (f126a) {
            b("videoOffset: " + this.i);
        }
        if (f126a) {
            b("videoLength: " + this.j);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private void a(int i2) {
        this.z = i2;
        a aVar = this.x;
        if (aVar != null) {
            aVar.a(this.z);
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str) {
        Log.i("Video", "VideoPlayer: " + str);
    }

    private void c() {
        FileInputStream fileInputStream;
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer != null) {
            mediaPlayer.setDisplay(this.d);
            if (!this.v) {
                if (f126a) {
                    b("Resuming playback");
                }
                this.q.start();
                return;
            }
            return;
        }
        a(0);
        doCleanUp();
        try {
            this.q = new MediaPlayer();
            if (this.h) {
                this.q.setDataSource(this.b, Uri.parse(this.e));
            } else {
                if (this.j != 0) {
                    fileInputStream = new FileInputStream(this.e);
                    this.q.setDataSource(fileInputStream.getFD(), this.i, this.j);
                } else {
                    try {
                        AssetFileDescriptor openFd = getResources().getAssets().openFd(this.e);
                        this.q.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                        openFd.close();
                    } catch (IOException e2) {
                        fileInputStream = new FileInputStream(this.e);
                        this.q.setDataSource(fileInputStream.getFD());
                    }
                }
                fileInputStream.close();
            }
            this.q.setDisplay(this.d);
            this.q.setScreenOnWhilePlaying(true);
            this.q.setOnBufferingUpdateListener(this);
            this.q.setOnCompletionListener(this);
            this.q.setOnPreparedListener(this);
            this.q.setOnVideoSizeChangedListener(this);
            this.q.setAudioStreamType(3);
            this.q.prepareAsync();
            this.y = new b(this);
            new Thread(this.y).start();
        } catch (Exception e3) {
            if (f126a) {
                b("error: " + e3.getMessage() + e3);
            }
            a(2);
        }
    }

    private void d() {
        if (!isPlaying()) {
            a(1);
            if (f126a) {
                b("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.v) {
                start();
            }
        }
    }

    public final void CancelOnPrepare() {
        a(2);
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        return this.v;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void destroyPlayer() {
        if (f126a) {
            b("destroyPlayer");
        }
        if (!this.v) {
            pause();
        }
        doCleanUp();
    }

    /* access modifiers changed from: protected */
    public final void doCleanUp() {
        b bVar = this.y;
        if (bVar != null) {
            bVar.a();
            this.y = null;
        }
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.q = null;
        }
        this.o = 0;
        this.p = 0;
        this.t = false;
        this.s = false;
    }

    public final int getAudioSessionId() {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getAudioSessionId();
    }

    public final int getBufferPercentage() {
        if (this.h) {
            return this.u;
        }
        return 100;
    }

    public final int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    public final int getDuration() {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getDuration();
    }

    public final boolean isPlaying() {
        boolean z2 = this.t && this.s;
        MediaPlayer mediaPlayer = this.q;
        return mediaPlayer == null ? !z2 : mediaPlayer.isPlaying() || !z2;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
        if (f126a) {
            b("onBufferingUpdate percent:" + i2);
        }
        this.u = i2;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f126a) {
            b("onCompletion called");
        }
        destroyPlayer();
        a(3);
    }

    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4 || (this.f == 2 && i2 != 0 && !keyEvent.isSystem())) {
            destroyPlayer();
            a(3);
            return true;
        }
        MediaController mediaController = this.r;
        return mediaController != null ? mediaController.onKeyDown(i2, keyEvent) : super.onKeyDown(i2, keyEvent);
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f126a) {
            b("onPrepared called");
        }
        b bVar = this.y;
        if (bVar != null) {
            bVar.a();
            this.y = null;
        }
        int i2 = this.f;
        if (i2 == 0 || i2 == 1) {
            this.r = new MediaController(this.b);
            this.r.setMediaPlayer(this);
            this.r.setAnchorView(this);
            this.r.setEnabled(true);
            Context context = this.b;
            if (context instanceof Activity) {
                this.r.setSystemUiVisibility(((Activity) context).getWindow().getDecorView().getSystemUiVisibility());
            }
            this.r.show();
        }
        this.t = true;
        if (this.t && this.s) {
            d();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f == 2 && action == 0) {
            destroyPlayer();
            a(3);
            return true;
        }
        MediaController mediaController = this.r;
        return mediaController != null ? mediaController.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
        if (f126a) {
            b("onVideoSizeChanged called " + i2 + "x" + i3);
        }
        if (i2 != 0 && i3 != 0) {
            this.s = true;
            this.o = i2;
            this.p = i3;
            if (this.t && this.s) {
                d();
            }
        } else if (f126a) {
            b("invalid video width(" + i2 + ") or height(" + i3 + ")");
        }
    }

    public final void pause() {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer != null) {
            if (this.w) {
                mediaPlayer.pause();
            }
            this.v = true;
        }
    }

    public final void seekTo(int i2) {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(i2);
        }
    }

    public final void start() {
        if (f126a) {
            b("Start");
        }
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer != null) {
            if (this.w) {
                mediaPlayer.start();
            }
            this.v = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        if (f126a) {
            b("surfaceChanged called " + i2 + " " + i3 + "x" + i4);
        }
        if (this.m != i3 || this.n != i4) {
            this.m = i3;
            this.n = i4;
            if (this.w) {
                updateVideoLayout();
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f126a) {
            b("surfaceCreated called");
        }
        this.w = true;
        c();
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f126a) {
            b("surfaceDestroyed called");
        }
        this.w = false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        if (r5 <= r3) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        r0 = (int) (((float) r1) * r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005d, code lost:
        if (r5 >= r3) goto L_0x004f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateVideoLayout() {
        /*
            r8 = this;
            boolean r0 = f126a
            if (r0 == 0) goto L_0x0009
            java.lang.String r0 = "updateVideoLayout"
            b(r0)
        L_0x0009:
            android.media.MediaPlayer r0 = r8.q
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            int r0 = r8.m
            if (r0 == 0) goto L_0x0016
            int r0 = r8.n
            if (r0 != 0) goto L_0x0034
        L_0x0016:
            android.content.Context r0 = r8.b
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.util.DisplayMetrics r1 = new android.util.DisplayMetrics
            r1.<init>()
            android.view.Display r0 = r0.getDefaultDisplay()
            r0.getMetrics(r1)
            int r0 = r1.widthPixels
            r8.m = r0
            int r0 = r1.heightPixels
            r8.n = r0
        L_0x0034:
            int r0 = r8.m
            int r1 = r8.n
            boolean r2 = r8.s
            if (r2 == 0) goto L_0x0065
            int r2 = r8.o
            float r3 = (float) r2
            int r4 = r8.p
            float r5 = (float) r4
            float r3 = r3 / r5
            float r5 = (float) r0
            float r6 = (float) r1
            float r5 = r5 / r6
            int r6 = r8.g
            r7 = 1
            if (r6 != r7) goto L_0x0058
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0053
        L_0x004f:
            float r1 = (float) r0
            float r1 = r1 / r3
            int r1 = (int) r1
            goto L_0x006e
        L_0x0053:
            float r0 = (float) r1
            float r0 = r0 * r3
            int r0 = (int) r0
            goto L_0x006e
        L_0x0058:
            r7 = 2
            if (r6 != r7) goto L_0x0060
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 < 0) goto L_0x0053
            goto L_0x004f
        L_0x0060:
            if (r6 != 0) goto L_0x006e
            r0 = r2
            r1 = r4
            goto L_0x006e
        L_0x0065:
            boolean r2 = f126a
            if (r2 == 0) goto L_0x006e
            java.lang.String r2 = "updateVideoLayout: Video size is not known yet"
            b(r2)
        L_0x006e:
            int r2 = r8.m
            if (r2 != r0) goto L_0x0076
            int r2 = r8.n
            if (r2 == r1) goto L_0x00a1
        L_0x0076:
            boolean r2 = f126a
            if (r2 == 0) goto L_0x0093
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "frameWidth = "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r3 = "; frameHeight = "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            b(r2)
        L_0x0093:
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r3 = 17
            r2.<init>(r0, r1, r3)
            android.widget.FrameLayout r0 = r8.k
            android.view.SurfaceView r1 = r8.c
            r0.updateViewLayout(r1, r2)
        L_0x00a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.p.updateVideoLayout():void");
    }
}
