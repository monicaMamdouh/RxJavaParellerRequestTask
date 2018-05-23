package com.example.monicamamdouh.rxjavaparellerrequesttask.common.helper;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

public class NetworkConnectionInterceptor implements Interceptor {

    private Context context;

    NetworkConnectionInterceptor(Context context) {
        this.context = context;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());

        // re-write response header to force use of cache
        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(2, TimeUnit.MINUTES)
                .build();

        return response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", cacheControl.toString())
                .build();
    }

}