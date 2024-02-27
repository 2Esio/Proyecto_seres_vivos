package com.google.vr.dynamite.client;

import android.content.Context;
import android.os.IBinder;
import java.lang.reflect.InvocationTargetException;

/* compiled from: RemoteLibraryLoader */
final class e {

    /* renamed from: a  reason: collision with root package name */
    private Context f46a;
    private ILoadedInstanceCreator b;
    private final f c;

    public e(f fVar) {
        this.c = fVar;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:4|5|6|7|8) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0013 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.content.Context b(android.content.Context r3) throws com.google.vr.dynamite.client.d {
        /*
            r2 = this;
            monitor-enter(r2)
            android.content.Context r0 = r2.f46a     // Catch:{ all -> 0x001d }
            if (r0 != 0) goto L_0x0019
            com.google.vr.dynamite.client.f r0 = r2.c     // Catch:{ NameNotFoundException -> 0x0013 }
            java.lang.String r0 = r0.a()     // Catch:{ NameNotFoundException -> 0x0013 }
            r1 = 3
            android.content.Context r3 = r3.createPackageContext(r0, r1)     // Catch:{ NameNotFoundException -> 0x0013 }
            r2.f46a = r3     // Catch:{ NameNotFoundException -> 0x0013 }
            goto L_0x0019
        L_0x0013:
            com.google.vr.dynamite.client.d r3 = new com.google.vr.dynamite.client.d     // Catch:{ all -> 0x001d }
            r3.<init>()     // Catch:{ all -> 0x001d }
            throw r3     // Catch:{ all -> 0x001d }
        L_0x0019:
            android.content.Context r3 = r2.f46a     // Catch:{ all -> 0x001d }
            monitor-exit(r2)
            return r3
        L_0x001d:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.vr.dynamite.client.e.b(android.content.Context):android.content.Context");
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.vr.dynamite.client.ILoadedInstanceCreator a(android.content.Context r3) throws com.google.vr.dynamite.client.d {
        /*
            r2 = this;
            monitor-enter(r2)
            com.google.vr.dynamite.client.ILoadedInstanceCreator r0 = r2.b     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x002d
            android.content.Context r3 = r2.b(r3)     // Catch:{ all -> 0x0031 }
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ all -> 0x0031 }
            java.lang.String r0 = "com.google.vr.dynamite.LoadedInstanceCreator"
            android.os.IBinder r3 = a(r3, r0)     // Catch:{ all -> 0x0031 }
            if (r3 != 0) goto L_0x0017
            r3 = 0
            goto L_0x002b
        L_0x0017:
            java.lang.String r0 = "com.google.vr.dynamite.client.ILoadedInstanceCreator"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)     // Catch:{ all -> 0x0031 }
            boolean r1 = r0 instanceof com.google.vr.dynamite.client.ILoadedInstanceCreator     // Catch:{ all -> 0x0031 }
            if (r1 == 0) goto L_0x0025
            r3 = r0
            com.google.vr.dynamite.client.ILoadedInstanceCreator r3 = (com.google.vr.dynamite.client.ILoadedInstanceCreator) r3     // Catch:{ all -> 0x0031 }
            goto L_0x002b
        L_0x0025:
            com.google.vr.dynamite.client.a r0 = new com.google.vr.dynamite.client.a     // Catch:{ all -> 0x0031 }
            r0.<init>(r3)     // Catch:{ all -> 0x0031 }
            r3 = r0
        L_0x002b:
            r2.b = r3     // Catch:{ all -> 0x0031 }
        L_0x002d:
            com.google.vr.dynamite.client.ILoadedInstanceCreator r3 = r2.b     // Catch:{ all -> 0x0031 }
            monitor-exit(r2)
            return r3
        L_0x0031:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.vr.dynamite.client.e.a(android.content.Context):com.google.vr.dynamite.client.ILoadedInstanceCreator");
    }

    private static IBinder a(ClassLoader classLoader, String str) {
        try {
            return (IBinder) classLoader.loadClass(str).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(str.length() != 0 ? "No constructor for dynamic class ".concat(str) : new String("No constructor for dynamic class "), e);
        } catch (InvocationTargetException e2) {
            throw new IllegalStateException(str.length() != 0 ? "Unable to invoke constructor of dynamic class ".concat(str) : new String("Unable to invoke constructor of dynamic class "), e2);
        } catch (ClassNotFoundException e3) {
            throw new IllegalStateException(str.length() != 0 ? "Unable to find dynamic class ".concat(str) : new String("Unable to find dynamic class "), e3);
        } catch (InstantiationException e4) {
            throw new IllegalStateException(str.length() != 0 ? "Unable to instantiate the remote class ".concat(str) : new String("Unable to instantiate the remote class "), e4);
        } catch (IllegalAccessException e5) {
            throw new IllegalStateException(str.length() != 0 ? "Unable to call the default constructor of ".concat(str) : new String("Unable to call the default constructor of "), e5);
        }
    }
}
