package com.elcom.com.quizupapp.db;

import android.content.Context;
import android.util.Base64;
import com.elcom.com.quizupapp.utils.ConstantsApp;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmDbHelper {

    private static final String TAG = RealmDbHelper.class.getName();
    private static RealmDbHelper instance;
    private Realm realm;
    private RealmConfiguration realmConfiguration;

    public static RealmDbHelper getInstance() {

        if (instance == null) {
            instance = new RealmDbHelper();
        }

        return instance;
    }

    private static String key = null;

    public Realm getRealm(Context context) {

        if (realmConfiguration == null)
            realmConfiguration = new RealmConfiguration.Builder(context)
                    .name(ConstantsApp.DB_NAME)
                    .deleteRealmIfMigrationNeeded()
//                    .encryptionKey(Base64.decode(key, Base64.DEFAULT))
                    .build();

        if (realm == null)
            realm = Realm.getInstance(realmConfiguration);

        return realm;
    }

    public void beginTransaction() {
        realm.beginTransaction();
    }

    public void commitTransaction() {
        realm.commitTransaction();
    }

    public void close() {
        realm.close();
    }


}
