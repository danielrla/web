package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acao.Acao;

/**
 * 
 * @author daniel
 * 
 *         Classe que controla o fluxo de navegacao e dados do formulário.
 *
 */
public class NovaEmpresaForm implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return "forward:formNovaEmpresa.jsp";
	}
}
