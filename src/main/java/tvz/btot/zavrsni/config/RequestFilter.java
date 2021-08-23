package tvz.btot.zavrsni.config;

import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tvz.btot.zavrsni.security.jwt.JwtTokenProvider;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class RequestFilter extends OncePerRequestFilter {
    final JwtTokenProvider jwtTokenProvider;

    public RequestFilter(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest,
                                    final @NonNull HttpServletResponse httpServletResponse,
                                    final @NonNull FilterChain filterChain) throws IOException, ServletException {
        final String bearerCompleteToken = httpServletRequest.getHeader("authorization");
        String token = "";
        if (bearerCompleteToken != null && bearerCompleteToken.startsWith("Bearer ")) {
            token = bearerCompleteToken.substring(7);
        }
        SecurityContextUtils.setUser(token, jwtTokenProvider);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
