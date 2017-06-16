package napodev.framework.bework.utils;

/**
 * Created by opannapo on 12/17/16.
 */
public final class C {
    public static final class Values {
        public static final String UNKNOW = "UNKNOW";
        public static final int VOUCHER_STATUS_ACTIVE = 1;
        public static final int VOUCHER_STATUS_USED = 2;
    }

    public static final class Broadcast {
        public static final String BROADCAST_FILTER_NAME = "NAPO_BROADCAST";
        public static final String BROADCAST_DEALS_NEED_UPDATE = "BROADCAST_DEALS_NEED_UPDATE ";
        public static final String USER_NEED_UPDATE = "USER_NEED_UPDATE";
        public static final String USER_UPDATED = "USER_UPDATED";
    }

    public static final class BundleKeys {
        public static final String BUNDLE_EXTRA_NAME = "BUNDLE_EXTRA_NAME";
        public static final String BUNDLE_DEAL_OBJECT = "BUNDLE_DEAL_OBJECT";
        public static final String BUNDLE_ACTIVATE_OBJECT = "BUNDLE_ACTIVATE_OBJECT";
        public static final String BUNDLE_VOUCHER_STATUS = "BUNDLE_VOUCHER_STATUS";
        public static final String BUNDLE_VOUCHER_DEAL_ID = "BUNDLE_VOUCHER_DEAL_ID";
        public static final String BUNDLE_VOUCHER_STORE_ID = "BUNDLE_VOUCHER_STORE_ID";
        public static final String BUNDLE_DEAL_DETAIL_FROM_VOUCHER = "BUNDLE_DEAL_DETAIL_FROM_VOUCHER";
        public static final String BUNDLE_NOTIF_MODEL = "BUNDLE_NOTIF_MODEL";
    }

    public static final class PreferenceKeys {
        public static final String TOKEN = "TOKEN";
        public static final String TOKEN_CERATED_DATE = "TOKEN_CERATED_DATE";
        public static final String USER = "USER";
        public static final String FCM_TOKEN = "FCM_TOKEN";
        public static final String FCM_TOKEN_CERATED_DATE = "FCM_TOKEN_CERATED_DATE";
        public static final String SETTINGS_URL_NEWSLETTER = "SETTINGS_URL_NEWSLETTER";
        public static final String SETTINGS_URL_INFINITY_PORTAL = "SETTINGS_URL_INFINITY_PORTAL";
        public static final String SETTINGS_HELPDESK_NUMBER = "SETTINGS_HELPDESK_NUMBER";
    }
}
