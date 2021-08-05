package tvz.btot.zavrsni.security;

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
import tvz.btot.zavrsni.infrastructure.service.impl.UserDetailsServiceImpl;

import java.util.List;

import static tvz.btot.zavrsni.infrastructure.utils.Constants.PASSWORD_ENCODER;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String CORS_CONFIGURATION_PATTERN = "/**";
    private static final boolean ALLOW_CREDENTIALS = true;
    private static final String ALL_HEADERS = "*";
    private static final String ALL_METHODS = "*";
    private static final List<String> ALLOWED_ORIGINS = List.of(
            "http://localhost:4200",
            "http://192.168.1.6:4200");

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public SecurityConfig(final UserDetailsServiceImpl userDetailsServiceImpl,
                          final JwtTokenProvider jwtTokenProvider) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(final WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers("/user/test")
                .antMatchers(HttpMethod.GET, "/subject")
                .antMatchers(HttpMethod.GET, "/course")
                .antMatchers(HttpMethod.POST, "/user/auth")
                .antMatchers("/socket/**");
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
        config.setAllowedOrigins(ALLOWED_ORIGINS);
        config.addAllowedHeader(ALL_HEADERS);
        config.addAllowedMethod(ALL_METHODS);
        config.addExposedHeader(ALL_HEADERS);
        config.setAllowCredentials(ALLOW_CREDENTIALS);
        source.registerCorsConfiguration(CORS_CONFIGURATION_PATTERN, config);
        return new CorsFilter(source);
    }

}
