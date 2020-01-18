package com.example.note;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    public String title;
    public String description;
    public String picPath;

    Task(){

    }

    Task(String t, String d){
        title = t;
        description = d;
        picPath = null;
    }

    Task(String t, String d,String pic){
        title = t;
        description = d;
        picPath = pic;
    }

    protected Task(Parcel in) {
        title = in.readString();
        description = in.readString();
        picPath = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public String toString(){
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(picPath);
    }
}
