package ir.apend.slider.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import java.util.List;
import java.util.Random;

import ir.apend.slider.model.Slide;
import ir.apend.slider.ui.adapter.SliderAdapter;
import ir.apend.slider.ui.customUI.LooperWrapViewPager;
import ir.apend.slider.ui.indicators.IndicatorShape;
import ir.apend.slider.ui.indicators.SlideIndicatorsGroup;
import ir.apend.sliderlibrary.R;

/**
 * Created by Farzad Farazmand on 28,June,2017
 * farzad.farazmand@gmail.com
 */

public class Slider extends FrameLayout implements ViewPager.OnPageChangeListener {

    public static final String TAG = "SLIDER";

    private LooperWrapViewPager viewPager;
    private AdapterView.OnItemClickListener itemClickListener;

    //Custom attributes
    private Drawable selectedSlideIndicator;
    private Drawable unSelectedSlideIndicator;
    private int defaultIndicator;
    private int indicatorSize;
    private boolean mustAnimateIndicators;
    private boolean mustLoopSlides;
    private SlideIndicatorsGroup slideIndicatorsGroup;
    private int slideShowInterval = 5000;

    private Handler handler = new Handler();
    private int slideCount;
    private int currentPageNumber;
    private boolean hideIndicators = false;

    public Slider(@NonNull Context context) {
        super(context);
    }

    public Slider(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseCustomAttributes(attrs);
    }

    public Slider(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseCustomAttributes(attrs);
    }

    private void parseCustomAttributes(AttributeSet attributeSet) {
        try {
            if (attributeSet != null) {
                TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.BannerSlider);
                try {
                    indicatorSize = typedArray.getDimensionPixelSize(R.styleable.BannerSlider_indicatorSize, getResources().getDimensionPixelSize(R.dimen.default_indicator_size));
                    selectedSlideIndicator = typedArray.getDrawable(R.styleable.BannerSlider_selected_slideIndicator);
                    unSelectedSlideIndicator = typedArray.getDrawable(R.styleable.BannerSlider_unselected_slideIndicator);
                    defaultIndicator = typedArray.getInt(R.styleable.BannerSlider_defaultIndicators, IndicatorShape.CIRCLE);
                    mustAnimateIndicators = typedArray.getBoolean(R.styleable.BannerSlider_animateIndicators, true);
                    mustLoopSlides = typedArray.getBoolean(R.styleable.BannerSlider_loopSlides, false);
                    hideIndicators = typedArray.getBoolean(R.styleable.BannerSlider_hideIndicators, false);
                    int slideShowIntervalSecond = typedArray.getInt(R.styleable.BannerSlider_intervalSecond, 5);
                    slideShowInterval = slideShowIntervalSecond * 1000;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    typedArray.recycle();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSlides(List<Slide> slideList) {
        if (slideList == null || slideList.size() == 0)
            return;

        viewPager = new LooperWrapViewPager(getContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            viewPager.setId(View.generateViewId());
        } else {
            int id = Math.abs(new Random().nextInt((5000 - 1000) + 1) + 1000);
            viewPager.setId(id);
        }
        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewPager.addOnPageChangeListener(Slider.this);
        addView(viewPager);
        SliderAdapter adapter = new SliderAdapter(getContext(), slideList, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(adapterView, view, i, l);
            }
        });
        viewPager.setAdapter(adapter);
        slideCount = slideList.size();
        viewPager.setCurrentItem(slideCount - 1);
        if (!hideIndicators && slideCount > 1) {
            slideIndicatorsGroup = new SlideIndicatorsGroup(getContext(), selectedSlideIndicator, unSelectedSlideIndicator, defaultIndicator, indicatorSize, mustAnimateIndicators);
            addView(slideIndicatorsGroup);
            slideIndicatorsGroup.setSlides(slideCount);
            slideIndicatorsGroup.onSlideChange(slideCount - 1);
        }
        if (slideCount > 1)
            setupTimer();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPageNumber = position;
        if (slideIndicatorsGroup != null && !hideIndicators) {
            if (position == 0) {
                slideIndicatorsGroup.onSlideChange(slideCount - 1);
            } else if (position == slideCount + 1) {
                slideIndicatorsGroup.onSlideChange(0);
            } else {
                slideIndicatorsGroup.onSlideChange(position - 1);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                handler.removeCallbacksAndMessages(null);
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                setupTimer();
                break;
        }
    }

    private void setupTimer() {
        try {
            if (mustLoopSlides) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (currentPageNumber < slideCount)
                                currentPageNumber += 1;
                            else
                                currentPageNumber = 1;

                            viewPager.setCurrentItem(currentPageNumber - 1, true);

                            handler.removeCallbacksAndMessages(null);
                            handler.postDelayed(this, slideShowInterval);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, slideShowInterval);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // setters
    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setHideIndicators(boolean hideIndicators) {
        this.hideIndicators = hideIndicators;
        try {
            if (hideIndicators)
                slideIndicatorsGroup.setVisibility(INVISIBLE);
            else
                slideIndicatorsGroup.setVisibility(VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
