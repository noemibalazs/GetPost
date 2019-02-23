package com.noemi.android.getpost.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.noemi.android.getpost.R;
import com.noemi.android.getpost.adapter.ItemAdapter;
import com.noemi.android.getpost.model.Item;
import com.noemi.android.getpost.model.ItemList;
import com.noemi.android.getpost.network.ApiClient;
import com.noemi.android.getpost.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private static final String TAG = "GetFragment";

    public GetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_get, container, false);

        recyclerView = rootView.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ItemList> itemListCall = apiInterface.getListItems();
        itemListCall.enqueue(new Callback<ItemList>() {
            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                }

                if (response.body() != null) {
                    List<Item> items = response.body().getListItem();
                    adapter = new ItemAdapter(getContext(), items);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.toast), Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }

}
