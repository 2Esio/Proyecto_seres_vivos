package com.google.ar.core;

import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: FaceCache */
final class j extends LinkedHashMap<Long, AugmentedFace> {
    j(int i, float f, boolean z) {
        super(1, 0.75f, true);
    }

    /* access modifiers changed from: protected */
    public final boolean removeEldestEntry(Map.Entry<Long, AugmentedFace> entry) {
        return size() > 10;
    }
}
