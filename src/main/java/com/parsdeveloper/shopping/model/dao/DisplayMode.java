package com.parsdeveloper.shopping.model.dao;

import java.io.Serializable;

public enum DisplayMode implements Serializable {
    byType,
    inputText,
    date,
    password,
    textarea,
    checkbox,
    selectOne,
    /**
     * all select Ones (<b>selectOne</b>) now are select2 mode as default
     */
    @Deprecated
    select2,
    selectMulti
}
