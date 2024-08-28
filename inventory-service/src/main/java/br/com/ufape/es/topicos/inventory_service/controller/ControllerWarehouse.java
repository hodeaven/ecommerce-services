package br.com.ufape.es.topicos.inventory_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.es.topicos.inventory_service.dto.WarehouseRequest;
import br.com.ufape.es.topicos.inventory_service.dto.WarehouseResponse;
import br.com.ufape.es.topicos.inventory_service.service.ServiceWarehouse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class ControllerWarehouse {

    private final ServiceWarehouse serviceWarehouse;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createWarehouse(@RequestBody WarehouseRequest warehouseRequest) {
        serviceWarehouse.createWarehouse(warehouseRequest);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public List<WarehouseResponse> getAllWarehouses() {
        return serviceWarehouse.getAllWarehouses();
    }
    

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public WarehouseResponse getWarehouseById(@PathVariable Long id) {
        return serviceWarehouse.getWarehouseById(id);
    }
}
