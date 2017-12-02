package com.example.tahasaber.numberingconversion.binaryRepresentationsNP;

import com.example.tahasaber.numberingconversion.numberConverter.FromDecimalToOthers;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by TahaSaber on 10/25/2017.
 */

public class BinaryRepresentations {

    public String signAndMagnitude(String decimalVal, int bitsNumber) {



        char firstBit = decimalVal.charAt(0);
        FromDecimalToOthers decimalToConvert = new FromDecimalToOthers();
        if (decimalVal.charAt(0) == '-') {
            decimalVal = decimalVal.substring(1, decimalVal.length());
        }
        BigInteger val = new BigInteger(decimalVal);
        BigInteger two = new BigInteger("2");
        two = two.pow(bitsNumber - 1);
        two = two.subtract(new BigInteger("1"));
        System.out.println("Sign = " + two);

        String binary = decimalToConvert.decimalToBinary(decimalVal);

       /* if (Integer.parseInt(decimalVal) > (Math.pow(2, bitsNumber - 1) - 1)) {
            String warning = "Sorry! your number don't fit in " + bitsNumber + " bits \nMax/Min " +
                    "numbers to store is ±(2 to power n-1) -1";
            return warning;
        }*/

        if (val.compareTo(two) == 1) {
            String warning = "Sorry! your number don't fit in " + bitsNumber + " bits \nMax/Min " +
                    "numbers to store is ±(2 to power n-1) -1";
            return warning;
        } else {
            int binaryLength = binary.length();
            for (int i = 0; i < (bitsNumber - binaryLength) - 1; i++) {
                binary = "0" + binary;
            }
            if (firstBit == '-')
                binary = "1" + binary;
            else binary = "0" + binary;
            return binary;
        }


    }

    public String onesComplement(String decimalVal, int bitsNumber) {



        char firstBit = decimalVal.charAt(0);
        FromDecimalToOthers decimalToConvert = new FromDecimalToOthers();
        if (firstBit == '-') {
            decimalVal = decimalVal.substring(1, decimalVal.length());
        }

        BigInteger val = new BigInteger(decimalVal);
        BigInteger two = new BigInteger("2");
        two = two.pow(bitsNumber - 1);
        two = two.subtract(new BigInteger("1"));
        System.out.println(two);

        String binary = decimalToConvert.decimalToBinary(decimalVal);

        if (val.compareTo(two) == 1) {
            String warning = "Sorry! your number don't fit in " + bitsNumber + " bits \nMax/Min " +
                    "numbers to store is ±(2 to power n-1) -1";
            return warning;
        } else {
            int binaryLength = binary.length();
            for (int i = 0; i < bitsNumber - binaryLength; i++) {
                binary = "0" + binary;
            }
            if (firstBit == '-') {
                StringBuilder result = new StringBuilder(binary);

                for (int i = 0; i < binary.length(); i++) {
                    if (binary.charAt(i) == '0') {
                        result.setCharAt(i, '1');
                    } else result.setCharAt(i, '0');
                }
                binary = result.toString();
            }
            return binary;
        }

    }

    public String towsComplement(String decimalVal, int bitsNumber) {



        char firstBit = decimalVal.charAt(0);
        String warningOnesComplement = "Sorry! your number don't fit in " + bitsNumber + " bits \nMax/Min " +
                "numbers to store is ±(2 to power n-1) -1";
        String warning = "Sorry! your number don't fit in " + bitsNumber + " bits \nMax number to store is +(2 to power n-1) -1 and min number is -(2 to power n-1).";
        FromDecimalToOthers decimalToConvert = new FromDecimalToOthers();
        String binary;
        int length = 0;
        if (firstBit == '-') {
            decimalVal = decimalVal.substring(1, decimalVal.length());
            BigInteger val = new BigInteger(decimalVal);
            BigInteger two = new BigInteger("2");
            two = two.pow(bitsNumber - 1);
            System.out.println(two);
            if (val.compareTo(two) == 1) {
                return warning;
            } else {
                binary = decimalToConvert.decimalToBinary(decimalVal);
                StringBuilder result = new StringBuilder(binary);
                length = binary.length();
                for (int i = 0; i < length; i++) {
                    if (binary.charAt(i) == '0') {
                        result.setCharAt(i, '1');
                    } else result.setCharAt(i, '0');
                }
                binary = result.toString();
                for (int i = 0; i < (bitsNumber - length); i++) {
                    binary = "1" + binary;
                }
                StringBuffer finalResult = new StringBuffer();
                length = binary.length();
                char remainder = '1';
                for (int i = length - 1; i >= 0; i--) {
                    if (remainder == '1' && binary.charAt(i) == '1') {
                        finalResult.append("0");
                    } else if (remainder == '1' && binary.charAt(i) == '0') {
                        finalResult.append("1");
                        remainder = '0';
                    } else finalResult.append(binary.charAt(i));
                }

                finalResult.reverse();
                return finalResult.toString();
            }

        } else {
            if (onesComplement(decimalVal, bitsNumber).equals(warningOnesComplement)) {
                return warning;
            } else {
                return onesComplement(decimalVal, bitsNumber);
            }
        }
    }

    public String excessNotation(String decimalVal, int bitsNumber) {

        String warning = "Sorry! your number don't fit in " + bitsNumber + " bits \nMax number to store is +(2 to power n-1) -1 and min number is -(2 to power n-1).";

        if (!towsComplement(decimalVal, bitsNumber).equals(warning)) {
            StringBuilder result = new StringBuilder(towsComplement(decimalVal, bitsNumber));
            if (result.charAt(0) == '0') {
                result.setCharAt(0, '1');
            } else result.setCharAt(0, '0');

            return result.toString();
        }

        return warning;
    }
}
