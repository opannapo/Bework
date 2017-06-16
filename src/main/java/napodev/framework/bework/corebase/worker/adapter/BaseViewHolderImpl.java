package napodev.framework.bework.corebase.worker.adapter;

import android.view.View;

/**
 * Created by opannapo on 8/29/16.
 */
public interface BaseViewHolderImpl {
    void onInit(View itemView);
    void onViewBinding(Object objectEntities, int position);
}
