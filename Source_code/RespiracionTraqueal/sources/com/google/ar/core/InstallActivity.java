package com.google.ar.core;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.annotations.UsedByReflection;
import com.google.ar.core.exceptions.FatalException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import java.util.concurrent.atomic.AtomicReference;

@TargetApi(24)
@UsedByReflection("AndroidManifest.xml")
public class InstallActivity extends Activity {
    private static final int BOX_SIZE_DP = 280;
    private static final int INSTALLING_TEXT_BOTTOM_MARGIN_DP = 30;
    static final String INSTALL_BEHAVIOR_KEY = "behavior";
    static final String MESSAGE_TYPE_KEY = "message";
    private static final String TAG = "ARCore-InstallActivity";
    private boolean finished;
    private ArCoreApk.InstallBehavior installBehavior;
    private boolean installStarted;
    /* access modifiers changed from: private */
    public q lastEvent = q.CANCELLED;
    private ArCoreApk.UserMessageType messageType;
    private final ContextThemeWrapper themeWrapper = new ContextThemeWrapper(this, 16974394);
    /* access modifiers changed from: private */
    public boolean waitingForCompletion;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            try {
                finishWithFailure(new FatalException("Install activity was suspended and recreated."));
            } catch (RuntimeException e) {
                finishWithFailure(new FatalException("Exception starting install flow", e));
            }
        } else {
            this.messageType = (ArCoreApk.UserMessageType) getIntent().getSerializableExtra(MESSAGE_TYPE_KEY);
            this.installBehavior = (ArCoreApk.InstallBehavior) getIntent().getSerializableExtra(INSTALL_BEHAVIOR_KEY);
            if (this.messageType != null) {
                if (this.installBehavior != null) {
                    setTheme(16974394);
                    getWindow().requestFeature(1);
                    setFinishOnTouchOutside(isOptional());
                    if (this.messageType == ArCoreApk.UserMessageType.USER_ALREADY_INFORMED) {
                        showSpinner();
                        return;
                    }
                    AtomicReference atomicReference = new AtomicReference(ArCoreApk.Availability.UNKNOWN_CHECKING);
                    h.a().a((Context) this).a((Context) this, (ArCoreApk.a) new ac(atomicReference));
                    int ordinal = ((ArCoreApk.Availability) atomicReference.get()).ordinal();
                    if (ordinal == 0) {
                        Log.w(TAG, "Preliminary compatibility check failed.");
                    } else if (ordinal == 3) {
                        finishWithFailure(new UnavailableDeviceNotCompatibleException());
                        return;
                    }
                    showEducationDialog();
                    return;
                }
            }
            Log.e(TAG, "missing intent data.");
            finishWithFailure(new FatalException("Install activity launched without config data."));
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("didResume", true);
    }

    private void showEducationDialog() {
        setContentView(R.layout.__arcore_education);
        findViewById(R.id.__arcore_cancelButton).setOnClickListener(new k(this));
        if (!isOptional()) {
            findViewById(R.id.__arcore_cancelButton).setVisibility(8);
        }
        findViewById(R.id.__arcore_continueButton).setOnClickListener(new m(this));
        TextView textView = (TextView) findViewById(R.id.__arcore_messageText);
        if (this.messageType.ordinal() != 1) {
            textView.setText(R.string.__arcore_install_app);
        } else {
            textView.setText(R.string.__arcore_install_feature);
        }
    }

    /* access modifiers changed from: private */
    public void showSpinner() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = (int) (displayMetrics.density * 280.0f);
        getWindow().setLayout(i, i);
        RelativeLayout relativeLayout = new RelativeLayout(this.themeWrapper);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        ProgressBar progressBar = new ProgressBar(this.themeWrapper);
        progressBar.setIndeterminate(true);
        progressBar.setLayoutParams(layoutParams);
        relativeLayout.addView(progressBar);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(14);
        layoutParams2.addRule(12);
        layoutParams2.bottomMargin = (int) (displayMetrics.density * 30.0f);
        TextView textView = new TextView(this.themeWrapper);
        textView.setText(R.string.__arcore_installing);
        textView.setLayoutParams(layoutParams2);
        relativeLayout.addView(textView);
        setContentView(relativeLayout);
        getWindow().setLayout(i, i);
    }

    /* access modifiers changed from: private */
    public void animateToSpinner() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = (int) (displayMetrics.density * 280.0f);
        int width = getWindow().getDecorView().getWidth();
        int height = getWindow().getDecorView().getHeight();
        setContentView(new RelativeLayout(this));
        getWindow().getDecorView().setMinimumWidth(i);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(300);
        ofFloat.addUpdateListener(new l(this, width, i, height));
        ofFloat.addListener(new o(this));
        ofFloat.start();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.installStarted) {
            if (this.messageType == ArCoreApk.UserMessageType.USER_ALREADY_INFORMED) {
                startInstaller();
            }
        } else if (!this.finished) {
            synchronized (this) {
                if (this.lastEvent == q.CANCELLED) {
                    finish();
                } else if (this.lastEvent == q.ACCEPTED) {
                    this.waitingForCompletion = true;
                } else {
                    finishWithFailure(h.a().f30a);
                }
            }
        }
    }

    public void finish() {
        finishWithFailure(new UnavailableUserDeclinedInstallationException());
    }

    /* access modifiers changed from: private */
    public void finishWithFailure(Exception exc) {
        h.a().f30a = exc;
        h.a().b();
        this.finished = true;
        super.finish();
    }

    public void onDestroy() {
        if (!this.finished) {
            h.a().b();
        }
        super.onDestroy();
    }

    private boolean isOptional() {
        return this.installBehavior == ArCoreApk.InstallBehavior.OPTIONAL;
    }

    /* access modifiers changed from: private */
    public void startInstaller() {
        this.installStarted = true;
        this.lastEvent = q.CANCELLED;
        h.a().a((Context) this).a((Activity) this, new p(this));
    }

    /* access modifiers changed from: private */
    public void closeInstaller() {
        startActivity(new Intent(this, InstallActivity.class).setFlags(67108864));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        StringBuilder sb = new StringBuilder(27);
        sb.append("activityResult: ");
        sb.append(i2);
        Log.i(TAG, sb.toString());
    }
}
