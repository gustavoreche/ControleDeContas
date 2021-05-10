package br.com.contas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "regra_de_multa_por_conta")
public class RegraDeMultaPorConta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer diasDeAtraso;
	private Double valorOriginal;
	private Double porcentagemMulta;
	private Double valorComMulta;
	private Double porcentagemJuros;
	private Double valorJurosPorDia;
	private Double valorCorrigido;
	@OneToOne(fetch = FetchType.LAZY)
	private ContasAPagar contasAPagar;
	
	public RegraDeMultaPorConta() {
	}
	
	public RegraDeMultaPorConta(Integer diasDeAtraso, Double valorOriginal, Double porcentagemMulta,
			Double valorComMulta, Double porcentagemJuros, Double valorJurosPorDia, Double valorCorrigido) {
		this.diasDeAtraso = diasDeAtraso;
		this.valorOriginal = valorOriginal;
		this.porcentagemMulta = porcentagemMulta;
		this.valorComMulta = valorComMulta;
		this.porcentagemJuros = porcentagemJuros;
		this.valorJurosPorDia = valorJurosPorDia;
		this.valorCorrigido = valorCorrigido;
	}

	public Integer getId() {
		return id;
	}

	public Integer getDiasDeAtraso() {
		return diasDeAtraso;
	}

	public Double getValorCorrigido() {
		return valorCorrigido;
	}

	public void vinculaAConta(ContasAPagar contaAPagar) {
		this.contasAPagar = contaAPagar;
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public Double getPorcentagemMulta() {
		return porcentagemMulta;
	}

	public Double getValorComMulta() {
		return valorComMulta;
	}

	public Double getPorcentagemJuros() {
		return porcentagemJuros;
	}

	public Double getValorJurosPorDia() {
		return valorJurosPorDia;
	}

	public ContasAPagar getContasAPagar() {
		return contasAPagar;
	}
	
}
