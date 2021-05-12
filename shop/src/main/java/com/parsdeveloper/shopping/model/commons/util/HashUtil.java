package com.parsdeveloper.shopping.model.commons.util;

public class HashUtil {

    private HashUtil() {
    }

    //Cantor’s Pairing Function - always makes a unique number per x & y
    public static Long hashXY(Long x, Long y) {
        return (x ^ 2 + 3 * x + 2 * x * y + y + y ^ 2) / 2;
    }

}
