package br.com.ufape.ecommerce.price.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.ecommerce.price.model.Politica;
import br.com.ufape.ecommerce.price.service.ServicePolitica;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/politicas")
public class ControllerPolitica {
    
	private final ServicePolitica servicePolitica;

	public ControllerPolitica(ServicePolitica servicePolitica) {
        this.servicePolitica = servicePolitica;
    }

    @GetMapping
    public List<Politica> encontrarTodasPoliticas() {
        return servicePolitica.encontrarTodasPoliticas();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Politica> encontrarPoliticaPorId(@PathVariable Long id) {
    	Politica politica = servicePolitica.encontrarPoliticaPorId(id);
        return ResponseEntity.ok(politica);
    }

    @PostMapping
    public ResponseEntity<Politica> criarPolitica(@Valid @RequestBody Politica politica) {
        return new ResponseEntity<>(servicePolitica.criarPolitica(politica), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Politica> atualizarPolitica(@PathVariable Long id, @Valid @RequestBody Politica politica) {
    	Politica politicaAtualizada = servicePolitica.atualizarPolitica(id, politica);
        return ResponseEntity.ok(politicaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPolitica(@PathVariable Long id) {
    	servicePolitica.removerPolitica(id);
        return ResponseEntity.noContent().build();
    }
}
