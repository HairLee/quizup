package com.elcom.com.quizupapp.db;

import android.content.Context;

import com.elcom.com.quizupapp.db.model.Country;
import com.elcom.com.quizupapp.db.model.GamePause;
import com.elcom.com.quizupapp.db.model.Invention;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Hailpt on 4/13/2018.
 */
public class MangerDB {

    private static MangerDB instance;

    public static MangerDB getInstance() {
        if (instance == null) {
            instance = new MangerDB();
            return instance;
        }
        return instance;
    }


    public void insertCoutry(Context context, final ArrayList<Country> listMember) {
        Realm realm = RealmDbHelper.getInstance().getRealm(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Country model : listMember) {
                    realm.copyToRealmOrUpdate(model);
                }
            }
        });
    }

    public Country getCountryById(Context context, int channelId) {
        Realm realm = RealmDbHelper.getInstance().getRealm(context);
        Country channel = realm.where(Country.class).equalTo("id", channelId).findFirst();
        if (channel == null) {
            return null;
        }
        return channel;
    }

    public List<Country> getCountryList(Context context) {
        Realm realm = RealmDbHelper.getInstance().getRealm(context);
        List<Country> mList = realm.where(Country.class).findAllAsync();
        return mList;
    }

    public void deleteCounttry(Context context, final int channelId) {
        Realm realm = RealmDbHelper.getInstance().getRealm(context);
        final Country channel = realm.where(Country.class).equalTo("id", channelId).findFirst();
        if (channel == null) return;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                channel.removeFromRealm();
            }
        });
    }


    public void insertInvention(Context context, final Invention pInvention) {
        Realm realm = RealmDbHelper.getInstance().getRealm(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(pInvention);
            }
        });
    }

    public Invention getInvention(Context context) {
        Realm realm = RealmDbHelper.getInstance().getRealm(context);
        Invention invention = realm.where(Invention.class).findFirst();

        return invention;
    }

    public void insertGamePause(Context context, final GamePause gamePause) {
        Realm realm = RealmDbHelper.getInstance().getRealm(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(gamePause);
            }
        });
    }

    public GamePause getGamePause(Context context) {
        Realm realm = RealmDbHelper.getInstance().getRealm(context);
        GamePause gamePause = realm.where(GamePause.class).findFirst();

        return gamePause;
    }

}
