package com.google.vr.dynamite.client;

import android.content.Context;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import dalvik.system.DexClassLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UsedByNative
public final class DynamiteClient {

    /* renamed from: a  reason: collision with root package name */
    private static final ArrayMap<d, e> f47a = new ArrayMap<>();

    private DynamiteClient() {
    }

    @UsedByNative
    public static synchronized int checkVersion(Context context, String str, String str2, String str3) {
        d dVar;
        synchronized (DynamiteClient.class) {
            f fVar = null;
            if (str3 != null) {
                try {
                    if (!str3.isEmpty()) {
                        Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)").matcher(str3);
                        if (!matcher.matches()) {
                            String valueOf = String.valueOf(str3);
                            Log.w("Version", valueOf.length() != 0 ? "Failed to parse version from: ".concat(valueOf) : new String("Failed to parse version from: "));
                        } else {
                            fVar = new f(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
                        }
                    }
                } catch (RemoteException | c | IllegalArgumentException | IllegalStateException | SecurityException | UnsatisfiedLinkError e) {
                    String valueOf2 = String.valueOf(dVar);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 54);
                    sb.append("Failed to load native library ");
                    sb.append(valueOf2);
                    sb.append(" from remote package:\n  ");
                    Log.e("DynamiteClient", sb.toString(), e);
                    return -1;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (fVar == null) {
                String valueOf3 = String.valueOf(str3);
                throw new IllegalArgumentException(valueOf3.length() != 0 ? "Improperly formatted minVersion string: ".concat(valueOf3) : new String("Improperly formatted minVersion string: "));
            }
            dVar = new d(str, str2);
            e remoteLibraryLoaderFromInfo = getRemoteLibraryLoaderFromInfo(dVar);
            INativeLibraryLoader newNativeLibraryLoader = remoteLibraryLoaderFromInfo.a(context).newNativeLibraryLoader(ObjectWrapper.a(remoteLibraryLoaderFromInfo.b(context)), ObjectWrapper.a(context));
            if (newNativeLibraryLoader == null) {
                String valueOf4 = String.valueOf(dVar);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf4).length() + 72);
                sb2.append("Failed to load native library ");
                sb2.append(valueOf4);
                sb2.append(" from remote package: no loader available.");
                Log.e("DynamiteClient", sb2.toString());
                return -1;
            }
            int checkVersion = newNativeLibraryLoader.checkVersion(str3);
            return checkVersion;
        }
    }

    @UsedByNative
    public static synchronized long loadNativeRemoteLibrary(Context context, String str, String str2) {
        synchronized (DynamiteClient.class) {
            d dVar = new d(str, str2);
            e remoteLibraryLoaderFromInfo = getRemoteLibraryLoaderFromInfo(dVar);
            try {
                INativeLibraryLoader newNativeLibraryLoader = remoteLibraryLoaderFromInfo.a(context).newNativeLibraryLoader(ObjectWrapper.a(remoteLibraryLoaderFromInfo.b(context)), ObjectWrapper.a(context));
                if (newNativeLibraryLoader == null) {
                    String valueOf = String.valueOf(dVar);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 72);
                    sb.append("Failed to load native library ");
                    sb.append(valueOf);
                    sb.append(" from remote package: no loader available.");
                    Log.e("DynamiteClient", sb.toString());
                    return 0;
                }
                long initializeAndLoadNativeLibrary = newNativeLibraryLoader.initializeAndLoadNativeLibrary(str2);
                return initializeAndLoadNativeLibrary;
            } catch (RemoteException | c | IllegalArgumentException | IllegalStateException | SecurityException | UnsatisfiedLinkError e) {
                String valueOf2 = String.valueOf(dVar);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 54);
                sb2.append("Failed to load native library ");
                sb2.append(valueOf2);
                sb2.append(" from remote package:\n  ");
                Log.e("DynamiteClient", sb2.toString(), e);
                return 0;
            }
        }
    }

    @UsedByNative
    public static synchronized ClassLoader getRemoteClassLoader(Context context, String str, String str2) {
        synchronized (DynamiteClient.class) {
            Context remoteContext = getRemoteContext(context, str, str2);
            if (remoteContext == null) {
                return null;
            }
            ClassLoader classLoader = remoteContext.getClassLoader();
            return classLoader;
        }
    }

    @UsedByNative
    public static synchronized ClassLoader getRemoteDexClassLoader(Context context, String str) {
        synchronized (DynamiteClient.class) {
            Context remoteContext = getRemoteContext(context, str, (String) null);
            if (remoteContext == null) {
                return null;
            }
            try {
                DexClassLoader dexClassLoader = new DexClassLoader(remoteContext.getPackageCodePath(), context.getCodeCacheDir().getAbsolutePath(), remoteContext.getApplicationInfo().nativeLibraryDir, context.getClassLoader());
                return dexClassLoader;
            } catch (RuntimeException e) {
                Log.e("DynamiteClient", "Failed to create class loader for remote package\n ", e);
                return null;
            }
        }
    }

    @UsedByNative
    public static synchronized Context getRemoteContext(Context context, String str, String str2) {
        Context b;
        synchronized (DynamiteClient.class) {
            d dVar = new d(str, str2);
            try {
                b = getRemoteLibraryLoaderFromInfo(dVar).b(context);
            } catch (c e) {
                String valueOf = String.valueOf(dVar);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 52);
                sb.append("Failed to get remote Context");
                sb.append(valueOf);
                sb.append(" from remote package:\n  ");
                Log.e("DynamiteClient", sb.toString(), e);
                return null;
            }
        }
        return b;
    }

    @UsedByNative
    private static synchronized e getRemoteLibraryLoaderFromInfo(d dVar) {
        e eVar;
        synchronized (DynamiteClient.class) {
            eVar = f47a.get(dVar);
            if (eVar == null) {
                eVar = new e(dVar);
                f47a.put(dVar, eVar);
            }
        }
        return eVar;
    }
}
