package com.yu.fragmenttest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yu.fragmenttest.R;


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

        setActivityRusult(intent, extras, id);
    }

    /**
     * 设置返回的结果
     * @param intent
     * @param extras
     * @param id
     */
    private void setActivityRusult(Intent intent, Bundle extras, String id) {
        extras.putString("id",String.valueOf(Integer.parseInt(id)+100)); // 改id的值
        intent.putExtras(extras);
        setResult(6,intent);
    }
}
