package com.example.bookjsonparsing.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookjsonparsing.Model.BookItem;
import com.example.bookjsonparsing.MainActivity;
import com.example.bookjsonparsing.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBook extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    MainActivity mainActivity;
    LayoutInflater inflater;
    List<BookItem> bookItemList;

    public AdapterBook(MainActivity mainActivity, List<BookItem> bookItemList) {
        this.mainActivity = mainActivity;
        this.bookItemList = bookItemList;
        inflater = LayoutInflater.from(this.mainActivity);
    }

    public void Update(ArrayList<BookItem> arrayList) {
        this.bookItemList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.book_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        BookItem current = bookItemList.get(position);
        myHolder.textTitle.setText(current.bookTitle);
        myHolder.textIsbn.setText(current.bookIsbn);
        myHolder.text.setText(current.bookText);
    }

    @Override
    public int getItemCount() {
        return bookItemList.size();
    }

    public BookItem getItem(int position) {
        return this.bookItemList.get(position);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        //TextView textName;
        TextView textTitle;
        TextView textIsbn;
        TextView text;
        //Button idRemove;


        public MyHolder(View itemView) {
            super(itemView);
            //textName = (TextView) itemView.findViewById(R.id.textName);
            textTitle = (TextView) itemView.findViewById(R.id.textTitle);
            textIsbn = (TextView) itemView.findViewById(R.id.textIsbn);
            text = (TextView) itemView.findViewById(R.id.text);
           //idRemove = (Button) itemView.findViewById(R.id.idRemove);
        }
    }
}
