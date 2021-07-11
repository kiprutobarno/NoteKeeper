package com.ywalakamar.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

public final class ModuleInfo implements Parcelable {
    private final String moduleId;
    private final String title;
    private boolean isComplete;

    public ModuleInfo(String moduleId, String title) {
        this(moduleId, title, false);
    }

    public ModuleInfo(String moduleId, String title, boolean isComplete) {
        this.moduleId = moduleId;
        this.title = title;
        this.isComplete = isComplete;
    }

    private ModuleInfo(Parcel source) {
        moduleId = source.readString();
        title = source.readString();
        isComplete = source.readByte() == 1;
    }

    public String getModuleId() {
        return moduleId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleInfo that = (ModuleInfo) o;

        return moduleId.equals(that.moduleId);
    }

    @Override
    public int hashCode() {
        return moduleId.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(moduleId);
        dest.writeString(title);
        dest.writeByte((byte)(isComplete ? 1 : 0));
    }

    public static final Creator<ModuleInfo> CREATOR =
            new Creator<ModuleInfo>() {

                @Override
                public ModuleInfo createFromParcel(Parcel source) {
                    return new ModuleInfo(source);
                }

                @Override
                public ModuleInfo[] newArray(int size) {
                    return new ModuleInfo[size];
                }
            };
}