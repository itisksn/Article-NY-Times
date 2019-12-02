package com.assignment.nytimes.network;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHTTPHelper {


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String TAG = "OkHTTPHelper";
    private static final long DEFAULT_SERVER_TIME_OUT = 90; // Seconds
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private static OkHTTPHelper instance;
    private OkHttpClient client;

    private OkHTTPHelper() {
        client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_SERVER_TIME_OUT, DEFAULT_TIME_UNIT)
                .readTimeout(DEFAULT_SERVER_TIME_OUT, DEFAULT_TIME_UNIT)
                .writeTimeout(DEFAULT_SERVER_TIME_OUT, DEFAULT_TIME_UNIT)
                .build();
    }


    public static OkHTTPHelper getInstance() {
        if (instance == null) {
            Log.i(TAG, "getInstance: OkHTTPHelper is null creating new instance.");
            synchronized (OkHTTPHelper.class) {
                if (instance == null) {
                    instance = new OkHTTPHelper();
                }
            }
        }
        return instance;
    }

    private static JsonObject combineSignature(@NonNull JsonObject jsonObject,
                                               @Nullable String signature) {
        JsonObject obj = new JsonObject();
        obj.add("data", jsonObject);
        obj.addProperty("signature", (TextUtils.isEmpty(signature) ? "" : signature));
        return obj;
    }

    public OkHttpClient getClient() {
        return client;
    }


    @MainThread
    public void asyncServiceCall(@NonNull String url,
                                 @NonNull JsonObject jsonObject,
                                 @NonNull Callback callback,
                                 @Nullable String signature) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        Log.d(TAG, "asyncServiceCall: URL \"%s\"" + url);

        RequestBody body = RequestBody.create(JSON, (TextUtils.isEmpty(signature) ?
                jsonObject.toString() :
                combineSignature(jsonObject, signature).toString()));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);

    }

    @WorkerThread
    public JsonElement syncServiceCall(@NonNull String url,
                                       @NonNull JsonObject jsonObject,
                                       @Nullable String signature) {
        if (TextUtils.isEmpty(url)) {
            return JsonNull.INSTANCE;
        }

        Log.d(TAG, "asyncServiceCall: URL \"%s\"" + url);

        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JsonParser parser = new JsonParser();
            return parser.parse(response.body().string()).getAsJsonObject();
        } catch (IOException e) {
            Log.e(TAG, "syncServiceCall: " + e.toString());
        }
        return JsonNull.INSTANCE;
    }

}
