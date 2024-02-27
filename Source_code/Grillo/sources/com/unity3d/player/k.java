package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.ar.core.ImageMetadata;

public final class k extends Dialog implements TextWatcher, View.OnClickListener {
    private static int c = 1627389952;
    private static int d = -1;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f114a = null;
    /* access modifiers changed from: private */
    public UnityPlayer b = null;
    private int e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k(Context context, UnityPlayer unityPlayer, String str, int i, boolean z, boolean z2, boolean z3, String str2, int i2, boolean z4) {
        super(context);
        this.f114a = context;
        this.b = unityPlayer;
        Window window = getWindow();
        window.requestFeature(1);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(0));
        final View createSoftInputView = createSoftInputView();
        setContentView(createSoftInputView);
        window.setLayout(-1, -2);
        window.clearFlags(2);
        window.clearFlags(134217728);
        window.clearFlags(67108864);
        EditText editText = (EditText) findViewById(1057292289);
        a(editText, str, i, z, z2, z3, str2, i2);
        ((Button) findViewById(1057292290)).setOnClickListener(this);
        this.e = editText.getCurrentTextColor();
        a(z4);
        this.b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public final void onGlobalLayout() {
                if (createSoftInputView.isShown()) {
                    Rect rect = new Rect();
                    k.this.b.getWindowVisibleDisplayFrame(rect);
                    int[] iArr = new int[2];
                    k.this.b.getLocationOnScreen(iArr);
                    Point point = new Point(rect.left - iArr[0], rect.height() - createSoftInputView.getHeight());
                    Point point2 = new Point();
                    k.this.getWindow().getWindowManager().getDefaultDisplay().getSize(point2);
                    int height = k.this.b.getHeight() - point2.y;
                    int height2 = k.this.b.getHeight() - point.y;
                    if (height2 != height + createSoftInputView.getHeight()) {
                        k.this.b.reportSoftInputIsVisible(true);
                    } else {
                        k.this.b.reportSoftInputIsVisible(false);
                    }
                    k.this.b.reportSoftInputArea(new Rect(point.x, point.y, createSoftInputView.getWidth(), height2));
                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                if (z) {
                    k.this.getWindow().setSoftInputMode(5);
                }
            }
        });
        editText.requestFocus();
    }

    private static int a(int i, boolean z, boolean z2, boolean z3) {
        int i2 = 0;
        int i3 = (z ? 32768 : ImageMetadata.LENS_APERTURE) | (z2 ? 131072 : 0);
        if (z3) {
            i2 = 128;
        }
        int i4 = i3 | i2;
        if (i < 0 || i > 11) {
            return i4;
        }
        int[] iArr = {1, 16385, 12290, 17, 2, 3, 8289, 33, 1, 16417, 17, 8194};
        return (iArr[i] & 2) != 0 ? iArr[i] : iArr[i] | i4;
    }

    private void a(EditText editText, String str, int i, boolean z, boolean z2, boolean z3, String str2, int i2) {
        editText.setImeOptions(6);
        editText.setText(str);
        editText.setHint(str2);
        editText.setHintTextColor(c);
        editText.setInputType(a(i, z, z2, z3));
        editText.setImeOptions(33554432);
        if (i2 > 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
        }
        editText.addTextChangedListener(this);
        editText.setSelection(editText.getText().length());
        editText.setClickable(true);
    }

    /* access modifiers changed from: private */
    public void a(String str, boolean z) {
        ((EditText) findViewById(1057292289)).setSelection(0, 0);
        this.b.reportSoftInputStr(str, 1, z);
    }

    /* access modifiers changed from: private */
    public String b() {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText == null) {
            return null;
        }
        return editText.getText().toString().trim();
    }

    public final String a() {
        InputMethodSubtype currentInputMethodSubtype = ((InputMethodManager) this.f114a.getSystemService("input_method")).getCurrentInputMethodSubtype();
        if (currentInputMethodSubtype == null) {
            return null;
        }
        String locale = currentInputMethodSubtype.getLocale();
        if (locale != null && !locale.equals("")) {
            return locale;
        }
        String mode = currentInputMethodSubtype.getMode();
        String extraValue = currentInputMethodSubtype.getExtraValue();
        return mode + " " + extraValue;
    }

    public final void a(int i) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText == null) {
            return;
        }
        if (i > 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
            return;
        }
        editText.setFilters(new InputFilter[0]);
    }

    public final void a(int i, int i2) {
        int i3;
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null && editText.getText().length() >= (i3 = i2 + i)) {
            editText.setSelection(i, i3);
        }
    }

    public final void a(String str) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null) {
            editText.setText(str);
            editText.setSelection(str.length());
        }
    }

    public final void a(boolean z) {
        int i;
        EditText editText = (EditText) findViewById(1057292289);
        Button button = (Button) findViewById(1057292290);
        View findViewById = findViewById(1057292291);
        if (z) {
            i = 0;
            editText.setBackgroundColor(0);
            editText.setTextColor(0);
            editText.setCursorVisible(false);
            button.setClickable(false);
            button.setTextColor(0);
        } else {
            editText.setBackgroundColor(d);
            editText.setTextColor(this.e);
            editText.setCursorVisible(true);
            button.setClickable(true);
            button.setTextColor(this.e);
            i = d;
        }
        findViewById.setBackgroundColor(i);
    }

    public final void afterTextChanged(Editable editable) {
        this.b.reportSoftInputStr(editable.toString(), 0, false);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public final View createSoftInputView() {
        RelativeLayout relativeLayout = new RelativeLayout(this.f114a);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(d);
        relativeLayout.setId(1057292291);
        AnonymousClass3 r1 = new EditText(this.f114a) {
            public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
                if (i == 4) {
                    k kVar = k.this;
                    kVar.a(kVar.b(), true);
                    return true;
                } else if (i == 84) {
                    return true;
                } else {
                    return super.onKeyPreIme(i, keyEvent);
                }
            }

            /* access modifiers changed from: protected */
            public final void onSelectionChanged(int i, int i2) {
                k.this.b.reportSoftInputSelection(i, i2 - i);
            }

            public final void onWindowFocusChanged(boolean z) {
                super.onWindowFocusChanged(z);
                if (z) {
                    ((InputMethodManager) k.this.f114a.getSystemService("input_method")).showSoftInput(this, 0);
                }
            }
        };
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, 1057292290);
        r1.setLayoutParams(layoutParams);
        r1.setId(1057292289);
        relativeLayout.addView(r1);
        Button button = new Button(this.f114a);
        button.setText(this.f114a.getResources().getIdentifier("ok", "string", "android"));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        button.setLayoutParams(layoutParams2);
        button.setId(1057292290);
        button.setBackgroundColor(0);
        relativeLayout.addView(button);
        ((EditText) relativeLayout.findViewById(1057292289)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    k kVar = k.this;
                    kVar.a(kVar.b(), false);
                }
                return false;
            }
        });
        relativeLayout.setPadding(16, 16, 16, 16);
        return relativeLayout;
    }

    public final void onBackPressed() {
        a(b(), true);
    }

    public final void onClick(View view) {
        a(b(), false);
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
