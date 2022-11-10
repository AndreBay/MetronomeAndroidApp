package com.example.metronome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.time.Instant;
import java.util.Date;

import me.tankery.lib.circularseekbar.CircularSeekBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MetronomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MetronomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private CircularSeekBar progress_circular;
    private int max = 10000;
    private Button tap_button;
    private Date currentTime;
    private TextView textView1;


    public MetronomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MetronomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MetronomeFragment newInstance(String param1, String param2) {
        MetronomeFragment fragment = new MetronomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_metronome, container, false);
        tap_button = (Button) view.findViewById(R.id.tap_button);
        progress_circular = view.findViewById(R.id.progress_circular);
        textView1 = view.findViewById(R.id.textView);
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
            }
        });

        return view;
    }

    private float bpmConverter(long dt){
        float dtToSeconds = (float) dt / 1000;
        float bpm = 60/dtToSeconds;
        return bpm;
    }
}