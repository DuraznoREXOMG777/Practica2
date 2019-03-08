package Filters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Filter2
 */
public class Filter2 implements Filter {
	Logger loggers = null;

	/**
	 * Default constructor.
	 */
	public Filter2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean flag = false;
		String permission = "ALLOWED";
		String[] privates = {"Ejercicio5", 
				"index.jsp"};
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String[] uri = req.getRequestURI().split("([/])");
		
		for(String ur: uri) {
			if(ur.equals("interfaces"))
					flag = true;
		}
		for(String priv: privates) {
			if(uri[uri.length-1].equals(priv)) {
				flag = true;
			}
		}
		
		
		if(flag) {
			permission = "NOT ALLOWED";
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(404);
		}

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM//yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

		String logg = req.getRemoteAddr() + " | " + sdf.format(date) + " | " + sdf2.format(date) + " | "
				+ req.getMethod() + " | " + req.getContextPath() + req.getServletPath() + " | "+permission;

		Logger.getAnonymousLogger().info(logg);
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
