package com.mail.dinesh.realmtest.dbTranscations;

import android.util.Log;

import com.mail.dinesh.realmtest.bo.LocalMail;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by dinesh on 23.10.16.
 */

public class DbCommit {

    protected void saveData(Realm realm, String threadId) {

        realm.beginTransaction();

        // Create an object
        LocalMail mailObj = realm.createObject(LocalMail.class);
        int id = 0;
        id = getNextKey(realm);
        mailObj.setId(id);
        mailObj.setThreadId(threadId);

        realm.commitTransaction();
    }

    protected static LocalMail pullData(Realm realm) {
        RealmResults<LocalMail> results = realm.where(LocalMail.class).findAll();
        /*for(LocalMail c:results) {
            Log.d("Data from realm", c.getThreadId());
        }*/
        LocalMail latestResult = results.last();
        return latestResult;
    }

    protected static void deleteData(Realm realm, String threadId) {
        RealmResults<LocalMail> results = realm.where(LocalMail.class).findAll();
        LocalMail mail = results .where().equalTo("threadId",threadId).findFirst();
        if(mail!=null) {
            if (!realm.isInTransaction())
            {
                realm.beginTransaction();
            }
            mail.removeFromRealm();
            realm.commitTransaction();
        }
    }

    protected static List<String> pullAllData(Realm realm) {
        List<String> dbSpit = new ArrayList<>();
        RealmResults<LocalMail> results = realm.where(LocalMail.class).findAll();
        for(LocalMail c:results) {
            Log.d("Data from realm", c.getThreadId());
            dbSpit.add(c.getThreadId());
        }
        return dbSpit;
    }

    //Primary key implementation
    private int getNextKey(Realm realm)
    {
        return realm.where(LocalMail.class).max("id").intValue() + 1;
    }

}
