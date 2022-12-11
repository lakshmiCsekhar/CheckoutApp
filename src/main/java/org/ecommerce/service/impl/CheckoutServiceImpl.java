package org.ecommerce.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.ecommerce.exception.ServiceFailedException;
import org.ecommerce.model.Discount;
import org.ecommerce.model.Product;
import org.ecommerce.repository.ProductRepository;
import org.ecommerce.request.CheckoutRequest;
import org.ecommerce.response.CheckoutResponse;
import org.ecommerce.service.CheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    public static final String PRODUCT_ID_VALIDATION_REGEX = "^\\d{1,3}$";
    Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    @Autowired
    ProductRepository productRepository;


    public CheckoutResponse doCheckout(CheckoutRequest request) throws ServiceFailedException {
        float totalPrice = 0;
        if (ObjectUtils.isNotEmpty(request)) {
            try {
                totalPrice = startCheckout(request, totalPrice);
            } catch (Exception e) {
                logger.error("Something went wrong during checkout.");
                throw new ServiceFailedException("Something went wrong : " + e);
            }

        } else {
            logger.error("Request is empty.");
        }
        return new CheckoutResponse(totalPrice);
    }

    private float startCheckout(CheckoutRequest request, float totalPrice) {
        List<String> productIds = new ArrayList<>(request.getProductsToCheckout());
        Set<Product> products = new HashSet<>();
        if (!CollectionUtils.isEmpty(productIds)) {
            Map<String, Integer> productToCountMap = new HashMap<>();
            for (String productId : request.getProductsToCheckout()) {
                fetchProductsForProductId(products, productToCountMap, productId);
            }
            for (Product product : products) {
                float totalPriceForProduct = getTotalPrice(productToCountMap, product);
                totalPrice = totalPrice + totalPriceForProduct;
            }
        } else {
            logger.error("Product ids are empty.");
        }
        return totalPrice;
    }

    private void fetchProductsForProductId(Set<Product> products, Map<String, Integer> productToCountMap, String productId) {
        if (productId.matches(PRODUCT_ID_VALIDATION_REGEX)) {
            Optional<Product> product = productRepository.findByProductId(productId);
            if (product.isPresent()) {
                if (productToCountMap.containsKey(productId)) {
                    productToCountMap.put(productId, productToCountMap.get(productId) + 1);
                } else {
                    productToCountMap.put(productId, 1);
                }
                products.add(product.get());
            }
        } else {
            logger.error(String.format("ProductId is incorrect:  %s", productId));
        }
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

    private static float applyDiscountAndReturnCostForProduct(Product product, int quantity) {
        float totalCostForProduct = 0;
        float totalCostForUnit = product.getUnitPrice() * quantity;
        Discount discount = product.getDiscount();
        if (ObjectUtils.isNotEmpty(discount)) {
            int quantityNeedToAvailDiscount = discount.getQuantity();
            if (quantity >= quantityNeedToAvailDiscount) {
                int numberOfTimesDiscountIsApplied = quantity / quantityNeedToAvailDiscount;
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
