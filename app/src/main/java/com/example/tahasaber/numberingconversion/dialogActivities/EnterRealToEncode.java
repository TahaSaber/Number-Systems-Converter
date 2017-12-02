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

public class EnterRealToEncode extends Activity {

    private EditText takeReal;
    private Button convert, cancel;
    private String realNumber = "";
    private TextView errorText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_real_to_encode);

       /* DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * 0.90), (int) (height * 0.41));*/

        takeReal = (EditText) findViewById(R.id.take_real_number);
        convert = (Button) findViewById(R.id.convert_button);
        cancel = (Button) findViewById(R.id.cancel_button);
        errorText = (TextView) findViewById(R.id.error_message);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeReal.setText("");
                finish();
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realNumber = takeReal.getText().toString();
                String expression = "[-+]?([0-9]*\\.[0-9]+|[0-9]+)";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(realNumber);

                if (realNumber.equals("") || !matcher.matches()) {
                    errorText.setText("Please Enter Valid Decimal value ex.  123 or 123.456 or .456");
                } else {

                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("real", realNumber);
                    setResult(RESULT_OK, returnIntent);
                    finish();

                }
            }
        });
    }
}
