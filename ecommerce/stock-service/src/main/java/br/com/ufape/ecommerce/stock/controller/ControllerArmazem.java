package br.com.ufape.ecommerce.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.ecommerce.stock.model.Armazem;
import br.com.ufape.ecommerce.stock.repository.RepositoryArmazem;

@RestController
@RequestMapping("/api/armazens")
public class ControllerArmazem {

	@Autowired
	private RepositoryArmazem armazemService;
	
	@GetMapping
	public List<Armazem> listarArmazens(){
		return armazemService.findAll();
	}
	
	@GetMapping("/{id}")
	public Armazem encontrarArmazem(@PathVariable long id) {
		return armazemService.findById(id).orElse(null);
	}
	
	@PostMapping
	public Armazem criarNovoArmazem(@RequestBody Armazem novoArmazem) {
		return armazemService.save(novoArmazem);
	}
	
	@DeleteMapping("/{id}")
	public void removerArmazem(@PathVariable long id) {
		armazemService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Armazem atualizarArmazem(@PathVariable long id, @RequestBody Armazem novoArmazem) {
		Armazem armazemAntigo = armazemService.findById(id).orElse(null);
		armazemAntigo.setGerenteId(novoArmazem.getGerenteId());
		armazemAntigo.setCapacidadeMaxima(novoArmazem.getCapacidadeMaxima());
		armazemAntigo.setCapacidadeAtual(novoArmazem.getCapacidadeAtual());
		armazemAntigo.setEnderecoId(novoArmazem.getEnderecoId());
		return armazemService.save(armazemAntigo);
	}
	
}
