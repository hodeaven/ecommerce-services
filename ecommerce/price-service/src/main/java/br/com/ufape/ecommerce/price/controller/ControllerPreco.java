package br.com.ufape.ecommerce.price.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.ecommerce.price.model.Preco;
import br.com.ufape.ecommerce.price.service.ServicePreco;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/precos")
public class ControllerPreco {
    
	private final ServicePreco servicePreco;

	public ControllerPreco(ServicePreco servicePreco) {
        this.servicePreco = servicePreco;
    }

    @GetMapping
    public List<Preco> encontrarTodosPrecos() {
        return servicePreco.encontrarTodosPrecos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Preco> encontrarPrecoPorId(@PathVariable Long id) {
    	Preco preco = servicePreco.encontrarPrecoPorId(id);
        return ResponseEntity.ok(preco);
    }

    @PostMapping
    public ResponseEntity<Preco> criarPreco(@Valid @RequestBody Preco preco) {
        return new ResponseEntity<>(servicePreco.criarPreco(preco), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Preco> atualizarPreco(@PathVariable Long id, @Valid @RequestBody Preco preco) {
    	Preco precoAtualizado = servicePreco.atualizarPreco(id, preco);
        return ResponseEntity.ok(precoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPreco(@PathVariable Long id) {
    	servicePreco.removerPreco(id);
        return ResponseEntity.noContent().build();
    }
    
}
