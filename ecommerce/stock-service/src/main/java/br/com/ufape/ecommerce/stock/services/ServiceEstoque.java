package br.com.ufape.ecommerce.stock.services;

import br.com.ufape.ecommerce.stock.exception.DadoNaoEncontradoException;
import br.com.ufape.ecommerce.stock.model.Estoque;
import br.com.ufape.ecommerce.stock.repository.RepositoryArmazem;
import br.com.ufape.ecommerce.stock.repository.RepositoryEstoque;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEstoque {

    private final RepositoryEstoque repositorioEstoque;
    
    @Autowired
    private RepositoryArmazem repositorioArmazem;
    
    @RabbitListener(queues = "estoqueQueue")
    public void receiveMessage(Long produtoId) {
    	System.out.println("Ouvi o Rabbit");
        // Lógica para criar um item de inventário com estoque zero
        Estoque estoque = new Estoque();
        estoque.setProdutoId(produtoId);
        estoque.setArmazem(repositorioArmazem.findAll().get(0));
        estoque.setQuantidade(0);

        // Salvar o inventário no banco de dados
        repositorioEstoque.save(estoque);
        System.out.println("Processei a mensagem do Rabbit");
    }

    public ServiceEstoque(RepositoryEstoque repositorioEstoque) {
        this.repositorioEstoque = repositorioEstoque;
    }

    public List<Estoque> encontrarTodosEstoques() {
        return repositorioEstoque.findAll();
    }
    
    public Estoque encontrarEstoquePorId(Long id) {
        return repositorioEstoque.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Estoque com o id (" + id + ") não encontrado."));
    }

    public List<Estoque> encontrarEstoquePorIdDeArmazem(Long warehouseId) {
        return repositorioEstoque.findByArmazemId(warehouseId);
    }

    public Estoque criarEstoque(Estoque stock) {
        return repositorioEstoque.save(stock);
    }
    
    public Estoque atualizarEstoque(Long id, Estoque estoqueAtualizado) {
        return repositorioEstoque.findById(id)
            .map(estoqueAtual -> {
            	estoqueAtual.setProdutoId(estoqueAtualizado.getProdutoId());
            	estoqueAtual.setQuantidade(estoqueAtualizado.getQuantidade());
            	estoqueAtual.setArmazem(estoqueAtualizado.getArmazem());
                return repositorioEstoque.save(estoqueAtual);
            })
            .orElseThrow(() -> new DadoNaoEncontradoException("Estoque com o id (" + id + ") não encontrado."));
    }

    public void removerEstoque(Long id) {
        Estoque estoque = repositorioEstoque.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Estoque com o id (" + id + ") não encontrado."));
        repositorioEstoque.delete(estoque);
    }
}
