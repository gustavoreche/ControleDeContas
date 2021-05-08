package br.com.contas.infrastructure.controllers;

import java.io.Serializable;


public class ContasAPagarInclusaoDTOTest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String valorOriginal;
	private String dataVencimento;
	private String dataPagamento;
	
	public ContasAPagarInclusaoDTOTest(String nome, String valorOriginal, 
			String dataVencimento, String dataPagamento) {
		this.nome = nome;
		this.valorOriginal = valorOriginal;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public String getNome() {
		return nome;
	}

	public String getValorOriginal() {
		return valorOriginal;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

//	public ContasAPagar converteParaOModelcom(Double valorCorrigido, Integer diasDeAtraso) {
//		return new ContasAPagar(this.nome, this.valorOriginal, this.dataVencimento, 
//				this.dataPagamento, valorCorrigido, diasDeAtraso);
//	}

}
