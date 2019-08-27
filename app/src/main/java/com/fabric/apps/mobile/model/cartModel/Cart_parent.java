package com.fabric.apps.mobile.model.cartModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart_parent {
    @SerializedName("data")
    @Expose
    private List<Cart_data> cart_data;

    @SerializedName("message")
    @Expose
    private String message;

    public List<Cart_data> getCart_data() {
        return cart_data;
    }

    public void setCart_data(List<Cart_data> cart_data) {
        this.cart_data = cart_data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
