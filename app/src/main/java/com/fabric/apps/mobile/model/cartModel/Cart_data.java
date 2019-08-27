package com.fabric.apps.mobile.model.cartModel;

import com.fabric.apps.mobile.model.costumerModel.Costumer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart_data {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("productInboundId")
    @Expose
    private Integer productInboundId;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("length_per_meter")
    @Expose
    private Integer lengthPerMeter;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("ProductInbound")
//    @Expose
//    private Product_catalog product_catalog;
//    @SerializedName("CustomerSignup")
    @Expose
    private Costumer costumer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductInboundId() {
        return productInboundId;
    }

    public void setProductInboundId(Integer productInboundId) {
        this.productInboundId = productInboundId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getLengthPerMeter() {
        return lengthPerMeter;
    }

    public void setLengthPerMeter(Integer lengthPerMeter) {
        this.lengthPerMeter = lengthPerMeter;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

//    public Product_catalog getProduct_catalog() {
//        return product_catalog;
//    }
//
//    public void setProduct_catalog(Product_catalog product_catalog) {
//        this.product_catalog = product_catalog;
//    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }
}
