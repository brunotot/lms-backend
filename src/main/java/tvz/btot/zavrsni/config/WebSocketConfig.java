package tvz.btot.zavrsni.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import tvz.btot.zavrsni.security.jwt.JwtTokenProvider;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final String APPLICATION_DESTINATION_PREFIX = "/app";
    private static final String SIMPLE_BROKER_DESTINATION_PREFIX = "/";
    private static final String AUTH_HEADER_NAME = "authorization";

    private final String[] frontendUrls;
    private final JwtTokenProvider tokenProvider;

    public WebSocketConfig(final JwtTokenProvider tokenProvider,
                           final @Value("${security.allowed-origins}") String frontendUrl) {
        this.tokenProvider = tokenProvider;
        this.frontendUrls = frontendUrl.split(";");
    }

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes(APPLICATION_DESTINATION_PREFIX);
        config.enableSimpleBroker(SIMPLE_BROKER_DESTINATION_PREFIX);
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/socket").setAllowedOrigins(frontendUrls);
        registry.addEndpoint("/socket").setAllowedOrigins(frontendUrls).withSockJS();
    }

    @Override
    public void configureClientInboundChannel(final ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(final Message<?> message, final MessageChannel channel) {
                SecurityContextUtils.SOCKET_TOKEN = "";
                final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                assert accessor != null;
                StompCommand cmd = accessor.getCommand();
                if (StompCommand.SEND == cmd || StompCommand.CONNECT == cmd) {
                    String token = "";
                    List<String> nativeHeaderStringList = accessor.getNativeHeader(AUTH_HEADER_NAME);
                    if (nativeHeaderStringList != null) {
                        token = String.join(Strings.EMPTY, nativeHeaderStringList);
                    }
                    SecurityContextUtils.SOCKET_TOKEN = token;
                    // TODO: Error handling
                    tokenProvider.validateToken(token);
                }
                return message;
            }
        });
    }
}