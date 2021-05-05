package br.com.contas.infrastructure.configuration.handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.contas.model.dto.ErroDeFormularioDTO;

@RestControllerAdvice
public class ErroConversaoDeCampoHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErroDeFormularioDTO handle(HttpMessageNotReadableException exception) {
		return simplificaMensagemDeErro(pegaCampo(exception.getCause().getMessage()));
	}

	private ErroDeFormularioDTO simplificaMensagemDeErro(String campo) {
		return new ErroDeFormularioDTO(campo, "Erro no formato do campo");
	}
	
	private String pegaCampo(String mensagemDeErro) {
		final Pattern pattern = Pattern.compile("(?<=\\[\")([^\"\\]]+)", Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(mensagemDeErro);
        if (matcher.find()) {
        	return matcher.group(0);        	
        }
		return "Não foi possível encontrar o campo na mensagem de erro"; 		
	}

}
