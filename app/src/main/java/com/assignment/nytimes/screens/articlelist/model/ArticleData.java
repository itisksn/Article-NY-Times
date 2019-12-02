package com.assignment.nytimes.screens.articlelist.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ArticleData implements Parcelable {

    public static final Creator<ArticleData> CREATOR = new Creator<ArticleData>() {
        @Override
        public ArticleData createFromParcel(Parcel in) {
            return new ArticleData(in);
        }

        @Override
        public ArticleData[] newArray(int size) {
            return new ArticleData[size];
        }
    };
    private String copyright;
    private ArrayList<ArticleDataResults> results;
    private int num_results;
    private String status;

    public ArticleData() {

    }

    protected ArticleData(Parcel in) {
        copyright = in.readString();
        results = in.createTypedArrayList(ArticleDataResults.CREATOR);
        num_results = in.readInt();
        status = in.readString();
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ArrayList<ArticleDataResults> getResults() {
        return this.results;
    }

    public void setResults(ArrayList<ArticleDataResults> results) {
        this.results = results;
    }

    public int getNum_results() {
        return this.num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(copyright);
        dest.writeTypedList(results);
        dest.writeInt(num_results);
        dest.writeString(status);
    }
}
