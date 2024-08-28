package br.com.ufape.es.topicos.price_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ufape.es.topicos.price_service.dto.PriceRequest;
import br.com.ufape.es.topicos.price_service.dto.PriceResponse;
import br.com.ufape.es.topicos.price_service.model.Price;
import br.com.ufape.es.topicos.price_service.service.ServicePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class ControllerPrice {

    private final ServicePrice servicePrice;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Price createPrice(@RequestBody PriceRequest priceRequest) {
        return servicePrice.createPrice(priceRequest);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PriceResponse> getAllPrices() {
        return servicePrice.getAllPrices();
    }
    

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public PriceResponse getPriceById(@PathVariable Long id) {
        return servicePrice.getPriceById(id);
    }
    
}
