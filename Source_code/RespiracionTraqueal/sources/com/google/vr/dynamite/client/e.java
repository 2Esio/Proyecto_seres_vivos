package com.google.vr.dynamite.client;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.IBinder;
import java.lang.reflect.InvocationTargetException;

/* compiled from: RemoteLibraryLoader */
final class e {

    /* renamed from: a  reason: collision with root package name */
    private Context f50a;
    private ILoadedInstanceCreator b;
    private final d c;

    public e(d dVar) {
        this.c = dVar;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.vr.dynamite.client.ILoadedInstanceCreator a(android.content.Context r3) throws com.google.vr.dynamite.client.c {
        /*
            r2 = this;
            monitor-enter(r2)
            com.google.vr.dynamite.client.ILoadedInstanceCreator r0 = r2.b     // Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x002e
            android.content.Context r3 = r2.b(r3)     // Catch:{ all -> 0x0032 }
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ all -> 0x0032 }
            java.lang.String r0 = "com.google.vr.dynamite.LoadedInstanceCreator"
            android.os.IBinder r3 = a(r3, r0)     // Catch:{ all -> 0x0032 }
            if (r3 != 0) goto L_0x0018
            r3 = 0
            goto L_0x002c
        L_0x0018:
            java.lang.String r0 = "com.google.vr.dynamite.client.ILoadedInstanceCreator"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)     // Catch:{ all -> 0x0032 }
            boolean r1 = r0 instanceof com.google.vr.dynamite.client.ILoadedInstanceCreator     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0026
            r3 = r0
            com.google.vr.dynamite.client.ILoadedInstanceCreator r3 = (com.google.vr.dynamite.client.ILoadedInstanceCreator) r3     // Catch:{ all -> 0x0032 }
            goto L_0x002c
        L_0x0026:
            com.google.vr.dynamite.client.a r0 = new com.google.vr.dynamite.client.a     // Catch:{ all -> 0x0032 }
            r0.<init>(r3)     // Catch:{ all -> 0x0032 }
            r3 = r0
        L_0x002c:
            r2.b = r3     // Catch:{ all -> 0x0032 }
        L_0x002e:
            com.google.vr.dynamite.client.ILoadedInstanceCreator r3 = r2.b     // Catch:{ all -> 0x0032 }
            monitor-exit(r2)
            return r3
        L_0x0032:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.vr.dynamite.client.e.a(android.content.Context):com.google.vr.dynamite.client.ILoadedInstanceCreator");
    }

    public final synchronized Context b(Context context) throws c {
        if (this.f50a == null) {
            try {
                this.f50a = context.createPackageContext(this.c.a(), 3);
            } catch (PackageManager.NameNotFoundException e) {
                throw new c(1);
            }
        }
        return this.f50a;
    }

    private static IBinder a(ClassLoader classLoader, String str) {
        try {
            return (IBinder) classLoader.loadClass(str).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (NoSuchMethodException e) {
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? "No constructor for dynamic class ".concat(valueOf) : new String("No constructor for dynamic class "));
        } catch (InvocationTargetException e2) {
            String valueOf2 = String.valueOf(str);
            throw new IllegalStateException(valueOf2.length() != 0 ? "Unable to invoke constructor of dynamic class ".concat(valueOf2) : new String("Unable to invoke constructor of dynamic class "));
        } catch (ClassNotFoundException e3) {
            String valueOf3 = String.valueOf(str);
            throw new IllegalStateException(valueOf3.length() != 0 ? "Unable to find dynamic class ".concat(valueOf3) : new String("Unable to find dynamic class "));
        } catch (InstantiationException e4) {
            String valueOf4 = String.valueOf(str);
            throw new IllegalStateException(valueOf4.length() != 0 ? "Unable to instantiate the remote class ".concat(valueOf4) : new String("Unable to instantiate the remote class "));
        } catch (IllegalAccessException e5) {
            String valueOf5 = String.valueOf(str);
            throw new IllegalStateException(valueOf5.length() != 0 ? "Unable to call the default constructor of ".concat(valueOf5) : new String("Unable to call the default constructor of "));
        }
    }
}
