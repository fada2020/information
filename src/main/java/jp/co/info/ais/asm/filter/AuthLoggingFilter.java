package jp.co.info.ais.asm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class AuthLoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        if (session != null) {
            MDC.put("sessionId", session.getId());
        }
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove("sessionId");
        }
	}

}