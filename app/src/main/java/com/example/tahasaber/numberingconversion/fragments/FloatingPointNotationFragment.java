package com.example.tahasaber.numberingconversion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tahasaber.numberingconversion.R;
import com.example.tahasaber.numberingconversion.dialogActivities.EnterBinaryToDecode;
import com.example.tahasaber.numberingconversion.dialogActivities.EnterRealToEncode;
import com.example.tahasaber.numberingconversion.floatingPointNotation.FloatingPointNotation;
import com.example.tahasaber.numberingconversion.floatingPointNotation.IEEE754SinglePrecision;

import static android.app.Activity.RESULT_OK;


public class FloatingPointNotationFragment extends android.app.Fragment {


    private TextView encodeText, decodeText, ieee;
    private static final int ENCODE = 22;
    private static final int DECODE = 33;
    private FloatingPointNotation floatingPointNotation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_floating_point_notation, container, false);

        encodeText = (TextView) rootView.findViewById(R.id.encode);
        ieee = (TextView) rootView.findViewById(R.id.ieee);
        decodeText = (TextView) rootView.findViewById(R.id.decode);

        if (savedInstanceState != null) {
            encodeText.setText(savedInstanceState.getString("e"));
            decodeText.setText(savedInstanceState.getString("d"));
            ieee.setText(savedInstanceState.getString("i"));
        }
        floatingPointNotation = new FloatingPointNotation();
        encodeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeRealToEncode();
            }
        });

        decodeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeBinaryToDecode();
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("e", encodeText.getText().toString());
        outState.putString("d", decodeText.getText().toString());
        outState.putString("i", ieee.getText().toString());

    }


    public void writeRealToEncode() {

        Intent openEncode = new Intent(getActivity(), EnterRealToEncode.class);
        startActivityForResult(openEncode, ENCODE);

    }

    public void writeBinaryToDecode() {

        Intent openDecode = new Intent(getActivity(), EnterBinaryToDecode.class);
        startActivityForResult(openDecode, DECODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ENCODE) {
            if (resultCode == RESULT_OK) {
                String real = data.getStringExtra("real");
                setEncodeText(real);
                setIEEE(real);
            }
        } else if (requestCode == DECODE) {
            if (resultCode == RESULT_OK) {
                String binaryVal = data.getStringExtra("binary");
                setDecodeText(binaryVal);
            }
        }
    }

    public void setEncodeText(String realVal) {

        encodeText.setText(realVal);
        String result = floatingPointNotation.floatingPointNotationEncoding(Double.parseDouble(realVal));
        decodeText.setText(result);


    }

    public void setDecodeText(String binaryVal) {
        decodeText.setText(binaryVal);
        String real = floatingPointNotation.floatingPointNotationDecoding(binaryVal);
        encodeText.setText(real);
        setIEEE(real);
    }

    public void setIEEE(String realVal) {
        String result = (new IEEE754SinglePrecision()).convertFromDecimalToIEEE754SinglePrecision(realVal);
        ieee.setText(result);
    }
}
