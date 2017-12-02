package com.example.tahasaber.numberingconversion.numberConverter;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by TahaSaber on 10/10/2017.
 */

public class FromHexaDecimalToOthers {

    public String hexadecimalToDecimal(String hexaVal) {

        HashMap<String, String> values = new HashMap<>();
        values.put("A", "10");
        values.put("B", "11");
        values.put("C", "12");
        values.put("D", "13");
        values.put("E", "14");
        values.put("F", "15");
        values.put("a", "10");
        values.put("b", "11");
        values.put("c", "12");
        values.put("d", "13");
        values.put("e", "14");
        values.put("f", "15");


        // remove negative sign from binary value
        int neg = 0;

        if (hexaVal.charAt(0) == '-') {
            hexaVal = hexaVal.substring(1, hexaVal.length());
            neg = 1;
        }else if(hexaVal.charAt(0) =='+'){
            hexaVal = hexaVal.substring(1, hexaVal.length());
        }
        BigDecimal result = new BigDecimal("0.0");
        BigDecimal SIXTEEN = new BigDecimal("16.0");
        String beforePoint = null;
        String afterPoint = null;
        int pointIndex = 0;
        int stringLengthIterator = 0;
        int stringLength = 0;

        pointIndex = hexaVal.indexOf(".");
        if (pointIndex >= 0) {
            beforePoint = hexaVal.substring(0, pointIndex);
            afterPoint = hexaVal.substring(pointIndex + 1, hexaVal.length());
        } else {
            beforePoint = hexaVal;
        }
        stringLength = beforePoint.length() - 1;

        while (stringLengthIterator < beforePoint.length()) {

            String oneChar = beforePoint.charAt(stringLengthIterator) + "";
            if ((beforePoint.charAt(stringLengthIterator) >= 65 && beforePoint.charAt(stringLengthIterator) <= 70) || (beforePoint.charAt(stringLengthIterator) >= 97 && beforePoint.charAt(stringLengthIterator) <= 102)) {
                oneChar = values.get(oneChar);
            }
            BigDecimal temp = new BigDecimal(oneChar);
            result = result.add(temp.multiply(SIXTEEN.pow(stringLength)));

            stringLength--;
            stringLengthIterator++;
        }

        stringLengthIterator = 0;
        stringLength = 1;

        if (pointIndex >= 0 && !afterPoint.equals("")) {

            while (stringLengthIterator < afterPoint.length()) {

                String oneChar = afterPoint.charAt(stringLengthIterator) + "";
                if ((afterPoint.charAt(stringLengthIterator) >= 65 && afterPoint.charAt(stringLengthIterator) <= 70) || (afterPoint.charAt(stringLengthIterator) >= 97 && afterPoint.charAt(stringLengthIterator) <= 102)) {
                    oneChar = values.get(oneChar);
                }
                BigDecimal temp = new BigDecimal(oneChar);
                result = result.add(temp.divide(SIXTEEN.pow(stringLength)));

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

    public String hexadecimalToBinary(String hexaVal) {

        return new FromDecimalToOthers().decimalToBinary(hexadecimalToDecimal(hexaVal).toString());
    }

    public String hexadecimalToOctal(String hexaVal) {

        return new FromDecimalToOthers().decimalToOctal(hexadecimalToDecimal(hexaVal).toString());
    }
}
