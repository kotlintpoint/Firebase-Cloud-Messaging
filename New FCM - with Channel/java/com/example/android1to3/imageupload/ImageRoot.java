package com.example.android1to3.imageupload;

import com.google.gson.annotations.SerializedName;

import java.util.List;




@SuppressWarnings("all")
public class ImageRoot {
    @SerializedName("status")
    private final String status;

    @SerializedName("images")
    private final List<Images> images;

    public ImageRoot(String status, List<Images> images) {
        this.status = status;
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public List<Images> getImages() {
        return images;
    }

    public static class Images {
        @SerializedName("id")
        private final String id;

        @SerializedName("image")
        private final String image;

        public Images(String id, String image) {
            this.id = id;
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public String getImage() {
            return image;
        }
    }
}
