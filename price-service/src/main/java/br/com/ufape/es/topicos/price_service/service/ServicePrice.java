package br.com.ufape.es.topicos.price_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.es.topicos.price_service.config.RabbitMQConsumerConfig;
import br.com.ufape.es.topicos.price_service.dto.PriceRequest;
import br.com.ufape.es.topicos.price_service.dto.PriceResponse;
import br.com.ufape.es.topicos.price_service.model.Price;
import br.com.ufape.es.topicos.price_service.repository.RepositoryPrice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ServicePrice {

    private final RepositoryPrice repositoryPrice;
    private final PriceMessageProducer messageProducer;

    public Price createPrice(PriceRequest priceRequest){
        Price price = Price.builder()
        .productId(priceRequest.getProductId())
        .productValue(priceRequest.getProductValue())
        .build();

        repositoryPrice.save(price);
        log.info("Price {} is saved", price.getId());
        return price;
    }

    public List<PriceResponse> getAllPrices(){
        List<Price> prices = repositoryPrice.findAll();
        return prices.stream().map(this::mapToPriceResponse).toList();
    }

    public PriceResponse getPriceById(Long id){
        Price price = repositoryPrice.findById(id).orElse(null);
        return mapToPriceResponse(price);
    }

    public PriceResponse getPriceByProductId(Long id){
        Price price = repositoryPrice.findByProductId(id);
        return mapToPriceResponse(price);
    }

    @RabbitListener(queues = RabbitMQConsumerConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        String valorDoProduto = String.valueOf(getPriceByProductId(Long.parseLong(message)).getProductValue());
        System.out.println("Preço do produto "+message+": R$ "+valorDoProduto);
        messageProducer.sendMessage(valorDoProduto);
    }

    private PriceResponse mapToPriceResponse(Price price){
        return PriceResponse.builder()
        .id(price.getId())
        .productId(price.getProductId())
        .productValue(price.getProductValue())
        .build();
    }
}
