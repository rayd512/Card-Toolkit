package com.example.cardtoolkit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button mAddCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card_recycler);
        mAddCardButton = findViewById(R.id.addCardButton);

        ArrayList<CardItem> cardList = new ArrayList<>();
        ArrayList<String> test = new ArrayList<>();
        test.add("Card1");
        test.add("Card2");
        for (int i = 0; i < test.size(); i++) {
            cardList.add(new CardItem(R.drawable.ic_launcher_background, test.get(i)));
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardAdapter(cardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Open Fragment for adding card when add button is clicked
//        mAddCardButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
