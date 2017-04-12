package com.android.dongnan.taskmaster.data;

import android.text.TextUtils;

/**
 * Created by dream on 2017/4/12.
 */

public class DataItem {

    private String title = "";
    private String content = "";

    public static DataItem getInstance() {
        return new DataItem();
    }

    private DataItem() {
    }

    public String getTitle() {
        return title;
    }

    public DataItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public DataItem setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "data{"
                + "title:" + title
                + "content:" + content
                + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || TextUtils.isEmpty(this.title)
                || TextUtils.isEmpty(this.content)) {
            return false;
        }

        DataItem previous = (DataItem)obj;
        if (this.title.equals(previous.getTitle())
                && this.content.equals(previous.getContent())) {
            return true;
        }

        return false;
    }
}
