package br.com.contas.application.multa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("maisDeCinco")
public class MultaMaisDeCincoDiasDeAtraso implements Multa {
	
	private CalculadorDeMultaEJuros calculadorDeMultaEJuros;
	
	@Autowired
	public MultaMaisDeCincoDiasDeAtraso(CalculadorDeMultaEJuros calculadorDeMultaEJuros) {
		this.calculadorDeMultaEJuros = calculadorDeMultaEJuros;
	}

	@Override
	public Double valorComMulta(int dias, Double valor) {
		if(dias > 5) {
			return this.calculadorDeMultaEJuros.calcula(valor, dias, 5, 0.3);
		}
		return valor;
	}

}
