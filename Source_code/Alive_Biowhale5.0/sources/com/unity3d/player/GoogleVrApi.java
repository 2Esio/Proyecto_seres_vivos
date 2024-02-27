package com.unity3d.player;

import java.util.concurrent.atomic.AtomicReference;

public class GoogleVrApi {

    /* renamed from: a  reason: collision with root package name */
    private static AtomicReference f50a = new AtomicReference();

    private GoogleVrApi() {
    }

    static void a() {
        f50a.set((Object) null);
    }

    static void a(f fVar) {
        f50a.compareAndSet((Object) null, new GoogleVrProxy(fVar));
    }

    static GoogleVrProxy b() {
        return (GoogleVrProxy) f50a.get();
    }

    public static GoogleVrVideo getGoogleVrVideo() {
        return (GoogleVrVideo) f50a.get();
    }
}
