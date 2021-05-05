package br.com.contas.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contas_a_pagar")
public class ContasAPagar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double valorOriginal;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private Double valorCorrigido;
	private Integer diasDeAtraso;
	
	public ContasAPagar() {
	}
	
	public ContasAPagar(String nome, Double valorOriginal, LocalDate dataVencimento, 
			LocalDate dataPagamento, Double valorCorrigido, Integer diasDeAtraso) {
		this.nome = nome;
		this.valorOriginal = valorOriginal;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valorCorrigido = valorCorrigido;
		this.diasDeAtraso = diasDeAtraso;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public Double getValorCorrigido() {
		return valorCorrigido;
	}
	
	public Integer getDiasDeAtraso() {
		return diasDeAtraso;
	}

}
