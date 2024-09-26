package br.com.ufape.es.topicos.product_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.ufape.es.topicos.product_service.dto.ProductRequest;
import br.com.ufape.es.topicos.product_service.dto.ProductResponse;
import br.com.ufape.es.topicos.product_service.model.Product;
import br.com.ufape.es.topicos.product_service.repository.RepositoryProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ServiceProduct {

    private final RepositoryProduct repositoryProduct;
    private final WebClient.Builder webClientBuilder;
    private final ProductMessageProducer messageProducer; //

    public void createProduct(ProductRequest productRequest, String quantity, String price){
        Product product = Product.builder()
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .size(productRequest.getSize())
        .build();

        repositoryProduct.save(product);

        if(quantity == null) quantity = "0";
        if(price == null) price = "0";

        //Requisição JSON para o Inventory Service
        String inventoryBody = "{"
                            +"\"productId\": "+ product.getId() +","
                            +"\"quantity\": "+ quantity +""
                            + "}";

        //Requisição JSON para o Price Service
        String priceBody = "{"
                            +"\"productId\": "+ product.getId() +","
                            +"\"productValue\": "+ price +""
                            + "}";
        
        //Comunicação Síncrona (usando WebClient)
        Object objectInventory = webClientBuilder.build().post()
            .uri("http://inventory-service/api/inventories/add-in-warehouse/1")
            .header("Content-Type", "application/json")
            .bodyValue(inventoryBody)
            .retrieve()
            .bodyToMono(Object.class)
            .block();

        Object objectPrice = webClientBuilder.build().post()
            .uri("http://price-service/api/prices")
            .header("Content-Type", "application/json")
            .bodyValue(priceBody)
            .retrieve()
            .bodyToMono(Object.class)
            .block();
        
        //Realizando o Rollback do repositorio caso o Inventário não responda
        if(objectInventory == null){
            throw new RuntimeException("O serviço de estocagem não comunicou-se.");
        }
        else if(objectPrice == null){
            throw new RuntimeException("O serviço de precificação não comunicou-se.");
        }

        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        // Recupera todos os produtos do repositório
        List<Product> products = repositoryProduct.findAll();
        List<ProductResponse> productResponses = products.stream()
                                                         .map(this::mapToProductResponse)
                                                         .toList();
        return productResponses;
    }

    public ProductResponse getProductById(Long id){
        Product product = repositoryProduct.findById(id).orElse(null);
        messageProducer.sendMessage(String.valueOf(product.getId())); //enviando uma requisição assíncrona
        return mapToProductResponse(product);
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .size(product.getSize())
        .build();
    }
}
