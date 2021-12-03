package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acao.Acao;

//@WebFilter("/entrada") // Foi incluido no webxml para garantir a ordem de chamada do Filter
public class ControladorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		
		System.out.println("ControladorFilter");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String paramAcao = request.getParameter("acao");

		// Padrão de projeto Reflection
		// O nome deve ser igual ao da classe Ex: MostraEmpresa
		String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao;
		Class classe;
		String nome;
		try {
			classe = Class.forName(nomeDaClasse);
			Object obj = classe.newInstance();
			Acao acao = (Acao) obj;
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}

		String[] tipoControle = nome.split(":");
		if (tipoControle[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoControle[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoControle[1]);
		}
		
		// Nao precisa por que ele será o ultimo a ser chamado.
		// chain.doFilter(request, response);
	}

}
