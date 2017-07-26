package com.ck_telecom.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String id = (String) extras.get("id");
        TextView tvId = (TextView) findViewById(R.id.id_tv_fragment_detail_land);
        tvId.setText("item:"+id);

    }
}
