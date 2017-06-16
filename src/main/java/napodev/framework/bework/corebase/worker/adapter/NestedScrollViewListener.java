package napodev.framework.bework.corebase.worker.adapter;

import android.os.Handler;
import android.support.v4.widget.NestedScrollView;

/**
 * Created by opannapo on 8/29/16.
 */
public abstract class NestedScrollViewListener implements NestedScrollView.OnScrollChangeListener {
    int postDelay = 1000;
    int scrollDelayToStop = 1000;
    long lastScrollUpdate;
    boolean isScrolling;
    Handler h = new Handler();
    Runnable check = new Runnable() {
        @Override
        public void run() {
            long timeNow = System.currentTimeMillis();
            if (timeNow > (lastScrollUpdate + scrollDelayToStop)) {
                //Log.i("Runnable check STOP " + timeNow + " vs " + lastScrollUpdate);
                h.removeCallbacks(check);
                isScrolling = false;
            } else {
                isScrolling = true;
                //Log.i("Runnable check RUN " + timeNow + " vs " + lastScrollUpdate);
            }
        }
    };

    @Override
    public void onScrollChange(NestedScrollView scrollView, int x, int y, int oldx, int oldy) {
        lastScrollUpdate = System.currentTimeMillis();
        h.postDelayed(check, postDelay);

        onScrolling(scrollView, x, y, oldx, oldy);

        if (y == 0) {
            //Log.i("TOP SCROLL");
            onTopChildLoaded(y);
        }

        if (y == (scrollView.getChildAt(0).getMeasuredHeight() - scrollView.getMeasuredHeight())) {
            //Log.i("BOTTOM SCROLL");
            onLastChildLoaded(y);
        }

        if (y > oldy) {
            //Log.i("Scroll DOWN");
            onScrollingDown(y, oldy);
        }
        if (y < oldy) {
            //Log.i("Scroll UP");
            onScrollingUp(y, oldy);
        }
    }


    public abstract void onScrolling(NestedScrollView scrollView, int x, int y, int oldx, int oldy);

    public abstract void onStopScrolling(int scrollY, int oldScrollY);

    public abstract void onLastChildLoaded(int scrollY);

    public abstract void onTopChildLoaded(int scrollY);

    public abstract void onScrollingDown(int scrollY, int oldScrollY);

    public abstract void onScrollingUp(int scrollY, int oldScrollY);


}