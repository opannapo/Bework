package napodev.framework.bework.utils.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import napodev.framework.bework.utils.C;

/**
 * Created by opannapo on 1/18/17.
 */
public class Broadcast {
    public static void send(Context context, String key, Bundle bundle) {
        Intent intent = new Intent(C.Broadcast.BROADCAST_FILTER_NAME);
        intent.putExtra("key", key);
        intent.putExtra(C.BundleKeys.BUNDLE_EXTRA_NAME, bundle);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
