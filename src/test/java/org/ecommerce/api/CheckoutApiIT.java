package org.ecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.Application;
import org.ecommerce.request.CheckoutRequest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-testdb.properties")
public class CheckoutApiIT {

    @Autowired
    private MockMvc mvc;

    @Test
    public void doCheckout()
            throws Exception {
        CheckoutRequest request = new CheckoutRequest();
        request.setProductsToCheckout(Arrays.asList("001", "001", "001", "002"));
        mvc.perform(post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price", Matchers.is(280.0)));
    }

}
