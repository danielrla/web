package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		Banco banco = new Banco();
		Usuario usuario = banco.validaUsuario(login, senha);
		if (usuario != null) {
			System.out.println("Usuario " + login + " fez login com sucesso.");
			return "redirect:entrada?acao=ListaEmpresas";
		} else {
			System.out.println("Usuario inv�lido.");
			return "redirect:entrada?acao=LoginForm";
		}

	}

}
