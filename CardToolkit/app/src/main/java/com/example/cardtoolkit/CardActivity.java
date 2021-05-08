package com.example.cardtoolkit;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mAddCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card_recycler);
        mAddCardButton = findViewById(R.id.add_card_button);

        ArrayList<CardItem> cardList = new ArrayList<>();
        ArrayList<String> test = new ArrayList<>();
        test.add("Card1");
        test.add("Card2");
        test.add("Card3");
        test.add("Card4");
        test.add("Card5");
        test.add("Card6");
        test.add("Card7");
        test.add("Card8");
        test.add("Card9");
        test.add("Card10");
        for (int i = 0; i < test.size(); i++) {
            cardList.add(new CardItem(R.drawable.ic_launcher_background, test.get(i)));
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardAdapter(cardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAddCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment myDialogFragment=new MyDialogFragment();
                myDialogFragment.show(getSupportFragmentManager(),"AddModelFragment");
            }
        });
    }
}
