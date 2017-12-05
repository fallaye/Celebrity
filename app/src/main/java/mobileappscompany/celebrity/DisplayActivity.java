package mobileappscompany.celebrity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class DisplayActivity extends AppCompatActivity {

    //TextView tvCelId, tvCelName, tvCelAge, tvCelGender, tvCelFavorite;
    ArrayList<Celebrity> celebrityArrayList = null;

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
        if(celebrityArrayList == null){
            Toast.makeText(this, "In Display Activity. No celebrities to show. ", Toast.LENGTH_LONG).show();
        return;
        }

        Iterator<Celebrity> iterator = celebrityArrayList.iterator();
        Celebrity celebrity;
        while (iterator.hasNext()){
            if(iterator == null) {
                Toast.makeText(this, "In Iterator. ", Toast.LENGTH_LONG).show();
            }
            celebrity = iterator.next();

            System.out.println("id: " + celebrity.getId() + "   name: " +
            celebrity.getName() + "     age: " + celebrity.getAge() +
            "       gender: " + celebrity.getGender() +
            "       favorite: " + celebrity.getFavorite());

            //Create new tablerows and textviews
            TableRow row = new TableRow(this);
            TextView tvCelebrityId = new TextView(this);
            TextView tvCelebrityName = new TextView(this);
            TextView tvCelebrityAge = new TextView(this);
            TextView tvCelebrityGender = new TextView(this);
            //CheckBox checkBoxCelebrityFavorite = new CheckBox(this);

            //setting the text
            tvCelebrityId.setText(celebrity.getId());
            tvCelebrityName.setText(celebrity.getName());
            tvCelebrityAge.setText(celebrity.getAge());
            tvCelebrityGender.setText(celebrity.getGender());
            //checkBoxCelebrityFavorite.setText(celebrity.getFavorite());

            tvCelebrityId.setLayoutParams(params1);
            tvCelebrityName.setLayoutParams(params1);
            tvCelebrityAge.setLayoutParams(params1);
            tvCelebrityGender.setLayoutParams(params1);
            //checkBoxCelebrityFavorite.setLayoutParams(params1);

            //add textviews to the rows created

            row.addView(tvCelebrityId);
            row.addView(tvCelebrityName);
            row.addView(tvCelebrityAge);
            row.addView(tvCelebrityGender);
            //row.addView(checkBoxCelebrityFavorite);

            row.setLayoutParams(params2);
            tbl.addView(row);



        }



    }



}
