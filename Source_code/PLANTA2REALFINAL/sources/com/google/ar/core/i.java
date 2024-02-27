package com.google.ar.core;

import java.util.Map;

/* compiled from: FaceCache */
final class i {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Long, AugmentedFace> f31a = new j(1, 0.75f, true);

    i() {
    }

    public final synchronized AugmentedFace a(long j, Session session) {
        AugmentedFace augmentedFace;
        augmentedFace = this.f31a.get(Long.valueOf(j));
        if (augmentedFace == null) {
            augmentedFace = new AugmentedFace(j, session);
            this.f31a.put(Long.valueOf(j), augmentedFace);
        }
        return augmentedFace;
    }
}
