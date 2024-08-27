package br.com.ufape.ecommerce.catalog.controller;

import java.util.List;

import br.com.ufape.ecommerce.catalog.model.Produto;
import br.com.ufape.ecommerce.catalog.service.ServiceProduto;
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

@RestController
@RequestMapping("/produtos")
public class ControllerProduto {

	private final ServiceProduto serviceProduto;

	public ControllerProduto(ServiceProduto serviceProduto) {
        this.serviceProduto = serviceProduto;
    }

    @GetMapping
    public List<Produto> encontrarTodosProdutos() {
        return serviceProduto.encontrarTodosProdutos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> encontrarProdutoPorId(@PathVariable Long id) {
    	Produto produto = serviceProduto.encontrarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Produto> encontrarProdutosPorCategoria(@PathVariable Long categoriaId) {
        return serviceProduto.encontrarProdutosPorCategoria(categoriaId);
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto) {
    	return new ResponseEntity<>(serviceProduto.criarProduto(produto), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produto) {
    	Produto produtoAtualizado = serviceProduto.atualizarProduto(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long id) {
    	serviceProduto.removerProduto(id);
        return ResponseEntity.noContent().build();
    }
}
