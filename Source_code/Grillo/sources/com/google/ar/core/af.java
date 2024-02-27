package com.google.ar.core;

import com.google.ar.core.Session;

/* 'enum' modifier removed */
/* compiled from: Session */
final class af extends Session.b {
    af(String str, int i, int i2, Class cls) {
        super(str, 3, 1095893250, cls, (byte) 0);
    }

    public final Trackable a(long j, Session session) {
        return new Point(j, session);
    }
}
