package com.example.wia2007_zerohunger.Part5.FinancialDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    public void insert(Note note);

    @Update
    public void update(Note note);

    @Delete
    public void delete(Note note);

    @Query("SELECT * FROM note_table ORDER BY aidId ASC")
    LiveData<List<Note>> getAllNotes();
}

