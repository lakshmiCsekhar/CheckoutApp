package org.ecommerce.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.model.Product;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String jsonFromFile(String filePath) throws IOException {
        File resource = new ClassPathResource(filePath).getFile();
        byte[] byteArray = Files.readAllBytes(resource.toPath());
        return new String(byteArray);
    }

    public static Product jsonToProduct(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Product.class);
    }
}
