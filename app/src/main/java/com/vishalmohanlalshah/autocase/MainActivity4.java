//Vishal Shah
//These are all the packages and dependencies for this class
package com.vishalmohanlalshah.autocase;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //This TextView object is for the one that shows the contents of the history file
        TextView view1 = findViewById(R.id.textViewHistory);

        //Button object for the delete button
        Button buttonDelete = findViewById(R.id.buttonDeleteHistory);

        //Button delete listener
        buttonDelete.setOnClickListener(this);

        /*The try catch is needed here because their could be a FileNotFoundException
        the setText method is called on view1 and the readHistory method is passed into setText
        as the text that will be displayed*/
        try {
            view1.setText(readHistory());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View view) {
        //This is for if the delete button is pressed. The deleteHistory method is called
        if(view.getId() == R.id.buttonDeleteHistory ){
            deleteHistory();
        }
    }

    public void deleteHistory(){
        File[] files = this.getFilesDir().listFiles();
        if(files != null) {
            for (File file : files) {
                file.delete();
                Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show();
            }
        }
        else{
            //For some reason, this toast is never shown. Need to figure out why
            Toast.makeText(this, "No Text To Delete", Toast.LENGTH_LONG).show();
        }

        TextView tempTextView = findViewById(R.id.textViewHistory);
        tempTextView.setText(" ");
    }

    public String readHistory() throws FileNotFoundException {
        FileInputStream fis = this.openFileInput("history.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        String contents;
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            contents = stringBuilder.toString();
        }
        return contents;
    }

}