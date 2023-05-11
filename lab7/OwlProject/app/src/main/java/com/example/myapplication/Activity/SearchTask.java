package com.example.myapplication.Activity;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

class SearchTask extends AsyncTask<String, Void, List<String>> {
    @Override
    protected List<String> doInBackground(String... strings) {
        String query = strings[0];
        List<String> results = new ArrayList<>();
        // выполнение поискового запроса
        return results;
    }

  
}