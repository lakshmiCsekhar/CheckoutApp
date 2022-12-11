package org.ecommerce.api;

import org.ecommerce.request.CheckoutRequest;
import org.ecommerce.response.CheckoutResponse;
import org.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutApi {

    @Autowired
    CheckoutService checkoutService;


    @PostMapping("/checkout")
    public CheckoutResponse doCheckout(@RequestBody CheckoutRequest request)
    {
        return checkoutService.doCheckout(request);
    }

}
