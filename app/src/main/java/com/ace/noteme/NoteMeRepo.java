package com.ace.noteme;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteMeRepo {

    private notesDao myNotesDao;
    private LiveData<List<notesEn>> myNotes;

    NoteMeRepo(Application application) {
        NoteMeDatabase db = NoteMeDatabase.getDatabase(application);
        myNotesDao = db.notesdao();
        myNotes = myNotesDao.getAllNotes();
    }

    LiveData<List<notesEn>> getAllNotes() {
        return myNotes;
    }

    void deleteNote(notesEn noteToBeDeleted) {
        NoteMeDatabase.databaseWriteExecutor.execute(() -> {
            myNotesDao.delete(noteToBeDeleted);
        });
    }

    void updateNote(notesEn noteToBeUpdated) {
        NoteMeDatabase.databaseWriteExecutor.execute(() -> {
            myNotesDao.update(noteToBeUpdated);
        });
    }
    void insert(notesEn noteToBeInserted) {
        NoteMeDatabase.databaseWriteExecutor.execute(() -> {
            myNotesDao.insert(noteToBeInserted);
        });
    }
}
