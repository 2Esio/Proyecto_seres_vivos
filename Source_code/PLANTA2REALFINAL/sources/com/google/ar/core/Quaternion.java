package com.google.ar.core;

class Quaternion {

    /* renamed from: a  reason: collision with root package name */
    public static final Quaternion f10a = new Quaternion();
    private float w = 1.0f;
    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;

    public Quaternion() {
        a(0.0f, 0.0f, 0.0f, 1.0f);
    }

    private Quaternion(Quaternion quaternion) {
        a(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }

    public Quaternion(float f, float f2, float f3, float f4) {
        a(f, f2, f3, f4);
    }

    private final void a(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.w = f4;
    }

    public final float a() {
        return this.x;
    }

    public final float b() {
        return this.y;
    }

    public final float c() {
        return this.z;
    }

    public final float d() {
        return this.w;
    }

    public final void a(float[] fArr, int i) {
        fArr[i] = this.x;
        fArr[i + 1] = this.y;
        fArr[i + 2] = this.z;
        fArr[i + 3] = this.w;
    }

    public final Quaternion e() {
        return new Quaternion(-this.x, -this.y, -this.z, this.w);
    }

    public final Quaternion a(Quaternion quaternion) {
        Quaternion quaternion2 = new Quaternion();
        float f = this.x;
        float f2 = quaternion.w;
        float f3 = this.y;
        float f4 = quaternion.z;
        float f5 = this.z;
        float f6 = quaternion.y;
        float f7 = this.w;
        quaternion2.x = (((f * f2) + (f3 * f4)) - (f5 * f6)) + (quaternion.x * f7);
        float f8 = this.x;
        float f9 = ((-f8) * f4) + (f3 * f2);
        float f10 = quaternion.x;
        quaternion2.y = f9 + (f5 * f10) + (f6 * f7);
        float f11 = quaternion.y;
        float f12 = this.y;
        quaternion2.z = ((f8 * f11) - (f12 * f10)) + (f5 * f2) + (f4 * f7);
        quaternion2.w = ((((-f8) * f10) - (f12 * f11)) - (this.z * quaternion.z)) + (f7 * f2);
        return quaternion2;
    }

    public static Quaternion a(Quaternion quaternion, Quaternion quaternion2, float f) {
        float f2;
        Quaternion quaternion3 = new Quaternion();
        float f3 = (quaternion.x * quaternion2.x) + (quaternion.y * quaternion2.y) + (quaternion.z * quaternion2.z) + (quaternion.w * quaternion2.w);
        if (f3 < 0.0f) {
            Quaternion quaternion4 = new Quaternion(quaternion2);
            f3 = -f3;
            quaternion4.x = -quaternion4.x;
            quaternion4.y = -quaternion4.y;
            quaternion4.z = -quaternion4.z;
            quaternion4.w = -quaternion4.w;
            quaternion2 = quaternion4;
        }
        float acos = (float) Math.acos((double) f3);
        float sqrt = (float) Math.sqrt((double) (1.0f - (f3 * f3)));
        if (((double) Math.abs(sqrt)) > 0.001d) {
            float f4 = 1.0f / sqrt;
            f2 = ((float) Math.sin((double) ((1.0f - f) * acos))) * f4;
            f = ((float) Math.sin((double) (f * acos))) * f4;
        } else {
            f2 = 1.0f - f;
        }
        float f5 = (quaternion.x * f2) + (quaternion2.x * f);
        quaternion3.x = f5;
        float f6 = (quaternion.y * f2) + (quaternion2.y * f);
        quaternion3.y = f6;
        float f7 = (quaternion.z * f2) + (quaternion2.z * f);
        quaternion3.z = f7;
        float f8 = (f2 * quaternion.w) + (f * quaternion2.w);
        quaternion3.w = f8;
        float sqrt2 = (float) (1.0d / Math.sqrt((double) ((((f5 * f5) + (f6 * f6)) + (f7 * f7)) + (f8 * f8))));
        quaternion3.x *= sqrt2;
        quaternion3.y *= sqrt2;
        quaternion3.z *= sqrt2;
        quaternion3.w *= sqrt2;
        return quaternion3;
    }

    public final void a(float[] fArr, int i, int i2) {
        float f = this.x;
        float f2 = this.y;
        float f3 = (f * f) + (f2 * f2);
        float f4 = this.z;
        float f5 = f3 + (f4 * f4);
        float f6 = this.w;
        float f7 = f5 + (f6 * f6);
        float f8 = 0.0f;
        if (f7 > 0.0f) {
            f8 = 2.0f / f7;
        }
        float f9 = this.x;
        float f10 = f9 * f8;
        float f11 = this.y;
        float f12 = f11 * f8;
        float f13 = this.z;
        float f14 = f8 * f13;
        float f15 = this.w;
        float f16 = f15 * f10;
        float f17 = f15 * f12;
        float f18 = f15 * f14;
        float f19 = f10 * f9;
        float f20 = f9 * f12;
        float f21 = f9 * f14;
        float f22 = f12 * f11;
        float f23 = f11 * f14;
        float f24 = f13 * f14;
        fArr[i] = 1.0f - (f22 + f24);
        fArr[i + 4] = f20 - f18;
        fArr[i + 8] = f21 + f17;
        int i3 = i + 1;
        fArr[i3] = f20 + f18;
        fArr[i3 + 4] = 1.0f - (f24 + f19);
        fArr[i3 + 8] = f23 - f16;
        int i4 = i + 2;
        fArr[i4] = f21 - f17;
        fArr[i4 + 4] = f23 + f16;
        fArr[i4 + 8] = 1.0f - (f19 + f22);
    }

    public static void a(Quaternion quaternion, float[] fArr, int i, float[] fArr2, int i2) {
        float f = fArr[i];
        float f2 = fArr[i + 1];
        float f3 = fArr[i + 2];
        float f4 = quaternion.x;
        float f5 = quaternion.y;
        float f6 = quaternion.z;
        float f7 = quaternion.w;
        float f8 = ((f7 * f) + (f5 * f3)) - (f6 * f2);
        float f9 = ((f7 * f2) + (f6 * f)) - (f4 * f3);
        float f10 = ((f7 * f3) + (f4 * f2)) - (f5 * f);
        float f11 = -f4;
        float f12 = ((f * f11) - (f2 * f5)) - (f3 * f6);
        float f13 = -f6;
        float f14 = -f5;
        fArr2[i2] = (((f8 * f7) + (f12 * f11)) + (f9 * f13)) - (f10 * f14);
        fArr2[i2 + 1] = (((f9 * f7) + (f12 * f14)) + (f10 * f11)) - (f8 * f13);
        fArr2[i2 + 2] = (((f10 * f7) + (f12 * f13)) + (f8 * f14)) - (f9 * f11);
    }

    public String toString() {
        return String.format("[%.3f, %.3f, %.3f, %.3f]", new Object[]{Float.valueOf(this.x), Float.valueOf(this.y), Float.valueOf(this.z), Float.valueOf(this.w)});
    }
}
