package br.com.ufape.ecommerce.catalog.controller;

import java.util.List;
import br.com.ufape.ecommerce.catalog.service.ServiceCategoria;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import br.com.ufape.ecommerce.catalog.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class ControllerCategoria {

	private final ServiceCategoria serviceCategoria;

	public ControllerCategoria(ServiceCategoria serviceCategoria) {
        this.serviceCategoria = serviceCategoria;
    }

    @GetMapping
    public List<Categoria> encontrarTodasCategorias() {
        return serviceCategoria.encontrarTodasCategorias();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> encontrarCategoriaPorId(@PathVariable Long id) {
    	Categoria categoria = serviceCategoria.encontrarCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(serviceCategoria.criarCategoria(categoria), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
    	Categoria categoriaAtualizada = serviceCategoria.atualizarCategoria(id, categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCategoria(@PathVariable Long id) {
    	serviceCategoria.removerCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
