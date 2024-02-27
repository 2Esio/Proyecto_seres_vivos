package com.unity3d.player;

import android.content.Context;
import com.unity3d.player.a;

public class AudioVolumeHandler implements a.b {

    /* renamed from: a  reason: collision with root package name */
    private a f52a;

    AudioVolumeHandler(Context context) {
        a aVar = new a(context);
        this.f52a = aVar;
        aVar.a(this);
    }

    public final void a() {
        this.f52a.a();
        this.f52a = null;
    }

    public final native void onAudioVolumeChanged(int i);
}
