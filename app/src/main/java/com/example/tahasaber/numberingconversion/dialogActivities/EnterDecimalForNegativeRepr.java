package com.example.tahasaber.numberingconversion.dialogActivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tahasaber.numberingconversion.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnterDecimalForNegativeRepr extends Activity {


    private EditText takeDecimal, takeBits;
    private Button convert, cancel;
    private String decimalNumber = "", bitsNum = "";
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_decimal_for_negative_repr);

      /*  DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * 0.90), (int) (height * 0.41));*/

        takeDecimal = (EditText) findViewById(R.id.take_integer);
        takeBits = (EditText) findViewById(R.id.take_bits);
        convert = (Button) findViewById(R.id.convert_button);
        cancel = (Button) findViewById(R.id.cancel_button);
        errorText = (TextView) findViewById(R.id.error_message);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeDecimal.setText("");
                finish();
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decimalNumber = takeDecimal.getText().toString();
                bitsNum = takeBits.getText().toString();
                String expression = "[-+]?([0-9]+)";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(decimalNumber);

                String bitsValidation = "([0-9]+)";
                Pattern bitsPattern = Pattern.compile(bitsValidation, Pattern.CASE_INSENSITIVE);
                Matcher bitsMatcher = pattern.matcher(bitsNum);

                if (decimalNumber.equals("") || !matcher.matches()) {
                    errorText.setText("Please Enter Valid Integer value ex. 12, -3");
                } else if (takeBits.getText().toString().equals("") || !bitsMatcher.matches()) {
                    errorText.setText("Please Enter Valid Bits Number to Represent");
                } else {

                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("integer", decimalNumber);
                    returnIntent.putExtra("bits", bitsNum);
                    setResult(RESULT_OK, returnIntent);
                    finish();

                }
            }
        });
    }
}
