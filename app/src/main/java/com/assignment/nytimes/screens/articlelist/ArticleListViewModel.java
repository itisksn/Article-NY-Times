package com.assignment.nytimes.screens.articlelist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.assignment.nytimes.network.Resource;
import com.assignment.nytimes.screens.articlelist.model.ArticleData;

public class ArticleListViewModel extends AndroidViewModel {

    private LiveData<Resource<ArticleData>> articleLiveData;
    private int period;

    public ArticleListViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<Resource<ArticleData>> getLiveDataProperty(int period) {
        if (this.period != period) {
            this.period = period;
            articleLiveData = ArticleListRepo.get(getApplication()).articleDataList(getApplication().getApplicationContext(), period);
        }

        return articleLiveData;
    }


}
