package com.parsdeveloper.shopping.model.entity.shop;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ShoppingCartHistory {

    private Long id;
    private String token;
    @Enumerated(EnumType.STRING)
    private ShoppingCartHistoryAction shoppingCartHistoryAction;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ShoppingCartHistoryAction getShoppingCartHistoryAction() {
        return shoppingCartHistoryAction;
    }

    public void setShoppingCartHistoryAction(ShoppingCartHistoryAction shoppingCartHistoryAction) {
        this.shoppingCartHistoryAction = shoppingCartHistoryAction;
    }
}
