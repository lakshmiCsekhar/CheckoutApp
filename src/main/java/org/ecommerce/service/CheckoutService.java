package org.ecommerce.service;

import org.ecommerce.exception.ServiceFailedException;
import org.ecommerce.request.CheckoutRequest;
import org.ecommerce.response.CheckoutResponse;


public interface CheckoutService {
    public CheckoutResponse doCheckout(CheckoutRequest request) throws ServiceFailedException;
}
