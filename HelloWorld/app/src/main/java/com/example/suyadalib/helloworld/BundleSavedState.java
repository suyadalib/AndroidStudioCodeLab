package com.example.suyadalib.helloworld;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class BundleSavedState extends View.BaseSavedState {

    private Bundle bundle;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public BundleSavedState(Parcel source) {
        super(source);
        bundle = source.readBundle();
    }

    @TargetApi(24)
    public BundleSavedState(Parcel source, ClassLoader loader) {
        super(source, loader);
    }

    public BundleSavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeBundle(bundle);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new BundleSavedState(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new BundleSavedState[i];
        }
    };
}
