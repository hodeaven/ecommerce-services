package br.com.ufape.es.topicos.inventory_service.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.es.topicos.inventory_service.model.Inventory;
import br.com.ufape.es.topicos.inventory_service.dto.InventoryRequest;
import br.com.ufape.es.topicos.inventory_service.dto.InventoryResponse;
import br.com.ufape.es.topicos.inventory_service.service.ServiceInventory;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class ControllerInventory {

    private final ServiceInventory serviceInventory;

    @PostMapping("/add-in-warehouse/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventory(@RequestBody InventoryRequest inventoryRequest, @PathVariable Long id) {
        return serviceInventory.createInventory(inventoryRequest, id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public List<InventoryResponse> getAllInventories() {
        return serviceInventory.getAllInventories();
    }
    

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public InventoryResponse getInventoryById(@PathVariable Long id) {
        return serviceInventory.getInventoryById(id);
    }
    
}
