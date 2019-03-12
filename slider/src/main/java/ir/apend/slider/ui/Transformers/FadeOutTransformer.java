package ir.apend.slider.ui.Transformers;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

public class FadeOutTransformer implements ViewPager.PageTransformer{

    public void transformPage(View page, float position) {
        page.setTranslationX(-position*page.getWidth());

        page.setAlpha(1-Math.abs(position));
    }

}
