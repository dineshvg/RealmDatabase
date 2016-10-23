package com.mail.dinesh.realmtest.dbTranscations;

import com.mail.dinesh.realmtest.bo.LocalMail;
import com.mail.dinesh.realmtest.dbTranscations.DbCommit;

import io.realm.Realm;

/**
 * Created by dinesh on 23.10.16.
 */

public class Manager {

    private  static DbCommit dbCommit = new DbCommit();

    public static void commitIntoRealm(Realm realm, String threadId) {
        dbCommit.saveData(realm,threadId);
    }

    public static LocalMail obtainFromRealm(Realm realm) {
        LocalMail mailObj = new LocalMail();
        mailObj = dbCommit.pullData(realm);
        return mailObj;
    }
}
