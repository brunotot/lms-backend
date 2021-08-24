package tvz.btot.zavrsni.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import tvz.btot.zavrsni.domain.AllowableUrlMethods;
import tvz.btot.zavrsni.infrastructure.service.impl.UserDetailsServiceImpl;
import tvz.btot.zavrsni.security.jwt.JwtTokenFilterConfigurer;
import tvz.btot.zavrsni.security.jwt.JwtTokenProvider;

import java.util.Arrays;
import java.util.List;

import static tvz.btot.zavrsni.infrastructure.utils.AnnotationUtils.getAllowableUrlMethodsListForAllControllerClassNames;
import static tvz.btot.zavrsni.infrastructure.utils.Constants.PASSWORD_ENCODER;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final List<String> allowedOrigins;
    private final List<String> allowedHeaders;
    private final List<String> allowedMethods;
    private final boolean allowCredentials;

    public SecurityConfig(final UserDetailsServiceImpl userDetailsServiceImpl,
                          final JwtTokenProvider jwtTokenProvider,
                          final @Value("${security.allowed-origins}") String allowedOrigins,
                          final @Value("${security.allowed-methods}") String allowedMethods,
                          final @Value("${security.allowed-headers}") String allowedHeaders,
                          final @Value("${security.allow-credentials}") boolean allowCredentials) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtTokenProvider = jwtTokenProvider;
        this.allowedOrigins = Arrays.asList(allowedOrigins.split(";"));
        this.allowedHeaders = Arrays.asList(allowedHeaders.split(";"));
        this.allowedMethods = Arrays.asList(allowedMethods.split(";"));
        this.allowCredentials = allowCredentials;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(final WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers("/socket/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui/**")
                .antMatchers(HttpMethod.GET, "/announcement-type")
                .antMatchers(HttpMethod.GET, "/course")
                .antMatchers(HttpMethod.GET, "/course/{courseId}")
                .antMatchers(HttpMethod.GET, "/role")
                .antMatchers(HttpMethod.GET, "/subject")
                .antMatchers(HttpMethod.GET, "/subject/{subjectId}")
                .antMatchers(HttpMethod.POST, "/user/auth");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .apply(new JwtTokenFilterConfigurer(jwtTokenProvider))
            .and()
                .cors();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImpl);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PASSWORD_ENCODER;
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(this.userDetailsServiceImpl);
        return authenticationProvider;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(allowedOrigins);
        config.setAllowedHeaders(allowedHeaders);
        config.setAllowedMethods(allowedMethods);
        config.setExposedHeaders(allowedHeaders);
        config.setAllowCredentials(allowCredentials);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
