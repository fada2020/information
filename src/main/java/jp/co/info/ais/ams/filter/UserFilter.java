package jp.co.info.ais.ams.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(UserFilter.class);
	/**
	 * ログインしないで、ダッシュボード進入禁止
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		if (servletRequest instanceof HttpServletRequest) {
			String url = ((HttpServletRequest) servletRequest).getRequestURI().toString();
			String queryString = ((HttpServletRequest) servletRequest).getQueryString();
			logger.debug("url::" + url);
			logger.debug("url::" + queryString);
		}

		Object loginId = ((HttpServletRequest) servletRequest).getSession().getAttribute("id");
		System.out.println(loginId);
		if( loginId == null ) {
			HttpServletResponse res = (HttpServletResponse)servletResponse;
	        res.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	        res.setHeader("Location", "/");
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

}