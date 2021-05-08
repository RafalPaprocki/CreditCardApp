package com.example.bamprojekt;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bamprojekt.dao.CreditCardDao;
import com.example.bamprojekt.dao.UserDao;
import com.example.bamprojekt.models.CreditCard;
import com.example.bamprojekt.models.User;

@Database(entities = {User.class, CreditCard.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String dbName = "CreditCardApp.db";
    private static AppDatabase appDatabase;

    public static synchronized AppDatabase getAppDatabase(Context context){
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return appDatabase;
    }

    public abstract UserDao userDao();
    public abstract CreditCardDao creditCardDao();
}
