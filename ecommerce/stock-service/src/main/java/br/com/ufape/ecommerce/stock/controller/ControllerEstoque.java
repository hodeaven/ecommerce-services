package br.com.ufape.ecommerce.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ufape.ecommerce.stock.model.Estoque;
import br.com.ufape.ecommerce.stock.services.ServiceEstoque;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estoques")
public class ControllerEstoque {

    private final ServiceEstoque serviceEstoque;

    public ControllerEstoque(ServiceEstoque serviceEstoque) {
        this.serviceEstoque = serviceEstoque;
    }

    @GetMapping
    public List<Estoque> encontrarTodosEstoques() {
        return serviceEstoque.encontrarTodosEstoques();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Estoque> encontrarEstoquePorId(@PathVariable Long id) {
        Estoque estoque = serviceEstoque.encontrarEstoquePorId(id);
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/armazem/{armazemId}")
    public List<Estoque> encontrarEstoquePorIdDeArmazem(@PathVariable Long armazemId) {
        return serviceEstoque.encontrarEstoquePorIdDeArmazem(armazemId);
    }

    @PostMapping
    public ResponseEntity<Estoque> criarEstoque(@Valid @RequestBody Estoque estoque) {
        return new ResponseEntity<>(serviceEstoque.criarEstoque(estoque), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Estoque> updateStock(@PathVariable Long id, @Valid @RequestBody Estoque estoque) {
        Estoque estoqueAtualizado = serviceEstoque.atualizarEstoque(id, estoque);
        return ResponseEntity.ok(estoqueAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEstoque(@PathVariable Long id) {
    	serviceEstoque.removerEstoque(id);
        return ResponseEntity.noContent().build();
    }
    
}
