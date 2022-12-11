package org.ecommerce.model;

import javax.persistence.*;

@Entity
@Table
public class Product {
    @Column
    @Id
    private int id;
    @Column(name = "product_id")
    private String productId;
    @Column
    private String name;
    @Column(name = "unitprice")
    private float unitPrice;
    @Column(name = "is_discount_applicable")
    private boolean isDiscountApplicable;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "discounted_product_id")
    private Discount discount;

    public int getId() {
        return id;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discounts) {
        this.discount = discounts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isDiscountApplicable() {
        return isDiscountApplicable;
    }

    public void setDiscountApplicable(boolean discountApplicable) {
        isDiscountApplicable = discountApplicable;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
