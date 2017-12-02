package com.example.tahasaber.numberingconversion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tahasaber.numberingconversion.R;
import com.example.tahasaber.numberingconversion.dialogActivities.EnterBinaryNumberActivity;
import com.example.tahasaber.numberingconversion.dialogActivities.EnterDecimalNumberActivity;
import com.example.tahasaber.numberingconversion.dialogActivities.EnterHexaNumberActivity;
import com.example.tahasaber.numberingconversion.dialogActivities.EnterOctalNumberActivity;
import com.example.tahasaber.numberingconversion.numberConverter.FromBinaryToOthers;
import com.example.tahasaber.numberingconversion.numberConverter.FromDecimalToOthers;
import com.example.tahasaber.numberingconversion.numberConverter.FromHexaDecimalToOthers;
import com.example.tahasaber.numberingconversion.numberConverter.FromOctalToOthers;

import static android.app.Activity.RESULT_OK;


public class NumberConverterFragment extends android.app.Fragment {

    private TextView decimalText, binaryText, octalText, hexaText;
    private FromDecimalToOthers fromDecimalToOthers;
    private FromBinaryToOthers fromaBinaryToOthers;
    private FromOctalToOthers fromOctalToOthers;
    private FromHexaDecimalToOthers fromHexaDecimalToOthers;


    static final int REQUESTCODEDECIMAL = 1;
    static final int REQUESTCODEBINARY = 2;
    static final int REQUESTCODEOCTAL = 3;
    static final int REQUESTCODEHEXA = 4;


    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_number_converter, container, false);



        decimalText = (TextView) rootView.findViewById(R.id.decimal);
        binaryText = (TextView) rootView.findViewById(R.id.binary);
        octalText = (TextView) rootView.findViewById(R.id.octal);
        hexaText = (TextView) rootView.findViewById(R.id.hexadecimal);
        fromDecimalToOthers = new FromDecimalToOthers();
        fromaBinaryToOthers = new FromBinaryToOthers();
        fromOctalToOthers = new FromOctalToOthers();
        fromHexaDecimalToOthers = new FromHexaDecimalToOthers();
        if(savedInstanceState!=null)
        {
            decimalText.setText(savedInstanceState.getString("d"));
            binaryText.setText(savedInstanceState.getString("b"));
            octalText.setText(savedInstanceState.getString("o"));
            hexaText.setText(savedInstanceState.getString("h"));
            //handle your data on screen rotation
        }

        decimalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFromDecimal();
            }
        });

        binaryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFromBinary();
            }
        });
        octalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFromOctal();
            }
        });
        hexaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFromHexa();
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("d", decimalText.getText().toString());
        outState.putString("b", binaryText.getText().toString());
        outState.putString("o", octalText.getText().toString());
        outState.putString("h", hexaText.getText().toString());
    }

    public void setFromDecimal() {

        Intent intent = new Intent(getActivity(), EnterDecimalNumberActivity.class);
        startActivityForResult(intent, REQUESTCODEDECIMAL);
    }

    public void setFromBinary() {
        Intent intent = new Intent(getActivity(), EnterBinaryNumberActivity.class);
        startActivityForResult(intent, REQUESTCODEBINARY);
    }

    public void setFromOctal() {
        Intent intent = new Intent(getActivity(), EnterOctalNumberActivity.class);
        startActivityForResult(intent, REQUESTCODEOCTAL);

    }

    public void setFromHexa() {

        Intent intent = new Intent(getActivity(), EnterHexaNumberActivity.class);
        startActivityForResult(intent, REQUESTCODEHEXA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODEDECIMAL) {
            if (resultCode == RESULT_OK) {
                String decimal = data.getStringExtra("decimal");
                convertFromDecimal(decimal);
                //Toast.makeText(getActivity(), "Convert From Decimal...", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUESTCODEBINARY) {
            if (resultCode == RESULT_OK) {
                String binary = data.getStringExtra("binary");
                convertFromBinary(binary);
               // Toast.makeText(getActivity(), "Convert From Binary...", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUESTCODEOCTAL) {
            if (resultCode == RESULT_OK) {
                String octal = data.getStringExtra("octal");
                convertFromOctal(octal);
              //  Toast.makeText(getActivity(), "Convert From Octal...", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUESTCODEHEXA) {
            if (resultCode == RESULT_OK) {
                String hexadecimal = data.getStringExtra("hexadecimal");
                convertFromHexa(hexadecimal);
              //  Toast.makeText(getActivity(), "Convert From Hexadecimal...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void convertFromDecimal(String decimal) {

        decimalText.setText(decimal.replaceAll("....(?=.)", "$0  "));
        binaryText.setText(fromDecimalToOthers.decimalToBinary(decimal).replaceAll("....(?=.)", "$0  "));
        octalText.setText(fromDecimalToOthers.decimalToOctal(decimal).replaceAll("....(?=.)", "$0  "));
        hexaText.setText(fromDecimalToOthers.decimalToHexadecimal(decimal).replaceAll("....(?=.)", "$0  "));
    }

    public void convertFromBinary(String binary) {

        binaryText.setText(binary.replaceAll("....(?=.)", "$0  "));
        decimalText.setText(fromaBinaryToOthers.binaryToDecimal(binary).replaceAll("....(?=.)", "$0  "));
        octalText.setText(fromaBinaryToOthers.binaryToOctal(binary).replaceAll("....(?=.)", "$0  "));
        hexaText.setText(fromaBinaryToOthers.binaryToHexadecimal(binary).replaceAll("....(?=.)", "$0  "));
    }

    public void convertFromOctal(String octal) {

        octalText.setText(octal.replaceAll("....(?=.)", "$0  "));
        decimalText.setText(fromOctalToOthers.octalToDecimal(octal).replaceAll("....(?=.)", "$0  "));
        binaryText.setText(fromOctalToOthers.octalToBinary(octal).replaceAll("....(?=.)", "$0  "));
        hexaText.setText(fromOctalToOthers.octalToHexadecimal(octal).replaceAll("....(?=.)", "$0  "));
    }

    public void convertFromHexa(String hexadecimal) {

        hexaText.setText(hexadecimal.replaceAll("....(?=.)", "$0  "));
        decimalText.setText(fromHexaDecimalToOthers.hexadecimalToDecimal(hexadecimal).replaceAll("....(?=.)", "$0  "));
        binaryText.setText(fromHexaDecimalToOthers.hexadecimalToBinary(hexadecimal).replaceAll("....(?=.)", "$0  "));
        octalText.setText(fromHexaDecimalToOthers.hexadecimalToOctal(hexadecimal).replaceAll("....(?=.)", "$0  "));
    }


}


