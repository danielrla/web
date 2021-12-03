package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

/**
 * Servlet implementation class ListaEmpresasService
 */
@WebServlet("/empresas")
public class ListaEmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Empresa> listaEmpresas = new Banco().getEmpresas();

		String valor = request.getHeader("Accept");

		if (valor.contains("xml")) {
			response.setContentType("application/xml");
			response.getWriter().print(retornaXml(listaEmpresas));
		} else if (valor.contains("json")) {
			response.setContentType("application/json");
			response.getWriter().print(retornaJson(listaEmpresas));
		} else {
			response.setContentType("application/json");
			response.getWriter().print("{'message':'no content'}");
		}

	}

	private String retornaJson(List<Empresa> listaEmpresas) {

		Gson gson = new Gson();
		String json = gson.toJson(listaEmpresas);

		return json;
	}

	private String retornaXml(List<Empresa> listaEmpresas) {

		XStream xStream = new XStream();
		xStream.alias("empresa", Empresa.class);
		String xml = xStream.toXML(listaEmpresas);

		return xml;
	}

}
