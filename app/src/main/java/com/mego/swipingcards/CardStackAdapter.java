package com.mego.swipingcards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<ItemModel> items;
    private Context context;

    public CardStackAdapter(List<ItemModel> items,Context context) {
        this.items = items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView topspeed, mark, model;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            topspeed = itemView.findViewById(R.id.item_topSpeed);
            mark = itemView.findViewById(R.id.item_mark);
            model = itemView.findViewById(R.id.item_model);
        }

        void setData(ItemModel data) {
            Glide
                    .with(context)
                    .load(data.getImage())
                    .centerCrop()
                    .into(image);
        //    topspeed.setText(data.getTopSpeed());
            mark.setText(data.getMark());
            model.setText(data.getModel());
        }
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }
}
