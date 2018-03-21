package ch.globaz.tmmas.rentesservice.application.api.web;


import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class MDCRequestFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		try{
			MDC.put("correlationId", UUID.randomUUID().toString());
			filterChain.doFilter(servletRequest,servletResponse);
		}
		finally{

			MDC.clear();

		}
	}

	@Override
	public void destroy() {

	}
}
