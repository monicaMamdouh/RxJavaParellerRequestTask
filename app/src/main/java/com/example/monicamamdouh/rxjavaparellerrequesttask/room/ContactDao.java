package com.example.monicamamdouh.rxjavaparellerrequesttask.room;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.contacts.Contact;

import java.util.List;

@Dao
public abstract class ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(List<Contact> genreEntities);

    @Query("Select * from contact")
    public abstract LiveData<List<Contact>>  getAllContact();
}
