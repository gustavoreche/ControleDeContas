package br.com.contas.infrastructure.controllers;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.contas.infrastructure.service.ContasAPagarService;
import br.com.contas.model.dto.ContasAPagarInclusaoDTO;
import br.com.contas.model.dto.ContasAPagarListagemDTO;

@RestController
@RequestMapping("/contas")
public class ContasAPagarController {
	
	private ContasAPagarService contasAPagarService;
	
	@Autowired
	public ContasAPagarController(ContasAPagarService contasAPagarService) {
		this.contasAPagarService = contasAPagarService;
	}
	
	@GetMapping()
	public ResponseEntity<List<ContasAPagarListagemDTO>> listaTodasContas(){
		List<ContasAPagarListagemDTO> listaTodasContas = this.contasAPagarService.listaTodasContas();
		if(Objects.isNull(listaTodasContas) || listaTodasContas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ContasAPagarListagemDTO>>(listaTodasContas, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<String> incluiConta(@RequestBody @Valid ContasAPagarInclusaoDTO conta){
		this.contasAPagarService.inclui(conta);
		return new ResponseEntity<String>("Conta criada", HttpStatus.CREATED);
	}

}
