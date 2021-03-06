package com.example.words;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>> allWordLive;

    private WordDao wordDao;
    public WordRepository(Context context){
        WordDatabase wordDatabase = WordDatabase.getDatabase(context.getApplicationContext());
        wordDao = wordDatabase.GetWordDao();
        allWordLive = wordDao.GetAllWordsLive();
    }

    public LiveData<List<Word>> getAllWordLive() {
        return allWordLive;
    }

    public LiveData<List<Word>> findWordsWithPattern(String pattern){
        return wordDao.findWordsWithPattern("%" + pattern + "%");
    }

    void insertWords(Word...words){
        new InsertAsyncTask(wordDao).execute(words);
    }
    void updateWords(Word...words){
        new UpdateAsyncTask(wordDao).execute(words);
    }
    void deleteAllWords(){
        new DeleteAllAsyncTask(wordDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;
        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.InsertWords(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordDao wordDao;
        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.UpdateWords(words);
            return null;
        }
    }


    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private WordDao wordDao;
        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.DeleteAllWords();
            return null;
        }
    }
}
