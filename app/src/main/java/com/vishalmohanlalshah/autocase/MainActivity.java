//Vishal Shah
//These are all the packages and dependencies for this class
package com.vishalmohanlalshah.autocase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //This variable, "text", is the text that the user inputs into the text box in this activity
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Standard Android code when creating an app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*These variables get the ids of the buttons for MainActivity.xml and then puts listeners on them to see when they're clicked
        When the listener sees if its button is clicked, the onCLick method is run*/
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button buttonHistory = findViewById(R.id.buttonHistory);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        buttonHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //This gets the id for the textView in MainActivity
        TextView textString = findViewById(R.id.inputText1);

        //This extracts the text from textString and puts it in a temporary variable. This String will be manipulated in the if statements
        String tempText = textString.getText().toString();


        if(view.getId() == R.id.button1) {
            /*This makes tempText all uppercase and then text gets set to it
            It does not matter if there are any special characters (ex: *$%^,\')
            because the toUpperCase methods handles it*/
            text = upperCase(tempText);
        }

        else if(view.getId() == R.id.button2) {
            /*This makes tempText all lowercase and then text gets set to it
            It does not matter if there are any special characters (ex: *$%^,\')
            because the toLowerCase methods handles it*/
            text = lowerCase(tempText);
        }

        else if((view.getId() == R.id.button3)) {
            //This calls altAndRanMethod if the Alternate button is clicked
            text = altAndRanMethod(tempText, false);
        }

        else if(view.getId() == R.id.button4){
            //The ranFlag is set to true because the user selected the random button and then altAndRanMethod is called
            text = altAndRanMethod(tempText, true);
        }
        else{
            //This calls the method openActivityHistory because the user must have selected the HISTORY button
            openActivityHistory();
            //This is so that the openActivity2 is not reached
            return;
        }

        //When all the if statements are done the openActivity2 methods is called to open MainActivity2
        openActivity2(view);
    }



    //These two methods were created for abstraction.
    //They uppercase/lowercase the text that is passed into the method and then return it
    public String upperCase(String text){
        return text.toUpperCase();
    }
    public String lowerCase(String text){
        return text.toLowerCase();
    }


    public String altAndRanMethod(String tempText, Boolean ranFlag){
        //An ArrayList is created
        ArrayList<String> letList = new ArrayList<>();

        /*This for loop puts each letter of the String
        into each index of the ArrayList AND makes every
        letter lowercase because if there are any letters
        uppercase in the String, it will mess up the
        alternating upper/lower casing*/
        for(int i = 0; i<tempText.length();i++){
            letList.add(tempText.substring(i, i+1).toLowerCase());
        }

        if(!ranFlag){
         /*This for loop makes every other letter uppercase
        and it does not matter if there are any special
        characters (ex: *$%^,\') because the toUpperCase
        methods handles it*/
            for(int i = 0; i<letList.size(); i = i+2){
                letList.set(i, letList.get(i).toUpperCase());
            }
        }
        else{
            Random tempRan = new Random();
            /*This for loop makes random letters uppercase by using
             tempRan.nextInt(3) as the random number generator and it
             does not matter if there are any special characters
             (ex: *$%^,\') because the toUpperCase methods handles it*/
            for(int i = 0; i<letList.size(); i += tempRan.nextInt(3)){
                letList.set(i, letList.get(i).toUpperCase());
            }
        }

        /*The ArrayList has the be rebuilt into a String
        so the first thing that has to happen is this for each loop.
        A StringBuilder object (tempString) is created and then the
        for each puts every index of the ArrayList into the StringBuilder*/
        StringBuilder tempString = new StringBuilder();
        for(String x : letList){
            tempString.append(x);
        }

        /*text is set to tempString.totoString() because the toString()
        method extracts the pure String from the StringBuilder.
        The toString() method is needed because you cannot directly
        convert a StringBuilder object to a String */
        return tempString.toString();
    }



    public void openActivity2(View view){
        //This creates a new intent so when a button is clicked, the user can go to the next activity, MainActivity2
        Intent intent = new Intent(this, MainActivity2.class);
        //This lets the "text" variable be passed into the next activity so that it can be displayed
        intent.putExtra("text", text);
        //This starts the new activity
        startActivity(intent);
    }

    public void openActivityHistory(){
        //This creates a new intent so when a button is clicked, the user can go to the next activity, MainActivity4
        Intent intent = new Intent(this, MainActivity4.class);
        //This starts the new activity
        startActivity(intent);
    }

}