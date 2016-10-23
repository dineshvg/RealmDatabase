package com.mail.dinesh.realmtest.dbTranscations;

import android.util.Log;

import com.mail.dinesh.realmtest.bo.LocalMail;
import com.mail.dinesh.realmtest.dbTranscations.DbCommit;

import java.util.List;

import io.realm.Realm;

/**
 * Created by dinesh on 23.10.16.
 */

public class Manager {

    private final static String TAG = Manager.class.getSimpleName();
    private  static DbCommit dbCommit = new DbCommit();

    public static void commitIntoRealm(Realm realm, String threadId) {
        dbCommit.saveData(realm,threadId);
    }

    public static LocalMail obtainFromRealm(Realm realm) {
        LocalMail mailObj = new LocalMail();
        mailObj = dbCommit.pullData(realm);
        return mailObj;
    }

    public static String completeDBOut(Realm realm) {
        Log.d(TAG,"completeDBOut called");
        List<String> db =  dbCommit.pullAllData(realm);
        return localMail2String(db);
    }

    private static String localMail2String(List<String> db) {
        String dbSpit = "";
        for(String threadId : db) {
            dbSpit = dbSpit+threadId+"\n";
        }
        Log.d(TAG,dbSpit+"");
        return dbSpit;
    }

    public static void deleteRecord(Realm realm, String threadId) {
        dbCommit.deleteData(realm, threadId);
    }
}
