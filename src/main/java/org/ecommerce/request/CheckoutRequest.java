package org.ecommerce.request;

import java.util.List;

public class CheckoutRequest {

    private List<String> productsToCheckout;

    public List<String> getProductsToCheckout() {
        return productsToCheckout;
    }

    public void setProductsToCheckout(List<String> productsToCheckout) {
        this.productsToCheckout = productsToCheckout;
    }
}
