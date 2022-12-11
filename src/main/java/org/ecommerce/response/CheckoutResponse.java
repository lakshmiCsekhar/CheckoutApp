package org.ecommerce.response;

public class CheckoutResponse {
    private float price;
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public CheckoutResponse() {
    }
    public CheckoutResponse(float price) {
        this.price = price;
    }
}
