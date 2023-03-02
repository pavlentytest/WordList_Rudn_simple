package com.example.myapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }

    public void insert(Word word) {
        mWordDao.insert(word); // <- Так нельзя!!!
        /* WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        }); */
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                mWordDao.insert(word);
            }
        }).start();*
         */
    }
}