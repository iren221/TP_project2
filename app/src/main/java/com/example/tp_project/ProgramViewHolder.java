package com.example.tp_project;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder {
    ImageView itemImage;
    TextView petName;
    ProgramViewHolder(View v) {
        itemImage = v.findViewById(R.id.btn_photo);
        petName = v.findViewById(R.id.name_recommend);
    }
}
