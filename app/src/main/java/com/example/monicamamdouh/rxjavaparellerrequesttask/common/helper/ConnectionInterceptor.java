package com.example.monicamamdouh.rxjavaparellerrequesttask.common.helper;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectionInterceptor implements Interceptor {

    private Context context;

    ConnectionInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        if (!UtilsHelper.isConnectedToNetwork(context)) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build();

            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .cacheControl(cacheControl)
                    .build();
        }

        return chain.proceed(request);


    }
}