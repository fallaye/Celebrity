package mobileappscompany.celebrity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class DisplayActivity extends AppCompatActivity {

    //TextView tvCelId, tvCelName, tvCelAge, tvCelGender, tvCelFavorite;
    ArrayList<Celebrity> celebrityArrayList = null;

    Button btnUpdateCel;
    Button btnDeleteCel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TableRow.LayoutParams params1 = new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1.0f);

        TableRow.LayoutParams params2 = new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        TableLayout tbl = findViewById(R.id.tlMarksTable);

        celebrityArrayList = getIntent().getParcelableArrayListExtra("CelebrityArrayList");

        //celebrityArrayList = getIntent().getParcelableArrayListExtra("CelebrityArrayList");
        if(celebrityArrayList == null){
            Toast.makeText(this, "In Display Activity. No celebrities to show. ", Toast.LENGTH_LONG).show();
        return;
        }

        Iterator<Celebrity> iterator = celebrityArrayList.iterator();
        Celebrity celebrity;

        TableRow headerRow = new TableRow(this);
        TextView tvHeaderIdTextView = new TextView(this);
        TextView tvHeaderNameTextView = new TextView(this);
        TextView tvHeaderAgeTextView = new TextView(this);
        TextView tvHeaderGenderTextView = new TextView(this);
        TextView tvHeaderEmpty1TextView = new TextView(this);
        TextView tvHeaderEmpty2TextView = new TextView(this);

        tvHeaderIdTextView.setText("Id:    ");
        tvHeaderNameTextView.setText("Name:    ");
        tvHeaderAgeTextView.setText("Age:    ");
        tvHeaderGenderTextView.setText("Gender:    ");
        tvHeaderEmpty1TextView.setText("    ");
        tvHeaderEmpty2TextView.setText("    ");

        tvHeaderIdTextView.setLayoutParams(params1);
        tvHeaderNameTextView.setLayoutParams(params1);
        tvHeaderAgeTextView.setLayoutParams(params1);
        tvHeaderGenderTextView.setLayoutParams(params1);
        tvHeaderEmpty1TextView.setLayoutParams(params1);
        tvHeaderEmpty2TextView.setLayoutParams(params1);

        headerRow.addView(tvHeaderIdTextView);
        headerRow.addView(tvHeaderNameTextView);
        headerRow.addView(tvHeaderAgeTextView);
        headerRow.addView(tvHeaderGenderTextView);
        headerRow.addView(tvHeaderEmpty1TextView);
        headerRow.addView(tvHeaderEmpty2TextView);

        headerRow.setLayoutParams(params2);
        tbl.addView(headerRow);

        while (iterator.hasNext()){
            if(iterator == null) {
                Toast.makeText(this, "In Iterator. ", Toast.LENGTH_LONG).show();
            }
            celebrity = iterator.next();

            System.out.println("id: " + celebrity.getId() + "   name: " +
            celebrity.getName() + "     age: " + celebrity.getAge() +
            "       gender: " + celebrity.getGender());

            //Create new tablerows and textviews
            TableRow row = new TableRow(this);
            EditText etCelebrityId = new EditText(this);
            EditText etCelebrityName = new EditText(this);
            EditText etCelebrityAge = new EditText(this);
            EditText etCelebrityGender = new EditText(this);
            btnUpdateCel = new Button(this);
            btnDeleteCel = new Button(this);

            //setting the text
            etCelebrityId.setText(celebrity.getId());
            etCelebrityName.setText(celebrity.getName());
            etCelebrityAge.setText(celebrity.getAge());
            etCelebrityGender.setText(celebrity.getGender());
            btnUpdateCel.setText("Update");
            btnDeleteCel.setText("Delete");


            etCelebrityId.setLayoutParams(params1);
            etCelebrityName.setLayoutParams(params1);
            etCelebrityAge.setLayoutParams(params1);
            etCelebrityGender.setLayoutParams(params1);
            btnUpdateCel.setLayoutParams(params1);
            btnDeleteCel.setLayoutParams(params1);

            //add textviews to the rows created

            row.addView(etCelebrityId);
            row.addView(etCelebrityName);
            row.addView(etCelebrityAge);
            row.addView(etCelebrityGender);
            row.addView(btnUpdateCel);
            row.addView(btnDeleteCel);

            row.setLayoutParams(params2);
            tbl.addView(row);



        }//end of while loop




    }


    public void saveToFile(View view) {
    }
}
