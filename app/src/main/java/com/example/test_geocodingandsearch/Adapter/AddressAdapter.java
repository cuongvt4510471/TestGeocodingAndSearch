package com.example.test_geocodingandsearch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_geocodingandsearch.Model.Item;
import com.example.test_geocodingandsearch.databinding.ItemSuggestBinding;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {
    private final Context context;
    private final List<Item> list;
    private String query;

    public AddressAdapter(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSuggestBinding binding = ItemSuggestBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AddressViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Item item = list.get(position);
        if (item == null) {
            return;
        }
        if (query != null && !query.isEmpty()) {
            int startPos = item.getTitle().toLowerCase().indexOf(query.toLowerCase());
            int endPos = startPos + query.length();
            if (startPos != -1) {
                Spannable spannable = new SpannableString(item.getTitle());
                ForegroundColorSpan highlightSpan = new ForegroundColorSpan(Color.BLACK);
                spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.binding.tvAddress.setText(spannable);
            } else {
                holder.binding.tvAddress.setText(item.getTitle());
            }
        } else {
            holder.binding.tvAddress.setText(item.getTitle());
        }
        holder.binding.ivDirection.setOnClickListener(v -> {
            String uri = "https://www.google.com/maps/dir/?api=1&destination=" + item.getAddress().getLabel();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;
    }

    public static class AddressViewHolder extends RecyclerView.ViewHolder {
        private final ItemSuggestBinding binding;

        public AddressViewHolder(@NonNull ItemSuggestBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
