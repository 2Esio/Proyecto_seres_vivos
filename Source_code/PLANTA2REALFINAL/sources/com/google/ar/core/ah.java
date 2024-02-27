package com.google.ar.core;

import com.google.ar.core.Session;

/* 'enum' modifier removed */
/* compiled from: Session */
final class ah extends Session.b {
    ah(String str, int i, int i2, Class cls) {
        super(str, 5, 1095893253, cls, (byte) 0);
    }

    public final Trackable a(long j, Session session) {
        return session.faceCache.a(j, session);
    }
}
