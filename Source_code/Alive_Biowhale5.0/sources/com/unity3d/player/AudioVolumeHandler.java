package com.unity3d.player;

import android.content.Context;
import com.unity3d.player.a;

public class AudioVolumeHandler implements a.b {

    /* renamed from: a  reason: collision with root package name */
    private a f48a;

    AudioVolumeHandler(Context context) {
        this.f48a = new a(context);
        this.f48a.a(this);
    }

    public final void a() {
        this.f48a.a();
        this.f48a = null;
    }

    public final native void onAudioVolumeChanged(int i);
}
