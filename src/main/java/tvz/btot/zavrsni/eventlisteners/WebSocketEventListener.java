package tvz.btot.zavrsni.eventlisteners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class WebSocketEventListener {
    @SuppressWarnings("unused")
    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectEvent event) {
        System.out.println("User connected.");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event) {
        System.out.println("User disconnected.");
    }

    @EventListener
    public void handleWebSocketSubscribeListener(final SessionSubscribeEvent event) {
        System.out.println("User subscribed to a channel.");
    }
}