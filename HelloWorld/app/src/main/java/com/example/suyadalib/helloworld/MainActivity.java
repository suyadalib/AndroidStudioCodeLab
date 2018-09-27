package com.example.suyadalib.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private static int REQUEST_CODE_SECOND = 123;

    TextView tvHello;
    EditText editTextHello;
    Button btnCopy;

    TextView tvResult;
    EditText editNumber1;
    EditText editNumber2;
    Button btnCalculate;

    CheckBox chkAgree;
//    RadioButton rbPlus;
//    RadioButton rbMinus;
//    RadioButton rbMultiply;
//    RadioButton rbDivide;

    RadioGroup rgOperator;

    CustomViewGroup viewGroup1;
    CustomViewGroup viewGroup2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.welcome);

        //initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;  //screen width
        int height = size.y; //screen height

        Toast.makeText(MainActivity.this, "Width = " + width + ", Height = " + height, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save thing(s) here

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore thing(s) here

    }

    private void initInstances() {
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        //tvHello.setText(Html.fromHtml("<b>Hello</b> <i>World</i> <a href=\"https://www.github.com/suyadalib\">my github</a>"));

        editTextHello = (EditText) findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(this);

        btnCopy = (Button) findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);

        // calculate
        editNumber1 = (EditText) findViewById(R.id.editNumber1);
        editNumber2 = (EditText) findViewById(R.id.editNumber2);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(this);

//        rbPlus = (RadioButton) findViewById(R.id.rbPlus);
//        rbMinus = (RadioButton) findViewById(R.id.rbMinus);
//        rbMultiply = (RadioButton) findViewById(R.id.rbMultiply);
//        rbDivide = (RadioButton) findViewById(R.id.rbDivide);
        rgOperator = (RadioGroup) findViewById(R.id.rgOperator);


        // Custom View Group
        viewGroup1 = (CustomViewGroup) findViewById(R.id.viewGroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.viewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");

    }

    @Override
    public void onClick(View view) {
        if (view == btnCopy) {
            tvHello.setText(editTextHello.getText());
        }
        if (view == btnCalculate) {
            int result = Calculate();
            tvResult.setText("= " + result);
            Log.d("[CALCULATION]", "result = " + result);
            Toast.makeText(MainActivity.this, "Result = " + result, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("result", result);

            Coordinate c1 = new Coordinate();
            c1.x = 5;
            c1.y = 10;
            c1.z = 20;

            Bundle bundle = new Bundle();
            bundle.putInt("x", c1.x);
            bundle.putInt("y", c1.y);
            bundle.putInt("z", c1.z);
            intent.putExtra("cBundle", bundle);

            CoordinateSerializable c2 = new CoordinateSerializable();
            c2.x = 5;
            c2.y = 10;
            c2.z = 20;
            intent.putExtra("cSerializable", c2);

            CoordinateParcelable c3 = new CoordinateParcelable();
            c3.x = 5;
            c3.y = 10;
            c3.z = 20;
            intent.putExtra("cParcelable", c3);

            startActivityForResult(intent, REQUEST_CODE_SECOND);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SECOND) {
            if (resultCode == RESULT_OK) {
                // Get data from data's Extra
                String result = data.getStringExtra("result");
                Toast.makeText(MainActivity.this, "Result is " + result, Toast.LENGTH_LONG).show();
            }
        }
    }

    private int Calculate() {
        int number1 = 0;
        int number2 = 0;
        int result = 0;
        try {
            number1 = Integer.parseInt(editNumber1.getText().toString());
        } catch (NumberFormatException e) {

        }

        try {
            number2 = Integer.parseInt(editNumber2.getText().toString());
        } catch (NumberFormatException e) {

        }

        switch (rgOperator.getCheckedRadioButtonId()) {
            case R.id.rbPlus:
                result = number1 + number2;
                break;
            case R.id.rbMinus:
                result = number1 - number2;
                break;
            case R.id.rbMultiply:
                result = number1 * number2;
                break;
            case R.id.rbDivide:
                try {
                    result = number1 / number2;
                } catch (ArithmeticException e) {
                }
                break;
        }
        return result;
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (textView == tvHello) {
            if (actionId == EditorInfo.IME_ACTION_DONE ) {
                tvHello.setText(editTextHello.getText());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Click Settings", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
