package com.noemi.android.getpost.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.noemi.android.getpost.R;
import com.noemi.android.getpost.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> items;
    private Context context;

    public ItemAdapter(Context cxt, List<Item> itemList){
        this.context = cxt;
        this.items = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        String firstName = item.getFirstName();
        String lastName = item.getLastName();
        String fullName = firstName + " " + lastName;
        holder.name.setText(fullName);
        Picasso.get()
                .load(item.getUrl())
                .transform(new CircleAdapter())
                .error(R.drawable.anonymus)
                .placeholder(R.drawable.anonymus)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_name);
        }
    }
}
