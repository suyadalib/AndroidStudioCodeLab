package com.example.suyadalib.helloworld;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * created by Suyada
 */

public class CustomerViewSavedState extends View.BaseSavedState {

    private boolean blue;

    public boolean isBlue() {
        return blue;
    }

    public void setBlue(boolean blue) {
        this.blue = blue;
    }

    public CustomerViewSavedState(Parcel source) {
        super(source);
        blue = source.readInt() == 1 ? true : false;
    }

    @TargetApi(24)
    public CustomerViewSavedState(Parcel source, ClassLoader loader) {
        super(source, loader);
        blue = source.readInt() == 1 ? true : false;
    }

    public CustomerViewSavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(blue ? 1 : 0);

    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new CustomerViewSavedState(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new CustomerViewSavedState[size];
        }
    };
}
