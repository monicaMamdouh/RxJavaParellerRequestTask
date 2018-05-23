package com.example.monicamamdouh.rxjavaparellerrequesttask.common.models;

import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.contacts.Contact;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.news.Source;

import java.util.List;

public class MergedObjectList {
    private List<MergedObject> mergedObjectsList;

    public List<MergedObject> getMergedObjectsList() {
        return mergedObjectsList;
    }

    public List<Source> getNameList() {
        return nameList;
    }

    public void setNameList(List<Source> nameList) {
        this.nameList = nameList;
    }

    public List<Contact> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<Contact> newsList) {
        this.newsList = newsList;
    }

    private List<Source> nameList;
    private List<Contact> newsList;

    public void setMergedObjectsList(List<MergedObject> mergedObjectsList) {
        this.mergedObjectsList = mergedObjectsList;
    }

}
