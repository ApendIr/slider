package ir.apend.slider.model;


import java.io.Serializable;

/**
 * Created by Farzad Farazmand on 28,June,2017
 * farzad.farazmand@gmail.com
 */

public class Slide implements Serializable{

    private int id;
    private String imageUrl;
    private int imageCorner;

    public Slide(int id, String imageUrl,int imageCorner) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.imageCorner = imageCorner;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageCorner() {
        return imageCorner;
    }

    public void setImageCorner(int imageCorner) {
        this.imageCorner = imageCorner;
    }
}
