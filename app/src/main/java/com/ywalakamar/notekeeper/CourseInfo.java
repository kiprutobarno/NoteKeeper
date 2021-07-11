package com.ywalakamar.notekeeper;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public final class CourseInfo implements Parcelable {
    private final String courseId;
    private final String courseTitle;
    private final List<ModuleInfo> modules;

    public CourseInfo(String courseId, String title, List<ModuleInfo> modules) {
        this.courseId = courseId;
        this.courseTitle = title;
        this.modules = modules;
    }

    private CourseInfo(Parcel source) {
        courseId = source.readString();
        courseTitle = source.readString();
        modules = new ArrayList<>();
        source.readTypedList(modules, ModuleInfo.CREATOR);
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return courseTitle;
    }

    public List<ModuleInfo> getModules() {
        return modules;
    }

    public boolean[] getModulesCompletionStatus() {
        boolean[] status = new boolean[modules.size()];

        for(int i=0; i < modules.size(); i++)
            status[i] = modules.get(i).isComplete();

        return status;
    }

    public void setModulesCompletionStatus(boolean[] status) {
        for(int i=0; i < modules.size(); i++)
            modules.get(i).setComplete(status[i]);
    }

    public ModuleInfo getModule(String moduleId) {
        for(ModuleInfo moduleInfo: modules) {
            if(moduleId.equals(moduleInfo.getModuleId()))
                return moduleInfo;
        }
        return null;
    }

    @Override
    public String toString() {
        return courseTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseInfo that = (CourseInfo) o;

        return courseId.equals(that.courseId);

    }

    @Override
    public int hashCode() {
        return courseId.hashCode();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseId);
        dest.writeString(courseTitle);
        dest.writeTypedList(modules);
    }

    public static final Creator<CourseInfo> CREATOR =
            new Creator<CourseInfo>() {

                @Override
                public CourseInfo createFromParcel(Parcel source) {
                    return new CourseInfo(source);
                }

                @Override
                public CourseInfo[] newArray(int size) {
                    return new CourseInfo[size];
                }
            };

}