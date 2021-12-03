package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;

/**
 * Este servlet servira como a única entrada para a aplicação
 */
@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramAcao = request.getParameter("acao");

		HttpSession sessao = request.getSession();
		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
		if (ehUmaAcaoProtegida & usuarioNaoEstaLogado) {
			response.sendRedirect("entrada?acao=LoginForm");
			return;
		}

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

		// Usando o Padrão de projeto Reflection todo o codigo abaixo nao precisa.
//		if (paramAcao.equals("listaEmpresas")) {
//
//			ListaEmpresas acao = new ListaEmpresas();
//			nome = acao.executa(request, response);
//
//		} else if (paramAcao.equals("mostraEmpresa")) {
//
//			MostraEmpresa acao = new MostraEmpresa();
//			nome = acao.executa(request, response);
//
//		} else if (paramAcao.equals("alteraEmpresa")) {
//
//			AlteraEmpresa acao = new AlteraEmpresa();
//			nome = acao.executa(request, response);
//
//		} else if (paramAcao.equals("novaEmpresa")) {
//
//			NovaEmpresa acao = new NovaEmpresa();
//			nome = acao.executa(request, response);
//
//		} else if (paramAcao.equals("removeEmpresa")) {
//
//			RemoveEmpresa acao = new RemoveEmpresa();
//			nome = acao.executa(request, response);
//
//		} else if (paramAcao.equals("novaEmpresaForm")) {
//
//			NovaEmpresaForm acao = new NovaEmpresaForm();
//			nome = acao.executa(request, response);
//
//		}

	}

}
