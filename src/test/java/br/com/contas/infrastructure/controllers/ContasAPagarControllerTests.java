package br.com.contas.infrastructure.controllers;

import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.DATA_PAGAMENTO_CONTA_1;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.NOME_CONTA_1;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.QUANTIDADE_DIAS_DE_ATRASO_CONTA_1;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.QUANTIDADE_DIAS_DE_ATRASO_CONTA_2;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.QUANTIDADE_DIAS_DE_ATRASO_CONTA_3;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.QUANTIDADE_DIAS_DE_ATRASO_CONTA_4;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.QUANTIDADE_DIAS_DE_ATRASO_CONTA_5;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.VALOR_CORRIGIDO_CONTA_1;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.VALOR_CORRIGIDO_CONTA_2;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.VALOR_CORRIGIDO_CONTA_3;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.VALOR_CORRIGIDO_CONTA_4;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.VALOR_CORRIGIDO_CONTA_5;
import static br.com.contas.infrastructure.controllers.ContasAPagarControllerHooks.VALOR_ORIGINAL_CONTA_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.contas.model.ContasAPagar;
import br.com.contas.model.dto.ContasAPagarListagemDTO;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class ContasAPagarControllerTests {
	
	private ContasAPagarControllerHooks contasAPagarContollerHooks;
	
	@Autowired
	public ContasAPagarControllerTests(ContasAPagarControllerHooks contasAPagarContollerHooks) {
		this.contasAPagarContollerHooks = contasAPagarContollerHooks;
	}
	
	@BeforeEach
	void init() {
		this.contasAPagarContollerHooks.limpaBaseDeDados();
	}
	
	@Test
	void listaTodasContas_semDadosNoBanco_deveRetornar404() {
		ResponseEntity<ContasAPagarListagemDTO[]> contas = this.contasAPagarContollerHooks
				.quandoRealizarAConsultaNoEndpointDeListarTodasAsContas();

		entaoOHttpStatusDeveSer(HttpStatus.NOT_FOUND, contas.getStatusCode());
		eOBodyDeveSerNull(contas.getBody());
	}
	
	@Test
	void listaTodasContas_comDadosNoBanco_deveRetornar200() {
		this.contasAPagarContollerHooks.dadoTenhaInformacaoNaBaseDeDados();
		
		ResponseEntity<ContasAPagarListagemDTO[]> contas = this.contasAPagarContollerHooks
				.quandoRealizarAConsultaNoEndpointDeListarTodasAsContas();

		List<ContasAPagarListagemDTO> listaDeContas = new ArrayList<ContasAPagarListagemDTO>();
		Collections.addAll(listaDeContas, contas.getBody());
		
		entaoOHttpStatusDeveSer(HttpStatus.OK, contas.getStatusCode());
		eOBodyDeveEstarPreenchido(listaDeContas);
	}
	
	@Test
	void incluiConta_comParametrosPreenchidosCorretamenteSemAtrasoDePagamento_deveRetornar201() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoCorretamenteNoEndpointDeInclusaoDeConta();
		
		List<ContasAPagar> contas = this.contasAPagarContollerHooks
				.quandoRealizarAConsultaAoBancoDeDados();

		entaoOHttpStatusDeveSer(HttpStatus.CREATED, retornoDoPreenchimento.getStatusCode());
		eORetornoDoBancoDeDadosDeveEstarPreenchido(this.contasAPagarContollerHooks.getContaIncluida(), contas,
				QUANTIDADE_DIAS_DE_ATRASO_CONTA_2, VALOR_CORRIGIDO_CONTA_2);
	}
	
	@Test
	void incluiConta_comParametrosPreenchidosCorretamenteComAtrasoDePagamentoDe2Dias_deveRetornar201() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoCorretamenteCom2DiasDeAtrasoNoPagamentoNoEndpointDeInclusaoDeConta();
		
		List<ContasAPagar> contas = this.contasAPagarContollerHooks
				.quandoRealizarAConsultaAoBancoDeDados();

		entaoOHttpStatusDeveSer(HttpStatus.CREATED, retornoDoPreenchimento.getStatusCode());
		eORetornoDoBancoDeDadosDeveEstarPreenchido(this.contasAPagarContollerHooks.getContaIncluida(), contas,
				QUANTIDADE_DIAS_DE_ATRASO_CONTA_3, VALOR_CORRIGIDO_CONTA_3);
	}
	
	@Test
	void incluiConta_comParametrosPreenchidosCorretamenteComAtrasoDePagamentoDe4Dias_deveRetornar201() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoCorretamenteCom4DiasDeAtrasoNoPagamentoNoEndpointDeInclusaoDeConta();
		
		List<ContasAPagar> contas = this.contasAPagarContollerHooks
				.quandoRealizarAConsultaAoBancoDeDados();

		entaoOHttpStatusDeveSer(HttpStatus.CREATED, retornoDoPreenchimento.getStatusCode());
		eORetornoDoBancoDeDadosDeveEstarPreenchido(this.contasAPagarContollerHooks.getContaIncluida(),contas,
				QUANTIDADE_DIAS_DE_ATRASO_CONTA_4, VALOR_CORRIGIDO_CONTA_4);
	}
	
	@Test
	void incluiConta_comParametrosPreenchidosCorretamenteComAtrasoDePagamentoDe6Dias_deveRetornar201() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoCorretamenteCom6DiasDeAtrasoNoPagamentoNoEndpointDeInclusaoDeConta();
		
		List<ContasAPagar> contas = this.contasAPagarContollerHooks
				.quandoRealizarAConsultaAoBancoDeDados();

		entaoOHttpStatusDeveSer(HttpStatus.CREATED, retornoDoPreenchimento.getStatusCode());
		eORetornoDoBancoDeDadosDeveEstarPreenchido(this.contasAPagarContollerHooks.getContaIncluida(),contas,
				QUANTIDADE_DIAS_DE_ATRASO_CONTA_5, VALOR_CORRIGIDO_CONTA_5);
	}
	
	@Test
	void incluiConta_comParametroNomePreenchidoComNulo_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoNomeComNuloNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroNomePreenchidoComVazio_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoNomeComVazioNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroValorOriginalPreenchidoComZero_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoValorOriginalComValorZeroNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroValorOriginalPreenchidoComNulo_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoValorOriginalComNuloNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroValorOriginalPreenchidoComFormatoErrado_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoValorOriginalComFormatoErradoNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroValorOriginalPreenchidoComVazio_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoValorOriginalComVazioNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroDataVencimentoPreenchidoComVazio_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoDataVencimentoComVazioNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroDataVencimentoPreenchidoComNulo_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoDataVencimentoComNuloNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroDataVencimentoPreenchidoComFormatoErrado_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoDataVencimentoComFormatoErradoNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroDataPagamentoPreenchidoComNulo_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoDataPagamentoComNuloNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroDataPagamentoPreenchidoComVazio_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoDataPagamentoComVazioNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	@Test
	void incluiConta_comParametroDataPagamentoPreenchidoComFormatoErrado_deveRetornar400() {
		ResponseEntity<String> retornoDoPreenchimento = this.contasAPagarContollerHooks
				.dadoOPreenchimentoDoCampoDataPagamentoComFormatoErradoNoEndpointDeInclusaoDeConta();
		
		entaoOHttpStatusDeveSer(HttpStatus.BAD_REQUEST, retornoDoPreenchimento.getStatusCode());
	}
	
	private void entaoOHttpStatusDeveSer(HttpStatus esperado, HttpStatus retornado) {
		assertEquals(esperado, retornado);
	}
	
	private void eOBodyDeveSerNull(ContasAPagarListagemDTO[] body) {
		assertNull(body);
	}
	
	private void eOBodyDeveEstarPreenchido(List<ContasAPagarListagemDTO> body) {
		assertNotNull(body);
		assertEquals(NOME_CONTA_1, body.get(0).getNome());
		assertEquals(VALOR_ORIGINAL_CONTA_1, body.get(0).getValorOriginal());
		assertEquals(DATA_PAGAMENTO_CONTA_1, body.get(0).getDataPagamento());
		assertEquals(QUANTIDADE_DIAS_DE_ATRASO_CONTA_1, body.get(0).getQuantidadeDeDiasDeAtraso());
		assertEquals(VALOR_CORRIGIDO_CONTA_1, body.get(0).getValorCorrigido());
	}
	
	private void eORetornoDoBancoDeDadosDeveEstarPreenchido(ContasAPagarInclusaoDTOTest contaPreenchida, List<ContasAPagar> contas,
			Integer diasDeAtraso, Double valorCorrigido) {
		assertNotNull(contas);
		assertEquals(contaPreenchida.getNome(), contas.get(0).getNome());
		assertEquals(Double.parseDouble(contaPreenchida.getValorOriginal()), contas.get(0).getValorOriginal());
		assertEquals(LocalDate.parse(contaPreenchida.getDataPagamento()), contas.get(0).getDataPagamento());
		assertEquals(LocalDate.parse(contaPreenchida.getDataVencimento()), contas.get(0).getDataVencimento());
		assertEquals(diasDeAtraso, contas.get(0).getDiasDeAtraso());
		assertEquals(valorCorrigido, contas.get(0).getValorCorrigido());
	}
	
}
