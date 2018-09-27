package com.example.suyadalib.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    int sum = 0;

    TextView tvSummary;
    EditText editTextResult;
    Button btnOK;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        sum = intent.getIntExtra("result", 0);

        bundle = (Bundle) intent.getBundleExtra("cBundle");
        Coordinate c1 = new Coordinate();
        c1.x = bundle.getInt("x");
        c1.y = bundle.getInt("y");
        c1.z = bundle.getInt("z");

        CoordinateSerializable c2 = (CoordinateSerializable) intent.getSerializableExtra("cSerializable");

        CoordinateParcelable c3 = intent.getParcelableExtra("cParcelable");

        initInstance();
    }

    private void initInstance() {
        tvSummary = (TextView) findViewById(R.id.tvSummary);
        tvSummary.setText("Result = " + sum);

        editTextResult = (EditText) findViewById(R.id.editTextResult);

        btnOK= (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnOK) {
            Intent retIntent = new Intent();
            retIntent.putExtra("result", editTextResult.getText().toString());
            setResult(RESULT_OK, retIntent);
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
