package com.melnikov.virtualmuseum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    private StringBuilder text = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        BufferedReader reader = null;
        String FILENAME = name+".txt";
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(FILENAME)));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {}
            }
            TextView output=  findViewById(R.id.textView2);
            output.setText(text);
        }
    }
}