package br.com.contas.application.multa;

import org.springframework.stereotype.Component;

@Component
public class CalculadorDeMultaEJuros {
	
	public Double calcula(Double valor, int dias, 
			double porcentagemMulta, double porcentagemJuros) {
		valor = valor + ((valor * porcentagemMulta) / 100);
		double valorTotalJuros = ((valor * porcentagemJuros) / 100) * dias;
		return valor + valorTotalJuros;
	}

}
