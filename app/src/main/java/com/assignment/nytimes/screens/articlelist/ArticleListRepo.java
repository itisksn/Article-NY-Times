package com.assignment.nytimes.screens.articlelist;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.assignment.nytimes.R;
import com.assignment.nytimes.network.CallServer;
import com.assignment.nytimes.network.Resource;
import com.assignment.nytimes.screens.articlelist.model.ArticleData;
import com.assignment.nytimes.utils.DateComparator;
import com.assignment.nytimes.utils.Utils;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleListRepo {

    private Application application;

    ArticleListRepo(Application application) {
        this.application = application;

    }

    public static ArticleListRepo get(Application application) {
        return new ArticleListRepo(application);
    }

    public LiveData<Resource<ArticleData>> articleDataList(final Context c, int period) {
        if (!Utils.isNetworkAvailable(application)) {
            Toast.makeText(application, "" + application.getString(R.string.internet_not_working), Toast.LENGTH_LONG).show();
            return null;
        }

        final MutableLiveData<Resource<ArticleData>> data = new MutableLiveData<>();
        data.setValue(Resource.<ArticleData>loading(null));

        CallServer.get().getAPIName().getArticles(period).enqueue(new Callback<ArticleData>() {
            @Override
            public void onResponse(Call<ArticleData> call, Response<ArticleData> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Collections.sort(response.body().getResults(), new DateComparator());
                        data.setValue(Resource.success(response.body()));
                    } else
                        data.setValue(Resource.error(response.message(), null, 0, null));

                }
            }

            @Override
            public void onFailure(Call<ArticleData> call, Throwable t) {
                data.setValue(Resource.error(CallServer.serverError, null, 0, t));

            }
        });
        return data;
    }

}
