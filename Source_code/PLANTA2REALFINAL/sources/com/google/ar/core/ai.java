package com.google.ar.core;

import com.google.ar.core.Session;

/* 'enum' modifier removed */
/* compiled from: Session */
final class ai extends Session.b {
    ai(String str, int i, int i2, Class cls) {
        super(str, 4, 1095893252, cls, (byte) 0);
    }

    public final Trackable a(long j, Session session) {
        return new AugmentedImage(j, session);
    }
}
