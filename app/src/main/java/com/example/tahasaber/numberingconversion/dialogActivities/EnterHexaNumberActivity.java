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

public class EnterHexaNumberActivity extends Activity {

    private EditText takehexa;
    private Button convert, cancel;
    private String hexaNumber = "";
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_hexa_number);


      /*  DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * 0.90), (int) (height * 0.41));*/

        takehexa = (EditText) findViewById(R.id.take_hexa);
        convert = (Button) findViewById(R.id.convert_button);
        cancel = (Button) findViewById(R.id.cancel_button);
        errorText = (TextView) findViewById(R.id.error_message);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takehexa.setText("");
                finish();
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hexaNumber = takehexa.getText().toString();
                String expression = "[-+]?([0-9a-fA-F]*\\.[0-9a-fA-F]+|[0-9a-fA-F]+)";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(hexaNumber);

                if (hexaNumber.equals("") || !matcher.matches()) {
                    errorText.setText("Please Enter Valid Hexadecimal value ex. 123 or 123.456 or A.456 .ac4");
                } else {

                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("hexadecimal", hexaNumber);
                    setResult(RESULT_OK, returnIntent);
                    finish();

                }
            }
        });


    }
}
