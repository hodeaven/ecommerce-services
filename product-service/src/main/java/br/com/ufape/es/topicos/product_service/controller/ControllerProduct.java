package br.com.ufape.es.topicos.product_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.es.topicos.product_service.dto.ProductRequest;
import br.com.ufape.es.topicos.product_service.dto.ProductResponse;
import br.com.ufape.es.topicos.product_service.service.ServiceProduct;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ControllerProduct {

    private final ServiceProduct serviceProduct;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod= "fallbackMethod")
    public void createProduct(@RequestBody ProductRequest productRequest) {
        serviceProduct.createProduct(productRequest);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponse> getAllProducts() {
        return serviceProduct.getAllProducts();
    }
    

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponse getProductById(@PathVariable Long id) {
        return serviceProduct.getProductById(id);
    }

    public String fallbackMethod(ProductRequest productRequest, RuntimeException runtimeException){
        return "Oops! O serviço está indisponível. Tente novamente mais tarde.";
    }
    
}
