package org.hwyl.sexytopo.model.sketch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import org.hwyl.sexytopo.R;


public enum Symbol {

    STALAGMITE(R.drawable.stalagmite),
    STALACTITE(R.drawable.stalactite);

    private static Resources resources;

    private int bitmapId;
    private Bitmap bitmap;
    private Bitmap buttonBitmap;

    Symbol(int bitmapId) {
        this.bitmapId = bitmapId;
    }

    public static void setResources(Resources resources) {
        Symbol.resources = resources;
    }

    public int getBitmapId() {
        return bitmapId;
    }

    public Bitmap getBitmap() {
        if (resources == null) {
            return null;
        }
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(resources, bitmapId);
        }
        return bitmap;
    }

    public Bitmap getButtonBitmap() {
        if (resources == null) {
            return null;
        }

        if (buttonBitmap == null) {
            int dimen = (int)resources.getDimension(R.dimen.toolbar_button_height);
            buttonBitmap = Bitmap.createScaledBitmap(getBitmap(), dimen, dimen, true);
        }

        return buttonBitmap;
    }

    public Bitmap getBitmapWithHeightDp(float heightDp) {
        int heightInPixels = (int)convertDpToPixel(heightDp);
        return Bitmap.createScaledBitmap(getBitmap(), heightInPixels, heightInPixels, true);
    }

    private static float convertDpToPixel(float dp){
        return dp * ((float)
            resources.getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }


    public static Symbol getDefault() {
        return STALACTITE;
    }

}
