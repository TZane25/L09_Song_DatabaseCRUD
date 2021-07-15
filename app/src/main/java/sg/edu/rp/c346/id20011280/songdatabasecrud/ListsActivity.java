package sg.edu.rp.c346.id20011280.songdatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListsActivity extends AppCompatActivity {

    Button btnTopfive;
    ListView listView;
    ArrayList<Song> songsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        btnTopfive = findViewById(R.id.btnfivestars);
        listView = findViewById(R.id.lv);

        songsArray = new ArrayList<Song>();

        ArrayAdapter songadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songsArray);
        listView.setAdapter(songadapter);

        DBHelper dbh = new DBHelper(ListsActivity.this);
        songsArray.addAll(dbh.getAllSongs());
        songadapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song target = songsArray.get(position);

                Intent intent = new Intent(ListsActivity.this, EditsActivity.class);
                intent.putExtra("data", target);

                startActivity(intent);

            }
        });
        btnTopfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListsActivity.this);
                songsArray.clear();
                songsArray.addAll(dbh.getAllSongs());
                songadapter.notifyDataSetChanged();
            }
        });

    }
}