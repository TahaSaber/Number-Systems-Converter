package com.example.tahasaber.numberingconversion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tahasaber.numberingconversion.R;
import com.example.tahasaber.numberingconversion.binaryRepresentationsNP.BinaryRepresentations;
import com.example.tahasaber.numberingconversion.dialogActivities.EnterDecimalForNegativeRepr;

import static android.app.Activity.RESULT_OK;

public class BinaryRepresentationFragment extends android.app.Fragment {

    private TextView decimalText, signText, onesText, twosText, excessText;
    static final int NEGATIVECODE = 5;
    private BinaryRepresentations binaryRepresentation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_binary_representation, container, false);


        decimalText = (TextView) rootView.findViewById(R.id.decimal_text);
        signText = (TextView) rootView.findViewById(R.id.sign_magnitude);
        onesText = (TextView) rootView.findViewById(R.id.ones_complement);
        twosText = (TextView) rootView.findViewById(R.id.twos_complement);
        excessText = (TextView) rootView.findViewById(R.id.excess_notation);

        if (savedInstanceState != null) {
            decimalText.setText(savedInstanceState.getString("d"));
            signText.setText(savedInstanceState.getString("s"));
            onesText.setText(savedInstanceState.getString("o"));
            twosText.setText(savedInstanceState.getString("t"));
            excessText.setText(savedInstanceState.getString("e"));
            //handle your data on screen rotation
        }

        decimalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDecimalTaker();
            }
        });

        return rootView;
    }

    public void openDecimalTaker() {
        Intent openActivity = new Intent(getActivity(), EnterDecimalForNegativeRepr.class);
        startActivityForResult(openActivity, NEGATIVECODE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("d", decimalText.getText().toString());
        outState.putString("s", signText.getText().toString());
        outState.putString("o", onesText.getText().toString());
        outState.putString("t", twosText.getText().toString());
        outState.putString("e", excessText.getText().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == NEGATIVECODE) {
            if (resultCode == RESULT_OK) {
                String integerVal = data.getStringExtra("integer");
                int bitsNum = Integer.parseInt(data.getStringExtra("bits"));
                setDecimalConverter(integerVal, bitsNum);
               // Toast.makeText(getActivity(), "Converting...", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void setDecimalConverter(String integerVal, int bitsNum) {

        String warning1 = "Sorry! your number don't fit in " + bitsNum + " bits \nMax/Min " +
                "numbers to store is ±(2 to power n-1) -1";
        String warning2 = "Sorry! your number don't fit in " + bitsNum + " bits \nMax number to store is +(2 to power n-1) -1 and min number is -(2 to power n-1).";

        binaryRepresentation = new BinaryRepresentations();
        decimalText.setText(integerVal + "\n(in " + bitsNum + " bits)");
        String signAndMagnitude = binaryRepresentation.signAndMagnitude(integerVal, bitsNum);
        String onesComplement = binaryRepresentation.onesComplement(integerVal, bitsNum);
        String twosComplement = binaryRepresentation.towsComplement(integerVal, bitsNum);
        String excessNotation = binaryRepresentation.excessNotation(integerVal, bitsNum);

        if (!signAndMagnitude.equals(warning1)) {
            signText.setText(signAndMagnitude.replaceAll("....(?=.)", "$0  "));
        } else {
            signText.setText(signAndMagnitude);
        }
        if (!onesComplement.equals(warning1)) {
            onesText.setText(onesComplement.replaceAll("....(?=.)", "$0  "));
        } else {
            onesText.setText(onesComplement);
        }
        if (!twosComplement.equals(warning2)) {
            twosText.setText(twosComplement.replaceAll("....(?=.)", "$0  "));
        } else {
            twosText.setText(twosComplement);
        }
        if (!excessNotation.equals(warning2)) {
            excessText.setText(excessNotation.replaceAll("....(?=.)", "$0  "));
        } else {
            excessText.setText(excessNotation);
        }


    }
}
