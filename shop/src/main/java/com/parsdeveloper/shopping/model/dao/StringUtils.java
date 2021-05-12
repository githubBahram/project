package com.parsdeveloper.shopping.model.dao;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class StringUtils extends org.springframework.util.StringUtils {


    private static final char[] TO_REMOVE_CHARACTERS = {'\u0020',
            '.',
            '-',
            '_',
            ','};


    /**
     * @see EscapeStringUtil#escapeHtml(String)
     */
    public static String escapeHtml(String value) {
        return EscapeStringUtil.escapeHtml(value);
    }


    private static StringBuilder correctInvalidCharactersStringBuilder(String str) {
        if (isEmpty(str))
            return null;
        final StringBuilder stringBuilder = new StringBuilder(str);
        int bound = stringBuilder.length();
        for (int i = 0; i < bound; i++) {
            for (ArabicCharacterVariations cleanWordTypes : ArabicCharacterVariations.values()) {
                for (char ch : cleanWordTypes.getArabicWords()) {
                    if (stringBuilder.charAt(i) == ch) {
                        stringBuilder.setCharAt(i, cleanWordTypes.getFarsiWord());
                        break;
                    }
                }
            }
        }

        return stringBuilder;
    }

    public static String correctInvalidCharacters(String str) {
        if (isEmpty(str))
            return null;
        StringBuilder stringBuilder = correctInvalidCharactersStringBuilder(str);
        if (stringBuilder != null)
            return stringBuilder.toString();
        else return null;
    }

    public static String correctInvalidCharactersAndRemoveSpecialCharacters(String str) {
        String result = null;
        StringBuilder stringBuilder = correctInvalidCharactersStringBuilder(str);
        if (stringBuilder != null) {
            StringBuilder secondStringBuilder = new StringBuilder();
            int bound = stringBuilder.length();
            for (int index = 0; index < bound; index++) {
                boolean foundCharacter = false;
                char currentChar = stringBuilder.charAt(index);
                for (char removeChar : TO_REMOVE_CHARACTERS) {
                    if (removeChar == currentChar) {
                        foundCharacter = true;
                        break;
                    }
                }
                if (!foundCharacter) {
                    secondStringBuilder.append(currentChar);
                }
            }
            result = secondStringBuilder.toString();
        }
        return result;
    }

    /**
     * This Enum demonstrates the character variations in arabic and farsi
     * if fact which character in persian has differences in arabic
     *
     * @author h.zare
     */
    private enum ArabicCharacterVariations {
        YEH('\u06CC', new char[]{'\u064A', '\ufef1', '\ufeef'}),
        KAAF('\u06a9', new char[]{'\u0643', '\ufb8e'});

        char farsiWord;
        char[] arabicWords;

        ArabicCharacterVariations(char farsiWord, char[] arabicWords) {
            this.farsiWord = farsiWord;
            this.arabicWords = arabicWords;
        }

        public char getFarsiWord() {
            return farsiWord;
        }

        public char[] getArabicWords() {
            return arabicWords;
        }
    }

    public static final class EscapeStringUtil {

//        private static final Logger LOGGER = Logger.getLogger(EscapeStringUtil.class);

        private static char[][] specialCharactersRepresentation = new char['>' + 1][];

        static {
            specialCharactersRepresentation['&'] = "&amp;".toCharArray();
            specialCharactersRepresentation['<'] = "&lt;".toCharArray();
            specialCharactersRepresentation['>'] = "&gt;".toCharArray();
            specialCharactersRepresentation['"'] = "&#034;".toCharArray();
        }

        public static String escapeHtml(String value) {
            if (value != null) {
                StringWriter writer = new StringWriter(value.length());
                try {
                    writeEscapedHtml(value.toCharArray(), value.length(), writer);
                    value = writer.toString();
                } catch (IOException e) {
//                    LOGGER.error(e.getMessage(), e);
                    //DO NOTHING
                }
            }
            return value;
        }

        public static void writeEscapedHtml(char[] buffer, int length, Writer w) throws IOException {

            int start = 0;
            for (int i = 0; i < length; i++) {
                char c = buffer[i];
                if (c <= '>') {
                    char[] escaped = specialCharactersRepresentation[c];
                    if (escaped != null) {
                        // add unescaped portion
                        if (start < i) {
                            w.write(buffer, start, i - start);
                        }
                        // add escaped xml
                        w.write(escaped);
                        start = i + 1;
                    }
                }
            }
            // add rest of unescaped portion
            if (start < length) {
                w.write(buffer, start, length - start);
            }
        }
    }

}
