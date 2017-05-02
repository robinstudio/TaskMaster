/**
 * Created by dream on 2017/5/1.
 */

package com.android.dongnan.taskmaster.data;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;


public class DataConstants {

    public static final String DB_NAME = "task.db";
    public static final int DB_VERSION = 1;

    public static class StoryTable {
        public static final String NAME = "story";

        public static final String ID = "id";
        public static final String TIME = "time";
        public static final String TITLE = "title";
        public static final String ADDRESS = "address";
        public static final String PEOPLE = "people";
        public static final String CONTENT = "content";
        public static final String COMMENT = "comment";
        public static final String RESULT = "result";

        public static final String CREATE_SQL = "create table "
                + NAME + "("
                + ID + " integer primary key autoincrement,"
                + TIME + " long,"
                + TITLE + " title,"
                + ADDRESS + " text,"
                + PEOPLE + " text,"
                + CONTENT + " text,"
                + COMMENT + " text,"
                + RESULT + " result"
                + ")";
    }

    public static ContentValues getValuesFromStory(StoryData data) {
        ContentValues values = new ContentValues();
        if (data.id != -1) {
            values.put(StoryTable.ID, data.id);
        }
        values.put(StoryTable.TIME, data.time);
        values.put(StoryTable.TITLE, data.title);
        values.put(StoryTable.ADDRESS, data.address);
        values.put(StoryTable.PEOPLE, data.people);
        values.put(StoryTable.CONTENT, data.content);
        values.put(StoryTable.COMMENT, data.comment);
        values.put(StoryTable.RESULT, data.result);
        return values;
    }

    public static ArrayList<StoryData> getDataFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        ArrayList<StoryData> dataList = new ArrayList<>();
        cursor.moveToFirst();
        do {
            StoryData data = StoryData.builder()
                    .setId(cursor.getInt(cursor.getColumnIndex(StoryTable.ID)))
                    .setTime(cursor.getLong(cursor.getColumnIndex(StoryTable.TIME)))
                    .setTitle(cursor.getString(cursor.getColumnIndex(StoryTable.TITLE)))
                    .setAddress(cursor.getString(cursor.getColumnIndex(StoryTable.ADDRESS)))
                    .setPeople(cursor.getString(cursor.getColumnIndex(StoryTable.PEOPLE)))
                    .setContent(cursor.getString(cursor.getColumnIndex(StoryTable.CONTENT)))
                    .setComment(cursor.getString(cursor.getColumnIndex(StoryTable.COMMENT)))
                    .setResult(cursor.getString(cursor.getColumnIndex(StoryTable.RESULT)));
            dataList.add(data);
        } while (cursor.moveToNext());
        cursor.close();

        return dataList;
    }

    public static StoryData createTestStory(int index) {
        StoryData data = StoryData.builder()
                .setTime(new Date().getTime())
                .setTitle("title-" + index)
                .setAddress("address-" + index)
                .setPeople("people-" + index)
                .setContent("content-" + index)
                .setComment("comment-" + index)
                .setResult("result-" + index);
        return data;
    }

    public static class StoryData {
        public int id = -1;
        public long time = -1;
        public String title = "";
        public String address = "";
        public String people = "";
        public String content = "";
        public String comment = "";
        public String result = "";

        public static StoryData builder() {
            return new StoryData();
        }

        public StoryData setId(int id) {
            this.id = id;
            return this;
        }

        public StoryData setTime(long time) {
            this.time = time;
            return this;
        }

        public StoryData setTitle(String title) {
            this.title = title;
            return this;
        }

        public StoryData setAddress(String address) {
            this.address = address;
            return this;
        }

        public StoryData setPeople(String people) {
            this.people = people;
            return this;
        }

        public StoryData setContent(String content) {
            this.content = content;
            return this;
        }

        public StoryData setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public StoryData setResult(String result) {
            this.result = result;
            return this;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            StoryData data = (StoryData) obj;
            return (this.id != -1) && (this.id == data.id);
        }
    }

    public interface ActivityCallback {
        void onItemClicked(DataConstants.StoryData data);
        void onDataChanged();
    }
}
