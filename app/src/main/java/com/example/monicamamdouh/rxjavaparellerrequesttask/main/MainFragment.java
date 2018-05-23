package com.example.monicamamdouh.rxjavaparellerrequesttask.main;


import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monicamamdouh.rxjavaparellerrequesttask.R;
import com.example.monicamamdouh.rxjavaparellerrequesttask.base.BaseFragment;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.MergedObjectList;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.contacts.Contact;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.news.Source;
import com.example.monicamamdouh.rxjavaparellerrequesttask.room.AppDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements MainView {

    private MainPresenter mainPresenter;
    private Context context;
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private MergedObjectList mergedObjectList;
    private AppDatabase appDatabase;
    private List<Contact>contactList;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        appDatabase = AppDatabase.getInstance(context);
        mainPresenter = new MainPresenter(context, this);
        mainPresenter.getContacts();
        getDataFromDB();

    }


    private void getDataFromDB()
    {
        appDatabase.contactDao().getAllContact().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                contactList=contacts;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        initializeViews(v);
        setListeners();
        initializeAdapter();
        return v;
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected void initializeViews(View v) {

        recyclerView = v.findViewById(R.id.recyclerView);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void onRetrieveContactsSuccess(MergedObjectList mergedObjectList) {

        mainRecyclerViewAdapter.setMergedObjectList(mergedObjectList);
        mainRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRetrieveSaveSuccess(List<Contact> contactsList) {
        appDatabase.contactDao().insert( contactsList);
    }

    @Override
    public void onRetrieveContactsError(String error) {

    }

    @Override
    public void initializeAdapter() {


        linearLayoutManager = new LinearLayoutManager(context);
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(mainRecyclerViewAdapter);
    }
}
