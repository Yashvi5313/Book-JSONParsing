package com.example.bookjsonparsing.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookjsonparsing.Model.AuthorItem;
import com.example.bookjsonparsing.MainActivity;
import com.example.bookjsonparsing.R;

import java.util.Collections;
import java.util.List;

public class AdapterAuthor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    MainActivity mainActivity;
    LayoutInflater inflater;
    List<AuthorItem> authorItems = Collections.emptyList();
    AuthorClickEvent authorClickEvent;
    AuthorItem current;
    int currentPos = 0;

    public AdapterAuthor(MainActivity mainActivity, List<AuthorItem> authorItems) {
        this.mainActivity = mainActivity;
        inflater = LayoutInflater.from(mainActivity);
        this.authorItems = authorItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.author_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyHolder myHolder = (MyHolder) holder;
        AuthorItem current = authorItems.get(position);
        myHolder.idAuthorName.setText(current.authorName);
        myHolder.authCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorClickEvent.onAuthorClick(current, position);
            }
        });
    }

    public void setOnAuthorClickListner(AuthorClickEvent authorClickEvent) {
        this.authorClickEvent = authorClickEvent;
    }

    public interface AuthorClickEvent {
        void onAuthorClick(AuthorItem authorItem, int pos);
    }
    @Override
    public int getItemCount() {
        return authorItems.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView idAuthorName;
        CardView authCard;

        public MyHolder(View itemView) {
            super(itemView);
            idAuthorName = (TextView) itemView.findViewById(R.id.idAuthorName);
            authCard = (CardView) itemView.findViewById(R.id.authCard);
        }
    }
}
