package br.com.ufape.es.topicos.inventory_service.dto;

import br.com.ufape.es.topicos.inventory_service.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private Long productId;
    private int quantity;
    private Warehouse warehouse;
}
