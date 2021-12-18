package com.example.bookjsonparsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookjsonparsing.Adapter.AdapterAuthor;
import com.example.bookjsonparsing.Adapter.AdapterBook;
import com.example.bookjsonparsing.Model.AuthorItem;
import com.example.bookjsonparsing.Model.BookItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView idRecyclerview, idRecyclerview1;
    private AdapterAuthor myAdapter;
    private AdapterBook adapterBook;

    String strJson = new String("[\n" + " {\n" + "  \"name\": \"Austen, Jane\",\n" + "  \"books\": [\n" + "   {\n" + "    \"title\": \"Pride and Prejudice\",\n" + "    \"isbn\": \"9788491051329\",\n" + "    \"text\": [\"It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife\", \"However little known the feelings or views of such a man may be on his first entering a neighbourhood, this truth is so well fixed in the minds of the surrounding families, that he is considered as the rightful property of some one or other of their daughters\"]\n" + "},\n" +
            "{\n" + "\"title\": \"Beach Town\",\n" + "    \"isbn\": \"9788491051330\",\n" + "    \"text\": [\"It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife\",\"However little known the feelings or views of such a man may be on his first entering a neighbourhood, this truth is so well fixed in the minds of the surrounding families, that he is considered as the rightful property of some one or other of their daughters\"]\n" + "},\n" +
            "{\n" + "\"title\": \"Loss Of Reason\",\n" + "    \"isbn\": \"9788491051331\",\n" + "    \"text\": [\"It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife\",\"However little known the feelings or views of such a man may be on his first entering a neighbourhood, this truth is so well fixed in the minds of the surrounding families, that he is considered as the rightful property of some one or other of their daughters\"]\n" + "}\n" + " ]\n" + " },\n" +
            "{\n" + "\"name\": \"Stephenson, Neal\",\n" + "  \"books\": [\n" + "   {\n" + "    \"title\": \"Snow Crash\",\n" + "    \"isbn\": \"9780140232929\",\n" + "    \"text\": [\n" + "     \"The Deliverator belongs to an elite order, a hallowed subcategory\",\n" + "     \"He's got esprit up to here\"\n" + "    ]\n" + "   },\n" +
            "{\n" + "\"title\": \"First Strike\",\n" + "    \"isbn\": \"9780140232930\",\n" + "    \"text\": [\n" + "     \"The Deliverator belongs to an elite order, a hallowed subcategory\",\n" + "     \"He's got esprit up to here\"\n" + "    ]\n" + "   },\n" +
            "{\n" + "\"title\": \"Independence Day\",\n" + "    \"isbn\": \"9780140232921\",\n" + "    \"text\": [\n" + "     \"The Deliverator belongs to an elite order, a hallowed subcategory\",\n" + "     \"He's got esprit up to here\"\n" + "    ]\n" + "   }\n" + "  ]\n" + " },\n" +
            "{\n" + "\"name\": \"Tolstoy Leo\",\n" + "  \"books\": [\n" + "   {\n" + "    \"title\": \"Anna Karenina\",\n" + "    \"isbn\": \"9780736639859\",\n" + "    \"text\": [\n" + "     \"Happy families are all alike; every unhappy family is unhappy in its own way\",\n" + "     \"Everything was in confusion in the Oblonskys' house\"\n" + "    ]\n" + "   },\n" +
            "{\n" + "\"title\": \"Army Of None\",\n" + "    \"isbn\": \"9780736639860\",\n" + "    \"text\": [\n" + "     \"Happy families are all alike; every unhappy family is unhappy in its own way\",\n" + "     \"Everything was in confusion in the Oblonskys' house\"\n" + "    ]\n" + "   }\n" + "  ]\n" + " }\n" + "]");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<AuthorItem> contactData = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(strJson);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                AuthorItem authorItem = new AuthorItem();
                authorItem.authorName = jsonObject.getString("name");

                JSONArray book = jsonObject.getJSONArray("books");

                List<BookItem> bookItemList = new ArrayList<>();
                for (int i1 = 0; i1 < book.length(); i1++) {
                    JSONObject jsonObject1 = book.getJSONObject(i1);
                    BookItem bookItem = new BookItem();
                    bookItem.bookTitle = jsonObject1.getString("title");
                    bookItem.bookIsbn = jsonObject1.getString("isbn");
                    bookItem.bookText = jsonObject1.getString("text");
                    bookItemList.add(bookItem);
                }
                authorItem.bookItemList.addAll(bookItemList);
                contactData.add(authorItem);
            }
            idRecyclerview = (RecyclerView) findViewById(R.id.idRecyclerview);
            myAdapter = new AdapterAuthor(MainActivity.this, contactData);
            idRecyclerview.setAdapter(myAdapter);
            idRecyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

            myAdapter.setOnAuthorClickListner(new AdapterAuthor.AuthorClickEvent() {
                @Override
                public void onAuthorClick(AuthorItem authorItem, int pos) {

                    idRecyclerview1 = (RecyclerView) findViewById(R.id.idRecyclerview1);
                    adapterBook = new AdapterBook(MainActivity.this, authorItem.bookItemList);
                    idRecyclerview1.setAdapter(adapterBook);
                    idRecyclerview1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                }
            });

            idRecyclerview1 = (RecyclerView) findViewById(R.id.idRecyclerview1);
            List<BookItem> list = new ArrayList();
            adapterBook = new AdapterBook(MainActivity.this, list);
            idRecyclerview1.setAdapter(adapterBook);
            idRecyclerview1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

            TextView idSeeAll = (TextView) findViewById(R.id.idSeeAll);
            idSeeAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<BookItem> bookItemArrayList = new ArrayList<>();
                    for (int i = 0; i < contactData.size(); i++) {
                        if (idSeeAll.getText() == "See All") {
                            idSeeAll.setText("See Less");
                        } else {
                            idSeeAll.setText("See All");
                        }
                        bookItemArrayList.addAll(contactData.get(i).bookItemList);
                    }
                    adapterBook.Update(bookItemArrayList);
                }
            });

        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}