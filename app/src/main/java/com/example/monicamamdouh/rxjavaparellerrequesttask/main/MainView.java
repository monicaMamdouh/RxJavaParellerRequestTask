package com.example.monicamamdouh.rxjavaparellerrequesttask.main;

import com.example.monicamamdouh.rxjavaparellerrequesttask.base.BaseView;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.MergedObjectList;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.contacts.Contact;

import java.util.List;

interface MainView extends BaseView {

    void showProgress(boolean show);

    void onRetrieveContactsSuccess(MergedObjectList mergedObjectList);

    void onRetrieveContactsError(String error);

    void onRetrieveSaveSuccess(List<Contact> contactsList);

    void initializeAdapter();

}
