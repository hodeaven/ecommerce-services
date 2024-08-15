package br.com.ufape.ecommerce.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ufape.ecommerce.stock.model.Armazem;
import br.com.ufape.ecommerce.stock.services.ServiceArmazem;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/armazens")
public class ControllerArmazem {

    private final ServiceArmazem serviceArmazem;

    public ControllerArmazem(ServiceArmazem serviceArmazem) {
        this.serviceArmazem = serviceArmazem;
    }

    @GetMapping
    public List<Armazem> encontrarTodosArmazens() {
        return serviceArmazem.encontrarTodosArmazens();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Armazem> encontrarArmazemPorId(@PathVariable Long id) {
    	Armazem armazem = serviceArmazem.encontrarArmazemPorId(id);
        return ResponseEntity.ok(armazem);
    }

    @PostMapping
    public ResponseEntity<Armazem> criarArmazem(@Valid @RequestBody Armazem armazem) {
        return new ResponseEntity<>(serviceArmazem.criarArmazem(armazem), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Armazem> atualizarArmazem(@PathVariable Long id, @Valid @RequestBody Armazem armazem) {
    	Armazem armazemAtualizado = serviceArmazem.atualizarArmazem(id, armazem);
        return ResponseEntity.ok(armazemAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerArmazem(@PathVariable Long id) {
    	serviceArmazem.removerArmazem(id);
        return ResponseEntity.noContent().build();
    }

}
