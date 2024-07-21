package br.com.ufape.ecommerce.stock.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Armazem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nomeDoArmazem;
	private int enderecoId;
	private int gerenteId;
	private int capacidadeAtual;
	private int capacidadeMaxima;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Estoque> estoques;
	
	public Armazem() {}
	
	public Armazem(String nomeDoArmazem, int enderecoId, int gerenteId, int capacidadeMaxima) {
		this.nomeDoArmazem = nomeDoArmazem;
		this.enderecoId = enderecoId;
		this.gerenteId = gerenteId;
		this.capacidadeAtual = 0;
		this.capacidadeMaxima = capacidadeMaxima;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeDoArmazem() {
		return nomeDoArmazem;
	}

	public void setNomeDoArmazem(String nomeDoArmazem) {
		this.nomeDoArmazem = nomeDoArmazem;
	}

	public int getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(int enderecoId) {
		this.enderecoId = enderecoId;
	}

	public int getGerenteId() {
		return gerenteId;
	}

	public void setGerenteId(int gerenteId) {
		this.gerenteId = gerenteId;
	}

	public int getCapacidadeAtual() {
		return capacidadeAtual;
	}

	public void setCapacidadeAtual(int capacidadeTotal) {
		this.capacidadeAtual = capacidadeTotal;
	}

	public int getCapacidadeMaxima() {
		return capacidadeMaxima;
	}

	public void setCapacidadeMaxima(int capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}
	
}
