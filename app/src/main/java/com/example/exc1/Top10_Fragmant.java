package com.example.exc1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Top10_Fragmant extends Fragment {
    private TextView[] top10;
    private Button top10_BTN_menu;
    public final int TOP = 10;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top10, container, false);
        top10_BTN_menu = view.findViewById(R.id.top10_BTN_menu);
        top10_BTN_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                view.finish();
            }
        });
        TextView[] top10 = new TextView[TOP];
        top10[0] = view.findViewById(R.id.top10_TXT_1);
        top10[1] = view.findViewById(R.id.top10_TXT_2);
        top10[2] = view.findViewById(R.id.top10_TXT_3);
        top10[3] = view.findViewById(R.id.top10_TXT_4);
        top10[4] = view.findViewById(R.id.top10_TXT_5);
        top10[5] = view.findViewById(R.id.top10_TXT_6);
        top10[6] = view.findViewById(R.id.top10_TXT_7);
        top10[7] = view.findViewById(R.id.top10_TXT_8);
        top10[8] = view.findViewById(R.id.top10_TXT_9);
        top10[9] = view.findViewById(R.id.top10_TXT_10);
        ArrayList<HighScore> records = Top_10.getInstance().getRecords();
        for (int i = 0; i < records.size(); i++) {
            top10[i].setText(records.get(i).getName() + " : " + records.get(i).getAttacks());
        }
    return view;
    }
}
