package com.assignment.nytimes.screens.articlelist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ArticleDataResultsMediaMediaMetadata implements Parcelable {
    public static final Creator<ArticleDataResultsMediaMediaMetadata> CREATOR = new Creator<ArticleDataResultsMediaMediaMetadata>() {
        @Override
        public ArticleDataResultsMediaMediaMetadata createFromParcel(Parcel source) {
            ArticleDataResultsMediaMediaMetadata var = new ArticleDataResultsMediaMediaMetadata();
            var.format = source.readString();
            var.width = source.readInt();
            var.url = source.readString();
            var.height = source.readInt();
            return var;
        }

        @Override
        public ArticleDataResultsMediaMediaMetadata[] newArray(int size) {
            return new ArticleDataResultsMediaMediaMetadata[size];
        }
    };
    private String format;
    private int width;
    private String url;
    private int height;

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.format);
        dest.writeInt(this.width);
        dest.writeString(this.url);
        dest.writeInt(this.height);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
