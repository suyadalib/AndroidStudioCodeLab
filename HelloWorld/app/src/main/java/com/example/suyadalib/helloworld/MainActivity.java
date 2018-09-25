package com.example.suyadalib.helloworld;

import android.graphics.Point;
import android.provider.MediaStore;
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
        setContentView(R.layout.activity_main);

        initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;  //screen width
        int height = size.y; //screen height

        Toast.makeText(MainActivity.this, "Width = " + width + ", Height = " + height, Toast.LENGTH_SHORT).show();

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
