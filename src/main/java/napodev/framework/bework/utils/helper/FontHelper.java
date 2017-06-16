package napodev.framework.bework.utils.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by opannapo on 12/17/16.
 */
public class FontHelper {
    private Typeface font;
    private Typeface fontBold;
    private Typeface fontItalic;
    private boolean hasFontBold;
    private boolean hasFontItalic;

    public FontHelper with(Context context, String typeface) {
        if (typeface != null) {
            font = Typeface.createFromAsset(context.getAssets(), typeface);
            hasFontItalic = false;
            hasFontBold = false;
        }

        return this;
    }

    public FontHelper with(Context context, String typefaceNormal, String typefaceBold, String typefaceItalic) {
        if (typefaceNormal != null) {
            font = Typeface.createFromAsset(context.getAssets(), typefaceNormal);
        }
        if (typefaceBold != null) {
            fontBold = Typeface.createFromAsset(context.getAssets(), typefaceBold);
            hasFontBold = true;
        }
        if (typefaceItalic != null) {
            fontItalic = Typeface.createFromAsset(context.getAssets(), typefaceItalic);
            hasFontItalic = true;
        }

        return this;
    }

    public void setInto(ViewGroup v) {
        generate(v);
    }

    private void generate(ViewGroup v) {
        for (int i = 0; i < v.getChildCount(); i++) {
            View vChild = v.getChildAt(i);
            if (vChild instanceof TextView) {
                TextView t = ((TextView) vChild);
                if ((!hasFontBold) && (!hasFontItalic)) {//only default
                    if (font != null) t.setTypeface(font);
                } else {
                    if (t.getTypeface() != null) {
                        if (t.getTypeface().getStyle() == Typeface.BOLD) {
                            if (fontBold != null) t.setTypeface(fontBold);
                        } else if (t.getTypeface().getStyle() == Typeface.ITALIC) {
                            if (fontItalic != null) t.setTypeface(fontItalic);
                        } else {
                            if (font != null) t.setTypeface(font);
                        }
                    } else {
                        if (font != null) t.setTypeface(font);
                    }
                }
            } else {
                if (vChild instanceof ViewGroup) {
                    generate((ViewGroup) v.getChildAt(i));
                }
            }
        }
    }
}
