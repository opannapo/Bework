package napodev.framework.bework.utils.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by opannapo on 12/17/16.
 */
public class SpandHelper {
    public static SpannableString addQuote(Context c, String vals, int drawable) {
        String tmp = "-- " + vals + " --";
        SpannableString ss = new SpannableString(tmp);
        Drawable d = ContextCompat.getDrawable(c, drawable);
        d.setBounds(0, 0, ScreenHelper.dpToPx(10), ScreenHelper.dpToPx(10));
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        ImageSpan span2 = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        ss.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(span2, tmp.length() - 2, tmp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return ss;
    }
}
