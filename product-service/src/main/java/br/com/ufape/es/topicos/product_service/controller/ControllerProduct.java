package br.com.ufape.es.topicos.product_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.ufape.es.topicos.product_service.dto.ProductRequest;
import br.com.ufape.es.topicos.product_service.dto.ProductResponse;
import br.com.ufape.es.topicos.product_service.service.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import java.util.ArrayList;
import java.util.Base64;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ControllerProduct {

    private final ServiceProduct serviceProduct;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod= "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> createProduct(@RequestHeader(value = "Authorization", required = false) String authorization, @RequestBody ProductRequest productRequest, @RequestParam(required = false) String quantity, @RequestParam(required = false) String price) {
        if(authorization.isEmpty())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        if(isUser(authorization,"vendedor") || isUser(authorization,"gerente")){
            serviceProduct.createProduct(productRequest,quantity,price);
            return CompletableFuture.supplyAsync(() -> "Produto adicionado!");
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponse> getAllProducts(@RequestHeader(value = "Authorization", required = false) String authorization) {
        return serviceProduct.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponse getProductById(@RequestHeader(value = "Authorization", required = false) String authorization, @PathVariable Long id) {
        return serviceProduct.getProductById(id);
    }

    public CompletableFuture<String> fallbackMethod(String authorization, ProductRequest productRequest, String quantity, String price, Throwable throwable) {
        return CompletableFuture.supplyAsync(() -> "O serviços auxiliares estão temporariamente indisponíveis. Tente novamente mais tarde.");
    }
    
    //Métodos utilizados para validações de usuários
    private Boolean isUser(String token, String role){
        if(getRolesFromToken(token).contains(role)){
            return true;
        } else {
            return false;
        }
    }

    private String decodeJWT(String token) {
        try {
            String[] chunks = token.split("\\.");
            if (chunks.length < 2) {
                throw new IllegalArgumentException("Token JWT inválido.");
            }
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            return payload;
        } catch (RuntimeException e) {
            return null;
        }
    }
    
    private List<String> getRolesFromToken(String token) throws RuntimeException {
        try {
            String payload = decodeJWT(token);

            if (payload == null) {
                throw new RuntimeException("Invalid token");
            }

            // Converte o payload para um JSONObject
            JSONObject jsonPayload = new JSONObject(payload);

            // Navega até a lista de roles
            JSONObject resourceAccess = jsonPayload.getJSONObject("resource_access");
            JSONObject springCloudClient = resourceAccess.getJSONObject("spring-cloud-client");
            JSONArray rolesArray = springCloudClient.getJSONArray("roles");

            // Cria a lista para armazenar os roles
            List<String> roles = new ArrayList<>();

            // Itera sobre o JSONArray
            for (int i = 0; i < rolesArray.length(); i++) {
                roles.add(rolesArray.getString(i));
            }

            return roles;
        } catch (Exception e) {
            throw new RuntimeException("Error decoding roles from token", e);
        }
    }
    
}
