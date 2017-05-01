package com.android.dongnan.taskmaster.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dream on 2017/5/1.
 */

public class TaskDatabase extends SQLiteOpenHelper {

    public TaskDatabase() {
        super(CommonHolder.getApplicationContext(), DataConstants.DB_NAME, null, DataConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataConstants.StoryTable.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
