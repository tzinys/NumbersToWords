package com;

import java.util.ArrayList;

public class NumberToWordsConverter {
    private static String[] digitsInWords = {"nulis", "vienas", "du", "trys", "keturi", "penki", "šeši", "septyni", "aštuoni", "devyni"};
    private static String[] teensInWords = {"dešimt", "vienuolika", "dvylika", "trylika", "keturiolika", "penkiolika", "šešiolika", "septiniolika", "aštuoniolika", "devyniolika"};
    private static String[] tenthsInWords = {"", "dešimt", "dvidešimt", "trisdešimt", "keturesdešimt", "penkiasdešimt", "šešiasdešimt", "septyniasdešimt", "aštuoniasdešimt", "devyniasdešimt"};
    private static String[] exponentInWords1 = {"", "tūkstantis", "milijonas", "milijardas", "trilijardas"};
    private static String[] exponentInWords2 = {"", "tūkstančiai", "milijonai", "milijardai", "trilijardai"};
    private static String[] exponentInWords3 = {"", "tūkstančių", "milijonų", "milijardų", "trilijardų"};

    public static String getNumberToWords(int number) {
        ArrayList<Integer> groups = getDigitsGroups(number);
        StringBuilder words = new StringBuilder();
        for (int i = groups.size() - 1; i >= 0; i--) {
            words.append(" ");
            words.append(getGroupedNumberToWords(groups.get(i)));
            words.append(" ");
            words.append(getExponentName(groups.get(i), i));
        }
        return words.toString().trim();
    }

    public static String getExponentName(int number, int exponent) {
        if (number == 0) {
            return "";
        }

        int[] digits = getDigits(number);
        if (digits[0] == 1 && digits[1] != 1) {
            return exponentInWords1[exponent];
        }

        if (digits[1] == 1 || digits[0] == 0) {
            return exponentInWords3[exponent];
        }

        return exponentInWords2[exponent];
    }

    public static int[] getDigits(int number) {
        int[] digits = new int[3];
        int i = 0;
        while (number != 0) {
            digits[i] = number % 10;
            number /= 10;
            i++;
        }
        return digits;
    }

    public static ArrayList<Integer> getDigitsGroups(int number) {
        ArrayList<Integer> groups = new ArrayList<>();
        while (number != 0) {
            groups.add(number % 1000);
            number /= 1000;
        }
        return groups;
    }

    public static String getGroupedNumberToWords(int number) {
        StringBuilder words = new StringBuilder();
        int[] digits = getDigits(number);
        if (digits[2] > 0) {
            words.append(digitsInWords[digits[2]]);
            words.append(digits[2] == 1 ? " šimtas" : " šimtai");
        }

        if (digits[1] == 0) {
            if (digits[0] != 0) {
                words.append(" ");
                words.append(digitsInWords[digits[0]]);
            }
        } else if (digits[1] == 1) {
            words.append(" ");
            words.append(teensInWords[digits[0]]);
        } else {
            words.append(" ");
            words.append(tenthsInWords[digits[1]]);
            if (digits[0] != 0) {
                words.append(" ");
                words.append(digitsInWords[digits[0]]);
            }
        }
        return words.toString().trim();
    }
}
