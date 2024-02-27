package com.google.vr.dynamite.client;

import java.util.Objects;

/* compiled from: Version */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    private final int f51a;
    private final int b;
    private final int c;

    public f(int i, int i2, int i3) {
        this.f51a = i;
        this.b = i2;
        this.c = i3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.f51a == fVar.f51a && this.b == fVar.b && this.c == fVar.c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.f51a), Integer.valueOf(this.b), Integer.valueOf(this.c)});
    }

    public final String toString() {
        return String.format("%d.%d.%d", new Object[]{Integer.valueOf(this.f51a), Integer.valueOf(this.b), Integer.valueOf(this.c)});
    }
}
