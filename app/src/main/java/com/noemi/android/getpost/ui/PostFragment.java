package com.noemi.android.getpost.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.noemi.android.getpost.R;
import com.noemi.android.getpost.adapter.CircleAdapter;
import com.noemi.android.getpost.model.Item;
import com.noemi.android.getpost.network.ApiClient;
import com.noemi.android.getpost.network.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {

    public PostFragment() { }

    private ImageView post;
    private ImageView image;
    private TextView name;
    private static final String TAG = "PostFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);

        name = rootView.findViewById(R.id.item_name_post);
        image = rootView.findViewById(R.id.item_image_post);

        post = rootView.findViewById(R.id.image_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letsPost();
            }
        });


        return rootView;
    }

    public void letsPost(){

        Item newItem =  new Item(9, "John", "Doe",
                "https://vignette.wikia.nocookie.net/anonymous/images/e/ec/Official_Anonymous_Mask.PNG/revision/latest?cb=20150807065745");
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Item> itemCall = apiInterface.createPost(newItem);
        itemCall.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(),getString(R.string.toast), Toast.LENGTH_SHORT).show();
                    return;
                }

                Item item = response.body();
                String text = item.getFirstName() + " " + item.getLastName();
                name.setText(text);
                Picasso.get()
                        .load(item.getUrl())
                        .transform(new CircleAdapter())
                        .placeholder(R.drawable.anonymus)
                        .error(R.drawable.anonymus)
                        .into(image);

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.toast), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });



    }



}
