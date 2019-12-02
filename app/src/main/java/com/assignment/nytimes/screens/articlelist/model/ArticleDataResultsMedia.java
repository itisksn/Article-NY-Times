package com.assignment.nytimes.screens.articlelist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArticleDataResultsMedia implements Parcelable {

    public static final Creator<ArticleDataResultsMedia> CREATOR = new Creator<ArticleDataResultsMedia>() {
        @Override
        public ArticleDataResultsMedia createFromParcel(Parcel in) {
            return new ArticleDataResultsMedia(in);
        }

        @Override
        public ArticleDataResultsMedia[] newArray(int size) {
            return new ArticleDataResultsMedia[size];
        }
    };
    private String copyright;

    @SerializedName("media-metadata")
    private ArrayList<ArticleDataResultsMediaMediaMetadata> media_metadata;
    private String subtype;
    private String caption;
    private String type;
    private int approved_for_syndication;

    protected ArticleDataResultsMedia(Parcel in) {
        copyright = in.readString();
        media_metadata = in.createTypedArrayList(ArticleDataResultsMediaMediaMetadata.CREATOR);
        subtype = in.readString();
        caption = in.readString();
        type = in.readString();
        approved_for_syndication = in.readInt();
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ArrayList<ArticleDataResultsMediaMediaMetadata> getMedia_metadata() {
        return this.media_metadata;
    }

    public void setMedia_metadata(ArrayList<ArticleDataResultsMediaMediaMetadata> media_metadata) {
        this.media_metadata = media_metadata;
    }

    public String getSubtype() {
        return this.subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getApproved_for_syndication() {
        return this.approved_for_syndication;
    }

    public void setApproved_for_syndication(int approved_for_syndication) {
        this.approved_for_syndication = approved_for_syndication;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(copyright);
        dest.writeTypedList(media_metadata);
        dest.writeString(subtype);
        dest.writeString(caption);
        dest.writeString(type);
        dest.writeInt(approved_for_syndication);
    }
}
