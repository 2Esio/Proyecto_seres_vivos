package com.google.vr.dynamite.client;

import java.util.Objects;

/* compiled from: TargetLibraryInfo */
final class d {

    /* renamed from: a  reason: collision with root package name */
    private final String f49a;
    private final String b;

    public d(String str, String str2) {
        this.f49a = str;
        this.b = str2;
    }

    public final String a() {
        return this.f49a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            return Objects.equals(this.f49a, dVar.f49a) && Objects.equals(this.b, dVar.b);
        }
    }

    public final int hashCode() {
        return (Objects.hashCode(this.f49a) * 37) + Objects.hashCode(this.b);
    }

    public final String toString() {
        return "[packageName=" + this.f49a + ",libraryName=" + this.b + "]";
    }
}
