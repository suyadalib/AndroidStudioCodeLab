package com.example.suyadalib.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int sum = 0;
    TextView tvSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        sum = intent.getIntExtra("result", 0);

        initInstance();
    }

    private void initInstance() {
        tvSummary = (TextView) findViewById(R.id.tvSummary);
        tvSummary.setText("Result = " + sum);
    }
}
