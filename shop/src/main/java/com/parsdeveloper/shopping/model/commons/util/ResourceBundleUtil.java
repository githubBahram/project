package com.parsdeveloper.shopping.model.commons.util;

import org.springframework.context.MessageSource;

import java.util.Locale;

public final class ResourceBundleUtil {
    private static MessageSource messageSource;

    public ResourceBundleUtil() {
    }

    public ResourceBundleUtil(MessageSource messageSource) {
        ResourceBundleUtil.messageSource = messageSource;
    }

    public static String getMessage(String key, Object... args) {
        return getMessage(key, Locale.getDefault(), args);

    }

    public static String getMessage(String key, Locale locale, Object... args) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (Exception e) {
            return key;
        }
    }


    public static MessageSource getMessageSource() {
        return ResourceBundleUtil.messageSource;
    }

    public static void setMessageSource(MessageSource messageSource) {
        ResourceBundleUtil.messageSource = messageSource;
    }
}
