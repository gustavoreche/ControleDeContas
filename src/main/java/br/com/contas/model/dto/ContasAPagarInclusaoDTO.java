package br.com.contas.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.contas.model.ContasAPagar;


public class ContasAPagarInclusaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "O campo não pode ser nulo")
	@NotEmpty(message = "O campo não pode ser vazio")
	private String nome;
	
	@NotNull(message = "O campo não pode ser nulo")
	@DecimalMin(value = "0.01", message = "O valor original tem que ser maior que zero")
	private Double valorOriginal;
	
	@NotNull(message = "O campo não pode ser nulo")
	private LocalDate dataVencimento;
	
	@NotNull(message = "O campo não pode ser nulo")
	private LocalDate dataPagamento;
	
	public ContasAPagarInclusaoDTO(
			@NotNull(message = "O campo não pode ser nulo") @NotEmpty(message = "O campo não pode ser vazio") String nome,
			@NotNull(message = "O campo não pode ser nulo") @DecimalMin(value = "0.01", message = "O valor original tem que ser maior que zero") Double valorOriginal,
			@NotNull(message = "O campo não pode ser nulo") LocalDate dataVencimento,
			@NotNull(message = "O campo não pode ser nulo") LocalDate dataPagamento) {
		this.nome = nome;
		this.valorOriginal = valorOriginal;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
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

	public ContasAPagar converteParaOModelcom(Double valorCorrigido, Integer diasDeAtraso) {
		return new ContasAPagar(this.nome, this.valorOriginal, this.dataVencimento, 
				this.dataPagamento, valorCorrigido, diasDeAtraso);
	}

}
