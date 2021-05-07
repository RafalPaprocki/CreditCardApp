package com.example.bamprojekt.contentProviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.bamprojekt.AppDatabase;
import com.example.bamprojekt.dao.CreditCardDao;

public class CreditCardProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.provider.bamprojekt";
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    private AppDatabase db;
    private CreditCardDao creditCardDao;
    private Cursor c = null;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(PROVIDER_NAME, "cards", uriCode);

        uriMatcher.addURI(PROVIDER_NAME, "cards/*", uriCode);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        db = AppDatabase.getAppDatabase(context);
        creditCardDao = db.creditCardDao();

        if (db != null) {
            return true;
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        Thread thread = new Thread(() -> {
            try {
                c = creditCardDao.getCursorAll();
                c.setNotificationUri(getContext().getContentResolver(), uri);
            }catch(Exception e){
                Log.d("Exception", e.getMessage());
            }
        });

        thread.start();

        try {
            thread.join();
        }catch(Exception e){
        }

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
