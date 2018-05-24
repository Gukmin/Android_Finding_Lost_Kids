package com.example.kyshi.finding_lost_kids_application;


/**
 * Created by android on 2018-05-12.
 */

public class ChildItem {
    String name;
    String tag;
    String time;
    int resId;

    public ChildItem(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public ChildItem(String name, String tag, String time, int resId) {
        this.name = name;
        this.tag = tag;
        this.time = time;
        this.resId = resId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag() {
        this.tag = tag;
    }
}
