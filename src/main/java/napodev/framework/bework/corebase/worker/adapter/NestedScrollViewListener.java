package napodev.framework.bework.corebase.worker.adapter;

import android.os.Handler;
import android.support.v4.widget.NestedScrollView;

import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 8/29/16.
 */
public abstract class NestedScrollViewListener implements NestedScrollView.OnScrollChangeListener {
    int postDelay = 500;
    long lastScrollUpdate;
    boolean isScrolling;

    int oldY;
    int curY;

    Handler h = new Handler();
    Runnable check = new Runnable() {
        @Override
        public void run() {
            long timeNow = System.currentTimeMillis();
            if ((timeNow > (lastScrollUpdate + postDelay)) && isScrolling) {
                Log.i("Runnable check STOP " + timeNow + " vs " + (lastScrollUpdate + postDelay));
                h.removeCallbacks(check);
                isScrolling = false;
                onStopScrolling(curY, oldY);
            } else {
                isScrolling = true;
                Log.i("Runnable check RUN " + timeNow + " vs " + (lastScrollUpdate + postDelay));
            }
        }
    };

    @Override
    public void onScrollChange(NestedScrollView scrollView, int x, int y, int oldx, int oldy) {
        Log.i("onScrollChange check y " + y + " vs oldy " + oldy);

        lastScrollUpdate = System.currentTimeMillis();
        h.postDelayed(check, postDelay);

        onScrolling(scrollView, x, y, oldx, oldy);
        curY = y;
        oldY = oldy;

        if (y == 0) {
            Log.i("TOP SCROLL");
            onTopChildLoaded(y);
        }

        if (y == (scrollView.getChildAt(0).getMeasuredHeight() - scrollView.getMeasuredHeight())) {
            Log.i("BOTTOM SCROLL");
            onLastChildLoaded(y);
        }

        if (y > oldy) {
            Log.i("Scroll DOWN");
            onScrollingDown(y, oldy);
        }
        if (y < oldy) {
            Log.i("Scroll UP");
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