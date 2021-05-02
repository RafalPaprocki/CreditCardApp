package com.example.bamprojekt.creditCard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bamprojekt.R;
import com.example.bamprojekt.viewAdapters.CreditCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreditCardList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_list);

        // create list:
        List<String> titles = new ArrayList<>();
        titles.add("first item");
        titles.add("second item");
        titles.add("third item");
        titles.add("third item");
        titles.add("third item");
        titles.add("third item");
        titles.add("third item");
        titles.add("first item");
        titles.add("second item");
        titles.add("third item");
        titles.add("third item");
        titles.add("third item");
        titles.add("third item");
        titles.add("third item");
        titles.add("first item");
        titles.add("second item");
        titles.add("third item");
        titles.add("third item");
        titles.add("third item");
        titles.add("third item");
        titles.add("third item");

        // define the adapter:
        CreditCardAdapter adapter = new CreditCardAdapter(this, titles);

        // set the RecyclerView:
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}