package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mapping.User;
import util.ObjectSessionNames;

/**
 * Servlet implementation class LogIn
 */
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		/*
		 * If you're trying to get to login page you'll check the session if user != null
		 * you'll get forwarded to the main site.
		*/
		HttpSession session = request.getSession(false);
		if(session != null) {
			user = (User) session.getAttribute(ObjectSessionNames.USER);
		}
		if(user != null) {
			response.sendRedirect("LogOn");
		}else {
			String message = (String) request.getAttribute("message");
			
			if(message == null) message = "";
			out.println(
					"<html>\n" + 
					"<head>\n" + 
					"<title></title>\n" + 
					"</head>\n" + 
					"\n" + 
					"\n" + 
					"<body>\n" + 
					"	<form action=\"LogOn\" method=post>\n" + 
					"		<div align=\"center\">\n" + 
					"			<h2>Login</h2>\n" + 
					"			<font \n" + 
					"			System.out.println(\"Sabrosano\");color=\"red\"> "+
					"					"+message+"\n" + 
					"			</font> <br /> <br />\n" + 
					"		</div>\n" + 
					"		<div class=\"container\" align=\"center\">\n" + 
					"			<div align=\"center\">\n" + 
					"				<label for=\"uname\"><b>Username</b></label> <input type=\"text\"\n" + 
					"					placeholder=\"Enter Username\" name=\"uname\" required>\n" + 
					"			</div>\n" + 
					"\n" + 
					"			<div align=\"center\">\n" + 
					"				<label for=\"psw\"><b>Password</b></label> <input type=\"password\"\n" + 
					"					placeholder=\"Enter Password\" name=\"psw\" required>\n" + 
					"			</div>\n" + 
					"			<button type=\"submit\">Log In</button>\n" + 
					"		</div>\n" + 
					"	</form>\n" + 
					"</body>\n" + 
					"</html>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
