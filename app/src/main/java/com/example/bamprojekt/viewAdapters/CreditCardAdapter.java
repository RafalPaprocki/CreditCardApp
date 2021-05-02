package com.example.bamprojekt.viewAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bamprojekt.R;

import java.util.List;


// made base on source: https://www.javaer101.com/en/article/4124327.html
public class CreditCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private List<String> titles;

    public CreditCardAdapter(Context context, List<String> titles)
    {
        this.context = context;
        this.titles = titles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        viewHolder = getViewHolder(parent, inflater);

        return viewHolder;
    }

    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater)
    {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.card_item, parent, false);
        viewHolder = new CardItem(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        CardItem CardItem = (CardItem) holder;

        // set title for each item:
        CardItem.cardName.setText(titles.get(position));
        CardItem.deleteButton.setText("Delete");
        CardItem.editButton.setText("Edit");
        CardItem.detailsButton.setText("Details");
        CardItem.deleteButton.setBackgroundColor(Color.parseColor("#ff0000"));
        CardItem.editButton.setBackgroundColor(Color.parseColor("#00ff00"));

        // click listener for first button:
        CardItem.deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
            }
        });

        // click listener for second button:
        CardItem.editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
            }
        });

        CardItem.detailsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, "Details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return titles.size();
    }

    // add item to list:
    public void addItem(String title)
    {
        titles.add(title);
        notifyItemInserted(titles.size() - 1);
    }

    // remove item from list:
    public void remove(String title)
    {
        int position = titles.indexOf(title);
        if (position > -1)
        {
            titles.remove(position);
            notifyItemRemoved(position);
        }
    }

    protected class CardItem extends RecyclerView.ViewHolder
    {
        private TextView cardName;
        private Button deleteButton;
        private Button editButton;
        private Button detailsButton;

        public CardItem(View itemView)
        {
            super(itemView);

            cardName = itemView.findViewById(R.id.cardItem_name);
            deleteButton = itemView.findViewById(R.id.delete_button);
            editButton = itemView.findViewById(R.id.edit_button);
            detailsButton = itemView.findViewById(R.id.detail_button);
        }
    }
}