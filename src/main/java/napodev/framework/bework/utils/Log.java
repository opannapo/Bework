package napodev.framework.bework.utils;

import java.util.ArrayList;

/**
 * Created by opannapo on 12/19/16.
 */
public final class Log {
    private static Config config;

    private static void init() {
        if (config == null)
            return;

        config.setStackTraceElements(Thread.currentThread().getStackTrace());

        if (config.getBlockedClasses().contains(config.getStackTraceElements()[4].getFileName().replace(".java", "")))
            return;

        if (config.isWithDetailCaller) {
            config.TAG_ADDITION = config.getStackTraceElements()[4].getFileName() + "->" +
                    config.getStackTraceElements()[4].getMethodName() +
                    (config.isWithDetailLine ? " line:" + config.getStackTraceElements()[4].getLineNumber() + " -> " : " ");
        } else {
            config.TAG_ADDITION = "";
        }
    }

    public static void i(String message) {
        init();
        if (config.isLogEnable()) {
            android.util.Log.i(config.getTAG() + " i", config.TAG_ADDITION + message);
        } else {
            android.util.Log.i(config.getTAG() + " i", "LOG BLOCKED");
        }
    }

    public static void d(String message) {
        init();
        if (config.isLogEnable()) {
            android.util.Log.d(config.getTAG() + " d", config.TAG_ADDITION + message);
        } else {
            android.util.Log.d(config.getTAG() + " d", "LOG BLOCKED");
        }
    }

    public static void e(String message) {
        init();
        if (config.isLogEnable()) {
            android.util.Log.e(config.getTAG() + " e", config.TAG_ADDITION + message);
        } else {
            android.util.Log.e(config.getTAG() + " e", "LOG BLOCKED");
        }
    }

    public static Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public static void clearConfig() {
        config = null;
    }


    public static final class Config {
        private StackTraceElement[] stackTraceElements;
        private String TAG;
        private String TAG_ADDITION;
        private boolean isLogEnable;
        private boolean isWithDetailLine;
        private boolean isWithDetailCaller;
        private ArrayList<String> blockedClasses = new ArrayList<>();


        public boolean isLogEnable() {
            return isLogEnable;
        }

        public Config setLogEnable(boolean logEnable) {
            isLogEnable = logEnable;
            return this;
        }

        private StackTraceElement[] getStackTraceElements() {
            return stackTraceElements;
        }

        private void setStackTraceElements(StackTraceElement[] stackTraceElements) {
            this.stackTraceElements = stackTraceElements;
        }

        public String getTAG() {
            return TAG;
        }

        public Config setTAG(String TAG) {
            this.TAG = TAG;
            return this;
        }

        private boolean isWithDetailLine() {
            return isWithDetailLine;
        }

        public Config setWithDetailLine(boolean withDetailLine) {
            isWithDetailLine = withDetailLine;
            return this;
        }

        public boolean isWithDetailCaller() {
            return isWithDetailCaller;
        }

        public Config setWithDetailCaller(boolean withDetailCaller) {
            isWithDetailCaller = withDetailCaller;
            return this;
        }

        public Config blockClass(Class className) {
            blockedClasses.add(className.getSimpleName());
            return this;
        }

        public ArrayList<String> getBlockedClasses() {
            return blockedClasses;
        }


    }
}