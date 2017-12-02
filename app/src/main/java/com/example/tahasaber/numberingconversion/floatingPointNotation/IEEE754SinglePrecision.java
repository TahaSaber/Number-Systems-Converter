package com.example.tahasaber.numberingconversion.floatingPointNotation;

import com.example.tahasaber.numberingconversion.numberConverter.FromDecimalToOthers;

/**
 * Created by TahaSaber on 11/4/2017.
 */

public class IEEE754SinglePrecision {

    public String convertFromDecimalToIEEE754SinglePrecision(String decimalVal) {

        // result is the final result that will be returned.
        StringBuffer result = new StringBuffer();
        // the power is the number of bits that decimal point will shift to left, and point is the index of point in the binary representation of decimal input.
        int power = 0, point = 0, one = 0;
        // the exponent is the second part in the final result after sign bit, it initialized by 127 then will be added to power.
        int exponentInt = 127;
        // this is the binary representation of exponent int.
        String exponentBinary = "";

        // the binary representation for the decimal input number
        String binary = (new FromDecimalToOthers()).decimalToBinary(decimalVal);
        // check if input value is zero then the returned result will be 32 zeros.
        if (binary.equals("0")) {
            result.append("0 00000000 00000000000000000000000");
            return result.toString();
        }
        // check the sign of the number, if negative will remove sign from binary string and result append one as an sign bit, else result append sign bit zero.
        char sign = 'f';
        if (binary.charAt(0) == '-' || binary.charAt(0) == '+') {
            sign = binary.charAt(0);
            binary = binary.substring(1, binary.length());

        }
        if (sign == '-') {
            result.append("1");
        } else {
            result.append("0");
        }

        // get the index of the decimal point and first one in converter decimal input.
        point = binary.indexOf('.');
        one = binary.indexOf('1');

        if(one == -1){
            result.append(" 00000000 00000000000000000000000");
            return result.toString();
        }
        // if point index is less than zero, then the input is integer value, and the shifted bits = the binary length - 1, else shifted bits = the integer part - 1.
        if (point >= 0) {
            if ((point - one) > 0) {
                power = (point - one) - 1;
            } else {
                power = point - one;
            }
        } else {
            power = binary.length() - 1;
        }


        // exponentInt = 172 + shifted bits.
        exponentInt += power;

        // binary representation for exponent int.
        exponentBinary = (new FromDecimalToOthers()).decimalToBinary(String.valueOf(exponentInt));

        // check if exponent binary representation is more then 8, then we can't convert to IEEE754 single precision floating point format.
        if (exponentBinary.length() > 8) {
            String reject = "Maximum input length exceeded";
            return reject;
        }

        if (exponentBinary.length() < 8) {
            for (int i = exponentBinary.length(); i < 8; i++) {
                exponentBinary = "0" + exponentBinary;
            }
        }

        // else result will append the exponent part in binary representation.
        result.append("  " + exponentBinary);

        // the binary part will be the same except the first bit, the most left 1.
        binary = binary.substring(one + 1, binary.length());
        StringBuilder builder = new StringBuilder(binary);

        // remove the decimal point from old index.
        if (binary.indexOf('.') >= 0) {
            binary = builder.deleteCharAt(binary.indexOf('.')).toString();
        }
        // check if mantissa length is less than 23, then mantissa's length will be increased by zeros else mantissa will be the first 23 bits in the binary string.
        if (binary.length() < 23) {
            for (int i = binary.length(); i < 23; i++) {
                binary += "0";
            }
        } else if (binary.length() > 23) {
            binary = binary.substring(0, 23);
        }

        // append mantissa part to result.
        result.append("  " + binary);


        // return the final result.
        return result.toString();
    }
}
