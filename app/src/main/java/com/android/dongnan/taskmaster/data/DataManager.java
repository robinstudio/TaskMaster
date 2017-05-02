package com.android.dongnan.taskmaster.data;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by dream on 2017/5/1.
 */

public class DataManager {

    public static DataManager sInstance = null;

    public DataManager getInstance() {
        synchronized (sInstance) {
            if (sInstance == null) {
                sInstance = new DataManager();
            }
        }

        return sInstance;
    }

    private DataManager() {
        // Do Nothing.
    }

    public static void insertStory(DataConstants.StoryData data) {
        TaskDatabase database = new TaskDatabase();
        database.getWritableDatabase().insert(DataConstants.StoryTable.NAME,
                DataConstants.StoryTable.TITLE, DataConstants.getValuesFromStory(data));
        database.close();
    }

    public static void updateStory(DataConstants.StoryData data) {
        TaskDatabase database = new TaskDatabase();
        database.getWritableDatabase().update(DataConstants.StoryTable.NAME,
                DataConstants.getValuesFromStory(data),
                DataConstants.StoryTable.ID + "=?",
                new String[] {String.valueOf(data.id)});
        database.close();
    }

    public static void deleteStory(DataConstants.StoryData data) {
        TaskDatabase database = new TaskDatabase();

        database.getWritableDatabase().delete(DataConstants.StoryTable.NAME, DataConstants.StoryTable.ID + "=?",
                new String[]{String.valueOf(data.id)});

        database.close();
    }

    public static ArrayList<DataConstants.StoryData> queryStory(String selection, String[] selectionArgs) {
        TaskDatabase database = new TaskDatabase();

        Cursor cursor = database.getReadableDatabase().query(DataConstants.StoryTable.NAME,
                null, selection, selectionArgs, null, null, DataConstants.StoryTable.ID + " ASC");


        ArrayList<DataConstants.StoryData> datas = DataConstants.getDataFromCursor(cursor);

        database.close();

        return datas;
    }
}
