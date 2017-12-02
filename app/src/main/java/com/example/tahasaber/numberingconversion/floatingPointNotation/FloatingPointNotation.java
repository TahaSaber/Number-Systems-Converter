package com.example.tahasaber.numberingconversion.floatingPointNotation;

import android.widget.Toast;

import com.example.tahasaber.numberingconversion.binaryRepresentationsNP.BinaryRepresentations;
import com.example.tahasaber.numberingconversion.fragments.BinaryRepresentationFragment;
import com.example.tahasaber.numberingconversion.numberConverter.FromBinaryToOthers;
import com.example.tahasaber.numberingconversion.numberConverter.FromDecimalToOthers;

import java.util.HashMap;

/**
 * Created by TahaSaber on 10/25/2017.
 */

public class FloatingPointNotation {

    public String floatingPointNotationEncoding(double decimalVal) {

        if (decimalVal == 0) {
            return "0  000  0000";
        }
        int check = 0, oneIndex = 0, pointIndex = 0;
        String originalMantissa = "", exponentBinary = "";
        StringBuffer result = new StringBuffer();
        String mantissa = "";
        int exponent = 0;
        BinaryRepresentations binaryRepresentations = new BinaryRepresentations();
        String warning = "Sorry! your number don't fit in 3 bits \nMax number to store is +(2 to power n-1) -1 and min number is -(2 to power n-1).";


        if (decimalVal < 0) {
            result.append("1");
            decimalVal *= -1;
        } else result.append("0");


        originalMantissa = new FromDecimalToOthers().decimalToBinary(String.valueOf(decimalVal));
        oneIndex = originalMantissa.indexOf("1");
        pointIndex = originalMantissa.indexOf(".");
        int point = pointIndex;

        if (pointIndex == -1) {
            pointIndex = originalMantissa.length();
        }

        if ((oneIndex - pointIndex) == 1) {
            exponent = 0;
            exponentBinary = binaryRepresentations.excessNotation(Integer.toString(exponent), 3);
            if (exponentBinary.equals(warning)) {
                return warning;
            }
        } else if ((oneIndex - pointIndex) > 1) {
            exponent = ((oneIndex - pointIndex)-1) * -1;
            exponentBinary = binaryRepresentations.excessNotation(Integer.toString(exponent), 3);
            if (exponentBinary.equals(warning)) {
                return warning;
            }
        } else if ((oneIndex - pointIndex) < 1) {
            exponent = pointIndex - oneIndex;
            exponentBinary = binaryRepresentations.excessNotation(Integer.toString(exponent), 3);
            if (exponentBinary.equals(warning)) {
                return warning;
            }
        }

        originalMantissa = originalMantissa.substring(oneIndex, originalMantissa.length());

        if (pointIndex > oneIndex && point >= 0) {
            StringBuilder builder = new StringBuilder(originalMantissa);
            builder.deleteCharAt(originalMantissa.indexOf('.'));
            originalMantissa = builder.toString();

        }


        //  String binary = new FromDecimalToOthers().decimalToBinary(String.valueOf(decimalVal));
        //  String exponentBinary = new BinaryRepresentations().excessNotation(Integer.toString(exponent), 3);


        if (originalMantissa.length() > 4) {

            mantissa = originalMantissa;
            originalMantissa = originalMantissa.substring(0, 4);
            check = 1;
        } else if (originalMantissa.length() < 4) {
            for (int i = originalMantissa.length(); i < 4; i++) {
                originalMantissa += "0";
            }


        }
        result.append("  " + exponentBinary);
        result.append("  " + originalMantissa);
        if (check == 1) {
            return result.toString() + "\nTruncation Error Occurred the original mantissa\n(" + mantissa + ")";
        }
        return result.toString();

    }

    public String floatingPointNotationDecoding(String binaryVal) {

        char leftMostBit = binaryVal.charAt(0);
        String exponent = binaryVal.substring(1, 4);
        String mantissa = binaryVal.substring(4, binaryVal.length());
        String result = null;

        HashMap<String, Integer> excessNotationVals = new HashMap<>();

        excessNotationVals.put("111", 3);
        excessNotationVals.put("110", 2);
        excessNotationVals.put("101", 1);
        excessNotationVals.put("100", 0);
        excessNotationVals.put("011", -1);
        excessNotationVals.put("010", -2);
        excessNotationVals.put("001", -3);
        excessNotationVals.put("000", -4);

        int exponentVal = excessNotationVals.get(exponent);

        if (exponentVal > 0) {
            StringBuilder stringBuilder = new StringBuilder(mantissa);
            stringBuilder.insert(exponentVal, ".");
            mantissa = stringBuilder.toString();
        } else if (exponentVal < 0) {
            exponentVal *= -1;
            for (int i = 0; i < exponentVal; i++) {
                mantissa = "0" + mantissa;
            }
            mantissa = "." + mantissa;
        } else {
            mantissa = "." + mantissa;
        }


        result = new FromBinaryToOthers().binaryToDecimal(mantissa).toString();

        if (leftMostBit == '1') {
            result = "-" + result;
        }

        return result;
    }
}
