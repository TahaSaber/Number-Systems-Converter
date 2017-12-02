package com.example.tahasaber.numberingconversion.numberConverter;

import android.util.Log;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by TahaSaber on 10/7/2017.
 */

public class FromDecimalToOthers {

    public String decimalToBinary(String decimalVal) {

        StringBuffer result = new StringBuffer();
        String beforePoint = null;
        final BigInteger TWO = new BigInteger("2");
        final BigInteger ZERO = new BigInteger("0");
        final BigDecimal ZERODECIMAL = new BigDecimal("0.0");
        String afterPoint = null;
        BigInteger integralPart;
        int pointIndex = 0;
        int digits = 0;
        char sign = 'd';
        BigDecimal fractionalPart;
        final BigDecimal ONE = new BigDecimal("1");
        final BigDecimal TWODECIMAL = new BigDecimal("2");


        BigDecimal check = new BigDecimal(decimalVal);
        if (decimalVal.equals("") || check.compareTo(ZERODECIMAL) == 0) {
            result.append("0");
        }
        if (decimalVal.charAt(0) == '-' || decimalVal.charAt(0) == '+') {
            sign = decimalVal.charAt(0);
            decimalVal = decimalVal.substring(1, decimalVal.length());

        }


        pointIndex = decimalVal.indexOf(".");
        if (pointIndex >= 0) {
            beforePoint = decimalVal.substring(0, pointIndex);
            afterPoint = decimalVal.substring(pointIndex, decimalVal.length());

            if (beforePoint.equals("") || beforePoint.equals("0")) {
                beforePoint = "0";
            }
            if (afterPoint.equals("") || afterPoint.equals(".")) {
                afterPoint = "0";
            }
        } else {
            beforePoint = decimalVal;
        }
        integralPart = new BigInteger(beforePoint);
        if ((integralPart.compareTo(ZERO) < 0)) {
            integralPart = integralPart.multiply(new BigInteger("-1"));
        }

        while (!(integralPart.compareTo(ZERO) == 0)) {
            if ((integralPart.remainder(TWO).compareTo(ZERO)) == 0) {
                result.append("0");
            } else result.append("1");
            integralPart = integralPart.divide(TWO);
        }


        if (result.toString().equals("")) {
            result.append("0");
        }
        result = result.reverse();
        System.out.println("Result ...................." + result.toString());

        if (pointIndex >= 0 && afterPoint.length() > 1) {

            fractionalPart = new BigDecimal(afterPoint);
            int compare = fractionalPart.compareTo(ZERODECIMAL);
            if (compare == 1) {
                result.append(".");
            }
            while (compare == 1 && digits != 20) {

                fractionalPart = fractionalPart.multiply(TWODECIMAL);
                System.out.println("Fractional part = " + fractionalPart);
                int x = fractionalPart.compareTo(ONE);
                if (x == -1) {
                    result.append("0");
                } else result.append("1");
                String s = fractionalPart.toString();
                int point = s.indexOf(".");
                if (point >= 0) {
                    s = s.substring(point, s.length());
                }
                fractionalPart = new BigDecimal(s);

                compare = fractionalPart.compareTo(ZERODECIMAL);
                digits++;

            }


        }
        if (sign == '-') {
            result = result.insert(0, '-');
        }
        return result.toString();
    }

    public String decimalToOctal(String decimalVal) {

        StringBuffer result = new StringBuffer();
        String beforePoint = null;
        final BigInteger EIGHT = new BigInteger("8");
        final BigInteger ZERO = new BigInteger("0");
        final BigDecimal ZERODECIMAL = new BigDecimal("0.0");
        String afterPoint = null;
        BigInteger integralPart;
        int pointIndex = 0;
        int digits = 0;
        char sign = 'e';
        BigDecimal fractionalPart = null;
        final BigDecimal ONE = new BigDecimal("1");
        final BigDecimal EIGHTDECIMAL = new BigDecimal("8");

        if (decimalVal.equals("") || decimalVal.equals("0")) {
            result.append("0");
        }

        if (decimalVal.charAt(0) == '-' || decimalVal.charAt(0) == '+') {
            sign = decimalVal.charAt(0);
            decimalVal = decimalVal.substring(1, decimalVal.length());

        }


        pointIndex = decimalVal.indexOf(".");
        if (pointIndex >= 0) {
            beforePoint = decimalVal.substring(0, pointIndex);
            afterPoint = decimalVal.substring(pointIndex, decimalVal.length());

            if (beforePoint.equals("") || beforePoint.equals(0)) {
                beforePoint = "0";
            }
            if (afterPoint.equals("") || afterPoint.equals(".")) {
                afterPoint = "0";
            }
        } else {
            beforePoint = decimalVal;
        }

        integralPart = new BigInteger(beforePoint);
        if ((integralPart.compareTo(ZERO) < 0)) {
            integralPart = integralPart.multiply(new BigInteger("-1"));
        }


        while (!(integralPart.compareTo(ZERO) == 0)) {
            if (integralPart.remainder(EIGHT).equals(ZERO)) {
                result.append("0");
            } else {
                BigInteger remainder = integralPart.remainder(EIGHT);
                result.append(remainder.toString());
            }
            integralPart = integralPart.divide(EIGHT);
        }


        if (result.toString().equals("")) {
            result.append("0");
        }
        result = result.reverse();

        // BigInteger val = new BigInteger(afterPoint.substring(1, afterPoint.length()));

        if (pointIndex >= 0 && !afterPoint.equals("") && !afterPoint.equals(".") && !afterPoint.equals("0")) {

            fractionalPart = new BigDecimal(afterPoint);
            int compare = fractionalPart.compareTo(ZERODECIMAL);
            if (compare == 1) {
                result.append(".");
            }

            while (compare == 1 && digits != 20) {

                fractionalPart = fractionalPart.multiply(EIGHTDECIMAL);
                System.out.println("Fractional part = " + fractionalPart);
                int x = fractionalPart.compareTo(ONE);
                if (x == -1) {
                    result.append("0");
                } else {

                    String s = fractionalPart.toString();
                    int point = s.indexOf(".");
                    s = s.substring(0, point);
                    result.append(s);
                }
                String fraction = fractionalPart.toString();
                int point = fraction.indexOf(".");
                if (point >= 0) {
                    fraction = fraction.substring(point, fraction.length());
                }
                fractionalPart = new BigDecimal(fraction);
                digits++;
                compare = fractionalPart.compareTo(ZERODECIMAL);
            }


        }
        if (sign == '-') {
            result = result.insert(0, '-');
        }
        return result.toString();
    }

    public String decimalToHexadecimal(String decimalVal) {

        final HashMap<Integer, String> values = new HashMap<>();
        values.put(10, "A");
        values.put(11, "B");
        values.put(12, "C");
        values.put(13, "D");
        values.put(14, "E");
        values.put(15, "F");

        StringBuffer result = new StringBuffer();
        char sign = 'r';

        if (decimalVal.equals("") || decimalVal.equals("0"))
            result.append("0");
        if (decimalVal.charAt(0) == '-' || decimalVal.charAt(0) == '+') {
            sign = decimalVal.charAt(0);
            decimalVal = decimalVal.substring(1, decimalVal.length());

        }

        String beforePoint = null;
        final BigInteger SIXTEEN = new BigInteger("16");
        final BigInteger ZERO = new BigInteger("0");
        final BigDecimal ZERODECIMAL = new BigDecimal("0.0");
        String afterPoint = null;
        BigInteger integralPart;
        int pointIndex = 0;
        int digits = 0;

        BigDecimal fractionalPart;
        final BigDecimal ONE = new BigDecimal("1");
        final BigDecimal SIXTEENDECIMAL = new BigDecimal("16");
        pointIndex = decimalVal.indexOf(".");


        if (pointIndex >= 0) {
            beforePoint = decimalVal.substring(0, pointIndex);
            afterPoint = decimalVal.substring(pointIndex, decimalVal.length());

            if (beforePoint.equals("")) {
                beforePoint = "0";
            }
            if (afterPoint.equals("") || afterPoint.equals(".")) {
                afterPoint = "0";
            }
        } else {
            beforePoint = decimalVal;
        }

        integralPart = new BigInteger(beforePoint);
        if ((integralPart.compareTo(ZERO) < 0)) {
            integralPart = integralPart.multiply(new BigInteger("-1"));
        }


        while (!(integralPart.compareTo(ZERO) == 0)) {
            if (integralPart.remainder(SIXTEEN).equals(ZERO)) {
                result.append("0");
            } else {

                BigInteger remainder = integralPart.remainder(SIXTEEN);
                if (remainder.intValue() >= 10 && remainder.intValue() < 16) {

                    // System.out.println(integerPart);
                    result.append(values.get(remainder.intValue()));

                } else {
                    result.append(remainder.toString());
                }
            }
            integralPart = integralPart.divide(SIXTEEN);
        }


        if (result.toString().equals("")) {
            result.append("0");
        }

        result = result.reverse();


        if (pointIndex >= 0 && !afterPoint.equals("")) {

            fractionalPart = new BigDecimal(afterPoint);
            int compare = fractionalPart.compareTo(ZERODECIMAL);
            if (compare == 1) {
                result.append(".");
            }
            while (compare == 1 && digits != 20) {

                fractionalPart = fractionalPart.multiply(SIXTEENDECIMAL);
                System.out.println("Fractional part = " + fractionalPart);
                int x = fractionalPart.compareTo(ONE);
                if (x == -1) {
                    result.append("0");
                } else {

                    String s = fractionalPart.toString();
                    int point = s.indexOf(".");
                    if (point >= 0) {
                        s = s.substring(0, point);
                    }
                    if (Integer.parseInt(s) >= 10 && Integer.parseInt(s) < 16) {
                        result.append(values.get(Integer.parseInt(s)));
                    } else {
                        result.append(s);
                    }
                }
                String fraction = fractionalPart.toString();
                int point = fraction.indexOf(".");
                fraction = fraction.substring(point, fraction.length());
                fractionalPart = new BigDecimal(fraction);
                digits++;
                compare = fractionalPart.compareTo(ZERODECIMAL);
            }
        }

        if (sign == '-') {
            result = result.insert(0, '-');
        }

        return result.toString();
    }
}
