package com.example.metronome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.Instant;
import java.util.Date;


import me.tankery.lib.circularseekbar.CircularSeekBar;

public class MainActivity extends AppCompatActivity {

    private CircularSeekBar progress_circular;
    private int max = 10000;
    private Button tap_button;
    private Date currentTime;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tap_button = findViewById(R.id.tap_button);
        progress_circular = findViewById(R.id.progress_circular);
        textView1 = findViewById(R.id.textView);
        progress_circular.setMax(max);
        progress_circular.setProgress(0);


        tap_button.setOnClickListener(new View.OnClickListener() {
            long now = Instant.now().toEpochMilli();
            long firstTap = now;
            long secondTap = now;
            long dt;

            @Override
            public void onClick(View view) {
                firstTap = secondTap;
                secondTap = Instant.now().toEpochMilli();
                dt = secondTap - firstTap;
                textView1.setText(Long.toString(dt));
                textView1.setText(Float.toString(bpmConverter(dt)));


                //currentTime = Calendar.getInstance().getTime();
                /*Date currentTime = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
                String currentDateandTime = sdf.format(new Date());*/
            }
        });
    }
    private float bpmConverter(long dt){
        float dtToSeconds = (float) dt / 1000;
        float bpm = 60/dtToSeconds;
        return bpm;
    }
}