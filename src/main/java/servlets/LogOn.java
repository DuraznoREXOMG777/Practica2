package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class LogOn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("uname");
		String psw = request.getParameter("psw");
		
		String sql = "SELECT * FROM users WHERE tx_login = '"+name+"' AND tx_password = '"+psw+"'";
		ResultSet rs = SQLConnector.ejecutaQ(sql);
		try {
			if(rs.next()) {
				sql = "SELECT person.tx_first_name, person.tx_last_name_a, person.tx_last_name_b FROM users, person WHERE tx_login = '"+name+"' AND users.id_user = person.id_person";
				ResultSet rs2 = SQLConnector.ejecutaQ(sql);
				if(rs2.next()) {
					
					String nombre = rs2.getString(1)+" "+rs2.getString(2) + " " + rs2.getString(3);
					
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE html>\n" + 
							"<html>\n" + 
							"<head>\n" + 
							"	<title></title>\n" + 
							"</head>\n" + 
							"<body>\n" + 
							"	<div align=\"center\">\n" + 
							"		<h1>Welcome</h1>\n" + 
							"		<h4> "+ nombre +"</h4>\n" + 
							"	</div>\n" + 
							"	\n" + 
							"</body>\n" + 
							"</html>");
					
					out.close();
				}
				
				
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("LogIn");
				request.setAttribute("message", "Username and / or password are wrong");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
