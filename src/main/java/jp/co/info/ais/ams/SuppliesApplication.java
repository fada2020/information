package jp.co.info.ais.ams;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.info.ais.ams.filter.UserFilter;

@SpringBootApplication
@ServletComponentScan
public class SuppliesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuppliesApplication.class, args);
	}

	@Configuration
	public class WebConfig implements WebMvcConfigurer, WebMvcRegistrations {
		/**
		 * URLにフィルター連結
		 * @return FilterRegistrationBean<DashboardFilter>
		 */
		@Bean
		public FilterRegistrationBean<UserFilter> UserFilter() {
			FilterRegistrationBean<UserFilter> bean = new FilterRegistrationBean<>();
			bean.setFilter(new UserFilter());
			bean.setUrlPatterns(Arrays.asList("/dashboard","/rental","/history","/asset","/codemaster","/codedetail","/error","/loginprocess"));
			return bean;
		}

	}
}
