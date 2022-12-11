package org.ecommerce.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.ecommerce.model.Discount;
import org.ecommerce.model.Product;
import org.ecommerce.repository.DiscountRepository;
import org.ecommerce.repository.ProductRepository;
import org.ecommerce.request.CheckoutRequest;
import org.ecommerce.response.CheckoutResponse;
import org.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;


    public CheckoutResponse doCheckout(CheckoutRequest request) {
        float totalPrice = 0;
        if (ObjectUtils.isNotEmpty(request)) {
            List<String> productIds = new ArrayList<>(request.getProductsToCheckout());
            Set<Product> products = new HashSet<>();
            if (!CollectionUtils.isEmpty(productIds)) {
                Map<String, Integer> productToCountMap = new HashMap<>();
                for (String productId : request.getProductsToCheckout()) {
                    Optional<Product> product = productRepository.findByProductId(productId);
                    if (product.isPresent()) {
                        if (productToCountMap.containsKey(productId)) {
                            productToCountMap.put(productId, productToCountMap.get(productId) + 1);
                        } else {
                            productToCountMap.put(productId, 1);
                        }
                        products.add(product.get());
                    }
                }

                for (Product product : products) {
                    float totalPriceForProduct = getTotalPrice(productToCountMap, product);
                    totalPrice = totalPrice + totalPriceForProduct;
                }
            }
        }
        return new CheckoutResponse(totalPrice);
    }

    private static float getTotalPrice(Map<String, Integer> productToCountMap, Product product) {
        float unitCost = product.getUnitPrice();
        int quantity = productToCountMap.get(product.getProductId());
        float totalCostForProduct = unitCost * quantity;

        if (product.isDiscountApplicable()) {
            return applyDiscountAndReturnCostForProduct(product, quantity);
        }
        return totalCostForProduct;
    }

    private static float applyDiscountAndReturnCostForProduct(Product product, int quantity) { //7
        float totalCostForProduct = 0;
        float totalCostForUnit = product.getUnitPrice() * quantity; //700
        Discount discount = product.getDiscount();
        if (ObjectUtils.isNotEmpty(discount)) {
            int quantityNeedToAvailDiscount = discount.getQuantity(); //3
            if (quantity >= quantityNeedToAvailDiscount) {
                int numberOfTimesDiscountIsApplied = quantity / quantityNeedToAvailDiscount; //2
                float discountedPrice = numberOfTimesDiscountIsApplied * discount.getDiscountedAmount();
                float nonDiscountedPrice = (quantity - (quantityNeedToAvailDiscount * numberOfTimesDiscountIsApplied)) * product.getUnitPrice();
                totalCostForProduct = discountedPrice + nonDiscountedPrice;
            } else {
                totalCostForProduct = totalCostForUnit;
            }
        }
        return totalCostForProduct;
    }


}
