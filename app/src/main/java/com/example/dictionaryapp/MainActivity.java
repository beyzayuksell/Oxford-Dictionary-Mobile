package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionaryapp.Adapters.MeaningAdapter;
import com.example.dictionaryapp.Adapters.PhoneticsAdapter;
import com.example.dictionaryapp.Models.APIResponse;

public class MainActivity extends AppCompatActivity {

    public SearchView search_view;
    public TextView textView_word;
    public RecyclerView recycle_phonetics, recycle_meanings;
    public ProgressDialog progressDialog;
    public PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;
    // Start Data UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recycle_phonetics = findViewById(R.id.recycler_phonetics);
        recycle_meanings = findViewById(R.id.recycler_meanings);
        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Loading...");
        progressDialog.show();
        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getWordMeaning(listener, "hello");

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse == null) {
                Toast.makeText(MainActivity.this, "No data found!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        textView_word.setText("Word: " + apiResponse.getWord());
        recycle_phonetics.setHasFixedSize(true);
        recycle_phonetics.setLayoutManager(new GridLayoutManager(this,1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());
        recycle_phonetics.setAdapter(phoneticsAdapter);

        recycle_meanings.setHasFixedSize(true);
        recycle_meanings.setLayoutManager(new GridLayoutManager(this,1));
        meaningAdapter = new MeaningAdapter(this, apiResponse.getMeanings());
        recycle_meanings.setAdapter(meaningAdapter);
    }

}