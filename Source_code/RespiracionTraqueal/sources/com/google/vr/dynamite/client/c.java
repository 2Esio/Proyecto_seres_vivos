package com.google.vr.dynamite.client;

/* compiled from: LoaderException */
public final class c extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private final int f48a = 1;

    public c(int i) {
    }

    public final String getMessage() {
        String str;
        int i = this.f48a;
        if (i == 1) {
            str = "Package not available";
        } else if (i != 2) {
            str = "Unknown error";
        } else {
            str = "Package obsolete";
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 17);
        sb.append("LoaderException{");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
