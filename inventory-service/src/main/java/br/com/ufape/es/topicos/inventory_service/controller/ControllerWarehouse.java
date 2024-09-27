package br.com.ufape.es.topicos.inventory_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import java.util.ArrayList;
import java.util.Base64;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public void createWarehouse(@RequestHeader(value = "Authorization", required = false) String authorization, @RequestBody WarehouseRequest warehouseRequest) {
        if(authorization.isEmpty())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        if(isUser(authorization,"estoquista") || isUser(authorization,"gerente")){
            serviceWarehouse.createWarehouse(warehouseRequest);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public List<WarehouseResponse> getAllWarehouses(@RequestHeader(value = "Authorization", required = false) String authorization) {
        if(authorization.isEmpty())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        if(isUser(authorization,"estoquista") || isUser(authorization,"gerente")){
            return serviceWarehouse.getAllWarehouses();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
    

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public WarehouseResponse getWarehouseById(@RequestHeader(value = "Authorization", required = false) String authorization, @PathVariable Long id) {
        if(authorization.isEmpty())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        if(isUser(authorization,"estoquista") || isUser(authorization,"gerente")){
            return serviceWarehouse.getWarehouseById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
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
