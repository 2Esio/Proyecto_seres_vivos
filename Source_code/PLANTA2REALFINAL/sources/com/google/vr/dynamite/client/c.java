package com.google.vr.dynamite.client;

/* compiled from: LoaderException */
public final class c extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private final int f48a = 1;

    public c(int i) {
    }

    public final String getMessage() {
        int i = this.f48a;
        String str = i != 1 ? i != 2 ? "Unknown error" : "Package obsolete" : "Package not available";
        StringBuilder sb = new StringBuilder(str.length() + 17);
        sb.append("LoaderException{");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
