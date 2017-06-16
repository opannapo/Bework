package napodev.framework.bework.corebase.worker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


/**
 * Created by opannapo on 8/24/16.
 */
public interface BaseRecyclerViewAdapterImplement {
    int contentItemsViewType(int position);

    RecyclerView.ViewHolder createItemsViewHolder(ViewGroup parent, int viewType);

    BaseViewHolder createHeaderViewHolder(ViewGroup parent, int viewType);

    BaseViewHolder createFooterViewHolder(ViewGroup parent, int viewType);

}
