package napodev.framework.bework.corebase.worker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

import napodev.framework.bework.corebase.model.data.BaseDataModel;

/**
 * Created by opannapo on 8/24/16.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements BaseRecyclerViewAdapterImplement {

    private final String TAG = "BaseRecyclerViewAdapter";
    public Context context;
    private ArrayList<Object> dataModels;
    public boolean isHeaderAvailable;
    public boolean isFooterAvailable;
    public BaseViewHolder header;
    public BaseViewHolder footer;
    public Object headerObject;
    public Object footerObject;
    private OnAdapterItemsClickListener clickListener;

    public enum ACTION_TYPE {
        ADD, REPLACE, DELETE
    }

    public enum VIEW_TYPE {
        HEADER(0), FOOTER(1000);
        final int value;

        VIEW_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public BaseRecyclerViewAdapter(Context context, ArrayList<Object> dataModels, Object headerObject, Object footerObject) {
        this.context = context;
        this.dataModels = dataModels;
        this.isHeaderAvailable = headerObject != null;
        this.isFooterAvailable = footerObject != null;
        this.headerObject = headerObject;
        this.footerObject = footerObject;
        Log.d(TAG, "BaseRecyclerViewAdapter SUPER ");
    }

    @Override
    public int getItemCount() {
        if (dataModels == null) return 0;

        int count = dataModels.size();
        if (isHeaderAvailable) {
            count = count + 1;
        }
        if (isFooterAvailable) {
            count = count + 1;
        }

        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderAvailable) {
            if (position == 0) {
                return VIEW_TYPE.HEADER.getValue();
            } else if ((position == dataModels.size() + 1) && isFooterAvailable) {
                return VIEW_TYPE.FOOTER.getValue();
            } else {
                return contentItemsViewType(position - 1);
            }
        } else {
            if ((position == dataModels.size()) && isFooterAvailable) {
                return VIEW_TYPE.FOOTER.getValue();
            } else {
                return contentItemsViewType(position);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (viewType == VIEW_TYPE.HEADER.getValue()) {
            Log.d(TAG, "onCreateViewHolder " + viewType);
            return createHeaderViewHolder(parent, viewType);
        } else if (viewType == VIEW_TYPE.FOOTER.getValue()) {
            Log.d(TAG, "onCreateViewHolder " + viewType);
            return createFooterViewHolder(parent, viewType);
        } else {
            Log.d(TAG, "onCreateViewHolder " + viewType);
            return createItemsViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + holder);
        if (holder.getItemViewType() == VIEW_TYPE.HEADER.getValue()) {
            header.bindViews(headerObject, position);
        } else if (holder.getItemViewType() == VIEW_TYPE.FOOTER.getValue()) {
            footer.bindViews(footerObject, position);
        } else {
            if (isHeaderAvailable) {
                ((BaseViewHolder) holder).bindViews(dataModels.get(position - 1), position - 1);
            } else {
                ((BaseViewHolder) holder).bindViews(dataModels.get(position), position);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        if (isHeaderAvailable) {
            return position - 1;
        } else {
            return position;
        }
    }

    public Object getContentByPosition(int position) {
        return dataModels.get(position);
    }

    public void setOnItemClickListener(OnAdapterItemsClickListener listener) {
        this.clickListener = listener;
    }

    public OnAdapterItemsClickListener getOnItemClickListener() {
        return clickListener;
    }

    public void notifyHeader(Object object) {
        header.bindViews(object, 0);
    }

    public void notifyFooter(Object object) {
        if (footer != null)
            footer.bindViews(object, dataModels.size());
    }

    public void addMore(final ArrayList<BaseDataModel> object) {
        dataModels.addAll(object);
        notifyDataSetChanged();
    }

    public void updateDefaultContentByIndex(int index, Object object, ACTION_TYPE action_type) {

    }
}
