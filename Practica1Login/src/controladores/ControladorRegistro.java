package controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logicaNegocio.GestionArchivoUsuario;

/**
 * Servlet implementation class ControladorLogin
 */

@WebServlet("/controladorLogin")
public class ControladorRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int numeroPeticion = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorRegistro() {
		super();
		System.out.println("Se genera la instancia del servlet");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out
				.println("Ejecucion del metodo init() se invoca la instancia del servlet");
		System.out.println("Valor de la variable numeroPeticion"
				+ numeroPeticion);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Me mataste Dx");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Nombre del usuario: "
				+ request.getParameter("txtUsuario"));
		System.out.println("Contrasena : "
				+ request.getParameter("txtPassword"));
		System.out.println("Ip remota: " + request.getRemoteAddr());
		System.out.println("ID de sesion " + request.getSession().getId());
		System.out.println("Navegador: " + request.getHeader("User-Agent"));
		System.out.println("---------- noPeticion: " + numeroPeticion
				+ "--------------");
		System.out.println("--------------------------------------");
		numeroPeticion++;
		response.sendRedirect("bienvenido.html");

	}

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GestionArchivoUsuario manejoBusqueda = new GestionArchivoUsuario();
		String user = request.getParameter("txtUsuario");
		String password = request.getParameter("txtPassword");
		String eMail = request.getParameter("txtEMail");
		String nombre = request.getParameter("txtNombre");
		String apellidos = request.getParameter("txtApellidos");
		String mensaje = "---------------------\nError. Campo(s) nulo(s)\n-----------------------";
		if (user != null && password != null && eMail != null && nombre != null
				&& apellidos != null) {
			try {
				if(manejoBusqueda.buscarUsuario(user, password)){
					System.out.println("Este usuario ya ha sido registrado, introduzca otro.");
				}
				else{
					manejoBusqueda.registrarUsuario(user, password, nombre, apellidos, eMail);
				}
			} catch (Exception e) {
				System.out.println("Error al buscar usuario.");
				e.printStackTrace();
			}
		}
		mensaje = "-------------------------------\nRegistro exitoso!\n-----------------------------------";
		respuesta(response, mensaje);

	}

	private void respuesta(HttpServletResponse resp, String msg)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<t1>" + msg + "</t1>");
		out.println("</body>");
		out.println("</html>");
	}

	private boolean tieneNombreYDominio(String Email) {
		String[] tokens = Email.split("@");
		return tokens.length == 2;
	}
}