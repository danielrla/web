package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acao.AlteraEmpresa;
import br.com.alura.gerenciador.acao.ListaEmpresas;
import br.com.alura.gerenciador.acao.MostraEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresa;
import br.com.alura.gerenciador.acao.RemoveEmpresa;

/**
 * Este servlet servira como a única entrada para a aplicação
 */
@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao.equals("listaEmpresas")) {

			ListaEmpresas listaEmpresas = new ListaEmpresas();
			listaEmpresas.executa(request, response);

		} else if (acao.equals("mostraEmpresa")) {

			MostraEmpresa mostraEmpresa = new MostraEmpresa();
			mostraEmpresa.executa(request, response);

		} else if (acao.equals("alteraEmpresa")) {
			
			AlteraEmpresa alteraEmpresa = new AlteraEmpresa();
			alteraEmpresa.executa(request, response);

		} else if (acao.equals("novaEmpresa")) {

			NovaEmpresa novaEmpresa = new NovaEmpresa();
			novaEmpresa.executa(request, response);

		} else if (acao.equals("removeEmpresa")) {

			RemoveEmpresa removeEmpresa = new RemoveEmpresa();
			removeEmpresa.executa(request, response);
		}

	}

}
