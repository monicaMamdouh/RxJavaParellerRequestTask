package com.example.monicamamdouh.rxjavaparellerrequesttask.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.monicamamdouh.rxjavaparellerrequesttask.base.BasePresenter;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.helper.ServiceHelper;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.MergedObject;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.MergedObjectList;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.contacts.ContactsResponse;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter {

    private MainView mainView;
    private Context context;

    MainPresenter(Context context, MainView mainView) {
        this.context = context;
        this.mainView = mainView;
    }

//    Observer<MergedObjectList> singleObserver = new Observer<MergedObjectList>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onNext(MergedObjectList mergedObjectList) {
//
//            mainView.showProgress(false);
//            mainView.onRetrieveContactsSuccess(mergedObjectList);
//        }
//
//        @Override
//        public void onError(Throwable e) {
//
//        }
//
//        @Override
//        public void onComplete() {
//        }
//    };
    void getContacts() {
        mainView.showProgress(true);

        ServiceHelper.getInstance(context).callService(combineObserver,dataBaseObserver);

    }

    SingleObserver<MergedObjectList> combineObserver = new SingleObserver<MergedObjectList>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSuccess(MergedObjectList mergedObjectList) {
            mainView.showProgress(false);
            mainView.onRetrieveContactsSuccess(mergedObjectList);
        }

        @Override
        public void onError(Throwable e) {

        }
    };


    SingleObserver<ContactsResponse> dataBaseObserver=new SingleObserver<ContactsResponse>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSuccess(ContactsResponse s) {


            mainView.onRetrieveSaveSuccess(s.getContacts());
           // Toast.makeText(context,"hello save me in database :D ",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(Throwable e) {

        }
    };
}