package tvz.btot.zavrsni.security.jwt;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import tvz.btot.zavrsni.infrastructure.errorhandling.ApiException;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(final @NonNull HttpServletRequest httpServletRequest,
                                    final @NonNull HttpServletResponse httpServletResponse,
                                    final @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = jwtTokenProvider.resolveToken(httpServletRequest);
            SecurityContextUtils.SOCKET_TOKEN = token;
            jwtTokenProvider.validateToken(token);
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (final ApiException ex) {
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(ex.getProblem().getStatus(), ex.getMessage());
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
