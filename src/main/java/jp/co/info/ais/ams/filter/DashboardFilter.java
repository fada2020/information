package jp.co.info.ais.asm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DashboardFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(DashboardFilter.class);
	/**
	 * init filter
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("init filter!!");
	}
	/**
	 * dofilter : ログインしないで、ダッシュボード進入禁止
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		if (servletRequest instanceof HttpServletRequest) {
			String url = ((HttpServletRequest) servletRequest).getRequestURI().toString();
			String queryString = ((HttpServletRequest) servletRequest).getQueryString();
			logger.info("url::" + url);
			logger.info("url::" + queryString);
		}

		Object loginId = ((HttpServletRequest) servletRequest).getSession().getAttribute("id");
		System.out.println(loginId);
		if( loginId == null ) {
			HttpServletResponse res = (HttpServletResponse)servletResponse;
	        res.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
	        res.setHeader("Location", "/");
		}
		System.out.println("Do filter");
		filterChain.doFilter(servletRequest, servletResponse);
	}
	/**
	 * destroy filter
	 */
	@Override
	public void destroy() {
		logger.info("destroy filter!");
	}

}