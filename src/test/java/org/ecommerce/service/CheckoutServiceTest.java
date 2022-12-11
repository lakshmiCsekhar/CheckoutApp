package org.ecommerce.service;


import org.ecommerce.model.Product;
import org.ecommerce.repository.ProductRepository;
import org.ecommerce.request.CheckoutRequest;
import org.ecommerce.response.CheckoutResponse;
import org.ecommerce.service.impl.CheckoutServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.ecommerce.util.TestUtil.*;

@RunWith(SpringRunner.class)
public class CheckoutServiceTest {

    @InjectMocks
    CheckoutService checkoutService = new CheckoutServiceImpl();


    @Mock
    CheckoutRequest request;
    @MockBean
    ProductRepository productRepository;
    @Mock
    Product productWithDiscount;

    @Mock
    Product productWithDiscount_1;

    @Mock
    Product productWithoutDiscount;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDoCheckOutForDiscountedItem() throws IOException {
        // test data prep
        String discountedProductJsonString = jsonFromFile("/testdata/productWithDiscount.json");
        String nonDiscountedProductJsonString = jsonFromFile("/testdata/productWithoutDiscount.json");
        productWithDiscount = jsonToProduct(discountedProductJsonString);
        productWithoutDiscount = jsonToProduct(nonDiscountedProductJsonString);
        List<String> productsToCheckout = Arrays.asList("001", "001", "001", "001", "003");

        //mock
        when(request.getProductsToCheckout()).thenReturn(productsToCheckout);
        when(productRepository.findByProductId("001")).thenReturn(Optional.ofNullable(productWithDiscount));
        when(productRepository.findByProductId("003")).thenReturn(Optional.ofNullable(productWithoutDiscount));

        CheckoutResponse response = checkoutService.doCheckout(request);
        Assert.assertEquals(350F,response.getPrice(),0);
    }

    @Test
    public void shouldApplyMultipleDiscounts() throws IOException {
        // test data prep
        String discountedProductJsonString = jsonFromFile("/testdata/productWithDiscount.json");
        String discountedProductJsonString_1 = jsonFromFile("/testdata/productWithDiscount_1.json");
        String nonDiscountedProductJsonString = jsonFromFile("/testdata/productWithoutDiscount.json");
        productWithDiscount = jsonToProduct(discountedProductJsonString);
        productWithDiscount_1 = jsonToProduct(discountedProductJsonString_1);
        productWithoutDiscount = jsonToProduct(nonDiscountedProductJsonString);
        List<String> productsToCheckout = Arrays.asList("001", "001", "001", "002","002","002", "003");

        //mock
        when(request.getProductsToCheckout()).thenReturn(productsToCheckout);
        when(productRepository.findByProductId("001")).thenReturn(Optional.ofNullable(productWithDiscount));
        when(productRepository.findByProductId("002")).thenReturn(Optional.ofNullable(productWithDiscount_1));
        when(productRepository.findByProductId("003")).thenReturn(Optional.ofNullable(productWithoutDiscount));

        CheckoutResponse response = checkoutService.doCheckout(request);
        Assert.assertEquals(450F,response.getPrice(),0);
    }

    @Test
    public void shouldApplyDiscountMultipleTimes() throws IOException {
        // test data prep
        String discountedProductJsonString = jsonFromFile("/testdata/productWithDiscount.json");
        String discountedProductJsonString_1 = jsonFromFile("/testdata/productWithDiscount_1.json");
        String nonDiscountedProductJsonString = jsonFromFile("/testdata/productWithoutDiscount.json");
        productWithDiscount = jsonToProduct(discountedProductJsonString);
        productWithDiscount_1 = jsonToProduct(discountedProductJsonString_1);
        productWithoutDiscount = jsonToProduct(nonDiscountedProductJsonString);
        List<String> productsToCheckout = Arrays.asList("001", "001", "001", "001", "001", "001", "003");

        //mock
        when(request.getProductsToCheckout()).thenReturn(productsToCheckout);
        when(productRepository.findByProductId("001")).thenReturn(Optional.ofNullable(productWithDiscount));
        when(productRepository.findByProductId("002")).thenReturn(Optional.ofNullable(productWithDiscount_1));
        when(productRepository.findByProductId("003")).thenReturn(Optional.ofNullable(productWithoutDiscount));

        CheckoutResponse response = checkoutService.doCheckout(request);
        Assert.assertEquals(450F,response.getPrice(),0);
    }

    @Test
    public void shouldDoCheckOutWithoutDiscount() throws IOException {
        // test data prep
        String discountedProductJsonString = jsonFromFile("/testdata/productWithDiscount.json");
        String nonDiscountedProductJsonString = jsonFromFile("/testdata/productWithoutDiscount.json");
        productWithDiscount = jsonToProduct(discountedProductJsonString);
        productWithoutDiscount = jsonToProduct(nonDiscountedProductJsonString);
        List<String> productsToCheckout = Arrays.asList("001", "003");

        //mock
        when(request.getProductsToCheckout()).thenReturn(productsToCheckout);
        when(productRepository.findByProductId("001")).thenReturn(Optional.ofNullable(productWithDiscount));
        when(productRepository.findByProductId("003")).thenReturn(Optional.ofNullable(productWithoutDiscount));

        CheckoutResponse response = checkoutService.doCheckout(request);
        Assert.assertEquals(150F,response.getPrice(),0);
    }



}
