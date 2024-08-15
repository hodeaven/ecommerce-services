package br.com.ufape.ecommerce.catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ufape.ecommerce.catalog.exception.DadoNaoEncontradoException;
import br.com.ufape.ecommerce.catalog.model.Produto;
import br.com.ufape.ecommerce.catalog.repository.RepositoryProduto;

@Service
public class ServiceProduto {
	private final RepositoryProduto repositorioProduto;

    public ServiceProduto(RepositoryProduto repositorioProduto) {
        this.repositorioProduto = repositorioProduto;
    }

    public List<Produto> encontrarTodosProdutos() {
        return repositorioProduto.findAll();
    }
    
    public Produto encontrarProdutoPorId(Long id) {
        return repositorioProduto.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Produto com o id (" + id + ") não encontrado."));
    }

    public List<Produto> encontrarProdutosPorCategoria(Long categoriaId) {
        return repositorioProduto.findAllByCategoriaId(categoriaId);
    }

    public Produto criarProduto(Produto produto) {
        return repositorioProduto.save(produto);
    }
    
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        return repositorioProduto.findById(id)
            .map(produtoAtual -> {
            	produtoAtual.setCategoria(produtoAtualizado.getCategoria());
            	produtoAtual.setNome(produtoAtualizado.getNome());
            	produtoAtual.setPreco(produtoAtualizado.getPreco());
                return repositorioProduto.save(produtoAtual);
            })
            .orElseThrow(() -> new DadoNaoEncontradoException("Produto com o id (" + id + ") não encontrado."));
    }

    public void removerProduto(Long id) {
        Produto produto = repositorioProduto.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Produto com o id (" + id + ") não encontrado."));
        repositorioProduto.delete(produto);
    }
}
