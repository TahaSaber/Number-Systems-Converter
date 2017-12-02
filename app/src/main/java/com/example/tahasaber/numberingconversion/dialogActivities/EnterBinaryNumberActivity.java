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

public class EnterBinaryNumberActivity extends Activity {

    private EditText takeBinary;
    private Button convert, cancel;
    private String binaryNumber = "";
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_binary);


       /* DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * 0.90), (int) (height * 0.41));*/

        takeBinary = (EditText) findViewById(R.id.take_binary);
        convert = (Button) findViewById(R.id.convert_button);
        cancel = (Button) findViewById(R.id.cancel_button);
        errorText = (TextView) findViewById(R.id.error_message);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeBinary.setText("");
                finish();
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binaryNumber = takeBinary.getText().toString();
                String expression = "[-+]?([0-1]*\\.[0-1]+|[0-1]+)";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(binaryNumber);

                if (binaryNumber.equals("") || !matcher.matches()) {
                    errorText.setText("Please Enter Valid Binary Value ex.  11 or 10.010 or .011");
                } else {

                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("binary", binaryNumber);
                    setResult(RESULT_OK, returnIntent);
                    finish();

                }
            }
        });
    }
}
