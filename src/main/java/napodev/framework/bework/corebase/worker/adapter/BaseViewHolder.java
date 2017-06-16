package napodev.framework.bework.corebase.worker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by opannapo on 8/29/16.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder implements BaseViewHolderImpl, View.OnClickListener {
    private int viewType;
    private BaseRecyclerViewAdapter adapter;
    public Context context;
    private int itemsPosition;

    public BaseViewHolder(View itemView, BaseRecyclerViewAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        this.context = itemView.getContext();
        onInit(itemView);
    }

    public void bindViews(Object objectEntities, final int position) {
        setItemsPosition(position);
        onViewBinding(objectEntities, position);
    }


    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getViewType(int viewType) {
        return this.viewType;
    }

    public BaseRecyclerViewAdapter getAdapter() {
        return this.adapter;
    }

    public View getItemView() {
        return this.itemView;
    }

    public int getItemsPosition() {
        return itemsPosition;
    }

    public void setItemsPosition(int itemsPosition) {
        this.itemsPosition = itemsPosition;
    }


    public Context getContext() {
        return context;
    }

    @Override
    public void onClick(View view) {
        getAdapter().getOnItemClickListener().onItemsClicked(getItemViewType(), getItemsPosition(), view);
    }
}