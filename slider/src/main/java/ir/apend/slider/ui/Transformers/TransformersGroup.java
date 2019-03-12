package ir.apend.slider.ui.Transformers;

import android.content.Context;
import android.support.v4.view.ViewPager;

public class TransformersGroup {

    private static final int ANTI_CLOCK_SPIN=1;
    private static final int CAROUSEL_EFFECT=2;
    private static final int CLOCK_SPIN=3;
    private static final int CUBE_IN_ROTATION=4;
    private static final int CUBE_OUT_ROTATION=5;
    private static final int DEPTH=6;
    private static final int FADE_OUT=7;
    private static final int HORIZONTAL_FLIP =8;
    private static final int SIMPLE=9;
    private static final int SPINNER=10;
    private static final int VERTICAL_FLIP=11;
    private static final int VERTICAL_SHUT=12;
    private static final int ZOOM_OUT=13;

    public ViewPager.PageTransformer getTransformer(Context context,int transformer){

        switch (transformer){
            case ANTI_CLOCK_SPIN :
                return new AntiClockSpinTransformer();
            case CAROUSEL_EFFECT :
                return new CarouselEffectTransformer(context);
            case CLOCK_SPIN:
                return new ClockSpinTransformer();
            case CUBE_IN_ROTATION :
                return new CubeInRotationTransformer();
            case CUBE_OUT_ROTATION:
                return new CubeOutRotationTransformer();
            case DEPTH :
                return new  DepthTransformer();
            case FADE_OUT:
                return new FadeOutTransformer();
            case HORIZONTAL_FLIP:
                return new HorizontalFlipTransformer();
            case SIMPLE:
                return new SimpleTransformer();
            case SPINNER:
                return new SpinnerTransformer();
            case VERTICAL_FLIP:
                return new VerticalFlipTransformer();
            case VERTICAL_SHUT:
                return new VerticalShutTransformer();
            default:
                return new ZoomOutTransformer();
        }
    }
}
