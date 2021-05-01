package com.example.bamprojekt;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 3)
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
}
