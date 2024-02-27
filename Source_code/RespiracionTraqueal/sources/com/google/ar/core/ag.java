package com.google.ar.core;

import com.google.ar.core.Session;

/* 'enum' modifier removed */
/* compiled from: Session */
final class ag extends Session.b {
    ag(String str, int i, int i2, Class cls) {
        super(str, 2, 1095893249, cls, (byte) 0);
    }

    public final Trackable a(long j, Session session) {
        return new Plane(j, session);
    }
}
