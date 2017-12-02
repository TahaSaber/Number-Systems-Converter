package com.example.tahasaber.numberingconversion.numberConverter;

import java.math.BigDecimal;

/**
 * Created by TahaSaber on 10/10/2017.
 */

public class FromOctalToOthers {

    public String octalToDecimal(String octalVal) {

        // remove negative sign from binary value
        int neg = 0;

        if (octalVal.charAt(0) == '-') {
            octalVal = octalVal.substring(1, octalVal.length());
            neg = 1;
        } else if (octalVal.charAt(0) == '+') {
            octalVal = octalVal.substring(1, octalVal.length());
        }

        BigDecimal result = new BigDecimal("0.0");
        BigDecimal EIGHT = new BigDecimal("8.0");
        BigDecimal ZERo = new BigDecimal("0.0");
        //final BigDecimal ONE = new BigDecimal("1");
        String beforePoint = null;
        String afterPoint = null;
        int pointIndex = 0;
        int stringLengthIterator = 0;
        int stringLength = 0;

        pointIndex = octalVal.indexOf(".");
        if (pointIndex >= 0) {
            beforePoint = octalVal.substring(0, pointIndex);
            afterPoint = octalVal.substring(pointIndex + 1, octalVal.length());
        } else {
            beforePoint = octalVal;
        }
        stringLength = beforePoint.length() - 1;

        while (stringLengthIterator < beforePoint.length()) {

            BigDecimal temp = new BigDecimal(beforePoint.charAt(stringLengthIterator) + "");
            result = result.add(temp.multiply(EIGHT.pow(stringLength)));

            stringLength--;
            stringLengthIterator++;
        }

        stringLengthIterator = 0;
        stringLength = 1;

        if (pointIndex >= 0 && !afterPoint.equals("")) {

            while (stringLengthIterator < afterPoint.length()) {

                BigDecimal temp = new BigDecimal(afterPoint.charAt(stringLengthIterator) + "");
                result = result.add(temp.divide(EIGHT.pow(stringLength)));

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

    public String octalToBinary(String octalVal) {

        return new FromDecimalToOthers().decimalToBinary(octalToDecimal(octalVal).toString());
    }

    public String octalToHexadecimal(String octalVal) {

        return new FromDecimalToOthers().decimalToHexadecimal(octalToDecimal(octalVal).toString());
    }
}
