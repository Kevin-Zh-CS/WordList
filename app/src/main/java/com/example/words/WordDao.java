package com.example.words;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao //Database access object
public interface WordDao {
    @Insert
    void InsertWords(Word... words);

    @Update
    void UpdateWords(Word... words);

    @Delete
    void DeleteWords(Word... words);

    @Query("DELETE FROM WORD")
    void DeleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    LiveData<List<Word>> GetAllWordsLive();

    @Query("SELECT * FROM WORD WHERE EnglishWord LIKE :pattern ORDER BY ID DESC")
    LiveData<List<Word>> findWordsWithPattern(String pattern);
}
