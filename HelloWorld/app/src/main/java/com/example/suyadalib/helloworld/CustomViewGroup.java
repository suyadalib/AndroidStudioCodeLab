package com.example.suyadalib.helloworld;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;

public class CustomViewGroup extends FrameLayout {

    private Button btnHello;

    public CustomViewGroup(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public CustomViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public CustomViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @RequiresApi(21)
    public CustomViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    private void initInflate() {
        //inflate Layout here
        inflate(getContext(), R.layout.sample_layout, this);
    }

    private void initInstance() {
        btnHello = (Button) findViewById(R.id.btnCustomViewGroupHello);
    }

    public void setButtonText(String text) {
        btnHello.setText(text);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        // Save children's state as bundle
        Bundle childrenStates = new Bundle();
        for (int i = 0; i < getChildCount(); i++) {
            int id = getChildAt(i).getId();
            if (id != 0) {
                SparseArray childrenState = new SparseArray();
                getChildAt(i).saveHierarchyState(childrenState);
                childrenStates.putSparseParcelableArray(String.valueOf(id), childrenState);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("childrenStates", childrenStates);

        // Save it to Parcelable
        BundleSavedState savedState = new BundleSavedState(superState);
        savedState.setBundle(bundle);
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState savedState = (BundleSavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());

        // Restore SparseArray
        Bundle childrenStates = savedState.getBundle().getBundle("childrenStates");
        // Restore children's state
        for (int i = 0; i < getChildCount(); i++) {
            int id = getChildAt(i).getId();
            if (id != 0) {
                if (childrenStates.containsKey(String.valueOf(id))) {
                    SparseArray childrenState = childrenStates.getSparseParcelableArray(String.valueOf(id));
                    getChildAt(i).restoreHierarchyState(childrenState);
                }
            }
        }

    }
}
