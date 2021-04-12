package com.parsdeveloper.shopping.model.commons.util.impl;

import com.parsdeveloper.shopping.model.commons.util.SimpleSecureHash;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import java.util.Optional;

//@Component
public class DefaultSimpleSecureHash implements SimpleSecureHash {

    private static final char MAGIC_CHAR = '\\';

    PasswordEncoder passwordEncoder;

    public String encode(Long input, String userName) {
        Assert.notNull(input, "input must not null");
        String inputString = input.toString();
        String raw = inputString + userName;
        String encode = passwordEncoder.encode(raw);
        byte[] bs64 = Base64Utils.encode(inputString.getBytes());
        return new String(bs64) + MAGIC_CHAR + encode;
    }

    public Optional<Long> decode(String output, String userName) {
        int magicLocation = output.indexOf(MAGIC_CHAR);
        String bs64 = output.substring(0, magicLocation);
        String originalXString = new String(Base64Utils.decode(bs64.getBytes()));
        String hashed = output.substring(magicLocation + 1);
        String raw = originalXString + userName;
        return passwordEncoder.matches(raw, hashed) ? Optional.of(Long.valueOf(originalXString)) : Optional.empty();
    }

}
