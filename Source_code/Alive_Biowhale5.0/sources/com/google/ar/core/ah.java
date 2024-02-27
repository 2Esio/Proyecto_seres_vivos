package com.google.ar.core;

/* compiled from: Session */
enum ah {
    BASE_TRACKABLE(1095893248, Trackable.class),
    UNKNOWN_TO_JAVA(-1, (int) null),
    PLANE(1095893249, Plane.class),
    POINT(1095893250, Point.class),
    AUGMENTED_IMAGE(1095893252, AugmentedImage.class),
    FACE(1095893253, AugmentedFace.class);
    
    final int g;
    private final Class<?> h;

    private ah(int i2, Class<? extends Trackable> cls) {
        this.g = i2;
        this.h = cls;
    }

    public static ah a(Class<? extends Trackable> cls) {
        for (ah ahVar : values()) {
            Class<?> cls2 = ahVar.h;
            if (cls2 != null && cls2.equals(cls)) {
                return ahVar;
            }
        }
        return UNKNOWN_TO_JAVA;
    }
}
