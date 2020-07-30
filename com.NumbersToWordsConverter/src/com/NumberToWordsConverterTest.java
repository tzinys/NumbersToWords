package com;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NumberToWordsConverterTest {
    @Test
    void isNumberParsedToDigitsCorrectly() {
        int[] digits = NumberToWordsConverter.getDigits(123);
        assertNotNull(digits);
        assertEquals(3, digits.length);
        assertEquals(3, digits[0]);
        assertEquals(2, digits[1]);
        assertEquals(1, digits[2]);
    }

    @Test
    void isDigitsGroupedCorrectly() {

        ArrayList<Integer> digitsGrouped = NumberToWordsConverter.getDigitsGroups(54321);
        assertNotNull(digitsGrouped);
        assertEquals(2, digitsGrouped.size());
        assertEquals(321, digitsGrouped.get(0));
        assertEquals(54, digitsGrouped.get(1));
    }

    @Test
    void isGroupedNumbersCorrectlyConvertedToWords() {
        assertAll(
                () -> assertEquals("vienas šimtas dvidešimt trys", NumberToWordsConverter.getGroupedNumberToWords(123)),
                () -> assertEquals("vienas šimtas dvidešimt", NumberToWordsConverter.getGroupedNumberToWords(120)),
                () -> assertEquals("vienas šimtas penkiolika", NumberToWordsConverter.getGroupedNumberToWords(115)),
                () -> assertEquals("du šimtai penkiolika", NumberToWordsConverter.getGroupedNumberToWords(215)),
                () -> assertEquals("penkiolika", NumberToWordsConverter.getGroupedNumberToWords(15)));
    }

    @Test
    void isExponentNameCorrect(){
        assertAll(
                () -> assertEquals("tūkstantis", NumberToWordsConverter.getExponentName(121, 1)),
                () -> assertEquals("milijonų", NumberToWordsConverter.getExponentName(130, 2)),
                () -> assertEquals("milijardų", NumberToWordsConverter.getExponentName(115, 3)),
                () -> assertEquals("trilijardai", NumberToWordsConverter.getExponentName(252, 4)),
                () -> assertEquals("", NumberToWordsConverter.getExponentName(0, 4))
        );
    }

    @Test
    void isNumberToWordsCorrect(){
        assertAll(
                () -> assertEquals("penki tūkstančiai du šimtai", NumberToWordsConverter.getNumberToWords(5200)),
                () -> assertEquals("šeši šimtai trisdešimt penki milijonai penkiasdešimt penki tūkstančiai vienas šimtas devyniasdešimt aštuoni",
                        NumberToWordsConverter.getNumberToWords(635055198))
        );
    }
}