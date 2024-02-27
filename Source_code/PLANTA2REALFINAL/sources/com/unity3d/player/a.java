package com.unity3d.player;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

final class a {

    /* renamed from: a  reason: collision with root package name */
    private final Context f101a;
    private final AudioManager b;
    private C0000a c;

    /* renamed from: com.unity3d.player.a$a  reason: collision with other inner class name */
    private class C0000a extends ContentObserver {
        private final b b;
        private final AudioManager c;
        private final int d = 3;
        private int e;

        public C0000a(Handler handler, AudioManager audioManager, int i, b bVar) {
            super(handler);
            this.c = audioManager;
            this.b = bVar;
            this.e = audioManager.getStreamVolume(3);
        }

        public final boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        public final void onChange(boolean z, Uri uri) {
            int streamVolume;
            AudioManager audioManager = this.c;
            if (audioManager != null && this.b != null && (streamVolume = audioManager.getStreamVolume(this.d)) != this.e) {
                this.e = streamVolume;
                this.b.onAudioVolumeChanged(streamVolume);
            }
        }
    }

    public interface b {
        void onAudioVolumeChanged(int i);
    }

    public a(Context context) {
        this.f101a = context;
        this.b = (AudioManager) context.getSystemService("audio");
    }

    public final void a() {
        if (this.c != null) {
            this.f101a.getContentResolver().unregisterContentObserver(this.c);
            this.c = null;
        }
    }

    public final void a(b bVar) {
        this.c = new C0000a(new Handler(), this.b, 3, bVar);
        this.f101a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.c);
    }
}
