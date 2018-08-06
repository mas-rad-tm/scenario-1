package ch.globaz.tmmas.rentesservice.dossier.api.web.filter;


import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * Filtre pour toutes les requêtes. Permet d'appliquer l'id de correlation
 * pour toutes les requêtes entrantes.
 */
@Component
public class MDCRequestFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

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
	public void destroy() {}

}