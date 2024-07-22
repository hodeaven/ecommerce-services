package br.com.ufape.ecommerce.catalog.controller;

import java.util.List;
import br.com.ufape.ecommerce.catalog.model.Produto;
import br.com.ufape.ecommerce.catalog.repository.RepositoryCategoria;
import br.com.ufape.ecommerce.catalog.repository.RepositoryProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class ControllerProduto {

    @Autowired
    RepositoryProduto produtoService;
    @Autowired
    RepositoryCategoria categoriaService;

    @PostMapping("/categorias/{id}/produtos")
    public Produto criarProduto(@RequestBody Produto produto, @PathVariable Long id ) {
        produto.setCategoria(categoriaService.findById(id).orElse(null));
        return produtoService.save(produto);
    }

    @GetMapping("/produtos")
    public List<Produto> pegarProduto() {
        return produtoService.findAll();
    }

    @DeleteMapping("/produtos/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deleteById(id);
    }

    @PutMapping("/produtos/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto novoProduto) {
        Produto produtoAntigo = produtoService.findById(id).orElse(null);
        if (produtoAntigo == null) {
            return null;
        }
        produtoAntigo.setNome(novoProduto.getNome());
        
        produtoAntigo.setCategoria(novoProduto.getCategoria());
        produtoAntigo.setDescricao(novoProduto.getDescricao());
            return produtoService.save(produtoAntigo);
    }
}
