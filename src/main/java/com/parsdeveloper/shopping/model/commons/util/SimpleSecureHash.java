package com.parsdeveloper.shopping.model.commons.util;

import java.util.Optional;

public interface SimpleSecureHash {

    String encode(Long x, String userName);

    Optional<Long> decode(String x, String userName);

}
