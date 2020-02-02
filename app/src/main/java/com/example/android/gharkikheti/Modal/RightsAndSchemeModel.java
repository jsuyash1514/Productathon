package com.example.android.gharkikheti.Modal;

import android.graphics.drawable.Drawable;

public class RightsAndSchemeModel {
    String heading, description;
    Drawable image;

    public RightsAndSchemeModel(String heading, String description, Drawable image) {
        this.heading = heading;
        this.description = description;
        this.image = image;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
