package com.example.tahasaber.numberingconversion.numberConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by TahaSaber on 10/7/2017.
 */

public class FromBinaryToOthers {

    public String binaryToDecimal(String binaryVal) {

        // remove negative sign from binary value
        int neg = 0;

        if(binaryVal.charAt(0) == '-'){
            binaryVal = binaryVal.substring(1, binaryVal.length());
            neg = 1;
        }else if(binaryVal.charAt(0) =='+'){
            binaryVal = binaryVal.substring(1, binaryVal.length());
        }

        BigDecimal result = new BigDecimal("0.0");
        BigDecimal TWO = new BigDecimal("2.0");
        final BigDecimal ONE = new BigDecimal("1");
        String beforePoint = null;
        String afterPoint = null;
        int pointIndex = 0;
        int stringLengthIterator = 0;
        int stringLength = 0;

        pointIndex = binaryVal.indexOf(".");

        if (pointIndex >= 0) {
            beforePoint = binaryVal.substring(0, pointIndex);
            afterPoint = binaryVal.substring(pointIndex + 1, binaryVal.length());
        } else {
            beforePoint = binaryVal;
        }
        stringLength = beforePoint.length() - 1;



        while (stringLengthIterator < beforePoint.length()) {
            if (beforePoint.charAt(stringLengthIterator) == '1') {
                result = result.add(TWO.pow(stringLength));
            }
            stringLength--;
            stringLengthIterator++;
        }

        // reinitializing indexes
        stringLengthIterator = 0;
        stringLength = 1;

        // handling fractional part
        if (pointIndex >= 0 && !afterPoint.equals("")) {

            while (stringLengthIterator < afterPoint.length()) {
                if (afterPoint.charAt(stringLengthIterator) == '1') {

                    result = result.add(ONE.divide(TWO.pow(stringLength)));
                }
                stringLength++;
                stringLengthIterator++;
            }
        }

        if (neg == 1) {
            result = result.multiply(new BigDecimal("-1"));
        }

        if (pointIndex < 0) {
            String r = result.toString().substring(0, result.toString().indexOf('.'));
            return r;
        }


        return result.toString();
    }

    public String binaryToOctal(String binaryVal) {

        return new FromDecimalToOthers().decimalToOctal(binaryToDecimal(binaryVal).toString());

    }

    public String binaryToHexadecimal(String binaryVal) {

        return new FromDecimalToOthers().decimalToHexadecimal(binaryToDecimal(binaryVal).toString());

    }
}
