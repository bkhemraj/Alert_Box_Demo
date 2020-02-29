package com.khemraj.alertdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.french){
            sharedPreferences.edit().putString("language","French").apply();
            textView.setText(sharedPreferences.getString("language","Error"));
        }
        if(item.getItemId() == R.id.spanish){
            sharedPreferences.edit().putString("language","Spanish").apply();
            textView.setText(sharedPreferences.getString("language","Error"));
        }
        return super.onOptionsItemSelected(item);
    }

    public void setLanguage (String language){
     sharedPreferences.edit().putString("language",language).apply();
        textView = findViewById(R.id.textView);
       textView.setText(sharedPreferences.getString("language",""));
   }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.khemraj.alertdemo", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "Error");
        language = "Error";

        if (language.equals("Error")){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Choose Your Preffered Language.")
                    .setMessage("between following languages")
                    .setPositiveButton("French", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("French");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("Spanish");
                        }
                    }).show();
        }else {
            textView = findViewById(R.id.textView);
            textView.setText(language);

        }
    }

}
