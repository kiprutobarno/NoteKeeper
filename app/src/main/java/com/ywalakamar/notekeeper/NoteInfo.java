package com.ywalakamar.notekeeper;

public final class NoteInfo {
    private CourseInfo course;
    private String title;
    private String text;

    public NoteInfo(CourseInfo course, String title, String text) {
        NoteInfo.this.course = course;
        this.title = title;
        this.text = text;
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

}