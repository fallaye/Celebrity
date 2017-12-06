package mobileappscompany.celebrity;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class SaveToFileActivity extends AppCompatActivity {

    Context context;
    private static final String fileName = "myFile.txt";

    ArrayList<Celebrity> celebrityArrayList = null;

    TextView tvSaveFileConfirmTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_file);

        String string = "Hello world!";
        FileOutputStream outputStream;

        celebrityArrayList = getIntent().getParcelableArrayListExtra("SaveToFileActivity");

        Iterator<Celebrity> iterator = celebrityArrayList.iterator();
        Celebrity celebrity;

        String filePath = getApplicationContext().getFilesDir() + "/" + "myFile.txt";

        if(celebrityArrayList == null){
            Toast.makeText(this, "In Display Activity. No celebrities to show. ", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            //outputStream.write(string.getBytes());


            while (iterator.hasNext()) {

                celebrity = iterator.next();

                System.out.println("id: " + celebrity.getId() + "   name: " +
                        celebrity.getName() + "     age: " + celebrity.getAge() +
                        "       gender: " + celebrity.getGender());

                outputStream.write(celebrity.toString().getBytes());

                tvSaveFileConfirmTextView = findViewById(R.id.tvSaveFileConfirmTextView);
                tvSaveFileConfirmTextView.setText("Data successfully saved in: \n " + filePath);


            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void showFileContent(View view) {

        String tvText = "";
        TextView tvTextView = findViewById(R.id.tvTextView);
        if(view.getId() > 0){
            tvText = read_file(getApplicationContext(), "myFile.txt");
        }

        tvTextView.setText(tvText);


    }


    public String read_file(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }
}
