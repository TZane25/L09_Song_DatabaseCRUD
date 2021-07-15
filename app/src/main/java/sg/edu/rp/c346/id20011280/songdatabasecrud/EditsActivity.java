package sg.edu.rp.c346.id20011280.songdatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditsActivity extends AppCompatActivity {

    EditText editTitle, editSingers, editYear;
    RadioGroup rGrp;
    Button btnUpd,btnDel;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edits);

        editTitle = findViewById(R.id.editTitle);
        editSingers = findViewById(R.id.editSingers);
        editYear = findViewById(R.id.editYear);
        rGrp = findViewById(R.id.rgroup);
        btnUpd = findViewById(R.id.btnUpdate);
        btnDel = findViewById(R.id.btnDelete);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");
        System.out.println(data);

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditsActivity.this); // fail criteria
                if(editTitle.getText().toString().isEmpty() || editSingers.getText().toString().isEmpty() || editYear.getText().toString().isEmpty()){
                    Toast.makeText(EditsActivity.this, "Missing input", Toast.LENGTH_SHORT).show();
                }
                else{ // pass criteria

                    int selectedId = rGrp.getCheckedRadioButtonId(); // storing selected radio button
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);

                    int stars = Integer.parseInt(radioButton.getText().toString());

                    data.setTitle(editTitle.getText().toString());
                    data.setSingers(editSingers.getText().toString());
                    data.setYear(Integer.parseInt(editYear.getText().toString()));

                    data.setStars(stars);

                    System.out.println(data);

                    dbh.updateSong(data);
                    dbh.close();
                    Toast.makeText(EditsActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditsActivity.this);
                dbh.deleteSong(data.getId());
            }
        });



    }
}