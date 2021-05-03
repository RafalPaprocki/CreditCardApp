package com.example.bamprojekt.viewAdapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.bamprojekt.autorization.LoginActivity;
import com.example.bamprojekt.creditCard.CreditCardActivity;
import com.example.bamprojekt.creditCard.CreditCardDetails;
import com.example.bamprojekt.creditCard.CreditCardEdit;
import com.example.bamprojekt.models.CreditCard;

import java.util.List;


// made base on source: https://www.javaer101.com/en/article/4124327.html
public class CreditCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private List<CreditCard> cardNames;
    private OnDelete onDelete;
    public CreditCardAdapter(Context context, List<CreditCard> cardNames)
    {
        this.context = context;
        this.cardNames = cardNames;
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

        CardItem.cardName.setText(cardNames.get(position).getName());
        CardItem.deleteButton.setText("Delete");
        CardItem.editButton.setText("Edit");
        CardItem.detailsButton.setText("Details");
        CardItem.deleteButton.setBackgroundColor(Color.parseColor("#ff0000"));
        CardItem.editButton.setBackgroundColor(Color.parseColor("#00ff00"));

        CardItem.deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int cardId = cardNames.get(position).getId();
                cardNames.remove(position);
                notifyItemRemoved(position);
                if (onDelete != null) {
                    onDelete.OnDeleteAction(cardId);
                }
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        CardItem.editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int cardId = cardNames.get(position).getId();
                Intent intent = new Intent(context, CreditCardEdit.class);
                intent.putExtra("cardId", cardId);
                context.startActivity(intent);

                Toast.makeText(context, "Details", Toast.LENGTH_SHORT).show();
            }
        });

        CardItem.detailsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int cardId = cardNames.get(position).getId();
                Intent intent = new Intent(context, CreditCardDetails.class);
                intent.putExtra("cardId", cardId);
                context.startActivity(intent);

                Toast.makeText(context, "Details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return cardNames.size();
    }

    public void setOnDelete(OnDelete onDelete) {
        this.onDelete = onDelete;
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

    public interface OnDelete {
        void OnDeleteAction(int cardId);
    }
}

