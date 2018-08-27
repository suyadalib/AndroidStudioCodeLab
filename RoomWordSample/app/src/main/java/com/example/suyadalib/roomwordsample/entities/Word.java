package com.example.suyadalib.roomwordsample.entities;

import android.support.annotation.NonNull;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "word_table")
public class Word {
    public Word(@NonNull String word) {
        this.mWord = word;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public String getmWord() {
        return mWord;
    }

}
