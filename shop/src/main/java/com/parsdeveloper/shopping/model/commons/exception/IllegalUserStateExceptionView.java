package com.parsdeveloper.shopping.model.commons.exception;

import java.util.Map;

public class IllegalUserStateExceptionView extends IllegalStateException {

    private Map<String, Object> params;

    public IllegalUserStateExceptionView() {
    }

    public IllegalUserStateExceptionView(Map<String, Object> params) {
    }

    public IllegalUserStateExceptionView(String s) {
        super(s);
    }

    public IllegalUserStateExceptionView(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalUserStateExceptionView(Throwable cause) {
        super(cause);
    }
}
