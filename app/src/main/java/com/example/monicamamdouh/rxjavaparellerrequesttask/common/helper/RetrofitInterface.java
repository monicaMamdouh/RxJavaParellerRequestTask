package com.example.monicamamdouh.rxjavaparellerrequesttask.common.helper;

import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.contacts.ContactsResponse;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.news.NewsResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

interface RetrofitInterface {
    @GET("/contacts/")
    Single<ContactsResponse> getContacts();


    @GET("https://newsapi.org/v1/sources")
    Single<NewsResponse> getNews();

}