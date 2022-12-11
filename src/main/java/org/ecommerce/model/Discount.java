package org.ecommerce.model;

import javax.persistence.*;

@Entity
@Table
public class Discount {
    @Column
    @Id
    private int id;

    @Column(name = "discounted_product_id")
    private String discountedProductId;

    @Column
    private float percentage;
    @Column(name = "quantity_needed_to_avail_discount")
    private int quantity;
    @Column(name = "discounted_amount")
    private float discountedAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountedProductId() {
        return discountedProductId;
    }

    public void setDiscountedProductId(String productId) {
        this.discountedProductId = productId;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(float discountedAmount) {
        this.discountedAmount = discountedAmount;
    }
}
