package servletsPrivados;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.SQLConnector;

@WebServlet("Ejercicio5")

/**
 * Servlet implementation class Ejercicio5
 */
public class Ejercicio5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ejercicio5() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SQLConnector.conexion();
		String sql = "SELECT person.tx_first_name, person.tx_last_name_a, "
				+ "person.tx_last_name_b, person.tx_curp, person.fh_birth, users.tx_login FROM users, person WHERE users.id_user = person.id_person";
		ResultSet rs = SQLConnector.ejecutaQ(sql);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<table style=\"width:100%\">\n" + "  <tr>\n" + "    <th>First Name</th>\n"
				+ "    <th>Lastname</th>\n" + "    <th>Second Lastname</th>\n" + "    <th>CURP</th>\n"
				+ "    <th>Birthday</th>\n" + "    <th>Nickname</th>\n" + "  </tr>");
		try {
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getString(1) + "</td>\n" + "    <td>" + rs.getString(2) + "</td>\n" + "    <td>"
						+ rs.getString(3) + "</td>\n" + "    <td>" + rs.getString(4) + "</td>\n" + "    <td>"
						+ rs.getString(5) + "</td>\n" + "    <td>" + rs.getString(6) + "</td>");
				out.println("</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</table> ");
		out.println("<form action=\"formulario.jsp\" method=post>\n" + "		<button type=\"submit\">New</button>\n"
				+ "	</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String name = request.getParameter("uname");
		String lname = request.getParameter("lname");
		String lname2 = request.getParameter("lname2");

		String curp = request.getParameter("curp");

		String bd = request.getParameter("bd");
		String login = request.getParameter("login");
		String pw = request.getParameter("pw");

		SQLConnector.conexion();
		ResultSet rs = SQLConnector.ejecutaQ("SELECT max(id_person) FROM person;");

		String sql = "insert into person(tx_first_name, tx_last_name_a, tx_last_name_b, tx_curp, fh_birth) "
				+ "values ('" + name + "', '" + lname + "', '" + lname2 + "', '" + curp + "', " + "to_date('" + bd
				+ "','dd/MM/yyyy'));";
		SQLConnector.ejecuta(sql);

		int us = 0;
		try {
			rs.next();
			us = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		sql = "insert into users(id_user, tx_login, tx_password) " + "values (" + us + ",'" + login + "', '" + pw
				+ "');";
		SQLConnector.ejecuta(sql);

		doGet(request, response);
	}

}
