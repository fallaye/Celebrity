package mobileappscompany.celebrity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvCelId, tvCelName, tvCelAge, tvCelGender;

    EditText etCelId, etCelName, etCelAge, etCelGender;
    //CheckBox chBoxCelFavorite;

    String id, name, age, gender;
    public static final String TAG = "Main Activity";

    ArrayList<Celebrity> celebrityList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCelId = findViewById(R.id.tvCelId);
        tvCelName = findViewById(R.id.tvCelName);
        tvCelAge = findViewById(R.id.tvCelAge);
        tvCelGender = findViewById(R.id.tvCelGender);
        //tvCelFavorite = findViewById(R.id.tvCelFavorite);

        etCelId = findViewById(R.id.etCelId);
        etCelName = findViewById(R.id.etCelName);
        etCelAge = findViewById(R.id.etCelAge);
        etCelGender = findViewById(R.id.etCelGender);
        //chBoxCelFavorite = findViewById(R.id.chBoxCelFavorite);

    }

    public void databaseOperations(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        switch (view.getId()){
            case R.id.btnAddCel:
                id = etCelId.getText().toString();
                name = etCelName.getText().toString();
                age = etCelAge.getText().toString();
                gender = etCelGender.getText().toString();
//                if(chBoxCelFavorite.isChecked()){
//                    favorite = "1";
//                }else{
//                    favorite = "0";
//                }
                Celebrity celebrity = new Celebrity(id, name, age, gender);

                long rowId = databaseHelper.addCelebrity(celebrity);

                if(rowId > 0){
                    Toast.makeText(this, "Person added! ", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Person not added! ", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnDeleteCel:
                boolean result = false;
                result = databaseHelper.deleteCelebrity(etCelId.getText().toString());
                if(result){
                    Toast.makeText(this, "Person deleted! ", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Person not deleted! ", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnUpdateCel:

                id = etCelId.getText().toString();
                name = etCelName.getText().toString();
                age = etCelAge.getText().toString();
                gender = etCelGender.getText().toString();
                Celebrity celUpdate = new Celebrity(id, name, age, gender);

                long rowIdUpdate = databaseHelper.updateCelebrity(celUpdate);

                if(rowIdUpdate > 0){
                    Toast.makeText(this, "Person updated! ", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Person not updated! ", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btnShowAllCel:
                celebrityList = databaseHelper.getAllCelebrities();
                for(Celebrity cel: celebrityList){
                    Log.d(TAG, "Database Operations - Show all celebrities: "
                    + cel.toString());
                }

                if(celebrityList == null){
                    Toast.makeText(this, "No celebrities to show.", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(this, DisplayActivity.class);

                intent.putParcelableArrayListExtra("CelebrityArrayList", celebrityList);
                startActivity(intent);

                break;

            case R.id.btnSaveToFile:
                celebrityList = databaseHelper.getAllCelebrities();


                if(celebrityList == null){
                    Toast.makeText(this, "No celebrities to show.", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intentSaveToFile = new Intent(this, SaveToFileActivity.class);

                intentSaveToFile.putParcelableArrayListExtra("SaveToFileActivity", celebrityList);
                startActivity(intentSaveToFile);

                break;

            default:

                break;
        }//end switch

    }//end databaseOperations
}
