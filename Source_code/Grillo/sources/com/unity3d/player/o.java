package com.unity3d.player;

import java.lang.reflect.Method;
import java.util.HashMap;

final class o {

    /* renamed from: a  reason: collision with root package name */
    private HashMap f124a = new HashMap();
    private Class b = null;
    private Object c = null;

    class a {

        /* renamed from: a  reason: collision with root package name */
        public Class[] f125a;
        public Method b = null;

        public a(Class[] clsArr) {
            this.f125a = clsArr;
        }
    }

    public o(Class cls, Object obj) {
        this.b = cls;
        this.c = obj;
    }

    private void a(String str, a aVar) {
        try {
            aVar.b = this.b.getMethod(str, aVar.f125a);
        } catch (Exception e) {
            g.Log(6, "Exception while trying to get method " + str + ". " + e.getLocalizedMessage());
            aVar.b = null;
        }
    }

    public final Object a(String str, Object... objArr) {
        StringBuilder sb;
        if (!this.f124a.containsKey(str)) {
            sb = new StringBuilder("No definition for method ");
            sb.append(str);
            str = " can be found";
        } else {
            a aVar = (a) this.f124a.get(str);
            if (aVar.b == null) {
                a(str, aVar);
            }
            if (aVar.b == null) {
                sb = new StringBuilder("Unable to create method: ");
            } else {
                try {
                    return objArr.length == 0 ? aVar.b.invoke(this.c, new Object[0]) : aVar.b.invoke(this.c, objArr);
                } catch (Exception e) {
                    g.Log(6, "Error trying to call delegated method " + str + ". " + e.getLocalizedMessage());
                    return null;
                }
            }
        }
        sb.append(str);
        g.Log(6, sb.toString());
        return null;
    }

    public final void a(String str, Class[] clsArr) {
        this.f124a.put(str, new a(clsArr));
    }
}
