package com.ywalakamar.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

public final class NoteInfo implements Parcelable {
    private CourseInfo course;
    private String title;
    private String text;

    public NoteInfo(CourseInfo course, String title, String text) {
        NoteInfo.this.course = course;
        this.title = title;
        this.text = text;
    }

    private NoteInfo(Parcel source) {
        course=source.readParcelable(CourseInfo.class.getClassLoader());
        title=source.readString();
        text=source.readString();
    }

    public CourseInfo getCourse() {
        return course;
    }

    public void setCourse(CourseInfo course) {
        NoteInfo.this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String getCompareKey() {
        return course.getCourseId() + "|" + title + "|" + text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(course, 0);
        dest.writeString(title);
        dest.writeString(text);

    }

    public static final Parcelable.Creator<NoteInfo> CREATOR=new Parcelable.Creator<NoteInfo>(){
        @Override
        public NoteInfo createFromParcel(Parcel source) {
            return new NoteInfo(source);
        }

        @Override
        public NoteInfo[] newArray(int size) {
            return new NoteInfo[size];
        }
    };
}