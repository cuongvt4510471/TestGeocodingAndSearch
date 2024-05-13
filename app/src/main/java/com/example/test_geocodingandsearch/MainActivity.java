package com.example.test_geocodingandsearch;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.test_geocodingandsearch.Adapter.AddressAdapter;
import com.example.test_geocodingandsearch.Model.Data;
import com.example.test_geocodingandsearch.Model.Item;
import com.example.test_geocodingandsearch.Utils.SuggestService;
import com.example.test_geocodingandsearch.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SuggestAPI";
    public ActivityMainBinding binding;
    private AddressAdapter adapter;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callAPI(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callAPI(newText);
                return true;
            }
        });
    }

    public void callAPI(String query) {
        SuggestService.suggestService.getList(query, getString(R.string.at), getString(R.string.api_key)).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    items = new ArrayList<>();
                    items = response.body().getItems();
                    adapter = new AddressAdapter(MainActivity.this, items);
                    binding.recyclerView.setAdapter(adapter);
                    adapter.setQuery(query);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable throwable) {
                Log.d(TAG, "onFailure: " + throwable.getMessage());
            }
        });
    }
}