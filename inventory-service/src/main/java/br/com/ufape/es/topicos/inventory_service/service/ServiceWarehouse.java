package br.com.ufape.es.topicos.inventory_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.stereotype.Service;

import br.com.ufape.es.topicos.inventory_service.dto.WarehouseRequest;
import br.com.ufape.es.topicos.inventory_service.dto.WarehouseResponse;
import br.com.ufape.es.topicos.inventory_service.model.Warehouse;
import br.com.ufape.es.topicos.inventory_service.repository.RepositoryWarehouse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceWarehouse {

    private final RepositoryWarehouse repositoryWarehouse;

    public void createWarehouse(WarehouseRequest warehouseRequest){
        Warehouse warehouse = Warehouse.builder()
        .name(warehouseRequest.getName())
        .localization(warehouseRequest.getLocalization())
        .build();

        repositoryWarehouse.save(warehouse);
        log.info("Warehouse {} is saved", warehouse.getId());
    }

    public List<WarehouseResponse> getAllWarehouses(){
        List<Warehouse> warehouses = repositoryWarehouse.findAll();
        return warehouses.stream().map(this::mapToWarehouseResponse).toList();
    }

    public WarehouseResponse getWarehouseById(Long id){
        Warehouse warehouse = repositoryWarehouse.findById(id).orElse(null);
        return mapToWarehouseResponse(warehouse);
    }

    private WarehouseResponse mapToWarehouseResponse(Warehouse warehouse){
        return WarehouseResponse.builder()
        .id(warehouse.getId())
        .name(warehouse.getName())
        .localization(warehouse.getLocalization())
        .build();
    }
}
