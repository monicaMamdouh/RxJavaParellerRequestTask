package com.example.monicamamdouh.rxjavaparellerrequesttask.common.helper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.MergedObjectList;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.contacts.ContactsResponse;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.news.NewsResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceHelper {
    private static Retrofit retrofit;
    private Context context;
    private static final String BASE_URL = "https://api.androidhive.info";
    private static RetrofitInterface retrofitInterface;
    private static ServiceHelper mInstance;
    private OkHttpClient okHttpClient;
    private Cache cache;
    private SingleObserver<ContactsResponse> responseObservable;
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10 MB


    public static ServiceHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ServiceHelper(context);
        }
        return mInstance;
    }

    private ServiceHelper(Context context) {


        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(buildOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    private OkHttpClient buildOkHttpClient() {
        createCacheForOkHTTP();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkConnectionInterceptor(context))
                .addInterceptor(new ConnectionInterceptor(context))
                .cache(cache)
                .build();
        return okHttpClient;
    }

    private void createCacheForOkHTTP() {
        cache = new Cache(context.getCacheDir(), DISK_CACHE_SIZE);
    }


    public void callService(SingleObserver<MergedObjectList> combineObserver,SingleObserver<ContactsResponse> databaseObserver) {

        Single<ContactsResponse> contactsResponseSingle=retrofitInterface.getContacts().subscribeOn(Schedulers.newThread());
        Single<NewsResponse> newsResponseSingle=retrofitInterface.getNews().subscribeOn(Schedulers.newThread());

        Single<MergedObjectList> mergedObjectObservable = Single.zip(newsResponseSingle,
                contactsResponseSingle,
                new BiFunction<NewsResponse, ContactsResponse, MergedObjectList>() {
                    @Override
                    public MergedObjectList apply(NewsResponse newsResponse, ContactsResponse contactsResponse) throws Exception {

                        MergedObjectList mergedObject = new MergedObjectList();
                        mergedObject.setNameList(newsResponse.getSources());
                        mergedObject.setNewsList(contactsResponse.getContacts());
                        return mergedObject;

                    }
                });

        contactsResponseSingle.observeOn(Schedulers.io()).subscribe(databaseObserver);
        mergedObjectObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(combineObserver);




    }


}
