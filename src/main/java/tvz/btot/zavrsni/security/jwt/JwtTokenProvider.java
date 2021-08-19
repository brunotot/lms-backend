package tvz.btot.zavrsni.security.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.errorhandling.ApiException;
import tvz.btot.zavrsni.infrastructure.repository.UserRepository;
import tvz.btot.zavrsni.infrastructure.service.impl.UserDetailsServiceImpl;
import tvz.btot.zavrsni.infrastructure.utils.ObjectMapperUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Base64.Encoder;

@Component
public class JwtTokenProvider {
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String BAD_TOKEN_TITLE = "Bad token";
    private static final String EXPIRED_OR_INVALID_TOKEN_MESSAGE = "Expired or invalid JWT token";
    private static String SECRET_KEY_ENCODED;

    private final long validityInMilliseconds;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserRepository userRepository;

    public JwtTokenProvider(final UserDetailsServiceImpl userDetailsServiceImpl,
                            final @Value("${security.jwt.token.secretKey}") String secretKey,
                            final @Value("${security.jwt.token.expireLength}") long validityInMilliseconds,
                            final UserRepository userRepository) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        SECRET_KEY_ENCODED = encodeToBase64(secretKey);
        this.validityInMilliseconds = validityInMilliseconds;
        this.userRepository = userRepository;
    }

    private String encodeToBase64(final String decodedString) {
        final byte[] decodedStringBytes = decodedString.getBytes();
        final Encoder base64Encoder = Base64.getEncoder();
        return base64Encoder.encodeToString(decodedStringBytes);
    }

    public String createToken(final User user) {
        List<SimpleGrantedAuthority> authorities = user.getAuthorities();
        Date dateNow = new Date();
        Date dateValidity = new Date(dateNow.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .claim("auth", authorities)
                .claim("user", user)
                .setIssuedAt(dateNow)
                .setExpiration(dateValidity)
                .signWith(SIGNATURE_ALGORITHM, SECRET_KEY_ENCODED)
                .compact();
    }

//    public User getUser(final String token) {
//        final String username = getUsername(token);
//        return userRepository.findByUsername(username);
//    }

    public static User getUser(final String token) {
        LinkedHashMap userLinkedMap = Jwts
                .parser()
                .setSigningKey(SECRET_KEY_ENCODED)
                .parseClaimsJws(token)
                .getBody()
                .get("user", LinkedHashMap.class);
        userLinkedMap.remove("authorities");
        return ObjectMapperUtils.getMapper().convertValue(userLinkedMap, User.class);
    }

    public Authentication getAuthentication(final String token) {
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(final String token) {
        return (String) Jwts
                .parser()
                .setSigningKey(SECRET_KEY_ENCODED)
                .parseClaimsJws(token)
                .getBody()
                .get("user", Map.class)
                .get("username");
    }

    public String resolveToken(final HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public void validateToken(final String token) {
        try {
            Jwts
                .parser()
                .setSigningKey(SECRET_KEY_ENCODED)
                .parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                BAD_TOKEN_TITLE,
                EXPIRED_OR_INVALID_TOKEN_MESSAGE,
                e
            );
        }
    }
}
