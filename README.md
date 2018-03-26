# Slider

A beautiful and also simple image slider for android!

## Screenshots

[](https://user-images.githubusercontent.com/35394143/37894691-3c42681e-30f4-11e8-8a94-ffe08c4acbea.gif)

[Download Demo](https://github.com/ApendIr/slider/blob/master/demo/release/demo-release.apk?raw=true)

## Getting Started

First add xml view :
```xml
<ir.apend.slider.ui.Slider
            android:id="@+id/slider"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:animateIndicators="true"
            app:defaultIndicators="circle"
            app:indicatorSize="8dp"
            app:intervalSecond="5"
            app:loopSlides="true" />
```
then, add slide item :
```java
Slider slider = findViewById(R.id.slider);

//create list of slides
List<Slide> slideList = new ArrayList<>();
slideList.add(new Slide(0,"http://cssslider.com/sliders/demo-20/data1/images/picjumbo.com_img_4635.jpg" , getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
slideList.add(new Slide(1,"http://cssslider.com/sliders/demo-12/data1/images/picjumbo.com_hnck1995.jpg" , getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
slideList.add(new Slide(2,"http://cssslider.com/sliders/demo-19/data1/images/picjumbo.com_hnck1588.jpg" , getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
slideList.add(new Slide(3,"http://wowslider.com/sliders/demo-18/data1/images/shanghai.jpg" , getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));

//handle slider click listener
slider.setItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //do what you want
    }
});

//add slides to slider
slider.addSlides(slideList);
```
### Installing

Add library module to your project and that's it! Gradle support will be added soon.


## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

