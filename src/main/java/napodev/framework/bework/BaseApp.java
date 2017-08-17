package napodev.framework.bework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.Observable;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Map;

import napodev.framework.bework.utils.Log;
import napodev.framework.bework.utils.helper.ScreenHelper;
import napodev.framework.bework.utils.helper.StringHelper;
import napodev.framework.bework.utils.local.SPreference;

/**
 * Created by opannapo on 4/23/17.
 */
public abstract class BaseApp extends Application implements Application.ActivityLifecycleCallbacks {
    private Map<String, Observable> observables;
    private long startTimeExecute;
    private long endTimeExecute;
    private static BaseApp instance;
    private StringHelper stringHelper;
    public String subActivityName;

    public BaseApp(String subActivityName) {
        stringHelper = new StringHelper();
        this.subActivityName = subActivityName;
        instance = this;
    }

    public static BaseApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(instance);
        setupConfiguration();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        clearObservalbleList();
        instance = null;
        System.gc();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Log.d("subActivityName " + String.valueOf(subActivityName));

        getUsedMemorySizeToString();
        Log.d("onActivityCreated " + String.valueOf(activity.getClass().getSimpleName()));
        if (isFirstActivityRunning(activity)) {
            generateObservalbleList();
            ScreenHelper.getDeviceType(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        getUsedMemorySizeToString();
        Log.d("onActivityStarted " + String.valueOf(activity.getClass().getSimpleName()));
    }

    @Override
    public void onActivityResumed(Activity activity) {
        getUsedMemorySizeToString();
        Log.d("onActivityResumed " + String.valueOf(activity.getClass().getSimpleName()));
    }

    @Override
    public void onActivityPaused(Activity activity) {
        getUsedMemorySizeToString();
        System.gc();
        Log.d("onActivityPaused " + String.valueOf(activity.getClass().getSimpleName()));
    }

    @Override
    public void onActivityStopped(Activity activity) {
        getUsedMemorySizeToString();
        System.gc();
        Log.d("onActivityStopped " + String.valueOf(activity.getClass().getSimpleName()));
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        getUsedMemorySizeToString();
        Log.d("onActivitySaveInstanceState " + String.valueOf(activity.getClass().getSimpleName()));
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        getUsedMemorySizeToString();
        Log.d("onActivityDestroyed " + String.valueOf(activity.getClass().getSimpleName()));
        if (isFirstActivityRunning(activity)) {
            //API.clearConfig();
            Log.clearConfig();
            SPreference.clearConfig();
            clearObservalbleList();

            instance = null;
            System.exit(0);
        }
    }

    private void setupConfiguration() {
        SPreference.getConfig()
                .setAppContex(this)
                .setPrefName(configPreferenceName())
                .setAESKey(configPreferenceEncryptKey())
                .build();

        Log.getConfig()
                .setLogEnable(configLogEnable())
                .setWithDetailLine(configLogDetailLine())
                .setTAG(configLogTag())
                //.blockClass(SPreference.class)
                //.blockClass(StringHelper.class)
                .setWithDetailCaller(configLogCaller());
    }

    public Context getAppContext() {
        return getApplicationContext();
    }

    public void initStartExecute() {
        startTimeExecute = Calendar.getInstance().getTimeInMillis();
        Log.d("Time Execute Initial start on : " + String.valueOf(startTimeExecute));
        Log.d("Time Execute Result Memory Use: " + String.valueOf(getUsedMemorySizeToString()));
    }

    public void initEndExecute() {
        endTimeExecute = Calendar.getInstance().getTimeInMillis();
        Log.d("Time Execute Result: " + String.valueOf(endTimeExecute - startTimeExecute));
        Log.d("Time Execute Result Memory Use: " + String.valueOf(getUsedMemorySizeToString()));
        startTimeExecute = 0;
        endTimeExecute = 0;
        Log.d("Time Execute Result Memory Use: " + String.valueOf(getUsedMemorySizeToString()));

    }

    public long getUsedMemorySizeToLong() {
        long freeSize = 0L;
        long totalSize = 0L;
        long usedSize = -1L;
        try {
            Runtime info = Runtime.getRuntime();
            freeSize = info.freeMemory();
            totalSize = info.totalMemory();
            usedSize = totalSize - freeSize;
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sTotalsize = stringHelper.convertFormat((int) totalSize, StringHelper.FORMAT_TYPE.PRICE);
        String sUsedsize = stringHelper.convertFormat((int) usedSize, StringHelper.FORMAT_TYPE.PRICE);
        String sFreesize = stringHelper.convertFormat((int) freeSize, StringHelper.FORMAT_TYPE.PRICE);
        Log.d("getUsedMemorySize() Memory total(" + sTotalsize + ") used(" + sUsedsize + ") free(" + sFreesize + ")");
        return usedSize;
    }

    public String getUsedMemorySizeToString() {
        long freeSize = 0L;
        long totalSize = 0L;
        long usedSize = -1L;
        try {
            Runtime info = Runtime.getRuntime();
            freeSize = info.freeMemory();
            totalSize = info.totalMemory();
            usedSize = totalSize - freeSize;
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sTotalsize = stringHelper.convertFormat((int) totalSize, StringHelper.FORMAT_TYPE.PRICE);
        String sUsedsize = stringHelper.convertFormat((int) usedSize, StringHelper.FORMAT_TYPE.PRICE);
        String sFreesize = stringHelper.convertFormat((int) freeSize, StringHelper.FORMAT_TYPE.PRICE);
        Log.d("getUsedMemorySize() Memory total(" + sTotalsize + ") used(" + sUsedsize + ") free(" + sFreesize + ")");

        return stringHelper.convertFormat((int) usedSize, StringHelper.FORMAT_TYPE.PRICE);
    }

    public Object getObservable(String key) {
        return observables.get(key);
    }

    private void generateObservalbleList() {
        //observables = new ObservableCollection();
    }

    private void clearObservalbleList() {
        if (observables != null) {
            observables.clear();
        }
    }

    public boolean isFirstActivityRunning(Activity activity) {
        if (activity.getClass().getSimpleName().equals(subActivityName)) {
            Log.d("onActivityDestroyed instanceof " + subActivityName);
            return true;
        }
        return false;
    }

    public void notifyTokenUpdate() {
        //API.getConfig().setBearerAuthorization("Bearer " + Session.getToken());
    }

    protected abstract String configPreferenceName();

    protected abstract String configPreferenceEncryptKey();

    protected abstract boolean configLogEnable();

    protected abstract boolean configLogDetailLine();

    protected abstract boolean configLogCaller();

    protected abstract String configLogTag();
}
