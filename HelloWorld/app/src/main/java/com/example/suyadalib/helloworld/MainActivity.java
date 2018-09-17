package com.example.suyadalib.helloworld;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

    }

    private void initInstances() {
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        tvHello.setText(Html.fromHtml("<b>Hello</b> <i>World</i> <a href=\"https://www.github.com/suyadalib\">my github</a>"));

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

    }

    @Override
    public void onClick(View view) {
        if (view == btnCopy) {
            tvHello.setText(editTextHello.getText());
        }
        if (view == btnCalculate) {
            int result = Calculate();
            tvResult.setText("= " + result);
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
}
