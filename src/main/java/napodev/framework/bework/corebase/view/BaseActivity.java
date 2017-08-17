package napodev.framework.bework.corebase.view;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import napodev.framework.bework.BaseApp;
import napodev.framework.bework.R;
import napodev.framework.bework.utils.C;
import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 3/21/17.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseActivityListener {
    final String TAG = "BaseActivity";
    public BaseApp baseApp;
    public ViewGroup mainView;
    private Dialog dialogLoading;
    private String suClassNameTag;

    public enum ANIM_TYPE {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        BOTTOM_TO_TOP,
        TOP_TO_BOTTOM
    }

    public BaseActivity() {
        suClassNameTag = this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseApp = BaseApp.getInstance();
        dialogLoading = new Dialog(this);
        dialogLoading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastReceiver,
                new IntentFilter(C.Broadcast.BROADCAST_FILTER_NAME));

        mainView = (ViewGroup) findViewById(android.R.id.content);

        onActivityCreate(savedInstanceState);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onActivityLeave();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onActivityResume();
    }

    @Override
    protected void onDestroy() {
        unregisterBroadcast();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.d("");
        if (getWorker() != null) {
            onBack();
        } else {
            showToast("No initial Worker", false);
        }
    }

    private BroadcastReceiver localBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String key = intent.getStringExtra("key");
            Log.d(suClassNameTag + " Broadcast onReceive key : " + key);
            onBroadcastReceiver(intent, key);
        }
    };


    public void unregisterBroadcast() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadcastReceiver);
        Log.d("Broadcast unregisterBroadcast " + suClassNameTag);
    }

    public ViewGroup getMainView() {
        return this.mainView;
    }

    public void hiddenKeyboard() {
        Log.d("hiddenKeyboard");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mainView.getWindowToken(), 0);
    }

    public void showKeyboard(EditText editText) {
        Log.d("showKeyboard");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void redirect(Class destination, Bundle bundle, ANIM_TYPE anim, boolean isKill, boolean isTopLevelActivity) {
        Intent i = new Intent(this, destination);

        if (bundle != null) i.putExtra(C.BundleKeys.BUNDLE_EXTRA_NAME, bundle);

        startActivity(i);

        if (anim != null) {
            switch (anim) {
                case RIGHT_TO_LEFT:
                    overridePendingTransition(R.anim.come_right_to_left, R.anim.going_right_to_left);
                    break;
                case LEFT_TO_RIGHT:
                    overridePendingTransition(R.anim.come_left_to_right, R.anim.going_left_to_right);
                    break;
                case BOTTOM_TO_TOP:
                    overridePendingTransition(R.anim.come_down_to_top, R.anim.anim_idle);
                    break;
                default:
                    break;
            }
        }

        if (isTopLevelActivity) {
            baseApp.subActivityName = destination.getSimpleName();
        }

        if (isKill) finish();
    }

    public void redirect(Class destination, Bundle bundle, ANIM_TYPE anim) {
        Intent i = new Intent(this, destination);

        if (bundle != null) i.putExtra(C.BundleKeys.BUNDLE_EXTRA_NAME, bundle);

        startActivity(i);

        if (anim != null) {
            switch (anim) {
                case RIGHT_TO_LEFT:
                    overridePendingTransition(R.anim.come_right_to_left, R.anim.going_right_to_left);
                    break;
                case LEFT_TO_RIGHT:
                    overridePendingTransition(R.anim.come_left_to_right, R.anim.going_left_to_right);
                    break;
                case BOTTOM_TO_TOP:
                    overridePendingTransition(R.anim.come_down_to_top, R.anim.anim_idle);
                    break;
                default:
                    break;
            }
        }
    }

    public void redirect(Class destination, ANIM_TYPE anim) {
        Intent i = new Intent(this, destination);
        startActivity(i);

        if (anim != null) {
            switch (anim) {
                case RIGHT_TO_LEFT:
                    overridePendingTransition(R.anim.come_right_to_left, R.anim.going_right_to_left);
                    break;
                case LEFT_TO_RIGHT:
                    overridePendingTransition(R.anim.come_left_to_right, R.anim.going_left_to_right);
                    break;
                case BOTTOM_TO_TOP:
                    overridePendingTransition(R.anim.come_down_to_top, R.anim.anim_idle);
                    break;
                default:
                    break;
            }
        }
    }

    public void finishWithAnim(ANIM_TYPE anim) {
        finish();
        switch (anim) {
            case RIGHT_TO_LEFT:
                overridePendingTransition(R.anim.come_right_to_left, R.anim.going_right_to_left);
                break;
            case LEFT_TO_RIGHT:
                overridePendingTransition(R.anim.come_left_to_right, R.anim.going_left_to_right);
                break;
            case BOTTOM_TO_TOP:
                overridePendingTransition(R.anim.come_down_to_top, R.anim.going_top_to_down);
                break;
            case TOP_TO_BOTTOM:
                overridePendingTransition(R.anim.anim_idle, R.anim.going_top_to_down);
                break;
            default:
                break;
        }
    }

    public Bundle getBundleEtras() {
        if (getIntent().hasExtra(C.BundleKeys.BUNDLE_EXTRA_NAME)) {
            return getIntent().getBundleExtra(C.BundleKeys.BUNDLE_EXTRA_NAME);
        }

        return null;
    }

    public Bundle getBundleEtrasLocalIntent(Intent i) {
        if (i.hasExtra(C.BundleKeys.BUNDLE_EXTRA_NAME)) {
            return i.getBundleExtra(C.BundleKeys.BUNDLE_EXTRA_NAME);
        }

        return null;
    }

    public void showToast(String text, boolean isPositive) {
        View v = getLayoutInflater().inflate(isPositive ? R.layout.inflate_toast_positive
                : R.layout.inflate_toast_negative, mainView, false);
        Toast t = new Toast(this);
        t.setView(v);
        t.show();
        ((TextView) v.findViewById(R.id.tMsg)).setText(text);
    }

    public void showToast(String text, int layId, int textViewId) {
        View v = getLayoutInflater().inflate(layId, mainView, false);
        Toast t = new Toast(this);
        t.setView(v);
        t.show();
        ((TextView) v.findViewById(textViewId)).setText(text);
    }

    public void showDialogLoading(String text) {
        dialogLoading.show();
        dialogLoading.setContentView(R.layout.inflate_dialog_loading);
        ((TextView) dialogLoading.findViewById(R.id.tMsg)).setText(text);
    }

    public void showDialogLoading(String text, int layId, int textViewId, boolean cancelable) {
        dialogLoading.show();
        dialogLoading.setCancelable(cancelable);
        dialogLoading.setContentView(layId);
        ((TextView) dialogLoading.findViewById(textViewId)).setText(text);
    }

    public void dismissDialogLoading() {
        if (dialogLoading != null) {
            if (dialogLoading.isShowing()) {
                dialogLoading.dismiss();
            }
        }
    }

    public void restart(Class activity) {
        BaseApp.getInstance().subActivityName = activity.getSimpleName();
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public BaseActivity thisActivity() {
        return this;
    }

    protected void onActivityResume() {
    }

    protected void onActivityLeave() {
    }

    protected void onBroadcastReceiver(Intent intent, String key) {
    }

    protected void onBack() {
    }
}

