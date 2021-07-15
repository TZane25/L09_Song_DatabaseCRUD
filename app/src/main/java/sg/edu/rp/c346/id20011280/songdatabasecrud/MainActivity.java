package sg.edu.rp.c346.id20011280.songdatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView ID;
    TextView name;
    TextView Year;

    EditText editContent;
    EditText editSinger;
    EditText editYear;

    RadioGroup rGrp;
    RadioButton RB1;
    RadioButton RB2;
    RadioButton RB3;
    RadioButton RB4;
    RadioButton RB5;

    Button Insert;
    Button Show;

    ArrayList<Song> al;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = findViewById(R.id.ID);
        name = findViewById(R.id.name);
        Year = findViewById(R.id.year);

        editContent = findViewById(R.id.StContent);
        editSinger = findViewById(R.id.Sgname);
        editYear = findViewById(R.id.Year);
        rGrp = findViewById(R.id.rGroup);

        RB1 = findViewById(R.id.rb1);
        RB2 = findViewById(R.id.rb2);
        RB3 = findViewById(R.id.rb3);
        RB4 = findViewById(R.id.rb4);
        RB5 = findViewById(R.id.rb5);

        Insert = findViewById(R.id.btnInsert);
        Show = findViewById(R.id.btnShow);

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DBHelper dbh = new DBHelper(MainActivity.this);
                if(editContent.getText().toString().isEmpty() || editSinger.getText().toString().isEmpty() || editYear.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Missing input please ensure every field is keyed in", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String title = editContent.getText().toString();
                    String singers = editSinger.getText().toString();
                    int year = Integer.parseInt(editYear.getText().toString());

                    int selectedId = rGrp.getCheckedRadioButtonId(); // if selected
                    RadioButton radioButton = findViewById(selectedId);

                    int stars = Integer.parseInt(radioButton.getText().toString());

                    dbh.insertSong(title,singers,year,stars);
                    Toast.makeText(MainActivity.this, "Successfully inserted into database", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ListsActivity.class);
                startActivity(intent);

            }
        });
    }
}