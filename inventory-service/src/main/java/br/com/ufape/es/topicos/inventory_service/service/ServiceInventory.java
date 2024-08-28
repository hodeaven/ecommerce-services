package br.com.ufape.es.topicos.inventory_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.stereotype.Service;

import br.com.ufape.es.topicos.inventory_service.model.Inventory;
import br.com.ufape.es.topicos.inventory_service.repository.RepositoryInventory;
import br.com.ufape.es.topicos.inventory_service.repository.RepositoryWarehouse;
import br.com.ufape.es.topicos.inventory_service.dto.InventoryRequest;
import br.com.ufape.es.topicos.inventory_service.dto.InventoryResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceInventory {

    private final RepositoryInventory repositoryInventory;
    private final RepositoryWarehouse repositoryWarehouse;

    public Inventory createInventory(InventoryRequest inventoryRequest, Long warehouseId){
        Inventory inventory = Inventory.builder()
        .productId(inventoryRequest.getProductId())
        .quantity(inventoryRequest.getQuantity())
        .warehouse(repositoryWarehouse.findById(warehouseId).orElse(null))
        .build();

        repositoryInventory.save(inventory);
        log.info("Inventory {} is saved", inventory.getId());
        return inventory;
    }

    public List<InventoryResponse> getAllInventories(){
        List<Inventory> inventories = repositoryInventory.findAll();
        return inventories.stream().map(this::mapToInventoryResponse).toList();
    }

    public InventoryResponse getInventoryById(Long id){
        Inventory inventory = repositoryInventory.findById(id).orElse(null);
        return mapToInventoryResponse(inventory);
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory){
        return InventoryResponse.builder()
        .id(inventory.getId())
        .productId(inventory.getProductId())
        .quantity(inventory.getQuantity())
        .warehouse(inventory.getWarehouse())
        .build();
    }
}
