package br.com.contas.infrastructure.service;

import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.contas.application.multa.Multa;
import br.com.contas.application.repositorie.ContasAPagarRepository;
import br.com.contas.model.dto.ContasAPagarInclusaoDTO;
import br.com.contas.model.dto.ContasAPagarListagemDTO;

@Service
public class ContasAPagarService {

	private ContasAPagarRepository contasAPagarRepository;
	private Multa multa;

	@Autowired
	public ContasAPagarService(ContasAPagarRepository contasAPagarRepository, 
			@Qualifier("ateTres") Multa multa) {
		this.contasAPagarRepository = contasAPagarRepository;
		this.multa = multa;
	}
	
	public List<ContasAPagarListagemDTO> listaTodasContas() {
		return ContasAPagarListagemDTO.converte(contasAPagarRepository.findAll());
	}
	
	public void inclui(ContasAPagarInclusaoDTO conta) {
		double valorCorrigido = 0;
		int diasDeAtraso = 0;
		if(conta.getDataPagamento().isAfter(conta.getDataVencimento())) {
			diasDeAtraso = Period.between(conta.getDataVencimento(), conta.getDataPagamento()).getDays();
			valorCorrigido = this.multa.valorComMulta(diasDeAtraso, conta.getValorOriginal());
		}
		
		contasAPagarRepository.save(conta.converteParaOModelcom(valorCorrigido, diasDeAtraso));
	}

}
