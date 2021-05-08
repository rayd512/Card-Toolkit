package com.example.cardtoolkit.CardDisplay.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardtoolkit.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{
    private ArrayList<CardItem> mCardList;
    public static class CardViewHolder extends RecyclerView.ViewHolder{
        public ImageView mCardImage;
        public TextView mCardName;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardImage = itemView.findViewById(R.id.card_image_id);
            mCardName = itemView.findViewById(R.id.card_name_id);
        }
    }

    public CardAdapter(ArrayList<CardItem> cardList) {
        mCardList = cardList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_card_card_view, parent, false);
        CardViewHolder cvh = new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem currentItem = mCardList.get(position);
        holder.mCardImage.setImageResource(currentItem.getCardImage());
        holder.mCardName.setText(currentItem.getCardName());
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }
}
