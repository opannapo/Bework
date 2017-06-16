package napodev.framework.bework.corebase.worker.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 3/21/17.
 */
public abstract class BaseScrollingListener extends RecyclerView.OnScrollListener {
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    int visibleItemCount;
    int totalItemCount;
    int firstVisibleItemIndex;
    String signatureLastDetectedValues = "";
    int scrollState;

    public BaseScrollingListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        this.recyclerView = recyclerView;
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        Log.d("SCROLLING STATE " + (newState == 0 ? "STOP" : (newState == 1 ? "keyDown" : "keyUp")));
        scrollState = newState;
        if (newState == 0) {
            onStateStop();
        } else if (newState == 1) {
            onStateKeyDown();
        } else {
            onStateKeyUp();
        }
    }

    @Override
    public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItemIndex = linearLayoutManager.findFirstVisibleItemPosition();


        Log.d("SCROLLING items visibleItemCount " + visibleItemCount);
        Log.d("SCROLLING items totalItemCount " + totalItemCount);
        Log.d("SCROLLING items firstVisibleItemIndex " + firstVisibleItemIndex);


        if (dy < 0) {
            Log.d("SCROLLING UP");
            onScrollingUp(recyclerView, dx, dy);

            if (firstVisibleItemIndex == 0) {
                if (!checkLastDetectedValuesIsSame(visibleItemCount, totalItemCount, firstVisibleItemIndex)) {
                    onTopVisible(recyclerView, dx, dy);
                }
            }
        } else if (dy > 0) {
            Log.d("SCROLLING DOWN");
            onScrollingDown(recyclerView, dx, dy);
            if (visibleItemCount + firstVisibleItemIndex == totalItemCount) {
                if (!checkLastDetectedValuesIsSame(visibleItemCount, totalItemCount, firstVisibleItemIndex)) {
                    onLastVisible(recyclerView, dx, dy);
                }
            }
        }
        storeLastDetectedValues(visibleItemCount, totalItemCount, firstVisibleItemIndex);
    }


    private void storeLastDetectedValues(int visibleItemCount, int totalItemCount, int firstVisibleItemIndex) {
        signatureLastDetectedValues = String.valueOf(visibleItemCount) +
                String.valueOf(totalItemCount) +
                String.valueOf(firstVisibleItemIndex);

        Log.d("signatureCheck signatureLastDetectedValues " + signatureLastDetectedValues);
    }

    private boolean checkLastDetectedValuesIsSame(int visibleItemCount, int totalItemCount, int firstVisibleItemIndex) {
        if (signatureLastDetectedValues.equals("")) {
            return false;
        }

        String compareSignature = String.valueOf(visibleItemCount) +
                String.valueOf(totalItemCount) +
                String.valueOf(firstVisibleItemIndex);

        Log.d("signatureCheck compareSignature " + compareSignature + " : " + "signatureLastDetectedValues " + signatureLastDetectedValues);

        return signatureLastDetectedValues.equals(compareSignature);
    }


    public abstract void onStateStop();

    public abstract void onStateKeyDown();

    public abstract void onStateKeyUp();

    public abstract void onScrollingUp(RecyclerView recyclerView, int dx, int dy);

    public abstract void onScrollingDown(RecyclerView recyclerView, int dx, int dy);

    public abstract void onLastVisible(RecyclerView recyclerView, int dx, int dy);

    public abstract void onTopVisible(RecyclerView recyclerView, int dx, int dy);
}
