package org.ecommerce.api;

import org.ecommerce.exception.ServiceFailedException;
import org.ecommerce.request.CheckoutRequest;
import org.ecommerce.response.CheckoutResponse;
import org.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
public class CheckoutApi {

    @Autowired
    CheckoutService checkoutService;

    @PostMapping("/checkout")
    @Produces("application/json")
    public ResponseEntity doCheckout(@RequestBody CheckoutRequest request) {
        CheckoutResponse response;
        try {
            response = checkoutService.doCheckout(request);
            return ResponseEntity.ok(response);
        } catch (ServiceFailedException e) {
            return ResponseEntity.badRequest().body("Checkout failed.");
        }
    }

}
