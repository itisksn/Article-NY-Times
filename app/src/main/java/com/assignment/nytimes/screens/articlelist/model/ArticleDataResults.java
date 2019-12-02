package com.assignment.nytimes.screens.articlelist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArticleDataResults implements Parcelable {

    public static final Creator<ArticleDataResults> CREATOR = new Creator<ArticleDataResults>() {
        @Override
        public ArticleDataResults createFromParcel(Parcel in) {
            return new ArticleDataResults(in);
        }

        @Override
        public ArticleDataResults[] newArray(int size) {
            return new ArticleDataResults[size];
        }
    };
    private String column;
    private String section;
    private String source;
    private long asset_id;
    private ArrayList<ArticleDataResultsMedia> media;
    private String type;
    private String title;
    private String uri;
    private String url;
    @SerializedName("abstract")
    private String abstractDesc;
    private String adx_keywords;
    private long id;
    private String byline;
    private String published_date;
    private int views;

    protected ArticleDataResults(Parcel in) {
        column = in.readString();
        section = in.readString();
        source = in.readString();
        asset_id = in.readLong();
        media = in.createTypedArrayList(ArticleDataResultsMedia.CREATOR);
        type = in.readString();
        title = in.readString();
        uri = in.readString();
        url = in.readString();
        abstractDesc = in.readString();
        adx_keywords = in.readString();
        id = in.readLong();
        byline = in.readString();
        published_date = in.readString();
        views = in.readInt();
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(long asset_id) {
        this.asset_id = asset_id;
    }

    public ArrayList<ArticleDataResultsMedia> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<ArticleDataResultsMedia> media) {
        this.media = media;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAbstractDesc() {
        return abstractDesc;
    }

    public void setAbstractDesc(String abstractDesc) {
        this.abstractDesc = abstractDesc;
    }

    public String getAdx_keywords() {
        return adx_keywords;
    }

    public void setAdx_keywords(String adx_keywords) {
        this.adx_keywords = adx_keywords;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(column);
        dest.writeString(section);
        dest.writeString(source);
        dest.writeLong(asset_id);
        dest.writeTypedList(media);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(uri);
        dest.writeString(url);
        dest.writeString(abstractDesc);
        dest.writeString(adx_keywords);
        dest.writeLong(id);
        dest.writeString(byline);
        dest.writeString(published_date);
        dest.writeInt(views);
    }
}
