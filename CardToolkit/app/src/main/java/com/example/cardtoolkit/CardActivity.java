package com.example.cardtoolkit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card_recycler);

        ArrayList<CardItem> cardList = new ArrayList<>();
        cardList.add(new CardItem(R.drawable.ic_launcher_background,"random card"));
        cardList.add(new CardItem(R.drawable.ic_launcher_background,"random card2"));
        cardList.add(new CardItem(R.drawable.ic_launcher_background,"random card3"));
        cardList.add(new CardItem(R.drawable.ic_launcher_background,"random card4"));
        cardList.add(new CardItem(R.drawable.ic_launcher_background,"random card5"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardAdapter(cardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
