package br.com.ufape.es.topicos.price_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PriceResponse {
    private Long id;
    private Long productId;
    private double productValue;
}