package br.com.ufape.ecommerce.price.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ufape.ecommerce.price.model.Preco;
import br.com.ufape.ecommerce.price.repository.RepositoryPreco;
import br.com.ufape.ecommerce.price.exception.DadoNaoEncontradoException;

@Service
public class ServicePreco {
	
	private final RepositoryPreco repositorioPreco;

    public ServicePreco(RepositoryPreco repositorioPreco) {
        this.repositorioPreco = repositorioPreco;
    }

    public List<Preco> encontrarTodosPrecos() {
        return repositorioPreco.findAll();
    }
    
    public Preco encontrarPrecoPorId(Long id) {
        return repositorioPreco.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Preço com o id (" + id + ") não encontrado."));
    }

    public Preco criarPreco(Preco preco) {
        return repositorioPreco.save(preco);
    }
    
    public Preco atualizarPreco(Long id, Preco precoAtualizado) {
        return repositorioPreco.findById(id)
            .map(precoAtual -> {
            	precoAtual.setPolitica(precoAtualizado.getPolitica());
            	precoAtual.setProdutoId(precoAtualizado.getProdutoId());
            	precoAtual.setValorDoProduto(precoAtualizado.getValorDoProduto());
                return repositorioPreco.save(precoAtual);
            })
            .orElseThrow(() -> new DadoNaoEncontradoException("Preço com o id (" + id + ") não encontrado."));
    }

    public void removerPreco(Long id) {
        Preco preco = repositorioPreco.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Preço com o id (" + id + ") não encontrado."));
        repositorioPreco.delete(preco);
    }
}
