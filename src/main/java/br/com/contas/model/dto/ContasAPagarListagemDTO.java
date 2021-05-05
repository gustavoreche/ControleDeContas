package br.com.contas.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.contas.model.ContasAPagar;

public class ContasAPagarListagemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Double valorOriginal;
	private Double valorCorrigido;
	private Integer quantidadeDeDiasDeAtraso;
	private LocalDate dataPagamento;

	public ContasAPagarListagemDTO(ContasAPagar conta) {
		this.nome = conta.getNome();
		this.valorOriginal = conta.getValorOriginal();
		this.valorCorrigido = conta.getValorCorrigido();
		this.quantidadeDeDiasDeAtraso = conta.getDiasDeAtraso();
		this.dataPagamento = conta.getDataPagamento();
	}

	public String getNome() {
		return nome;
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public Double getValorCorrigido() {
		return valorCorrigido;
	}

	public Integer getQuantidadeDeDiasDeAtraso() {
		return quantidadeDeDiasDeAtraso;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public static List<ContasAPagarListagemDTO> converte(List<ContasAPagar> listaDeContas) {
		return listaDeContas.stream()
				.map(conta -> new ContasAPagarListagemDTO(conta))
				.collect(Collectors.toList());
	}

}
