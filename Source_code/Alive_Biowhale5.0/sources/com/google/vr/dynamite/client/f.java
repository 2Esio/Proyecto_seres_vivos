package com.google.vr.dynamite.client;

import java.util.Objects;

/* compiled from: TargetLibraryInfo */
final class f {

    /* renamed from: a  reason: collision with root package name */
    private final String f47a;
    private final String b;

    public f(String str, String str2) {
        this.f47a = str;
        this.b = str2;
    }

    public final String a() {
        return this.f47a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            return Objects.equals(this.f47a, fVar.f47a) && Objects.equals(this.b, fVar.b);
        }
    }

    public final int hashCode() {
        return (Objects.hashCode(this.f47a) * 37) + Objects.hashCode(this.b);
    }

    public final String toString() {
        return "[packageName=" + this.f47a + ",libraryName=" + this.b + "]";
    }
}
