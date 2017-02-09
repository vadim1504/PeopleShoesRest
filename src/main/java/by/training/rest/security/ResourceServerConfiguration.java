package by.training.rest.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;


@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
		public void configure(HttpSecurity http) throws Exception {
		http.
		anonymous().disable()
		.requestMatchers().antMatchers("/**")
		.and().authorizeRequests()
				.antMatchers (HttpMethod.GET, "/role").permitAll()
				.antMatchers (HttpMethod.GET, "/users").hasRole("ADMIN")
				.antMatchers (HttpMethod.POST, "/brand").hasRole("ADMIN")
				.antMatchers (HttpMethod.POST, "/cart").hasRole("USER")
				.antMatchers (HttpMethod.POST, "/color").hasRole("ADMIN")
				.antMatchers (HttpMethod.POST, "/material").hasRole("ADMIN")
				.antMatchers (HttpMethod.POST, "/menCollection").hasRole("ADMIN")
				.antMatchers (HttpMethod.POST, "/shoes").hasRole("ADMIN")
				.antMatchers (HttpMethod.POST, "/size").hasRole("ADMIN")
				.antMatchers (HttpMethod.PUT, "/brand/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.PUT, "/cart/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.PUT, "/color/*").hasRole("ADMIN")
				.antMatchers (HttpMethod.PUT, "/material/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.PUT, "/menCollection/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.PUT, "/shoes/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.PUT, "/size/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.PUT, "/users/**").hasRole("USER")
				.antMatchers (HttpMethod.DELETE, "/brand/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.DELETE, "/cart/**").hasRole("USER")
				.antMatchers (HttpMethod.DELETE, "/color/*").hasRole("ADMIN")
				.antMatchers (HttpMethod.DELETE, "/material/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.DELETE, "/menCollection/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.DELETE, "/shoes/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.DELETE, "/size/**").hasRole("ADMIN")
				.antMatchers (HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
				.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}