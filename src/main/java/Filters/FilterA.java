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

/**
 * Servlet Filter implementation class FilterA
 */
public class FilterA implements Filter {
	Logger loggers = null;

    /**
     * Default constructor. 
     */
    public FilterA() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM//yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		
		String logg = req.getRemoteAddr() +" | "+ sdf.format(date) + " | "+ sdf2.format(date) + " | "+ req.getMethod() + " | "+ req.getContextPath()+ req.getServletPath();
		System.out.println(logg);
		
		loggers = Logger.getAnonymousLogger();
		loggers.info(logg);
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
